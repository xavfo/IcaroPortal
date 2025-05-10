/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service.cargo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import aero.icaro.service.IcaroDocBean;
import aero.icaro.service.IcaroPaqBean;
import aero.icaro.service.IcaroServiceException;
import aero.icaro.service.IcaroServiceManager;
import aero.icaro.service.IcaroServiceMessageBean;
import aero.icaro.service.cargo.stub.IcargoServiceLocator;
import aero.icaro.service.cargo.stub.IcargoServicePortType;
import aero.icaro.service.cargo.stub.IcaroDoc;
import aero.icaro.service.cargo.stub.IcaroPaq;
import aero.icaro.service.skies.stub.IcaroServiceMessage;
import aero.icaro.service.skies.stub.SkiesServiceLocator;
import aero.icaro.service.skies.stub.SkiesServicePortType;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;

/**
 * @author ftamayo
 * @version 1.0
 * 
 * Implementacion de cliente para llamadas a servicios Web de Icaro
 */
public class IcargoServiceManager extends IcaroServiceManager {

	private static Log logger = LogFactory.getLog(IcargoServiceManager.class);

	private IcargoServicePortType cargo;

	private String endPointAddress;

	/**
	 * Crea un nuevo objeto manejador de clientes
	 * 
	 * @throws ServiceException
	 */
	public IcargoServiceManager() throws ServiceException {
		super();
		this.clear();
		this.reset();
	}

	/**
	 * Crea un nuevo objeto manejador de clientes
	 * 
	 * @param module
	 *            módulo desde donde se crea al manejador de usuarios
	 */
	public IcargoServiceManager(Object module) throws ServiceException {
		super(module);
		this.clear();
		this.reset();

	}

	/**
	 * Crea un nuevo objeto manejador de clientes
	 * 
	 * @param module
	 *            módulo desde donde se crea al manejador de usuarios
	 * @param request
	 *            petición HTTP desde donde se llama al manejador
	 * @throws ServiceException
	 */
	public IcargoServiceManager(Object module, HttpServletRequest request)
			throws ServiceException {
		super(module, request);
		this.clear();
		this.reset();
	}

	/**
	 * Elimina y reinicia al objeto
	 */
	private void clear() {
		this.cargo = null;
		this.endPointAddress = null;
	}

	/**
	 * Resetea al manejador para invocar a los servicios Web de la interfaz de
	 * la cual se encarga de invocar este manejador. Crea un Servicelocator para
	 * la interfaz y direcciona la petición a la direción de endpoint por
	 * defecto definida en
	 * 
	 * @link Constants.DEFAULT_SERVICE_ENDPOINT
	 * 
	 * @throws ServiceException
	 */
	protected void reset() throws ServiceException {
		IcargoServiceLocator locator = new IcargoServiceLocator();
		String address = StringUtils.replaceOnce(locator
				.getIcargoServicePortAddress(), "icaro.aero/serviciosweb/",
				Constants.DEFAULT_SERVICE_ENDPOINT);
		// setea la nueva direccion como dedault EndPoint
		// String address1= "http://19.168.0.1/services/Usuario";
		locator.setIcargoServicePortEndpointAddress(address);
		this.endPointAddress = " Endpoint: ".concat(address);
		// logger.info(address);
		this.setService(locator);
		cargo = locator.getIcargoServicePort();
	}

	/**
	 * Convierte un objeto IcaroPaq en IcaroPaqBean
	 * 
	 * @param bean
	 *            objeto IcaroPaq
	 * @return devuelve un IcaroPaqBean iniciado con las propiedades del
	 *         parametro bean
	 */
	protected IcaroPaqBean getIcaroPaqBean(IcaroPaq bean) {
		IcaroPaqBean paqBean = null;
		if (bean != null) {
			paqBean = new IcaroPaqBean();
			// Datos Llamada a WebService
			paqBean.setIdPaq(bean.getIdPaq());
			paqBean.setEstadoPaq(bean.getEstadoPaq());
			paqBean.setEstadoPaqDesc(bean.getEstadoPaqDesc());
			

		}
		return paqBean;
	}

	/**
	 * Convierte un objeto IcaroDoc en IcaroDocBean
	 * 
	 * @param bean
	 *            objeto IcaroDoc
	 * @return devuelve un IcaroDocBean iniciado con las propiedades del
	 *         parametro bean
	 */
	protected IcaroDocBean getIcaroDocBean(IcaroDoc bean) {
		IcaroDocBean docBean = null;
		if (bean != null) {
			docBean = new IcaroDocBean();
			// Datos Llamada a WebService
			docBean.setStatusCode(bean.getStatusCode());
			docBean.setResponse(bean.getResponse());
			docBean.setEstadoDoc(bean.getEstadoDoc());
			docBean.setEstadoDocDesc(bean.getEstadoDocDesc());
			if (bean.getPaquetes() != null
					&& bean.getPaquetes().length > 0) {
				ArrayList<IcaroPaqBean> paquetes = new ArrayList<IcaroPaqBean>();
				IcaroPaq paquetesBean = null;
				for (int i = 0; i < bean.getPaquetes().length; i++) {

					paquetesBean = bean.getPaquetes()[i];

					paquetes.add(this.getIcaroPaqBean(paquetesBean));
					

				}
				docBean.setPaquetes(paquetes);
			}

		}
		return docBean;
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

	
	public IcaroDocBean icargoEstado(String docType, String docNumber)
			throws IcaroServiceException {

		IcaroDocBean resp = null;
		String methodCalled = "icargoEstado";
		if (cargo == null) {
			throw new IcaroServiceException(BaseHelper
					.getApplicationBundleMessage(
							"error.webService.client.interface", new String[] {
									SkiesServicePortType.class.toString(),
									SkiesServiceLocator.class.toString() }));
		}


		try {
			//String sessionId = this.getRequest().getSession().getId();
			// invocar al servicio
			IcaroDoc result = this.cargo.icargoEstado(docType, docNumber);
			// convertir a userBean
			resp = this.getIcaroDocBean(result);

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
				resp = new IcaroDocBean(Constants.FALSE_BIG_INT, message
						.getResponse());
			}
		} finally {
			// auditar servicio
			// this.auditWebServiceCall(resp, methodCalled);
		}

		return resp;
	}

}
