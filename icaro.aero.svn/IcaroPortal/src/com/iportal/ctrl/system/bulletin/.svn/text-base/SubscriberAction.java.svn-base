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
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.model.Language;
import com.iportal.model.bulletin.Subscriber;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class SubscriberAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(SubscriberAction.class);

    
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
    	 ActionMessages messages = new ActionMessages();
         SubscriberListForm listForm = (SubscriberListForm) form;
         
         Session sess = null;
         List results = null;
	     
         if (listForm.getSearch() != null && listForm.getSearch().booleanValue()) {
	         
        	 if ( !(listForm.getName()!= null && listForm.getName().trim().length()>0) 
        			 && !(listForm.getEmail()!= null && listForm.getEmail().trim().length()>0)) {
        		 Language lang = (Language)request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        		 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.nameOrEmail",lang.getLocale())));
        	 } else {
        		 StringBuffer sql = new StringBuffer();
    	         ArrayList<Object> params = new ArrayList<Object>();
    	         ArrayList<NullableType> types = new ArrayList<NullableType>();
    	     
    	         try {
    	     		sess = getHibernateSession();
    	     		sql.append("from SubscriberTopic as subscriberTopic ");
    	            sql.append("where subscriberTopic.topic.code = ? ");
    	            params.add(listForm.getCode());
    	            types.add(Hibernate.LONG); 
    	            
    	            if (listForm.getName()!=null && listForm.getName().length()>0) {
    	            	sql.append(" and upper(subscriberTopic.subscriber.firstName || ' ' || subscriberTopic.subscriber.lastName) like ? ");
    	                params.add("%".concat(listForm.getName().toUpperCase()).concat("%"));
    	                types.add(Hibernate.STRING);
    	            }
    	            
    	            if (listForm.getEmail()!=null && listForm.getEmail().length()>0) {
    	            	sql.append(" and upper(subscriberTopic.subscriber.email) like ? ");
    	                params.add("%".concat(listForm.getEmail().toUpperCase()).concat("%"));
    	                types.add(Hibernate.STRING);
    	            }
    	            
    	            if (listForm.getCountryCode() != null && listForm.getCountryCode() > 0L) {
    	                sql.append(" and subscriberTopic.subscriber.country.code = ? ");
    	                params.add(listForm.getCountryCode());
    	                types.add(Hibernate.LONG);
    	            }
    	            
    	            if (listForm.getCity() != null && listForm.getCity().length() > 0) {
    	                sql.append(" and subscriberTopic.subscriber.city = ? ");
    	                params.add(listForm.getCity());
    	                types.add(Hibernate.STRING);
    	            }
    	            
    	            if (listForm.getFromDate() != null && listForm.getFromDate().length() > 0) {
    	                sql.append(" and subscriberTopic.subscriber.systemDate >= ? ");
    	                Calendar from = listForm.getFrom();
    	                from.set(Calendar.HOUR,0);
    	                from.set(Calendar.MINUTE,0);
    	                from.set(Calendar.SECOND,0);
    	                from.set(Calendar.MILLISECOND,0);   
    	                params.add(from);
    	                types.add(Hibernate.CALENDAR);
    	            }
    	
    	            if (listForm.getToDate() != null && listForm.getToDate().length() > 0) {
    	                sql.append(" and subscriberTopic.subscriber.systemDate < ? ");
    	                Calendar to = listForm.getTo();
    	                to.set(Calendar.HOUR,0);
    	                to.set(Calendar.MINUTE,0);
    	                to.set(Calendar.SECOND,0);
    	                to.set(Calendar.MILLISECOND,0);
    	                to.add(Calendar.DAY_OF_WEEK,1);
    	                params.add(to);
    	                types.add(Hibernate.CALENDAR);
    	            }
    	            
    	     		if ( listForm.getOrderField() != null ) {
    	         		sql.append("order by subscriberTopic.subscriber.");
    	         		sql.append(listForm.getOrderField());
    	         		if ( listForm.getOrderAsc().booleanValue() ) {
    	         		    sql.append(" asc ");
    	         		} else {
    	         		    sql.append(" desc ");
    	         		}
    	     		}
    	            
    	            Object[] arrayParams = params.toArray();
    	            Query query = sess.createQuery(sql.toString());
    	            for (int i = 0; i < types.size(); i++) {
    	                query.setParameter(i, arrayParams[i], (Type) types.get(i));
    	            }
    	            
    	            results = query.list(); 
    	     		
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
        	 }
        }
        
         if (!messages.isEmpty()) {
	  		saveMessages(request, messages);
		 } 
        
 		// Save the List of results in request scope
 		request.setAttribute("subscriberList", results);

 		return mapping.findForward(Globals.FORWARD_LIST);
     }

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
     public ActionForward changeStatus (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
    	 ActionMessages messages = new ActionMessages();
    	 SubscriberListForm subscriberForm = (SubscriberListForm) form;
         Session sess = null;
         Transaction tx = null;
    	 try {
    		 sess = getHibernateSession();
    		 Subscriber subscriber = (Subscriber) sess.get(Subscriber.class, subscriberForm.getItemCode());
    		 if (subscriber.getEnabled().booleanValue()) {
    			 subscriber.setEnabled(false);
    		 } else {
    			 subscriber.setEnabled(true);
    		 }
    		 tx = sess.beginTransaction();
    		 sess.saveOrUpdate(subscriber);
    		 tx.commit();
    	 } catch (Exception e) {
    		 if (tx != null)
    			 tx.rollback();
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
    	 
		 if (!messages.isEmpty()) {
	  			saveMessages(request, messages);
		 }
		 
    	 return mapping.findForward(Globals.FORWARD_SUCCESS); 
     }
     
}
