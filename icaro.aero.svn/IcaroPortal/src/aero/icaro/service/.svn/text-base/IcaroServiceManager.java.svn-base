 /*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;


import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import aero.icaro.service.skies.stub.IcaroUser;

import com.yage.struts.action.BaseForm;

/**
 * Clase Base para llamar a WebServices. En esta clase
 * se definen los objetos basicos para hacer llamadas a
 * Servicios Web de Icaro.<br/><br/>
 *
 * Cada Servicio invocado devuelve un objeto que tiene
 * como datos miembro a un estado de la llamada al servicio
 * que nos informa sobre el �xito o fracaso de la misma,
 * as� como un mensaje de texto con algun error. Esos datos
 * son guardados en la tabla de auditor�a de llamadas a servicios Web
 *
 *
 *
 * @author ftamayo
 * @version 1.0
 *
 */
public abstract class IcaroServiceManager {

    private static Log logger = LogFactory.getLog(IcaroServiceManager.class);

    private Service service;

    private Object module;

    private HttpServletRequest request;



    /**
     * Crea un nuevo Manejador para invocaci�n a servicios Web.<br/>
     * Se debe especificar desde que componente del sistema se crea
     * el objeto para que en cada llamada al servicio Web, registre
     * desde que m�dulo se invoc� al servicio Web.
     * @param module componente del sistema donde se instancia al manejador de invocaci�n de servicios Web
     * @param request petici�n HTTP desde donde se solicita invocar al servicio Web.
     */
    public IcaroServiceManager(Object module, HttpServletRequest request) {
        super();
        this.module = module;
        this.request = request;
    }


    /**
     * Crea un nuevo Manejador para invocaci�n a servicios Web.<br/>
     * Se debe especificar desde que componente del sistema se crea
     * el objeto para que en cada llamada al servicio Web, registre
     * desde que m�dulo se invoc� al servicio Web.
     * @param module componente del sistema donde se instancia al manejador de invocaci�n de servicios Web
     */
    public IcaroServiceManager(Object module) {
        super();
        this.module = module;
    }

    /**
     * Crea un nuevo Manejador para invocaci�n a servicios Web
     * espevificando cual es la petici�n HTTP que crea al manejador.<br/>
     *
     * @param request petici�n HTTP desde donde se solicita invocar al servicio Web.
     */
    public IcaroServiceManager(HttpServletRequest request) {
        super();
        this.request = request;
    }

    /**
     *  Crea un nuevo Manejador para invocaci�n a servicios Web.<br/>
     */
    public IcaroServiceManager() {
        super();
        this.clear();
    }

    /**
     * Borra las prpiedades del manejador de invocaci�n
     */
    private void clear () {
        this.module  = null;
        this.request = null;
    }


    /**
     * Resetea al manejador para invocar a los servicios Web
     * expuestos por en la interfaz que es propiedad del manejador.<br/>
     *
     * Crea un Servicelocator para la interfaz y direcciona
     * la petici�n a la direci�n de endpoint por defecto
     * definida en <code>Constants.DEFAULT_SERVICE_ENDPOINT</code>.<br/>
     * Por �ltimo obtiene una referencia a la interfaz de invocaci�n de servicios
     * correspondiente.
     *
     * @throws ServiceException si existe un error al resetear el manejador
     */
    protected  abstract void reset () throws ServiceException;

    /**
     * Crea un objeto {@link com.icaro.service.client.IcaroServiceMessageBean} copiando las propiedades
     * del par�metro bean. Esta par�metro generalmente es un objeto respuesta de los Web Services
     * invocados. <br/>
     * MessageBean cuenta con las siguientes propiedades:
     * <ul>
     *       <li>statusCode desde la propiedad  <code>statusCode</code>.</li>
     *         <li>response desde la propiedad <code>response</code>.</li>
     * </ul>
     *
     * @param bean Objeto respuesta de invocaci�n a Web Services
     * @return {@link com.icaro.service.client.IcaroServiceMessageBean} de respuesta de la invocaci�n a un Web Services
     * @throws IllegalAccessException En caso de que ocurra un error al acceder a las propiedades del objeto par�metro bean
     * @throws InvocationTargetException En caso de que ocurra un error al acceder a las propiedades del objeto par�metro bean
     * @throws NoSuchMethodException En caso de que al momento de efectuar la instrospecci�n a bean no encuentre la propiedad buscada.
     */
    protected IcaroServiceMessageBean getMessageBean (Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        IcaroServiceMessageBean message = null;
        if (bean != null){
            message = new IcaroServiceMessageBean ();
            //message.setCodes((String[])PropertyUtils.getSimpleProperty (bean, "codigos"));
            message.setStatusCode((BigInteger)PropertyUtils.getSimpleProperty (bean, "statusCode"));
            message.setResponse((String)PropertyUtils.getSimpleProperty (bean, "response"));

            //BeanUtils
        }
        return message;
    }

    /**
     * M�todo que llama a clase Helper {@link com.iportal.biz.audit.SysWebServiceAuditHelper} para
     * guardar registros de auditor�a de cada llamada a un Web Service
     * @see com.iportal.biz.audit.SysWebServiceAuditHelper
     * @param sess sesi�n de Hibernate con conexi�n abierta a base de datos
     * @param form forma con mensaje de respuesta de la llamada a servicio
     * @param method m�todo llamdo
     * @throws HibernateException si ocurre un error al almecenar los registros
     */
    /*protected void auditWebServiceCall (Session sess, MessageForm form, String method) throws HibernateException{
        SysWebServiceAuditHelper.audit (sess, form, this.getService().getServiceName().getNamespaceURI(), method, this.getModule(), this.getRequest()   );
    }
*/
    /**
     * Llamada a clase Helper para registrar datos de auditor�a de las
     * llamadas a WebSerices con una session de base de datos
     * ya abierta
     *
     * @see com.iportal.biz.audit.SysWebServiceAuditHelper
     * @see
     * @param sess sesion Hibernate de conexion a la base abierta
     * @param messageBean respuesta de llamada a servicio
     * @param method metodo del servicio que se estaba llamando
     * @throws HibernateException en caso de algun error al momento de ejecutar la sentencia de registro
     */
    /*protected void auditWebServiceCall (Session sess, MessageBean messageBean, String method) throws HibernateException{
        SysWebServiceAuditHelper.audit (sess, messageBean, this.getService().getServiceName().getNamespaceURI(), method, this.getModule(), this.getRequest()   );
    }*/

    /**
     * Metodo de llamada a clase Helper para almacenar registros
     * de auditor�a de llamadas a WebServices.
     *
     * Este m�todo llama a funci�n que abre una conexi�n de hibernate
     * independiente para almacenar los registros de auditoria en la
     * base de datos.
     *
     * @param messageBean respuesta de llamada a servicio
     * @param method metodo del servicio que se estaba llamando
     */
/*    protected void auditWebServiceCall (MessageBean messageBean, String method){
        SysWebServiceAuditHelper.audit (messageBean, this.getService().getServiceName().getNamespaceURI(), method, this.getModule(), this.getRequest()   );
    }*/

    /**
     * Lo mismo que el anterior pero con una forma en lugar de
     * un MessageBean
     * @see  #auditWebServiceCall()
     * @param form forma con mensaje de respuesta de la llamada a servicio
     * @param method m�todo invocado remotamente
     */
    /*protected void auditWebServiceCall (MessageForm form, String method){
        SysWebServiceAuditHelper.audit (form, this.getService().getServiceName().getNamespaceURI(), method, this.getModule(), this.getRequest()   );
    }*/


    /**
     * Devuelve el m�dulo desde donde se invoca a un Servicio Web
     * @return Componente desde donde se invoca a un Servicio Web
     */
    public Object getModule() {
        return module;
    }
    /**
     * Devuelve la petici�n HTTP que instaci� al manejador de Servicios Web.
     * @return Returns the request.
     */
    public HttpServletRequest getRequest() {
        return request;
    }
    /**
     * Devuelve referencia a servicio invocado.
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }
    /**
     * Setea el m�dulo desde donde se desea invocar al servicio Web o desde
     * donde se ha instanciado a este manejador de servicios Web.
     * @param m�dulo o componente desde donde se instanci� a este manejador
     */
    public void setModule(Object module) {
        this.module = module;
    }
    /**
     * Setea la peticion HTTP
     * @see #getRequest()
     * @param request The request to set.
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    /**
     * Setea Servicio
     * @see #getService()
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }


    /**
     * Convierte un objeto de tipo {@link com.yage.struts.action.BaseForm}
     * a un objeto de tipo {@link aero.icaro.service.skies.stub.IcaroUser}
     * que es usado para invocar a servicios Web de icaro.<br/>
     * Las propiedades que ser�n buscadas en los objetos de tipo <code>BaseForm</code> son las siguientes.
     * <ul>
     *          <li>nombre desde propiedad <code>name</code>.</li>
     *         <li>level desde  propiedad <code>level</code>.</li>
     *       <li>levelDescription desde  propiedad <code>levelDescription</code>.</li>
     * </ul>
     *
     * @param form forma con propiedades necesarias para crear un UsuarioBean
     * @return {@link aero.icaro.service.skies.stub.IcaroUser} cargado con las propiedades de {@link com.yage.struts.action.BaseForm}
     * @throws IllegalAccessException En caso de que ocurra un error al acceder a las propiedades del objeto par�metro bean
     * @throws InvocationTargetException En caso de que ocurra un error al acceder a las propiedades del objeto par�metro bean
     * @throws NoSuchMethodException En caso de que al momento de efectuar la instrospecci�n a bean no encuentre la propiedad buscada.
     */
    protected IcaroUser getUsuarioBean(BaseForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //
        IcaroUser resp = null;
        if (form != null) {
            resp = new IcaroUser ();
            resp.setName((String)PropertyUtils.getSimpleProperty (form, "name"));
            resp.setLevel((BigInteger)PropertyUtils.getSimpleProperty (form, "level"));
            resp.setLevelDescription((String)PropertyUtils.getSimpleProperty (form, "levelDescription"));
        }
        return resp;
    }


    /**
     * Escribe al archivo de log  todas las propiedades del objeto
     * pasado como referencia
     * @param result objeto que se va a logear en archivo
     */
    @SuppressWarnings("unchecked")
    protected void logAllProperties(Object result) {
        Map<String, Object> map = null;
        if (null != result) {
            try {
                map = PropertyUtils.describe(result);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    logger.info(entry.getKey()+": "+entry.getValue());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

    }


}
