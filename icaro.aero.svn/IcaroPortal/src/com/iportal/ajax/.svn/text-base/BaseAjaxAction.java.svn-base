/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseAction;

/**
 * Clase abstracta que puede ser extendida por clase Action de Struts.<br>
 * @author andresg
 * @author  YAGE
 * @version 1.0
 * @see com.yage.struts.action.BaseAction
 */
public abstract class BaseAjaxAction extends BaseAction {

	  /**
	   * Genera xml dinamico para consulta asincronica
	   * @see Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
	   */
	  public final ActionForward execute(
	      ActionMapping mapping, ActionForm form, HttpServletRequest request,
	      HttpServletResponse response) throws Exception {

	    String xml = null;
	    try {
	      xml = getXmlContent(mapping, form, request, response);
	    } catch (Exception ex) {
	      // Send back a 500 error code.
	      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Can not create response");
	      return null;
	    }

	    // Set content to xml
	    response.setContentType("text/xml; charset=UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
	    PrintWriter pw = response.getWriter();
	    pw.write(xml);
	    pw.close();

	    return null;
	  }

	  /**
	   * Cada clase hijo debe eliminar este método para generar el necesario 
	   * contento específico de XML para cada acción de AJAX.
	   * @param mapping
	   * @param form
	   * @param request the {@javax.servlet.http.HttpServletRequest} object
	   * @param response the {@javax.servlet.http.HttpServletResponse} object
	   * @return a {@java.lang.String} representation of the XML response/content
	   * @throws Exception
	   */
	  public abstract String getXmlContent(
	      ActionMapping mapping, ActionForm form, HttpServletRequest request,
	      HttpServletResponse response) throws Exception;
	  
}
