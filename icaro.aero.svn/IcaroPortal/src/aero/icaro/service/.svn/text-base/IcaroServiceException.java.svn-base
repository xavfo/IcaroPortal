/*
 * Created on May 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;

/**
 * Si ocurre un error al momento de llamar a un servicio Web de 
 * Fybeca
 * @author hleon
 * @version 1.0
 *
 */
public class IcaroServiceException extends Exception {
	
	/**
     * 
     */
    private static final long serialVersionUID = 5626170825717328999L;
    private String serviceName;
	
	
	
	/**
	 * Contruye una excepci�n de tiempo de ejecuci�n de una llamado a
	 * un WebService con mensaje de error, nombre del servicio llamado
	 * donde se produjo el error y excepci�n causa del error
	 * @param message mensaje de error
	 * @param name nombre del servicio llamado donde se produjo el error
	 * @param cause causa original del error
	 * 
	 */
	public IcaroServiceException(String message, String name, Throwable cause) {
		super(message, cause);
		this.setServiceName(name);
	}
	/**
	 * Contruye una excepci�n de tiempo de ejecuci�n de una llamado a
	 * un WebService con objeto causante de error.
	 * @param cause causa original del error
	 */
	public IcaroServiceException(Throwable cause) {
		super(cause);
	}
	/**
	 * Contruye una excepci�n de tiempo de ejecuci�n de una llamado a
	 * un WebService con mensaje de error y nombre del servicio llamado
	 * donde se produjo el error.
	 * 
	 * @param message mensaje de error
	 * @param name nombre del servicio llamado donde se produjo el error
	 */
	public IcaroServiceException(String message, String name) {
		super(message);
		this.setServiceName(name);
	}

	/**
	 * Contruye una excepci�n de tiempo de ejecuci�n de una llamado a
	 * un WebService con mensaje de error.
	 * 
	 * @param message mensaje de error
	 */
	public IcaroServiceException(String message) {
		super(message);
	}

	/**
	 * Devuelve el nombre del servicio ejecutado cuando se produjo el error.
	 * @return Devuelve el nombre del servicio ejecutado cuando se produjo el error.
	 */
	public String getServiceName() {
		return serviceName;
	}
	
	/**
	 * Setea el nombre del servicio ejecutado cuando se produjo el error.
	 * @param nombre del servicio ejecutado cuando se produjo el error.
	 */
	protected void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
