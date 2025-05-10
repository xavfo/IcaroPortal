/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.List;
import java.util.Set;

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
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.DocumentAssignFacade;
import com.iportal.biz.facade.DocumentContainerFacade;
import com.iportal.ctrl.system.container.DocumentContainerForm;
import com.iportal.model.container.DocumentContainer;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * java comment MenuAction
 * 
 * @author YAGE (jtite)
 * @version 1.0
 */
public class ContentDocumentAction extends ContentContainerBaseAction {

	private static Log logger = LogFactory.getLog(ContentDocumentAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionMessages messages = new ActionMessages();
    	DocumentContainerForm listForm =(DocumentContainerForm)form;
    	
    	try {
    		List results = null;
        	if ( !(listForm.getItemCode()!=null && listForm.getItemCode().longValue()>0) )
    			if (listForm.getCode()!=null && listForm.getCode().longValue()>0)
    				listForm.setItemCode(listForm.getCode());
    		
        	DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
        	results = facade.getNotAssignedDocuments(listForm.getItemCode(), listForm, Content.class);
        	RowItem currentContent = getRowItemContent(listForm.getItemCode(), null);
        	// Save the List of results in request scope
    		request.setAttribute("documentList", results);
    		request.setAttribute("currentContent", currentContent);
    		
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		}
		
		return mapping.findForward(Globals.FORWARD_AVAILABLE_LIST);
	}

	public ActionForward listAssigned (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	DocumentContainerForm listForm =(DocumentContainerForm)form;
    	
    	try {
    		List results = null;
        	if ( !(listForm.getItemCode()!=null && listForm.getItemCode().longValue()>0) )
    			if (listForm.getCode()!=null && listForm.getCode().longValue()>0)
    				listForm.setItemCode(listForm.getCode());
    		
        	if(listForm.getReset().booleanValue()) {
        		listForm.clear();
    		}
        	
        	DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
        	results = facade.getAssignedDocuments(listForm.getItemCode(), listForm, Content.class, request);
        	RowItem currentContent = getRowItemContent(listForm.getItemCode(), null);
        	// Save the List of results in request scope
    		request.setAttribute("documentList", results);
    		request.setAttribute("currentContent", currentContent);
    		
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward(Globals.FORWARD_ASSIGNED_LIST);
    }	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)) {
			return mapping.findForward(Globals.FORWARD_CANCEL);
		}

		DocumentContainerForm listForm = (DocumentContainerForm) form;

		RowItem currentContent = getRowItemContent(listForm.getItemCode(), null);

		// Save the List of results in request scope
		request.setAttribute("currentContent", currentContent);

		return mapping.findForward(Globals.FORWARD_FORM);
	}

	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		Session sess = null;
		DocumentContainer document = new DocumentContainer();
		DocumentContainerForm documentForm =(DocumentContainerForm)form;
		RowItem currentContent = null;
		try {
			
			if ( !(documentForm.getCode()!=null && documentForm.getCode().longValue()>0)
					&& (documentForm.getCodes()!=null && documentForm.getCodes().length>0))
				documentForm.setCode(documentForm.getCodes()[0]);
			
			sess = getHibernateSession();
			document = (DocumentContainer) sess.get(DocumentContainer.class, documentForm.getCode());
			
			PropertyUtils.copyProperties(documentForm, document);
			documentForm.setDocTypeCode(document.getType().getCode());
			documentForm.setCategoryCode(document.getCategory().getCode());
			documentForm.setDisplayModeCode(document.getDisplayMode().getCode());
			currentContent = getRowItemContent(documentForm.getItemCode(), sess);
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.failure"));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess!=null) {
				sess.clear();
				sess.close();
			}
		}
		
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		request.setAttribute("currentContent", currentContent);
		return mapping.findForward(Globals.FORWARD_FORM);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		DocumentContainerForm documentForm = (DocumentContainerForm) form;
		RowItem currentContent = null;
		
		if (isCancelled(request)){
			currentContent = getRowItemContent(documentForm.getItemCode(), null);
			request.setAttribute("currentContent", currentContent);
			return mapping.findForward(Globals.FORWARD_CANCEL);
		}
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			Content content = saveItem(documentForm, sess, request);
			currentContent = getRowItemContent(documentForm.getItemCode(), sess);
			tx.commit();

            // Audit Transaction
            SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.document", this
							.getLocale(request)), documentForm.getTitle(), null);

			// si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null) {
				sess.clear();
				sess.close();
			}
		}

		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
				"message.success"));
		saveMessages(request, messages);
		request.setAttribute("currentContent", currentContent);
		return mapping.findForward(Globals.FORWARD_SUCCESS);
	}

	public ActionForward apply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		DocumentContainerForm documentForm = (DocumentContainerForm) form;
		RowItem currentContent = null;
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			Content content = saveItem(documentForm, sess, request);
			currentContent = getRowItemContent(documentForm.getItemCode(), sess);
			tx.commit();
            
            // Audit Transaction
            SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.document", this
							.getLocale(request)), documentForm.getTitle(), null);

			// si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null) {
				sess.clear();
				sess.close();
			}
		}

		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
				"message.success"));
		saveMessages(request, messages);
		request.setAttribute("currentContent", currentContent);
		return mapping.findForward(Globals.FORWARD_APPLY);
	}

	public ActionForward assign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		Session sess = null;
		Transaction tx = null;
		
		DocumentContainerForm documentForm = (DocumentContainerForm) form;
		if (isCancelled(request)){
			return mapping.findForward(Globals.FORWARD_LIST);
		}
		try {
			
			sess = getHibernateSession();
			Content content = (Content)sess.load(Content.class, documentForm.getItemCode());
			
			Set<DocumentContainer> documents = content.getListOfRelatedDocuments();
			
			tx = sess.beginTransaction();
		    Long codes[] = documentForm.getCodes();
		    Long docCode = null;
		    if (codes != null){
		    	for (int i = 0; i < codes.length; i++){
		    		docCode = codes[i];
		    		DocumentContainer document = (DocumentContainer)sess.load(DocumentContainer.class, docCode);
		    		if ( !documents.contains(document)) {
		    			documents.add(document);
		    		}
		    	}
		    	content.setListOfRelatedDocuments(documents);
		    }
		    sess.saveOrUpdate(content);
		    tx.commit();
            
            // Audit Transaction
             SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.document", this
							.getLocale(request)), documentForm.getTitle(), null);

			// si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null) {
				sess.clear();
				sess.close();
			}
		}

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
				"message.success"));
		saveMessages(request, messages);

		return mapping.findForward(Globals.FORWARD_LIST);
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		Session sess = null;
		Transaction tx = null;
		
		DocumentContainerForm documentForm = (DocumentContainerForm) form;
		try {
			
			sess = getHibernateSession();
			Content content = (Content)sess.load(Content.class, documentForm.getItemCode());
			
			Set<DocumentContainer> documents = content.getListOfRelatedDocuments();
			
			tx = sess.beginTransaction();
		    Long codes[] = documentForm.getCodes();
		    Long docCode = null;
		    if (codes != null){
		    	for (int i = 0; i < codes.length; i++){
		    		docCode = codes[i];
		    		DocumentContainer documentContainer = (DocumentContainer)sess.load(DocumentContainer.class, docCode);
		    		documents.remove(documentContainer);
                    // Audit Transaction
		    	}
		    	content.setListOfRelatedDocuments(documents);
		    }
		    sess.saveOrUpdate(content);
		    tx.commit();

            SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.document", this
							.getLocale(request)), documentForm.getTitle(), null);

			// si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null) {
				sess.clear();
				sess.close();
			}
		}

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
				"message.success"));
		saveMessages(request, messages);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
	}
	/**
     * Adds a document to an existent content 
     * @param documentForm
     * @param sess
     * @return
     * @throws Exception
	 */
	private Content saveItem(DocumentContainerForm documentForm, Session sess, HttpServletRequest request)
	throws Exception{
		
		DocumentContainer document = null;
		DocumentContainerFacade facade = new DocumentContainerFacade(sess, this.getServlet().getServletContext(),
                this, request);
		
		document = facade.save(documentForm, false);
        
		documentForm.setCode(document.getCode());

		Content content = (Content) sess.load(Content.class, documentForm.getItemCode());

		Set<DocumentContainer> documents = content.getListOfRelatedDocuments();
		boolean contains = documents.contains(document);

		if (!contains) { // it is not in the document list
			documents.add(document);
			content.setListOfRelatedDocuments(documents);
			sess.saveOrUpdate(content);
		}
		return content;
	}

}
