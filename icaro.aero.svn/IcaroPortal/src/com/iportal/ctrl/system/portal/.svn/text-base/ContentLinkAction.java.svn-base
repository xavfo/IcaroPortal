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
import com.iportal.biz.facade.LinkContainerFacade;
import com.iportal.ctrl.system.container.LinkContainerForm;
import com.iportal.model.container.LinkContainer;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * java comment MenuAction
 * 
 * @author YAGE (jtite)
 * @version 1.0
 */
public class ContentLinkAction extends ContentContainerBaseAction {

	private static Log logger = LogFactory.getLog(ContentLinkAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionMessages messages = new ActionMessages();
    	LinkContainerForm listForm =(LinkContainerForm)form;
    	Session sess = null;
    	
    	try {
    		List results = null;
        	if ( !(listForm.getItemCode()!=null && listForm.getItemCode().longValue()>0) )
    			if (listForm.getCode()!=null && listForm.getCode().longValue()>0)
    				listForm.setItemCode(listForm.getCode());
        	
        	sess = getHibernateSession();
        	LinkContainerFacade facade = new LinkContainerFacade(sess);
        	results = facade.listNotAssigned(listForm.getItemCode(), listForm, Content.class);
        	RowItem currentContent = getRowItemContent(listForm.getItemCode(), sess);
        	// Save the List of results in request scope
    		request.setAttribute("linkList", results);
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
    	LinkContainerForm listForm =(LinkContainerForm)form;
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
        	LinkContainerFacade facade = new LinkContainerFacade(sess);
        	results = facade.listAssigned(listForm.getItemCode(), listForm, Content.class);
        	RowItem currentContent = getRowItemContent(listForm.getItemCode(), null);
        	// Save the List of results in request scope
    		request.setAttribute("linkList", results);
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

		LinkContainerForm listForm = (LinkContainerForm) form;
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
		LinkContainer link = new LinkContainer();
		LinkContainerForm linkForm =(LinkContainerForm)form;
		RowItem currentContent = null;
		try {
			
			if ( !(linkForm.getCode()!=null && linkForm.getCode().longValue()>0)
					&& (linkForm.getCodes()!=null && linkForm.getCodes().length>0))
				linkForm.setCode(linkForm.getCodes()[0]);
			
			sess = getHibernateSession();
			link = (LinkContainer) sess.get(LinkContainer.class, linkForm.getCode());
			
			PropertyUtils.copyProperties(linkForm, link);
			linkForm.setCategoryCode(link.getCategory().getCode());
			linkForm.setTypeCode(link.getType().getCode());
			currentContent = getRowItemContent(linkForm.getItemCode(), sess);
			
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
		LinkContainerForm linkForm = (LinkContainerForm) form;
		RowItem currentContent = null;
		
		if (isCancelled(request)){
			currentContent = getRowItemContent(linkForm.getItemCode(), null);
			request.setAttribute("currentContent", currentContent);
			return mapping.findForward(Globals.FORWARD_CANCEL);
		}
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			Content content = saveItem(linkForm, sess);
			currentContent = getRowItemContent(linkForm.getItemCode(), sess);
			tx.commit();
            
            // Audit Transaction
            SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.link", this
							.getLocale(request)), linkForm.getTitle(), null);

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
		LinkContainerForm linkForm = (LinkContainerForm) form;
		RowItem currentContent = null;
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			Content content = saveItem(linkForm, sess);
			currentContent = getRowItemContent(linkForm.getItemCode(), sess);
			tx.commit();
            
            // Audit Transaction
            SysAuditHelper.audit(this, request, content,content.getTitle(), Globals.AUDIT_UPDATE);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			boolean putMesage = checkControlledError(e, messages, BaseHelper
					.getApplicationBundleMessage("prompt.link", this
							.getLocale(request)), linkForm.getTitle(), null);

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
		
		LinkContainerForm linkForm = (LinkContainerForm) form;
		try {
			
			sess = getHibernateSession();
			Content content = (Content)sess.load(Content.class, linkForm.getItemCode());
			
			Set<LinkContainer> links = content.getListOfRelatedLink();
			
			tx = sess.beginTransaction();
		    Long codes[] = linkForm.getCodes();
		    Long docCode = null;
		    if (codes != null){
		    	for (int i = 0; i < codes.length; i++){
		    		docCode = codes[i];
		    		LinkContainer link = (LinkContainer)sess.load(LinkContainer.class, docCode);
		    		
		    		if ( !links.contains(link)) {
		    			links.add(link);
		    		}
		    	}
		    	content.setListOfRelatedLink(links);
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
					.getApplicationBundleMessage("prompt.link", this
							.getLocale(request)), linkForm.getTitle(), null);

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
		
		LinkContainerForm linksForm = (LinkContainerForm) form;
		try {
			
			sess = getHibernateSession();
			Content content = (Content)sess.load(Content.class, linksForm.getItemCode());
			
			Set<LinkContainer> links = content.getListOfRelatedLink();
			
			tx = sess.beginTransaction();
		    Long codes[] = linksForm.getCodes();
		    Long docCode = null;
		    if (codes != null){
		    	for (int i = 0; i < codes.length; i++){
		    		docCode = codes[i];
		    		LinkContainer linkContainer = (LinkContainer)sess.load(LinkContainer.class, docCode);
		    		links.remove(linkContainer);
		    	}
		    	content.setListOfRelatedLink(links);
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
					.getApplicationBundleMessage("prompt.link", this
							.getLocale(request)), linksForm.getTitle(), null);

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
	
	private Content saveItem(LinkContainerForm linkForm, Session sess)
	throws Exception{
		
		LinkContainer link = null;
		LinkContainerFacade facade = new LinkContainerFacade(sess);

		link = facade.save(linkForm, false);
		linkForm.setCode(link.getCode());

		Content content = (Content) sess.load(Content.class, linkForm
				.getItemCode());

		Set<LinkContainer> links = content
				.getListOfRelatedLink();
		boolean contains = links.contains(link);

		if (!contains) { // it is not in the link list
			links.add(link);
			content.setListOfRelatedLink(links);
			sess.saveOrUpdate(content);
		}
        return content;
	}

}
