/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.system.SysAccessMode;
import com.iportal.model.system.SysModule;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SysModuleAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(SysModuleAction.class);

    
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
         
         SysModuleForm listForm = (SysModuleForm) form;
         
         Session sess = null;
         List results = null;

         try {
     		sess = getHibernateSession();

     		StringBuffer sql = new StringBuffer();
     		

     		sql.append("from SysModule as sysModule ");
     		if ( listForm.getOrderField() != null ) {
         		sql.append("order by sysModule.");
         		sql.append(listForm.getOrderField());
         		if ( listForm.getOrderAsc().booleanValue() ) {
         		    sql.append(" asc ");
         		} else {
         		    sql.append(" desc ");
         		}
     		}
     		Query query = sess.createQuery(sql.toString());
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
         
 		// Save the List of results in request scope
 		request.setAttribute("sysModuleList", results);

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
         SysModuleForm sysModuleForm = (SysModuleForm) form;
         sysModuleForm.reset(mapping, request);
         
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
 		SysModuleForm sysModuleForm = (SysModuleForm) form;
 		
 		Session sess = null;
 		SysModule sysModule = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			sysModule = (SysModule) sess.get(SysModule.class, sysModuleForm.getCode());
 			
 			PropertyUtils.copyProperties(sysModuleForm, sysModule);
 			if (sysModule.getParent() != null) {
 			    sysModuleForm.setParentCode( sysModule.getParent().getCode() );
 			}
 			
 			if (sysModule.getAccessMode() != null) {
 				sysModuleForm.setAccessModeCode(sysModule.getAccessMode().getCode());
 			}
 			
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
 		SysModuleForm sysModuleForm = (SysModuleForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		SysModule sysModule = null;
 		SysModule parent = null;
 		SysAccessMode accessMode = null;

 		try {
 		    sess = getHibernateSession();
 		    
 			if (sysModuleForm.getCode() != null && sysModuleForm.getCode().longValue() != 0L) {
 				sysModule = (SysModule) sess.load(SysModule.class, sysModuleForm.getCode());
 			} else {
 				sysModule = new SysModule();
 				sysModuleForm.setCode(null);
 			}
 			
 			int level = 0;
 			if (sysModuleForm.getParentCode() != null && sysModuleForm.getParentCode().longValue() != 0) {
 				parent = (SysModule) sess.load(SysModule.class, sysModuleForm.getParentCode());
 				level = parent.getLevel().intValue(); 
 			}
 			
 			if (sysModuleForm.getAccessModeCode() != null && sysModuleForm.getAccessModeCode().longValue() != 0) {
 				accessMode = (SysAccessMode) sess.load(SysAccessMode.class, sysModuleForm.getAccessModeCode());
 			}

 			PropertyUtils.copyProperties(sysModule, sysModuleForm);
 			sysModule.setParent(parent);
 			sysModule.setLevel(new Integer(level + 1));	
 			sysModule.setAccessMode(accessMode);

 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(sysModule);
 			tx.commit();
 			
this.reloadMenu(mapping, form, request, response);
 			
 			//Audit Transaction
			if ( sysModuleForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, sysModule, sysModule.getName(), Globals.AUDIT_INSERT);
			} else {
				SysAuditHelper.audit(this, request, sysModule, sysModule.getName(), Globals.AUDIT_UPDATE);
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
 		SysModuleForm sysModuleForm = (SysModuleForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		SysModule sysModule = null;

 		try {
 		    sess = getHibernateSession();
 		    
 			sysModule = (SysModule) sess.load(SysModule.class, sysModuleForm.getCode());
 			
 			//Delete object
 			tx = sess.beginTransaction();
 			sess.delete(sysModule);
 			tx.commit();
 			
 			this.reloadMenu(mapping, form, request, response);
			SysAuditHelper.audit(this, request, sysModule, sysModule.getName(), Globals.AUDIT_DELETE, sess);
 			
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
     
     private void reloadMenu (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      	StringBuffer menu = (StringBuffer) request.getSession().getAttribute(Constants.SYSTEM_MENU_KEY);
      	menu.delete(0, menu.length());
      	menu = null;
      	SystemMenuAction menuAction = new SystemMenuAction();
      	menuAction.execute(mapping, form, request, response);
      }

}
