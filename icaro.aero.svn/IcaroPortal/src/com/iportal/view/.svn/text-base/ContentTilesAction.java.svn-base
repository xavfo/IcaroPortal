/**
 * Yagé 2007
 */
package com.iportal.view;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.biz.portal.bean.ItemSummaryBean;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * @author burkhard
 * @verion 1.0 created on Apr 18, 2007
 * 
 */
public class ContentTilesAction extends BaseTilesAction {

	private static Log logger = LogFactory.getLog(ContentTilesAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.tiles.actions.TilesAction#execute(org.apache.struts.tiles.ComponentContext,
	 *      org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ComponentContext context,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Session sess = getHibernateSession();
		Content content = null;
		ArrayList<ItemSummaryBean> childs = null;
		Long code = new Long(request.getParameter("code"));
		try {

			content = (Content) sess.get(Content.class, code);			
			StringBuffer hql = new StringBuffer();
			hql = new StringBuffer();
			hql.append("select new com.iportal.biz.portal.bean.ItemSummaryBean ( ");
			hql.append(" content.code, content.title, content.description, content.link, ");
			hql.append(" accessImage.path, accessImage.height, accessImage.width, content.text, content.intro ) ");
			hql.append(" FROM Content as content ");
			hql.append(" inner join  content.parent parent");
			hql.append(" left  join  content.accessImage accessImage ");
			hql.append(" WHERE parent.code = ? ");
			hql.append(" and content.enabled = ? ");

			// sql.append("AND menuItem.menuLanguage.menu.code = ? ");
			hql.append(" order by content.order");
			Query query = sess.createQuery(hql.toString());

			query.setLong(0, code);
			query.setBoolean(1, Globals.TRUE);
			query.setCacheable(true);
			childs = (ArrayList<ItemSummaryBean>) query.list();
			request.setAttribute("content", content);
			request.setAttribute("contentChildList", childs);
			
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

		return null;

	}
}
