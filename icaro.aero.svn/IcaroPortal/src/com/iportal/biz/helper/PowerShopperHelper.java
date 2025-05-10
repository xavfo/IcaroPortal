/**
 *
 */
package com.iportal.biz.helper;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.worldspan.FlightInfo;
import com.iportal.biz.worldspan.PSAlternateInfo;
import com.iportal.biz.worldspan.PaxTypeDetailInfo;
import com.iportal.biz.worldspan.PaxTypeInfo;
import com.iportal.biz.worldspan.PowerShopperResponse;
import com.iportal.biz.worldspan.TripInfo;
import com.iportal.biz.worldspan.WorldspanError;
import com.iportal.cart.ctrl.flight.SearchFlightForm;
import com.iportal.model.icaro.fare.PaxType;
import com.yage.commons.DateFormatUtils;

/**
 * @author hernan
 *
 */
public class PowerShopperHelper extends WorldspanHelper {

    protected static Log logger = LogFactory.getLog(PowerShopperHelper.class);

    List<PaxType> paxTypes;

    public PowerShopperHelper() {
        super();
        this.clear();
    }



    @Override
    public void clear() {

        super.clear();

        if (paxTypes != null) {
            paxTypes.clear();
        }
        paxTypes = null;
    }

    @SuppressWarnings("unchecked")
    private void initPaxTypeList () {
        Session sess = null;
        try {
            sess = getHibernateSession();
            this.paxTypes = (ArrayList<PaxType>) sess.createQuery(" From PaxType").setCacheable(true).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch (Exception e) {
                    sess = null;
                }
            }
        }
    }



    public List<PaxType> getPaxTypes() {
        return paxTypes;
    }



    public void setPaxTypes(List<PaxType> paxTypes) {
        this.paxTypes = paxTypes;
    }



    /**
     * Configura al objeto Digester para respuesta de BPC9 Book and Price  para leer las respuestas de la consulta
     * XML a worlspan.
     * Para mas información de como usar digester ver tutorial: http://www.devguru.com/features/tutorials/jakarta/jakarta.html
     * y la documentacion oficial en http://jakarta.apache.org/commons/digester.
     *
     */
    @Override
    protected void initMessageDigester() {

        this.digester.addObjectCreate( "PSW5", PowerShopperResponse.class);
        this.digester.addSetProperties("PSW5","trackingid", "trackingid");
        this.digester.addBeanPropertySetter( "PSW5/CUS_INF", "customerInfo" );

        this.digester.addObjectCreate( "PSW5/ERR", WorldspanError.class );
        this.digester.addBeanPropertySetter( "PSW5/ERR/NUM", "number" );
        this.digester.addBeanPropertySetter( "PSW5/ERR/MSG_TXT", "message" );
        this.digester.addSetNext( "PSW5/ERR", "addError" );

        this.digester.addBeanPropertySetter("PSW5/ADV_MSG", "advisoryMessage");
        this.digester.addBeanPropertySetter("PSW5/ALT_COU", "alternateCount");

        this.digester.addObjectCreate( "PSW5/ALT_INF", PSAlternateInfo.class );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/COM_INF", "comment" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/BAS_FAR", "baseFare" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TAX", "taxFare" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TTL_FAR", "totalFare" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/SRC_NUM", "contractNumber" );//Secure Contract Number
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/ISO_CUR_COD", "currencyCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FAR_SEL", "fareType" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/ACC_COD", "accountCode" );

        this.digester.addObjectCreate( "PSW5/ALT_INF/PTC_FAR_INF", PaxTypeInfo.class );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/PTC_FAR_INF/PTC", "paxTypeCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/PTC_FAR_INF/NUM_PAX", "numberOfPax" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/PTC_FAR_INF/CUR_BAS", "baseFareCurrencyCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/PTC_FAR_INF/BAS_FAR", "baseFare" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/PTC_FAR_INF/CUR_TTL", "totalFareCurrencyCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/PTC_FAR_INF/TTL_FAR", "totalFare" );
        this.digester.addSetNext( "PSW5/ALT_INF/PTC_FAR_INF", "addPaxTypeInfo" );

        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/LAS_TIC_DAT", "lastTicketDateInfo" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TIC_ADV", "ticketAdvisory" );

        this.digester.addObjectCreate( "PSW5/ALT_INF/FLI_INF", FlightInfo.class );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/LNK_IND", "linkIndicator" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/ARL_COD", "airlineCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/FLI_NUM", "flightNumber" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/FAR_CLA", "fareClass" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/FLI_DAT", "flightDate" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/FLI_DAY", "flightDay" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/DEP_ARP", "departureAirportCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/ARR_ARP", "arrivalAirportCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/DEP_TIM", "departureTime" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/ARR_TIM", "arrivalTime" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/DAY_CHG_IND", "dayChangeIndicator" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/DEP_ARR_DAT_DIF", "depArrvDateDifference" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/EQP_TYP", "aircraftType" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/MEA_TYP", "mealType" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/NUM_STO", "numberOfStops" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/E_TIC_ELI", "eTicketEligible" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/LNK_RES_IND", "linkResponseIndicator" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/COD_SHA_INF", "codeShareInfo" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/FLI_INF/PRI_SEG_NUM", "pricingSegmentNumber" );
        this.digester.addSetNext( "PSW5/ALT_INF/FLI_INF", "addFlight" );

        this.digester.addObjectCreate( "PSW5/ALT_INF/TRI_INF", TripInfo.class );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TRI_INF/MKT_PUL/ORI", "origin" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TRI_INF/MKT_PUL/DES", "destination" );

        this.digester.addObjectCreate( "PSW5/ALT_INF/TRI_INF/ARP_COD", String.class );
        this.digester.addSetNext("PSW5/ALT_INF/TRI_INF/ARP_COD", "addAirportCode");

        this.digester.addObjectCreate( "PSW5/ALT_INF/TRI_INF/PTC_DTL_INF", PaxTypeDetailInfo.class );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TRI_INF/PTC_DTL_INF/PTC", "paxTypeCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TRI_INF/PTC_DTL_INF/ARL_COD", "airlineCarrierCode" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TRI_INF/PTC_DTL_INF/FAR_RST", "fareRestriction" );
        this.digester.addBeanPropertySetter( "PSW5/ALT_INF/TRI_INF/PTC_DTL_INF/FAR_BAS_COD", "fareBasisCode" );
        this.digester.addSetNext( "PSW5/ALT_INF/TRI_INF/PTC_DTL_INF", "addPaxTypeDetailInfo" );

        this.digester.addSetNext( "PSW5/ALT_INF/TRI_INF", "addTripInfo" );

        this.digester.addSetNext( "PSW5/ALT_INF", "addAlternateInfo" );
    }


    private String getPaxInfoRequest (Integer number, Long paxTypeCode) throws Exception {
        if (this.paxTypes == null) {
            this.initPaxTypeList();
        }
        PaxType type = null;
        for (PaxType it: this.paxTypes) {
            if (paxTypeCode.equals(it.getCode())) {
                type = it;
                break;
            }
        }
        if (type == null) {
            throw new Exception ("paxType with code "+paxTypeCode+ " does not exists");
        }
        StringBuffer request = new StringBuffer ();
        request.append("<PTC_INF>");
        request.append("<NUM_PAX>");
        request.append(number);
        request.append("</NUM_PAX>");
        request.append("<PTC>");
        request.append(type.getExternalCode());
        request.append("</PTC>");
        request.append("</PTC_INF>");
        return request.toString();
    }

    public String getPowerShopperRequest (SearchFlightForm flightForm, String airlineCode, String customerRef  ) throws Exception {
        StringBuffer request = new StringBuffer ();
        request.append("<PSC5>");
        request.append("<MSG_VERSION>9</MSG_VERSION>");
        request.append("<CUS_INF>");
        request.append(customerRef);
        request.append("</CUS_INF>");
        /*
         * Esto se envia para obtener tarifas de vuelos de ida y regreso sola via
         * */
        if (flightForm.getTwoWay()){
            request.append("<OPT>F</OPT>");
        } /*else {
            request.append("<OPT>C</OPT>");
        }*/

        request.append("<POI_ORI>");
        request.append("<CIT>");
        request.append(flightForm.getFromCityCode());
        request.append("</CIT>");
        request.append("</POI_ORI>");
        if (flightForm.getTwoWay()){
            String returnDateStr = DateFormatUtils.format( flightForm.getTo().getTime(), "ddMMM", Locale.ENGLISH);
            request.append("<REG_RET_DAT>");
            request.append(returnDateStr.toUpperCase());
            request.append("</REG_RET_DAT>");
        }
        request.append("<NUM_ALT>");
        request.append(Constants.WORLDSPAN_DEFAULT_ALTERNATIVES_NUM);
        request.append("</NUM_ALT>");

        request.append("<ARL_INF>");
        request.append("<ARL_OPT>B</ARL_OPT>");
        request.append("<ARL_COD>");
        request.append(airlineCode);
        request.append("</ARL_COD>");
        request.append("</ARL_INF>");

        if (flightForm.getPassengerAdult() != null &&  flightForm.getPassengerAdult() > 0) {
            request.append(this.getPaxInfoRequest(flightForm.getPassengerAdult(), Constants.PAX_TYPE_ADULT));
        }
        if (flightForm.getPassengerChild() != null &&  flightForm.getPassengerChild() > 0) {
            request.append(this.getPaxInfoRequest(flightForm.getPassengerChild(), Constants.PAX_TYPE_CHILD));
        }
        if (flightForm.getPassengerHandicapped() != null &&  flightForm.getPassengerHandicapped() > 0) {
            request.append(this.getPaxInfoRequest(flightForm.getPassengerHandicapped(), Constants.PAX_TYPE_HANDICAPPED));
        }
        if (flightForm.getPassengerInfant() != null &&  flightForm.getPassengerInfant() > 0) {
            request.append(this.getPaxInfoRequest(flightForm.getPassengerInfant(), Constants.PAX_TYPE_INFANT));
        }
        if (flightForm.getPassengerOld() != null &&  flightForm.getPassengerOld() > 0) {
            request.append(this.getPaxInfoRequest(flightForm.getPassengerOld(), Constants.PAX_TYPE_SENIOR));
        }
        String departureDateStr = DateFormatUtils.format( flightForm.getFrom().getTime(), "ddMMM", Locale.ENGLISH);
        request.append("<DES_INF>");
        request.append("<DEP_DAT>");
        request.append( departureDateStr.toUpperCase());
        request.append("</DEP_DAT>");
        request.append("<INC_CLA>");
        //request.append("<CAB_CLA>W</CAB_CLA>");
        // Solo tarifas tipe "V"
        request.append("<CAB_CLA>V</CAB_CLA>");
        request.append("</INC_CLA>");
        request.append("<DEP_TIM>");
        request.append(flightForm.getTimeFrom());
        request.append("</DEP_TIM>");
        /*request.append("<CAB_CLA>");
            request.append("F");
        request.append("</CAB_CLA>");
        if (flightForm.getTwoWay()){
            request.append("<ARR_TIM >");
            request.append(flightForm.getTimeTo());
            request.append("</ARR_TIM >");

        }*/
        request.append("<POI_DES>");
        request.append("<CIT>");
        request.append(flightForm.getToCityCode());
        request.append("</CIT>");
        request.append("</POI_DES>");
        request.append("</DES_INF>");
        request.append("</PSC5>");
        return request.toString();
    }

    public PowerShopperResponse queryPowerShopper (SearchFlightForm flightForm, String airlineCode, String customerRef ) throws Exception {
        PowerShopperResponse resp = null;
        this.initDigester (PowerShopperResponse.class);

        String query = this.getPowerShopperRequest(flightForm, airlineCode, customerRef );
        logger.debug("query: "+query);
        String response = doXMLRequest (query);
        logger.debug("response: "+response);
        if (response != null) {
            StringReader reader = new StringReader (response);
            resp = (PowerShopperResponse) this.digester.parse(reader);
        }
        return resp;
    }
}
