/**
 * Yagé 2007
 */
package com.iportal.cart.ctrl;

import java.lang.reflect.Constructor;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import aero.icaro.service.skies.SkiesServiceManager;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.biz.helper.TicketPNRHelper;
import com.iportal.biz.worldspan.TicketPNRResponse;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.payment.PaymentIF;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.PaymentStatus;
import com.iportal.cart.model.cart.PaymentType;
import com.iportal.cart.model.customer.Customer;
import com.iportal.cart.model.tax.Tax;
import com.iportal.ctrl.icaro.ClientWebServiceCall;
import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.commons.StringBean;
import com.yage.struts.action.BaseDispatchAction;

/**
 * The Class PaymentAction.
 *
 * @author burkhard
 * @verion 1.0
 * created on Apr 4, 2007
 *
 */
public class PaymentAction extends BaseDispatchAction  implements ClientWebServiceCall {



    private SkiesServiceManager managerSkies;

    private static Log logger = LogFactory.getLog(CartAction.class);

    public void initManagerSkies(HttpServletRequest request) throws ServiceException {
        if (this.managerSkies == null) {
            this.managerSkies = new SkiesServiceManager(this, request);
        } else {
            this.managerSkies.setRequest(null);
            this.managerSkies.setRequest(request);
        }
    }

    public void initManagerCorp(HttpServletRequest request) throws ServiceException {
    }

    public void initManagerAgency(HttpServletRequest request) throws ServiceException {
    }

//    public ActionForward send(
//            ActionMapping mapping,
//            ActionForm form,
//            HttpServletRequest request,
//            HttpServletResponse response)
//    throws Exception {
//
//        ActionMessages messages = new ActionMessages();
//
//        PaymentForm paymentForm =  (PaymentForm) form;
//
//        Session sess = null;
//        Transaction tx = null;
//        try {
//            sess = getHibernateSession();
////            tx = sess.beginTransaction();
////            sess.saveOrUpdate(_order);
////            tx.commit();
//            PaymentType pt = (PaymentType) sess.get(PaymentType.class, paymentForm.getPaymentType());
//            Class clazz =  Class.forName(pt.getExternalCode());
//            Object obj = clazz.newInstance();
//            PaymentIF payment;
//            if(obj instanceof PaymentIF) {
//                payment = (PaymentIF)obj;
//                payment.initPayment(request, response, paymentForm.getOrder());
//            }
//        } catch (Exception e) {
//            if(null != tx) {
//                tx.rollback();
//            }
//            logger.error(e.getMessage(), e);
//            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
//        } finally {
//            if(null != sess) {
//                sess.close();
//            }
//        }
//
//        return null;
//    }
//
    /**
 * Receive.
 *
 * @param mapping the mapping
 * @param form the form
 * @param request the request
 * @param response the response
 *
 * @return the action forward
 *
 * @throws Exception the exception
 */
    public ActionForward receive(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
    throws Exception {

        ActionMessages messages = new ActionMessages();
        HttpSession session = request.getSession();
        PaymentForm paymentForm =  (PaymentForm) form;
        Order order = null;
        Session sess = null;
        Transaction tx = null;
        Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
        LanguageUtils utils = new LanguageUtils();


        try {
            sess = getHibernateSession();
//            tx = sess.beginTransaction();
//            sess.saveOrUpdate(_order);
//            tx.commit();
            PaymentType pt = (PaymentType) sess.get(PaymentType.class, paymentForm.getPaymentTypeCode() );
            Class<?> clazz =  Class.forName(pt.getExternalCode());
            Constructor<?> constr = clazz.getConstructor(new Class[] {Action.class});
            Object obj = constr.newInstance(new Object[] {this});
            PaymentIF payment;
            if(obj instanceof PaymentIF) {
                payment = (PaymentIF)obj;
                payment.setSession(sess);

                payment.processPaymentResponse(request, response);
                order = payment.getPayment().getOrder();
                //Traer la orden para iniciarla y mandarla a pagina de confirmacion
                if (!Hibernate.isInitialized(order.getPassengers()) || !Hibernate.isInitialized(order.getItineraries())) {
                sess.refresh(order);
                if (!Hibernate.isInitialized(order.getPassengers())) {
                    Hibernate.initialize(order.getPassengers());
                    }
                }
                if (!Hibernate.isInitialized(order.getItineraries())) {
                    Hibernate.initialize(order.getItineraries());
                }

                if (order.getLanguageCode() != null) {
                    language = utils.getLanguage(order.getLanguageCode());
                } else {
                    //language = utils.getLanguage(request);
                    language = utils.getLanguage(Constants.LANGUAGE_SPANISH);
                }
                session.setAttribute(Constants.LANGUAGE_KEY, language);

                session.setAttribute("order", order);

                Tax ivaTax = (Tax) sess.load(Tax.class, Constants.IVA_TAX_CODE);
                double ivaRate = (ivaTax != null)?ivaTax.getCurrentRate():0D;
                if (ivaTax != null) {
                    session.setAttribute("ivaRate", ivaTax.getCurrentRate());
                }
                session.setAttribute("today", Calendar.getInstance());
                order.setIvaAmount(ivaRate*order.getBeforeDiscount());
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PAYMENT);

                //pago para la pagina final de proceso de pago.
                if (payment.getPayment() != null && PaymentStatus.AUTHORIZED.getCode().equals(payment.getPayment().getStatus().getCode())) {

                    request.setAttribute("cartContent", "cart.paymentSuccess");
                } else {
                    request.setAttribute("cartContent", "cart.paymentFailed");
                }

                /** comprueba si El pagos e ha procesado correctamente y se ha actualizado*/
                if (payment.getProcessed()) {
                    /** Si el pago fue procesado invoca a servicio worlspan para generar tickets*/
                    TicketPNRHelper helper = new TicketPNRHelper ();
                    TicketPNRResponse ticketPNR = helper.queryTicketPNR(order.getRecordLocator());

                    if (ticketPNR != null && ticketPNR.getErrorMessage() == null) {
                        tx = sess.beginTransaction();
                        order.setSale(Globals.TRUE);
                        order.setTicketDate(ticketPNR.getTicketDate());
                        StringBuffer ticketList = new StringBuffer();
                        int count = 0;
                        for (StringBean s :ticketPNR.getETickets()  ) {
                            if (count > 0) {
                                ticketList.append(",");
                            }
                            ticketList.append(s.toString());
                            count++;

                        }
                        order.setTicketNumbers(ticketList.toString());

                        sess.saveOrUpdate(order);
                        tx.commit();

                        /* FIXME PAYMENT llamar a WebService de actualizacion de millas skies*/

                        /*IcaroServiceMessageBean accountUpdate=null;
                        this.initManagerSkies(request);
                        for (String s :ticketPNR.getETickets()  ) {
                            accountUpdate=this.managerSkies.updateSkiesAccount(accountForm);

                            if (accountUpdate == null) {
                                messages.add(ActionMessages.GLOBAL_MESSAGE,
                                        new ActionMessage("error.webService.client.login"));
                            } else if (accountUpdate.getStatusCode().intValue() == Constants.FALSE_INT) {
                                messages.add(ActionMessages.GLOBAL_MESSAGE,
                                        new ActionMessage("error.webService.client.message.response", accountUpdate.getResponse()));
                            }
                        }*/

                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PAYMENT);

                    } else {
                        //TODO para mejorar esto se podria Actualizar orden a Estado que indica error en la generacion de tickets para posteriror proceso
                        request.setAttribute("ticketPNRError", Globals.TRUE);// Con este indicardor se envia una mail

                        //al administrador para que arregle el tema de generacion de tickets
                        //Se informa en pantalla sobre problema al cliente
                        if (ticketPNR != null) {
                            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.ticketPNRErrorMsg", ticketPNR.getErrorMessage()));
                            request.setAttribute("ticketPNRErrorMessage", ticketPNR.getErrorMessage());
                        } else {
                            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.ticketPNRError"));
                        }
                    }

                } else {
                    if (!PaymentStatus.AUTHORIZED.getCode().equals(payment.getPayment().getStatus().getCode())) {
                        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.paymentNotificationError"));
                    } else {
                        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.paymentAuthorizedBefore", DateFormatUtils.format( payment.getPayment().getSuccessDate().getTime(), Constants.DATE_TIME_FORMAT)));
                    }
                }
            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
            }
        } catch (Exception e) {
            if(null != tx) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
        } finally {
            if(null != sess) {
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
            }
        }

         if (!messages.isEmpty()) {
        saveMessages(request, messages);
             return mapping.getInputForward();
         }
         messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.paymentAuthorized"));

        if (language == null) {
            language = utils.getLanguage(Constants.LANGUAGE_SPANISH);
            session.setAttribute(Constants.LANGUAGE_KEY, language);
    }

        return mapping.findForward(Globals.FORWARD_SUCCESS+"_receive");
    }

    /**
     * Cancel.
     *
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     *
     * @return the action forward
     *
     * @throws Exception the exception
     */
    public ActionForward cancel(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
    throws Exception {

        ActionMessages messages = new ActionMessages();

        PaymentForm checkoutForm =  (PaymentForm) form;

        Session sess = null;
//        Transaction tx = null;
        try {
            sess = getHibernateSession();
            PaymentType pt = (PaymentType) sess.get(PaymentType.class, checkoutForm.getPaymentTypeCode());
            Class<?> clazz =  Class.forName(pt.getExternalCode());
            Constructor<?> constr = clazz.getConstructor(new Class[] {Action.class});
            Object obj = constr.newInstance(new Object[] {this});
            PaymentIF payment;
            if(obj instanceof PaymentIF) {
                payment = (PaymentIF)obj;
                payment.cancelPayment(request, response);
                Order order = payment.getPayment().getOrder();
                sess.refresh(order);
                order.setPayment(payment.getPayment());
                if (!Hibernate.isInitialized(order.getItineraries())) {
                    Hibernate.initialize(order.getItineraries());
                }
                if (!Hibernate.isInitialized(order.getPassengers())) {
                    Hibernate.initialize(order.getPassengers());
                }
                // Restore User session
                Customer cust = order.getCustomer();
                sess.refresh(cust);
//                Hibernate.initialize(cust.getAddresses());
                request.getSession().setAttribute(CartConstants.CUSTOMER, cust);

                //FIXME revisar si esta correcto esto intenta actualizar el stock hasta que funcione intenta 3 veces
                Integer orderIntentCount = (Integer) request.getSession().getAttribute("orderIntentCount");
                if(null == orderIntentCount) {
                    orderIntentCount = Integer.valueOf(1);
                }
                if(3 >= orderIntentCount.intValue()) {
                    orderIntentCount++;
                    request.getSession().setAttribute("lastOrder", order);
                    request.getSession().setAttribute("orderIntentCount", orderIntentCount);
                } else {
                    request.getSession().removeAttribute("lastOrder");
                    request.getSession().removeAttribute("orderIntentCount");
                }

                request.setAttribute("order", order);

                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_RESULT);
                request.setAttribute("cartContent", "cart.result");
            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
            }
        } catch (Exception e) {
//            if(null != tx) {
//                tx.rollback();
//            }
            logger.error(e.getMessage(), e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
        } finally {
            if(null != sess) {
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
            }
        }

        saveMessages(request, messages);
        return mapping.findForward(Globals.FORWARD_SUCCESS+"_receive");

    }

}

