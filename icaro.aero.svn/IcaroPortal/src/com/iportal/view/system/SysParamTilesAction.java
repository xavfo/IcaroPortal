/*
 * Copyright (c) Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;

import com.iportal.biz.RowItem;
import com.yage.struts.action.BaseTilesAction;


/**
 * Retrieve a list of Java Types.
 * 
 * @author YAGE (burkhard)
 * @version 1.0
 * 
 */
public class SysParamTilesAction extends BaseTilesAction {

    // private static Log logger = LogFactory.getLog(StateTilesAction.class);

    public ActionForward execute(ComponentContext context,
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        ArrayList<RowItem> typesList = new ArrayList<RowItem>();

        typesList.add(new RowItem(Boolean.class.getName()));
        typesList.add(new RowItem(Byte.class.getName()));
        typesList.add(new RowItem(Character.class.getName()));
        typesList.add(new RowItem(Date.class.getName()));
        typesList.add(new RowItem(Double.class.getName()));
        typesList.add(new RowItem(Float.class.getName()));
        typesList.add(new RowItem(Integer.class.getName()));
        typesList.add(new RowItem(Long.class.getName()));
        typesList.add(new RowItem(Object.class.getName()));
        typesList.add(new RowItem(Short.class.getName()));
        typesList.add(new RowItem(String.class.getName()));
        typesList.add(new RowItem(Timestamp.class.getName()));

        // Save the Lists in request scope
        request.setAttribute("typesList", typesList);

        return null;
    }
}
