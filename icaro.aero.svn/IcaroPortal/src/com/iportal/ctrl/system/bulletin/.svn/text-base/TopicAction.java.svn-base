/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.bulletin.Topic;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class TopicAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(TopicAction.class);

    
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
         
         TopicForm listForm = (TopicForm) form;
         
         Session sess = null;
         List results = null;

         try {
     		sess = getHibernateSession();

     		StringBuffer sql = new StringBuffer();

     		sql.append("from Topic as topic ");
    		
     		if ( listForm.getOrderField() != null ) {
         		sql.append("order by topic.");
         		sql.append(listForm.getOrderField());
         		if ( listForm.getOrderAsc().booleanValue() ) {
         		    sql.append(" asc ");
         		} else {
         		    sql.append(" desc ");
         		}
     		}
			
			results = sess.createQuery(sql.toString()).list();
     		
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
 		request.setAttribute("topicList", results);

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
         TopicForm topicForm = (TopicForm) form;
         topicForm.reset(mapping, request);
         
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
 		TopicForm topicForm = (TopicForm) form;
 		
 		Session sess = null;
 		Topic topic = null;
 		
 		try {
 			if ( !(topicForm.getCode()!=null && topicForm.getCode().longValue()>0 ) 
 					&&(topicForm.getCodes()!=null && topicForm.getCodes().length>0 )) 
 				topicForm.setCode(topicForm.getCodes()[0]);
 			
 			sess = getHibernateSession();
 			
 			topic = (Topic) sess.load(Topic.class, topicForm.getCode());
 			
 			PropertyUtils.copyProperties(topicForm, topic);
 			
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
 		TopicForm topicForm = (TopicForm) form;
 		
 		saveItem(topicForm, messages, request);

 		// Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

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
 		ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		TopicForm topicForm = (TopicForm) form;
 		
 		saveItem(topicForm, messages, request);

 		// Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
 		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		return mapping.findForward(Globals.FORWARD_APPLY);
     }
     
     
     private void saveItem(TopicForm topicForm, ActionMessages messages, HttpServletRequest request) 
     throws Exception{
    	Session sess = null;
  		Transaction tx = null;
  		Topic topic = null;
    	 
    	try {
    		sess = getHibernateSession();
  		    
    		if (topicForm.getCode() != null && topicForm.getCode().longValue() != 0L) {
				topic = (Topic) sess.load(Topic.class, topicForm.getCode());
			} else {
				topic = new Topic();
				topicForm.setCode(null);
			} 						

			PropertyUtils.copyProperties(topic, topicForm);

			//Persist data
			tx = sess.beginTransaction();
			sess.saveOrUpdate(topic);
			tx.commit(); 
			
			//Audit Transaction
			if ( topicForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, topic, topic.getName(), Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, topic, topic.getName(), Globals.AUDIT_UPDATE);
			}
			
			topicForm.setCode(topic.getCode());
  		} catch (Exception e) {
  			if (tx != null) {
  			    tx.rollback();
  			}
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
		TopicForm topicForm = (TopicForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Topic topic = null;

		try {
		    Long codes[] = topicForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	topic = (Topic) sess.load(Topic.class, codes[i]);
					sess.delete(topic);
					SysAuditHelper.audit(this, request, topic, topic.getName(), Globals.AUDIT_DELETE, sess);
			    }
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

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

}
