/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.yage.Globals;

/**
 *
 *
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class UploadForm extends PopUpForm {

    private static final long serialVersionUID = 9001716275256422448L;

    private FormFile file;

    private String path;

    private String basePath;

    private String name;

    private String prefix; //Prefijo para renombrar im�genes

    private String sequence; //Secuencia para nombrar imagen o archivo




    /**
     * Crea una nueva instnacia de UploadForm
     */
    public UploadForm() {
        super();
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        file = null;
        path = Globals.DEFAULT_BASE_PATH;
        basePath = null;
        name = null;
        prefix = null;
        sequence = null;
    }

    public FormFile getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSequence() {
        return sequence;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
