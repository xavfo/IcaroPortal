/**
 * 
 */
package com.iportal.biz.helper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import xxclient.XXClient;

import com.iportal.Constants;
import com.iportal.biz.worldspan.AvailabilityResponse;
import com.iportal.biz.worldspan.Leg;
import com.iportal.cart.ctrl.flight.SearchFlightForm;
import com.yage.commons.DateFormatUtils;


/**
 * Clase para conectarse a Worspan 
 * enviar y recibir tramas XML para consultas y respuestas	
 * 
 * @author hernan
 * @version 1.0
 *
 */
public class AirAvailabilityHelper extends WorldspanHelper {
	
	protected static Log logger = LogFactory.getLog(AirAvailabilityHelper.class);
	
	public AirAvailabilityHelper() {
		super();
		this.clear();
	}
	


	/**
	 * Configura al objeto Digester para respuesta de ADC1 disponibilidad  para leer las respuestas de la consulta
	 * XML a worlspan.
	 * Para mas información de como usar digester ver tutorial: http://www.devguru.com/features/tutorials/jakarta/jakarta.html
	 * y la documentacion oficial en http://jakarta.apache.org/commons/digester.
	 * 
	 */
	protected void initMessageDigester () {
		this.digester.addObjectCreate( "ADW1", AvailabilityResponse.class);
		//this.adwDigester.addSetProperties("ADW1");
		this.digester.addBeanPropertySetter( "ADW1/MSG_VER", "messageVersion" );
		this.digester.addBeanPropertySetter( "ADW1/VAR_COU", "variableCount" );
		this.digester.addBeanPropertySetter( "ADW1/LEG_COU", "legCount" );	

		this.digester.addObjectCreate( "ADW1/LEG_INF", Leg.class );
		this.digester.addBeanPropertySetter( "ADW1/LEG_INF/LEG_STA", "status" );
		this.digester.addBeanPropertySetter( "ADW1/LEG_INF/VER_STA", "verbiageStatus" );
		this.digester.addSetNext("ADW1/LEG_INF", "addLeg");
		
		this.digester.addObjectCreate( "ADW1/AVA_VER", String.class );
		this.digester.addSetNext("ADW1/AVA_VER", "addAvailabilityVerbiage");
		this.digester.addObjectCreate( "ADW1/SEG_INF", "com.iportal.biz.worldspan.Segment" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/LEG_NUM", "legNumber" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/AVA_LOC", "availabilityLocation" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ALL_IND", "allotmentIndicator" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/WGR_LOC", "worldGroupLocator" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/AVA_DEP_TIM", "availabilityDepartureTime" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/AVA_ARR_TIM", "availabilityArrivalTime" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/SEG_TYP", "segmentType" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ARL_COD", "airlineCode" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/FLI_NUM", "flightNumber" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/DEP_ARP", "departureAirportCode" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/DEP_DAT", "departureDate" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/REL_DAY_IND_DEP", "relativeDayIndicatorDeparture" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/REL_DAY_DEP", "relativeDayDeparture" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/DEP_TIM", "departureTime" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ARR_ARP", "arrivalAirportCode" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ARR_DAT", "arrivalDate" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/REL_DAY_IND_ARR", "relativeDayIndicatorArrival" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/REL_DAY_ARR", "relativeDayArrival" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ARR_TIM", "arrivalTime" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/EQP_COD", "equipmentCode" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/MEA_COD", "mealCode" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/NUM_STO", "numberStops" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/DEP_TER", "departureTerminal" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ARR_TER", "arrivalTerminal" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/COD_SHA", "codeShare" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/ARL_SOU", "airlineSource" );
		this.digester.addBeanPropertySetter( "ADW1/SEG_INF/COS", "classService" );
		this.digester.addSetNext("ADW1/SEG_INF", "addSegment");

	}
	
	public String getAirAvailabilityRequest (Integer seats, Boolean round, String airlineCode, String departureAirport, String arrivalAirport, String departureDate,  String departureTime, String returnDate,  String returnTime) {
		StringBuffer request = new StringBuffer ();
		request.append("<ADC1>");
		request.append("<MSG_VER>1</MSG_VER>");
		if (round) {
			request.append("<VAR_COU>002</VAR_COU>");
		} else {
			request.append("<VAR_COU>001</VAR_COU>");	
		}
		request.append("<SEA>");
		request.append(seats);
		request.append("</SEA>");
		request.append("<MAX_RSP_SEG>41</MAX_RSP_SEG>");
		request.append("<PAR_RSP_ALL>Y</PAR_RSP_ALL>");
		request.append("<AVA_SEA_IND>N</AVA_SEA_IND>");
		request.append("<LEG_INF>");
		/*request.append("<DEP_ARL>");
		request.append(airlineCode);
		request.append("</DEP_ARL>");*/
		request.append("<DEP_ARP>");
		request.append(departureAirport);
		request.append("</DEP_ARP>");
		request.append("<DEP_CLA>");
		request.append(Constants.WORLDSPAN_DEFAULT_CLASSSEARH);
		request.append("</DEP_CLA>");
		request.append("<ARR_ARP>");
		request.append(arrivalAirport);
		request.append("</ARR_ARP>");
		request.append("<DEP_DAT>");
		request.append(departureDate);
		request.append("</DEP_DAT>");
		request.append("<DEP_TIM>");
		request.append(departureTime);
		request.append("</DEP_TIM>");
		request.append("<RUL_INF>");
		request.append("<RUL_DIR>O</RUL_DIR>");
		request.append("<APP_RUL>");
		request.append("<IGN_FLT>N</IGN_FLT>");
		request.append("<TRA_CLA>NNNNN</TRA_CLA>");
		request.append("<FEE_IN>IIIII</FEE_IN>");
		request.append("<FEE_OUT>IIIII</FEE_OUT>");
		request.append("<ALL_FLT>N</ALL_FLT>");
		request.append("</APP_RUL>");
		request.append("</RUL_INF>");
		request.append("</LEG_INF>");
		
		if (round) {
			request.append("<LEG_INF>");
			/*request.append("<DEP_ARL>");
			request.append(airlineCode);
			request.append("</DEP_ARL>");*/
			request.append("<DEP_ARP>");
			request.append(arrivalAirport);
			request.append("</DEP_ARP>");
			request.append("<DEP_CLA>");
			request.append(Constants.WORLDSPAN_DEFAULT_CLASSSEARH);
			request.append("</DEP_CLA>");
			request.append("<ARR_ARP>");
			request.append(departureAirport);
			request.append("</ARR_ARP>");
			request.append("<DEP_DAT>");
			request.append(returnDate);
			request.append("</DEP_DAT>");
			request.append("<DEP_TIM>");
			request.append(returnTime);
			request.append("</DEP_TIM>");
			request.append("<RUL_INF>");
			request.append("<RUL_DIR>O</RUL_DIR>");
			request.append("<APP_RUL>");
			request.append("<IGN_FLT>N</IGN_FLT>");
			request.append("<TRA_CLA>NNNNN</TRA_CLA>");
			request.append("<FEE_IN>IIIII</FEE_IN>");
			request.append("<FEE_OUT>IIIII</FEE_OUT>");
			request.append("<ALL_FLT>N</ALL_FLT>");
			request.append("</APP_RUL>");
			request.append("</RUL_INF>");
			request.append("</LEG_INF>");
		}
		request.append("</ADC1>");
		return request.toString();
	}
	

	
	public AvailabilityResponse queryAirAvailability (SearchFlightForm flightForm, String airlineCode ) throws IOException, SAXException {
		AvailabilityResponse resp = null;
		this.initDigester (AvailabilityResponse.class);
		String returnDate =  null;
		String departureDate = DateFormatUtils.format( flightForm.getFrom().getTime(), "ddMMM", Locale.ENGLISH);
		departureDate = departureDate.toUpperCase();
		if (flightForm.getTwoWay()) {
			returnDate = DateFormatUtils.format( flightForm.getTo().getTime(), "ddMMM", Locale.ENGLISH);
			returnDate = returnDate.toUpperCase();
		}
		String query = this.getAirAvailabilityRequest(flightForm.getTotalPassengers(), flightForm.getTwoWay(), airlineCode, flightForm.getFromCityCode(), flightForm.getToCityCode(), departureDate, flightForm.getTimeFrom(), returnDate, flightForm.getTimeTo());
		logger.debug("query: "+query);
		String response = doXMLRequest (query);
		logger.debug("response: "+response);

		if (response != null) {
			StringReader reader = new StringReader (response);
			resp = (AvailabilityResponse) this.digester.parse(reader);
		}

		return resp;
	}
	
	public static void main(String[] args) {
		//Sample ssll = new Sample();
		AirAvailabilityHelper ws = new AirAvailabilityHelper (); 
		ws.xxc = new XXClient();
		ws.xxc.host = "xmlpropp.worldspan.com";
		ws.xxc.port = 443; // without SSL
		
		// this.xxc.port = 443; //SSL
		ws.xxc.ssl = false; // SSL (if true, change port to 443!)
		ws.xxc.compress = true; // compression
		ws.xxc._native = true; // native request; MUST set to true for XML
		
		// Pro
		ws.xxc.keepalive = false; // indicates if the connection to the server
		ws.xxc.tc = "<tc><iden u = 'icaro' p='icaro0000'/>" +
		"<provider session= 'ICARODEV' pcc='DYT'>Worldspan</provider></tc>";
		
		// should persist
		// transaction control
		/*this.xxc.tc = "<tc><iden u = 'SdkDevUser' p='sdkuser0000'/>"
				+ "<provider session='W1XMLDEV' pcc='XUF'>Worldspan</provider></tc>";*/
		/*ws.xxc.tc = "<tc><iden u = 'SdkDevUser' p='sdkuser0000'/>" +
				"<provider session= 'W1XMLDEV' pcc='XUF'>Worldspan</provider></tc>";
		
		// should persist
		// transaction control
		/*this.xxc.tc = "<tc><iden u = 'SdkDevUser' p='sdkuser0000'/>"
				+ "<provider session='W1XMLDEV' pcc='XUF'>Worldspan</provider></tc>";*/
		/*this.xxc.tc = "<tc><iden u = 'SdkDevUser' p='sdkuser0000'/>" +
				"<provider session= 'W1XMLDEV' pcc='XUF'>Worldspan</provider></tc>";*/
		try {
			//ws.initXXCLient();
			//ws.initDigester();
			String response ="<ADW1 frame=\"06600\"><MSG_VER>1</MSG_VER><VAR_COU>0004</VAR_COU><LEG_COU>1</LEG_COU><LEG_INF><LEG_STA>GOOD</LEG_STA><VER_STA>N</VER_STA></LEG_INF><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><AVA_VER>000</AVA_VER><SEG_INF><LEG_NUM>1</LEG_NUM><AVA_LOC>A</AVA_LOC><AVA_DEP_TIM>1030</AVA_DEP_TIM><SEG_TYP>S</SEG_TYP><ARL_COD>X8</ARL_COD><FLI_NUM>0908</FLI_NUM><DEP_ARP>UIO</DEP_ARP><DEP_DAT>01AUG</DEP_DAT><DEP_TIM>1050</DEP_TIM><ARR_ARP>GYE</ARR_ARP><ARR_DAT>01AUG</ARR_DAT><ARR_TIM>1140</ARR_TIM><EQP_COD>DH8</EQP_COD><NUM_STO>0</NUM_STO><COS>Y09</COS></SEG_INF><SEG_INF><LEG_NUM>1</LEG_NUM><AVA_LOC>A</AVA_LOC><AVA_DEP_TIM>1030</AVA_DEP_TIM><SEG_TYP>S</SEG_TYP><ARL_COD>X8</ARL_COD><FLI_NUM>0900</FLI_NUM><DEP_ARP>UIO</DEP_ARP><DEP_DAT>01AUG</DEP_DAT><DEP_TIM>0715</DEP_TIM><ARR_ARP>GYE</ARR_ARP><ARR_DAT>01AUG</ARR_DAT><ARR_TIM>0800</ARR_TIM><EQP_COD>737</EQP_COD><NUM_STO>0</NUM_STO><COS>Y09</COS></SEG_INF><SEG_INF><LEG_NUM>1</LEG_NUM><AVA_LOC>A</AVA_LOC><AVA_DEP_TIM>1030</AVA_DEP_TIM><SEG_TYP>S</SEG_TYP><ARL_COD>X8</ARL_COD><FLI_NUM>0916</FLI_NUM><DEP_ARP>UIO</DEP_ARP><DEP_DAT>01AUG</DEP_DAT><DEP_TIM>1530</DEP_TIM><ARR_ARP>GYE</ARR_ARP><ARR_DAT>01AUG</ARR_DAT><ARR_TIM>1615</ARR_TIM><EQP_COD>737</EQP_COD><NUM_STO>0</NUM_STO><COS>Y09</COS></SEG_INF><SEG_INF><LEG_NUM>1</LEG_NUM><AVA_LOC>A</AVA_LOC><AVA_DEP_TIM>1030</AVA_DEP_TIM><SEG_TYP>S</SEG_TYP><ARL_COD>X8</ARL_COD><FLI_NUM>0924</FLI_NUM><DEP_ARP>UIO</DEP_ARP><DEP_DAT>01AUG</DEP_DAT><DEP_TIM>1830</DEP_TIM><ARR_ARP>GYE</ARR_ARP><ARR_DAT>01AUG</ARR_DAT><ARR_TIM>1915</ARR_TIM><EQP_COD>737</EQP_COD><NUM_STO>0</NUM_STO><COS>Y09</COS></SEG_INF></ADW1>";
			
			/*StringReader reader = new StringReader (response);
			AvailabilityResponse resp = (AvailabilityResponse) ws.digester.parse(reader);
			System.out.println(resp.getFrame());*/
			response = ws.xxc.transaction("<PSC5><OPT>F</OPT><POI_ORI><CIT>CUE</CIT></POI_ORI><NUM_ALT>15</NUM_ALT><ARL_INF><ARL_OPT>B</ARL_OPT><ARL_COD>X8</ARL_COD></ARL_INF><PTC_INF><NUM_PAX>1</NUM_PAX><PTC>ADT</PTC></PTC_INF><DES_INF><DEP_DAT>02SEP</DEP_DAT><DEP_TIM>0700</DEP_TIM><POI_DES><CIT>GYE</CIT></POI_DES></DES_INF></PSC5>");	
			//response = ws.doXMLRequest("<PSC5><OPT>F</OPT><POI_ORI><CIT>CUE</CIT></POI_ORI><NUM_ALT>15</NUM_ALT><ARL_INF><ARL_OPT>B</ARL_OPT><ARL_COD>X8</ARL_COD></ARL_INF><PTC_INF><NUM_PAX>1</NUM_PAX><PTC>ADT</PTC></PTC_INF><DES_INF><DEP_DAT>02SEP</DEP_DAT><DEP_TIM>0700</DEP_TIM><POI_DES><CIT>GYE</CIT></POI_DES></DES_INF></PSC5>");
			System.out.println(response);
			/*
			String response = ws.xxc.transaction(ssll.getListXML());
			System.out.println("*******LIST**********");
			System.out.println(response);
			response = ws.doXMLRequest(ssll.getListXML());
			System.out.println(response);
			response = ws.doXMLRequest("<ADC1><MSG_VER>1</MSG_VER><VAR_COU>001</VAR_COU><SEA>2</SEA><MAX_RSP_SEG>41</MAX_RSP_SEG><PAR_RSP_ALL>Y</PAR_RSP_ALL><AVA_SEA_IND>N</AVA_SEA_IND><LEG_INF><DEP_ARL>X8</DEP_ARL><DEP_ARP>12345</DEP_ARP><DEP_CLA>BCY</DEP_CLA><ARR_ARP>67890</ARR_ARP><DEP_DAT>212Jul</DEP_DAT><DEP_TIM>2000</DEP_TIM><RUL_INF><RUL_DIR>O</RUL_DIR><APP_RUL><IGN_FLT>N</IGN_FLT><TRA_CLA>NNNNN</TRA_CLA><FEE_IN>IIIII</FEE_IN><FEE_OUT>IIIII</FEE_OUT><ALL_FLT>N</ALL_FLT></APP_RUL></RUL_INF></LEG_INF>");
			
			System.out.println(response);*/
		/*} catch (HttpErrorException httpe) {
			
			logger.error(httpe.getMessage() + "\n"
					+ httpe.getFaultString() + "\n" + httpe.getFaultCode()
					+ "\nConnection terminated by Client!", httpe);
			httpe.printStackTrace();
			try {
				ws.xxc.disconnect(); // disconnect specified server
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			
		} finally {
			try {
				ws.xxc.disconnect(); // disconnect specified server
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			ws.xxc = null;
			
		}
	}
	
}
