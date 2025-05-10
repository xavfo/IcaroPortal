/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yage.struts.action.BaseTilesAction;
/**
 * Retrieve a list of countries, states and cities.
 * 
 * @author  YAGE (pajaro)
 * @version 1.0
 *
 */
public class CityTilesAction extends BaseTilesAction {

    

    private static Log logger = LogFactory.getLog(CityTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	Session sess = null;
        Query query = null;
         
        List countryList = null;
        List stateList = null;
         
        try {
            sess = getHibernateSession();
             
     		if (null == request.getAttribute("countryRestricted") && null == request.getParameter("countryRestricted")) {
                query = sess.createQuery("select new com.iportal.biz.RowItem (country.code, country.name) from Country as country order by country.name");
            } else {
                query = sess.createQuery("select new com.iportal.biz.RowItem (country.code, country.name) from Country as country where country.homeDelivery = 1 order by country.name");
            }
     		query.setCacheable(true);
     		countryList = query.list();


     		if (request.getParameter("listStates") != null) {
	     		query = sess.createQuery("select new com.iportal.biz.RowItem (state.code, state.country.code, state.name) from State as state order by state.name");
	     		query.setCacheable(true);
	     		stateList = query.list();
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
 		request.setAttribute("countryList", countryList);
 		if (request.getParameter("listStates") != null) {
 			request.setAttribute("stateList", stateList); 	
 		}
		
 		
 		return null;
     }
 }
