/*
 * Created Feb 16, 2006
 *	DocumentContainerAction.java
 */
package com.iportal.ctrl.system.container;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.iportal.biz.facade.DocumentAssignFacade;
import com.iportal.biz.facade.DocumentContainerFacade;
import com.iportal.model.container.DocumentContainer;
import com.iportal.model.container.FaqContainer;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualizaci�n y eliminaci�n)
 * sobre clase persistente {@link com.iportal.model.container.DocumentContainer}.
 *  
 * @author YAGE(martha)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class FaqDocumentAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(FaqDocumentAction.class);
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	DocumentContainerForm listForm =(DocumentContainerForm)form;
		List results = null;
		HttpSession session = request.getSession();
    	Long code = (Long)session.getAttribute("faqCode");
    	
    	DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
    	results = facade.getAssignedDocuments(code, listForm, FaqContainer.class, request);
   		
    	// Save the List of results in request scope
		request.setAttribute("documentList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }
 
    public ActionForward create (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        DocumentContainerForm documentContainerForm = (DocumentContainerForm) form;
        
        Long docTypeCode = documentContainerForm.getDocTypeCode();
        documentContainerForm.reset(mapping, request);
        documentContainerForm.setDocTypeCode(docTypeCode);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }

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
		DocumentContainerForm documentForm = (DocumentContainerForm)form;
		
		Session sess = null;
		DocumentContainer document = null;
		
		try {
			sess = getHibernateSession();
			
			document = (DocumentContainer) sess.load(DocumentContainer.class, documentForm.getCode());
			
			PropertyUtils.copyProperties(documentForm, document);
			
			documentForm.setDocTypeCode(document.getType().getCode());
			documentForm.setCategoryCode(document.getCategory().getCode());
			documentForm.setDisplayModeCode(document.getDisplayMode().getCode());
			
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
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

    public ActionForward save (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	DocumentContainerForm documentForm = (DocumentContainerForm)form;
    	
    	if ( isCancelled(request) ) { 
    		Long docTypeCode = documentForm.getDocTypeCode();
    		documentForm.reset(mapping, request);
    		documentForm.setDocTypeCode(docTypeCode);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages	 = new ActionMessages();    	
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("docTypeCode", documentForm.getDocTypeCode()); 
    	
    	Transaction tx = null;
    	Session sess = null;
    	DocumentContainer documentContainer = null;
    	Long code = (Long)session.getAttribute("faqCode");
        FaqContainer faqContainer = null;
    	try {
    		sess = getHibernateSession();
    		tx = sess.beginTransaction();
    		DocumentContainerFacade facade = new DocumentContainerFacade(sess, 
                    this.getServlet().getServletContext());
    		documentContainer = facade.save(documentForm, false);
              
			if (code != null ){
				faqContainer = (FaqContainer)sess.load(FaqContainer.class, code);
				Set<DocumentContainer> documents = faqContainer.getDocuments();
				boolean contains = documents.contains(documentContainer);
				if (! contains){ // it is not in the document list
					documents.add(documentContainer);
                    faqContainer.setDocuments(documents);
					sess.saveOrUpdate(faqContainer);
				}
			}
            tx.commit();
            
            if (documentForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, documentContainer, documentContainer.getTitle(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, documentContainer, documentContainer.getTitle(), Globals.AUDIT_UPDATE);
            }
            SysAuditHelper.audit(this, request, faqContainer, faqContainer.getQuestion(), Globals.AUDIT_UPDATE);
             
    	} catch(Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.document", this.getLocale(request)), documentForm.getTitle(), null);
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
    	} finally {
    		if (sess != null){
    			sess.close();
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

		//this.removeForm(mapping, request);				

		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
		
		
    }
    
    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	DocumentContainerForm documentForm = (DocumentContainerForm)form;
    	
    	if ( isCancelled(request) ) { 
    		Long docTypeCode = documentForm.getDocTypeCode();
    		documentForm.reset(mapping, request);
    		documentForm.setDocTypeCode(docTypeCode);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages	 = new ActionMessages();    	
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("docTypeCode", documentForm.getDocTypeCode()); 
    	
    	Transaction tx = null;
    	Session sess = null;
    	DocumentContainer documentContainer = null;
    	Long code = (Long)session.getAttribute("faqCode");
        FaqContainer faqContainer = null;
    	try {
    		sess = getHibernateSession();
    		tx = sess.beginTransaction();
    		DocumentContainerFacade facade = new DocumentContainerFacade(sess, 
                    this.getServlet().getServletContext());
    		documentContainer = facade.save(documentForm, false);
              
			if (code != null ){
				faqContainer = (FaqContainer)sess.load(FaqContainer.class, code);
				Set<DocumentContainer> documents = faqContainer.getDocuments();
				boolean contains = documents.contains(documentContainer);
				if (! contains){ // it is not in the document list
					documents.add(documentContainer);
                    faqContainer.setDocuments(documents);
					sess.saveOrUpdate(faqContainer);
				}
			}
            tx.commit();
            
            if (documentForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, documentContainer, documentContainer.getTitle(),Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, documentContainer, documentContainer.getTitle(),Globals.AUDIT_UPDATE);
            }
            SysAuditHelper.audit(this, request, faqContainer, faqContainer.getQuestion(), Globals.AUDIT_UPDATE);
             
    	} catch(Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.document", this.getLocale(request)), documentForm.getTitle(), null);
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
    	} finally {
    		if (sess != null){
    			sess.close();
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

		//this.removeForm(mapping, request);				

		return mapping.findForward(Globals.FORWARD_FORM);  //the default action
		
		
    }
    
    /**
     * Unbound a group of documents from the current FAQ 
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
		DocumentContainerForm documentForm = (DocumentContainerForm) form;
		
		Session sess = null;
		Transaction tx = null;
		String currentTitle = "";
		Long documentCode = null;
		
		HttpSession session = request.getSession();
		Long code = (Long)session.getAttribute("faqCode");
		if (code != null){
			try {
				sess = getHibernateSession();
                FaqContainer faqContainer = (FaqContainer)sess.load(FaqContainer.class, code);
				
				Set<DocumentContainer> documents = faqContainer.getDocuments();
				
				tx = sess.beginTransaction();
			    Long codes[] = documentForm.getCodes();
			    if (codes != null){
			    	for (int i = 0; i < codes.length; i++){
			    		documentCode = codes[i];
			    		DocumentContainer documentContainer = (DocumentContainer)sess.load(DocumentContainer.class, documentCode);
			    		documents.remove(documentContainer);
			    	}
                    faqContainer.setDocuments(documents);
			    }
			    sess.saveOrUpdate(faqContainer);
			    tx.commit();
                
                SysAuditHelper.audit(this, request, faqContainer, faqContainer.getQuestion(), Globals.AUDIT_UPDATE);
			} catch (Exception e) {
			
				//This code was copied from MCPartners, verify what does the next line.
				boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.document", this.getLocale(request)), currentTitle, null);
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
		}

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
		
    }

}
