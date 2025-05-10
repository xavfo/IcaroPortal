package com.iportal.ctrl.system.jobs;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iportal.model.City;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * @author martha
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class PositionRequestAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(PositionRequestAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {            
    	
    	PositionRequestForm positionRequestForm = (PositionRequestForm) form;
    	
		String areaParam = request.getParameter("code");
		
		List cityList = new ArrayList<City>();
		
		Long areaCode = null;
		List results = null;
		HttpSession session = request.getSession();
		
		if (areaParam != null){
			areaCode = Long.valueOf(areaParam);
		} 
		if (areaCode != null){
			session.setAttribute("areaCode", areaCode);
		}
		areaCode = (Long)session.getAttribute("areaCode");
		
		positionRequestForm.setAreaCode(areaCode);      
		
      
        JobsBussinessLogic jobBL = new JobsBussinessLogic();
        Long countryCode = positionRequestForm.getCountryCode();
        
        if (countryCode != null){
        	cityList = jobBL.getAllCities(countryCode);
        }
        
        try {        	        	
        	results = jobBL.getAllPositionRequests(positionRequestForm);     
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} 
        
		// Save the List of results in request scope
		request.setAttribute("positionRequestList", results);
		request.setAttribute("cityList", cityList);

		return mapping.findForward(Globals.FORWARD_LIST);
    }
    
    
    public ActionForward listCities (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {            
    	
		String countryParam = request.getParameter("countryCode");
		
		Long countryCode = null;
		List results = null;

		PositionRequestForm positionRequestForm = (PositionRequestForm) form;
		if (countryParam != null){
			countryCode = Long.valueOf(countryParam);
			positionRequestForm.setCountryCode(countryCode);
		}
	
		JobsBussinessLogic jobBL = new JobsBussinessLogic();
		
		results = jobBL.getAllCities(countryCode);
		
		// Save the List of results in request scope
		request.setAttribute("cityList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }

}
