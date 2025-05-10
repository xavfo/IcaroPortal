/**
 * 
 */
package com.iportal.cart.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author hleon
 *
 */
public class CartForm extends BaseForm {
	
    private static final long serialVersionUID = 7448088250633981338L;

    private Integer[] quantities;

    private Long[] deleteCodes;
    
    /**
     * Creates a new instance of CartForm
     */
    public CartForm() {
        super();
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);        
        quantities = null;
        deleteCodes = new Long[0];
    }

	/**
	 * @return Returns the deleteCodes.
	 */
	public Long[] getDeleteCodes() {
		return deleteCodes;
	}

    /**
	 * @param deleteCodes The deleteCodes to set.
	 */
	public void setDeleteCodes(Long[] deleteCodes) {
		this.deleteCodes = deleteCodes;
	}


	/**
	 * @return Returns the quantities.
	 */
	public Integer[] getQuantities() {
		return quantities;
	}
	
    /**
	 * @param quantities The quantities to set.
	 */
	public void setQuantities(Integer[] quantities) {
		this.quantities = quantities;
	}
    
}
