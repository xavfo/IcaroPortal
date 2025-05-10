/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.security;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * Accion para salir de la cuenta de usuario, borra todos los elementos de la sesión.
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class LogoutAction extends BaseAction {

    public ActionForward execute(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
        
        // Gets the current session
        HttpSession session = request.getSession();

		Enumeration attributeList = session.getAttributeNames();
		while (attributeList.hasMoreElements()) {
			String attributeName = (String) attributeList.nextElement();
			if (attributeName.indexOf("LOCALE") <= 0) {
				session.removeAttribute(attributeName);
			}
		}

        
        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
}
