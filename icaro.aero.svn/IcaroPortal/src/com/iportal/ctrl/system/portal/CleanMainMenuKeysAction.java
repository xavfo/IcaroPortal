/*
 * Created Nov 10, 2006
 *	CleanMainMenuKeysAction.java
 */
package com.iportal.ctrl.system.portal;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * @author hernan
 *
 */
public class CleanMainMenuKeysAction extends BaseAction {

	
	
	/**
	 * 
	 */
	public CleanMainMenuKeysAction() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
		ServletContext context =  this.getServlet().getServletContext();
		
		Enumeration names = context.getAttributeNames();
		String element = "";
		int count = 0;
		while( names.hasMoreElements() ) {
			element = (String) names.nextElement();
			if (element.startsWith(com.iportal.Constants.MAIN_MENU_KEY)) {
				context.removeAttribute(element);
				count++;
			}
		}
		request.setAttribute("itemsRemoved", count);
		return mapping.findForward(Globals.FORWARD_SUCCESS);
		
	}
	
	

}
