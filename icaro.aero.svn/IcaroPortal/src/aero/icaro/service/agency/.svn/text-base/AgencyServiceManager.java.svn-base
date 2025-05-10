/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service.agency;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import aero.icaro.service.IcaroServiceException;
import aero.icaro.service.IcaroServiceManager;
import aero.icaro.service.IcaroServiceMessageBean;
import aero.icaro.service.agency.stub.AgencyAccountSummary;
import aero.icaro.service.agency.stub.AgencyServiceLocator;
import aero.icaro.service.agency.stub.AgencyServicePortType;
import aero.icaro.service.agency.stub.IcaroUser;
import aero.icaro.service.agency.stub.MilleDetail;
import aero.icaro.service.IcaroUserBean;
import aero.icaro.service.MillesDetailBean;
import aero.icaro.service.agency.stub.IcaroServiceMessage;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.ctrl.icaro.service.ServiceAccountForm;

/**
 * @author hleon
 * @version 1.0
 *
 * Implementacion de cliente para llamadas a servicios Web de Fybeca
 */
public class AgencyServiceManager extends IcaroServiceManager {

    private static Log logger = LogFactory.getLog(AgencyServiceManager.class);

    private AgencyServicePortType agency;

    private String endPointAddress;

    /**
     * Crea un nuevo objeto manejador de clientes
     *
     * @throws ServiceException
     */
    public AgencyServiceManager() throws ServiceException {
        super();
        this.clear();
        this.reset();
    }

    /**
     * Crea un nuevo objeto manejador de clientes
     *
     * @param module
     *            m�dulo desde donde se crea al manejador de usuarios
     */
    public AgencyServiceManager(Object module) throws ServiceException {
        super(module);
        this.clear();
        this.reset();

    }

    /**
     * Crea un nuevo objeto manejador de clientes
     *
     * @param module
     *            m�dulo desde donde se crea al manejador de usuarios
     * @param request
     *            petici�n HTTP desde donde se llama al manejador
     * @throws ServiceException
     */
    public AgencyServiceManager(Object module, HttpServletRequest request)
            throws ServiceException {
        super(module, request);
        this.clear();
        this.reset();
    }

    /**
     * Elimina y reinicia al objeto
     */
    private void clear() {
        this.agency = null;
        this.endPointAddress = null;
    }

    /**
     * Resetea al manejador para invocar a los servicios Web de la interfaz de
     * la cual se encarga de invocar este manejador. Crea un Servicelocator para
     * la interfaz y direcciona la petici�n a la direci�n de endpoint por
     * defecto definida en
     *
     * @link Constants.DEFAULT_SERVICE_ENDPOINT
     *
     * @throws ServiceException
     */
    protected void reset() throws ServiceException {
        AgencyServiceLocator locator = new AgencyServiceLocator();
        String address = StringUtils.replaceOnce(locator
                .getagencyServicePortAddress(), "icaro.aero/serviciosweb/",
                Constants.DEFAULT_SERVICE_ENDPOINT);
        // setea la nueva direccion como dedault EndPoint
        // String address1= "http://19.168.0.1/services/Usuario";
        locator.setagencyServicePortEndpointAddress(address);
        this.endPointAddress = " Endpoint: ".concat(address);
        // logger.info(address);
        this.setService(locator);
        agency = locator.getagencyServicePort();
    }

    /**
     * Convierte un objeto IcaroUser en IcaroUserBean
     *
     * @param bean
     *            objeto IcaroUser
     * @return devuelve un IcaroUserBean iniciado con las propiedades del
     *         parametro bean
     */
    protected IcaroUserBean getIcaroUserBean(IcaroUser bean) {
        IcaroUserBean userBean = null;
        if (bean != null) {
            userBean = new IcaroUserBean();
            // Datos Llamada a WebService
            userBean.setStatusCode(bean.getStatusCode());
            userBean.setResponse(bean.getResponse());
            userBean.setLevel(bean.getLevel());
            userBean.setLevelDescription(bean.getLevelDescription());
            userBean.setName(bean.getName());

        }
        return userBean;
    }

    protected MillesDetailBean getUserMillesBean(MilleDetail bean) {
        MillesDetailBean resp = null;
        if (bean != null) {
            resp = new MillesDetailBean();
            resp.setLabel(bean.getLabel());
            resp.setMilles(bean.getMiles());
        }
        return resp;
    }

    protected IcaroServiceMessageBean getIcaroServiceMessageBean(
            IcaroServiceMessage bean) {
        IcaroServiceMessageBean resp = null;
        if (bean != null) {
            resp = new IcaroServiceMessageBean();
            resp.setStatusCode(bean.getStatusCode());
            resp.setResponse(bean.getResponse());
        }
        return resp;
    }

    protected AgencyAccountSummaryBean getAgencyAccountSummaryBean(
            AgencyAccountSummary bean) {
        AgencyAccountSummaryBean accountBean = null;
        if (bean != null) {
            accountBean = new AgencyAccountSummaryBean();
            // Datos Llamada a WebService
            accountBean.setStatusCode(bean.getStatusCode());
            accountBean.setResponse(bean.getResponse());
            accountBean.setAvailableMiles(bean.getAvailableMiles());
            accountBean.setMemberSince(bean.getMemberSince());
            if (bean.getMillesDetail() != null
                    && bean.getMillesDetail().length > 0) {
                ArrayList<MillesDetailBean> milles = new ArrayList<MillesDetailBean>();
                MilleDetail millesBean = null;
                for (int i = 0; i < bean.getMillesDetail().length; i++) {

                    millesBean = bean.getMillesDetail()[i];

                    milles.add(this.getUserMillesBean(millesBean));


                }
                accountBean.setMillesDetail(milles);
            }

        }
        return accountBean;
    }

    public IcaroUserBean agencyLogin(String cardNumber, String password)
            throws IcaroServiceException {

        IcaroUserBean resp = null;
        String methodCalled = "agencyLogin";
        if (agency == null) {
            throw new IcaroServiceException(BaseHelper
                    .getApplicationBundleMessage(
                            "error.webService.client.interface", new String[] {
                                    AgencyServicePortType.class.toString(),
                                    AgencyServiceLocator.class.toString() }));
        }

        try {
//            String sessionId = this.getRequest().getSession().getId();
            // invocar al servicio
            IcaroUser result = this.agency.agencyLogin(cardNumber, password);
            // convertir a userBean
            resp = this.getIcaroUserBean(result);

                } catch (Exception e) {
            logger.error(e.getMessage().concat(this.endPointAddress), e);
            if (resp == null) {
                String[] arguments = new String[] {
                        this.getService().getServiceName().getNamespaceURI()
                                .concat(".").concat(methodCalled),
                        e.getMessage(), e.toString() };
                IcaroServiceMessageBean message = new IcaroServiceMessageBean(
                        Constants.FALSE_BIG_INT, BaseHelper
                                .getApplicationBundleMessage(
                                        "error.webService.client.callFailed",
                                        arguments));
                resp = new IcaroUserBean(Constants.FALSE_BIG_INT, message
                        .getResponse());
            }
        } finally {
            // auditar servicio
            // this.auditWebServiceCall(resp, methodCalled);
        }

        return resp;
    }

    public AgencyAccountSummaryBean getAgencyAccountSummary(String cardNumber)
            throws IcaroServiceException {

        AgencyAccountSummaryBean resp = null;
        String methodCalled = "getagencyAccountSummary";
        if (agency == null) {
            throw new IcaroServiceException(BaseHelper
                    .getApplicationBundleMessage(
                            "error.webService.client.interface", new String[] {
                                    AgencyServicePortType.class.toString(),
                                    AgencyServiceLocator.class.toString() }));
        }

        try {

            // invocar al servicio
            AgencyAccountSummary result = this.agency
                    .getagencyAccountSummary(cardNumber);
            // convertir a userBean
            resp = this.getAgencyAccountSummaryBean(result);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (resp == null) {
                String[] arguments = new String[] {
                        this.getService().getServiceName().getNamespaceURI()
                                .concat(".").concat(methodCalled),
                        e.getMessage(), e.toString() };
                IcaroServiceMessageBean message = new IcaroServiceMessageBean(
                        Constants.FALSE_BIG_INT, BaseHelper
                                .getApplicationBundleMessage(
                                        "error.webService.client.callFailed",
                                        arguments));
                resp = new AgencyAccountSummaryBean(Constants.FALSE_BIG_INT,
                        message.getResponse());
            }
        } finally {
            // auditar servicio
            // this.auditWebServiceCall(resp, methodCalled);

        }

        return resp;
    }

    public IcaroServiceMessageBean getAgencyAccountDetail(String cardNumber)
            throws IcaroServiceException {

        IcaroServiceMessageBean resp = null;
        String methodCalled = "getagencyAccountDetail";
        if (agency == null) {
            throw new IcaroServiceException(BaseHelper
                    .getApplicationBundleMessage(
                            "error.webService.client.interface", new String[] {
                                    AgencyServicePortType.class.toString(),
                                    AgencyServiceLocator.class.toString() }));
        }

        try {

            // invocar al servicio
            IcaroServiceMessage result = this.agency
                    .getagencyAccountDetail(cardNumber);
            // convertir a userBean
            resp = this.getIcaroServiceMessageBean(result);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (resp == null) {
                String[] arguments = new String[] {
                        this.getService().getServiceName().getNamespaceURI()
                                .concat(".").concat(methodCalled),
                        e.getMessage(), e.toString() };
                IcaroServiceMessageBean message = new IcaroServiceMessageBean(
                        Constants.FALSE_BIG_INT, BaseHelper
                                .getApplicationBundleMessage(
                                        "error.webService.client.callFailed",
                                        arguments));
                resp = new AgencyAccountSummaryBean(Constants.FALSE_BIG_INT,
                        message.getResponse());
            }
        } finally {
            // auditar servicio
            // this.auditWebServiceCall(resp, methodCalled);

        }

        return resp;
    }

    public IcaroServiceMessageBean updateAgencyAccount(ServiceAccountForm form)
            throws IcaroServiceException {

        IcaroServiceMessageBean resp = null;
        String methodCalled = "actualizaEmpresa";
        if (agency == null) {
            throw new IcaroServiceException(BaseHelper
                    .getApplicationBundleMessage(
                            "error.webService.client.interface", new String[] {
                                    AgencyServicePortType.class.toString(),
                                    AgencyServiceLocator.class.toString() }));
        }

        try {

            // invocar al servicio
            IcaroServiceMessage result = this.agency.updateagencyAccount(form
                    .getCardNumber(), form.getETicketNumber(), form
                    .getRecordLocator(), form.getSegments(), form.getTotal());
            // convertir a userBean
            resp = this.getMessageBean(result);
            // user.registrarUsuario()
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (resp == null) {
                String[] arguments = new String[] {
                        this.getService().getServiceName().getNamespaceURI()
                                .concat(".").concat(methodCalled),
                        e.getMessage(), e.toString() };
                IcaroServiceMessageBean message = new IcaroServiceMessageBean(Constants.FALSE_BIG_INT,
                        BaseHelper
                                .getApplicationBundleMessage(
                                        "error.webService.client.callFailed",
                                        arguments));
                resp = message;
            }
        }

        return resp;
    }



    public static void main(String[] args) {
        try {

            AgencyServiceManager manager = new AgencyServiceManager();
            IcaroUserBean client=null;
            client = manager.agencyLogin("5450512470","5405");

            System.out.println(client.getCardNumber());
            System.out.println(client.getName());
            System.out.println(client.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
