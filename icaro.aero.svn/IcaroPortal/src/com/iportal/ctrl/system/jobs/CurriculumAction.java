package com.iportal.ctrl.system.jobs;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * @author martha
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class CurriculumAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(CurriculumAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {            
    	
    	PositionRequestForm positionRequestForm = (PositionRequestForm) form;
    	
		String areaParam = request.getParameter("code");
		
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
        
        try {        	        	
        	results = jobBL.getCurriculumList(positionRequestForm);     
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} 
        
		// Save the List of results in request scope
		request.setAttribute("curriculumList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }
    
    
   

}
