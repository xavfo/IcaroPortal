/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;

import com.iportal.biz.PageHelper;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class PagerTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(PagerTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	Integer formIndex = null;
    	String styleClass = null;
    	String selectedClass = null;
    	Integer currentPage = null;
    	List<Integer> pages = new ArrayList<Integer>();
        try {
        	
        	if (context.getAttribute("totalPages")!= null && context.getAttribute("totalPages").toString() != ""){
        		formIndex = new Integer( context.getAttribute("formIndex").toString() );
            	styleClass = new String( context.getAttribute("styleClass").toString() );
            	selectedClass = new String( context.getAttribute("selectedClass").toString() );
            	Integer totalPages = new Integer( context.getAttribute("totalPages").toString() );
            	currentPage = new Integer( context.getAttribute("currentPage").toString() );
                
            	pages = PageHelper.getPages(totalPages);
            	
            	if ( !(selectedClass != null && selectedClass.length()>0) )
            		selectedClass = styleClass;
            	if (currentPage == 0)
            		currentPage = 1;
        	}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
		}
		
		//Save the params in request scope
		request.setAttribute("formIndex", formIndex);
		request.setAttribute("styleClass", styleClass);
		request.setAttribute("selectedClass", selectedClass);
		request.setAttribute("pages", pages);
		request.setAttribute("currentPage", currentPage);
		return null;
    }

}
