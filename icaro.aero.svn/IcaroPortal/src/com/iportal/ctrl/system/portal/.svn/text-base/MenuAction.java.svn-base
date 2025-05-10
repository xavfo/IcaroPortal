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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.system.portal.MenuBean;
import com.iportal.biz.system.portal.MenuFacade;
import com.iportal.biz.system.portal.MenuForm;
import com.iportal.model.Language;
import com.iportal.model.portal.Menu;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * java comment MenuAction
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 */
public class MenuAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(MenuAction.class);
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	List<MenuBean> results = null;

        try {
        	MenuForm menuForm = (MenuForm) form;
    		MenuFacade menuFacade = new MenuFacade();
    		results = menuFacade.getAll(menuForm, request);
    		
        } catch (Exception e) {
        	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		}
        
		// Save the List of results in request scope
		request.setAttribute("menuList", results);

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
        MenuForm menuForm = (MenuForm) form;
        menuForm.reset(mapping, request);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }

    public ActionForward read (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	MenuForm menuForm = (MenuForm) form;
		
		try {
			MenuFacade menuFacade = new MenuFacade();
     		menuFacade.getByCode(menuForm, request);
			
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
    		MenuForm menuForm = (MenuForm) form;
     		MenuFacade menuFacade = new MenuFacade( this);
     		menuFacade.save(menuForm, request);
     		
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
    		MenuForm menuForm = (MenuForm) form;
     		MenuFacade menuFacade = new MenuFacade();
     		menuFacade.save(menuForm, request);
     		
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
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		MenuForm menuForm = (MenuForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Menu menu = null;
        Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        
		try {
		    Long codes[] = menuForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
					menu = (Menu) sess.load(Menu.class, codes[i]);
					sess.delete(menu);	
                    SysAuditHelper.audit(this, request, menu, menu.getMenuLanguage(language).getName(), Globals.AUDIT_DELETE, sess);
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
