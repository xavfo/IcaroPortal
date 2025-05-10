/*
 * Created Jul 28, 2006
 *    SearchContainerAction.java
 */
package com.iportal.ctrl.portal.search;

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
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.MapSiteBean;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.biz.helper.MapHelper;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * @author hernan
 *
 */
public class SearchGeneralAction extends ContentContainerAction {

    private static Log logger = LogFactory.getLog(SearchGeneralAction.class);

    public SearchGeneralAction() {
        super();
    }

    public ActionForward search (
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
    throws Exception {
        Session sess = null;
        List<MapSiteBean> results = null;
        List<MapSiteBean> finalResults = new ArrayList<MapSiteBean>();
        Query query = null;
        String forward =null;
        HttpSession session = request.getSession();
        SearchGeneralForm searchForm = (SearchGeneralForm)form;

        MenuPortalBean menuPortalBean = (MenuPortalBean)session.getAttribute(Constants.MENU_KEY);
        forward = menuPortalBean.getLayoutForward();

        try {
            sess = getHibernateSession();
            StringBuffer hql = new StringBuffer();
            // Products
            if( true == searchForm.getIncludeProduct() ) {
                hql.setLength(0); hql.trimToSize();
//              hql.append(" select new com.iportal.biz.MapSiteBean(cat.code, cat.parent.code, cat.name, cat.level, CONCAT('/portal/catalog/category.do?action=load&amp;itemCode=" + Constants.CONTENT_CATALOG + "&amp;code=',cat.code), cat.description)");
                hql.append(" select new com.iportal.biz.MapSiteBean(prod.code, " + Constants.CONTENT_CATALOG  + "L, CONCAT(prod.name, ' - ', prod.category.name), 2, CONCAT('/portal/catalog/product.do?action=load&amp;itemCode=" + Constants.CONTENT_CATALOG + "&amp;code=',prod.code))");
                hql.append(" from Product prod");
                hql.append(" where prod.enabled = ? ");
                hql.append(" and ( UPPER(prod.name) like ? ");
                hql.append("  or   UPPER(prod.slogan) like ? ");
                hql.append("  or   UPPER(prod.description) like ? ");
                hql.append("  or   UPPER(prod.features) like ? ) ");
                hql.append(" order by prod.name ");
                query = sess.createQuery(hql.toString());
                query.setBoolean(0, Globals.TRUE);
                String text = "%" + searchForm.getText() + "%";
                query.setString(1,text.toUpperCase());
                query.setString(2,text.toUpperCase());
                query.setString(3,text.toUpperCase());
                query.setString(4,text.toUpperCase());
                results = (ArrayList<MapSiteBean>)query.list();

                if( null != results && 0 < results.size() ) {
                    Content catalog = (Content)sess.get(Content.class, Constants.CONTENT_CATALOG);
                    MapSiteBean catalogMap = new MapSiteBean(catalog.getCode(), null, catalog.getTitle(), catalog.getLevel(), catalog.getLink());
                    catalogMap.setChildren(results);

                    finalResults.add(catalogMap);
                }
            }
            // Content
            if( true == searchForm.getIncludeInstitutional() ) {
                hql.setLength(0); hql.trimToSize();
    //            hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, content.title, content.level, content.link, content.text)");
                hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, CASE WHEN NULL != content.parent THEN CONCAT(content.title,' - ',content.parent.title) ELSE content.title END, content.level, content.link)");
                hql.append(" from Content content");
                hql.append(" where content.enabled = ? ");
                hql.append("   and ( UPPER(content.title) like ? ");
                hql.append("    or   UPPER(content.intro) like ? ");
                hql.append("    or   UPPER(content.text) like ? ");
                hql.append("    or   UPPER(content.keywords) like ? ) ");
                // hide Carrito de Compras
                hql.append("   and content.code != ? ");
                hql.append("   and (content.parent.code != ? or content.parent.code = null) ");
                hql.append(" order by  content.level, content.parent.code, content.code ");

                query = sess.createQuery(hql.toString());
                query.setBoolean(0, Globals.TRUE);
                String text = "%" + searchForm.getText() + "%";
                query.setString(1,text.toUpperCase());
                query.setString(2,text.toUpperCase());
                query.setString(3,text.toUpperCase());
                query.setString(4,text.toUpperCase());
                query.setLong(5, Constants.CONTENT_CATALOG);
                query.setLong(6, Constants.CONTENT_CATALOG);
                results = (ArrayList<MapSiteBean>)query.list();

                if( null != results && 0 < results.size() ) {
                    // need to add not selected parents
                    for (int i =0; i < results.size(); i++) {
                        MapSiteBean bean = results.get(i);
                        if( !results.contains(new MapSiteBean(bean.getParentCode(), "", bean.getLevel()-1))) {
                            Content cont = (Content) sess.get(Content.class, bean.getParentCode());
                            MapSiteBean contMap = new MapSiteBean(cont.getCode(), (null==cont.getParent()?null:cont.getParent().getCode()), cont.getTitle(), cont.getLevel(), cont.getLink());
                            if(!results.contains(contMap)) {
                                results.add(0==i?0:i-1, contMap);
                                i++;
                            }
                        }
                    }

                    finalResults.addAll(MapHelper.organizeMapBeanList(results));
                }
            }

            // Provider
           /* if( true == searchForm.getIncludeProvider() ) {
                hql.setLength(0); hql.trimToSize();
    //            hql.append(" select new com.iportal.biz.MapSiteBean(seller.code, new Long(" + Constants.CONTENT_SELLER + "), seller.name, new Integer(2), CONCAT('/portal/catalog/seller.do?action=load&amp;itemCode="+ Constants.CONTENT_SELLER + "&amp;code=',seller.code), seller.summary)");
                hql.append(" select new com.iportal.biz.MapSiteBean(seller.code, " + Constants.CONTENT_SELLER + "L, seller.name, 2, '/portal/catalog/seller.do?action=load&amp;itemCode="+ Constants.CONTENT_SELLER + "')");
                hql.append(" from Seller seller");
                hql.append(" where seller.enabled = ? ");
                hql.append(" and ( UPPER(seller.name) like ? ");
                hql.append("  or   UPPER(seller.summary) like ? ");
                hql.append("  or   UPPER(seller.description) like ? ) ");
                hql.append(" order by seller.name ");
                query = sess.createQuery(hql.toString());
                query.setBoolean(0, Globals.TRUE);
                String text = "%" + searchForm.getText() + "%";
                query.setString(1,text.toUpperCase());
                query.setString(2,text.toUpperCase());
                query.setString(3,text.toUpperCase());
                results = (ArrayList<MapSiteBean>)query.list();

                if( null != results && 0 < results.size() ) {
                    Content seller = (Content)sess.get(Content.class, Constants.CONTENT_SELLER);
                    MapSiteBean sellerMap = new MapSiteBean(seller.getCode(), null, seller.getTitle(), seller.getLevel(), seller.getLink());
                    sellerMap.setChildren(results);

                    finalResults.add(sellerMap);
                }
            }

            // Brand
            // NO_FIXME: Only select Brands in use (related to active Products)
            if( true == searchForm.getIncludeBrand() ) {
                hql.setLength(0); hql.trimToSize();
    //            hql.append(" select new com.iportal.biz.MapSiteBean(brand.code, " + Constants.CONTENT_BRAND + "L, brand.name, 2, CONCAT('/portal/catalog/brand.do?action=load&amp;itemCode="+ Constants.CONTENT_BRAND + "&amp;code=',brand.code), brand.description)");
                hql.append(" select new com.iportal.biz.MapSiteBean(brand.code, " + Constants.CONTENT_BRAND + "L, brand.name, 2, '/portal/catalog/brand.do?action=load&amp;itemCode="+ Constants.CONTENT_BRAND + "')");
                hql.append(" from Brand brand");
                hql.append(" where ( UPPER(brand.name) like ? ");
                hql.append("    or   UPPER(brand.description) like ? ) ");
                hql.append(" order by brand.name ");
                query = sess.createQuery(hql.toString());
                query.setBoolean(0, Globals.TRUE);
                String text = "%" + searchForm.getText() + "%";
                query.setString(0,text.toUpperCase());
                query.setString(1,text.toUpperCase());
                results = (ArrayList<MapSiteBean>)query.list();

                if( null != results && 0 < results.size() ) {
                    Content brand = (Content)sess.get(Content.class, Constants.CONTENT_BRAND);
                    MapSiteBean brandMap = new MapSiteBean(brand.getCode(), null, brand.getTitle(), brand.getLevel(), brand.getLink());
                    brandMap.setChildren(results);

                    finalResults.add(brandMap);
                }
            }*/

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



/*        SearchGeneralForm listForm = (SearchGeneralForm) form;

        List contentList = null;

        Query query = null;
        Session sess = null;

        try {
            sess = getHibernateSession();

            StringBuffer hql = new StringBuffer();
              ArrayList<Object> params = new ArrayList<Object>();
              ArrayList<NullableType> types = new ArrayList<NullableType>();

              hql.append("select new com.iportal.biz.RowItem ( ");
              hql.append(" content.code, content.title, content.intro ) ");
               hql.append(" from Content content");

               hql.append(" where 1 = 1 ");
               //hql.append(" where content.level in (2,3) ");

               hql.append(" and content.enabled = ? ");
               params.add(Globals.TRUE);
            types.add(Hibernate.BOOLEAN);

            if (listForm.getContentCode() != null && listForm.getContentCode() > 0 ) {
                hql.append(" and content.code = ? ");
                params.add(listForm.getContentCode());
                types.add(Hibernate.LONG);
            }

               if (listForm.getText() != null && listForm.getText().length() > 0 ) {
                String text = "%" + listForm.getText() + "%";
                hql.append(" and ( UPPER(content.title) like ? ");
                hql.append(" or UPPER(content.intro) like ? ");
                hql.append(" or UPPER(content.text) like ? ");
                hql.append(" or UPPER(content.keywords) like ? ) ");
                params.add(text.toUpperCase());
                params.add(text.toUpperCase());
                params.add(text.toUpperCase());
                params.add(text.toUpperCase());
                types.add(Hibernate.STRING);
                types.add(Hibernate.STRING);
                types.add(Hibernate.STRING);
                types.add(Hibernate.STRING);
            }

               if ((listForm.getTitle()) != null && listForm.getTitle().length() > 0 ) {
                String title = "%" + listForm.getTitle() + "%";
                hql.append(" and UPPER(content.title) like ? ");
                params.add(title.toUpperCase());
                types.add(Hibernate.STRING);
            }

               if (listForm.getContent() != null && listForm.getContent().length() > 0 ) {
                String content = "%" + listForm.getContent() + "%";
                hql.append(" and ( UPPER(content.intro) like ? ");
                hql.append(" or UPPER(content.text) like ? ) ");
                params.add(content.toUpperCase());
                params.add(content.toUpperCase());
                types.add(Hibernate.STRING);
                types.add(Hibernate.STRING);
            }

               if (listForm.getKey() != null && listForm.getKey().length() > 0 ) {
                String key = "%" + listForm.getKey() + "%";
                hql.append(" and UPPER(content.keywords) like ? ");
                params.add(key.toUpperCase());
                types.add(Hibernate.STRING);
            }

               hql.append(" order by content.title ");

               Object[] arrayParams = params.toArray();
            query = sess.createQuery(hql.toString());
            for (int i = 0; i < types.size(); i++) {
                query.setParameter(i, arrayParams[i], (Type) types.get(i));
            }

               contentList = query.list();
               request.setAttribute("contentList", contentList);

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

        //redirecciona a la forma de busqueda adecuada

        return mapping.findForward(Globals.FORWARD_LIST);
    } */

}

