/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SetLanguageAction extends BaseAction {

    private static Log logger = LogFactory.getLog(SetLanguageAction.class);


    public ActionForward execute (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
		// Look for the user in the Session Context
		HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();
        
		BasePortalForm portalForm = (BasePortalForm) form;
		
        LanguageUtils utils = new LanguageUtils();
        Language language = utils.getLanguage(portalForm.getLanguageCode());
        
        if (language == null) {
            ActionMessage message = new ActionMessage("error.language");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			logger.error(message);
        }
        
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		
		// Save the Language in session scope
        session.setAttribute(Constants.LANGUAGE_KEY, language);
        setLocale(request, language.getLocale());
		
        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
}
