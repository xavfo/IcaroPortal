/**
 *
 */
package com.iportal.cart.ctrl.flight;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.worldspan.PaxTypeInfo;
import com.iportal.cart.CartConstants;
import com.iportal.model.Language;
import com.iportal.model.icaro.Airport;
import com.iportal.model.icaro.fare.TicketRate;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * Forma de busqueda de productos
 *
 * @author fernandoT
 * @version 1.0
 *
 */
public class SearchFlightForm extends BaseForm {

    private static final long serialVersionUID = -2692723428772701896L;

    private String fromCityCode;

    private String toCityCode;

    private Boolean twoWay;

    private Integer passengerAdult;

    private Integer passengerChild;

    private Integer passengerOld;

    private Integer passengerInfant;

    private Integer passengerHandicapped;

    private String dateFrom;

    private String timeFrom;

    private String dateTo;

    private String timeTo;

    private String nextStep;

    private Integer alternativeIndex;

    private Integer departureOptionIndex;

    private Integer arrivalOptionIndex;

    // PASSENGER DATA
    private Long[] idTypeCode;

    private Long[] paxTypeCode;

    private String[] firstName;

    private String[] lastName;

    private Integer[] requireWheelchair;

    private String[] nationality;

    private String[] identity;

    private Integer[] birthYear;

    private Integer[] birthMonth;

    private Integer[] birthDay;

    private String[] phone;

    private String[] mobile;

    private String[] email;

    private String[] frequentTravelNumber;

    private String billName;

    private String billId;

    private String billAddress;

    private String billPhone;

    private Boolean acceptTerms;

    private Boolean sale;

    private Boolean isAvailibility;

    private Long paymentTypeCode;

    private Long orderCode;

    public SearchFlightForm() {
        super();
        this.clear();
    }

    public void clear() {
        this.fromCityCode = null;
        this.toCityCode = null;
        this.twoWay = Globals.TRUE;
        this.sale = Globals.FALSE;
        this.isAvailibility = Globals.FALSE;
        this.passengerAdult = 0;
        this.passengerChild = 0;
        this.passengerOld = 0;
        this.passengerInfant = 0;
        this.passengerHandicapped = 0;
        this.dateFrom = null;
        this.timeFrom = null;
        this.dateTo = null;
        this.timeTo = null;
        this.nextStep = null;

        this.departureOptionIndex = null;
        this.arrivalOptionIndex = null;
        this.alternativeIndex = null;

        this.billName = null;

        this.billId = null;

        this.billAddress = null;

        this.billPhone = null;

        this.acceptTerms = Globals.FALSE;
        this.paymentTypeCode = null;
        this.orderCode = null;

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

    protected Airport getAirportByIataCode(String iataCode) {
        ServletContext context = this.getServlet().getServletContext();
        Map<String, Airport> airportHash = (Hashtable<String, Airport>) context.getAttribute(CartConstants.AIRPORT_HASH_KEY);
        if (airportHash == null) {
            Session sess = null;
            try {
                sess = BaseHelper.getHibernateSession();
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

    /**
     * query for fares for every paxType on the specified route.
     *
     * @return List of web fares. One per paxType.
     */
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

            sess = BaseHelper.getHibernateSession();
            Query query = sess.createQuery(" from TicketRate rate where rate.route.departure.code = :deptCode and rate.route.arrival.code = :arrvCode  ");
            // and rate.paxType in (:paxCodes)
            query.setLong("deptCode", departure.getCode());
            query.setLong("arrvCode", arrival.getCode());
            // query.setParameterList("paxCodes", paxCodes.toArray(),
            // Hibernate.LONG);
            results = query.list();
            // setea indicador de tarifa a mostrar de acuerdo a si es o no
            // roundTrip
            /*
             * if (searchForm.getTwoWay()){ for (TicketRate tr:results) { tr.setRoundTrip(searchForm.getTwoWay()); } }
             */
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

    public Integer getTotalPassengers() {
        int resp = this.passengerAdult + passengerChild + passengerInfant + passengerOld + passengerHandicapped;
        return new Integer(resp);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors errors = null;
        if (this.orderCode != null && this.orderCode > 0L) {
            // do not validate
            return new ActionErrors();
        }
        if (this.page != 11) {
            errors = super.validate(mapping, request);
        } else {
            errors = new ActionErrors();
        }
        Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        Locale locale = null;
        if (language != null) {
            locale = language.getLocale();
        } else {
            locale = new Locale("es");
        }
        if (errors.isEmpty()) {
            switch (this.page) {
            case 0:
                Calendar earlyBook = Calendar.getInstance();
                earlyBook.set(Calendar.HOUR_OF_DAY, 0);
                earlyBook.set(Calendar.MINUTE, 0);
                earlyBook.set(Calendar.SECOND, 0);
                earlyBook.set(Calendar.MILLISECOND, 0);
                earlyBook.getTime();
                earlyBook.add(Calendar.DATE, Constants.FLIGHT_SEARCH_BOOK_DELAY_DAYS);
                earlyBook.add(Calendar.MINUTE, -1);
                earlyBook.getTime();

                if (this.getFrom().before(earlyBook)) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.greaterDate", BaseHelper.getApplicationBundleMessage("prompt.departureDate", locale), DateFormatUtils.format(
                        earlyBook.getTime(), Constants.DATE_TIME_FORMAT)));
                }
                if (this.getTwoWay() && this.getTo().before(this.getFrom())) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.greaterDate", BaseHelper.getApplicationBundleMessage("prompt.arrivalDate", locale), BaseHelper
                        .getApplicationBundleMessage("prompt.departureDate")));
                }
                if (this.getTotalPassengers() < 1) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.searchFligh.minPassengers"));
                }
                if (this.getPassengerChild() != null && this.getPassengerChild().longValue() > 0L && !(this.getTotalPassengers() > this.getPassengerChild())) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.searchFligh.noChildWithotherPassengers"));
                }
                // validar que solo se escojan vuelos web
                List<TicketRate> fares = this.queryWebFares(this, null);
                if (fares == null || fares.isEmpty()) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.powerShop.noAlternatives"));
                }
                break;
            case 2:
                // Validar que las fechas sean obligatorias porque no puede hacerse
                // desde el framework validator porque no son strings

                int adultCurrentIndex = 0;
                int childCurrentIndex = 0;
                int seniorCurrentIndex = 0;
                int handicappedCurrentIndex = 0;
                int infantCurrentIndex = 0;
                int currentPaxIndex = 0;
                String currentPaxTypeName = null;
                boolean birthDateNotComplete = false;
                boolean passengerOver18Exists = false;
                Integer currentPaxMinAge = null;
                Integer currentPaxMaxAge = null;
                for (int i = 0; i < this.getPaxTypeCode().length; i++) {
                    birthDateNotComplete = false;
                    currentPaxMinAge = null;
                    currentPaxMaxAge = null;
                    // FIXME Esto deberia estar registrado en la tabla Tipos de pasajero pero no hay
                    // presupuesto para hacerlo asi por lo que queda como un mejora a futuro
                    switch (this.getPaxTypeCode()[i].intValue()) {
                    case 1: // if ( Constants.PAX_TYPE_ADULT.intValue():
                        adultCurrentIndex++;
                        currentPaxIndex = new Integer(adultCurrentIndex);
                        currentPaxTypeName = BaseHelper.getMessageBundleMessage("label.passenger.adult", locale);
                        currentPaxMinAge = Constants.PAX_ADULT_MIN_AGE;
                        currentPaxMaxAge = Constants.PAX_ADULT_MAX_AGE;
                        break;
                    case 2:// Constants.PAX_TYPE_CHILD.intValue():
                        childCurrentIndex++;
                        currentPaxIndex = new Integer(childCurrentIndex);
                        currentPaxTypeName = BaseHelper.getMessageBundleMessage("label.passenger.child", locale);
                        currentPaxMinAge = Constants.PAX_CHILD_MIN_AGE;
                        currentPaxMaxAge = Constants.PAX_CHILD_MAX_AGE;
                        break;
                    case 4: // Constants.PAX_TYPE_SENIOR.intValue():
                        seniorCurrentIndex++;
                        currentPaxIndex = new Integer(seniorCurrentIndex);
                        currentPaxTypeName = BaseHelper.getMessageBundleMessage("label.passenger.old", locale);
                        currentPaxMinAge = Constants.PAX_SENIOR_MIN_AGE;
                        break;
                    case 5: // Constants.PAX_TYPE_HANDICAPPED.intValue():
                        handicappedCurrentIndex++;
                        currentPaxIndex = new Integer(handicappedCurrentIndex);
                        currentPaxTypeName = BaseHelper.getMessageBundleMessage("label.passenger.handicapped", locale);
                        break;
                    case 3: // Constants.PAX_TYPE_INFANT.intValue():
                        infantCurrentIndex++;
                        currentPaxIndex = new Integer(infantCurrentIndex);
                        currentPaxTypeName = BaseHelper.getMessageBundleMessage("label.passenger.infant", locale);
                        break;
                    }
                    if (this.getBirthYear()[i] == null || this.getBirthYear()[i].intValue() < 1) {
                        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required.propertyList", BaseHelper.getApplicationBundleMessage("prompt.birthYear", locale),
                            currentPaxTypeName, currentPaxIndex));
                        birthDateNotComplete = true;
                    }
                    if (this.getBirthMonth()[i] == null || this.getBirthMonth()[i].intValue() < 1) {
                        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required.propertyList", BaseHelper.getApplicationBundleMessage("prompt.birthMonth", locale),
                            currentPaxTypeName, currentPaxIndex));
                        birthDateNotComplete = true;
                    }
                    if (this.getBirthDay()[i] == null || this.getBirthDay()[i].intValue() < 1) {
                        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required.propertyList", BaseHelper.getApplicationBundleMessage("prompt.birthDay", locale),
                            currentPaxTypeName, currentPaxIndex));
                        birthDateNotComplete = true;
                    }
                    if (!birthDateNotComplete && (currentPaxMaxAge != null || currentPaxMinAge != null)) {
                        // calcula que la edad sea la correspondiente del tipo actual de pasajero
                        Calendar birthdate = Calendar.getInstance();
                        birthdate.set(Calendar.YEAR, this.getBirthYear()[i]);
                        birthdate.set(Calendar.MONTH, this.getBirthMonth()[i].intValue() - 1); // searchForm.getBirthYear()[i]);
                        birthdate.set(Calendar.DATE, this.getBirthDay()[i]);

                        Calendar age = Calendar.getInstance();
                        age.setTimeInMillis(Math.abs(birthdate.getTimeInMillis() - System.currentTimeMillis()));

                        int years = age.get(Calendar.YEAR) - 1970;
                        // int months = age.get(Calendar.MONTH);

                        if (currentPaxMaxAge != null && currentPaxMinAge != null) {
                            // controlar que fecha este en el rango de acuerdo al tipo
                            if (currentPaxMinAge.intValue() > years || years >= currentPaxMaxAge) {
                                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.passenger.ageRange", currentPaxTypeName, currentPaxIndex, currentPaxMinAge, currentPaxMaxAge));
                            }
                        } else if (currentPaxMinAge != null) {
                            // controlar que fecha nacimiento sea mayor a la mínima
                            if (currentPaxMinAge.intValue() > years) {
                                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.passenger.minAge", currentPaxTypeName, currentPaxIndex, currentPaxMinAge));
                            }
                        } else {// controlar que fecha nacimiento sea menor que la máxima (infantes algun día)
                            if (years >= currentPaxMaxAge) {
                                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.passenger.maxAge", currentPaxTypeName, currentPaxIndex, currentPaxMaxAge));
                            }
                        }
                        if (years > 18 && Constants.PAX_TYPE_CHILD.intValue() != this.getPaxTypeCode()[i].intValue()) {
                            passengerOver18Exists = true;
                        }
                    }
                }
                if (this.passengerChild != null && this.passengerChild > 0 && !passengerOver18Exists) {
                    // controlar que existe ni un pasajero ademas del ninio mayor a 18 anios
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.searchFligh.noChildWithotherPassengers"));
                }
                break;
            case 11:
                if (this.departureOptionIndex == null || this.departureOptionIndex.longValue() < 0) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.departureSegment", locale)));
                }
                if (this.twoWay && (this.arrivalOptionIndex == null || this.arrivalOptionIndex.longValue() < 0)) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.arrivalSegment", locale)));
                }
                break;
            }
        }
        // if flow gos to inputForward, o r if there is an error
        if (!errors.isEmpty()) {
            if (request.getAttribute(CartConstants.CART_FLOW_STEP) == null) {
                if (request.getParameter(CartConstants.CART_FLOW_STEP) != null) {
                    request.setAttribute(CartConstants.CART_FLOW_STEP, Long.valueOf(request.getParameter(CartConstants.CART_FLOW_STEP)));
                }
            }
            if (request.getAttribute("cartContent") == null) {
                if (request.getParameter("cartContent") != null) {
                    request.setAttribute("cartContent", request.getParameter("cartContent"));
                }
            }
        }

        return errors;
    }

    public Long getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(Long paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public Boolean getIsAvailibility() {
        return isAvailibility;
    }

    public void setIsAvailibility(Boolean isAvailibility) {
        this.isAvailibility = isAvailibility;
    }

    /**
     * @return Returns the fromCityCode.
     */
    public String getFromCityCode() {
        return fromCityCode;
    }

    /**
     * @param fromCityCode
     *            The fromCityCode to set.
     */
    public void setFromCityCode(String fromCityCode) {
        this.fromCityCode = fromCityCode;
    }

    public Boolean getSale() {
        return sale;
    }

    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    /**
     * @return Returns the toCityCode.
     */
    public String getToCityCode() {
        return toCityCode;
    }

    /**
     * @param toCityCode
     *            The toCityCode to set.
     */
    public void setToCityCode(String toCityCode) {
        this.toCityCode = toCityCode;
    }

    /**
     * @return Returns the twoWay.
     */
    public Boolean getTwoWay() {
        return twoWay;
    }

    /**
     * @param twoWay
     *            The twoWay to set.
     */
    public void setTwoWay(Boolean twoWay) {
        this.twoWay = twoWay;
    }

    /**
     * @return Returns the passengerAdult.
     */
    public Integer getPassengerAdult() {
        return passengerAdult;
    }

    /**
     * @param passengerAdult
     *            The passengerAdult to set.
     */
    public void setPassengerAdult(Integer passengerAdult) {
        this.passengerAdult = passengerAdult;
    }

    /**
     * @return Returns the passengerChild.
     */
    public Integer getPassengerChild() {
        return passengerChild;
    }

    /**
     * @param passengerChild
     *            The passengerChild to set.
     */
    public void setPassengerChild(Integer passengerChild) {
        this.passengerChild = passengerChild;
    }

    /**
     * @return Returns the passengerInfant.
     */
    public Integer getPassengerInfant() {
        return passengerInfant;
    }

    /**
     * @param passengerInfant
     *            The passengerInfant to set.
     */
    public void setPassengerInfant(Integer passengerInfant) {
        this.passengerInfant = passengerInfant;
    }

    public Integer getPassengerHandicapped() {
        return passengerHandicapped;
    }

    public void setPassengerHandicapped(Integer passengerHandicapped) {
        this.passengerHandicapped = passengerHandicapped;
    }

    /**
     * @return Returns the passengerOld.
     */
    public Integer getPassengerOld() {
        return passengerOld;
    }

    /**
     * @param passengerOld
     *            The passengerOld to set.
     */
    public void setPassengerOld(Integer passengerOld) {
        this.passengerOld = passengerOld;
    }

    public Calendar getFrom() {
        Calendar calendar = new GregorianCalendar();

        if (this.getDateFrom() != null && this.getDateFrom().length() > 0) {
            calendar.setTime(DateFormatUtils.parseToDate(this.getDateFrom(), Constants.DATE_FORMAT));
            if (this.getTimeFrom() != null) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(this.getTimeFrom().substring(0, 2)));
                calendar.set(Calendar.MINUTE, Integer.valueOf(this.getTimeFrom().substring(2)));
                calendar.getTime();
            }
            return calendar;
        }
        return null;

    }

    public void setFrom(Calendar calendar) {
        if (calendar != null)
            this.dateFrom = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }

    /**
     * @return Returns the dateFrom.
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom
     *            The dateFrom to set.
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Calendar getTo() {
        Calendar calendar = new GregorianCalendar();

        if (this.getDateTo() != null && this.getDateTo().length() > 0) {
            calendar.setTime(DateFormatUtils.parseToDate(this.getDateTo(), Constants.DATE_FORMAT));

            if (this.getTimeTo() != null) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(this.getTimeTo().substring(0, 2)));
                calendar.set(Calendar.MINUTE, Integer.valueOf(this.getTimeTo().substring(2)));
                calendar.getTime();
            }

            return calendar;
        }
        return null;

    }

    public void setTo(Calendar calendar) {
        if (calendar != null)
            this.dateTo = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }

    /**
     * @return Returns the dateTo.
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo
     *            The dateTo to set.
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Calendar getFromTime() {
        Calendar calendar = new GregorianCalendar();

        if (this.getTimeFrom() != null && this.getTimeFrom().length() > 0) {
            calendar.setTime(DateFormatUtils.parseToDate(this.getTimeFrom(), Constants.TIME_FORMAT));
            return calendar;
        }
        return null;

    }

    /**
     * @return Returns the timeFrom.
     */
    public String getTimeFrom() {
        return timeFrom;
    }

    /**
     * @param timeFrom
     *            The timeFrom to set.
     */
    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Calendar getToTime() {
        Calendar calendar = new GregorianCalendar();

        if (this.getTimeTo() != null && this.getTimeTo().length() > 0) {
            calendar.setTime(DateFormatUtils.parseToDate(this.getTimeTo(), Constants.TIME_FORMAT));
            return calendar;
        }
        return null;

    }

    /**
     * @return Returns the timeTo.
     */
    public String getTimeTo() {
        return timeTo;
    }

    public Integer getAlternativeIndex() {
        return alternativeIndex;
    }

    public Integer getArrivalOptionIndex() {
        return arrivalOptionIndex;
    }

    public Integer getDepartureOptionIndex() {
        return departureOptionIndex;
    }

    /**
     * @param timeTo
     *            The timeTo to set.
     */
    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public void setAlternativeIndex(Integer alternativeIndex) {
        this.alternativeIndex = alternativeIndex;
    }

    public void setArrivalOptionIndex(Integer arrivalSegment) {
        this.arrivalOptionIndex = arrivalSegment;
    }

    public void setDepartureOptionIndex(Integer departureSegment) {
        this.departureOptionIndex = departureSegment;
    }

    public Integer[] getBirthDay() {
        return birthDay;
    }

    public Integer[] getBirthMonth() {
        return birthMonth;
    }

    public Integer[] getBirthYear() {
        return birthYear;
    }

    public String[] getEmail() {
        return email;
    }

    public String[] getFirstName() {
        return firstName;
    }

    public String[] getFrequentTravelNumber() {
        return frequentTravelNumber;
    }

    public String[] getIdentity() {
        return identity;
    }

    public Long[] getIdTypeCode() {
        return idTypeCode;
    }

    public String[] getLastName() {
        return lastName;
    }

    public String[] getMobile() {
        return mobile;
    }

    public String[] getNationality() {
        return nationality;
    }

    public Long[] getPaxTypeCode() {
        return paxTypeCode;
    }

    public String[] getPhone() {
        return phone;
    }

    public Integer[] getRequireWheelchair() {
        return requireWheelchair;
    }

    public void setBirthDay(Integer[] birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthMonth(Integer[] birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(Integer[] birthYear) {
        this.birthYear = birthYear;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public void setFirstName(String[] firstName) {
        this.firstName = firstName;
    }

    public void setFrequentTravelNumber(String[] frequenTravelNumber) {
        this.frequentTravelNumber = frequenTravelNumber;
    }

    public void setIdentity(String[] identity) {
        this.identity = identity;
    }

    public void setIdTypeCode(Long[] idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

    public void setLastName(String[] lastName) {
        this.lastName = lastName;
    }

    public void setMobile(String[] mobile) {
        this.mobile = mobile;
    }

    public void setNationality(String[] nationality) {
        this.nationality = nationality;
    }

    public void setPaxTypeCode(Long[] paxTypeCode) {
        this.paxTypeCode = paxTypeCode;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

    public void setRequireWheelchair(Integer[] requireWheelchair) {
        this.requireWheelchair = requireWheelchair;
    }

    public Boolean getAcceptTerms() {
        return acceptTerms;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public String getBillId() {
        return billId;
    }

    public String getBillName() {
        return billName;
    }

    public String getBillPhone() {
        return billPhone;
    }

    public void setAcceptTerms(Boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public void setBillPhone(String billPhone) {
        this.billPhone = billPhone;
    }

    /**
     * @return the orderCode
     */
    public Long getOrderCode() {
        return orderCode;
    }

    /**
     * @param orderCode
     *            the orderCode to set
     */
    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

}
