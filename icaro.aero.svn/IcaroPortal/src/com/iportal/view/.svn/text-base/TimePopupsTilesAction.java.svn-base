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

import com.iportal.ctrl.portal.news.TimeUnitBean;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class TimePopupsTilesAction extends BaseTilesAction {

//    private static Log logger = LogFactory.getLog(TimePopupsTilesAction.class);
    
    private List<TimeUnitBean> populateDays(){
    	List<TimeUnitBean> results = new ArrayList<TimeUnitBean>();
    	for (int i = 1; i < 32; i++){
    		TimeUnitBean tub = new TimeUnitBean(new Integer(i), new Integer(i).toString() );
    		results.add(tub);
    	}
    	return results;
    }
    
    private List<TimeUnitBean> populateMonths(){
    	List<TimeUnitBean> results = new ArrayList<TimeUnitBean>();
    	String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
    						"Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}; 
    	
    	for (int i = 1; i < 13; i++){
    		TimeUnitBean tub = new TimeUnitBean(new Integer(i), months[i - 1] );
    		results.add(tub);
    	}
    	return results;
    }

    private List<TimeUnitBean> populateYears(){
    	List<TimeUnitBean> results = new ArrayList<TimeUnitBean>();
    	String years[] = {"2000", "2001", "2002", "2003", "2004", "2005","2006", "2007","2008","2009","2010","2011","2012","2013","2014"}; 
    	
    	for (int i = 0; i < 15; i++){
    		TimeUnitBean tub = new TimeUnitBean(new Integer(i), years[i] );
    		results.add(tub);
    	}
    	return results;
    }

    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
     	List<TimeUnitBean> dayList = populateDays();
     	List<TimeUnitBean> monthList = populateMonths();
     	List<TimeUnitBean> yearList = populateYears();
     	
     	List<TimeUnitBean> dayListTO = populateDays();
     	List<TimeUnitBean> monthListTO = populateMonths();
     	List<TimeUnitBean> yearListTO = populateYears();
     	 
     	
     	request.setAttribute("dayList", dayList);
     	request.setAttribute("monthList", monthList);
     	request.setAttribute("yearList", yearList);
     	
     	request.setAttribute("dayListTO", dayListTO);
     	request.setAttribute("monthListTO", monthListTO);
     	request.setAttribute("yearListTO", yearListTO);
 		
		return null;
    }

}
