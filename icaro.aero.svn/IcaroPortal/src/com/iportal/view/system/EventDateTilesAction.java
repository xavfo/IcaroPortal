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
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;

import com.yage.struts.action.BaseTilesAction;

/**
 * Tile para Fechas de Eventos<BR>
 * Carga todos las fecha (años, meses y horas) y todas la provincias 
 * para realzar la administración de los eventos (Fechas).
 * 
 * @author YAGE (aigiler)
 * @version 1.0
 * @see com.yage.struts.action.BaseTilesAction
 */
public class EventDateTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(EventDateTilesAction.class);
    
    @SuppressWarnings("unchecked")
	public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        
        ArrayList<LabelValueBean> hourList = new ArrayList();
		ArrayList<LabelValueBean> minuteList = new ArrayList();	
		ArrayList<LabelValueBean> yearList = new ArrayList();
		
        try {
            Calendar calendar = new GregorianCalendar();
			int year = calendar.get(Calendar.YEAR);
			for (int i=year; i<year + 10; i++) {
				String opt = String.valueOf(i);
				LabelValueBean lvb = new LabelValueBean(opt, opt);
				yearList.add( lvb );
			}
			
			for (int i=0; i<24; i++) {
				String opt = String.valueOf(i);
				LabelValueBean lvb = new LabelValueBean(opt, opt);
				hourList.add( lvb );
			}
			
			for (int j=0; j<60; j+=5) {
				String opt = String.valueOf(j);
				LabelValueBean lvb = new LabelValueBean(opt, opt);
				minuteList.add( lvb );
			}
            
     		
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		//Save the Lists in request scope
		request.setAttribute("yearList", yearList);
		request.setAttribute("hourList", hourList);
		request.setAttribute("minuteList", minuteList);

		return null;
    }
}