/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal.event;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

public class EventForm extends BaseForm {

	private static final long serialVersionUID = -635403064230964844L;
	
	private String title;
	
	private String keyWord;
	
	private String fromDate;
		
	private String toDate;
	
	private Integer enabledInt;
	
	private Boolean enabled;
	
	private Long categoryCode;
	
	private String leadingImageName;
	
	private Long imageCode;
	
	private Long leadingImageCode;

	private String imageName;
	
	 private Long languageCode;
	 
	 private Long menuItem;
	 
	 private String schedule;
	 
	 private Boolean isEvent;
	 
	 private Long cityCode;
	 
	 private Boolean listEvent;
	
	
	
	  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	       
	        this.title = null;
	        this.keyWord = null;
	        this.fromDate = null; 
			this.toDate = null; 
			this.enabledInt = new Integer(2);
			this.enabled = Globals.TRUE;
			this.categoryCode = null;
			this.leadingImageName = null;
			this.imageCode = null;
			this.leadingImageCode = null;
			this.imageName = null; 
			this.languageCode = null;	
			this.menuItem=null;
			this.schedule = null; 
			this.isEvent = null;
			this.listEvent=Globals.FALSE;
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
		  
		  public Integer getEnabledInt() {
				return enabledInt;
			}
		  public void setEnabledInt(Integer enabledInt) {
				this.enabledInt = enabledInt;
			}
		  public Boolean getEnabled() {
				return enabled;
			}
		  
		  public void setEnabled(Boolean enabled) {
				this.enabled = enabled;
			}



		public Long getCategoryCode() {
			return categoryCode;
		}



		public void setCategoryCode(Long categoryCode) {
			this.categoryCode = categoryCode;
		}



		public Long getImageCode() {
			return imageCode;
		}



		public void setImageCode(Long imageCode) {
			this.imageCode = imageCode;
		}



		public String getImageName() {
			return imageName;
		}



		public void setImageName(String imageName) {
			this.imageName = imageName;
		}



		public Long getLanguageCode() {
			return languageCode;
		}



		public void setLanguageCode(Long languageCode) {
			this.languageCode = languageCode;
		}



		public Long getLeadingImageCode() {
			return leadingImageCode;
		}



		public void setLeadingImageCode(Long leadingImageCode) {
			this.leadingImageCode = leadingImageCode;
		}



		public String getLeadingImageName() {
			return leadingImageName;
		}



		public void setLeadingImageName(String leadingImageName) {
			this.leadingImageName = leadingImageName;
		}


	/**
	 * @return Returns the keyWord.
	 */
	public String getKeyWord() {
		return keyWord;
	}
	/**
	 * @param keyWord The keyWord to set.
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public Long getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(Long menuItem) {
		this.menuItem = menuItem;
	}



	public String getSchedule() {
		return schedule;
	}



	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}



	public Boolean getIsEvent() {
		return isEvent;
	}



	public void setIsEvent(Boolean isEvent) {
		this.isEvent = isEvent;
	}



	public Long getCityCode() {
		return cityCode;
	}



	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}



	public Boolean getListEvent() {
		return listEvent;
	}



	public void setListEvent(Boolean listEvent) {
		this.listEvent = listEvent;
	}
}

