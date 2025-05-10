/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;

import java.math.BigInteger;
import java.util.ArrayList;


/**
 * @author ftamayo
 * @version 1.0
 * 
 * Objeto tipo envoltura (wrapper) para objeto remoto
 * @see aero.icaro.service.cargo.stub.IcaroDoc
 * 
 */
public class IcaroDocBean extends IcaroServiceMessageBean {

	private String estadoDoc;

	private String estadoDocDesc;

	private ArrayList<IcaroPaqBean> paquetes;
	
	private String numberDoc;

	

	public void clear() {
		this.estadoDoc = null;
		this.estadoDocDesc = null;
		if (this.paquetes != null) {
			this.paquetes.clear();
			this.paquetes = null;
		}

		this.numberDoc=null;
	}

	public IcaroDocBean() {
		super();
		this.clear();
	}
	
	/**
	 * @param statusCode
	 * @param response
	 */
	public IcaroDocBean(BigInteger statusCode, String response) {
		super(statusCode, response);
		this.clear();
	}

	/**
	 * @return Returns the estadoDoc.
	 */
	public String getEstadoDoc() {
		return estadoDoc;
	}

	/**
	 * @param estadoDoc The estadoDoc to set.
	 */
	public void setEstadoDoc(String estadoDoc) {
		this.estadoDoc = estadoDoc;
	}

	/**
	 * @return Returns the estadoDocDesc.
	 */
	public String getEstadoDocDesc() {
		return estadoDocDesc;
	}

	/**
	 * @param estadoDocDesc The estadoDocDesc to set.
	 */
	public void setEstadoDocDesc(String estadoDocDesc) {
		this.estadoDocDesc = estadoDocDesc;
	}

	/**
	 * @return Returns the paquetes.
	 */
	public ArrayList<IcaroPaqBean> getPaquetes() {
		return paquetes;
	}

	/**
	 * @param paquetes The paquetes to set.
	 */
	public void setPaquetes(ArrayList<IcaroPaqBean> paquetes) {
		this.paquetes = paquetes;
	}

	/**
	 * @return Returns the numberDoc.
	 */
	public String getNumberDoc() {
		return numberDoc;
	}

	/**
	 * @param numberDoc The numberDoc to set.
	 */
	public void setNumberDoc(String numberDoc) {
		this.numberDoc = numberDoc;
	}



}
