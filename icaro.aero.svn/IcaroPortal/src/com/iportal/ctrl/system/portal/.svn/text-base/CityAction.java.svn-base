/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
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
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.helper.QueryHelper;
import com.iportal.model.City;
import com.iportal.model.State;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class CityAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(CityAction.class);

   /* public List getStates (Long countryCode){
        Session sess = null;
        Query query = null;
        
        List stateList = null;        
        
        try {
            sess = getHibernateSession();
            StringBuffer sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (state.code, state.country.code, state.name) ");
            sql.append(" from State as state ");
            if (countryCode != null && countryCode.longValue() > 0L){
                sql.append(" where state.country.code = ? ");
            }
            sql.append(" order by state.name ");
            
            
            query = sess.createQuery(sql.toString());
            if (countryCode != null && countryCode.longValue() > 0L ){
                query.setParameter(0, countryCode, Hibernate.LONG);
            }
            query.setCacheable(true);
            stateList = query.list();
            
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
        return stateList;
    }*/
    
    public ActionForward listStates(ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response){
        CityForm cityForm = (CityForm)form;
        List<RowItem> stateList = null;
		try {
			stateList = QueryHelper.queryStatesByCountry(cityForm.getCountryCode(),null,null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}        
        request.setAttribute("listOfOptions", stateList);
        request.setAttribute("targetProperty", "stateCode");
        request.setAttribute("theForm", form);
        
        return mapping.findForward("ajaxForm");
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
     public ActionForward list (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         CityForm listForm = (CityForm) form;
         
         Session sess = null;
         List results = null;
         
         if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer sql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();

         		sql.append(" from City as city "); 
         		sql.append(" where 1 = 1 ");
         		
         		if (listForm.getCountryCode() != null && listForm.getCountryCode().longValue() > 0) {
    				sql.append(" and city.state.country.code = ? ");
    				params.add(listForm.getCountryCode());
    				types.add(Hibernate.LONG);
    			}

         		if (listForm.getStateCode() != null && listForm.getStateCode().longValue() > 0) {
    				sql.append(" and city.state.code = ? ");
    				params.add(listForm.getStateCode());
    				types.add(Hibernate.LONG);
    			}
         		

         		if (listForm.getName() != null && listForm.getName().length() > 0) {
         			String name = "%" + listForm.getName() + "%";				
    				sql.append(" and city.name like ? ");
    				params.add(name);
    				types.add(Hibernate.STRING);
    			}      		
         		
         		if ( listForm.getOrderField() != null ) {
             		sql.append(" order by city.");
             		sql.append(listForm.getOrderField());
             		if ( listForm.getOrderAsc().booleanValue() ) {
             		    sql.append(" asc ");
             		} else {
             		    sql.append(" desc ");
             		}
         		}
         		
         		sess = getHibernateSession(); 
         		
	     		Query query = sess.createQuery(sql.toString());
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, params.get(i), types.get(i));
				}
				
				query.setCacheable(true);
				results = query.list ();
          		
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
              
      		// Save the List of results in request scope
      		request.setAttribute("cityList", results);
         }
         

 		return mapping.findForward(Globals.FORWARD_LIST);
     }
     
     
     /**
      * Create action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward create (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         CityForm cityForm = (CityForm) form;
         cityForm.reset(mapping, request);
         /*List stateList = getStates(cityForm.getCountryCode());        
         request.setAttribute("states", stateList);*/
         
         return mapping.findForward(Globals.FORWARD_FORM);
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
     public ActionForward read (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
 		ActionMessages messages = new ActionMessages();

 		// Gets the action form
 		CityForm cityForm = (CityForm) form;
 		
 		Session sess = null;
 		City city = null;
        // List states = null;
 		State state = null;
 		try {
 			sess = getHibernateSession();
 			
 			city = (City) sess.load(City.class, cityForm.getCode());
 			
 			PropertyUtils.copyProperties(cityForm, city);
 			state = city.getState();
            if (state != null){
                cityForm.setStateCode( state.getCode() );
                cityForm.setCountryCode(state.getCountry().getCode());
            }
             
            //states = getStates(cityForm.getCountryCode());
 			
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
 			logger.error(e.getMessage(), e);
 		} finally {
 			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}
 		if (state != null) {
 	 		RowItem row = new RowItem(city.getState().getCode(), city.getState().getName());
 	        List<RowItem> options =  new ArrayList<RowItem>();
 	        options.add(row);
 	 		request.setAttribute("listOfOptions",  options);
 		}

 		// Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
         return mapping.findForward(Globals.FORWARD_FORM);
     }   
     
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
     public ActionForward save (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         
         ActionMessages messages = new ActionMessages();  		
         
         //Gets the action form
         CityForm cityForm = (CityForm) form;

 		 this.saveItem(cityForm, messages, request);
        
        /* List states = getStates(cityForm.getCountryCode());
         request.setAttribute("states", states);*/
 		 
 		 //Report any messages we have discovered back to the original form
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
  		
  		//Report a success action
 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
 		saveMessages(request, messages);
 		
		 return mapping.findForward(Globals.FORWARD_SUCCESS);
		 
     }
     
     /**
      * Apply action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward apply (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
    	 if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         
         ActionMessages messages = new ActionMessages();  		
         
         //Gets the action form
         CityForm cityForm = (CityForm) form;

 		 City city = this.saveItem(cityForm, messages, request);
 		 
 		/* List states = getStates(cityForm.getCountryCode());
 		 request.setAttribute("states", states);*/
         
         if (city != null){
             cityForm.setCode(city.getCode());
         }
        
 		 //Report any messages we have discovered back to the original form
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
  		
  		//Report a success action
 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
 		saveMessages(request, messages);
 		
		 return mapping.findForward(Globals.FORWARD_APPLY);
		 
     }
     
     public City saveItem (
    		 CityForm cityForm,
    		 ActionMessages messages, HttpServletRequest request)
     throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		City city = null;
  		State state = null;

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (cityForm.getCode() != null && cityForm.getCode().longValue() != 0L) {
  				city = (City) sess.load(City.class, cityForm.getCode());
  			} else {
  				city = new City();
  				cityForm.setCode(null);
  			}
  			
  			if (cityForm.getStateCode()!= null && cityForm.getStateCode().longValue() > 0L ) {
  				state = (State) sess.load(State.class, cityForm.getStateCode());
  				city.setState(state); 
 			}

  			
  			PropertyUtils.copyProperties(city, cityForm);
  			
  			 			
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(city);			
  			tx.commit();
         
            // Audit Transaction
            if ( cityForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, city, city.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, city, city.getName(), Globals.AUDIT_UPDATE);
            }

  		} catch (Exception e) {
  			if (tx != null) {
  			    tx.rollback();
  			}
  			
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
  			logger.error(e.getMessage(), e);
  		} finally {
  			if (sess != null)
  				try {
  					sess.close();
  				} catch (Exception e) {
  				}
  		}
  		return city;
        
     }


     /**
      * Delete action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward delete (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		CityForm cityForm = (CityForm) form;
		
		Session sess = null;
		Transaction tx = null;
		City city = null;

		try {
		    Long codes[] = cityForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	city = (City) sess.load(City.class, codes[i]);
					sess.delete(city);
                    SysAuditHelper.audit(this, request, city, city.getName(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.city", this.getLocale(request)), city.getName(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

}
