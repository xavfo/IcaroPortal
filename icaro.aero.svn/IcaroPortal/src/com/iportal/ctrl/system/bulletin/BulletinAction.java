/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.bulletin.Bulletin;
import com.iportal.model.bulletin.Topic;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class BulletinAction extends BulletinTabBaseAction {

    
    private static Log logger = LogFactory.getLog(BulletinAction.class);

    
     /**
      * List action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward list (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
     	 BulletinForm listForm = (BulletinForm) form;
         
         Session sess = null;
         List results = null;
         RowItem currentItem = null;
         try {
     		
        	if (listForm.getItemCode()!=null && listForm.getItemCode().longValue()>0)
        		listForm.setCode(listForm.getItemCode());
        	sess = getHibernateSession();
     		StringBuffer sql = new StringBuffer();
     		
     		sql.append("From Bulletin as bulletin ");
     		sql.append("Where bulletin.topic.code = ? ");
     		
     		Query query = sess.createQuery(sql.toString());
     		query.setLong(0, listForm.getCode());
    		
     		results = query.list();
    		
    		currentItem = getRowItemTopic(listForm.getCode());
    		
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
 		} finally {
 			if (sess != null)
 				try {
 					sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}
        
 		// Save the List of results in request scope
 		request.setAttribute("bulletinList", results);
 		request.setAttribute("currentItem", currentItem);

 		return mapping.findForward(Globals.FORWARD_LIST);
     }
     
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
         BulletinForm bulletinForm = (BulletinForm) form;
         bulletinForm.reset(mapping, request);
         RowItem currentItem = getRowItemTopic(bulletinForm.getItemCode());
         request.setAttribute("currentItem", currentItem);
         
         return mapping.findForward(Globals.FORWARD_FORM);
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

 		
 		ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		BulletinForm bulletinForm = (BulletinForm) form;
 		
 		Session sess = null;
 		Bulletin bulletin = null;
 		RowItem currentItem = null;
 		try {
 			sess = getHibernateSession();
 			
 			if ( !(bulletinForm.getCode() != null && bulletinForm.getCode().longValue()>0) 
 					&& (bulletinForm.getCodes()!= null && bulletinForm.getCodes().length>0) )
 				bulletinForm.setCode(bulletinForm.getCodes()[0]);
 				
 			bulletin = (Bulletin) sess.get(Bulletin.class, bulletinForm.getCode());
 			
 			PropertyUtils.copyProperties(bulletinForm, bulletin);
 			bulletinForm.setTopicCode( bulletin.getTopic().getCode() );
 			
 			currentItem = getRowItemTopic(bulletinForm.getItemCode(), sess);
 			
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
 		 request.setAttribute("currentItem", currentItem);
         return mapping.findForward(Globals.FORWARD_FORM);
     }


     
     /**
      * Save action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward save (
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
 		BulletinForm bulletinForm = (BulletinForm) form;
 		Session sess = null;
 		RowItem currentItem = null;
 		
 		try {
 		    sess = getHibernateSession();
 			saveItem(bulletinForm, sess, request);
 			currentItem = getRowItemTopic(bulletinForm.getItemCode(), sess);
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
 		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		request.setAttribute("currentItem", currentItem);
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
     }
     
     
     /**
      * Apply action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward apply (
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
 		BulletinForm bulletinForm = (BulletinForm) form;
 		Session sess = null;
 		RowItem currentItem = null;
 		
 		try {
 		    sess = getHibernateSession();
 			saveItem(bulletinForm, sess, request);
 			currentItem = getRowItemTopic(bulletinForm.getItemCode(), sess);
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
 		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		request.setAttribute("currentItem", currentItem);
		
		return mapping.findForward(Globals.FORWARD_APPLY);
     }


     /**
      * Delete action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward delete (
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
		BulletinForm bulletinForm = (BulletinForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Bulletin bulletin = null;
		RowItem currentItem = null;
		
		try {
		    Long codes[] = bulletinForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
		    	List<Bulletin> deletedObjects = new ArrayList<Bulletin>();
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	bulletin = (Bulletin) sess.load(Bulletin.class, codes[i]);
					sess.delete(bulletin);
					deletedObjects.add(bulletin);
			    }
				tx.commit();
				
				for (Bulletin item : deletedObjects ){
					SysAuditHelper.audit(this, request, item, item.getTitle(),Globals.AUDIT_DELETE, sess);
				}
				
				currentItem = getRowItemTopic(bulletinForm.getItemCode(), sess);
		    } 
		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			boolean putMesage = checkControlledError (e, messages, MessageResources.getMessageResources(Constants.APPLICATION_BUNDLE).getMessage(this.getLocale(request), "prompt.bulletin"),   
														bulletin.getSubject(), null);
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		request.setAttribute("currentItem", currentItem);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
     
     /**
      * Send action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward send (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//ActionMessages messages = new ActionMessages();

		// Gets the action form
		/*SubscriberForm bulletinForm = (SubscriberForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Bulletin bulletin = null;
		List<Account> accounts = new ArrayList<Account>();

		try {
			
			sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
						
		    Long codes[] = bulletinForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {		    	
		    	for (int i = 0; i < codes.length; i++) {
		    		Account account = (Account) sess.load(Account.class, codes[i]);
		    		accounts.add(account);
		    	}	
		    	
				bulletin = (Bulletin) sess.load(Bulletin.class, bulletinForm.getBulletinCode());
				bulletin.setSent(new Boolean(true));
				bulletin.setDateSent(new Date());
				tx = sess.beginTransaction();
			    sess.saveOrUpdate(bulletin);
				tx.commit();
		    } 		    
			
		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}
*/
		// Report any messages we have discovered back to the original form
/*		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
*/
		// Report a success action
/*		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
*/		
 		// Save the List of results in request scope
/* 		request.setAttribute("bulletin", bulletin);
 		request.setAttribute("accounts", accounts);
*/
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
     
     public ActionForward load (
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
 		BulletinForm bulletinForm = (BulletinForm) form;
 		
 		Session sess = null;
 		Bulletin bulletin = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			bulletin = (Bulletin) sess.get(Bulletin.class, bulletinForm.getCode());
 			
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
 		
 		request.setAttribute("bulletin",bulletin);
 		
        return mapping.findForward(Globals.FORWARD_POPUP);
     }
     
     private void saveItem(BulletinForm bulletinForm, Session sess, HttpServletRequest request)
     throws Exception {
    	Bulletin bulletin = null;
    	Topic topic=null;
    	Transaction tx = null;
    	 
	 	if (bulletinForm.getCode() != null && bulletinForm.getCode().longValue() != 0L) {
			bulletin = (Bulletin) sess.load(Bulletin.class, bulletinForm.getCode());
		} else {
			bulletin = new Bulletin();
			bulletinForm.setCode(null);
		}
		
		topic = (Topic) sess.load(Topic.class, bulletinForm.getTopicCode());

		PropertyUtils.copyProperties(bulletin, bulletinForm);

		bulletin.setTopic(topic);

		//Persist data
		tx = sess.beginTransaction();
		sess.saveOrUpdate(bulletin);
		tx.commit();
		
		//Audit Transaction
		if ( bulletinForm.getCode() == null ) {
			SysAuditHelper.audit(this, request, bulletin, bulletin.getTitle(), Globals.AUDIT_INSERT);				
		} else {
			SysAuditHelper.audit(this, request, bulletin, bulletin.getTitle(),Globals.AUDIT_UPDATE);
		}
		
		bulletinForm.setCode(bulletin.getCode());
			
     } 
     
}
