package com.iportal.ctrl.portal.event;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.biz.portal.event.EventDateSearchForm;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

public class EventDateAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(EventDateAction.class);
    
    /**
     * Save action
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	 ActionMessages messages = new ActionMessages();
    	 Session sess = null;
    	 
    	 
	     HttpSession session = request.getSession();
	     List results =  new ArrayList();
	     List cityList = new ArrayList();
	     MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	     try {
    		 sess = getHibernateSession();
    		 EventDateSearchForm searchForm = (EventDateSearchForm) form;
    		 
    		 StringBuffer sql = new StringBuffer();
    		 List<Object> params = new ArrayList<Object>();
    		 List<NullableType> types = new ArrayList<NullableType>();
    		 sql.append("From EventDate ed ");
    		 sql.append("Where ed.event.code = ? ");
    		 params.add(searchForm.getEventCode());
    		 types.add(Hibernate.LONG);
    		 
    		 if (searchForm.getCityCode()!=null && searchForm.getCityCode().longValue()>0){
    			 sql.append(" and ed.city.code = ? ");
    			 params.add(searchForm.getCityCode());
    			 types.add(Hibernate.LONG);
    		 }
    		 sql.append(" Order by ed.city.name, ed.location, ed.from, ed.schedule ");
    		 
    		 Object[] arrayParams = params.toArray();
             Query query = sess.createQuery(sql.toString());
             for (int i = 0; i < types.size(); i++) {
                query.setParameter(i, arrayParams[i], (Type) types.get(i));
             }
             results = query.list();
             
             sql.delete(0, sql.length());
             
             sql.append("Select distinct ed.city ");
             sql.append("From EventDate ed ");
             sql.append("Where ed.event.code = ? ");
             
             query = sess.createQuery(sql.toString());
             query.setLong(0, searchForm.getEventCode());
             cityList = query.list();
             
    	 } catch (Exception e) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
   			logger.info(e.getMessage(), e);
  			}finally {
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				} 			
  			}
  			
  			// Report a success action
	  		if (messages.isEmpty()) 
	  			saveMessages(request, messages);
	  		
	 		request.setAttribute("eventDateList", results);
	 		request.setAttribute("cityList", cityList);
	 		return mapping.findForward(portalBean.getLayoutForward() +"_"+ Globals.FORWARD_SUCCESS);
    }

    

    
}
    