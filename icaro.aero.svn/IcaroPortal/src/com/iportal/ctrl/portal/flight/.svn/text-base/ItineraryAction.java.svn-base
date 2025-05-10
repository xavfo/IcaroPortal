package com.iportal.ctrl.portal.flight;

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
import com.iportal.model.icaro.fare.Route;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * Obtiene los datos de Itinerarios
 * @author ferTamayo
 * @version 1.0
 */
public class ItineraryAction extends ContentContainerAction {

	private static Log logger = LogFactory.getLog(ItineraryAction.class);

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

		try {

			if (portalBean != null) {
				content = (Content) sess.get(Content.class, portalBean
						.getContentCode());
				request.setAttribute("content", content);
			}

			/*
			 * sql.append(" select new
			 * com.iportal.ctrl.portal.flight.FlightItineraryItem ( ");
			 * sql.append(" flight.code, route.code,"); sql.append("
			 * frequency.code, flight.number, route.name,"); sql.append("
			 * frequency.name, frequency.name, "); sql.append(" frequency.name )
			 * "); sql.append(" From Flight as flight "); sql.append(" inner
			 * join flight.frequency frequency "); sql.append(" left join
			 * frequency.route route"); sql.append(" where flight.enabled=? ");
			 * sql.append(" order by 2 "); query =
			 * sess.createQuery(sql.toString()); query.setBoolean(0,
			 * Globals.TRUE); results = query.list();
			 * request.setAttribute("itineraryList", results);
			 */

			StringBuffer sql = new StringBuffer();
			sql.append(" select new com.iportal.biz.RowItem ( ");
			sql.append(" route.code, route.name, route.notes, route.rateNotes) ");
			sql.append(" From Route as route ");
			sql.append(" where route.enabled=? ");
			sql.append(" order by route.name ");
			query = sess.createQuery(sql.toString());
			query.setBoolean(0, Globals.TRUE);
			results = query.list();
			request.setAttribute("routeList", results);

			sql = new StringBuffer();
			sql.append(" From Flight as flight ");
			sql.append(" inner join flight.frequency frequency ");
			sql.append(" left join frequency.route route");
			sql.append(" where flight.enabled=? ");
			sql.append(" order by route.code, frequency.code ");
			query = sess.createQuery(sql.toString());
			query.setBoolean(0, Globals.TRUE);
			results = query.list();
			request.setAttribute("flightList", results);

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

	public ActionForward readRate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Session sess = null;
		Query query = null;
		List results = null;
		sess = this.getHibernateSession();

		try {
			BaseForm route = (BaseForm) form;

			StringBuffer sql = new StringBuffer();
			sql.append(" from TicketRate as rate ");
			sql.append(" where rate.enabled=? ");
			sql.append(" and rate.specialFare=?");
			sql.append(" and rate.route.enabled=? ");
			sql.append(" and rate.route.code=? ");
			sql.append(" order by rate.paxType.code ");
			query = sess.createQuery(sql.toString());
			query.setBoolean(0, Globals.TRUE);
			query.setBoolean(1, Globals.FALSE);
			query.setBoolean(2, Globals.TRUE);
			query.setLong(3, route.getCode());
			results = query.list();
			request.setAttribute("rateList", results);

			query = sess.createQuery(sql.toString());
			query.setBoolean(0, Globals.TRUE);
			query.setBoolean(1, Globals.TRUE);
			query.setBoolean(2, Globals.TRUE);
			query.setLong(3, route.getCode());
			results = query.list();
			request.setAttribute("especialRateList", results);

			sql = new StringBuffer();
			sql.append(" from Route as route ");
			sql.append(" where route.code=? ");
			query = sess.createQuery(sql.toString());
			query.setLong(0, route.getCode());
			Route ruta = (Route) query.uniqueResult();
			request.setAttribute("ruta", ruta);

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

		return mapping.findForward(Globals.FORWARD_FORM.concat("_"
				+ "itinerary_popup"));
	}

}
