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
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseTilesAction;
/**
 * Retrieve a list of cities.
 * 
 * @author ferTamayo
 * @version 1.0
 *
 */
public class FlightTilesAction extends BaseTilesAction {

    

    private static Log logger = LogFactory.getLog(FlightTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	Session sess = null;
        Query query = null;
        List airportList = null;
        	 
        List<LabelValueBean> timeList = new ArrayList<LabelValueBean>(48);
        try {
            sess = getHibernateSession();
             
                query = sess.createQuery("select new com.iportal.biz.RowItem (concat(airport.city.name,'-',airport.name,'(',airport.iataCode,')'), airport.iataCode) from Airport as airport where airport.enabled=? order by airport.city.name" );
                query.setBoolean(0, Globals.TRUE);
                query.setCacheable(true);
	     		airportList = query.list();
     		
     	/*TODO
     	 * Agregar los listados que arrojen los nombres de aeropuertos
     	*/
	     		
	     		Calendar today = Calendar.getInstance();
	     		if(today.get(Calendar.MINUTE)<15) {
	     			today.set(Calendar.MINUTE, 1);
	     		} else  		{
	     			if(today.get(Calendar.MINUTE)>30){
	     				today.add(Calendar.HOUR, 1);
	     				today.set(Calendar.MINUTE, 1);
	     			}
	     			else {
	     				today.set(Calendar.MINUTE, 30);
	     			}
	     		}
	     		   		
	     		int totalHours = 24; // El numero de horas que se pondra en el listado separados en rangos de 30 minutos
	    		for(int i=0;i<totalHours;i++){
	    			LabelValueBean label = new LabelValueBean(DateFormatUtils.format(today.getTime(),"HH:mm"), DateFormatUtils.format(today.getTime(),"HHmm"));
	    			timeList.add(label);
	    			today.add(Calendar.MINUTE,30);
	    			label = new LabelValueBean(DateFormatUtils.format(today.getTime(),"HH:mm"), DateFormatUtils.format(today.getTime(),"HHmm"));
	    			timeList.add(label);
	    			today.add(Calendar.MINUTE,30);
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
 			request.setAttribute("airportList", airportList);
 			request.setAttribute("timeList", timeList);
 		
		
 		
 		return null;
     }
 }
