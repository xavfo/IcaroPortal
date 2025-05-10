/**
 * 
 */
package com.iportal.cart.view;

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

import com.iportal.cart.CartConstants;
import com.iportal.cart.model.cart.CheckoutStep;
import com.yage.struts.action.BaseTilesAction;

/**
 * @author hernan
 *
 */
public class CartHeaderTilesAction extends BaseTilesAction {
    
	private static Log logger = LogFactory.getLog(CartHeaderTilesAction.class);

    /* (non-Javadoc)
     * @see org.apache.struts.tiles.actions.TilesAction#execute(org.apache.struts.tiles.ComponentContext, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        Session sess = null;
        HttpSession session = request.getSession();  
        List flow    = null;
        Long curFlow = null;
        Query query  = null;
        
        flow = (List)session.getAttribute(CartConstants.CART_FLOW_ITEMS);
        curFlow = (Long)request.getAttribute(CartConstants.CART_FLOW_STEP);

        try {
            sess = getHibernateSession();

            if (null == flow ) {
                //Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);                
                StringBuffer hql = new StringBuffer();
                
                hql.append(" from CheckoutStep cos ");
                hql.append("order by cos.orderIndex ");
                query = sess.createQuery(hql.toString());
                query.setCacheable(true);
                flow = query.list();
    
                session.setAttribute(CartConstants.CART_FLOW_ITEMS, flow);                
            }
                      
            request.setAttribute(CartConstants.CART_FLOW_ITEMS, flow);
            if(null == curFlow) {
                curFlow = new Long(1);
            }
            if(null != request.getAttribute("cartContent")) {
                context.putAttribute("cartContent", request.getAttribute("cartContent"));
            } else if (null == context.getAttribute("cartContent")) {
                context.putAttribute("cartContent", "cart.searchFlight");
            }
            CheckoutStep curStep = null;
            List<CheckoutStep> list = flow;
            for (CheckoutStep s: list) {
            	if (curFlow.equals(s.getCode())){
            		curStep = s;
            		break;
            	}
            	
            }
            request.setAttribute("curSection", curStep);        
            
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
        
        return null;
    }

}
