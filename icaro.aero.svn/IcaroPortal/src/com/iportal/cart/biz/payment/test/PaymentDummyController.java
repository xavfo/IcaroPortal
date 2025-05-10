/**
 * Copyright ©2007 MINDSOFT. All Rights Reserved.
 * 
 * MINDSOFT Cia. Ltda. Pasaje Giron Oe3-30 y America Quito-Ecuador
 * www.mindsoft.biz 2007 Sep 10, 2007 1:23:22 PM
 */
package com.iportal.cart.biz.payment.test;

import java.util.GregorianCalendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.payment.PaymentController;
import com.iportal.cart.biz.payment.PaymentIF;
import com.iportal.cart.ctrl.PaymentForm;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.OrderStatus;
import com.iportal.cart.model.cart.Payment;
import com.iportal.cart.model.cart.PaymentStatus;
import com.iportal.cart.model.cart.PaymentType;
import com.yage.Globals;
import com.yage.struts.plugin.InitParametersPlugIn;

/**
 * Pagina que no procesa pago sino solo emite un pago registrado para hacer
 * pruebas
 * 
 * @author Mindsoft (hernan)
 * 
 */
public class PaymentDummyController extends PaymentController {

    private Payment payment;

    public PaymentDummyController(Action action) {
	super(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iportal.cart.biz.payment.PaymentIF#getPayment()
     */
    public Payment getPayment() {

	return this.payment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iportal.cart.biz.payment.PaymentIF#initPayment(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse,
     *      com.iportal.cart.model.cart.Order)
     */
    public String initPayment(HttpServletRequest _request,
	    HttpServletResponse _response, Order _order) throws Exception {

	StringBuffer result = new StringBuffer();
	result.append("<html><body>\n");
	result
		.append("<script type=\"text/javascript\">document.location = \"");
	result.append(_request.getContextPath());
	result.append("/portal/cart/payment.do?action=receive&itemCode=");
	result.append(com.iportal.Constants.CONTENT_FLIGHT_SEARCH);
	result.append("&orderCode=");
	result.append(_order.getCode());
	result.append("&paymentTypeCode=");
	result.append(_order.getPayment().getType().getCode());
	result.append("\";</script>\n");
	result.append("</body></html>\n");
	// FIXME pruebas con llaves reales!!!
	// _response.getOutputStream().print(result.toString());
	// _response.getOutputStream().flush();
	return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iportal.cart.biz.payment.PaymentIF#registerPaymentType()
     */
    public PaymentIF registerPaymentType() {
	return this;
    }

    public Payment getPayment(Order order, PaymentType paymentType) {
	if (null == payment) {
	    payment = new Payment();
	}
	payment.setOrder(order);
	payment.setType(paymentType);
	payment.setStatus(PaymentStatus.PENDING);
	payment.setLastStatus(GregorianCalendar.getInstance());
	return payment;
    }

    public Payment processPaymentResponse(HttpServletRequest _request,
	    HttpServletResponse _response) throws Exception {
	Session sess = null;
	Transaction tx = null;
	Order order = null;

	PaymentForm paymentForm =
		(PaymentForm) _request.getAttribute("paymentForm");
	try {
	    // si sesion de controlador no esta iniciada usa crea una nueva
	    sess =
		    (this.session != null && this.session.isOpen()) ? this.session
			    : getHibernateSession();
	    // order = (Order) _request.getSession().getAttribute("order");
	    if (order == null) {
		order =
			(Order) sess.get(Order.class, paymentForm
				.getOrderCode());
	    }
	    // verify IDADQUIRER, IDCOMMERCE and order
	    // recover request data
	    payment = order.getPayment();

	    if (verifyOrderStatus(order)) {
		if (!Hibernate.isInitialized(order)) {
		    sess.refresh(order);
		}

		tx = sess.beginTransaction();
		// response data
		payment.setResponseDate(GregorianCalendar.getInstance());

		Properties initProperties =
			(Properties) _request.getSession().getServletContext()
				.getAttribute(InitParametersPlugIn.DEFAULT_ID);

		Boolean paymentFail =
			(Boolean) initProperties.get("CART_DUMMY_PAYMENT_FAIL");

		if (!paymentFail) {
		    payment.setAuthorizationCode("Dummy1");
		    payment.setResult(Globals.TRUE);
		    payment.setSuccessDate(GregorianCalendar.getInstance());
		    payment.setResultMessage("Operaci&oacute;n autorizada");
		    payment.setStatus(PaymentStatus.AUTHORIZED);
		    order.setStatus((OrderStatus) sess.get(OrderStatus.class,
			    CartConstants.CART_PAYMENT_SUCCESS_STATUS));
		} else {
		    payment.setResult(Globals.FALSE);
		    payment.setRejectDate(GregorianCalendar.getInstance());
		    payment.setResultMessage("Operaci&oacute;n NO autorizada");
		    payment.setStatus(PaymentStatus.REJECTED);
		    order.setStatus((OrderStatus) sess.get(OrderStatus.class,
			    CartConstants.CART_PAYMENT_FAILED_STATUS));
		}
		payment.setLastStatus(GregorianCalendar.getInstance());
		order.setPayment(payment);

		sess.saveOrUpdate(order);
		tx.commit();
		if (!paymentFail) {
		    this.setProcessed(Globals.TRUE);
		}
	    } else {
		this.setProcessed(Globals.FALSE);
	    }

	} catch (Exception e) {
	    this.setProcessed(Globals.FALSE);
	    if (null != tx) {
		tx.rollback();
	    }
	    logger.error(e.getMessage(), e);
	    throw e;
	} finally {
	    if ((null == this.session || !this.session.isOpen())) {
		if (null != sess) {
		    sess.clear();
		    sess.close();
		}

	    }
	}
	return payment;
    }

    public Payment cancelPayment(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	return null;
    }

    public Payment getPayment(Order order, PaymentType paymentType,
	    String extraInfo) {
	return this.getPayment(order, paymentType);
    }

}
