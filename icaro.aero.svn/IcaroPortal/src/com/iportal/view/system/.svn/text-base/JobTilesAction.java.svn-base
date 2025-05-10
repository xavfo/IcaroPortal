/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.ctrl.system.jobs.JobsBussinessLogic;
import com.iportal.ctrl.system.jobs.PositionForm;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class JobTilesAction extends BaseTilesAction {

    
    private static Log logger = LogFactory.getLog(JobTilesAction.class);
    
    
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
        HttpSession session = request.getSession();
        Long areaCode = (Long)session.getAttribute("areaCode");
        List positionList = null;
        List maritalStatusList = null;
        List instructionLevelList = null;
        List studyBranchList = null;
        List salaryAspirationList = null;
        
        try {
            sess = getHibernateSession();
            
            query = sess.createQuery("from Country as country order by country.name");
    		query.setCacheable(true);
    		countryList = query.list();
    		
    		if (areaCode != null){
    			PositionForm positionForm = new PositionForm();
    			positionForm.setAreaCode(areaCode);
    			positionForm.setOrderField("name");
    			positionForm.setOrderAsc(Globals.TRUE);
    			JobsBussinessLogic jobBL = new JobsBussinessLogic();
    			positionList = jobBL.getAllPositions(positionForm);
    			maritalStatusList = jobBL.getAllCatalogues(Constants.CATALOGUE_TYPE_MARITAL_STATUS);
    			instructionLevelList = jobBL.getAllCatalogues(Constants.CATALOGUE_TYPE_INSTRUCTION_LEVEL);
    			studyBranchList = jobBL.getAllCatalogues(Constants.CATALOGUE_TYPE_STUDY_BRANCH);
    			salaryAspirationList = jobBL.getAllCatalogues(Constants.CATALOGUE_TYPE_SALARY_ASPIRATION);
    				
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
		request.setAttribute("positionList", positionList);
		request.setAttribute("maritalStatusList", maritalStatusList);
		request.setAttribute("instructionLevelList", instructionLevelList);
		request.setAttribute("studyBranchList", studyBranchList);
		request.setAttribute("salaryAspirationList", salaryAspirationList);
		

		return null;
    }


}
