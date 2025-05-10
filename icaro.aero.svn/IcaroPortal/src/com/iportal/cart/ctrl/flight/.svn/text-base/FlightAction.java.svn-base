/*
 * Created Jul 28, 2006 SearchContainerAction.java
 */
package com.iportal.cart.ctrl.flight;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.iportal.biz.helper.AirAvailabilityHelper;
import com.iportal.biz.helper.BookAndPriceHelper;
import com.iportal.biz.helper.PowerShopperHelper;
import com.iportal.biz.helper.QueryHelper;
import com.iportal.biz.worldspan.AvailabilityResponse;
import com.iportal.biz.worldspan.BookAndPriceResponse;
import com.iportal.biz.worldspan.FlightInfo;
import com.iportal.biz.worldspan.FlightOptionBean;
import com.iportal.biz.worldspan.Leg;
import com.iportal.biz.worldspan.PSAlternateInfo;
import com.iportal.biz.worldspan.PaxTypeInfo;
import com.iportal.biz.worldspan.PowerShopperResponse;
import com.iportal.biz.worldspan.Segment;
import com.iportal.biz.worldspan.WorldspanError;
import com.iportal.biz.worldspan.WorldspanResponse;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.model.cart.Itinerary;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.OrderStatus;
import com.iportal.cart.model.cart.Passenger;
import com.iportal.cart.model.customer.BillInfo;
import com.iportal.cart.model.customer.IDType;
import com.iportal.cart.model.customer.OrderCustomerType;
import com.iportal.cart.model.tax.Tax;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.model.Language;
import com.iportal.model.icaro.Airport;
import com.iportal.model.icaro.fare.PaxType;
import com.iportal.model.icaro.fare.TicketRate;
import com.yage.Globals;

/**
 * Seccion que llama a consulta de disponibilidad de vuelos ADC1 y al primer paso del flujo
 *
 * @author hernan
 * @version 1.0
 *
 */
public class FlightAction extends ContentContainerAction {

    private static Log logger = LogFactory.getLog(FlightAction.class);

    public FlightAction() {
        super();
    }

    /**
     * Consulta todos los aeropuertos y carga en el contexto de la aplicacion ejecutandose unicamente una vez este
     * metodo por eso static synchronized
     *
     * @param sess
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked")
    private static synchronized Map<String, Airport> populateAirportsHash(Session sess, ServletContext context) {
        Map<String, Airport> airportHash = (Hashtable<String, Airport>) context.getAttribute(CartConstants.AIRPORT_HASH_KEY);
        if (airportHash == null) {
            airportHash = new Hashtable<String, Airport>();
            List<Airport> airports = (ArrayList<Airport>) sess.createQuery(" From Airport ").setCacheable(Globals.TRUE.booleanValue()).list();
            for (Airport air : airports) {
                airportHash.put(air.getIataCode(), air);
            }
        }
        context.setAttribute(CartConstants.AIRPORT_HASH_KEY, airportHash);
        return airportHash;
    }

    /**
     * Consulta todos los tipos de pasajeros disponibles y los coloca en el contexto de la aplicacion
     *
     * @param sess
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked")
    private static synchronized Map<String, PaxType> populatePaxTypeHash(Session sess, ServletContext context) {
        Map<String, PaxType> paxTypeHash = (Hashtable<String, PaxType>) context.getAttribute(CartConstants.PAX_TYPE_KEY);
        if (paxTypeHash == null) {
            paxTypeHash = new Hashtable<String, PaxType>();
            List<PaxType> results = (ArrayList<PaxType>) sess.createQuery(" From PaxType ").setCacheable(Globals.TRUE.booleanValue()).list();
            for (PaxType paxType : results) {
                paxTypeHash.put(paxType.getExternalCode(), paxType);
            }
        }
        context.setAttribute(CartConstants.PAX_TYPE_KEY, paxTypeHash);
        return paxTypeHash;
    }

    @SuppressWarnings("unchecked")
    protected PaxType getPaxTypeByExternalCode(String extCode) {
        ServletContext context = this.getServlet().getServletContext();
        Map<String, PaxType> map = (Hashtable<String, PaxType>) context.getAttribute(CartConstants.PAX_TYPE_KEY);
        if (map == null) {
            map = populatePaxTypeHash(this.getHibernateSession(), context);
        }
        return (PaxType) map.get(extCode);
    }

    /**
     * @param iataCode
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Airport getAirportByIataCode(String iataCode) {
        ServletContext context = this.getServlet().getServletContext();
        Map<String, Airport> airportHash = (Hashtable<String, Airport>) context.getAttribute(CartConstants.AIRPORT_HASH_KEY);
        if (airportHash == null) {
            Session sess = null;
            try {
                sess = this.getHibernateSession();
                airportHash = populateAirportsHash(sess, context);
            } finally {
                if (sess != null)
                    try {
                        sess.clear();
                        sess.close();
                    } catch (Exception e) {
                    }
            }

        }
        return (Airport) airportHash.get(iataCode);
    }

    public ActionForward searchFlight(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SearchFlightForm searchForm = (SearchFlightForm) form;
        HttpSession session = request.getSession();
        ActionMessages messages = new ActionMessages();
        PowerShopperResponse powerShop = null;
        AvailabilityResponse availibility = null;
        if (!this.isCancelled(request)) {

            try {
                UserBean userBean = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
                searchForm.setIsAvailibility(userBean != null && Constants.CLIENT_CORP_KEY.equals(userBean.getCustomerType()));

                if ((userBean != null && Constants.CLIENT_CORP_KEY.equals(userBean.getCustomerType()))) {
                    AirAvailabilityHelper helper = new AirAvailabilityHelper();
                    availibility = helper.queryAirAvailability(searchForm, Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE);

                    if (availibility == null || availibility.getErrorMessage() != null) {
                        throw new Exception("Availability Request failed");
                    }
                    boolean problemsWithLegs = false;
                    StringBuffer legStatus = new StringBuffer();
                    int count = 1;
                    for (Leg leg : availibility.getLegs()) {
                        legStatus.append("ESTADO LEG");
                        legStatus.append(count);
                        legStatus.append(": ");
                        legStatus.append(leg.getStatus());
                        if (leg.getPartialReasonCode() != null) {
                            legStatus.append(" REASON: ");
                            legStatus.append(leg.getPartialReasonCode());
                        }
                        legStatus.append(" FIN LEG ");
                        legStatus.append(count);

                        if (!"GOOD".equals(leg.getStatus())) {
                            problemsWithLegs = true;
                        }
                        count++;
                    }
                    if (!problemsWithLegs && availibility.getVariableCount() != null && availibility.getVariableCount() > 0) {

                        List<FlightOptionBean> departureOptions = new ArrayList<FlightOptionBean>(availibility.getVariableCount());
                        List<FlightOptionBean> arrivalOptions = new ArrayList<FlightOptionBean>(availibility.getVariableCount());
                        // armado de opciones de vuelo para salidas (departures)
                        FlightOptionBean option = null;
                        for (Segment seg : availibility.getSegments()) {
                            seg.setArrivalAirport(this.getAirportByIataCode(seg.getArrivalAirportCode()));
                            seg.setDepartureAirport(this.getAirportByIataCode(seg.getDepartureAirportCode()));

                            if (1 == seg.getLegNumber()) {
                                if ("S".equals(seg.getSegmentType())) {
                                    // si es segmento inicial es una opcion de
                                    // vuelo
                                    option = new FlightOptionBean();
                                    option.addSegment(seg);
                                    departureOptions.add(option);
                                } else if (option!= null) {
                                    option.addSegment(seg);
                                }
                            } else {
                                // segmento de regreso
                                if ("S".equals(seg.getSegmentType())) {
                                    option = new FlightOptionBean();
                                    option.addSegment(seg);
                                    arrivalOptions.add(option);
                                } else if (option!= null) {
                                    option.addSegment(seg);
                                }
                            }
                        }
                        availibility.setArrivalOptions(arrivalOptions);
                        availibility.setDepartureOptions(departureOptions);

                        session.setAttribute("availibility", availibility);
                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SELECTION);
                    } else {
                        // NO hay alternativas de vuelos
                        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.powerShop.noAlternatives"));
                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                        searchForm.setNextStep("cart.searchFlight");
                        if (problemsWithLegs) { // enviar al log informacion de
                            // legs
                            logger.info("Error en legs: " + legStatus.toString());
                        }
                    }
                } else {
                    PowerShopperHelper helper = new PowerShopperHelper();

                    powerShop = helper.queryPowerShopper(searchForm, Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE, (userBean != null ? userBean.getName() : "usuario_vacio"));

                    if (powerShop == null || powerShop.getErrorMessage() != null) {
                        throw new Exception("PowerShopper Request failed");
                    }
                    if (powerShop.getErrors() != null && powerShop.getErrors().size() > 0) {
                        throw new Exception("PowerShopper WorldspanError ocurred");
                    }
                    // consulta todas las tarifas web en la ruta buscada
                    // paxTypes es null a proposito
                    // es por otra opcion de solucion en la q solo se consulta
                    // los tipos de pasajeros solicitados
                    List<PaxTypeInfo> paxTypes = null;

                    List<TicketRate> fares = this.queryWebFares(searchForm, paxTypes);

                    if (powerShop.getAlternateCount() > 0) {
                        List<PSAlternateInfo> alternativeToRemove = new ArrayList<PSAlternateInfo>();
                        for (PSAlternateInfo alt : powerShop.getAlternatives()) {
                            paxTypes = alt.getPaxTypes();
//                            String previousFlightDay = null;
//                            int flightIndex = 0;
                            for (FlightInfo info : alt.getFlights()) {
                                if (alt.getFlights().size() > 2 || (alt.getFlights().size() > 1 && !searchForm.getTwoWay())) {
                                    // eliminar todas las alternativas con conexiones
                                    alternativeToRemove.add(alt);
                                    /* // es un viaje con conexiones debe controlarse que no se hagan al dia siguiente
                                     * // comprobar si en el vuelo de conexion se lo hace al dia siguiente
                                     * // si el indice de vuelo es impar(es una conexion) y el dia de vuelo anterior es distinto
                                     * if((flightIndex %2 == 1) && !previousFlightDay.equals(info.getFlightDate())) {
                                     *     //marcar esta alternativa para ser eliminada
                                     *     alternativeToRemove.add(alt);
                                     *     break;
                                     * }
                                     * //contar indices de vuelos solo en vuelos con conexiones
                                     * previousFlightDay = info.getFlightDate();
                                     * flightIndex ++;
                                     */
                                } else {
                                    if (info.getArrivalAirportCode() != null) {
                                        info.setArrivalAirport(this.getAirportByIataCode(info.getArrivalAirportCode()));
                                    }
                                    if (info.getDepartureAirportCode() != null) {
                                        info.setDepartureAirport(this.getAirportByIataCode(info.getDepartureAirportCode()));
                                    }
                                }
                            }

                            double totalBaseFare = 0.0D;
                            double totalIVAFare = 0.0D;
                            double totalTax1Fare = 0.0D;
                            double totalTax2Fare = 0.0D;
                            double totalTax3Fare = 0.0D;
                            double totalFare = 0.0D;
                            for (PaxTypeInfo paxT : alt.getPaxTypes()) {
                                if (paxT.getPaxTypeCode() != null) {
                                    PaxType paxType = this.getPaxTypeByExternalCode(paxT.getPaxTypeCode());
                                    paxT.setPaxType(paxType);
                                    // consultar temporalmente las tarifas web
                                    // en portal icaro
                                    // y guarda en objeto
                                    for (TicketRate fare : fares) {
                                        if (fare.getPaxType().getCode().equals(paxType.getCode())) {
                                            // sobreescribir tarifas
                                            // powershopper
                                            paxT.setBaseFare(searchForm.getTwoWay() ? fare.getRoundFare() : fare.getFare());
                                            paxT.setIvaRate(searchForm.getTwoWay() ? fare.getRoundIvaRate() : fare.getIvaRate());
                                            paxT.setTax1(searchForm.getTwoWay() ? fare.getRoundTax1() : fare.getTax1());
                                            paxT.setTax2(searchForm.getTwoWay() ? fare.getRoundTax2() : fare.getTax2());
                                            paxT.setTax3(searchForm.getTwoWay() ? fare.getRoundTax3() : fare.getTax3());
                                            paxT.setTotalFare(searchForm.getTwoWay() ? fare.getTotalRoundFare() : fare.getTotalFare());

                                            totalBaseFare += paxT.getBaseFare() * paxT.getNumberOfPax();
                                            totalFare += paxT.getTotalFare() * paxT.getNumberOfPax();
                                            totalIVAFare += paxT.getIvaRate() * paxT.getNumberOfPax();
                                            totalTax1Fare += paxT.getTax1() * paxT.getNumberOfPax();
                                            totalTax2Fare += paxT.getTax2() * paxT.getNumberOfPax();
                                            totalTax3Fare += paxT.getTax3() != null ? paxT.getTax3() * paxT.getNumberOfPax() : 0.0D;
                                            break;
                                        }
                                    }
                                }
                            }
                            paxTypes = alt.getPaxTypes();
                            // TODO TEMPORAL HASTA Q POWERSHOOPER ARREGLE PROBLEMA
                            alt.setPowerTotalFare(alt.getTotalFare());
                            alt.setBaseFare(totalBaseFare);
                            alt.setTotalFare(totalFare);
                            alt.setTaxFare(totalIVAFare + totalTax1Fare + totalTax2Fare + totalTax3Fare);
                        }

                        // eliminar alternativas con conexiones al dia siguiente
                        if (alternativeToRemove.size() > 0) {
                            for (PSAlternateInfo alt : alternativeToRemove) {
                                powerShop.getAlternatives().remove(alt);
                            }
                        }

                        searchForm.setNextStep("cart.powerSelection");

                        session.setAttribute("powerShopOptions", powerShop);

                        // Consulta por informacion de tarifas web - arreglo temporal
                        // fares = this.queryWebFares (searchForm, paxTypes);

                        // coloca en session informacion de tarifas web
                        // session.setAttribute("webFares", fares);
                        // Pasa al paso de seleccion de vuelos
                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SELECTION);
                    } else {
                        // NO hay alternativas de vuelos
                        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.powerShop.noAlternatives"));
                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                        searchForm.setNextStep("cart.searchFlight");
                    }
                }
            } catch (Exception e) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                searchForm.setNextStep("cart.searchFlight");
                logger.error(e.getMessage(), e);

                if (powerShop != null && powerShop.getErrors() != null && powerShop.getErrors().size() > 0) {
                    for (WorldspanError wsError : powerShop.getErrors()) {
                        logger.error(wsError.getNumber() + " " + wsError.getMessage());
                    }
                }
                WorldspanResponse resp = powerShop != null ? powerShop : availibility != null ? availibility : null;
                if (resp != null && resp.getErrorMessage() != null) {
                    logger.error(resp.getErrorMessage());
                }
            }
        } else {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
            searchForm.setNextStep("cart.searchFlight");
        }

        request.setAttribute("cartContent", searchForm.getNextStep());

        if (!messages.isEmpty()) {
            // Regresa al paso de busqueda
            session.removeAttribute("availibility");
            saveMessages(request, messages);
            return mapping.getInputForward();
        }

        // Report a success action
        if (!this.isCancelled(request)) {
            messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
            saveMessages(request, messages);
        }

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    /**
     * query for fares for every paxType on the specified route.
     *
     * @return List of web fares. One per paxType.
     */
    @SuppressWarnings("unchecked")
    public List<TicketRate> queryWebFares(SearchFlightForm searchForm, List<PaxTypeInfo> paxTypes) {
        List<TicketRate> results = null;
        Session sess = null;
        try {
            Airport departure = this.getAirportByIataCode(searchForm.getFromCityCode());
            Airport arrival = this.getAirportByIataCode(searchForm.getToCityCode());
            // List<Long> paxCodes = new ArrayList<Long> ();
            // for (PaxTypeInfo paxT : paxTypes ) {
            // paxCodes.add(paxT.getPaxType().getCode());
            // }

            sess = this.getHibernateSession();
            Query query = sess.createQuery(" from TicketRate rate where rate.route.departure.code = :deptCode and rate.route.arrival.code = :arrvCode  ");
            // and rate.paxType in (:paxCodes)
            query.setLong("deptCode", departure.getCode());
            query.setLong("arrvCode", arrival.getCode());
            // query.setParameterList("paxCodes", paxCodes.toArray(),
            // Hibernate.LONG);
            results = query.list();
            // setea indicador de tarifa a mostrar de acuerdo a si es o no roundTrip
//            if (searchForm.getTwoWay()){
//                for (TicketRate tr:results) {
//                    tr.setRoundTrip(searchForm.getTwoWay());
//                }
//            }
        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }
        return results;

    }

    public ActionForward selectFlight(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SearchFlightForm searchForm = (SearchFlightForm) form;
        HttpSession session = request.getSession();
        ActionMessages messages = new ActionMessages();
        UserBean userBean = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
        searchForm.setIsAvailibility(userBean != null && Constants.CLIENT_CORP_KEY.equals(userBean.getCustomerType()));
        if (searchForm.getIsAvailibility()) {
            // usuario corporativo
            AvailabilityResponse availibility = (AvailabilityResponse) session.getAttribute("availibility");
            if (availibility == null || "cart.searchFlight".equals(searchForm.getNextStep()) || this.isCancelled(request)) {
                if (availibility == null) {
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.cart.lostAvailibility"));
                }
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                // debe regresar al paso inicial de la busqueda
                searchForm.setNextStep("cart.searchFlight");
            } else {

                try {
                    // seleccionar vuelos y pasara a ingreso de pasajeros
                    availibility.setSelectedArrivalOptionIndex(searchForm.getArrivalOptionIndex());
                    availibility.setSelectedDepartureOptionIndex(searchForm.getDepartureOptionIndex());

                    // Pasa al paso de registro de pasajeros
                    request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);

                } catch (Exception e) {
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
                    request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SELECTION);
                    searchForm.setNextStep("cart.selection");
                    logger.error(e.getMessage(), e);

                }
            }
        } else {
            // Seleccion de vuelo a partir de PowerShopper
            PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");

            if (powerShop == null || "cart.searchFlight".equals(searchForm.getNextStep()) || this.isCancelled(request)) {
                if (powerShop == null) {
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.cart.lostPowerShopInfo"));
                }
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                // debe regresar al paso inicial de la busqueda
                searchForm.setNextStep("cart.searchFlight");
            } else {

                try {
                    // seleccionar vuelos y pasara a ingreso de pasajeros
                    powerShop.setSelectedAlternativeOptionIndex(searchForm.getAlternativeIndex());

                    // Pasa al paso de registro de pasajeros solo si el usuario
                    // ya esta logeado
                    if (userBean != null) {
                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
                    } else {
                        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
                        searchForm.setNextStep("cart.registerLogin");
                    }
                } catch (Exception e) {
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
                    request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SELECTION);
                    searchForm.setNextStep("cart.powerSelection");
                    logger.error(e.getMessage(), e);
                }
            }
        }

        // se va un paso atras o un paso adelante dependiendo del boton
        // presionado
        request.setAttribute("cartContent", searchForm.getNextStep());

        if (!messages.isEmpty()) {
            // Regresa al paso de busqueda en caso de error o si se dio clic en
            // el boton de retroceder
            saveMessages(request, messages);
            return mapping.getInputForward();
        }

        if (!this.isCancelled(request)) {
            // Report a success action
            messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
            saveMessages(request, messages);
        }

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    public ActionForward checkPowerSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SearchFlightForm searchForm = (SearchFlightForm) form;

        PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");

        UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);

        if (powerShop == null) {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
            // debe regresar al paso inicial de la busqueda
            searchForm.setNextStep("cart.searchFlight");
        } else if (this.isCancelled(request)) {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SELECTION);
        } else if (icaroUser == null) {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
            searchForm.setNextStep("cart.registerLogin");
        } else {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);
        }

        // se va un paso atras o un paso adelante dependiendo del boton
        // presionado
        request.setAttribute("cartContent", searchForm.getNextStep());

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    /**
     * Accion que registra datos de pasajeros en orden y datos de factura Asi como itinerario seleccionado.
     *
     * NOTA IMPORTANTE: en caso de uqe se registren datos del vuelo a partir de Power Shopper esta funcion encuentra el
     * cambio de pata cuando reconoce que la ciudad de destino de la peticion y cuando coincide esta ciudad con la
     * ciudad destino del vuelo (segemento) cambia el indicado de patas para la siguiente. Para esto se asume que la
     * informacion en selected.getFlights()) esta ordenada. Con esta solucion solo se arregla para vuelos de hasta 2
     * patas (ida y vuelta) NO SIRVE PARA VUELOS DE MAS DE 2 PATAS
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward registerPassenger(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SearchFlightForm searchForm = (SearchFlightForm) form;
        HttpSession session = request.getSession();
        ActionMessages messages = new ActionMessages();
        AvailabilityResponse availibility = (AvailabilityResponse) session.getAttribute("availibility");
        PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");

        UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);

        boolean isAvailibility = icaroUser != null && Constants.CLIENT_CORP_KEY.equals(icaroUser.getCustomerType());
        if ((powerShop == null && !isAvailibility) || (isAvailibility && availibility == null) || "cart.searchFlight".equals(searchForm.getNextStep()) || this.isCancelled(request)) {
            if ((powerShop == null && !isAvailibility) || (isAvailibility && availibility == null) || "cart.searchFlight".equals(searchForm.getNextStep())) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.cart.lostAvailibility"));
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                // debe regresar al paso inicial de la busqueda
                searchForm.setNextStep("cart.searchFlight");
            } else if (!isAvailibility) {
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
            } else {
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SELECTION);
                searchForm.setNextStep("cart.selection");
                // debe regresar al paso inicial de la busqueda
            }
        } else {
            Order order = null;
            Session sess = null;
            try {
                // Crea la orden y guarda datos de factura
                order = new Order();
                order.setEnabled(Globals.TRUE);
                sess = this.getHibernateSession();

                if (isAvailibility) { // consulta de disponibilidad

                    order.setSale(Globals.FALSE);
                    order.setLegCount(availibility.getLegCount());

                    // Arma itinerarios
                    Itinerary it = null;

                    // Itinerario de para el tramo de ida
                    for (Segment seg : availibility.getSelectedDepartureOption().getSegments()) {
                        it = this.createAndLoadItinerary(seg, sess);
                        order.addItinerary(it);
                        it.setGoingLeg(Globals.TRUE);
                    }

                    if (order.getLegCount() > 1) { // solo si existe mas de una
                        // leg
                        // Itinerario de para el tramo de regreso
                        for (Segment seg : availibility.getSelectedArrivalOption().getSegments()) {
                            it = this.createAndLoadItinerary(seg, sess);
                            order.addItinerary(it);
                            it.setGoingLeg(Globals.FALSE);
                        }
                    }

                } else if(powerShop != null) {
                    // FIXME colocar lo seleccionado en passengers asi como el
                    // tipo de pago
                    order.setSale(Globals.FALSE);
                    PSAlternateInfo selected = powerShop.getSelectedAlternativeOption();

                    order.setLegCount(searchForm.getTwoWay() ? 2 : 1);
                    // esto estaba mal
                    /*
                     * if (selected.getTrips()!= null) { order.setLegCount(selected.getTrips().size()); } else {
                     * order.setLegCount(searchForm.getTwoWay()?2:1); }
                     */

                    int leg = 1;
                    for (FlightInfo info : selected.getFlights()) {
                        // identificar el LEG al que pertenece
                        Itinerary it = new Itinerary();
                        it.setCode(null);
                        it.setAirlineCode(info.getAirlineCode());
                        it.setAirplane(info.getAircraftType());
                        it.setFlightNumber(info.getFlightNumber());
                        it.setArrivalDateTime(info.getArrivalDateTime());
                        it.setArrivalIataCode(info.getArrivalAirportCode());
                        it.setArrivalAirport(info.getArrivalAirport());
                        it.setDepartureIataCode(info.getDepartureAirportCode());
                        it.setDepartureDateTime(info.getDepartureDateTime());
                        it.setDepartureAirport(info.getDepartureAirport());
                        it.setClassOfService(info.getFareClass());
                        it.setFlightInfo(info);
                        it.setLegNumber(leg);
                        /*
                         * si se ha llegado a la ciudad de destino se cambia el contador de LEGS esto solo sirve para
                         * encontrar si se esta en la pata de salida o de regreso Para esto se asume que la informacion
                         * en selected.getFlights()) esta ordenada. Esto es una solución simple. Con esta solucion solo
                         * se arregla para vuelos de hasta 2 patas (ida y vuelta) NO SIRVE PARA VUELOS DE MAS DE 2 PATAS
                         * SI SE MANAJAN VUELOS CON MAS DE DOS PATAS ESTO DEBE CAMBIARSE TODO mejorar este algoritmo
                         * para identificar patas de vuelos con mas opciones que ida y round trip
                         */
                        if (info.getArrivalAirportCode().equals(searchForm.getToCityCode())) {
                            leg++;
                        }
                        order.addItinerary(it);
                    }

                }

                BillInfo billInfo = new BillInfo();
                billInfo.setName(searchForm.getBillName());
                billInfo.setPhone(searchForm.getBillPhone());
                order.setBillInfo(billInfo);
                // searchForm.getBillName()
                // PASAR AL PASO CORRESPONDIENTE
                /*
                 * BillInfo billInfo = new BillInfo ();
                 * billInfo.setName(searchForm.getBillName());
                 * billInfo.setIdentity(searchForm.getBillId());
                 * billInfo.setAddress(searchForm.getBillAddress());
                 * billInfo.setPhone(searchForm.getBillPhone());
                 * order.setBillInfo(billInfo);
                 */

                Passenger passenger = null;
                PaxType paxType = null;
                IDType idType = null;
                // Guarda Pasajeros
                for (int i = 0; i < searchForm.getFirstName().length; i++) {
                    passenger = new Passenger();
                    passenger.setCode(null);

                    paxType = (PaxType) sess.get(PaxType.class, searchForm.getPaxTypeCode()[i]);
                    passenger.setPaxType(paxType);
                    if (searchForm.getIdTypeCode()[i] != null && searchForm.getIdTypeCode()[i] > 0L) {
                        idType = (IDType) sess.get(IDType.class, searchForm.getIdTypeCode()[i]);
                        passenger.setIdType(idType);
                    }
                    if (searchForm.getBirthYear()[i] != null && searchForm.getBirthYear()[i] > 0 && searchForm.getBirthMonth()[i] != null && searchForm.getBirthMonth()[i] > 0
                        && searchForm.getBirthDay()[i] != null && searchForm.getBirthDay()[i] > 0) {
                        Calendar birthdate = Calendar.getInstance();
                        birthdate.set(Calendar.YEAR, searchForm.getBirthYear()[i]);
                        birthdate.set(Calendar.MONTH, searchForm.getBirthMonth()[i].intValue() - 1); // searchForm.getBirthYear()[i]);
                        birthdate.set(Calendar.DATE, searchForm.getBirthDay()[i]);
                        passenger.setBirthdate(birthdate.getTime());
                    }
                    searchForm.getFirstName()[i] = StringUtils.replace(searchForm.getFirstName()[i], " ", ".");
                    searchForm.getLastName()[i] = StringUtils.replace(searchForm.getLastName()[i], " ", ".");
                    passenger.setEmail(searchForm.getEmail()[i]);
                    passenger.setFirstName(searchForm.getFirstName()[i]);
                    passenger.setFrequenTravelNumber(searchForm.getFrequentTravelNumber()[i]);
                    passenger.setIdentity(searchForm.getIdentity()[i]);
                    passenger.setLastName(searchForm.getLastName()[i]);
                    passenger.setMobile(searchForm.getMobile()[i]);
                    passenger.setNationality(searchForm.getNationality()[i]);
                    passenger.setPhone(searchForm.getPhone()[i]);
                    passenger.setRequireWheelchair(Globals.FALSE);
                    if (searchForm.getRequireWheelchair() != null) {
                        for (int j = 0; j < searchForm.getRequireWheelchair().length; j++) {
                            if (i == searchForm.getRequireWheelchair()[j].intValue()) {
                                passenger.setRequireWheelchair(Globals.TRUE);
                                break;
                            }
                        }
                    }
                    order.addPassengers(passenger);
                }
                session.setAttribute("order", order);

                // Pasa al paso de registro de pasajeros
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SUMMARY);

            } catch (Exception e) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);
                searchForm.setNextStep("cart.passenger");
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
        }

        // se va un paso atras o un paso adelante dependiendo del boton
        // presionado
        request.setAttribute("cartContent", searchForm.getNextStep());

        if (!messages.isEmpty()) {
            // Regresa al paso de busqueda en caso de error o si se dio clic en
            // el boton de retroceder
            saveMessages(request, messages);
            return mapping.getInputForward();
        }

        if (!this.isCancelled(request)) {
            // Report a success action
            messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
            saveMessages(request, messages);
        }

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    private Itinerary createAndLoadItinerary(Segment seg, Session sess) {
        Itinerary it = new Itinerary();
        it.setCode(null);
        it.setAirlineCode(seg.getAirlineCode());
        it.setAirplane(seg.getEquipmentCode());
        it.setFlightNumber(seg.getFlightNumber());
        it.setArrivalDateTime(seg.getArrivalDateTime());
        it.setArrivalIataCode(seg.getArrivalAirportCode());
        it.setArrivalAirport(QueryHelper.airportByIataCode(sess, seg.getArrivalAirportCode()));
        it.setDepartureIataCode(seg.getDepartureAirportCode());
        it.setDepartureDateTime(seg.getDepartureDateTime());
        it.setDepartureAirport(QueryHelper.airportByIataCode(sess, seg.getDepartureAirportCode()));
        it.setLegNumber(seg.getLegNumber());
        it.setClassOfService(seg.getClassService());
        it.setSegment(seg);
        return it;

    }

    public ActionForward book(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SearchFlightForm searchForm = (SearchFlightForm) form;
        HttpSession session = request.getSession();
        ActionMessages messages = new ActionMessages();
        BookAndPriceResponse bookResponse = null;
        Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
        UserBean userBean = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
        Order order = (Order) session.getAttribute("order");
        String errorMessage = "BPW9 is Null";
        Session sess = null;
        Transaction tx = null;

        // FIXME: Hernan, por que dice false? + aclara con parentesis los || y &&
        if (null == order || null == userBean /*&& false*/ || this.isCancelled(request) || "cart.searchFlight".equals(searchForm.getNextStep())) {
            if (this.isCancelled(request)) {
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PASSENGER);
            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.cart.lostInfo"));
                request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
                // debe regresar al paso inicial de la busqueda
                searchForm.setNextStep("cart.searchFlight");

            }
        } else {
            BookAndPriceHelper helper = new BookAndPriceHelper();
            PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");
            PSAlternateInfo selected = (powerShop != null) ? powerShop.getSelectedAlternativeOption() : null;
            sess = this.getHibernateSession();
            tx = sess.beginTransaction();

            try {
                if (!order.getSale()) { // reservar solamente
                    // TODO quitar esto cuando PowerShopper de bien los precios
                    bookResponse = helper.queryBookAndPrice(order, userBean, "CA", (null != selected) ? selected.getPowerTotalFare() : null);
                    // TODO poner esto cuando poweshopper se arregle
//                    bookResponse = helper.queryBookAndPrice(order, userBean, "CA", (null != selected) ? selected.getTotalFare() : null);
                } else {// reservar compra efectuada
                    // TODO quitar esto cuando PowerShopper de bien los precios
                    bookResponse = helper.queryBookAndPrice(order, userBean, "CA", (selected != null) ? selected.getPowerTotalFare() : null);
                    // TODO poner esto cuando poweshopper se arregle
//                    bookResponse = helper.queryBookAndPrice(order, userBean, "CA", (selected != null) ? selected.getTotalFare() : null);
                }
                if (null == bookResponse || null != bookResponse.getErrorMessage() || null == bookResponse.getRecordLocator() || bookResponse.getRecordLocator().length() < 1) {
                    if (null != bookResponse && bookResponse.getErrorMessage() != null) {
                        errorMessage = bookResponse.getErrorMessage();
                    } else if (null == bookResponse || null == bookResponse.getRecordLocator() || bookResponse.getRecordLocator().length() < 1) {
                        errorMessage = BaseHelper.getApplicationBundleMessage("message.bookFailed.noRecordLocator", language.getLocale());
                    }
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.bookFailed", errorMessage));
                    searchForm.setNextStep("cart.result");
                } else {// Reservacion Exitosa
                    // setear el numero de recorlocator en la reservación
                    order.setRecordLocator(bookResponse.getRecordLocator());
                    // setear precios definitivos o con los que se va a cobrar
                    if (bookResponse.getPricingInfo() != null) {
                        double quotient = Math.pow(10, bookResponse.getPricingInfo().getDecimalPositions());
                        order.setBeforeDiscount(bookResponse.getPricingInfo().getBaseFareAllPax() / quotient);
                        order.setTotalAmount(bookResponse.getPricingInfo().getTotalFareAllPax() / quotient);
                        // bookResponse.getPricingInfo().getPaxTypes()

                        for (Passenger p : order.getPassengers()) {
                            for (PaxTypeInfo pt : bookResponse.getPricingInfo().getPaxTypes()) {
                                if (pt.getPaxTypeCode().equals(p.getPaxType().getExternalCode())) {
                                    p.setFareAmount(pt.getBaseFare() / quotient);
                                    p.setTotalAmount(pt.getTotalFare() / quotient);
                                    break;
                                }
                            }
                        }
                    } else if (null != selected) {
                        order.setBeforeDiscount(selected.getBaseFare());
                        order.setTotalAmount(selected.getTotalFare());
                        for (Passenger p : order.getPassengers()) {
                            for (PaxTypeInfo pt : selected.getPaxTypes()) {
                                if (pt.getPaxTypeCode().equals(p.getPaxType().getExternalCode())) {
                                    p.setFareAmount(pt.getBaseFare());
                                    p.setTotalAmount(pt.getTotalFare());
                                    break;
                                }
                            }
                        }
                    }
                    order.setDiscount(0D);

                    order.setCode(null);
                    order.setCreation(Calendar.getInstance());
                    order.setLastStatus(order.getCreation());
                    OrderStatus status = (OrderStatus) sess.get(OrderStatus.class, CartConstants.CART_RESERVATION_SUCCESS_STATUS);
                    order.setStatus(status);
                    order.setCustomer(userBean.getCustomer());
                    order.setCustomerReference(userBean.getName());

                    Tax ivaTax = (Tax) sess.load(Tax.class, Constants.IVA_TAX_CODE);
                    double ivaRate = 0.0D;
                    if(ivaTax != null) {
                        ivaRate = ivaTax.getCurrentRate();
                        order.setIvaPercentage(ivaTax.getCurrentRate());
                    } else {
                        order.setIvaPercentage(Double.valueOf(0.0D));
                    }
                    order.setIvaAmount(ivaRate * order.getBeforeDiscount());
                    order.setLanguageCode(language.getCode());

                    if (userBean.getCustomerType() != null) {
                        OrderCustomerType custType = (OrderCustomerType) sess.load(OrderCustomerType.class, userBean.getCustomerType());
                        order.setCustomerType(custType);
                    }
                    sess.saveOrUpdate(order);

                    for (Passenger p : order.getPassengers()) {
                        p.setIvaAmount(p.getFareAmount() * ivaRate);
                        sess.saveOrUpdate(p);
                    }
                    for (Itinerary it : order.getItineraries()) {
                        sess.saveOrUpdate(it);
                    }
                    tx.commit();
                }
                request.setAttribute("bookResponse", bookResponse);
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                    tx = null;
                }
                if (bookResponse != null && bookResponse.getErrorMessage() != null) {
                    errorMessage = bookResponse.getErrorMessage();
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.bookFailed", errorMessage));
                    searchForm.setNextStep("cart.result");
                } else {
                    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.bookFailed.unexpected"));
                    searchForm.setNextStep("cart.searchFlight");
                }
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
        }

        // se va un paso atras o un paso adelante dependiendo del boton
        // presionado
        request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_RESULT);
        request.setAttribute("cartContent", searchForm.getNextStep());

        if (!messages.isEmpty()) {
            // Regresa al paso de busqueda en caso de error o si se dio clic en
            // el boton de retroceder
            saveMessages(request, messages);
            // Setear indicadores de fracaso en la reserva:
            request.setAttribute("titleStep", BaseHelper.getMessageBundleMessage("label.bookFailed", language.getLocale()));

            return mapping.getInputForward();
        }

        // Report a success action
        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.bookSuccess"));
        request.setAttribute("titleStep", BaseHelper.getMessageBundleMessage("label.bookSuccess", language.getLocale()));
        request.setAttribute("bookSuccess", Globals.TRUE);
        saveMessages(request, messages);

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    public ActionForward preparePayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SearchFlightForm searchForm = (SearchFlightForm) form;

        PowerShopperResponse powerShop = (PowerShopperResponse) session.getAttribute("powerShopOptions");
        AvailabilityResponse availibility = (AvailabilityResponse) session.getAttribute("availibility");
        Order order = (Order) session.getAttribute("order");
        UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);

        if (null == order || null == powerShop || this.isCancelled(request) || null != availibility) {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_SEARCH);
            // debe regresar al paso inicial de la busqueda
            searchForm.setNextStep("cart.searchFlight");

            // Eliminar todo de sesion
            session.removeAttribute("powerShopOptions");
            session.removeAttribute("availibility");
            session.removeAttribute("order");
            session.removeAttribute("searchFlightForm");

            // comprueba si esta usando disponibilidad
            if (null != availibility) {
                ActionMessages messages = new ActionMessages();
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.accessDenied.corpUsers"));
                saveMessages(request, messages);
            }

        } else if (null == icaroUser && null != powerShop) {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_LOGIN);
            searchForm.setNextStep("cart.registerLogin");
        } else {
            request.setAttribute(CartConstants.CART_FLOW_STEP, CartConstants.CART_FLOW_PAYMENT);
            searchForm.setNextStep("cart.payment");
        }

        // se va un paso atras o un paso adelante dependiendo del boton
        // presionado
        request.setAttribute("cartContent", searchForm.getNextStep());

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

}
