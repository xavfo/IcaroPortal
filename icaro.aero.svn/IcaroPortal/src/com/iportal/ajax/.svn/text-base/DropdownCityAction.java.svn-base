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

import com.iportal.biz.RowItem;

public class DropdownCityAction extends BaseAjaxAction {
	
	private static Log logger = LogFactory.getLog(DropdownZoneAction.class);

	  /**
	   * Genera el esquema con los datos para el XLM dinamico
	   * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest,
	   *      javax.servlet.http.HttpServletResponse)
	   */
	  public String getXmlContent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		      HttpServletResponse response)
	      throws Exception {
		  
		List list = getList((BaseAjaxForm)form);
	    return new AjaxXmlBuilder().addItems(list, "name", "code").toString();
	  }
	  
	  /**
	   * Lista las ciudades segun los estados o provincia. 
	   * Toma el codigo de <code>code</code> del Objeto <code>BaseAjaxForm</code>
	   * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest)
	   */
	@SuppressWarnings("unchecked")
	public List<RowItem> getList(BaseAjaxForm form) {

		Session sess = null;
        List<RowItem> results = null;
        List<RowItem> resultsFinal = null;

        try {
    		sess = this.getHibernateSession();

    		StringBuffer sql = new StringBuffer();

    		sql.append("select new com.iportal.biz.RowItem (city.code, city.name) ");
            sql.append("from City as city ");
    		sql.append("where city.state.country.code = ? ");
			sql.append("order by city.name asc");
			
			Query query = sess.createQuery(sql.toString());
			query.setLong(0, form.getCode());

			results = query.list();
	        //Agrega un registro si la consulta no trae registro.
			resultsFinal = new ArrayList<RowItem>();
	        resultsFinal.add(new RowItem());
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
