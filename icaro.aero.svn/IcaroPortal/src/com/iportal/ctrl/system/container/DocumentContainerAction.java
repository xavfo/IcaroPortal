/*
 * Created Feb 16, 2006
 *	DocumentContainerAction.java
 */
package com.iportal.ctrl.system.container;

import java.util.List;

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
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.DocumentContainerFacade;
import com.iportal.model.container.DocumentContainer;
import com.yage.Globals;
import com.yage.file.FileManagerUtils;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.container.DocumentContainer}.
 *  
 * @author YAGE(hernan)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class DocumentContainerAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(DocumentContainerAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	ActionMessages messages = new ActionMessages();
    	DocumentContainerForm docForm =(DocumentContainerForm)form;
    	
    	Long docTypeCode = docForm.getDocTypeCode();
    	
    	try {
    		DocumentContainerFacade facade = new DocumentContainerFacade();
    		
    		HttpSession session = request.getSession();
        	
        	if (session.getAttribute("docTypeCode") != null) {
        		 docTypeCode = (Long)session.getAttribute("docTypeCode");
        		 session.removeAttribute("docTypeCode");
        	}
    		
    		if(docForm.getReset().booleanValue()) {
    			docForm.reset(mapping, request);
    			docForm.setDocTypeCode(docTypeCode);
    		}    		
    		
    		
	    	List results = facade.list(docForm);        
			
	    	// Save the List of results in request scope
			request.setAttribute("documentList", results);
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} 		
    	//this.removeForm(mapping, request);
		return mapping.findForward(Globals.FORWARD_LIST);
    }
		
    /*public ActionForward listNewsDocuments (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	ActionMessages messages = new ActionMessages();
        
    	try {
	    	ContainerFacade documents = new ContainerFacade();
	    	
	    	DocumentContainerForm currentForm = (DocumentContainerForm)form;
	    	
	    	/*List<DocumentContainer> results = documents.GetAllNewsDocuments(currentForm);        
			
	    	// Save the List of results in request scope
			request.setAttribute("documentContainerList", results);
			
			//Save the ownerObject code
			request.setAttribute("ownerCode", currentForm.getOwnerCode());
			
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} 		

		return mapping.findForward(Globals.FORWARD_LIST);
    }*/
	
    
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
		DocumentContainer document = null;    	
		DocumentContainerFacade facade = new DocumentContainerFacade(this.getServlet().getServletContext()); 
    	
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("docTypeCode", documentForm.getDocTypeCode()); 
    	
    	Transaction tx = null;
    	
    	try {
    		document =facade.save(documentForm, true);
            if (documentForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, document, document.getTitle(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, document,document.getTitle(), Globals.AUDIT_UPDATE);
            }   
    		
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

    		
    	}
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		this.removeForm(mapping, request);				
		

		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
		
		
    }

    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    	
    	// Gets the action form
		DocumentContainerForm documentContainerForm = (DocumentContainerForm)form;
        if ( isCancelled(request) ) {        	
            documentContainerForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		DocumentContainer document = null;	
		DocumentContainerFacade facade = new DocumentContainerFacade(getServlet().getServletContext());
		
		try {
			document = facade.save(documentContainerForm, true);
            if (document != null){
                documentContainerForm.setCode(document.getCode());
            }
            if (documentContainerForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, document, document.getTitle(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, document, document.getTitle(), Globals.AUDIT_UPDATE);
            }   
            
		} catch (Exception e) {			
			
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.documentType", this.getLocale(request)), documentContainerForm.getTitle(), null);
			
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		}
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		
		return mapping.findForward(Globals.FORWARD_FORM);
    }

    
    @SuppressWarnings("unchecked")
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
		DocumentContainer documentContainer = null;
		List<RowItem> fileList = null;
		int deletedEntities = 0;
		String currentTitle = "";
		
		try {
			sess = getHibernateSession();
			
			tx = sess.beginTransaction();
			
		    Long codes[] = documentForm.getCodes();
		    FileManagerUtils fileUtils = new FileManagerUtils(this.getServlet().getServletContext()); 
			
		    if (codes != null && codes.length > 0) {
			    if ( codes.length > 1) {			
			    	//lista los paths de documentos
			    	fileList = sess
							.createQuery(
									" select new com.iportal.biz.RowItem (doc.title, doc.path) from DocumentContainer doc where doc.code in (:codes) ")
							.setParameterList("codes", codes, Hibernate.LONG)
							.list();
					String hqlDelete = " delete DocumentContainer doc where doc.code in (:codes)";
					//for (int i =0; i < )
						
					//Borra la lista los paths de documentos que se eliminan
					for (RowItem rowItem : fileList) {
						currentTitle = rowItem.getName();
						try {
							fileUtils.deleteFile(rowItem.getNameValue());
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
						        
					deletedEntities = sess.createQuery(hqlDelete)
							.setParameterList("codes", codes, Hibernate.LONG)
							.executeUpdate();
					logger.debug("deleted: "+deletedEntities);
			    } else {
			    	documentContainer = (DocumentContainer) sess.load(
							DocumentContainer.class, codes[0]);
					sess.delete(documentContainer);
					SysAuditHelper.audit(this, request, documentContainer, documentContainer.getTitle(), Globals.AUDIT_DELETE, sess);
					currentTitle = documentContainer.getTitle();
					fileUtils.deleteFile(documentContainer.getPath());
			    }
		    }
		    
		    tx.commit();
			
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