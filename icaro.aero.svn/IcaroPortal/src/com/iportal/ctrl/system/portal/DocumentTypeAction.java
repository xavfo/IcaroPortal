/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

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

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.DocumentType;
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
public class DocumentTypeAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(DocumentTypeAction.class);

    
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
         
         DocumentTypeForm listForm = (DocumentTypeForm) form;
         
         Session sess = null;
         List results = null;

         try {
     		sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);

     		StringBuffer sql = new StringBuffer();

     		sql.append("from DocumentType as documentType ");    		
     		if ( listForm.getOrderField() != null ) {
         		sql.append("order by documentType.");
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
 		request.setAttribute("documentTypeList", results);

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
         DocumentTypeForm documentTypeForm = (DocumentTypeForm) form;
         documentTypeForm.reset(mapping, request);
         
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

 		//HttpSession session = request.getSession();
 		ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		DocumentTypeForm documentTypeForm = (DocumentTypeForm) form;
 		
 		Session sess = null;
 		DocumentType documentType = null;
 		
 		Long applyCode = 0L;
 		
 		try {
 			sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 			
 			if (request.getAttribute("isApply") != null)
 				applyCode = (Long) request.getAttribute("isApply");
 			
 			if (applyCode.longValue() > 0)  //Comes from an apply action
 				documentType = (DocumentType) sess.load(DocumentType.class, applyCode);
 			else  			
 				documentType = (DocumentType) sess.load(DocumentType.class, documentTypeForm.getCode());
 			
 			
 			PropertyUtils.copyProperties(documentTypeForm, documentType);
			 			
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

 		//HttpSession session = request.getSession();
 		ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		DocumentTypeForm documentTypeForm = (DocumentTypeForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		DocumentType documentType = null;

 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (documentTypeForm.getCode() != null && documentTypeForm.getCode().longValue() != 0L) {
 				documentType = (DocumentType) sess.load(DocumentType.class, documentTypeForm.getCode());
 			} else {
 				documentType = new DocumentType();
 				documentTypeForm.setCode(null);
 			}
 			
 			PropertyUtils.copyProperties(documentType, documentTypeForm);
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(documentType);			
 			tx.commit();
            
            // Audit Transaction
            if (documentTypeForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, documentType, documentType.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, documentType, documentType.getName(), Globals.AUDIT_UPDATE);
            }
 			
 		} catch (Exception e) {
 			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.documentType", this.getLocale(request)), documentType.getName(), null);
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

		return mapping.findForward(Globals.FORWARD_LIST);
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

 		//HttpSession session = request.getSession();
 		ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		DocumentTypeForm documentTypeForm = (DocumentTypeForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		DocumentType documentType = null;

 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (documentTypeForm.getCode() != null && documentTypeForm.getCode().longValue() != 0L) {
 				documentType = (DocumentType) sess.load(DocumentType.class, documentTypeForm.getCode());
 			} else {
 				documentType = new DocumentType();
 				documentTypeForm.setCode(null);
 			}
 			
 			PropertyUtils.copyProperties(documentType, documentTypeForm);
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(documentType);			
 			tx.commit();
 			
 			request.setAttribute("isApply", documentType.getCode());
            
            // Audit Transaction
            if (documentTypeForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, documentType,documentType.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, documentType, documentType.getName(), Globals.AUDIT_UPDATE);
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

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		DocumentTypeForm documentTypeForm = (DocumentTypeForm) form;
		
		Session sess = null;
		Transaction tx = null;
		DocumentType documentType = null;

		try {
		    Long codes[] = documentTypeForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	documentType = (DocumentType) sess.load(DocumentType.class, codes[i]);
					sess.delete(documentType);
                    SysAuditHelper.audit(this, request, documentType,documentType.getName(), Globals.AUDIT_DELETE, sess);								        
			    }
				tx.commit();
		    } 
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.documentType", this.getLocale(request)), documentType.getName(), null);
			
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
