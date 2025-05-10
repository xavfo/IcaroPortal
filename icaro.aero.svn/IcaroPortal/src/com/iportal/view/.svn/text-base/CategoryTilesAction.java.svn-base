/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.model.Language;
import com.yage.struts.action.BaseTilesAction;

/**
 * Gets a list of <code>Category</code> and store it in request scope.
 * 
 * <p>Tiles Parameters:<br>
 * <code>parentCode</code> The category parent Code.<br>
 * <code>maxResults</code> The max number of news to retrieve.<br>
 * <code>attributeName</code> The request scope attribute under which a List object is stored.
 * </p>
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class CategoryTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(CategoryTilesAction.class);
    

    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        // Gets the StringBuffer for the currently user's menu
        HttpSession session = request.getSession();
        Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);

        
        // Gets the attribute (parameter)
        String pc = (String) context.getAttribute("parentCode");
        String mr = (String) context.getAttribute("maxResults");
        String an = (String) context.getAttribute("attributeName");

        
        Session sess = null;
        Query query = null;
        List results = null;
        
        try {
            StringBuffer sql = new StringBuffer();
    		ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();
            
            sql.append("from Category as category ");
            sql.append("where category.language.code = ? ");
            params.add(language.getCode());
            types.add(Hibernate.LONG);
            
            if (pc != null && pc.length() > 0) {
                sql.append("and category.parent.code = ? ");
                params.add( new Long(pc) );
                types.add( Hibernate.LONG );
            } else {
                sql.append("and category.level = 1 ");
            }
    		
			Type[] type = new Type[types.size()];
			for (int i = 0; i < types.size(); i++) {
				type[i] = (Type) types.get(i);
			}
    		
			// Creates a query to retrieve Category
			sess = getHibernateSession();
    		query = sess.createQuery(sql.toString());
    		for (int i = 0; i < type.length; i++) {
    		    query.setParameter(i, params.get(i), type[i]);
    		}
    		if (mr != null && NumberUtils.isNumber(mr) ) {
    		    int max = Integer.parseInt(mr);
    		    query.setMaxResults(max);
    		}
    		results = query.list();
    		
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
        
		// Store the results in request scope
		String name = "categoryList";
		if (an != null && an.length() > 0) {
		    name = an;
		}
		request.setAttribute(name, results);

		
		return null;
    }

}
