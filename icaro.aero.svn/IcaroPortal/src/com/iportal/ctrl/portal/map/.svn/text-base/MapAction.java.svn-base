/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.map;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.MapSiteBean;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * Incluir arreglos para evitar n+1 queries
 * @author  YAGE
 * @version 1.1 
 * 
 * 
 *
 */
public class MapAction extends BaseAction {
    
    private static Log logger = LogFactory.getLog(MapAction.class);
     
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
        
    throws Exception {
    	Session sess = null;
    	List results = null;
    	List<MapSiteBean> finalResults = null;
    	Query query = null;
    	Content content=null;
  	    String forward =null;
  	    
  	     HttpSession session = request.getSession();
      
  	  	 MenuPortalBean menuPortalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
  	  	 forward = menuPortalBean.getLayoutForward();
  	  
    	 sess = getHibernateSession();
    	 try {
    		 if (menuPortalBean != null) {
 				content = (Content) sess.get(Content.class, menuPortalBean
 						.getContentCode());
 				request.setAttribute("content", content);
 				
 			}
		 	  StringBuffer hql = new StringBuffer(); 
		  	  /*
		  	   * hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.title, content.level)");
		   	  hql.append(" from Content content");
		   	  hql.append(" where content.level = 1");*/
		 	 
		 	  //hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, content.title, content.level, content.link, content.text)");
		 	 hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, content.title, content.level, content.link)");
		 	  hql.append(" from Content content");
		 	  hql.append(" where content.enabled = ? ");
		 	  hql.append(" order by  content.level, content.parent.code, content.code ");
		 	  //c.content_level, c.content_parent, c.content_code
		 	  
		 	  
		   	  query = sess.createQuery(hql.toString());
		   	  query.setParameter(0, Globals.TRUE, Hibernate.BOOLEAN);
		   	  results = query.list();
		   	   
		   	  finalResults = this.organizeMapBeanList(results);
            
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
        
		
		request.setAttribute("mapList", finalResults);
		
		return mapping.findForward(forward);
    }
    
	public  List<MapSiteBean> organizeMapBeanList (List<MapSiteBean> results ) {
		List<MapSiteBean> finalResults =  new ArrayList<MapSiteBean> (results.size());
		MapSiteBean oldParent = null;
   		MapSiteBean parent = null;
   		List<MapSiteBean> children = null; 
   		for (MapSiteBean map : results) {
   			if (map.getParentCode() != null) {
   				parent = this.findMapSiteBean(finalResults, map.getParentCode());
				if (parent != null) {
					if ( oldParent != null && !parent.getCode().equals(oldParent.getCode())) {
						/*if (oldParent != null) {
							//finalResults.add(oldParent);
						}*/
						children = null;
					}
					oldParent= parent;
					if (children == null) {
						children = new ArrayList<MapSiteBean> ();
						oldParent.setChildren(children);
					}
					children.add(map);
					
				} else {
					logger.error("parent not Found in finalResultList code: "+map.getParentCode());
				}
			  } else {
				finalResults.add(map);
			  }
		}
   		
   		return finalResults;
	}
	
    private MapSiteBean findMapSiteBean (List<MapSiteBean> source, Long code) {
    	for (MapSiteBean map : source) {
    		if (map.getCode().equals(code)) {
    			return map;
    		} else {
    			if (map.getChildren() != null) {
    				MapSiteBean result = this.findMapSiteBean(map.getChildren(), code);
    				if (result != null) {
    					return result;
    				}
    			}
    		}
    			
    		
    	}
    	return null;
    }
    
}  
    
    
    
    
    
    
    
    
    
    /*protected List<MapSiteBean> queryChildren (Session sess, List<MapSiteBean> parentList) {
          StringBuffer hql = new StringBuffer(); 
        hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.title, content.level)");
        hql.append(" from Content content");
        hql.append(" where content.parent = ? ");
        hql.append(" AND  content.enabled = true ");
        hql.append(" order by content.title");
        Query query = sess.createQuery(hql.toString());
        for (MapSiteBean map : parentList) {
            query.setLong(0, map.getCode());
            HashSet<MapSiteBean> children = query.list();
            if (map.getLevel() < 4 && children != null && children.size() > 0) {
                map.setChildren(children);
                this.queryChildren(sess, children);
            } 
        }
        
        return parentList;
    }*/
    
    /*public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        Session sess = null;
        List<MapSiteBean> results = null;
        List<MapSiteBean> finalResults = null;
        Query query = null;
        String forward =null;
          
        HttpSession session = request.getSession();
      
        MenuPortalBean menuPortalBean = (MenuPortalBean)session.getAttribute(Constants.MENU_KEY);
        forward = menuPortalBean.getLayoutForward();
        
        sess = getHibernateSession();
        try {
            StringBuffer hql = new StringBuffer();
            // Catalog!
//            hql.append(" select new com.iportal.biz.MapSiteBean(cat.code, cat.parent.code, cat.name, cat.level, CONCAT('/portal/catalog/category.do?action=load&amp;itemCode=" + Constants.CONTENT_CATALOG + "&amp;code=',cat.code), cat.description)");
            hql.append(" select new com.iportal.biz.MapSiteBean(cat.code, cat.parent.code, cat.name, cat.level, '/portal/catalog/category.do?action=load&amp;itemCode=" + Constants.CONTENT_CATALOG + "')");
            hql.append(" from Category cat");
            hql.append(" where cat.enabled = ? ");
            hql.append(" order by  cat.level, cat.parent.code, cat.code ");
            query = sess.createQuery(hql.toString());
            query.setBoolean(0, Globals.TRUE);
            results = (ArrayList<MapSiteBean>)query.list();

            Content catalog = (Content)sess.get(Content.class, Constants.CONTENT_CATALOG);
            MapSiteBean catalogMap = new MapSiteBean(catalog.getCode(), null, catalog.getTitle(), catalog.getLevel(), catalog.getLink());
            catalogMap.setChildren(new ArrayList<MapSiteBean>());
            
            for (MapSiteBean bean : results) {
				if(null == bean.getParentCode()) {
					bean.setParentCode(new Long(1));
					catalogMap.getChildren().add(bean);
				}
			}
            results.add(0, catalogMap);
            
            finalResults = MapHelper.organizeMapBeanList(results);
            
            // Provider
            hql.setLength(0); hql.trimToSize();
//            hql.append(" select new com.iportal.biz.MapSiteBean(seller.code, new Long(" + Constants.CONTENT_SELLER + "), seller.name, new Integer(2), CONCAT('/portal/catalog/seller.do?action=load&amp;itemCode="+ Constants.CONTENT_SELLER + "&amp;code=',seller.code), seller.summary)");
            hql.append(" select new com.iportal.biz.MapSiteBean(seller.code, " + Constants.CONTENT_SELLER + "L, seller.name, 2, '/portal/catalog/seller.do?action=load&amp;itemCode="+ Constants.CONTENT_SELLER + "')");
            hql.append(" from Seller seller");
            hql.append(" where seller.enabled = ? ");
            hql.append(" order by seller.name ");
            query = sess.createQuery(hql.toString());
            query.setBoolean(0, Globals.TRUE);
            results = (ArrayList<MapSiteBean>)query.list();

            Content seller = (Content)sess.get(Content.class, Constants.CONTENT_SELLER);
            MapSiteBean sellerMap = new MapSiteBean(seller.getCode(), null, seller.getTitle(), seller.getLevel(), seller.getLink());
            sellerMap.setChildren(results);
            
            finalResults.add(sellerMap);
            
            // Brand
            hql.setLength(0); hql.trimToSize();
//            hql.append(" select new com.iportal.biz.MapSiteBean(brand.code, " + Constants.CONTENT_BRAND + "L, brand.name, 2, CONCAT('/portal/catalog/brand.do?action=load&amp;itemCode="+ Constants.CONTENT_BRAND + "&amp;code=',brand.code), brand.description)");
            hql.append(" select new com.iportal.biz.MapSiteBean(brand.code, " + Constants.CONTENT_BRAND + "L, brand.name, 2, '/portal/catalog/brand.do?action=load&amp;itemCode="+ Constants.CONTENT_BRAND + "')");
            hql.append(" from Brand brand");
//            hql.append(" where brand.enabled = ? ");
            hql.append(" order by brand.name ");
            query = sess.createQuery(hql.toString());
//            query.setBoolean(0, Globals.TRUE);
            results = (ArrayList<MapSiteBean>)query.list();

            Content brand = (Content)sess.get(Content.class, Constants.CONTENT_BRAND);
            MapSiteBean brandMap = new MapSiteBean(brand.getCode(), null, brand.getTitle(), brand.getLevel(), brand.getLink());
            brandMap.setChildren(results);
            
            finalResults.add(brandMap);
            
            /*
             * hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.title, content.level)");
            hql.append(" from Content content");
            hql.append(" where content.level = 1");*/
              
         /*   hql.setLength(0); hql.trimToSize();
//            hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, content.title, content.level, content.link, content.text)");
            hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, content.title, content.level, content.link)");
            hql.append(" from Content content");
            hql.append(" where content.enabled = ? ");
            // hide Carrito de Compras
            hql.append("   and content.code != ? ");
            hql.append("   and (content.parent.code != ? or content.parent.code = null) ");
            hql.append(" order by  content.level, content.parent.code, content.code ");
            //c.content_level, c.content_parent, c.content_code
                            
            query = sess.createQuery(hql.toString());
            query.setBoolean(0, Globals.TRUE);
            query.setLong(1, Constants.CONTENT_CATALOG);
            query.setLong(2, Constants.CONTENT_CATALOG);
            results = (ArrayList<MapSiteBean>)query.list();
                  
            finalResults.addAll(MapHelper.organizeMapBeanList(results));
            
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
        
        request.setAttribute("mapList", finalResults);
        
        return mapping.findForward(forward);
    }
    
}*/
