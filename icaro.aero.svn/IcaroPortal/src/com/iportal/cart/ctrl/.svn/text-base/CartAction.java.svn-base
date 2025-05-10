package com.iportal.cart.ctrl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.ctrl.flight.SearchFlightForm;
import com.iportal.cart.ctrl.system.cart.CustomerAction;
import com.iportal.cart.ctrl.system.cart.CustomerForm;
import com.iportal.cart.model.customer.Customer;
import com.iportal.model.Country;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.commons.Md5;
import com.yage.commons.PasswordGenerator;
import com.yage.struts.action.BaseDispatchAction;

public class CartAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(CartAction.class);

	public ActionForward saveClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();

		ActionForward forward = null;
		CustomerForm customerForm = (CustomerForm) form;
		CustomerAction customerAction = new CustomerAction();
		String nextForward = customerForm.getNextForward();
		Country country = null;
		Session sess = null;
		try {
			forward = customerAction.save(mapping, form, request, response);

			if (!Globals.FORWARD_SUCCESS.equals(forward.getName())) {
				if (nextForward.equals("portalUser")) {
					return mapping.findForward("accountLogin");
				} else {
					request.setAttribute("cartContent", "cart.registerLogin");
					request.setAttribute("openRegister", Globals.TRUE);
					return mapping.findForward("cartFlow");
				}
			}
			Long countryCode = new Long(customerForm.getCountryCode());
			request.setAttribute("sendMail", Globals.TRUE);
			request.setAttribute("newPassword", customerForm.getPassword());
			request.setAttribute("customer", customerForm);
			sess = this.getHibernateSession();
			country = (Country) sess.get(Country.class, countryCode);
			Calendar calendar = new GregorianCalendar();
			String today = DateFormatUtils.format(calendar.getTime(),
					Constants.DATE_TIME_FORMAT);
			request.setAttribute("today", today);
			request.setAttribute("country", country);
			
			Customer user = (Customer)sess.get(Customer.class, customerForm.getCode());
			UserBean iUser=new UserBean(user, Constants.CLIENT_PORTAL_KEY, user.getUserName());	
			request.getSession().setAttribute(Constants.CLIENT_KEY, iUser);

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.success.register", customerForm.getEmail()));
			saveMessages(request, messages);

			// request.setAttribute(CartConstants.CART_FLOW_STEP,
			// CartConstants.CART_FLOW_DELIVERY);
			request.setAttribute("cartContent", "cart.delivery");

		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.failure"));
			logger.error(e.getMessage(), e);
        } finally {
            if (sess != null)
                try {
                    sess.close();
                } catch (Exception e) {
                }
        }

		if (nextForward.equals("portalUser")) {
			return mapping.findForward("succesRegister");
		}
		request.setAttribute("openRegister", Globals.TRUE);
		request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);
		request.setAttribute("cartContent", "cart.passenger");

		return mapping.findForward("cartFlow");

	}

    public ActionForward forgetPassword(
	        ActionMapping mapping, 
	        ActionForm form,
	        HttpServletRequest request, 
	        HttpServletResponse response) 
	    throws Exception {
    	CustomerForm theForm = (CustomerForm) form;
    	String nextForward=theForm.getNextForward();
    	HttpSession session = request.getSession();
    	Content content=null;
    	Session sess=null;
    	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
    	sess = getHibernateSession();
    	if (portalBean != null) {
			content = (Content) sess.get(Content.class, portalBean
					.getContentCode());
			request.setAttribute("content", content);

		}
	
	    if ( isCancelled(request) ) {
	        return mapping.findForward(Globals.FORWARD_CANCEL);
	    }
	    
	    ActionMessages messages = resetPassword(form, request, response);
	
	    // Report any messages we have discovered back to the original form
	    if (!messages.isEmpty()) {
	        saveMessages(request, messages);
	        if(nextForward.equals("portalUser")){
	        	return mapping.findForward("accountLogin");	
	        }
	        else{
	       //    	request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_REGISTER);
	        	request.setAttribute("cartContent", "cart.register");	        
	        	return mapping.findForward(Globals.FORWARD_CANCEL);
	        }
	    }
	    
	    // Report a success action
	    messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
	    saveMessages(request, messages);
	    if(nextForward.equals("portalUser")){
        	return mapping.findForward("accountPasswordReset");	
        }
	    else{
	    //	request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_REGISTER);
		    request.setAttribute("cartContent", "cart.passwordReset");
		    return mapping.findForward(Globals.FORWARD_CANCEL); 
	    }
	    
    }
	public ActionForward accountSaveClient(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionMessages messages = new ActionMessages();

		HttpSession session = request.getSession();
		
		ActionForward forward = null;
		CustomerForm customerForm = (CustomerForm) form;
		CustomerAction customerAction = new CustomerAction();
		Country country = null;
		
		Session sess = getHibernateSession();
		String nextForward = (String) PropertyUtils.getSimpleProperty(form,
		"nextForward");
		Boolean isFlowForward = nextForward != null && "cartFlow".equals(nextForward) ;

		try {
			forward = customerAction.save(mapping, form, request, response);
			if (!Globals.FORWARD_SUCCESS.equals(forward.getName())) {
				request.setAttribute("registering", Globals.TRUE);
				return mapping.findForward("accountLogin");
			}
			Long countryCode = new Long(customerForm.getCountryCode());
			request.setAttribute("sendMail", Globals.TRUE);
			request.setAttribute("newPassword", customerForm.getPassword());
			request.setAttribute("customer", customerForm);
			country = (Country) sess.get(Country.class, countryCode);
			request.setAttribute("country", country);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.success.register", customerForm.getEmail()));
			saveMessages(request, messages);

		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.failure"));
			logger.error(e.getMessage(), e);
			request.setAttribute("registering", Globals.TRUE);
			
			if (isFlowForward) {
				SearchFlightForm searchForm = (SearchFlightForm) session.getAttribute("searchFlightForm");
				if (searchForm != null) {
	    			request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
	    			searchForm.setNextStep("cart.registerLogin");
	    			request.setAttribute("cartContent", searchForm.getNextStep());
				}
				return mapping.getInputForward();
			} else {
				return mapping.findForward("accountLogin");	
			}
			
		}

		if (isFlowForward) {
			SearchFlightForm searchForm = (SearchFlightForm) session.getAttribute("searchFlightForm");
			if (searchForm != null) {
				request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);
				searchForm.setNextStep("cart.passenger");
				request.setAttribute("cartContent", searchForm.getNextStep());
			}
			return mapping.findForward("cartFlow");
		}
		
		return mapping.findForward("account");			
		

	}

	public ActionForward accountLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();

		// Look for the user in the Session Context
		HttpSession session = request.getSession();
		UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
		// Customer user = (Customer)
		// session.getAttribute(CartConstants.CUSTOMER);

		// Carga Seccion
		Session sess = getHibernateSession();
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);
		Content content = null;
		Transaction tx = null;
		Customer user = null;
		if (portalBean != null) {
			content = (Content) sess.get(Content.class, portalBean
					.getContentCode());
			request.setAttribute("content", content);

		}
		if(null!=icaroUser){
			if(icaroUser.getCustomerType()!=Constants.CLIENT_PORTAL_KEY){
				icaroUser=null;
			}
		}
		if (null == icaroUser) {			
			// Get username/password
			// String username = (String) PropertyUtils.getSimpleProperty(form,
			// "loginEmail");
			String username = (String) PropertyUtils.getSimpleProperty(form,
					"userName");
			if (!(username == null || username.length()> 0) && request.getParameter("loginEmail") != null){
			    username = request.getParameter("loginEmail");
			}
			String password = (String) PropertyUtils.getSimpleProperty(form,
					"passwordLogin");

			if ((null == username || 0 == username.trim().length())
					|| (null == password || 0 == password.trim().length())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.cart.notLogged"));
			} else {

				try {
					StringBuffer sql = new StringBuffer();
					List userResults = null;

					sql.append("from Customer as customer ");
					// sql.append("where customer.email = ? ");
					sql.append("where customer.userName = ? ");
					// sql.append(" and customer.enabled = ? ");

					Query query = sess.createQuery(sql.toString());
					query.setString(0, username);
					// query.setBoolean(1, Globals.TRUE);
					query.setCacheable(true);
					userResults = query.list();

					if (null != userResults) {
						if (1 == userResults.size()) {
							user = (Customer) userResults.get(0);
							// Hibernate.initialize(user.getAddresses());
						} /*else {
							messages.add(ActionMessages.GLOBAL_MESSAGE,
									new ActionMessage("error.username"));
						}*/
					}
					// Validate User
					if (user == null) {
						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("error.username"));

					} else if (!user.getPassword().equals(Md5.hash(password))) {

						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("error.password"));
					} else if (!user.getEnabled().booleanValue()) {

						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("error.userAccount"));
					}

					if (messages.isEmpty()) {
						// if (user.getPasswordReset().booleanValue()) {
						// PropertyUtils.setSimpleProperty(form, "forward",
						// Globals.FORWARD_FORM);
						// }
						user.setLastLogin(new GregorianCalendar());
						UserBean iUser=new UserBean(user, Constants.CLIENT_PORTAL_KEY, user.getUserName());	
						tx = sess.beginTransaction();
						sess.saveOrUpdate(user);
						tx.commit();

						session.setAttribute(Constants.CLIENT_KEY, iUser);
						// session.setAttribute(CartConstants.CUSTOMER, user);
					}
				} catch (Exception e) {

					boolean putMesage = checkControlledError(e, messages,
							BaseHelper.getApplicationBundleMessage(
									"prompt.product", this.getLocale(request)),
							user.getFullName(), null);
					if (tx != null) {
						tx.rollback();
					}
					// si no se ha escrito ningun error colocar el error
					// generico
					if (!putMesage) {
						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("message.failure.msg", e
										.getMessage()));
					}
					logger.error(e.getMessage(), e);
				} finally {
					if (sess != null)
						try {
							sess.close();
						} catch (Exception e) {
						}
				}
			}
		}
		String nextForward = (String) PropertyUtils.getSimpleProperty(form,
		"nextForward");
		Boolean isFlowForward = nextForward != null && "cartFlow".equals(nextForward) ;
		
		if (null != messages && !messages.isEmpty()) {
			saveMessages(request, messages);
			if (isFlowForward) {
				SearchFlightForm searchForm = (SearchFlightForm) session.getAttribute("searchFlightForm");
				if (searchForm != null) {
	    			request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
	    			searchForm.setNextStep("cart.registerLogin");
	    			request.setAttribute("cartContent", searchForm.getNextStep());
				}
				return mapping.getInputForward();
			} else {
				return mapping.findForward("accountLogin");	
			}
			
		}

		if (isFlowForward) {
			SearchFlightForm searchForm = (SearchFlightForm) session.getAttribute("searchFlightForm");
			if (searchForm != null) {
				request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);
				searchForm.setNextStep("cart.passenger");
				request.setAttribute("cartContent", searchForm.getNextStep());
			}
			return mapping.findForward("cartFlow");
		}

		return mapping.findForward(nextForward);

	}

	public ActionForward accountForgetPassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (isCancelled(request)) {
			return mapping.findForward(Globals.FORWARD_CANCEL);
		}

		ActionMessages messages = resetPassword(form, request, response);
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return mapping.findForward("accountLogin");
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
				"message.success"));
		saveMessages(request, messages);
		request.setAttribute("content.mainContent", "cart.passwordReset");
		return mapping.findForward("accountLogin");
	}

	private ActionMessages resetPassword(ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages messages = new ActionMessages();
		CustomerForm theForm = (CustomerForm) form;

		Session sess = null;
		Transaction trans = null;
		Query query = null;
		String userName = theForm.getUserName();

		try {
			sess = getHibernateSession();

			Customer customer = null;
			List results = null;

			StringBuffer sql = new StringBuffer();

			// sql.append("select cust.code ");
			sql.append(" from Customer cust");
			sql.append(" where cust.userName = ?");
			sql.append(" and cust.enabled = ?");

			query = sess.createQuery(sql.toString());
			query.setString(0, userName);
			query.setBoolean(1, Globals.TRUE);
			query.setCacheable(true);

			try {
				results = query.list();
				if (results != null && results.size() > 0) {
					customer = (Customer) results.get(0);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			if (customer != null) {

				String newPassword = PasswordGenerator.getNext();
				String cryptPassword = Md5.hash(newPassword);
				customer.setPassword(cryptPassword);

				trans = sess.beginTransaction();
				sess.saveOrUpdate(customer);
				trans.commit();

				customer.setResetPassword(Globals.TRUE);
				request.setAttribute("tmpUser", customer);
				request.setAttribute("newPassword", newPassword);
				Calendar calendar = new GregorianCalendar();
				String today = DateFormatUtils.format(calendar.getTime(),
						Constants.DATE_TIME_FORMAT);
				request.setAttribute("today", today);
			} else {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"prompt.cart.retrieve.password", userName));
			}

		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.failure"));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}
		return messages;
	}

}
