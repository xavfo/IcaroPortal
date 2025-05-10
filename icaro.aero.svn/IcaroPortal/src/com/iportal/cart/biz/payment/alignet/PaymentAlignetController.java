/**
 * Yagé 2007
 */
package com.iportal.cart.biz.payment.alignet;


import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.alignet.bean.VPOSBean;
import com.alignet.exception.InvalidVPOSParameterException;
import com.alignet.exception.PlugInVPOSException;
import com.alignet.plugin.Receive;
import com.alignet.plugin.Send;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.payment.PaymentController;
import com.iportal.cart.biz.payment.PaymentIF;
import com.iportal.cart.biz.payment.alignet.PaymentAlignet.Language;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.OrderStatus;
import com.iportal.cart.model.cart.OrderTransaction;
import com.iportal.cart.model.cart.Payment;
import com.iportal.cart.model.cart.PaymentStatus;
import com.iportal.cart.model.cart.PaymentType;
import com.yage.Globals;
import com.yage.struts.plugin.InitParametersPlugIn;


/**
 *
 * @author burkhard
 * @verion 1.0 created on Apr 2, 2007
 *
 */
public class PaymentAlignetController extends PaymentController {

    private static Log logger = LogFactory.getLog(PaymentAlignetController.class);

    private Properties initProperties;

    private PaymentAlignet payment;

    private static final String KEY_YAGE_CIPHER = "YAGE.CIFRADO.PRIVADA.pem";

    private static final String KEY_YAGE_SIGNATURE = "YAGE.FIRMA.PRIVADA.pem";

    private static final String KEY_ALIGNET_CIPHER = "ALIGNET.CIFRADO.PUBLICA.pem";

    private static final String KEY_ALIGNET_SIGNATURE = "ALIGNET.FIRMA.PUBLICA.pem";

//    private static final String RANDOM_SEED = "494d434951584249";
    private static final String RANDOM_SEED = "54b53072540eeeb8";


    /**
     *
     */
    public PaymentAlignetController(Action action) {
        super(action);
        this.payment = null;
    }


    /**
     * @throws Exception
     * @see com.iportal.cart.biz.payment.PaymentIF#initPayment(com.iportal.cart.model.cart.Order)
     */
    public String initPayment(HttpServletRequest _request, HttpServletResponse _response, Order _order) throws Exception {
        // super.initPayment(_request, _response, _order);
        initProperties = (Properties) _request.getSession().getServletContext().getAttribute(InitParametersPlugIn.DEFAULT_ID);
        payment = (PaymentAlignet) populatePayment(_order, null);

        VPOSBean vPOSBean = new VPOSBean();
        try {
            vPOSBean.setCodigoAdquirente(payment.getCreditCardIssuer());
            vPOSBean.setCodigoMall(payment.getShopBranchCode()); // Not used. should send 0
            vPOSBean.setCodigoComercio(payment.getShopCode().toString());
            vPOSBean.setCodigoTerminal(payment.getTerminalCode());
            vPOSBean.setCodigoOperacion(payment.getOrder().getLastTransactionCode().toString());
            vPOSBean.setMonto(payment.getValue());
            vPOSBean.setCodigoMoneda(payment.getCurrency());
            vPOSBean.setReservado1(String.valueOf(Math.round(_order.getBeforeDiscount().doubleValue()*100)));
            vPOSBean.setReservado2(String.valueOf(Math.round(_order.getIvaAmount().doubleValue()*100)));
            vPOSBean.setReservado3("0");
            vPOSBean.setReservado4("0");
            vPOSBean.setReservado5(String.valueOf(Math.round((_order.getTotalAmount()-_order.getBeforeDiscount()-_order.getIvaAmount())*100)));
        } catch (InvalidVPOSParameterException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        // Idioma
//        vPOSBean.setReservado6(payment.getLang().toString());
        // Source
//        vPOSBean.setReservado7(payment.getSource());
        try {
            Send send = new Send(
                new InputStreamReader(this.getClass().getResourceAsStream(KEY_ALIGNET_CIPHER)),
                new InputStreamReader(this.getClass().getResourceAsStream(KEY_YAGE_SIGNATURE)),
                RANDOM_SEED);
            send.execute(vPOSBean);
        } catch (PlugInVPOSException e) {
            logger.error(e);
            throw e;
        }
        String sessionKey = vPOSBean.getEncryptEncodedSesionKeyReq();
        String xmlReq = vPOSBean.getEncryptEncodedXmlReq();
        String digitalSign = vPOSBean.getEncryptEncodedSignReq();
        payment.setRequestSessionKey(sessionKey);
//        payment.setStatus(PaymentStatus.PROCESING);
        payment.setLastStatus(GregorianCalendar.getInstance());

        String actionURL = (String) initProperties.get("ALIGNET_ACTION_URL");
        StringBuffer result = new StringBuffer();
//        result.append("<html><body>\n");
        result.append("<form name=\"alignetRequestForm\" method=\"post\" action=\"" + actionURL + "\">\n");
        result.append("    <input type=\"hidden\" name=\"XMLREQ\" value=\"" + xmlReq + "\">\n");
        result.append("    <input type=\"hidden\" name=\"DIGITALSIGN\" value=\"" + digitalSign + "\">\n");
        result.append("    <input type=\"hidden\" name=\"IDACQUIRER\" value=\"" + payment.getCreditCardIssuer() + "\">\n");
        result.append("    <input type=\"hidden\" name=\"IDCOMMERCE\" value=\"" + payment.getShopCode() + "\">\n");
        result.append("    <input type=\"hidden\" name=\"SESSIONKEY\" value=\"" + sessionKey + "\">\n");
        result.append("    <br/><br/>");
        result.append("    <p align=\"center\"><input type=\"submit\" id=\"submitBtn\" name=\"Submit\" value=\"Siguiente\"></p>\n");
        result.append("</form>\n");
        result.append("<script type=\"text/javascript\">document.alignetRequestForm.submit();document.alignetRequestForm.submitBtn.disabled=true;</script>\n");
//        result.append("</body></html>\n");
        return result.toString();
    }


    protected Payment populatePayment(Order _order, Payment _payment) {
        super.populatePayment(_order, payment);
        payment.setCreditCardIssuer(((Integer)initProperties.get("ALIGNET_IDACQUIRER")).toString());
        payment.setShopBranchCode(Long.valueOf(0L));
        payment.setShopCode((Integer) initProperties.get("ALIGNET_IDCOMMERCE"));
        payment.setTerminalCode((String)initProperties.get("ALIGNET_IDTERMINAL"));
        payment.setCurrency((String)initProperties.get("ALIGNET_CURRENCY"));
        payment.setLang(Language.ES);
        //payment.setSource(null);
        return payment;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.iportal.cart.biz.payment.PaymentIF#processPaymentResponse(javax.servlet.http.HttpServletRequest)
     */
    public Payment processPaymentResponse(HttpServletRequest _request, HttpServletResponse _response) throws Exception {
        String xmlRes = _request.getParameter("XMLRES");
        String digitalSign = _request.getParameter("DIGITALSIGN");
        String idAcquirer = _request.getParameter("IDACQUIRER");
        String idCommerce = _request.getParameter("IDCOMMERCE");
        String sessionKey = _request.getParameter("SESSIONKEY");

        VPOSBean vPOSBean = new VPOSBean();
        try {
            vPOSBean.setEncryptEncodedSesionKeyRes(sessionKey);
            vPOSBean.setEncryptEncodedXmlRes(xmlRes);
            vPOSBean.setEncryptEncodedSignRes(digitalSign);
        } catch (InvalidVPOSParameterException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

        try {
            Receive receive = new Receive(
                new InputStreamReader(this.getClass().getResourceAsStream(KEY_ALIGNET_SIGNATURE)),
                new InputStreamReader(this.getClass().getResourceAsStream(KEY_YAGE_CIPHER)),
                RANDOM_SEED);
            receive.execute(vPOSBean);
        } catch (PlugInVPOSException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        // Verificando la firma digital, que permitirá verificar la integridad
        // de los datos
        if(vPOSBean.isValidSign()) {
            Session sess = null;
            Transaction tx = null;
            Order order = null;
            OrderTransaction orderTransaction = null;
            try {
                sess = getHibernateSession();
                orderTransaction = (OrderTransaction) sess.get(OrderTransaction.class, Long.valueOf(vPOSBean.getCodigoOperacion()));
                //order = (Order)sess.get(Order.class, new Long(vPOSBean.getCodigoOperacion()));
                order = orderTransaction.getOrder();
                // verify IDADQUIRER, IDCOMMERCE and order
                if( !vPOSBean.getCodigoAdquirente().equals(idAcquirer) ||
                    !vPOSBean.getCodigoComercio().equals(idCommerce) || null == order ) {
                    throw new PlugInVPOSException("Invalid answer received from plug-in");
                }
                tx = sess.beginTransaction();
                // recover request data
                payment = new PaymentAlignet(order.getPayment());
                payment.setCreditCardIssuer(vPOSBean.getCodigoAdquirente());
                payment.setShopBranchCode(Long.valueOf(vPOSBean.getCodigoMall()));
                payment.setShopCode(Integer.valueOf(vPOSBean.getCodigoComercio()));
                payment.setTerminalCode(vPOSBean.getCodigoTerminal());
                payment.setCurrency(vPOSBean.getCodigoMoneda());
//                payment.setLang(Language.valueOf(vPOSBean.getReservado6()));
//                payment.setSource(vPOSBean.getReservado7());
                // response data
                payment.setAuthorizationCode(vPOSBean.getCodigoAutorizacion());
                payment.setResponseDate(GregorianCalendar.getInstance());
                payment.setResponseSessionKey(sessionKey);
                if(vPOSBean.getEstadoAutorizacion().equals("00")) {
                    payment.setResult(Globals.TRUE);
                    payment.setSuccessDate(GregorianCalendar.getInstance());
                    payment.setErrorCode(vPOSBean.getCodigoAutorizacion());
                    payment.setResultMessage("Operaci&oacute;n autorizada");
                    payment.setStatus(PaymentStatus.AUTHORIZED);
                    OrderStatus status = (OrderStatus) sess.get(OrderStatus.class, CartConstants.CART_PAYMENT_SUCCESS_STATUS);
                    order.setStatus(status);
                    orderTransaction.setStatus(status);
                    this.setProcessed(Boolean.TRUE);
                } else {
                    payment.setResult(Globals.FALSE);
                    payment.setRejectDate(GregorianCalendar.getInstance());
                    payment.setErrorCode(vPOSBean.getCodigoError());
                    payment.setResultMessage(vPOSBean.getMensajeError());
                    payment.setStatus(PaymentStatus.REJECTED);
                    OrderStatus status = (OrderStatus) sess.get(OrderStatus.class, CartConstants.CART_PAYMENT_FAILED_STATUS);
                    order.setStatus(status);
                    orderTransaction.setStatus(status);
                    this.setProcessed(Boolean.FALSE);
                }
                payment.setLastStatus(GregorianCalendar.getInstance());
                order.setLastStatus(payment.getLastStatus());
                orderTransaction.setLastUpdate(payment.getLastStatus());
                payment.getOrder().setLastStatus(GregorianCalendar.getInstance());
                payment.setEci(vPOSBean.getReservado5());
                payment.setVci(vPOSBean.getReservado6());
                payment.setCreditCard(vPOSBean.getPAN());
                payment.setCreditCardValidDate(vPOSBean.getExpDate());
                order.setPayment(payment);
                sess.saveOrUpdate(orderTransaction);
                sess.saveOrUpdate(order);

                // Audit Transaction
                SysAuditHelper.audit(action, _request, order, order.getCode().toString(), Globals.AUDIT_UPDATE, sess);
                SysAuditHelper.audit(action, _request, orderTransaction, orderTransaction.getCode().toString(), Globals.AUDIT_UPDATE, sess);
                tx.commit();
            } catch (Exception e) {
                if(null != tx) {
                    tx.rollback();
                }
                logger.error(e.getMessage(), e);
                throw e;
            } finally {
                if(null != sess) {
                    sess.close();
                }
                logger.debug("EstadoAutorizacion: "+vPOSBean.getEstadoAutorizacion());
                logger.debug("CodigoError: "+vPOSBean.getCodigoError());
                logger.debug("MensajeError: "+vPOSBean.getMensajeError());
                logger.debug("CodigoAutorizacion: "+vPOSBean.getCodigoAutorizacion());
            }
        } else {
            throw new PlugInVPOSException("Invalid answer received from plug-in");
        }
        return payment;
    }


    /**
     * @see com.iportal.cart.biz.payment.PaymentIF#registerPaymentType()
     */
    public PaymentIF registerPaymentType() {
        return this;
    }


    public Payment getPayment() {
        return payment;
    }


    /* (non-Javadoc)
     * @see com.iportal.cart.biz.payment.PaymentIF#getPayment(com.iportal.cart.model.cart.PaymentType)
     */
    public Payment getPayment(Order order, PaymentType paymentType, String extraInfo) {
        if(null == payment) {
            payment = new PaymentAlignet();
        }
        payment.setOrder(order);
        payment.setType(paymentType);
        payment.setExtraInfo(extraInfo);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setLastStatus(GregorianCalendar.getInstance());
        return payment;
    }


    public Payment cancelPayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new RuntimeException("Access not allowed");
    }

}
