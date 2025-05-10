/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;

import com.iportal.biz.RowItem;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author  YAGE
 * @version 1.0
 *
 */
public class SearchFormTilesAction extends BaseTilesAction {

    
//    private static Log logger = LogFactory.getLog(SearchFormTilesAction.class);
     
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        List<RowItem> searchSectionList = new ArrayList<RowItem>();
        searchSectionList.add(new RowItem("Productos de Compra en Linea","product"));
        searchSectionList.add(new RowItem("La Empresa","institutional"));
        searchSectionList.add(new RowItem("Proveedores","provider"));
        searchSectionList.add(new RowItem("Marcas","brand"));
		
		//Save the List in request scope
		request.setAttribute("searchSectionList", searchSectionList);

		return null;
    }


}
