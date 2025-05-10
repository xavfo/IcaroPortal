/**
 * Yagé 2007
 */
package com.iportal.cart.ctrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.PageHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.customer.Customer;
import com.iportal.ctrl.portal.content.ContentAction;
import com.iportal.model.portal.Content;
import com.yage.Globals;


/**
 *
 * @author fernandoT
 * @verion 1.0
 * created on August 6, 2007
 *
 */
public class SearchPurchaseAction extends ContentAction {

    private static Log logger = LogFactory.getLog(SearchPurchaseAction.class);
    
    public ActionForward read(
	        ActionMapping mapping, 
	        ActionForm form,
	        HttpServletRequest request, 
	        HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Session sess = null;
		Content content=null;
	    
		MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
		sess = this.getHibernateSession();
	    if (portalBean != null) {
			content = (Content) sess.get(Content.class, portalBean.getContentCode());
			request.setAttribute("content", content);			
		}	
	    UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
	    
      Customer user = icaroUser.getCustomer();
	        
	    Boolean show=Globals.FALSE;    
	   	request.setAttribute("show",show);
	   	if(user!=null){
	   		request.setAttribute("userName", user.getUserName());	
	   	}else{
	   		request.setAttribute("userName", icaroUser.getIcaroUserBean().getName());
	   	}
      	
	    return mapping.findForward("purchases");
	}

    
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

		SearchPurchaseForm listForm = (SearchPurchaseForm) form;
		Content content = null;
		Query query = null;
		Session sess = null;
		HttpSession session = request.getSession();
		List results = null;
		ArrayList <Order>ordenes=new ArrayList<Order>();
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);
		
		UserBean icaroUser = (UserBean) session.getAttribute(Constants.CLIENT_KEY);
	    
	      Customer user = icaroUser.getCustomer();
		try {

			sess = getHibernateSession();
			if (portalBean != null) {
				content = (Content) sess.get(Content.class, portalBean.getContentCode());
				request.setAttribute("content", content);

			}
   		            
			StringBuffer sql = new StringBuffer();
			
			sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
			ArrayList<NullableType> types = new ArrayList<NullableType>();
			Integer page = listForm.getPageNumber();
			Integer pageSize = Constants.ORDER_PAGE_SIZE;
			//pageSize = 2;			
			if (page == null)
				page = 1;

			if (page < 1)
				throw new Exception(page
						+ " is not a valid value to pageNumber");

			sql.append("from Order as order ");
			sql.append("where 1 = 1 ");
			sql.append("and order.enabled=? ");
			if(user!=null){
				sql.append("and order.customer.code=? ");
			}else{
				sql.append("and order.customerReference=? ");
			}
			params.add(Globals.TRUE);
			if(user!=null){
				params.add(user.getCode());
			}else{
				params.add(icaroUser.getIcaroUserBean().getCardNumber());
			}
			
			types.add(Hibernate.BOOLEAN);
			if(user!=null){
				types.add(Hibernate.LONG);
			}else{
				types.add(Hibernate.STRING);
			}
			

			// Criterio para busqueda por n�mero de Reserva (record Locator)
			if (listForm.getReserveNumber() != null	&& listForm.getReserveNumber().length() != 0L) {
				String title = "%" + listForm.getReserveNumber() + "%";
				sql.append("and order.recordLocator like ? ");
				params.add(title);
				types.add(Hibernate.STRING);
			}
			
			//Criterio para busqueda por tipo de pago
			if (listForm.getPaymentType() != null	&& listForm.getPaymentType().longValue() > 0L) {
				sql.append("and order.payment.type.code=?");
				params.add(listForm.getPaymentType());
				types.add(Hibernate.LONG);
			}

//			Criterio para busqueda por estado
			if (listForm.getPaymentType() != null	&& listForm.getPaymentType().longValue() > 0L) {
				sql.append("and order.status.code=?");
				params.add(listForm.getStatus());
				types.add(Hibernate.LONG);
			}
			
			// Define el inicio en del rango de fechas a filtrar sobre la fecha de orden
			if (listForm.getFromDate() != null	&& listForm.getFromDate().length() > 0) {
				sql.append(" and order.creation >= ? ");
				Calendar from = new GregorianCalendar();
				from.setTime(listForm.getFrom().getTime());
				from.set(Calendar.HOUR, 0);
				from.set(Calendar.MINUTE, 0);
				from.set(Calendar.SECOND, 0);
				from.set(Calendar.MILLISECOND, 0);

				params.add(from);
				types.add(Hibernate.CALENDAR);
			}

			// Define el fin en del rango de fechas a filtrar sobre la fecha de orden
			if (listForm.getToDate() != null && listForm.getToDate().length() > 0) {
				sql.append(" and order.creation <= ? ");
				Calendar to = new GregorianCalendar();
				to.setTime(listForm.getTo().getTime());
				to.set(Calendar.HOUR, 23);
				to.set(Calendar.MINUTE, 59);
				to.set(Calendar.SECOND, 59);
				to.set(Calendar.MILLISECOND, 0);
				params.add(to);
				types.add(Hibernate.CALENDAR);
			}

			
			Object[] arrayParams = params.toArray();
			query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i], (Type) types.get(i));
			}
			
//			 Para el paginador de ordenes
			page--;
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			
			results = query.list();
			
			query = sess.createQuery("SELECT count(*) " + sql.toString());
			//query.setBoolean(0, Globals.TRUE);
			
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i], (Type) types.get(i));
			}
			query.setCacheable(true);

			Long totalRows = (Long) query.iterate().next();
			
			listForm.setTotalPages(PageHelper.getTotalPages(totalRows, pageSize));
			
			Iterator it = results.iterator();
            while (it.hasNext()) {
            	Order orden = (Order) it.next();
            	Hibernate.initialize(orden.getItineraries());
            	ordenes.add(orden);    
            }
			
//			 Save the List of results in request scope
			request.setAttribute("orderList", ordenes);
            
			
			if (portalBean != null) {
				this.setCountContentContainers(request, sess, null, portalBean.getContentCode());
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

		
		Boolean show=Globals.TRUE;    
	   	request.setAttribute("show",show);
	   	if(user!=null){
	   		request.setAttribute("userName", user.getUserName());	
	   	}else{
	   		request.setAttribute("userName", icaroUser.getIcaroUserBean().getName());
	   	}
	   	
		return mapping.findForward("purchases");
	}


	/**
		 * Detail action
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward detail(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	
			SearchPurchaseForm listForm = (SearchPurchaseForm) form;
			Session sess = null;
			
			UserBean icaroUser = (UserBean) request.getSession().getAttribute(Constants.CLIENT_KEY);
		    
		      Customer user = icaroUser.getCustomer();
		      try {
		    	  	sess = getHibernateSession();  
		    	  	Order order = (Order)sess.get(Order.class, listForm.getCode());
		    	  	Hibernate.initialize(order.getItineraries());
		    	  	Hibernate.initialize(order.getPassengers());
		    	  	request.setAttribute("order", order);
		    	  	if(user!=null){
		    	  		request.setAttribute("userName", user.getFullName());	
		    	   	}else{
		    	   		request.setAttribute("userName", icaroUser.getIcaroUserBean().getName());
		    	   	}
		    	    
		    	    if(order.getSale()){
		    	          return mapping.findForward("showOrder");
		    	    }else{
		    	    	return mapping.findForward("showOrderReserve");
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
		    	        return null;
		 }
	
}

