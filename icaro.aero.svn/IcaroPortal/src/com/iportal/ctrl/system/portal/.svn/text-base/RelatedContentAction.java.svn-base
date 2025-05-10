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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.iportal.biz.system.portal.RelatedContentFacade;
import com.iportal.biz.system.portal.RelatedContentForm;
import com.iportal.model.portal.Content;
import com.iportal.model.portal.RelatedContent;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * java comment MenuAction
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 */
public class RelatedContentAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(RelatedContentAction.class);
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	List<RelatedContent> results = null;

        try {
        	RelatedContentForm relatedForm = (RelatedContentForm) form;
        	RelatedContentFacade relatedFacade = new RelatedContentFacade();
        	
    		results = relatedFacade.getAll(relatedForm);
    		
    		Content currentContent = relatedFacade.getContent(relatedForm.getCode());
    		request.setAttribute("currentContent", currentContent);
    		
        } catch (Exception e) {
        	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		}
        
		// Save the List of results in request scope
		request.setAttribute("relatedList", results);
		
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
        RelatedContentForm relatedForm = (RelatedContentForm) form;
        relatedForm.reset(mapping, request);
        
        RelatedContentFacade relatedFacade = new RelatedContentFacade();
        Content currentContent = relatedFacade.getContent(relatedForm.getItemCode());
		request.setAttribute("currentContent", currentContent);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }

    public ActionForward read (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	RelatedContentForm relatedForm = (RelatedContentForm) form;
		
		try {
			RelatedContentFacade relatedFacade = new RelatedContentFacade();
     		relatedFacade.getByCode(relatedForm);
			
     		Content currentContent = relatedFacade.getContent(relatedForm.getItemCode());
    		request.setAttribute("currentContent", currentContent);
     		
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
    		logger.info(e.getMessage());
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
    	if (isCancelled(request)) {
    		return mapping.findForward(Globals.FORWARD_CANCEL);
    	}
    	ActionMessages messages = new ActionMessages();
    	try {
    		RelatedContentForm relatedForm = (RelatedContentForm) form;
     		RelatedContentFacade relatedFacade = new RelatedContentFacade(this, request);
     		relatedFacade.save(relatedForm, request);
     		
     		Content currentContent = relatedFacade.getContent(relatedForm.getItemCode());
    		request.setAttribute("currentContent", currentContent);
     		
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
  			logger.error(e.getMessage(), e);
 		}
 		
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	try {
    		RelatedContentForm relatedForm = (RelatedContentForm) form;
     		RelatedContentFacade relatedFacade = new RelatedContentFacade(this, request);
     		relatedFacade.save(relatedForm, request);
         	
     		Content currentContent = relatedFacade.getContent(relatedForm.getItemCode());
    		request.setAttribute("currentContent", currentContent);
     		
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
  			logger.error(e.getMessage(), e);
 		}
 		
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		
		return mapping.findForward(Globals.FORWARD_APPLY);
    }

    public ActionForward delete (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
		ActionMessages messages = new ActionMessages();
		try {
			RelatedContentForm relatedForm = (RelatedContentForm) form;
			RelatedContentFacade relatedFacade = new RelatedContentFacade(this, request);
			relatedFacade.delete(relatedForm.getCodes());
		    
			Content currentContent = relatedFacade.getContent(relatedForm.getItemCode());
    		request.setAttribute("currentContent", currentContent);
		    
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }





}
