/**
 * 
 */
package com.iportal.biz.worldspan;

import java.util.ArrayList;
import java.util.List;

import com.yage.commons.StringBean;

/**
 * @author hernan
 *
 */
public class BookAndPriceResponse extends WorldspanResponse {
	
	public BookAndPriceResponse() {
		super();
		this.clear();
	}

	
	private String errorMessage;
	
	private Integer variableCount;//VARIABLE_COUNT 
	private String endOption;	//END_OPTION- Echoed from BPC request.
	private String recordLocator; //PNR_RLOC PNR Record Locator
	/**Customer Reference Number.Note 2: CUST-REF-NUM only checks the first 11 characters, but a character length up to the TPF limit can be entered*/
	private String customerReferenceNumber;//CUST_REF_NUM 
	/**Valid Values: “P”=requires passport information. “C”=contact information is required. “B”=both passport and contact information are required Information is passed on at ET. "I"=NEED SSRINFT FOR EACH INFANT SEGMENT*/
	private String passportContactRequired;//PASSPORT_CONTACT_REQ 
	/**QUEUE_STATUS_INFO*/ 
	private List<StringBean> sSRStatus;
	
	private List<StringBean> pricingInfoLines;
	
	private List<BookSegment> segments;
	
	/**If CK_TIME_PRICE_DIF was requested in BPC, any differences will result in booking being IGNORED and TIME_PRIC_DIF will be set accordingly. Valid Values: Not returned=No difference detected or no checking requested. “T”=Time difference detected. “P”=Price difference detected. “B”=Time and Price difference detected. "V"=Price variance detected, PRICE_CK_AMOUNT + PRICE_CK_VAR less than or equal to PNR price, PNR created. "L"=Lower price detected, PNR price less than PRICE_CK_AMOUNT, PNR created.*/
	private String timePriceDifference; //TIME_PRICE_DIF
	
	/**Valid Values: ttttdddd; tttt=hours: 00-23 and minutes: 01-59; dddd=month: 01-12 and day: 01-31*/
	private String ticketTimeLimit;//TICKET_TIME_LIMIT
	
	/**Status of TICKETLESS_IND Y = Yes or Blank*/
	private String ticketlessIndicator; //TICKETLESS_IND
	
	/**Status of T = invalid consolidator remark*/
	private String invalidConsolidator;//CONSOLIDATOR_IND
	
	private PricingInfo pricingInfo;

	
	public void clear () {
		if (this.sSRStatus != null) {
			this.sSRStatus.clear();
		}
		if (this.pricingInfoLines != null) {
			this.pricingInfoLines.clear();
		}
		if (this.pricingInfo != null) {
			this.pricingInfo.clear();
		}
		this.pricingInfo = null;
		if (this.segments != null) {
			this.segments.clear();
		}
		this.segments = null; // new ArrayList<BookSegment> ();
		this.sSRStatus =  new ArrayList<StringBean> ();
		this.pricingInfoLines = new ArrayList<StringBean> ();
		this.variableCount = null; 
		this.endOption = null;
		this.recordLocator = null;
		this.customerReferenceNumber = null; 
		this.passportContactRequired = null; 
		this.timePriceDifference = null;
		this.ticketTimeLimit = null;
		this.ticketlessIndicator = null;
		this.invalidConsolidator = null;
		this.errorMessage = null;

	}
	
	
	
	public void addPricingInfoLine (StringBean infoLine) {
		this.pricingInfoLines.add(infoLine);
	}

	public void addSSRStatus (StringBean status) {
		this.sSRStatus.add(status);
	}

	public void addSegment (BookSegment segment) {
		if (this.segments != null) {
			this.segments.add(segment);	
		}
		
	}

	public void setVariableCount(Integer variableCount) {
		this.variableCount = variableCount;
		if (this.segments != null) {
			this.segments.clear();
			this.segments =null;
		}
		this.segments = new ArrayList<BookSegment> (variableCount);

	}
	
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getCustomerReferenceNumber() {
		return customerReferenceNumber;
	}

	public String getEndOption() {
		return endOption;
	}

	public String getInvalidConsolidator() {
		return invalidConsolidator;
	}

	public String getPassportContactRequired() {
		return passportContactRequired;
	}

	public PricingInfo getPricingInfo() {
		return pricingInfo;
	}

	public String getRecordLocator() {
		return recordLocator;
	}

	public List<BookSegment> getSegments() {
		return segments;
	}

	public List<StringBean> getSSRStatus() {
		return sSRStatus;
	}

	public String getTicketlessIndicator() {
		return ticketlessIndicator;
	}

	public String getTicketTimeLimit() {
		return ticketTimeLimit;
	}

	public String getTimePriceDifference() {
		return timePriceDifference;
	}

	public Integer getVariableCount() {
		return variableCount;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setCustomerReferenceNumber(String customerReferenceNumber) {
		this.customerReferenceNumber = customerReferenceNumber;
	}

	public void setEndOption(String endOption) {
		this.endOption = endOption;
	}

	public void setInvalidConsolidator(String invalidConsolidator) {
		this.invalidConsolidator = invalidConsolidator;
	}

	public void setPassportContactRequired(String passportContactRequired) {
		this.passportContactRequired = passportContactRequired;
	}

	public void setPricingInfo(PricingInfo pricingInfo) {
		this.pricingInfo = pricingInfo;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public void setSegments(List<BookSegment> segments) {
		this.segments = segments;
	}

	public void setSSRStatus(List<StringBean> status) {
		sSRStatus = status;
	}

	public void setTicketlessIndicator(String ticketlessIndicator) {
		this.ticketlessIndicator = ticketlessIndicator;
	}

	public void setTicketTimeLimit(String ticketTimeLimit) {
		this.ticketTimeLimit = ticketTimeLimit;
	}

	public void setTimePriceDifference(String timePriceDifference) {
		this.timePriceDifference = timePriceDifference;
	}
	
	

	
	

}
