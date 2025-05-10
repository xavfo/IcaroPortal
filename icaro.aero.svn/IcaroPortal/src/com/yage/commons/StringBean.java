/**
 * 
 */
package com.yage.commons;

/**
 * Clase para menejar datos de un string como un bean
 * @author hernan
 *
 */
public class StringBean {
	
	private String value;
	
    public StringBean() {
        super();
        this.value = null;
    }

    public StringBean(String value) {
        super();
        this.value = value;
    }

	
    @Override
    public String toString() {
        return this.value!= null?this.value:super.toString();
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
    


}
