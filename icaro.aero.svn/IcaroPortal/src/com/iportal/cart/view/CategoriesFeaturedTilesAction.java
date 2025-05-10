package com.iportal.cart.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.biz.RowItem;
import com.iportal.cart.CartConstants;
import com.iportal.cart.model.catalog.Product;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

public class CategoriesFeaturedTilesAction extends BaseTilesAction {
	
private static Log logger = LogFactory.getLog(CategoriesFeaturedTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        List<RowItem> result = null;
        Query query = null;
        
        StringBuffer hql = new StringBuffer();
    	

        Integer maxProductResults = CartConstants.CATALOGUE_PRODUCTS_FEATURED;
        Long parentCode = null;
        Boolean narrowByParent = Globals.FALSE;
        if (context.getAttribute("maxResults") != null) {
        	maxProductResults = new Integer ((String)  context.getAttribute("maxResults"));	
        }
        if (context.getAttribute("parentCode") != null) {
        	parentCode = Long.valueOf((String)context.getAttribute("parentCode"));	
        }
        if (context.getAttribute("narrow") != null) {
        	narrowByParent = Boolean.valueOf((String)context.getAttribute("narrow"));	
        }

        
        try {
        	
        	
        	//Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        	sess = getHibernateSession();

            
            hql.append("select new com.iportal.biz.RowItem ( ");
            hql.append("  c.code, c.name ) ");
            hql.append("from Category c ");
            hql.append("where c.featured = 1 ");
            hql.append("  and c.enabled = ? ");
            if(narrowByParent && parentCode != null) {
            	hql.append("  and c.parent.code = ? ");
            }
            hql.append("order by RAND() ");
			query = sess.createQuery(hql.toString());
            query.setBoolean(0,Globals.TRUE);
            if(narrowByParent && parentCode != null) {
            	query.setParameter(1, parentCode, Hibernate.LONG );
            }
            query.setMaxResults(CartConstants.CATALOGUE_CATEGORIES_FEATURED);
			query.setCacheable(true);
			result = (ArrayList<RowItem>)query.list();


            hql.setLength(0); hql.trimToSize();
            hql.append("from Product p ");
            hql.append("where (p.category.code = ? ");
            hql.append("  or  p.category.parent.code = ?) ");
            hql.append("  and p.enabled = ? ");
            hql.append("order by RAND() ");
            query = sess.createQuery(hql.toString());
            query.setBoolean(2,Globals.TRUE);
            query.setMaxResults(maxProductResults);
            query.setCacheable(true);

            for (RowItem rowItem : result) {
                query.setLong(0,rowItem.getCode());
                query.setLong(1,rowItem.getCode());
                rowItem.setChildList((ArrayList<Product>)query.list());
                
            }
         	
            request.setAttribute("categoriesFeaturedList", result);
            
		
			
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
      
		
		
		
		return null;
    }    
    
    
}
