/**
 *
 */
package com.iportal.biz.helper;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import com.iportal.biz.worldspan.TicketPNRResponse;
import com.yage.commons.StringBean;

/**
 * Helper para invocar a servicio Ticket PNR
 * generador de eTickets
 * @author hernan
 * @version 1.0
 *
 */
public class TicketPNRHelper extends WorldspanHelper {

    protected static Log logger = LogFactory.getLog(TicketPNRHelper.class);

    /**
     *
     */
    public TicketPNRHelper() {
        super();
    }

    /**
     * Configura al objeto Digester para respuesta de TKC1 Ticket PNR para leer las respuestas de la consulta
     * XML a worlspan.
     * Para mas información de como usar digester ver tutorial: http://www.devguru.com/features/tutorials/jakarta/jakarta.html
     * y la documentacion oficial en http://jakarta.apache.org/commons/digester.
     *
     */
    @Override
    protected void initMessageDigester() {
        this.digester.addObjectCreate( "TKW1", TicketPNRResponse.class);
        this.digester.addBeanPropertySetter( "TKW1/MSG_VER", "messageVersion" );
        this.digester.addBeanPropertySetter( "TKW1/VAR_COU", "variableCount" );
        this.digester.addBeanPropertySetter( "TKW1/REC_LOC", "recordLocator" );
        this.digester.addBeanPropertySetter( "TKW1/TIC_DAT", "ticketDate" );
        this.digester.addBeanPropertySetter( "TKW1/TIC_TYP", "ticketType" );
        this.digester.addObjectCreate( "TKW1/TIC_INF/TIC_NUM", StringBean.class );
        this.digester.addBeanPropertySetter( "TKW1/TIC_INF/TIC_NUM", "value" );
        this.digester.addSetNext     ( "TKW1/TIC_INF/TIC_NUM", "addETickets");
    }


    public String getTicketPNRRequest ( String recordLocator) {
        StringBuffer request = new StringBuffer ();
        request.append("<TKC1>");
        request.append("<MSG_VER >1</MSG_VER >");
        request.append("<REC_LOC>");
        request.append(recordLocator);
        request.append("</REC_LOC>");
        request.append("</TKC1>");

        return request.toString();
    }

    public TicketPNRResponse queryTicketPNR (String recordLocator ) throws IOException, SAXException {
        TicketPNRResponse resp = null;
        this.initDigester (TicketPNRResponse.class);
        String query = this.getTicketPNRRequest(recordLocator);
        logger.debug("query: "+query);
        String response = doXMLRequest (query);
        logger.debug("response: "+response);

        if (response != null) {
            StringReader reader = new StringReader (response);
            resp = (TicketPNRResponse) this.digester.parse(reader);
        }
        return resp;
    }

}
