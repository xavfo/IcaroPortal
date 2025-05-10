/**
 * 
 */
package com.iportal.cart.ctrl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * @author fernandoT
 * 
 */
public class SearchPurchaseForm extends BaseForm {

	private static final long serialVersionUID = -8484601014713621244L;

	private String reserveNumber;

	private Long paymentType;

	private Long status;

	private String fromDate;

	private String toDate;

	public SearchPurchaseForm() {
		super();
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		super.reset(mapping, request);
		this.reserveNumber = null;
		this.paymentType = null;
		this.status = null;
		this.fromDate = null;
		this.toDate = null;
	}

	/**
	 * @return Returns the paymentType.
	 */
	public Long getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType The paymentType to set.
	 */
	public void setPaymentType(Long paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return Returns the reserveNumber.
	 */
	public String getReserveNumber() {
		return reserveNumber;
	}

	/**
	 * @param reserveNumber The reserveNumber to set.
	 */
	public void setReserveNumber(String reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	/**
	 * @return Returns the status.
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * @param state The status to set.
	 */
	public void setStatus(Long status) {
		this.status = status;
	}


	public Calendar getFrom() {

		Calendar calendar = new GregorianCalendar();

		if (this.getFromDate() != null && this.getFromDate().length() > 0) {

			calendar.setTime(DateFormatUtils.parseToDate(this.getFromDate(),
					Constants.DATE_FORMAT));
			return calendar;
		}
		return null;

	}

	public void setFrom(Calendar calendar) {
		if (calendar != null)

			this.fromDate = DateFormatUtils.format(calendar.getTime(),
					Constants.DATE_FORMAT);
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getTo() {
		Calendar calendar = new GregorianCalendar();

		if (this.getToDate() != null && this.getToDate().length() > 0) {
			calendar.setTime(DateFormatUtils.parseToDate(this.getToDate(),
					Constants.DATE_FORMAT));
			return calendar;
		}
		return null;

	}

	public void setTo(Calendar calendar) {
		if (calendar != null)
			this.toDate = DateFormatUtils.format(calendar.getTime(),
					Constants.DATE_FORMAT);
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
