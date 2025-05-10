/**
 * 
 */
package com.iportal.cart.ctrl.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.helper.CatalogHelper;
import com.iportal.cart.model.catalog.Category;
import com.iportal.cart.model.catalog.Product;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.action.BaseForm;

/**
 * Accion encargada de obtener la infomacion de categor�as para mostrar en el
 * catalogo.
 * 
 * @author hernan
 * @version 1.0
 * 
 */
public class CategoryAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(CategoryAction.class);

	
	/**
	 * Lista categorias de primer nivel para mostrar datos del subHome
	 * de compras en l�nea.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listCategories(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//BaseForm listForm = (BaseForm) form;

		Session sess = null;
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
		List<Category> results = null;
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);

		try {
			
			results = (ArrayList<Category>)servletContext.getAttribute(CartConstants.CART_CATALOG_ALL_CATEGORIES);
			
			if (results == null) {
				//consultar la informacion de las categorias de nivel 1 activas
				sess = getHibernateSession();
				results = CatalogHelper.listCategories(null, true, sess);

				/*} else {
					//lee el menu y arma la lista de categorias
					results = new ArrayList<RowItem> ();
					for (RowItem item : leftMenu) {
						if( 1 == item.getLevel().intValue()) {
							//a�adir a lista de categorias de nivel 1
							results.add(item);		
						}
					}
				}*/
				
				servletContext.setAttribute(CartConstants.CART_CATALOG_ALL_CATEGORIES, results);
			}
			
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

		// Save the List of results in request scope
		request.setAttribute("categoryList", results);

		return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_"
				+ portalBean.getLayoutForward()));
	}
	
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseForm baseForm = (BaseForm) form;
		HttpSession session = request.getSession();
		Session sess = null;
		Category category = null;

		try {
			category = (Category) session.getAttribute("category");
			sess = getHibernateSession();
			if(null == category  || !category.getCode().equals(baseForm.getCode())) {
				category = CatalogHelper.loadCategory(request, sess, baseForm.getCode(), null);				
				// Save the results in session scope
				session.setAttribute("category", category);
			}
			
			if(!category.getHasChildren()) {
				//si no tiene hijos debe cargar el listado de productos.
				SearchProductForm searchForm = new SearchProductForm();
				searchForm.reset(mapping, request);
				searchForm.setCategoryCode(baseForm.getCode());
				searchForm.setOrderField("regularPrice");
				searchForm.setListForward(Constants.FORWARD_SUCCESS_CATEGORY);
				
				//trae la lista de productos
				
				List<Product> products = CatalogHelper.listProducts(searchForm, sess);
				//Save the List of results in request scope
				request.setAttribute("productList", products);
				session.setAttribute("searchProductForm", searchForm);
			}
			
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
		
		if (category != null ) {
			String forward = Globals.FORWARD_SUCCESS;
			forward+= (category.getHasChildren())?"_category":"_subCategory";	
			return mapping.findForward(forward);			
		} else {
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		
	}
	

}
