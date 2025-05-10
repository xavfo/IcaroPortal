/**
 * Yagé 2007
 */
package com.iportal.cart.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.ctrl.system.cart.CustomerAction;
import com.iportal.cart.ctrl.system.cart.CustomerForm;
import com.iportal.cart.model.customer.Customer;
import com.iportal.ctrl.portal.content.ContentAction;
import com.iportal.model.portal.Content;
import com.yage.Globals;


/**
 *
 * @author burkhard
 * @verion 1.0
 * created on May 7, 2007
 *
 */
public class AccountAction extends ContentAction {

    private static Log logger = LogFactory.getLog(AccountAction.class);

    public ActionForward account(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Session sess = null;
        Content content=null;
        ActionForward forward = super.read(mapping, form, request, response);

        MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
        sess = this.getHibernateSession();
        if (portalBean != null) {
            content = (Content) sess.get(Content.class, portalBean.getContentCode());
            request.setAttribute("content", content);
        }

        CustomerForm custForm = (CustomerForm)form;
            UserBean icaroUser=(UserBean)request.getSession().getAttribute(Constants.CLIENT_KEY);
            //Customer user = (Customer) request.getSession().getAttribute(CartConstants.CUSTOMER);
            Customer user=icaroUser.getCustomer();
            custForm.setItemCode(custForm.getCode());
            PropertyUtils.copyProperties(custForm, user);
            PropertyUtils.copyProperties(custForm, user.getHomeAddress());
            custForm.setGenderOption(user.getGender()?1:2);
            custForm.setIdTypeCode(user.getIdType().getCode().intValue());
            custForm.setCountryCode(user.getHomeAddress().getCountry().getCode().toString());
            custForm.setPasswordRegister("");
            //TODO Aqui cargar los datos de fecha de nacimiento
            /*user.get
            Calendar cal = Calendar.getInstance();*/

            request.setAttribute("userName", user.getUserName());
        return forward;
    }

    public ActionForward searchBuys(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

//        HttpSession session = request.getSession();
//        CustomerForm custForm = (CustomerForm)form;
//        Long code=custForm.getCode();
//        UserBean icaroUser=(UserBean)request.getSession().getAttribute(Constants.CLIENT_KEY);
//        //Customer user = (Customer) request.getSession().getAttribute(CartConstants.CUSTOMER);
//        Customer user=icaroUser.getCustomer();
        return mapping.findForward("buys");
    }

    public ActionForward saveClient(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionMessages messages = new ActionMessages();

        ActionForward forward = null;
        CustomerAction customerAction = new CustomerAction();

        try {

            forward = customerAction.apply(mapping, form, request, response);
            if( !Globals.FORWARD_APPLY.equals(forward.getName()) ) {
                //request.setAttribute("content.mainContent", "contentAccount.account");
                return mapping.findForward("success_account_layout2");
            }

        } catch (Exception e) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
            logger.error(e.getMessage(), e);

            //request.setAttribute("content.mainContent", "contentAccount.account");
            return mapping.findForward("success_account_layout2");
        }

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.success.msg", "<p>Su informaci&oacute;n ha sido actualizada con &eacute;xito y han sido enviados a " + ((CustomerForm)form).getEmail() + ".</p>\n" +
                "<p>Lo invitamos a seguir navegando en nuestro portal.</p>"));
        request.setAttribute("clientUpdate", "true");

        return mapping.findForward(Globals.FORWARD_APPLY);

    }


    public ActionForward showOrder(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionMessages messages = new ActionMessages();
        Session sess = getHibernateSession();

        try {
    /*        Order order = (Order)sess.get(Order.class, custForm.getCode());
            Hibernate.initialize(order.getDetails());
            Hibernate.initialize(order.getTaxDetails());
            request.setAttribute("order", order);
      */
            return mapping.findForward("showOrder");
        } catch (Exception e) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
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


    public ActionForward recommend(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward forward = super.read(mapping, form, request, response);

        if(null != request.getAttribute("email") || null != request.getParameter("email")) {
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.success,msg", "<p>Su recomendaci&oacute;n ha sido enviada con &eacute;xito.</p>" +
                            "<p>Lo invitamos a seguir navegando por nuestro portal.</p>"));
            saveMessages(request, messages);
        }

        request.setAttribute("content.mainContent", "contentAccount.recommend");
        return forward;

    }

}

