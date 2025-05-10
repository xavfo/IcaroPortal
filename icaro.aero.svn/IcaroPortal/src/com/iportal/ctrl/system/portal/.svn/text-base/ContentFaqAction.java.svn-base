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
import org.apache.struts.action.Action;
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
import com.iportal.biz.facade.FaqContainerFacade;
import com.iportal.ctrl.system.container.FaqContainerForm;
import com.iportal.model.container.FaqContainer;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * java comment MenuAction
 * 
 * @author YAGE (jtite)
 * @version 1.0
 */
public class ContentFaqAction extends ContentContainerBaseAction {

	private static Log logger = LogFactory.getLog(ContentFaqAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionMessages messages = new ActionMessages();
		FaqContainerForm listForm =(FaqContainerForm)form;
    	Session sess = null;
    	
    	try {
    		List results = null;
        	if ( !(listForm.getItemCode()!=null && listForm.getItemCode().longValue()>0) )
    			if (listForm.getCode()!=null && listForm.getCode().longValue()>0)
    				listForm.setItemCode(listForm.getCode());
    		
        	sess = getHibernateSession();
        	FaqContainerFacade facade = new FaqContainerFacade(sess);
        	results = facade.listNotAssigned(listForm.getItemCode(), listForm, Content.class);
        	RowItem currentContent = getRowItemContent(listForm.getItemCode(), sess);
        	// Save the List of results in request scope
    		request.setAttribute("faqList", results);
    		request.setAttribute("currentContent", currentContent);
    		
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess!=null){
				sess.clear();
				sess.close();
			}
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
    	FaqContainerForm listForm =(FaqContainerForm) form;
    	Session sess = null;
    	try {
    		List results = null;
        	if ( !(listForm.getItemCode()!=null && listForm.getItemCode().longValue()>0) )
    			if (listForm.getCode()!=null && listForm.getCode().longValue()>0)
    				listForm.setItemCode(listForm.getCode());
    		
        	if(listForm.getReset().booleanValue()) {
    			listForm.reset(mapping, request);
    		}
        	
        	sess = getHibernateSession();
        	FaqContainerFacade facade = new FaqContainerFacade(sess);
        	results = facade.listAssigned(listForm.getItemCode(), listForm, Content.class);
        	RowItem currentContent = getRowItemContent(listForm.getItemCode(), sess);
        	
        	// Save the List of results in request scope
    		request.setAttribute("faqList", results);
    		request.setAttribute("currentContent", currentContent);
    		
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess!=null){
				sess.clear();
				sess.close();
			}
		}
    	
		return mapping.findForward(Globals.FORWARD_ASSIGNED_LIST);
    }	
	
	
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)) {
			return mapping.findForward(Globals.FORWARD_CANCEL);
		}

		FaqContainerForm listForm =(FaqContainerForm) form;
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
		FaqContainer faq = new FaqContainer();
		FaqContainerForm faqForm =(FaqContainerForm)form;
		RowItem currentContent = null;
		try {
			
			if ( !(faqForm.getCode()!=null && faqForm.getCode().longValue()>0)
					&& (faqForm.getCodes()!=null && faqForm.getCodes().length>0))
				faqForm.setCode(faqForm.getCodes()[0]);
			
			sess = getHibernateSession();
			faq = (FaqContainer) sess.get(FaqContainer.class, faqForm.getCode());
			PropertyUtils.copyProperties(faqForm, faq);
			
			faqForm.setCategoryCode(faq.getCategory().getCode());
			currentContent = getRowItemContent(faqForm.getItemCode(), sess);
			
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
		FaqContainerForm faqForm =(FaqContainerForm)form;
		RowItem currentContent = null;
		
		if (isCancelled(request)){
			currentContent = getRowItemContent(faqForm.getItemCode(), null);
			faqForm.reset(mapping, request);
			request.setAttribute("currentContent", currentContent);
			return mapping.findForward(Globals.FORWARD_CANCEL);
		}
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			Content content = saveItem(faqForm, this, request, sess);
			currentContent = getRowItemContent(faqForm.getItemCode(), sess);
			tx.commit();
			faqForm.reset(mapping, request);
            
            // Audit Transaction
            SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
							.getApplicationBundleMessage("prompt.faq", this
							.getLocale(request)), faqForm.getQuestion(), null);

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
		FaqContainerForm faqForm =(FaqContainerForm)form;
		RowItem currentContent = null;
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			Content content = saveItem(faqForm, this, request, sess);
			currentContent = getRowItemContent(faqForm.getItemCode(), sess);
			tx.commit();
            
            // Audit Transaction
            SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.faq", this
							.getLocale(request)), faqForm.getQuestion(), null);

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
		
		FaqContainerForm faqForm =(FaqContainerForm)form;
		try {
			
			sess = getHibernateSession();
			Content content = (Content)sess.load(Content.class, faqForm.getItemCode());
			
			Set<FaqContainer> faqs = content.getListOfRelatedFaq();
			
			tx = sess.beginTransaction();
		    Long codes[] = faqForm.getCodes();
		    Long faqCode = null;
		    if (codes != null){
		    	for (int i = 0; i < codes.length; i++){
		    		faqCode = codes[i];
		    		FaqContainer faq = (FaqContainer)sess.load(FaqContainer.class, faqCode);
		    		
		    		if ( !faqs.contains(faq)) {
		    			faqs.add(faq);
		    		}
		    	}
		    	content.setListOfRelatedFaq(faqs);
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
					.getApplicationBundleMessage("prompt.faq", this
							.getLocale(request)), faqForm.getQuestion(), null);

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
		
		FaqContainerForm faqForm =(FaqContainerForm)form;
		try {
			
			sess = getHibernateSession();
			Content content = (Content)sess.load(Content.class, faqForm.getItemCode());
			
			Set<FaqContainer> faqs = content.getListOfRelatedFaq();
			
			tx = sess.beginTransaction();
		    Long codes[] = faqForm.getCodes();
		    Long faqCode = null;
		    if (codes != null){
		    	for (int i = 0; i < codes.length; i++){
		    		faqCode = codes[i];
		    		FaqContainer faqContainer = (FaqContainer)sess.load(FaqContainer.class, faqCode);
		    		faqs.remove(faqContainer);
		    	}
		    	content.setListOfRelatedFaq(faqs);
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
					.getApplicationBundleMessage("prompt.faq", this
							.getLocale(request)), faqForm.getQuestion(), null);

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
	
	private Content saveItem(FaqContainerForm faqForm, Action action, HttpServletRequest request, Session sess)
	throws Exception{
		
		FaqContainer faq = null;
		FaqContainerFacade facade = new FaqContainerFacade(action, request, sess);

		faq = facade.save(faqForm, false);
		faqForm.setCode(faq.getCode());

		Content content = (Content) sess.load(Content.class, faqForm
				.getItemCode());

		Set<FaqContainer> faqs = content
				.getListOfRelatedFaq();
		boolean contains = faqs.contains(faq);

		if (!contains) { // it is not in the document list
			faqs.add(faq);
			content.setListOfRelatedFaq(faqs);
			sess.saveOrUpdate(content);
		}
        return content;
	}

}
