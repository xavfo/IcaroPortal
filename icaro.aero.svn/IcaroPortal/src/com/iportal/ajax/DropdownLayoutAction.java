package com.iportal.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.ItemBean;
import com.iportal.model.Language;

public class DropdownLayoutAction extends BaseAjaxAction {

	private static Log logger = LogFactory.getLog(DropdownLayoutAction.class);

	  /**
	   * Genera el esquema con los datos para el XLM dinamico
	   * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest,
	   *      javax.servlet.http.HttpServletResponse)
	   */
	  public String getXmlContent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		      HttpServletResponse response)
	      throws Exception {
		  
		List list = getList((BaseAjaxForm)form, request);
	    return new AjaxXmlBuilder().addItems(list, "name", "code").toString();
	  }
	  
	  /**
	   * Lista las ciudades segun los estados o provincia. 
	   * Toma el codigo de <code>code</code> del Objeto <code>BaseAjaxForm</code>
	   * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest)
	   */
	@SuppressWarnings("unchecked")
	public List<ItemBean> getList(BaseAjaxForm form, HttpServletRequest request) {

		Session sess = null;
      List<ItemBean> results = null;
      List<ItemBean> resultsFinal = null;

      try {
  		sess = this.getHibernateSession();

  		Language language = (Language)request.getSession()
		.getAttribute(Constants.LANGUAGE_KEY);
  		
  		
  		StringBuffer sql = new StringBuffer();

  		sql.append("SELECT new com.iportal.biz.ItemBean(layoutLang.layout.code, layoutLang.name) ");
    	sql.append("FROM LayoutLanguage as layoutLang ");
    	sql.append("WHERE layoutLang.language.code = ? ");
    	sql.append("AND layoutLang.layout.portalModule.code = ? ");
    	
    	Query query = sess.createQuery(sql.toString());
		query.setLong(0, language.getCode());
		query.setLong(1, form.getCode());

		results = query.list();
	    
		//Agrega un registro si la consulta no trae registro.
		resultsFinal = new ArrayList<ItemBean>();
	    resultsFinal.add(new ItemBean());
	    resultsFinal.addAll(1,results);
  		
      } catch (Exception e) {
          logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		return resultsFinal;
	}

}
