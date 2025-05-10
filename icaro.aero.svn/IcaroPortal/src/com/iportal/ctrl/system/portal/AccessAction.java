/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Access;
import com.iportal.model.AccessUrl;
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
public class AccessAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(AccessAction.class);

    
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
         
         AccessForm listForm = (AccessForm) form;
         
         Session sess = null;
         List results = null;
         
         if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer sql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
				ArrayList<NullableType> types = new ArrayList<NullableType>();

          		sql.append("from Access as access ");    		
          		sql.append("where 1 = 1 ");
          		
          		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
		   		    String name = "%" + listForm.getName() + "%";
		   		    sql.append(" and access.name like ? ");
		   		    params.add(name);
		   		    types.add(Hibernate.STRING);  
	    		}
          		
          		if (listForm.getAccessUrlCode() != null && listForm.getAccessUrlCode().longValue() > 0L ) {
		   		    sql.append(" and access.accessUrl.code = ? ");
		   		    params.add(listForm.getAccessUrlCode());
		   		    types.add(Hibernate.LONG);  
	    		}
          		
          		if ( listForm.getOrderField() != null ) {
              		sql.append("order by access.");
              		sql.append(listForm.getOrderField());
              		if ( listForm.getOrderAsc().booleanValue() ) {
              		    sql.append(" asc ");
              		} else {
              		    sql.append(" desc ");
              		}
          		}
          		sess = getHibernateSession();
	     		Query query = sess.createQuery(sql.toString());
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, params.get(i), types.get(i));
				}
				
				query.setCacheable(true);
				results = query.list ();
          		
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
      		request.setAttribute("accessList", results);
         }
         

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
         AccessForm accessForm = (AccessForm) form;
         accessForm.reset(mapping, request);
         
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
 		AccessForm accessForm = (AccessForm) form;
 		
 		Session sess = null;
 		Access access = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			access = (Access) sess.load(Access.class, accessForm.getCode());
 			
 			PropertyUtils.copyProperties(accessForm, access);
 			accessForm.setAccessUrlCode(access.getAccessUrl().getCode() );
 			
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
         
         //Gets the action form
         AccessForm accessForm = (AccessForm) form;

 		 this.saveItem(accessForm, messages, request);
 		 
 		 //Report any messages we have discovered back to the original form
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
  		
  		//Report a success action
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
         
    	 if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         
         ActionMessages messages = new ActionMessages();  		
         
         //Gets the action form
         AccessForm accessForm = (AccessForm) form;

 		 Access access = this.saveItem(accessForm, messages, request);
         if (access != null){
        	 accessForm.setCode(access.getCode());
         }
 		 
 		 //Report any messages we have discovered back to the original form
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
  		
  		//Report a success action
 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
 		saveMessages(request, messages);
 		
		 return mapping.findForward(Globals.FORWARD_APPLY);
		 
     }
     
     public Access saveItem ( AccessForm accessForm, ActionMessages messages, HttpServletRequest request)  throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		Access access = null;
  		AccessUrl accessUrl = null;
  		

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (accessForm.getCode() != null && accessForm.getCode().longValue() != 0L) {
  				access = (Access) sess.load(Access.class, accessForm.getCode());
  			} else {
  				access = new Access();
  				accessForm.setCode(null);
  			}
  			
  			if (access.getAccessUrl() == null || (!access.getAccessUrl().getCode().equals(accessForm.getAccessUrlCode()))) {
  				accessUrl = (AccessUrl) sess.load(AccessUrl.class, accessForm.getAccessUrlCode());
  				access.setAccessUrl(accessUrl); 				
 			}
  			
  			PropertyUtils.copyProperties(access, accessForm);
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(access);			
  			tx.commit();
            
            // Audit Transaction
            if (accessForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, access, access.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, access, access.getName(), Globals.AUDIT_UPDATE);
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
  		return access;
 		
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
		AccessForm accessForm = (AccessForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Access access = null;

		try {
		    Long codes[] = accessForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	access = (Access) sess.load(Access.class, codes[i]);
					sess.delete(access);
                    SysAuditHelper.audit(this, request, access, access.getName(), Globals.AUDIT_DELETE, sess);                    
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.access", this.getLocale(request)), access.getName(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
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

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

}
