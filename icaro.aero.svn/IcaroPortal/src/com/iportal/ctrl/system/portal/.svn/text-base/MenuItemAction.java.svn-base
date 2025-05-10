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

import com.iportal.biz.RowItem;
import com.iportal.biz.system.portal.MenuBean;
import com.iportal.biz.system.portal.MenuItemFacade;
import com.iportal.biz.system.portal.MenuItemForm;
import com.iportal.biz.system.portal.SearchContentForm;
import com.iportal.model.portal.MenuItem;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * java comment MenuAction
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 */
public class MenuItemAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(MenuItemAction.class);
    
    public ActionForward tree (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	List<RowItem> results = null;

        try {
        	SearchContentForm searchForm = (SearchContentForm) form;
    		MenuItemFacade menuItemFacade = new MenuItemFacade();
    		
    		results = menuItemFacade.getAllByTree(searchForm, request);
    		
        } catch (Exception e) {
        	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		}
        
		// Save the List of results in request scope
		request.setAttribute("resultList", results);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	List<MenuItem> results = null;

        try {
        	MenuItemForm menuItemForm = (MenuItemForm) form;
    		MenuItemFacade menuItemFacade = new MenuItemFacade();
    		
    		results = menuItemFacade.getAll(menuItemForm, request);
    		
    		MenuBean menuBean = menuItemFacade.getMenuByLanguage(menuItemForm.getCode(), request);
    		request.setAttribute("currentMenu", menuBean);
    		
        } catch (Exception e) {
        	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		}
        
		// Save the List of results in request scope
		request.setAttribute("menuItemList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }


    public ActionForward create (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        MenuItemForm menuItemForm = (MenuItemForm) form;
        menuItemForm.reset(mapping, request);
        
        MenuItemFacade menuItemFacade = new MenuItemFacade();
        MenuBean menuBean = menuItemFacade.getMenuByLanguage(menuItemForm.getItemCode(), request);
		request.setAttribute("currentMenu", menuBean);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }

    public ActionForward read (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	MenuItemForm menuItemForm = (MenuItemForm) form;
		
		try {
			MenuItemFacade menuItemFacade = new MenuItemFacade();
     		menuItemFacade.getByCode(menuItemForm, request);
			
     		MenuBean menuBean = menuItemFacade.getMenuByLanguage(menuItemForm.getItemCode(), request);
    		request.setAttribute("currentMenu", menuBean);
     		
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
    	if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
    	ActionMessages messages = new ActionMessages();
    	try {
    		MenuItemForm menuItemForm = (MenuItemForm) form;
     		MenuItemFacade menuItemFacade = new MenuItemFacade(this, request);
     		menuItemFacade.save(menuItemForm, request);
     		
     		MenuBean menuBean = menuItemFacade.getMenuByLanguage(menuItemForm.getItemCode(), request);
    		request.setAttribute("currentMenu", menuBean);
     		
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
    		MenuItemForm menuItemForm = (MenuItemForm) form;
     		MenuItemFacade menuItemFacade = new MenuItemFacade();
     		menuItemFacade.save(menuItemForm, request);
     		
     		MenuBean menuBean = menuItemFacade.getMenuByLanguage(menuItemForm.getItemCode(), request);
    		request.setAttribute("currentMenu", menuBean);
     		
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
		    MenuItemForm menuItemForm = (MenuItemForm) form;
     		MenuItemFacade menuItemFacade = new MenuItemFacade(this, request);
     		menuItemFacade.delete(menuItemForm.getCodes());
		    
		    
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
