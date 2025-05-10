package com.iportal.ctrl.system.ldap;

import com.iportal.Constants;


public class LdapUserBean {
   
   private Long code;
   private String username;
   private String firstName;
   private String lastName;
   private String email;
   private Integer registered;
   private Integer status;
   private String statusLabel;
   private String registeredLabel;
   
   
    /**
     * @return Returns the registered.
     */
    public Integer getRegistered() {
        return registered;
    }
    /**
     * @param registered The registered to set.
     */
    public void setRegistered(Integer registered) {
        this.registered = registered;
    }
    /**
     * @return Returns the status.
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return Returns the statusLabel.
     */
    /**
     * @return Returns the statusLabel.
     */
    public String getStatusLabel() {
        if (status.equals(Constants.LDAP_YES)){
            statusLabel = Constants.LDAP_ENABLED_LABEL;
        }else if (status.equals(Constants.LDAP_NO)){
            statusLabel = Constants.LDAP_DISABLED_LABEL;
        }else {
            statusLabel = Constants.LDAP_N_A_LABEL;
        }
        return statusLabel;
    }

    /**
     * @param statusLabel The statusLabel to set.
     */
    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }
    /**
     * @return Returns the registeredLabel.
     */
    public String getRegisteredLabel() {
        if (registered.equals(Constants.LDAP_YES)){
            registeredLabel = Constants.LDAP_YES_LABEL;
        }else {
            registeredLabel = Constants.LDAP_NO_LABEL;
        }
        return registeredLabel;
    }
    /**
     * @param registeredLabel The registeredLabel to set.
     */
    public void setRegisteredLabel(String registeredLabel) {
        this.registeredLabel = registeredLabel;
    }
    /**
     * @return Returns the code.
     */
    public Long getCode() {
        return code;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
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
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
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
   
}
