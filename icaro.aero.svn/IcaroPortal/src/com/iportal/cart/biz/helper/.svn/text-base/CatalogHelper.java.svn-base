/**
 * 
 */
package com.iportal.cart.biz.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.PageHelper;
import com.iportal.cart.CartConstants;
import com.iportal.cart.ctrl.catalog.SearchProductForm;
import com.iportal.cart.model.catalog.Brand;
import com.iportal.cart.model.catalog.Category;
import com.iportal.cart.model.catalog.Product;
import com.iportal.cart.model.catalog.Seller;
import com.yage.Globals;

/**
 * Helper para consultar informacion de productos desde las acciones
 * del front-end del portal
 * @author hernan
 * @version 1.0
 *
 */
public class CatalogHelper extends BaseHelper {

	private static Log logger = LogFactory.getLog(CatalogHelper.class);
	
	private CatalogHelper() {
		// only static access
	}
	
	public static List<Product> listProducts(Long categoryCode, Long brandCode, Long sellerCode, String orderField, Boolean orderAsc, Integer pageNumber, Integer pageSize) {
		SearchProductForm productForm = new SearchProductForm ();
		productForm.setCategoryCode(categoryCode);
		productForm.setBrandCode(brandCode);
		productForm.setSellerCode(sellerCode);
		productForm.setOrderField(orderField);
		productForm.setOrderAsc(orderAsc);
		productForm.setPageNumber(pageNumber);
		productForm.setPageSize(pageSize);
		
		List<Product> result = CatalogHelper.listProducts( productForm);

		return result;
	}

	public static List<Product> listProducts(SearchProductForm productForm) {
		List<Product> result = null;
		Session sess = null;
		try {
			sess = getHibernateSession();
			result = CatalogHelper.listProducts( productForm, sess);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (HibernateException e1) {
				}
		}


		return result;
	}

	/**
	 * Buscar productos en funcion de parametros
	 * 
	 * @param categoryCode
	 * @param brandCode
	 * @param sellerCode
	 * @param orderField
	 * @param orderAsc
	 * @param sess
	 * @return
	 * @throws Exception 
	 */
	public static List<Product> listProducts(SearchProductForm productForm, Session sess) throws Exception {
		List<Product> result = null;

		Integer page = productForm.getPageNumber() != null?productForm.getPageNumber():1;
		
		if (page<1)
			throw new Exception(page + " is not a valid value to pageNumber");

		ArrayList<Object> params = new ArrayList<Object>();
 		ArrayList<NullableType> types = new ArrayList<NullableType>();
 		

		StringBuffer hql = new StringBuffer();
        hql.append(" from Product p ");
        hql.append(" where p.enabled = ?  ");
  		params.add(Globals.TRUE);
  		types.add(Hibernate.BOOLEAN);

        if (productForm.getCategoryCode() != null && productForm.getCategoryCode().longValue() >0L) {
        	hql.append("  and p.category.code = ?");
      		params.add(productForm.getCategoryCode());
      		types.add(Hibernate.LONG);
        }

  		if ( productForm.getBrandCode() != null && productForm.getBrandCode().longValue() > 0L ) {
   			hql.append(" and p.brand.code = ?  ");
      		params.add( productForm.getBrandCode());
      		types.add(Hibernate.LONG);
   		}

  		if ( productForm.getSellerCode() != null && productForm.getSellerCode().longValue() > 0L ) {
   			hql.append(" and p.seller.code = ?  ");
      		params.add(productForm.getSellerCode());
      		types.add(Hibernate.LONG);
   		}
  		
  		if ( null != productForm.getOnSale() && true == productForm.getOnSale().booleanValue() ) {
   			hql.append(" and (p.onSale = ?  ");
   			hql.append("  or (p.onClearance = ?  ");
   			hql.append(" and  p.clearanceSince <= ?  ");
   			hql.append(" and  p.clearanceUntil >= ?))  ");
      		params.add(Globals.TRUE);
     		types.add(Hibernate.BOOLEAN);
      		params.add(Globals.TRUE);
     		types.add(Hibernate.BOOLEAN);
     		Calendar cal = GregorianCalendar.getInstance();  
      		params.add(cal);
     		types.add(Hibernate.CALENDAR);
      		params.add(cal);
     		types.add(Hibernate.CALENDAR);
   		}

  		if ( productForm.getOrderField() != null ) {
   			hql.append(" order by p.");
   			hql.append(productForm.getOrderField());
   			if ( productForm.getOrderAsc().booleanValue() ) {
   				hql.append(" asc ");
   			} else {
   				hql.append(" desc ");
   			}
   		}
		sess = getHibernateSession();
 		Query query = sess.createQuery(hql.toString());
		
		for (int i = 0; i < types.size(); i++) {
			query.setParameter(i, params.get(i), types.get(i));
		}
		
		query.setCacheable(true);
		page--;
		query.setFirstResult(page * productForm.getPageSize());
		query.setMaxResults(productForm.getPageSize());
		
		result = (ArrayList<Product>)query.list ();
		
		//Calcula el nuemro de paginas en funci�n del nuemor de resultados y tama�o de pagina
		
		query =  sess.createQuery("SELECT count(*) " + hql.toString());
		for (int i = 0; i < types.size(); i++) {
			query.setParameter(i, params.get(i), (Type) types.get(i));
		}
		
		Long totalRows = (Long) query.iterate().next();
		
		productForm.setTotalPages( PageHelper.getTotalPages(totalRows, productForm.getPageSize()) );
		
		
		return result;		
		
	}
	
	/**
	 * Lista los productos de codigos igual a los enviados en <CODE>codes</CODE>
	 * @param codes
	 * @param sess conexion a Hibernate
	 * @return lista de productos
	 * @throws Exception
	 */
	public static List<Product> listProducts(Long[] productCodes, Session sess) throws Exception {
		List<Product> result = null;



		StringBuffer hql = new StringBuffer();
        hql.append(" from Product p ");
        hql.append(" where p.enabled = :enabled  ");
        hql.append(" and p.code in (:codes)  ");
		sess = getHibernateSession();
 		Query query = sess.createQuery(hql.toString());
 		query.setParameter("enabled", Globals.TRUE, Hibernate.BOOLEAN);
 		query.setParameterList("codes", productCodes);
		
		result = (ArrayList<Product>)query.list ();
		
		
		return result;		
		
	}

	public static List<Category> listCategories (Long parentCode, boolean noParent, Session sess) {
		
		StringBuffer hql = new StringBuffer();
        hql.append("select new com.iportal.biz.RowItem ( ");
        hql.append(" c.code, c.name, c.level ) ");
        hql.append("from Category c ");
        hql.append("where c.enabled = ?  ");
        if (noParent) {
        	hql.append("  and c.parent.code is null ");
        }
        if (null != parentCode   && parentCode.longValue() > 0L) {
        	hql.append("  and c.parent.code = ? ");	
        }
        
        hql.append("order by  c.orderIndex ");
        Query query = sess.createQuery(hql.toString());
		
        
        
        query.setParameter(0, Globals.TRUE, Hibernate. BOOLEAN);
        if (null != parentCode   && parentCode.longValue() > 0L) {
        	query.setParameter(1, parentCode, Hibernate.LONG);
        }
		
		query.setCacheable(true);
		
		List<Category> result = (ArrayList<Category>)query.list();
		return result;

	}
	
	/**
	 * Carga la informacion de la categoria solicitada en <CODE>categoryCode</CODE> y la lista
	 * de categorias descendientes, o si no la lista de categorias del mismo nivel.
	 * Almacena en el request la categoria y la lista de categorias hijas o hermanas
	 * @param request peticion HTTP
	 * @param sess conexion de hibernate activa
	 * @param categoryCode codigo de categor�a que se desea sacar la informacion
	 * @param loadedCategory entidad de la categoria iniciada por un objeto dependiente como un producto.
	 * @return  la categoria cargada con las subcategorias descendientes o hermanas
	 */
	/**
	 * @param request
	 * @param sess
	 * @param categoryCode
	 * 
	 * @return
	 */
	public static Category loadCategory (HttpServletRequest request, Session sess, Long categoryCode, Category loadedCategory) {
		Category category = null;
		List children = null;
		HttpSession session = request.getSession();

		StringBuffer hql = new StringBuffer();
        hql.append("from Category c ");
        hql.append("where c.parent.code = ? ");
        hql.append("  and c.enabled = ? ");
        hql.append("order by  c.orderIndex ");

        Query query = sess.createQuery(hql.toString());
			
        query.setParameter(0, categoryCode,Hibernate.LONG);
        query.setParameter(1, Globals.TRUE,Hibernate.BOOLEAN);
        
        query.setCacheable(true);
        children = query.list();
        
        if( children == null ||  children.size() < 1) {
            
        	//Colo carga la cateforia si este vino en null
        	category = (loadedCategory != null)?loadedCategory:(Category) sess.get(Category.class, categoryCode);
        	if (category.getParent() == null) {
        		ServletContext servletContext = session.getServletContext();
        		children = (List)servletContext.getAttribute(CartConstants.CART_CATALOG_ALL_CATEGORIES);
        		if (children == null) {
        			children = CatalogHelper.listCategories(null, true, sess);
        			servletContext.setAttribute(CartConstants.CART_CATALOG_ALL_CATEGORIES, children);
        		}
        	} else {
        		children = CatalogHelper.listCategories(category.getParent().getCode(), false, sess);
        	}
        	category.setHasChildren(Globals.FALSE);
        	
        	
        	
        } else {
        	category = ((Category)children.get(0)).getParent();
        	category.setHasChildren(Globals.TRUE);
        	
        }

        category.setSubCategoryList(children);

		return category;
	}
	
	
	public static List<Seller> listSellers ( Session sess) {
		
		StringBuffer hql = new StringBuffer();
        hql.append("from Seller s ");
        hql.append("where s.enabled = ?  ");
        hql.append("order by  s.name ");
        Query query = sess.createQuery(hql.toString());
		
        query.setParameter(0, Globals.TRUE, Hibernate. BOOLEAN);
		
		query.setCacheable(true);
		
		List<Seller> result = (ArrayList<Seller>)query.list();
		return result;

	}

	public static List<Brand> listBrands ( Session sess) {
		
		StringBuffer hql = new StringBuffer();
        hql.append("from Brand b ");
        hql.append("order by  b.name ");
        Query query = sess.createQuery(hql.toString());
        //query.setParameter(0, Globals.TRUE, Hibernate. BOOLEAN);
		
		query.setCacheable(true);
		
		List<Brand> result = (ArrayList<Brand>)query.list();
		return result;

	}

}
