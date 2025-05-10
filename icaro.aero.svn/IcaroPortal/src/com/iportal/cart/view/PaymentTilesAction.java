/**
 * Yagé 2007
 */
package com.iportal.cart.view;

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
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.cart.ctrl.flight.SearchFlightForm;
import com.iportal.cart.model.cart.PaymentType;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 *
 * @author burkhard
 * @verion 1.0
 * created on May 3, 2007
 *
 */
public class PaymentTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(PaymentTilesAction.class);

    /**
     * @see org.apache.struts.tiles.actions.TilesAction#execute(org.apache.struts.tiles.ComponentContext, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ComponentContext context, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Session sess = null;
        List<PaymentType> results = null;

        try {
            

            StringBuffer hql = new StringBuffer();
            hql.append(" from PaymentType as type ");
            hql.append("where type.enabled = ? ");

            sess = getHibernateSession();
            Query query = sess.createQuery(hql.toString());
            query.setBoolean(0, Globals.TRUE);
            query.setCacheable(true);

            results = (ArrayList<PaymentType>)query.list();
            
            if(null != results && results.size() > 0 ) {
                SearchFlightForm searchForm = (SearchFlightForm) request.getSession().getAttribute("searchFlightForm");
                if(null == searchForm.getPaymentTypeCode() || searchForm.getPaymentTypeCode()< 1L) {
                	searchForm.setPaymentTypeCode(results.get(0).getCode());
                }
            }
            
            request.setAttribute("paymentTypeList", results);
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (sess != null) {
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

}

