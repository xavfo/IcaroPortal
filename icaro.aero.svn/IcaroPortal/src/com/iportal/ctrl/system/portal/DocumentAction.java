/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Document;
import com.iportal.model.DocumentType;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * java comment DocumentAction
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 */
public class DocumentAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(DocumentAction.class);

    
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
         
         DocumentForm listForm = (DocumentForm) form;
         
         Session sess = null;
         List results = null;
  		 ArrayList params = new ArrayList();
 		 ArrayList types = new ArrayList(); 		 
 		  		 

         try {
     		sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);    		

     		StringBuffer sql = new StringBuffer();

     		sql.append("from Document as document ");
     		sql.append("where 1 = 1 ");
     		     		        		
     		if ( listForm.getOrderField() != null ) {
         		sql.append("order by document.");
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
         
 		// Save the List of results in request scope
 		request.setAttribute("documentList", results); 		

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
         /**
          * Combrobar si esto hace algo de probecho
          * 
          */
         //DocumentForm documentForm = (DocumentForm) form;
         
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
 		DocumentForm documentForm = (DocumentForm) form;
 		
 		Session sess = null;
 		Document document = null;
 		
 		try {
 			sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 			
 			document = (Document) sess.load(Document.class, documentForm.getCode());
 			
 			PropertyUtils.copyProperties(documentForm, document);
 			documentForm.setDocumentTypeCode( document.getType().getCode() );
 			
 			/*
 			if ( document.getSection() != null ) {
 				documentForm.setSectionCode( document.getSection().getCode() );
 				request.setAttribute("section", document.getSection());
 			} 			
			*/
 			
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
 		DocumentForm documentForm = (DocumentForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		Document document = null; 		
 		DocumentType documentType = null; 		

 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID); 		     		    
 		    /*
     		if ( documentForm.getSectionCode() != null && documentForm.getSectionCode().longValue() > 0 ) {
     			section = (Section) sess.load(Section.class, documentForm.getSectionCode());
     			request.setAttribute("section", section);
     		} 
     		*/
 			if (documentForm.getCode() != null && documentForm.getCode().longValue() != 0L) {
 				document = (Document) sess.load(Document.class, documentForm.getCode());
 			} else { 				
 				document = new Document(); 				
 				documentForm.setCode(null);
 			}
 			
 			documentType = (DocumentType) sess.load(DocumentType.class, documentForm.getDocumentTypeCode());
 			
 			PropertyUtils.copyProperties(document, documentForm); 		
 			document.setType(documentType);
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(document);			
 			tx.commit();
            
            // Audit Transaction
             if ( documentForm.getCode() == null ) {
                 SysAuditHelper.audit(this, request, document, document.getName(), Globals.AUDIT_INSERT);
             } else {
                 SysAuditHelper.audit(this, request, document, document.getName(), Globals.AUDIT_UPDATE);
             }
 			
 		} catch (Exception e) {
 			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.document", this.getLocale(request)), documentType.getName(), null);
 			
 			if (tx != null) {
 			    tx.rollback();
 			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}

 			//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
		DocumentForm documentForm = (DocumentForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Document document = null;

		try {
		    Long codes[] = documentForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	document = (Document) sess.load(Document.class, codes[i]);
					sess.delete(document);
                    SysAuditHelper.audit(this, request, document, document.getName(), Globals.AUDIT_DELETE, sess);		        
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
