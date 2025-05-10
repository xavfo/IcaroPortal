/**
 * 
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
import org.apache.struts.util.LabelValueBean;

import com.yage.struts.action.BaseTilesAction;

/**
 * Carga iformacion para horarios de salida y llegada
 * @author hernan
 *
 */
public class FlightFormTilesAction extends BaseTilesAction {

	private static Log logger = LogFactory.getLog(FlightFormTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	List<LabelValueBean> hourList = new ArrayList<LabelValueBean>(24);
    	List<LabelValueBean> minuteList = new ArrayList<LabelValueBean>(60);
        
    	try {
    		
    		for (int i =0; i < 24 ; i++) {
    			String label = String.valueOf(i);
    			hourList.add(new LabelValueBean(label, label));
    		}
    		for (int i =0; i < 60 ; i++) {
    			String label = String.valueOf(i);
    			minuteList.add(new LabelValueBean(label, label));
    		}

 		} catch(Exception e) {
 			logger.error(e.getMessage(), e);
 		} finally {
 		}

 		//Save the Lists in request scope
		request.setAttribute("hourList", hourList);
		request.setAttribute("minuteList", minuteList);
 		
 		return null;
     }

}
