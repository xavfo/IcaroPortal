/**
 * 
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
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.cart.model.tax.Tax;
import com.yage.struts.action.BaseTilesAction;

/**
 * Lista los tipos de pasajero para creacion de tarifas
 * @author hernan
 * @version 1.0
 *
 */
public class TicketRateTilesAction extends BaseTilesAction {
    
	private static Log logger = LogFactory.getLog(TicketRateTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
              
        List paxTypeList = null;
        
        try {
            sess = getHibernateSession();
            
            StringBuffer hql = new StringBuffer();
            hql.append(" select new com.iportal.biz.RowItem (paxType.code, paxType.name) ");
            hql.append(" from PaxType as paxType ");
            hql.append(" order by paxType.name ");
            
            paxTypeList = sess.createQuery(hql.toString()).list();
            
            Tax ivaTax = (Tax) sess.load(Tax.class, Constants.IVA_TAX_CODE);
            request.setAttribute("ivaRate", ivaTax.getCurrentRate());
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
		request.setAttribute("paxTypeList", paxTypeList);

		return null;
    }

}
