/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.biz.audit.PageLogHelper;
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
public class HomeAction extends BaseAction {

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        HttpSession session = request.getSession();
        Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
        if (language == null) {
            LanguageUtils utils = new LanguageUtils();
            //language = utils.getLanguage(request);
            language = utils.getLanguage(Constants.LANGUAGE_SPANISH);
        }
        
        setLocale(request, language.getLocale());
        
    	//Registra visita a este contenido
		
		PageLogHelper.log(request, this.getClass().getName(), new Long (1), "Home");

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
}
