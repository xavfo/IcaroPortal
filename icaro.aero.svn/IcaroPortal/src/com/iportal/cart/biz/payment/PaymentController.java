/**
 * Yagé 2007
 */
package com.iportal.cart.biz.payment;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.Session;

import com.iportal.biz.BaseHelper;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.Payment;
import com.yage.Globals;

/**
 *
 * @author burkhard
 * @verion 1.0
 * created on Apr 2, 2007
 *
 */
public abstract class PaymentController extends BaseHelper implements PaymentIF {

    protected static Log logger = LogFactory.getLog(PaymentController.class);

    protected Action action;
    protected Session session;

    protected Boolean processed;



    protected PaymentController(Action action) {
        super();
        this.session = null;
        this.processed = Globals.FALSE;
        this.action = action;
    }

    /**
     * @see com.iportal.cart.biz.payment.PaymentIF#processPaymentResponse(javax.servlet.http.HttpServletRequest)
     */
    public Payment processPaymentResponse(HttpServletRequest _request, HttpServletResponse _response)  throws Exception {
        return null;
    }

    public void setSession(Session sess)  {
        this.session = sess;
    }





    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    protected Payment populatePayment(Order _order, Payment _payment) {
        if(null == _payment) {
            throw new NullPointerException("Parameter can not be null");
        }
        _payment.setOrder(_order);
        _payment.setValue(new Long(Math.round(_order.getTotalAmount()*100.0)));
        _payment.setRequestDate(GregorianCalendar.getInstance());
        _payment.setResult(Globals.FALSE);
        return _payment;
    }

    protected boolean  verifyOrderStatus (Order order) {
        boolean resp = false;
        if ( com.iportal.cart.CartConstants.CART_PENDING_PAYMENT_STATUS.equals(order.getStatus().getCode())) {
            resp = true;
        }
        return resp;

    }





}

