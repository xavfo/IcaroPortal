/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.yage.Globals;

/**
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public abstract class BaseLoginAction extends BaseAction {

    /**
     * Authenticate the user credentials sent in the ActionForm
     */
    protected abstract ActionMessages authenticate(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception;
    
    protected abstract ActionMessages authenticateLdap(ActionForm form, HttpServletRequest request) throws Exception;

    
    protected abstract void initUserSession(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception;
    

    /**
     * Gets the ActionForward. This method can be overriden.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected ActionForward findSuccessForward(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
        
        return mapping.findForward(Globals.FORWARD_SUCCESS); 
    }

    
    /**
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
        
        // Gets the current session
        HttpSession session = request.getSession();
        ActionMessages messages = new ActionMessages();
        
        // Removes any User in the current session
        session.removeAttribute(Globals.AUTHENTICATION_KEY);
        
        String ldapValidation = System.getProperty("com.yage.userservice.ldap.validation");
        
        if (ldapValidation != null){
            if(ldapValidation.equals("true")){
                messages = authenticateLdap(form, request);
            }else {
                messages = authenticate(mapping, form, request, response);
            }    
        }else {
            messages = authenticate(mapping, form, request, response);
        }
        
        // Authenticate the user
        //messages = authenticate(mapping, form, request, response);
        //ActionMessages messages = authenticateLdap(form, request);
        
        // Report any errors we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Remove the obsolete form bean
		removeForm(mapping, request);
		
		// Save an authentication flag for the logged-in user in the session
		session.setAttribute(Globals.AUTHENTICATION_KEY, Globals.TRUE);
		initUserSession(mapping, form, request, response);

		return findSuccessForward(mapping, form, request, response);
    }
    
    
}
