/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.BulletinBean;
import com.iportal.biz.EmailBean;
import com.iportal.biz.EmailItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.bulletin.Bulletin;
import com.iportal.model.bulletin.Historic;
import com.iportal.model.bulletin.SubscriberTopic;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class SendBulletinAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(SendBulletinAction.class);
     
     /**
      * Create action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward create (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         SendBulletinForm bulletinForm = (SendBulletinForm) form;
         bulletinForm.reset(mapping, request);
         
         return mapping.findForward(Globals.FORWARD_STEP1);
     }


     /**
      * Read action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward read (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }

 		// Gets the action form
 		SendBulletinForm sendBulletinForm = (SendBulletinForm) form;
 		
 		HttpSession session = request.getSession();		
 		BulletinBean bulletinBean = (BulletinBean) session.getAttribute("bulletinBean");
 		
 		 sendBulletinForm.setTopicCode(bulletinBean.getTopicCode());
 		 sendBulletinForm.setBulletinCode(bulletinBean.getBulletin().getCode());
	
 		
         return mapping.findForward(Globals.FORWARD_STEP1);
     }  
     
     public ActionForward step2 (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         
         ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		SendBulletinForm sendBulletinForm = (SendBulletinForm) form;
 		
 		Session sess = null;
 		Bulletin bulletin = null;
 		
 		HttpSession session = request.getSession();		
 		BulletinBean bulletinBean = (BulletinBean) session.getAttribute("bulletinBean");
 		 		
 		
 		try {
 			sess = getHibernateSession();
 			
 			if (bulletinBean == null)
 	 			bulletinBean = new BulletinBean();
 	 		
 	 		bulletinBean.setTopicCode( sendBulletinForm.getTopicCode() );
 	 		
 	 		if (bulletinBean.getBulletin() == null || (! bulletinBean.getBulletin().getCode().equals(sendBulletinForm.getBulletinCode()))) {
 	 			bulletin = (Bulletin) sess.get(Bulletin.class, sendBulletinForm.getBulletinCode());
 	 			bulletinBean.setBulletin(bulletin);
 	 		}
 			
 			
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
 			logger.error(e.getMessage(), e);
 		} finally {
 			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}

 		// Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
 		session.setAttribute("bulletinBean", bulletinBean);
 		session.setAttribute("bulletin", bulletinBean.getBulletin());
 		
         return mapping.findForward(Globals.FORWARD_STEP2);
     } 
     
     public ActionForward step3 (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         
         ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		SendBulletinForm sendBulletinForm = (SendBulletinForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		List subscriberList = null;
 		ArrayList<EmailItem> mailList = null;
 		EmailItem mailItem = null;
 		
 		HttpSession session = request.getSession();		
 		BulletinBean bulletinBean = (BulletinBean) session.getAttribute("bulletinBean");
 		SysUser currentUser = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
 		
 		try {
 			sess = getHibernateSession();
 			
 			if (bulletinBean == null) {
 				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.lostData", BaseHelper.getApplicationBundleMessage("prompt.bulletin")));
 	 			saveMessages(request, messages);
 			}
 			
 			StringBuffer sql = new StringBuffer();
 	         			
 	        sql.append("from SubscriberTopic as subscriberTopic ");
            sql.append("where subscriberTopic.subscriber.enabled = 1 ");
            
            if (sendBulletinForm.getCodes()!=null && sendBulletinForm.getCodes().length >0)
            	sql.append(" and ( subscriberTopic.topic.code = :mainTopic or subscriberTopic.topic.code in (:codes)) ");
            else
            	sql.append(" and subscriberTopic.topic.code = :mainTopic ");
            sql.append("order by subscriberTopic.topic ");
           
            Query q = sess.createQuery(sql.toString());
            q.setParameter("mainTopic", bulletinBean.getTopicCode(), Hibernate.LONG);
            if (sendBulletinForm.getCodes()!=null && sendBulletinForm.getCodes().length >0)
            	q.setParameterList("codes", sendBulletinForm.getCodes(), Hibernate.LONG);
            subscriberList = q.list();
            //subscriberList = sess.createQuery(sql.toString()).setParameter("mainTopic", bulletinBean.getTopicCode(), Hibernate.LONG).setParameterList("codes", sendBulletinForm.getCodes(), Hibernate.LONG).list();
           
            EmailBean emailBean = null;
            Long topicCode = new Long(0);
            for(int i = 0; i < subscriberList.size(); i++) {
            	SubscriberTopic st = (SubscriberTopic) subscriberList.get(i);            	  
				
				//Si cambia el topic, crea un nuevo EmailBean
				if (!st.getTopic().getCode().equals(topicCode) ) {
					mailList = new ArrayList<EmailItem>();
            		emailBean = new EmailBean(st.getTopic().getName(), mailList, new Integer(mailList.size()));
            		bulletinBean.getEmailBeans().add(emailBean);
            	}
				
				topicCode = st.getTopic().getCode();
				
				mailItem = new EmailItem(st.getSubscriber().getFullName(), st.getSubscriber().getEmail());
				emailBean.addEmailItem(mailItem);
				//mailList.add(mailItem);
            }
 			
            
            
 			
 			tx = sess.beginTransaction();
 			
 			Bulletin bulletin = bulletinBean.getBulletin();
 			bulletin.setLastUpdateDate(Calendar.getInstance());
 			bulletin.setLastUpdateUser(currentUser.getFullName());
 			sess.save(bulletin);
 			Historic historic = new Historic();
 			historic.setBulletin(bulletin);
 			historic.setDate(bulletin.getLastUpdateDate());
 			historic.setRoleCode(currentUser.getRole().getCode());
 			historic.setRoleName(currentUser.getRole().getName());
 			historic.setUserCode(currentUser.getCode());
 			historic.setUserName(currentUser.getFullName());
 			sess.save(historic);
 			
 			tx.commit();
 			
 			//Audit Transaction
 			SysAuditHelper.audit(this, request, historic, historic.toString(), Globals.AUDIT_INSERT); 			
 	 		 			
 			
 		} catch (Exception e) {
 			if (tx != null) {
 			    tx.rollback();
 			}
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.unexpected"));
 			logger.error(e.getMessage(), e);
 		} finally {
			if (tx != null) {
				tx = null;
			}
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}

 		// Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
 		session.setAttribute("bulletinBean", bulletinBean);
 		
        return mapping.findForward(Globals.FORWARD_STEP3);
     } 

}
