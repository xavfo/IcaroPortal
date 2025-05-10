/**
 * 
 */
package com.iportal.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.cart.model.cart.PaymentType;
import com.yage.struts.action.BaseTilesAction;

/**
 * @author hernan
 *
 */
public class PassengerTilesAction extends BaseTilesAction {

	private static Log logger = LogFactory.getLog(PassengerTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	Session sess = null;
        Query query = null;
        List idTypeList = null;
        
        List<PaymentType> paymentTypeList = null;
        List<LabelValueBean> yearList = new ArrayList<LabelValueBean>(100);
        
        List<LabelValueBean> monthList = new ArrayList<LabelValueBean>(12);
        List<LabelValueBean> dayList = new ArrayList<LabelValueBean>(31);
        try {
            sess = getHibernateSession();
             
            //select new com.iportal.biz.RowItem (type.name, type.code)
            //es mas pequeño el objeto IDTYPE que el ROWItem
            query = sess.createQuery(" from IDType as type order by type.name" );
            query.setCacheable(true);
            idTypeList = query.list();
     		
	     		
     		Calendar today = Calendar.getInstance();
     		int year = today.get(Calendar.YEAR);
     		
     		for (int i=year; i > year-100; i--  ) {
     			String yearStr = String.valueOf(i);
   				LabelValueBean label = new LabelValueBean(yearStr,yearStr);
   				yearList.add(label);
     		}
     		for (int i=1 ; i < 13; i ++) {
     			String mStr = String.valueOf(i);
   				LabelValueBean label = new LabelValueBean(mStr,mStr);
   				monthList.add(label);
     			
     		}
     		for (int i=1 ; i < 32; i ++) {
     			String dStr = String.valueOf(i);
   				LabelValueBean label = new LabelValueBean(dStr,dStr);
   				dayList.add(label);

     		}
     		
            /*Colocar esto cuando se escoja la forma de pago
             * StringBuffer hql = new StringBuffer();
            hql.append(" from PaymentType as type ");
            hql.append("where type.enabled = ? ");

            sess = getHibernateSession();
            query = sess.createQuery(hql.toString());
            query.setBoolean(0, Globals.TRUE);
            query.setCacheable(true);

            paymentTypeList = (ArrayList<PaymentType>)query.list();
            
            if(null != paymentTypeList && 0 < paymentTypeList.size()) {

                SearchFlightForm searchForm = (SearchFlightForm) request.getSession().getAttribute("searchFlightForm");
                if(null == searchForm.getPaymentTypeCode()) {
                	searchForm.setPaymentTypeCode(paymentTypeList.get(0).getCode());
                }
            }
            */
            
            request.setAttribute("paymentTypeList", paymentTypeList);

	     			
     		
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
 		//Save the Lists in request scope
 			request.setAttribute("yearList", yearList);
 			request.setAttribute("monthList", monthList);
 			request.setAttribute("dayList", dayList);
 			request.setAttribute("idTypeList", idTypeList);
 		
		
 		
 		return null;
     }
}
