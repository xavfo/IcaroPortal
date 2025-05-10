/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.audit.PageLogHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.biz.portal.content.ContainerForm;
import com.iportal.biz.portal.news.NewsForm;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.model.news.News;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * The Bussiness Logic for News
 */
public class NewsAction extends ContentContainerAction {

	private static Log logger = LogFactory.getLog(NewsAction.class);

	/**
	 * List action
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewsForm listForm = (NewsForm) form;
		Content content = null;
		Query query = null;
		Session sess = null;
		HttpSession session = request.getSession();
		List results = null;
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);
		String forward = Globals.FORWARD_SUCCESS.concat("_"+ portalBean.getLayoutForward());
		GregorianCalendar now = new GregorianCalendar();

		try {

			sess = getHibernateSession();
			if (portalBean != null) {
				content = (Content) sess.get(Content.class, portalBean
						.getContentCode());
				request.setAttribute("content", content);

			}
			 //Si se ha seleccionado una noticia para ver detalle
   		 if (listForm.getNewsCode() != null && listForm.getNewsCode().longValue() > 0L) {
   			 
           	 News news = (News) sess.get(News.class, listForm.getNewsCode());
           	 request.setAttribute("news", news);
           	 forward = Globals.FORWARD_FORM.concat("_"+ "news_layout1");
           	 
            } else {
            	
            
			StringBuffer sql = new StringBuffer();
			sql
					.append(" select new com.iportal.biz.RowItem (catalogue.code, catalogue.name)");
			sql.append(" from Catalogue as catalogue");
			sql.append(" where catalogue.enabled=? ");
			sql.append(" and catalogue.type.code=? ");
			sql.append(" order by catalogue.name ");
			query = sess.createQuery(sql.toString());
			query.setBoolean(0, Globals.TRUE);
			query.setLong(1, Constants.CATALOGUE_TYPE_NEWS);
			results = query.list();
			request.setAttribute("categoryList", results);

			sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
			ArrayList<NullableType> types = new ArrayList<NullableType>();

			sql.append("from News as news ");
			sql.append("where 1 = 1 ");
			sql.append("and news.isEnabled=? ");
			params.add(Globals.TRUE);
			types.add(Hibernate.BOOLEAN);
			
			sql.append("and news.from <= ? ");
            params.add(now);
            types.add(Hibernate.CALENDAR);
            
            sql.append("and news.to >= ? ");
            params.add(now);
            types.add(Hibernate.CALENDAR);

			// Criterio para busqueda por t�tulo
			if (listForm.getTitle() != null
					&& listForm.getTitle().length() != 0L) {
				String title = "%" + listForm.getTitle() + "%";
				sql.append("and news.title like ? ");
				params.add(title);
				types.add(Hibernate.STRING);
			}

			if (listForm.getCategoryCode() != null
					&& listForm.getCategoryCode().longValue() > 0L) {
				sql.append("and news.category.code=?");
				params.add(listForm.getCategoryCode());
				types.add(Hibernate.LONG);
			}

			// Define el inicio en del rango de fechas a filtrar sobre la fecha
			// de noticia
			if (listForm.getFromDate() != null
					&& listForm.getFromDate().length() > 0) {
				sql.append(" and news.creation >= ? ");
				Calendar from = new GregorianCalendar();
				// from.setTime(fromCalendar.getTime());
				from.setTime(listForm.getFrom().getTime());
				from.set(Calendar.HOUR, 0);
				from.set(Calendar.MINUTE, 0);
				from.set(Calendar.SECOND, 0);
				from.set(Calendar.MILLISECOND, 0);

				params.add(from);
				types.add(Hibernate.CALENDAR);
			}

			// Define el fin en del rango de fechas a filtrar sobre la fecha de
			// noticia
			if (listForm.getToDate() != null
					&& listForm.getToDate().length() > 0) {
				sql.append(" and news.creation <= ? ");
				Calendar to = new GregorianCalendar();
				// to.setTime(toCalendar.getTime());
				to.setTime(listForm.getTo().getTime());
				to.set(Calendar.HOUR, 23);
				to.set(Calendar.MINUTE, 59);
				to.set(Calendar.SECOND, 59);
				to.set(Calendar.MILLISECOND, 0);
				params.add(to);
				types.add(Hibernate.CALENDAR);
			}

			sql.append("order by news.from desc, news.code ");
			Object[] arrayParams = params.toArray();
			query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i], (Type) types.get(i));
			}
			
			results = query.list();
//			 Save the List of results in request scope
			request.setAttribute("newsList", results);
            }
			if (portalBean != null) {
				this.setCountContentContainers(request, sess, null, portalBean
						.getContentCode());
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

		
		
		return mapping.findForward(forward);
	}

	/**
	 * Read action
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		//HttpSession session = request.getSession();
		//MenuPortalBean portalBean = null;
		//Content content = null;

		Session sess = null;
		//Query query = null;
		NewsForm newsForm = (NewsForm) form;
		News news = null;
		try {

			//portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			Long code = null;

			code = newsForm.getCode();
			sess = getHibernateSession();

			news = (News) sess.get(News.class, code);
			request.setAttribute("news", news);

			/*
			 * content = (Content) sess.get(Content.class,
			 * portalBean.getContentCode());
			 * 
			 * query = sess.createFilter(news.getDocuments(),"select
			 * count(this.code) where this.isEnabled=? ").setBoolean(0,
			 * Globals.TRUE); Long documents=(Long) query.uniqueResult();
			 * request.setAttribute("documents", documents);
			 * request.setAttribute("news", news);
			 * request.setAttribute("content", content);
			 * 
			 * StringBuffer hql = new StringBuffer(); hql.append("select new
			 * com.iportal.biz.RowItem ( "); hql.append(" news.code, news.title )
			 * "); hql.append(" FROM News as news "); hql.append(" WHERE
			 * news.category.code = ? "); hql.append(" and news.code <> ? ");
			 * hql.append(" and news.isEnabled = ? "); hql.append("order by
			 * news.index asc");
			 * 
			 * query = sess.createQuery(hql.toString()); query.setLong(0,
			 * news.getCategory().getCode()); query.setLong(1, news.getCode());
			 * query.setBoolean(2,Globals.TRUE); query.setCacheable(true);
			 * query.setMaxResults(5); List results = query.list();
			 * request.setAttribute("newsList", results);
			 */

		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.failure"));
			logger.error(e.getMessage(), e);

		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}

		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		// Registra en el log de visitas
		PageLogHelper.log(request, news.getClass().getName(), news.getCode(),
				news.getTitle());

		return mapping.findForward(Globals.FORWARD_FORM.concat("_"
				+ "news_layout1"));
	}

	public ActionForward listDocuments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		List results = null;
		Session sess = null;
		Content content = null;
		HttpSession session = request.getSession();
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);

		ContainerForm containerForm = (ContainerForm) form;

		try {
			sess = getHibernateSession();

			content = (Content) sess.get(Content.class, portalBean
					.getContentCode());

			News news = (News) sess.load(News.class, containerForm.getCode());

			results = sess.createFilter(news.getDocuments(),
					" where this.isEnabled = ? order by this.title")
					.setBoolean(0, true).list();

			request.setAttribute("news", news);
			request.setAttribute("documents", results);
			request.setAttribute("content", content);

		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.failure"));

			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}

		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}

		return mapping.findForward(portalBean.getLayoutForward() + "_"
				+ Globals.FORWARD_LIST);
	}
}
