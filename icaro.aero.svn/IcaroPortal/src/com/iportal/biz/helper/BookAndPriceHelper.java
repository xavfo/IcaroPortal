/**
 * 
 */
package com.iportal.biz.helper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import com.iportal.Constants;
import com.iportal.biz.worldspan.BookAndPriceResponse;
import com.iportal.biz.worldspan.BookSegment;
import com.iportal.biz.worldspan.PaxTypeInfo;
import com.iportal.biz.worldspan.PricingInfo;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.model.cart.Itinerary;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.Passenger;
import com.yage.commons.DateFormatUtils;
import com.yage.commons.StringBean;

/**
 * @author hernan
 *
 */
public class BookAndPriceHelper extends WorldspanHelper {

	protected static Log logger = LogFactory.getLog(BookAndPriceHelper.class);
	
	
	public BookAndPriceHelper() {
		super();
		this.clear();
	}
	
	/**
	 * Configura al objeto Digester para respuesta de BPC9 Book and Price  para leer las respuestas de la consulta
	 * XML a worlspan.
	 * Para mas informaciÃ³n de como usar digester ver tutorial: http://www.devguru.com/features/tutorials/jakarta/jakarta.html
	 * y la documentacion oficial en http://jakarta.apache.org/commons/digester.
	 * 
	 */
	@Override
	protected void initMessageDigester() {
		
		//this.adwDigester.addSetProperties("ADW1");
		this.digester.addObjectCreate( "BPW9", BookAndPriceResponse.class);
		this.digester.addBeanPropertySetter( "BPW9/MSG_VERSION", "messageVersion" );
		this.digester.addBeanPropertySetter( "BPW9/VARIABLE_COUNT", "variableCount" );
		this.digester.addBeanPropertySetter( "BPW9/END_OPTION", "endOption" );	
		this.digester.addBeanPropertySetter( "BPW9/PNR_RLOC", "recordLocator" );
		this.digester.addBeanPropertySetter( "BPW9/CUST_REF_NUM", "customerReferenceNumber" );
		this.digester.addBeanPropertySetter( "BPW9/PASSPORT_CONTACT_REQ", "passportContactRequired" );
		// Mensajes no parseados
		//QUEUE_STATUS_INFO 
		//	QUEUE_STATUS Occurs 4 times, one for each request found in BPC. Valid Values: â€œYâ€�=queuing was successful, â€œNâ€�=queuing failed 
		this.digester.addObjectCreate      ( "BPW9/SSR_GENERIC_STATUS_INFO/SSR_GENERIC_STATUS", StringBean.class );
		this.digester.addBeanPropertySetter( "BPW9/SSR_GENERIC_STATUS_INFO/SSR_GENERIC_STATUS", "value" );
		this.digester.addSetNext           ( "BPW9/SSR_GENERIC_STATUS_INFO/SSR_GENERIC_STATUS", "addSSRStatus");
		this.digester.addBeanPropertySetter( "BPW9/TIME_PRICE_DIF", "timePriceDifference" );
		/* No implementados:
			CC_APPROVAL_CODE 
			CC_FAILED 
			CARD_CODE 
			CAVV_RSP 
		*/
		this.digester.addObjectCreate( "BPW9/PRICING_INFO", PricingInfo.class );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/TOKEN", "token" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/MSG_LEN", "messageLength" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/CURRENCY", "currency" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/BASEFARE_ALLPAX", "baseFareAllPax" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/TOTALFARE_ALLPAX", "totalFareAllPax" );
		 
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/SRC_NUM_2", "secondFareType" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/SRC_NUM_3", "thirdFareType" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/DEC_POS", "decimalPositions" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/NUM_TRIPS", "numberTrips" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/NUM_PTCS", "numberPaxTypes" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/SRC_NUM", "securateContractNumber" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/LAST_TKT_DATE", "lastTicketDateStr" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/TK_ADVISORY", "ticketAdvisory" );
		
		this.digester.addObjectCreate( "BPW9/PRICING_INFO/PTC", PaxTypeInfo.class );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/PTC/PTC_CODE", "paxTypeCode" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/PTC/PTC_BASEFARE", "baseFare" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/PTC/PTC_TOTALFARE", "totalFare" );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/PTC/PTC_NUMPAX", "numberOfPax" ); //Number of passengers for first PTC type
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/PTC/PTC_ETKT", "etickectReserved" );//Reserved for E-ticket indicator (future use)
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO/PTC/PTC_RESERVED", "reserved" );
		this.digester.addSetNext( "BPW9/PRICING_INFO/PTC", "addPaxTypeInfo" );
		this.digester.addSetNext( "BPW9/PRICING_INFO", "setPricingInfo" ); 
		
		//Valid Values: ttttdddd; tttt=hours: 00-23 and minutes: 01-59; dddd=month: 01-12 and day: 01-31
		this.digester.addBeanPropertySetter( "BPW9/TICKET_TIME_LIMIT", "ticketTimeLimit" );
		this.digester.addBeanPropertySetter( "BPW9/TICKETLESS_IND", "ticketlessIndicator" );
		this.digester.addBeanPropertySetter( "BPW9/CONSOLIDATOR_IND", "invalidConsolidator " );
		
		
		this.digester.addObjectCreate( "BPW9/PRICING_INFO_SCREEN/PRICING_INFO_LINE", StringBean.class );
		this.digester.addBeanPropertySetter( "BPW9/PRICING_INFO_SCREEN/PRICING_INFO_LINE", "value" );
		this.digester.addSetNext("BPW9/PRICING_INFO_SCREEN/PRICING_INFO_LINE", "addPricingInfoLine");

		
		this.digester.addObjectCreate( "BPW9/SEGMENT_INFO", BookSegment.class );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/SEGMENT_INDICATOR", "indicator" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/MARRIED_SEG_NUM", "marriedSegmentNumber" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/WGRP_LOCATOR", "worldGroupLocator" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/AIRLINE_CODE", "airlineCode" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/FLIGHT_NUM", "flightNumber" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/DEP_CLASS", "departureBookingClass" );
		
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/DEP_DATE/DEP_DAY", "departureDay" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/DEP_DATE/DEP_MONTH", "departureMonth" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/DEP_TIME/DEP_HOUR", "departureHour" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/DEP_TIME/DEP_MIN", "departureMinute" );
		
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/DEP_AIRPORT", "departureAirport" );

		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/ARRIVAL_DATE/ARRIV_DAY", "arrivalDay" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/ARRIVAL_DATE/ARRIV_MONTH", "arrivalMonth" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/ARRIVAL_TIME/ARRIV_HOUR", "arrivalHour" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/ARRIVAL_TIME/ARRIV_MIN", "arrivalMinute" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/ARRIV_AIRPORT", "arrivalAirport" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/E_TICKETING", "eticketElegible" );

		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/SEAT_PREF_STATUS", "arrivalMinute" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/SELL_INDICATOR", "arrivalAirport" );
		this.digester.addBeanPropertySetter( "BPW9/SEGMENT_INFO/ACTUAL_STATUS", "actualStatus" );

		

		this.digester.addSetNext("BPW9/SEGMENT_INFO", "addSegment");
		
		

		  
	 //Esto no voy a traducir  
	 /*
	  * TRIPS Occurs 0 to 99 times depending on NUM_TRIPS
	    	TRIP_NUM Trip number
	     	NUM_CITIES Number of cities - variable number 02-10
	     	CITY Cities in the fare component number of three characters cities is variable. Occurs 2 to 10 times depending on NUM_CITIES
	     	MKT_FAREPULL Market fare pull in the direction of travel
	     	MKT_CARRIER Market carrier
	     	PTCREF PTCREF
	     		PHASE_IND Value:
						P = Private/SecuRate Phase 3 fare used and fare is refundable
						X = No Private/SecuRate Phase 3 fare used and fare is non-refundable
						N = No Private/SecuRate Phase 3 fare used and reservation can not be changed
						B = No Private/SecuRate Phase 3 fare used, fare is non-refundable and reservation can not be changed
						no sent = No Private/SecuRate Phase 3 fare used and/or Category 16 not applicable, fare is refundable
				FAREBASIS_PTC
	   	PRICING_INFO_SCREEN	-PRICING_INFO_SCREEN
	   		PRICING_INFO_LINE -PRICING_INFO_LINE
	   	WF_ERRORS World File copy soft error indicator; Valid Values: 0-9 = number of G*CC commands issued; Valid Values: â€œ0â€�-â€œ9â€�
			*/ 
	}
	

	
	public String getBookAndPriceRequest (Order order, String customerReferenceCode, String formOfPayment, Double verifyAmount, Boolean isFromAvailibility  ) {
		StringBuffer request = new StringBuffer ();
		

		List<Itinerary> itineraries = order.getItineraries();
		List<Passenger> passengers = order.getPassengers();
		request.append("<BPC9>");
		request.append("<MSG_VERSION>9</MSG_VERSION>");
		request.append("<VARIABLE_COUNT>");
		request.append(itineraries.size());//numero de segmentos
		request.append("</VARIABLE_COUNT>");
		request.append("<SEATS>");
		request.append(passengers.size());//INDICA CUANTOS PASAJES SE VAN A RESERVAR
		request.append("</SEATS>");
		
		int i = 1;
		for (Passenger ps : passengers) {
			request.append("<PASSENGER_DATA>");
			request.append("<NAME_POSITION>");
			request.append(i);
			request.append("</NAME_POSITION>");
			request.append("<LAST_FIRST_MIDDLE>");
			request.append(ps.getFullName("/"));//indica el separador entre apellido y nombre
			request.append("</LAST_FIRST_MIDDLE>");
			request.append("<TYPE>");
			request.append(ps.getPaxType().getExternalCode());
			request.append("</TYPE>");
			if (ps.getBirthdate() != null) {
				request.append("<AGE>");
				request.append(ps.getWordspanAge());
				request.append("</AGE>");
				request.append("<DOB>");
				request.append(DateFormatUtils.format( ps.getBirthdate(), "yyMMdd"));
				request.append("</DOB>");
			}
			if ("INF".equals(ps.getPaxType().getExternalCode())) {
				//vuela en todos los segmentos
				request.append("<CUSTOM_NAME_DATA>00</CUSTOM_NAME_DATA>");
			}
			request.append("</PASSENGER_DATA>");
			i++;
		}
		request.append("<PNR_DATA>");//PASSANGER NAME RECORD
		request.append("<PHONE_FIELDS>");
		request.append("<PHONE_FIELD>");
		if(order.getBillInfo() != null &&  order.getBillInfo().getPhone() != null) {
			request.append(order.getBillInfo().getPhone());	
		} else {
			request.append(order.getPassengers().get(0).getPhone());
		}
		
		request.append("</PHONE_FIELD>");
		request.append("</PHONE_FIELDS>");
		
		request.append("<RECEIVED_FROM>");
		request.append(Constants.WORLDSPAN_DEFAULT_AGENCY_CODE);
		request.append("</RECEIVED_FROM>");
		request.append("<CUST_REF_NUM>");
		request.append(customerReferenceCode);
		request.append("</CUST_REF_NUM>");
		request.append("<FOP>");
		request.append(formOfPayment);
		request.append("</FOP>");
		int ssrCount = 0;
		i = 1;
		StringBuffer chairRequestFor = new StringBuffer ();
		
		for (Passenger ps : passengers) {
			if (ps.getFrequenTravelNumber() != null && ps.getFrequenTravelNumber().length() >0) {
				request.append("<SSR_GENERIC>");
					request.append("<SSR_CODE>FQTV</SSR_CODE>");
					request.append("<SSR_AIRLINE>");
					request.append(Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE);
					request.append("</SSR_AIRLINE>");
					request.append("<SSR_FREEFORM>");
					request.append("HK/X8");
					request.append(ps.getFrequenTravelNumber());
					request.append("-");
					request.append(i);
					request.append("</SSR_FREEFORM>");
				request.append("</SSR_GENERIC>");
				ssrCount++;
			}
			
			if (ps.getRequireWheelchair() != null && ps.getRequireWheelchair()) {
				if (chairRequestFor.length() > 0) {
					chairRequestFor.append("/");
				}
				chairRequestFor.append(i);
				ssrCount++;
			}
			i++;
		}
		
		// SSR para silla de ruedas
		if (chairRequestFor.length() > 0) {
			request.append("<SSR_GENERIC>");
				request.append("<SSR_CODE>SHRT</SSR_CODE>");
				request.append("<SSR_AIRLINE>");
				request.append(Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE);
				request.append("</SSR_AIRLINE>");
				request.append("<SSR_FREEFORM>");
				request.append("3SAN");
				if (chairRequestFor.length() > 1){
					request.append(chairRequestFor.toString());	
				} else {
					request.append("1.");
					request.append(chairRequestFor.toString());
				}
				
				request.append("WCHR");
				request.append("</SSR_FREEFORM>");
			request.append("</SSR_GENERIC>"); 

		}
		//
		request.append("</PNR_DATA>");
		request.append("<END_OPTION>E</END_OPTION>"); //OPCIONES DE PROCESO, EN ESTE CASO E: TERMINAR
		request.append("<PRICING_DISPLAY_OPTION>1</PRICING_DISPLAY_OPTION>");
		request.append("<PRICING_PS_LOC>FORCE:B</PRICING_PS_LOC>");   
		//request.append("<PRICING_COMMAND>4PQ</PRICING_COMMAND>");
		request.append("<PRICING_COMMAND>4P*</PRICING_COMMAND>");
		//request.append("<PRICING_COMMAND>4PLFB</PRICING_COMMAND>");
		//request.append("<SECURATE_NUMBER>4PFSR</SECURATE_NUMBER>");

		 
		  
		

		Calendar maxBook = Calendar.getInstance();
		maxBook.add(Calendar.HOUR_OF_DAY, Constants.WORLDSPAN_DEFAULT_HOURS_EXPIRE);
		
		//Aqui diferenciar si se requiere o no colocar una fecha de expiracion
		if (!isFromAvailibility) {
			
			Calendar firstFlightDate = itineraries.get(0).getDepartureDateTime();
			maxBook.getTime();
			if (!maxBook.before(firstFlightDate)) {
				if (maxBook.get(Calendar.DATE) == firstFlightDate.get(Calendar.DATE)) {
					maxBook.setTime(firstFlightDate.getTime());
					
				}
				maxBook.add(Calendar.DATE, -1);
			}
			maxBook.getTime();
			request.append("<TICKET_OPTION>8TL");
			request.append(maxBook.get(Calendar.HOUR));
			request.append(maxBook.get(Calendar.AM_PM)==Calendar.AM?"A":"P" );
			request.append("</TICKET_OPTION>");
			request.append("<TICKET_CAT>00</TICKET_CAT>");
			order.setEffectiveTo(maxBook);
			request.append("<TICKET_DATE>");
			request.append(DateFormatUtils.format( maxBook.getTime(), "ddMMM", Locale.ENGLISH).toUpperCase());
			request.append("</TICKET_DATE>");
		} else {
			// Debe hacerse la reserva sin expiración
			request.append("<TICKET_OPTION>7T/");
			request.append(customerReferenceCode);
			request.append("</TICKET_OPTION>");
			request.append("<TICKET_CAT>00</TICKET_CAT>");
		}
		request.append("<TICKET_CARRIER>");
		request.append(Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE);
		request.append("</TICKET_CARRIER>");
		
		
		request.append("<LEG_COUNT>");
		request.append(order.getLegCount());
		request.append("</LEG_COUNT>");
		
		if (verifyAmount != null && verifyAmount > 0.0D) {
			
			request.append("<CK_TIME_PRICE_DIF>B</CK_TIME_PRICE_DIF>");
			request.append("<PRICE_CK_AMOUNT>");
			request.append(Math.round(verifyAmount*100));
			request.append("</PRICE_CK_AMOUNT>");
			request.append("<PRICE_CK_VAR>");
			request.append(Math.round(Constants.WORLDSPAN_PRICE_CK_VAR*100));
			request.append("</PRICE_CK_VAR>");
			request.append("<PRICE_CK_NOD>2</PRICE_CK_NOD>");
		} else { // si no hay precio usar sin control de diferencial de precios
			//request.append("<CK_TIME_PRICE_DIF>T</CK_TIME_PRICE_DIF>");
			request.append("<CK_TIME_PRICE_DIF>N</CK_TIME_PRICE_DIF>");
			request.append("<PRICE_CK_NOD>N</PRICE_CK_NOD>");
		}
		
		request.append("<MCT_OVERRIDE>Y</MCT_OVERRIDE>");
		//iteracion entre los segmentos solicitados

		for (Itinerary it : itineraries) {
			request.append("<SEGMENT_INFO>");
			request.append("<LEG_NUM>");
			request.append(it.getLegNumber());
			request.append("</LEG_NUM>");
			//TIPO DE OPERACION EN ESTE CASO B: RESERVACION" +
			request.append("<SEGMENT_INDICATOR>B</SEGMENT_INDICATOR>");
			request.append("<AVAIL_LOCATION>N</AVAIL_LOCATION>");
			//request.append("<AVAIL_LOCATION>A</AVAIL_LOCATION>");
			   
			request.append("<AVAIL_INFO>");
				request.append("<AVAIL_DEP_AIRPORT>");
				request.append("<CITY_CODE>");
				request.append(it.getDepartureIataCode());
				request.append("</CITY_CODE>");
				request.append("</AVAIL_DEP_AIRPORT>");
				if (it.getClassOfService() != null) {
					request.append("<AVAIL_DEP_CLASS>");
					request.append(it.getClassOfService().substring(0, 1));
					request.append("</AVAIL_DEP_CLASS>");
				} else{
					request.append("<AVAIL_DEP_CLASS>Y</AVAIL_DEP_CLASS>");	
				}
				 
				request.append("<AVAIL_AIRLINE>");
				request.append(Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE);
				request.append("</AVAIL_AIRLINE>");
				
				
				request.append("<AVAIL_ARRIVAL_AIRPORT>");
				request.append("<CITY_CODE>");
				request.append(it.getArrivalIataCode());
				request.append("</CITY_CODE>");
				request.append("</AVAIL_ARRIVAL_AIRPORT>");
				
				request.append("<AVAIL_DEP_DAY>");
				request.append(it.getDepartureDateTime().get(Calendar.DAY_OF_MONTH));
				request.append("</AVAIL_DEP_DAY>");
				request.append("<AVAIL_DEP_MONTH>");
				String depMonth =DateFormatUtils.format( it.getDepartureDateTime().getTime(), "MMM", Locale.ENGLISH).toUpperCase(); 
				request.append(depMonth);
				request.append("</AVAIL_DEP_MONTH>");
				request.append("<AVAIL_DEP_TIME>");
				request.append(DateFormatUtils.format( it.getDepartureDateTime().getTime(), "HHmm"));
				request.append("</AVAIL_DEP_TIME>");
				request.append("<AVAIL_ARRIVAL_TIME>");
				request.append(DateFormatUtils.format( it.getArrivalDateTime().getTime(), "HHmm"));
				request.append("</AVAIL_ARRIVAL_TIME>");
				
			request.append("</AVAIL_INFO>");
			
			request.append("<AIRLINE_CODE>");
			request.append(Constants.WORLDSPAN_DEFAULT_AIRLINE_CODE);
			request.append("</AIRLINE_CODE>");
			request.append("<FLIGHT_NUM>");
			request.append(it.getFlightNumber());
			request.append("</FLIGHT_NUM>");
			request.append("<DEP_CLASS>");
			request.append(it.getClassOfService().substring(0, 1));
			request.append("</DEP_CLASS>");
			
			request.append("<DEP_DATE>");
			request.append("<DEP_DAY>");
			request.append(it.getDepartureDateTime().get(Calendar.DAY_OF_MONTH));
			request.append("</DEP_DAY>");
			request.append("<DEP_MONTH>");
			request.append(depMonth);
			request.append("</DEP_MONTH>");
			request.append("</DEP_DATE>");

			request.append("<DEP_TIME>");
			request.append("<DEP_HOUR>");
			request.append(it.getDepartureDateTime().get(Calendar.HOUR_OF_DAY));
			request.append("</DEP_HOUR>");
			request.append("<DEP_MIN>");
			request.append(it.getDepartureDateTime().get(Calendar.MINUTE));
			request.append("</DEP_MIN>");
			request.append("</DEP_TIME>");

			/* Aplicaba solo cuando consulta venia de ADC
			request.append("<DEP_DATE>");
			request.append("<DEP_DAY>");
			request.append(it.getSegment().getDepartureDate().substring(0,2));
			request.append("</DEP_DAY>");
			request.append("<DEP_MONTH>");
			request.append(it.getSegment().getDepartureDate().substring(2));
			request.append("</DEP_MONTH>");
			request.append("</DEP_DATE>");

			request.append("<DEP_TIME>");
			request.append("<DEP_HOUR>");
			request.append(it.getSegment().getDepartureTime().substring(0,2));
			request.append("</DEP_HOUR>");
			request.append("<DEP_MIN>");
			request.append(it.getSegment().getDepartureTime().substring(2));
			request.append("</DEP_MIN>");
			request.append("</DEP_TIME>");
			*/

			request.append("<DEP_AIRPORT>");
			request.append(it.getDepartureIataCode());
			request.append("</DEP_AIRPORT>");

			request.append("<ARRIVAL_DATE>");
			request.append("<ARRIV_DAY>");
			request.append(it.getArrivalDateTime().get(Calendar.DAY_OF_MONTH));
			request.append("</ARRIV_DAY>");
			request.append("<ARRIV_MONTH>");
			request.append(DateFormatUtils.format( it.getArrivalDateTime().getTime(), "MMM", Locale.ENGLISH).toUpperCase());
			request.append("</ARRIV_MONTH>");
			request.append("</ARRIVAL_DATE>");

			request.append("<ARRIVAL_TIME>");
			request.append("<ARRIV_HOUR>");
			request.append(it.getArrivalDateTime().get(Calendar.HOUR_OF_DAY));
			request.append("</ARRIV_HOUR>");
			request.append("<ARRIV_MIN>");
			request.append(it.getArrivalDateTime().get(Calendar.MINUTE));
			request.append("</ARRIV_MIN>");
			request.append("</ARRIVAL_TIME>");

			/*Aplicaba solo cuando consulta venia de ADC
			request.append("<ARRIVAL_DATE>");
			request.append("<ARRIV_DAY>");
			request.append(it.getSegment().getArrivalDate().substring(0,2));
			request.append("</ARRIV_DAY>");
			request.append("<ARRIV_MONTH>");
			request.append(it.getSegment().getArrivalDate().substring(2));
			request.append("</ARRIV_MONTH>");
			request.append("</ARRIVAL_DATE>");

			request.append("<ARRIVAL_TIME>");
			request.append("<ARRIV_HOUR>");
			request.append(it.getSegment().getArrivalTime().substring(0,2));
			request.append("</ARRIV_HOUR>");
			request.append("<ARRIV_MIN>");
			request.append(it.getSegment().getArrivalTime().substring(2));
			request.append("</ARRIV_MIN>");
			request.append("</ARRIVAL_TIME>");
			*/
			
			request.append("<ARRIV_AIRPORT>");
			request.append(it.getArrivalIataCode());
			request.append("</ARRIV_AIRPORT>");

			request.append("</SEGMENT_INFO>");
		}
		
		request.append("</BPC9>");

		

		return request.toString();
	}
	
	public BookAndPriceResponse queryBookAndPrice (Order order, UserBean userBean, String formOfPayment, Double verifyAmount ) throws IOException, SAXException {
		BookAndPriceResponse resp = null;
		String customerReferenceCode = userBean.getName()!= null?userBean.getName():"usuario vacio";
		this.initDigester (BookAndPriceResponse.class);
		boolean isFromAvailibility = userBean != null && Constants.CLIENT_CORP_KEY.equals(userBean.getCustomerType());
		
		String query = this.getBookAndPriceRequest(order, customerReferenceCode, formOfPayment, verifyAmount, isFromAvailibility );
		logger.debug("query: "+query);
		String response = doXMLRequest (query);
		logger.debug("response: "+response);
		if (response != null) {
			StringReader reader = new StringReader (response);
			resp = (BookAndPriceResponse) this.digester.parse(reader);
		}

		return resp;
	}


}
