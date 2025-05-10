/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * FormBean store temporarily the SysUser Information, and
 * this informtion is pass to front-end and persistent class. 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
  */
public class SysUserForm extends BaseForm {

	private static final long serialVersionUID = 2L;

	private String name;
    
	private String password;
	
	private String confirmPassword;
	
    private Boolean enabled;
    
    private Integer enabledOption;
    
    private Boolean application;
    
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private Long roleCode;
    
    private Boolean reset;
    
    private Long accessModeCode;
    
    
    //private Calendar registrationDate;
    private String date;
    
    /**
     * Creates a new SysUserForm
     */
    public SysUserForm() {
        super();
    }
    
    /**
	 * Valida los datos de la Forma para registrar un nuevo cliente
	 * o actualizar sus datos
	 * 
	 * Se valida que no exista otro cliente con el mismo codigo externo
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		if (this.getRoleCode() != null && this.getRoleCode().longValue() <= 0L) {
			this.setRoleCode(null);
		}
		ActionErrors errors = super.validate(mapping, request);
		
		if (this.getCode() == null || this.getCode().longValue() == 0L) {
			if (this.getPassword() == null || this.getPassword().length() == 0) {				
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.password")));
	        } else if(this.getConfirmPassword() == null || this.getConfirmPassword().length() == 0) {
	        	errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.passwordVerify")));
	        } else if (!this.getPassword().equals(this.getConfirmPassword())) {
	        	errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.twofields", BaseHelper.getApplicationBundleMessage("prompt.password"), BaseHelper.getApplicationBundleMessage("prompt.confirmPassword")));
	        }
			
        } 		

		return errors;
	}  
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;        
        enabled = Globals.TRUE;
        enabledOption   = null;
        application = Globals.FALSE;
        email = null;
        firstName = null;
        lastName = null;
        roleCode = null;
    }
    
    
    

	/**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return Returns the enabled.
     */
    public Boolean getEnabled() {
        return enabled;
    }
    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
  
    /**
     * @return Returns the roleCode.
     */
    public Long getRoleCode() {
        return roleCode;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param roleCode The roleCode to set.
     */
    public void setRoleCode(Long roleCode) {
        this.roleCode = roleCode;
    }
    /**
     * @return Returns the application.
     */
    public Boolean getApplication() {
        return application;
    }
    /**
     * @param application The application to set.
     */
    public void setApplication(Boolean application) {
        this.application = application;
    }


	/**
	 * @return Returns the accessModeCode.
	 */
	public Long getAccessModeCode() {
		return accessModeCode;
	}
	/**
	 * @param accessModeCode The accessModeCode to set.
	 */
	public void setAccessModeCode(Long accessModeCode) {
		this.accessModeCode = accessModeCode;
	}

	/**
	 * @return Returns the reset.
	 */
	public Boolean getReset() {
		return reset;
	}
	/**
	 * @param reset The reset to set.
	 */
	public void setReset(Boolean reset) {
		this.reset = reset;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns the confirmPassword.
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	/**
	 * @param confirmPassword The confirmPassword to set.
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


    /**
     * @return Returns the enabledOption.
     */
    public Integer getEnabledOption() {
        return enabledOption;
    }


    /**
     * @param enabledOption The enabledOption to set.
     */
    public void setEnabledOption(Integer enabledOption) {
        this.enabledOption = enabledOption;
    }
    
    



	/**
     * @return Returns the date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
   
    public Calendar getRegistrationDate() {
        Calendar calendar = new GregorianCalendar();
        if ( this.getDate() != null && this.getDate().length() > 0  ) {
            Date date = DateFormatUtils.parseToDate(this.getDate(),Constants.DATE_TIME_FORMAT);
            calendar.setTime( date );
            return calendar;
        }
        return null;
    }
    
    public void setRegistrationDate(Calendar calendar) {
        if ( calendar != null )
            this.date = DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
    }

}
