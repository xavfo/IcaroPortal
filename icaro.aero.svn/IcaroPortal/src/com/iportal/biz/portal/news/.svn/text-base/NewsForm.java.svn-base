/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal.news;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

public class NewsForm extends BaseForm {

	private static final long serialVersionUID = -635403064230964844L;
	
	private Long newsCode;
	
	private String title;
	
	private String leadingText;
	
	private String text;
	
	private String keyWord;
	
	private String fromDate;
		
	private String toDate;
	
	private Boolean typeFind;
	
	private Integer dayCode;
	private Integer monthCode;
	private Integer yearCode;
	
	private Integer dayCodeTO;
	private Integer monthCodeTO;
	private Integer yearCodeTO;
	
	private Boolean verify;
	private Boolean activeOnly;
	
	private Long categoryCode;
	
	public NewsForm() {
		super();
	}
	
	  public void reset(ActionMapping mapping, HttpServletRequest request) {
	       
		  	super.reset(mapping, request);
		  	this.newsCode=null;
		  	this.title = null;
	        this.leadingText=null;
	        this.text=null;
	        this.keyWord = null;
	        this.fromDate = null; 
			this.toDate = null; 
			this.typeFind = Globals.TRUE;
			this.verify = Globals.FALSE;
			this.categoryCode = null;
	  }
	  
	  
	  /**
		 * @return Returns the title.
		 */
		public String getTitle() {
			return title;
		}
		
		/**
		 * @param title The title to set.
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		
		
		  public Calendar getFrom() {
		    	
		    	Calendar calendar = new GregorianCalendar();
		    	
				if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
					
					calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT) );
					return calendar;
				}
				return null;
				
		    	
			}
		  
		  public void setFrom(Calendar calendar) {
				if ( calendar != null )
					
					this.fromDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
			}
		  
		  
		  public String getFromDate() {
				return fromDate;
			}
		  
		  public void setFromDate(String fromDate) {
				this.fromDate = fromDate;
			}
		  
		
		  public Calendar getTo() {
		    	Calendar calendar = new GregorianCalendar();
		    	
				if ( this.getToDate() != null && this.getToDate().length() > 0  ) { 
					calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT) );
					return calendar;
				}
				return null;
				
			}
		  
		  public void setTo(Calendar calendar) {
				if ( calendar != null )
					this.toDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
			}
		  
		  public String getToDate() {
				return toDate;
			}
		  public void setToDate(String toDate) {
				this.toDate = toDate;
			}
		

	

		public String getKeyWord() {
			return keyWord;
		}


		public void setKeyWord(String keyWord) {
			this.keyWord = keyWord;
		}


		




		public String getText() {
			return text;
		}


		public void setText(String text) {
			this.text = text;
		}


	

		public Integer getDayCode() {
			return dayCode;
		}


		public void setDayCode(Integer dayCode) {
			this.dayCode = dayCode;
		}


		public Integer getMonthCode() {
			return monthCode;
		}


		public void setMonthCode(Integer monthCode) {
			this.monthCode = monthCode;
		}


		public Integer getYearCode() {
			return yearCode;
		}


		public void setYearCode(Integer yearCode) {
			this.yearCode = yearCode;
		}


		public Integer getDayCodeTO() {
			return dayCodeTO;
		}


		public void setDayCodeTO(Integer dayCodeTO) {
			this.dayCodeTO = dayCodeTO;
		}


		public Integer getMonthCodeTO() {
			return monthCodeTO;
		}


		public void setMonthCodeTO(Integer monthCodeTO) {
			this.monthCodeTO = monthCodeTO;
		}


		public Integer getYearCodeTO() {
			return yearCodeTO;
		}


		public void setYearCodeTO(Integer yearCodeTO) {
			this.yearCodeTO = yearCodeTO;
		}


		public Boolean getTypeFind() {
			return typeFind;
		}


		public void setTypeFind(Boolean typeFind) {
			this.typeFind = typeFind;
		}


		public String getLeadingText() {
			return leadingText;
		}


		public void setLeadingText(String leadingText) {
			this.leadingText = leadingText;
		}


		/*public Long getCode() {
			return code;
		}


		public void setCode(Long code) {
			this.code = code;
		}*/
		
		public Boolean getVerify() {
			return verify;
		}


		public void setVerify(Boolean verify) {
			this.verify = verify;
		}


		public Boolean getActiveOnly() {
			return activeOnly;
		}
		
		public void setActiveOnly(Boolean activeOnly) {
			this.activeOnly = activeOnly;
		}


		public Long getCategoryCode() {
			return categoryCode;
		}


		public void setCategoryCode(Long categoryCode) {
			this.categoryCode = categoryCode;
		}

		/**
		 * @return Returns the newsCode.
		 */
		public Long getNewsCode() {
			return newsCode;
		}

		/**
		 * @param newsCode The newsCode to set.
		 */
		public void setNewsCode(Long newsCode) {
			this.newsCode = newsCode;
		}
	
	}

