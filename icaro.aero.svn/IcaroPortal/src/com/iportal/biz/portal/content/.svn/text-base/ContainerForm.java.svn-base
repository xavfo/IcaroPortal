package com.iportal.biz.portal.content;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

public class ContainerForm extends BaseForm {

    private static final long serialVersionUID = 8326746998075030073L;

    //Tipo  de contenedor documento o material de soporte
    protected Long typeCode;

    //Contenido al cual se encuentran relacionados los contenedores que se desea listar
    protected Long contentCode;

    public ContainerForm() {
        super();
    }

    /* (non-Javadoc)
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.typeCode=null;
        this.contentCode = null;
    }




    /**
     * @return Returns the contentCode.
     */
    public Long getContentCode() {
        return contentCode;
    }

    /**
     * @return Returns the typeCode.
     */
    public Long getTypeCode() {
        return typeCode;
    }



    /**
     * @param contentCode The contentCode to set.
     */
    public void setContentCode(Long itemContentCode) {
        this.contentCode = itemContentCode;
    }

    /**
     * @param typeCode The typeCode to set.
     */
    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }





}
