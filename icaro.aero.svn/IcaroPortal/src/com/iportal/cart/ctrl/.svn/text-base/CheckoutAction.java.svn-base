/**
 *
 */
package com.iportal.cart.ctrl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
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

import com.iportal.Constants;
import com.iportal.biz.worldspan.PSAlternateInfo;
import com.iportal.biz.worldspan.PaxTypeInfo;
import com.iportal.biz.worldspan.PowerShopperResponse;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.biz.payment.PaymentIF;
import com.iportal.cart.ctrl.flight.SearchFlightForm;
import com.iportal.cart.model.cart.Itinerary;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.OrderStatus;
import com.iportal.cart.model.cart.OrderTransaction;
import com.iportal.cart.model.cart.Passenger;
import com.iportal.cart.model.cart.PaymentType;
import com.iportal.cart.model.customer.BillInfo;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.InitParametersPlugIn;

/**
 * Acción que se encarga de interactuar con el carrito de compras. Las tareas que se invocan en esta acción son para
 * añadir, actualizar o elimanar productos del carrito de compras
 *
 * Version modificada para carrito de Banco del Austro
 *
 * @author hleon
 * @version 1.2
 */
public class CheckoutAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(CheckoutAction.class);

    /*
     * public ActionForward listStates(ActionMapping mapping, ActionForm form, HttpServletRequest request,
     * HttpServletResponse response){
     *
     * CheckoutForm checkoutForm = (CheckoutForm) form; List<RowItem> stateList = null; List<RowItem> cityList = null;
     * try { stateList = QueryHelper.queryStatesByCountry(checkoutForm.getCountryCode(),null,null); if (stateList.size()
     * > 0) { boolean changeState = true; if (checkoutForm.getStateCode() != null) { for (RowItem item : stateList) { if
     * (item.getCode().equals(checkoutForm.getStateCode())) { changeState = false; break; } } } if (changeState) {
     * checkoutForm.setStateCode(stateList.get(0).getCode()); } cityList =
     * QueryHelper.queryCitiesByState(checkoutForm.getStateCode(), null, null, Globals.TRUE); } } catch (Exception e) {
     * logger.error(e.getMessage(), e); } request.setAttribute("listOfOptions", stateList);
     * request.setAttribute("targetProperty", "stateCode"); request.setAttribute("theForm", form);
     * request.setAttribute("extra", "onchange=\"retrieveURL(CONTEXT_PATH +
     * '/portal/cart/checkout.do?action=listSecondaries',this.form);\"");
     *
     *
     * if (stateList.size() > 0) { request.setAttribute("listOfSecOptions", cityList);
     * request.setAttribute("targetSecProperty", "cityCode"); request.setAttribute("targetTerProperty",
     * "deliveryAgencyCode"); request.setAttribute("extra2", "onchange=\"setFields();\"");
     * request.setAttribute("extra3", "onchange=\"setFields();\""); }
     *
     * return mapping.findForward("ajaxForm"); }
     *
     *
     * public ActionForward listCities(ActionMapping mapping, ActionForm form, HttpServletRequest request,
     * HttpServletResponse response){ CheckoutForm checkoutForm = (CheckoutForm) form; List cityList = null; try {
     * cityList = QueryHelper.queryCitiesByState(checkoutForm.getStateCode(), null, null, Globals.TRUE); } catch
     * (Exception e) { logger.error(e.getMessage(), e); } request.setAttribute("listOfSecOptions", cityList);
     * request.setAttribute("targetSecProperty", "cityCode"); request.setAttribute("extra2",
     * "onchange=\"setFields();\""); request.setAttribute("theForm", form);
     *
     * return mapping.findForward("ajaxSecForm"); }
     */

    private boolean verifySession(HttpSession session, ActionMessages messages, Order order) {
        PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");

        UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);

        if (null == order || ((null == powerShop || null == icaroUser) && (order.getRecordLocator() == null || order.getRecordLocator().length() == 0))) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.lostData", "requerimiento"));
            return false;
        } else {
            // verificar que no haya expirado la order
            Calendar now = new GregorianCalendar();
            if (now.after(order.getEffectiveTo())) {
                // order exired
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.bookExpired", order.getEffectiveTo().getTime()));
                return false;
            }
        }
        return true;
    }

    /**
     * Función que se llama para cargar la informacion de una orden nuevamente para intentar volver a pagar
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward loadOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Session sess = getHibernateSession();

        ActionMessages messages = new ActionMessages();

        SearchFlightForm searchForm = (SearchFlightForm) form;
        UserBean icaroUser = (UserBean) request.getSession().getAttribute(Constants.CLIENT_KEY);
        if (icaroUser == null) {
            return mapping.findForward("account");
        }
        try {
            sess = getHibernateSession();
            Order order = (Order) sess.get(Order.class, searchForm.getCode());
            // verificar que no haya expirado la order
            Calendar now = new GregorianCalendar();
            boolean cannotPay = false;
            if (now.after(order.getEffectiveTo())) {
                // order exired
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.bookExpired", order.getRecordLocator() != null ? order.getRecordLocator() : order.getCode(), order
                    .getEffectiveTo().getTime()));
                cannotPay = true;
            }
            if (order.getStatus() != null && CartConstants.CART_PAYMENT_SUCCESS_STATUS == order.getStatus().getCode()) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.paymentAuthorizedBefore", order.getPayment().getSuccessDate().getTime()));
                cannotPay = true;
            }
            if (order.getRecordLocator() == null) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.bookFailed.noRecordLocator"));
                cannotPay = true;
            }

            if (cannotPay) {
                // Report error action
                saveMessages(request, messages);
                return mapping.findForward(Globals.FORWARD_FAILURE);

            }

            Hibernate.initialize(order.getItineraries());
            Hibernate.initialize(order.getPassengers());
            session.setAttribute("order", order);

            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PAYMENT);
            searchForm.setNextStep("cart.payment");
            request.setAttribute("cartContent", searchForm.getNextStep());
            BillInfo billInfo = order.getBillInfo();
            if (billInfo != null) {
                searchForm.setBillAddress(billInfo.getAddress());
                searchForm.setBillName(billInfo.getName());
                searchForm.setBillPhone(billInfo.getPhone());
                searchForm.setBillId(billInfo.getIdentity());
            }

            // populate indexed properties
            int size = order.getPassengers().size();
            String[] emails = new String[size];
            String[] firstNames = new String[size];
            String[] lastNames = new String[size];
            String[] nationalities = new String[size];
            String[] phones = new String[size];
            String[] mobiles = new String[size];

            String[] frequenTravelNumbers = new String[size];
            String[] identities = new String[size];
            Integer[] birthDays = new Integer[size];
            Integer[] birthMonths = new Integer[size];
            Integer[] birthYears = new Integer[size];
            Long[] idTypeCodes = new Long[size];
            Long[] paxTypeCodes = new Long[size];

            // cargar datos
            int i = 0;
            PSAlternateInfo info = new PSAlternateInfo();
            PaxTypeInfo pax = null;
            for (Passenger passenger : order.getPassengers()) {

                emails[i] = passenger.getEmail();
                firstNames[i] = passenger.getFirstName();
                lastNames[i] = passenger.getLastName();
                nationalities[i] = passenger.getNationality();
                phones[i] = passenger.getPhone();
                mobiles[i] = passenger.getMobile();

                frequenTravelNumbers[i] = passenger.getFrequenTravelNumber();
                identities[i] = passenger.getIdentity();
                if (passenger.getBirthdate() != null) {
                    Calendar birthdate = Calendar.getInstance();
                    birthdate.setTime(passenger.getBirthdate());
                    birthDays[i] = birthdate.get(Calendar.DAY_OF_MONTH);
                    birthMonths[i] = birthdate.get(Calendar.MONTH);
                    birthYears[i] = birthdate.get(Calendar.YEAR);
                }
                idTypeCodes[i] = passenger.getIdType().getCode();
                paxTypeCodes[i] = passenger.getPaxType().getCode();
                i++;

                // aumenta datos en los paxInfo para poweroptions
                // add pax info
                boolean addPaxTypeInfo = true;
                // buscar en la lista si ya este este tipo de pasajero, si ya esta aumentar el contador
                for (PaxTypeInfo paxTypeInfo : info.getPaxTypes()) {
                    if (paxTypeInfo.getPaxType().getCode().equals(passenger.getPaxType().getCode())) {
                        // aumentar el contador de pasajeros de este tipo
                        paxTypeInfo.setNumberOfPax(paxTypeInfo.getNumberOfPax() + 1);
                        addPaxTypeInfo = false;
                    }

                }

                // no es crear la entrada del paxtype
                if (addPaxTypeInfo) {
                    pax = new PaxTypeInfo();
                    pax.setBaseFare(passenger.getFareAmount());
                    pax.setIvaRate(passenger.getIvaAmount());
                    pax.setTotalFare(passenger.getTotalAmount());
                    pax.setNumberOfPax(1);
                    pax.setPaxType(passenger.getPaxType());
                    pax.setPaxTypeCode(passenger.getPaxType().getExternalCode());
                    pax.setTax1(passenger.getTaxATAmount());
                    pax.setTax2(passenger.getTaxWTAmount());
                    pax.setTax3(passenger.getTax3Amount());

                    info.addPaxTypeInfo(pax);
                }

            }

            if (order.getItineraries() != null && order.getItineraries().size() > 0) {
                Itinerary departure = order.getItineraries().get(0);
                Itinerary arrival = order.getItineraries().get(order.getItineraries().size() - 1);
                searchForm.setFromCityCode(departure.getDepartureIataCode());
                searchForm.setToCityCode(arrival.getArrivalIataCode());

                searchForm.setFrom(departure.getDepartureDateTime());
                searchForm.setTo(arrival.getArrivalDateTime());
                searchForm.setTimeFrom("0001");
                searchForm.setTimeTo("0001");

            }

            searchForm.setIdTypeCode(idTypeCodes);

            searchForm.setIdentity(identities);
            searchForm.setFrequentTravelNumber(frequenTravelNumbers);

            searchForm.setBirthDay(birthDays);
            searchForm.setBirthMonth(birthMonths);
            searchForm.setBirthYear(birthYears);

            searchForm.setPaxTypeCode(paxTypeCodes);
            searchForm.setEmail(emails);
            searchForm.setFirstName(firstNames);
            searchForm.setLastName(lastNames);
            searchForm.setNationality(nationalities);
            searchForm.setPhone(phones);
            searchForm.setMobile(mobiles);
            // cargar objeto PowerShopperResponse con valores para pagina de pago
            PowerShopperResponse powerShop = new PowerShopperResponse();

            powerShop.setAlternateCount(1);
            powerShop.setSelectedAlternativeOptionIndex(0);
            powerShop.addAlternateInfo(info);
            // cargar info de itinerarios con preciosa
            double subtotal = order.getBeforeDiscount() - order.getDiscount();
            info.setBaseFare(subtotal);
            info.setTaxFare(order.getTotalAmount() - subtotal);
            info.setTotalFare(order.getTotalAmount());

            session.setAttribute("powerShopOptions", powerShop);
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
        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    public ActionForward saveOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Session sess = getHibernateSession();
        Transaction tx = null;
        ActionMessages messages = new ActionMessages();

        try {
            Properties initProperties = (Properties) request.getSession().getServletContext().getAttribute(InitParametersPlugIn.DEFAULT_ID);
            SearchFlightForm searchForm = (SearchFlightForm) form;
            Long cartInitialOrderStatus = (Long) initProperties.get("CART_PENDING_PAYMENT_STATUS");
            Order order = null;
            if (null == cartInitialOrderStatus) {
                cartInitialOrderStatus = CartConstants.CART_PENDING_PAYMENT_STATUS;
            }
            if (searchForm.getOrderCode() != null && searchForm.getOrderCode() > 0L) {
                // si el se esta intentando enviar de nuevo una orden a cobrar
                // consultar los datos del orden
                order = (Order) sess.get(Order.class, searchForm.getOrderCode());
            } else {
                order = (Order) session.getAttribute("order");
                sess.refresh(order);
            }
            if (!verifySession(session, messages, order)) {
                saveMessages(request, messages);
                return mapping.findForward(Globals.FORWARD_FAILURE);
            }

            /*
             * PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");
             *
             * UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
             */

            order.setStatus((OrderStatus) sess.get(OrderStatus.class, cartInitialOrderStatus));
            order.setLastStatus(Calendar.getInstance());

            BillInfo billInfo = order.getBillInfo();
            if (null == billInfo) {
                billInfo = new BillInfo();
                order.setBillInfo(billInfo);
            }

            // actualiza datos de Factura
            PaymentType payType = null;
            if (searchForm.getOrderCode() != null && searchForm.getOrderCode() > 0L) {
                // usar los datos anteriores
                payType = order.getPayment().getType();

            } else {
                billInfo.setAddress(searchForm.getBillAddress());
                billInfo.setIdentity(searchForm.getBillId());
                billInfo.setName(searchForm.getBillName());
                billInfo.setPhone(searchForm.getBillPhone());

                payType = (PaymentType) sess.get(PaymentType.class, searchForm.getPaymentTypeCode());

            }
            if (!Hibernate.isInitialized(order.getOrderTransactions())) {
                Hibernate.initialize(order.getOrderTransactions());
            }

            preparePayment(payType, order, order.getItineraries(), order.getPassengers(), null, request, response, sess, tx, messages);

            if (order.getCode() == null) {
                sess.refresh(order);
            }
            session.removeAttribute("searchFlightForm");
            session.removeAttribute("powerShopOptions");
            session.removeAttribute("availibility");
            // TODO revisar si esto es correcto elimnar la orden de la sesion
            // tambien
            // session.removeAttribute("order");
        } catch (Exception e) {
            if (null != tx) {
                tx.rollback();
            }
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
            logger.error(e.getMessage(), e);
        } finally {
            if (null != sess) {
                sess.clear();
                sess.close();
            }
        }

        // Report a success action
        saveMessages(request, messages);
        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    /**
     * Función que se llama cuando se quiere mostrar los objetos en el carrito de Compras
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
        request.setAttribute("cartContent", "cart.searchFlight");
        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    public ActionForward tryAgain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Session sess = getHibernateSession();
        Transaction tx = null;
        ActionMessages messages = new ActionMessages();

        try {
            // if (!verifySession(session, messages)) {
            // return mapping.findForward(Globals.FORWARD_FAILURE);
            // }
            Properties initParams = (Properties) request.getSession().getServletContext().getAttribute(InitParametersPlugIn.DEFAULT_ID);
            // FIXME - do not just re-intent the payment but recreate the cart
            // and start over again.
            // create Order
            Order order = new Order();
            Order lastOrder = (Order) session.getAttribute("lastOrder");
            if (null == lastOrder || !(lastOrder instanceof Order)) {
                throw new IllegalAccessError("Now previous order found");
            } else {
                PropertyUtils.copyProperties(order, lastOrder);
            }
            order.setCode(null);
            order.setCreation(new GregorianCalendar());
            order.setEnabled(Globals.TRUE);
            order.setLastStatus(order.getCreation());
            Long cartInitialOrderStatus = (Long) initParams.get("CART_PENDING_PAYMENT_STATUS");
            if (null == cartInitialOrderStatus) {
                cartInitialOrderStatus = CartConstants.CART_PENDING_PAYMENT_STATUS;
            }
            order.setStatus((OrderStatus) sess.get(OrderStatus.class, cartInitialOrderStatus));
            for (Itinerary itinerary : lastOrder.getItineraries()) {
                Itinerary newItinerary = new Itinerary();
                PropertyUtils.copyProperties(newItinerary, itinerary);
                newItinerary.setCode(null);
                newItinerary.setOrder(order);
            }
            for (Passenger passenger : lastOrder.getPassengers()) {
                Passenger newPassenger = new Passenger();
                PropertyUtils.copyProperties(newPassenger, passenger);
                newPassenger.setCode(null);
                newPassenger.setOrder(order);
            }
            preparePayment(order.getPayment().getType(), order, new ArrayList<Itinerary>(order.getItineraries()), new ArrayList<Passenger>(order.getPassengers()), order.getPayment().getExtraInfo(),
                request, response, sess, tx, messages);
        } catch (Exception e) {
            if (null != tx) {
                tx.rollback();
            }
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
            logger.error(e.getMessage(), e);
        } finally {
            if (null != sess) {
                sess.clear();
                sess.close();
            }
        }

        // Report a success action
        saveMessages(request, messages);
        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    private void preparePayment(PaymentType pt, Order order, List<Itinerary> orderItineraries, List<Passenger> passengers, String extraInfo, HttpServletRequest request, HttpServletResponse response,
        Session sess, Transaction tx, ActionMessages messages) throws Exception {

        Class<?> clazz = Class.forName(pt.getExternalCode());
        Constructor<?> constr = clazz.getConstructor(new Class[] { Action.class });
        Object obj = constr.newInstance(new Object[] { this });
        PaymentIF paymentController = null;
        if (obj instanceof PaymentIF) {
            paymentController = (PaymentIF) obj;
            order.setPayment(paymentController.getPayment(order, pt, extraInfo));
            // crear nueva transaccion para la orden actual
            tx = sess.beginTransaction();
            OrderTransaction orderTransaction = new OrderTransaction(order.getStatus(), Calendar.getInstance());
            order.addOrderTransacion(orderTransaction);
            sess.saveOrUpdate(orderTransaction);
            sess.flush();
            order.setLastTransactionCode(orderTransaction.getCode());
            sess.saveOrUpdate(order);
            for (Itinerary itinerary : orderItineraries) {
                sess.saveOrUpdate(itinerary);
            }
            for (Passenger passenger : passengers) {
                sess.saveOrUpdate(passenger);
            }
            sess.flush();
            // Watch out - initPayment will set the payment within the order
            // without saving!!!
            String result = paymentController.initPayment(request, response, order);
            sess.saveOrUpdate(order);
            tx.commit();

            request.getSession().removeAttribute(CartConstants.CART);
            request.removeAttribute(CartConstants.CART_FLOW_STEP);
            request.setAttribute("cartContent", "cart.send");
            request.setAttribute("sendForm", result);
            messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
        } else {
            throw new InstantiationError("Invalid Controller class defined in PaymentType.");
        }
    }
}
