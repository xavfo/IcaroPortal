/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.RowItemFacade;
import com.iportal.model.City;
import com.iportal.model.event.Event;
import com.iportal.model.event.EventDate;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class EventDateAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(EventDateAction.class);

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
         
    	 EventDateForm listForm = (EventDateForm) form;
    	 
    	 HttpSession session = request.getSession();
         
         EventForm eventForm = (EventForm) session.getAttribute("eventForm");
         
         if (eventForm != null) {
        	 	listForm.setEventCode(eventForm.getCode());
         }
         
         Session sess = null;
         List results = null;
         
         try {

       		StringBuffer sql = new StringBuffer();
       		ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();

     		sql.append("from EventDate as date ");
     		sql.append("where date.event.code = ? ");   
     		params.add(listForm.getEventCode());
		    types.add(Hibernate.LONG);
		    
		    if (listForm.getCityCode() != null && listForm.getCityCode().longValue() > 0L ) {
       			sql.append("and date.city.code = ? ");
       		    params.add(listForm.getCityCode());
       		    types.add(Hibernate.LONG);  
    		}
     		
     		if (listForm.getFromStr() != null && listForm.getFromStr().length() > 0) {
     			sql.append(" and spec.request.creation >= ? ");
				Calendar from = listForm.getFromDate();
   		    	from.set(Calendar.HOUR,0);
				from.set(Calendar.MINUTE,0);
				from.set(Calendar.SECOND,0);
				from.set(Calendar.MILLISECOND,0);	
   		    	params.add(from);
   		    	types.add(Hibernate.CALENDAR);
   		    }

   		    if (listForm.getToStr() != null && listForm.getToStr().length() > 0) {
   		    	sql.append(" and spec.request.creation < ? ");
				Calendar to = listForm.getToDate();
				to.set(Calendar.HOUR,0);
				to.set(Calendar.MINUTE,0);
				to.set(Calendar.SECOND,0);
				to.set(Calendar.MILLISECOND,0);
				to.add(Calendar.DAY_OF_WEEK,1);
   		    	params.add(to);
   		    	types.add(Hibernate.CALENDAR);
   		    }
     		    		
    		if ( listForm.getOrderField() != null ) {
    			sql.append("order by date.");
    			sql.append(listForm.getOrderField());
    			if ( listForm.getOrderAsc().booleanValue() ) {
    				sql.append(" asc ");
    			} else {
    				sql.append(" desc ");
    			}
    		}
      		
    		sess = getHibernateSession();

            RowItemFacade relatedFacade = new RowItemFacade();
            relatedFacade.setInRequest(listForm.getEventCode(), sess, Event.class, request);
            
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
   		request.setAttribute("eventList", results);

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
         EventDateForm eventDateForm = (EventDateForm) form;
         eventDateForm.reset(mapping, request);
         
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
 		EventDateForm eventDateForm = (EventDateForm) form;
 		
 		Session sess = null;
 		EventDate eventDate = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			eventDate = (EventDate) sess.load(EventDate.class, eventDateForm.getCode());
 			
 			PropertyUtils.copyProperties(eventDateForm, eventDate);
 			eventDateForm.setCityCode( eventDate.getCity().getCode() );
 			eventDateForm.setCityName( eventDate.getCity().getName() );
 			eventDateForm.setCountryName( eventDate.getCity().getState().getCountry().getName() );
 			
 				
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
         EventDateForm eventDateForm = (EventDateForm) form;

 		 this.saveItem(request, eventDateForm, messages);
 		 
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
         EventDateForm eventDateForm = (EventDateForm) form;

 		 this.saveItem(request, eventDateForm, messages);
 		 
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
     
     public void saveItem (
    		 HttpServletRequest request,
    		 EventDateForm eventDateForm,
    		 ActionMessages messages)
     throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		Event event = null;
  		EventDate eventDate = null;
  		City city = null;

  		try {
  			sess = getHibernateSession();
  		    
  		    event = (Event) sess.load(Event.class, eventDateForm.getEventCode());
  		    			
  			if (eventDateForm.getCityCode() != null && eventDateForm.getCityCode().longValue() > 0L) {
  				city = (City) sess.load(City.class, eventDateForm.getCityCode());
 			}  			
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			
  			if (eventDateForm.getDate().length > 0){
				for (int i=0; i < eventDateForm.getDate().length; i++ ){
		 			eventDate = new EventDate();
		 			eventDateForm.setCode(null);
		 			eventDateForm.setStrDate(eventDateForm.getDate()[i]);
					PropertyUtils.copyProperties(eventDate, eventDateForm);
					eventDate.setCity(city);
					eventDate.setEvent(event);
					sess.save(eventDate);
					SysAuditHelper.audit(this, request, eventDate, eventDate.toString(), Globals.AUDIT_INSERT, sess);
				}
			}	
  			tx.commit();
  			
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
		EventDateForm eventDateForm = (EventDateForm) form;
		
		Session sess = null;
		Transaction tx = null;
		EventDate eventDate = null;

		try {
		    Long codes[] = eventDateForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	eventDate = (EventDate) sess.load(EventDate.class, codes[i]);
					sess.delete(eventDate);
					SysAuditHelper.audit(this, request, eventDate, eventDate.toString(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.eventDate", this.getLocale(request)), eventDate.getLocation(), null);
			
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
