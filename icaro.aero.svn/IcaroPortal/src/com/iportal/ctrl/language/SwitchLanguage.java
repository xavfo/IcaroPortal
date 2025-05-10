/**
 * 
 */
package com.iportal.ctrl.language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.biz.audit.PageLogHelper;
import com.iportal.ctrl.BasePortalForm;
import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * Cambio de lenguage por el lenguage indicado en el parametro languageCode.
 * @author hernan
 * @version 1.0
 *
 */
public class SwitchLanguage extends BaseAction {
	
	private static Log logger = LogFactory.getLog(SwitchLanguage.class);
	
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        HttpSession session = request.getSession();
        Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
        BasePortalForm portalForm = (BasePortalForm) form;
        try {
			if ((language == null || !language.getUserDefined()) || (portalForm.getLanguageCode() != null && !portalForm.getLanguageCode().equals(language.getCode()))) {
			    LanguageUtils utils = new LanguageUtils();
			    if (portalForm.getLanguageCode() != null) {
			    	language = utils.getLanguage(portalForm.getLanguageCode());
			    	language.setUserDefined(Globals.TRUE);
			    } else {
			    	//language = utils.getLanguage(request);	
			    	language = utils.getLanguage(Constants.LANGUAGE_SPANISH); 
			    }
			    session.setAttribute(Constants.LANGUAGE_KEY, language);
			    
			}
			
			setLocale(request, language.getLocale());
			
			request.setCharacterEncoding( language.getCharset());
			
			//Registra visita a este contenido
			PageLogHelper.log(request, this.getClass().getName(),portalForm.getItemCode()!= null?portalForm.getItemCode():new Long (1), "SwitchLanguage");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		//en action viene el nombre del forward donde debe ir la accion
		String forward = (portalForm.getAction()!= null) ?portalForm.getAction().toString():"home";
		this.removeForm(mapping, request);
        return mapping.findForward(forward);
    }

}
