/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service.corp;

import java.math.BigInteger;
import java.util.ArrayList;

import aero.icaro.service.IcaroServiceMessageBean;
import aero.icaro.service.MillesDetailBean;

/**
 * @author ftamayo
 * @version 1.0
 * 
 * Objeto tipo envoltura (wrapper) para objeto remoto
 * @see aero.icaro.service.corp.stub.CorpAccountSummary
 * 
 */
public class CorpAccountSummaryBean extends IcaroServiceMessageBean {

	private String reportDate;

	private String memberSince;

	private ArrayList<MillesDetailBean> millesDetail;

	private Long availableMiles;
	
	private String urlDetail;

	public void clear() {
		this.reportDate = null;
		this.memberSince = null;
		this.availableMiles = null;
		this.urlDetail=null;
		if (this.millesDetail != null) {
			this.millesDetail.clear();
			this.millesDetail = null;
		}

	}

	public CorpAccountSummaryBean() {
		super();
		this.clear();
	}

	/**
	 * @param statusCode
	 * @param response
	 */
	public CorpAccountSummaryBean(BigInteger statusCode, String response) {
		super(statusCode, response);
		this.clear();
	}

	/**
	 * @return Returns the availableMiles.
	 */
	public long getAvailableMiles() {
		return availableMiles;
	}

	/**
	 * @param availableMiles
	 *            The availableMiles to set.
	 */
	public void setAvailableMiles(long availableMiles) {
		this.availableMiles = availableMiles;
	}

	/**
	 * @return Returns the memberSince.
	 */
	public String getMemberSince() {
		return memberSince;
	}

	/**
	 * @param memberSince
	 *            The memberSince to set.
	 */
	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}

	/**
	 * @return Returns the millesDetail.
	 */
	public ArrayList<MillesDetailBean> getMillesDetail() {
		return millesDetail;
	}

	/**
	 * @param millesDetail
	 *            The millesDetail to set.
	 */
	public void setMillesDetail(ArrayList<MillesDetailBean> millesDetail) {
		this.millesDetail = millesDetail;
	}

	/**
	 * @return Returns the reportDate.
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 *            The reportDate to set.
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return Returns the urlDetail.
	 */
	public String getUrlDetail() {
		return urlDetail;
	}

	/**
	 * @param urlDetail The urlDetail to set.
	 */
	public void setUrlDetail(String urlDetail) {
		this.urlDetail = urlDetail;
	}

	/**
	 * @param availableMiles The availableMiles to set.
	 */
	public void setAvailableMiles(Long availableMiles) {
		this.availableMiles = availableMiles;
	}

}
