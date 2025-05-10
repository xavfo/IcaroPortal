/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.office;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.ctrl.system.entity.OfficeForm;
import com.iportal.model.portal.Content;
import com.yage.Globals;

public class OfficeAction extends ContentContainerAction {

	private static Log logger = LogFactory.getLog(OfficeAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		Session sess = null;
		Query query = null;
		HttpSession session = request.getSession();
		List results = null;
		Content content = null;
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);
		sess = this.getHibernateSession();
		OfficeForm officeForm= (OfficeForm) form;

		try {

			if (portalBean != null) {
				content = (Content) sess.get(Content.class, portalBean
						.getContentCode());
				request.setAttribute("content", content);
				
			}			
			StringBuffer sql = new StringBuffer();
			sql.append(" from Office as office");
			sql.append(" where office.enabled=? ");
			sql.append(" and office.type.code=? ");
			sql.append(" order by office.city.name, office.name ");
			query = sess.createQuery(sql.toString());
			query.setBoolean(0, Globals.TRUE);
			query.setLong(1, officeForm.getTypeCode());
			results = query.list();
			request.setAttribute("officeList", results);

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

		return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_"
				+ portalBean.getLayoutForward()));
	}

	/*
	 * public ActionForward listDetail(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * CatalogueForm catalogueForm = (CatalogueForm) form;
	 * 
	 * Session sess = null; HttpSession session = request.getSession(); List
	 * results = null; MenuPortalBean portalBean = (MenuPortalBean) session
	 * .getAttribute(Constants.MENU_KEY); try { sess =
	 * this.getHibernateSession();
	 * 
	 * StringBuffer sql = new StringBuffer(); Query query = null; // Hay que
	 * tomar en cuenta que los codigos del contenido para // obtener el listado
	 * de vinos han sido quemados
	 * 
	 * sql = new StringBuffer(); sql.append(" select new com.iportal.biz.RowItem (
	 * "); sql.append(" category.code, category.name, category.image) ");
	 * sql.append(" From Category as category "); sql.append(" Where
	 * category.parent.code = ? "); sql.append(" order by category.orderIndex
	 * asc "); query = sess.createQuery(sql.toString()); if
	 * (catalogueForm.getCode() == 260) { query.setLong(0, Constants.CEPA_CODE); }
	 * if (catalogueForm.getCode() == 261) { query.setLong(0,
	 * Constants.ENSAMBLAJE_CODE); } if (catalogueForm.getCode() == 200) {
	 * query.setLong(0, Constants.DENOMINACION_CODE); } results = query.list();
	 * request.setAttribute("subCategoryList", results);
	 *  } catch (Exception e) { logger.error(e.getMessage(), e); } finally { if
	 * (sess != null) try { sess.clear(); sess.close(); } catch (Exception e) { } }
	 * 
	 * return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_" +
	 * portalBean.getLayoutForward())); }
	 * 
	 * public ActionForward list(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * CatalogueForm catalogueForm = (CatalogueForm) form; Session sess = null;
	 * HttpSession session = request.getSession(); List results = null;
	 * MenuPortalBean portalBean = (MenuPortalBean) session
	 * .getAttribute(Constants.MENU_KEY); try { // Para el paginador Integer
	 * page = catalogueForm.getPageNumber(); Integer pageSize =
	 * Constants.PAGINADOR_CATALOGUE_SIZE; //pageSize = 1; if (page == null)
	 * page = 1;
	 * 
	 * if (page < 1) throw new Exception(page + " is not a valid value to
	 * pageNumber");
	 * 
	 * sess = this.getHibernateSession();
	 * 
	 * Category category = null; category = (Category) sess.get(Category.class,
	 * catalogueForm .getCode());
	 * 
	 * request.setAttribute("category", category); StringBuffer sql = new
	 * StringBuffer(); StringBuffer sql2 = new StringBuffer();
	 * 
	 * sql.append(" select new com.iportal.biz.RowItem ( "); sql.append("
	 * product.code, product.name, product.priceMin, product.priceMax,
	 * product.image2) "); sql.append("from Product as product ");
	 * sql.append("where product.category.code = ? "); sql.append(" and
	 * product.enabled = ? "); sql.append(" order by product.name asc "); //Para
	 * el paginador sql2.append(" select count(*)"); sql2.append("from Product
	 * as product "); sql2.append("where product.category.code = ? ");
	 * sql2.append(" and product.enabled = ? "); sql2.append(" order by
	 * product.name asc ");
	 * 
	 * Query query = sess.createQuery(sql.toString()); query.setLong(0,
	 * catalogueForm.getCode());
	 * query.setBoolean(1,Globals.TRUE.booleanValue());
	 * query.setCacheable(true); // Para el paginador de todas page--;
	 * query.setFirstResult(page * pageSize); query.setMaxResults(pageSize);
	 * 
	 * results = query.list();
	 * 
	 * query = sess.createQuery(sql2.toString()); query.setLong(0,
	 * catalogueForm.getCode());
	 * query.setBoolean(1,Globals.TRUE.booleanValue());
	 * query.setCacheable(true); Long totalRows = (Long) query.iterate().next();
	 * catalogueForm.setTotalPages(pageHelper.getTotalPages(totalRows,
	 * pageSize)); request.setAttribute("productList", results);
	 *  } catch (Exception e) { logger.error(e.getMessage(), e); } finally { if
	 * (sess != null) try { sess.clear(); sess.close(); } catch (Exception e) { } }
	 * 
	 * return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_" +
	 * "category_info")); }
	 */

}
