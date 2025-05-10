/**
 * Yagé 2007
 */
package com.iportal.cart.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import com.yage.struts.action.BaseTilesAction;

/**
 *
 * @author burkhard
 * @verion 1.0
 * created on Apr 18, 2007
 *
 */
public class CartFlowTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(CartFlowTilesAction.class);

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


            if(null != request.getAttribute("cartContent")) {
                context.putAttribute("cartContent", request.getAttribute("cartContent"));
            } else if (null == context.getAttribute("cartContent")) {
                context.putAttribute("cartContent", "cart.searchFlight");
            }
        return null;
    }

}

