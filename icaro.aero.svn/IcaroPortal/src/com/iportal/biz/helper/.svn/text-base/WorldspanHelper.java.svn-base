package com.iportal.biz.helper;

import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xxclient.HttpErrorException;
import xxclient.XXClient;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;

/**
 * Clase base para invocar mensajes de peticion y procesar mensajes
 * de respuesta del sistema de Worldspan.
 *
 * Para que la aplicacion pueda conectarse con Worldspan de manera
 * exitosa cuando se levanta en el eclipse debe hacerse las siguientes
 * operaciones
 * 1.- En Window/Preferences/Java/Installed JRE´s:
 *             en el JDK150 eliminar las librerias:
 *                 ext/dnsns.jar
 *                 ext/localedata.jar
 *                 ext/sunjce_provider.jar
 *                 ext/sunpkcs.jar
 *
 * 1.- En Window/Preferences/Tomcar/JVM Settings:
 *             en Boot Classpath incluir las librerias
 *                JAVA_HOME/jre/lib/rt.jar
 *                JAVA_HOME/jre/lib/jsse.jar
 *                JAVA_HOME/jre/lib/jce.jar
 *                JAVA_HOME/jre/lib/charsets.jar
 *
 * @author hernan
 *
 */
public abstract class WorldspanHelper extends BaseHelper {

    protected static Log logger = LogFactory.getLog(WorldspanHelper.class);

    protected XXClient xxc;

    protected Digester digester;

    protected String worldspanResponseClassName;

    /**
     * Inicia los errores de digester para todos los mensajes
     */
    protected void initDigester (Class<?> responseClass ) {
        if (this.digester == null ) {
            this.setWorldspanResponseClassName(responseClass.getName());
            this.digester = new Digester();
            this.digester.setValidating( false );
            this.digester.addObjectCreate( "XXW/ERROR/TEXT", responseClass);
            this.digester.addBeanPropertySetter( "XXW/ERROR/TEXT", "errorMessage" );
            this.initMessageDigester();
        }
    }

    protected abstract void initMessageDigester ();

    public WorldspanHelper() {
        super();
        this.clear();
    }

    protected void finalize() throws Throwable {
        this.clear();
        super.finalize();
    }

    public void clear() {
        if(this.xxc != null) {
            try {
                this.xxc.disconnect();
            } catch (IOException e) {

            }
        }
        this.xxc = null;
        if (this.digester != null) {
            this.digester.clear();
        }
        this.digester = null;
        this.worldspanResponseClassName = null;
    }

    protected void initXXCLient() {
        String host  = Constants.WORLDSPAN_DEFAULT_HOST.trim();

        Integer port = Constants.WORLDSPAN_DEFAULT_PORT;

        String user  = Constants.WORLDSPAN_DEFAULT_USER.trim();

        String password = Constants.WORLDSPAN_DEFAULT_PASSWORD.trim();

        String session = Constants.WORLDSPAN_DEFAULT_SESSION.trim();

        String pcc = Constants.WORLDSPAN_DEFAULT_PCC.trim();

        String provider = Constants.WORLDSPAN_DEFAULT_PROVIDER.trim();

        Boolean userSSL = Constants.WORLDSPAN_USE_SSL;

        String trace = Constants.WORLDSPAN_TRACE;

        this.xxc = new XXClient();
        this.xxc.host = host;
        this.xxc.port = port; // without SSL
        this.xxc.port = userSSL ? 443 : port;

        // this.xxc.port = 443; //SSL
        this.xxc.ssl = userSSL; //false; // SSL (if true, change port to 443!)
        this.xxc.compress = true; // compression
        this.xxc._native = true; // native request; MUST set to true for XML

        // Pro
        this.xxc.keepalive = false; // indicates if the connection to the server

        StringBuffer tc = new StringBuffer();
        tc.append("<tc><iden u = '");
        tc.append(user);
        tc.append("' p='");
        tc.append(password);
        tc.append("'/>");
        tc.append("<provider session= '");
        tc.append(session);
        tc.append("' pcc='");
        tc.append(pcc);
        tc.append("'>");
        tc.append(provider);
        tc.append("</provider><trace>");

        if (trace != null && trace.length() >0 ) {
            tc.append(trace);
        }
        tc.append("</trace></tc>");
        //para producccion quitarel trace
        //tc.append("</provider></tc>");

        this.xxc.tc = tc.toString();
    }

    public String doXMLRequest(String request) {
        /*System.setProperty("user.country",  Locale.ENGLISH.getCountry());
        System.setProperty("user.language", Locale.ENGLISH.getLanguage());
        System.setProperty("user.variant",  Locale.ENGLISH.getVariant());*/

        String response = null;
        try {
            if (this.xxc == null) {
                this.initXXCLient();
                logger.debug("TC: "+this.xxc.tc);
            }

            for (int i = 0; i < Constants.WORLDSPAN_MAX_RETRIES; i++) {
                try {
                    response = this.xxc.transaction(request);
                    // if success - get out of here;
                    break;
                } catch (HttpErrorException httpe) {
                    if((i+1)==Constants.WORLDSPAN_MAX_RETRIES) {
                        // max tries reached - report back to user
                        throw httpe;
                    } else {
                        logger.error(httpe.getMessage() + ": "   + httpe.getFaultString() + " (code: " + httpe.getFaultCode() + ") - retrying, intent:" + (i+1));
                    }
                }
            }
        } catch (HttpErrorException httpe) {
            logger.error(httpe.getMessage() + ": " + httpe.getFaultString() + " (code: " + httpe.getFaultCode()    + ") - Connection terminated by Client!", httpe);
            try {
                this.xxc.disconnect(); // disconnect specified server
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                this.xxc.disconnect(); // disconnect specified server
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            this.xxc = null;
        }

        return response;
    }

    public String getWorldspanResponseClassName() {
        return worldspanResponseClassName;
    }
    public void setWorldspanResponseClassName(String worldspanResponseClassName) {
        this.worldspanResponseClassName = worldspanResponseClassName;
    }





}
