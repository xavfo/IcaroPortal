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

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.DocumentAssignFacade;
import com.iportal.model.Catalogue;
import com.iportal.model.Image;
import com.iportal.model.Language;
import com.iportal.model.event.Event;
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
public class EventAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(EventAction.class);

    
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
         
         EventForm listForm = (EventForm) form;
         
         Session sess = null;
         List results = null;
         boolean eventsLoaded = true;
         
         if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer hql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
        		ArrayList<NullableType> types = new ArrayList<NullableType>();
        		
        		if ((listForm.getCityCode() != null && listForm.getCityCode().intValue() > 0)
						|| (listForm.getFromDate() != null && listForm.getFromDate().length() > 0)
						|| (listForm.getToDate() != null && listForm.getToDate().length() > 0)) {
        			
					//Selecciona los codigos de eventos que cumplen con las condiciones
        			eventsLoaded = false;
        			hql.append(" select distinct eventDate.event.code  ");
        			hql.append(" from EventDate eventDate ");
        			hql.append(" where 1 = 1 ");
					
					
					if (listForm.getCityCode() != null && listForm.getCityCode().intValue() > 0) {
						hql.append(" and eventDate.city.code = ? ");
	     				params.add( listForm.getCityCode() );
	     				types.add(Hibernate.LONG);
	     				
	     			} 

					if (listForm.getFromDate() != null && listForm.getFromDate().length() > 0) {
						hql.append(" and eventDate.from >= ? ");
						Calendar from = listForm.getFrom();
		   		    	from.set(Calendar.HOUR,0);
						from.set(Calendar.MINUTE,0);
						from.set(Calendar.SECOND,0);
						from.set(Calendar.MILLISECOND,0);	
		   		    	params.add(from);
		   		    	types.add(Hibernate.CALENDAR);
		   		    }

		   		    if (listForm.getToDate() != null && listForm.getToDate().length() > 0) {
		   		    	hql.append(" and eventDate.to < ? ");
						Calendar to = listForm.getTo();
						to.set(Calendar.HOUR,0);
						to.set(Calendar.MINUTE,0);
						to.set(Calendar.SECOND,0);
						to.set(Calendar.MILLISECOND,0);
						to.add(Calendar.DAY_OF_WEEK,1);
		   		    	params.add(to);
		   		    	types.add(Hibernate.CALENDAR);
		   		    }
					
				} else {
					hql.append(" from Event as event ");
					hql.append("where 1 = 1 ");
				}
        		
        		if (listForm.getTitle() != null && listForm.getTitle().length() != 0L ) {
           		    String name = "%" + listForm.getTitle() + "%";
           		    hql.append("and event.title like ? ");
           		    params.add(name);
           		    types.add(Hibernate.STRING);  
        		}
        		
        		if (listForm.getCategoryCode() != null && listForm.getCategoryCode().longValue() > 0L ) {
        			hql.append(" and event.category.code = ? ");
           		    params.add(listForm.getCategoryCode());
           		    types.add(Hibernate.LONG);  
        		}
        		        		
        		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
        			hql.append(" and event.enabled = ? ");
          			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
          				params.add( Globals.TRUE );
          			else
          				params.add( Globals.FALSE );
     				types.add(Hibernate.BOOLEAN);
     			}
        		
        		if (listForm.getSeminaryOption() != null && listForm.getSeminaryOption().intValue() > -1) {
        			hql.append(" and event.seminary = ? ");
          			if ( listForm.getSeminaryOption().intValue() == Constants.TRUE_INT.intValue() )
          				params.add( Globals.TRUE );
          			else
          				params.add( Globals.FALSE );
     				types.add(Hibernate.BOOLEAN);
     			}

        		//solo ordenar si ya se esta cargando eventos
        		if ( listForm.getOrderField() != null && eventsLoaded) {
        			hql.append(" order by event.");
        			hql.append(listForm.getOrderField());
        			if ( listForm.getOrderAsc().booleanValue() ) {
        				hql.append(" asc ");
        			} else {
        				hql.append(" desc ");
        			}
        		}
         		
         		sess = getHibernateSession();
	     		Query query = sess.createQuery(hql.toString());
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, params.get(i), types.get(i));
				}
				
				query.setCacheable(true);
				results = query.list ();
				
				if (!eventsLoaded ) { //se trata de una lista de codigos de eventos en que ocurren en la ciudad indicada
					//debe cargar esos eventos a memoria
					hql = hql.delete(0, hql.length());
					hql.append(" from Event as event where event.code in (:codes) ");
	        		if ( listForm.getOrderField() != null && eventsLoaded) {
	        			hql.append(" order by event.");
	        			hql.append(listForm.getOrderField());
	        			if ( listForm.getOrderAsc().booleanValue() ) {
	        				hql.append(" asc ");
	        			} else {
	        				hql.append(" desc ");
	        			}
	        		}
					
					results = sess.createQuery(hql.toString()).setParameterList("codes", results, Hibernate.LONG).list();
				}
				for (int i=0; i < results.size(); i++) {
					Event event = (Event) results.get(i);
					Hibernate.initialize(event.getDates());
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
              
      		// Save the List of results in request scope
      		request.setAttribute("eventList", results);
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
         EventForm eventForm = (EventForm) form;
         eventForm.reset(mapping, request);
         
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
 		EventForm eventForm = (EventForm) form;
 		
 		Session sess = null;
 		Event event = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			event = (Event) sess.load(Event.class, eventForm.getCode());
 			
 			PropertyUtils.copyProperties(eventForm, event);
 			eventForm.setCategoryCode( event.getCategory().getCode() );
 			
 			if (event.getMainImage() != null){
 				eventForm.setMainImageCode(event.getMainImage().getCode());
                eventForm.setMainImageName(event.getMainImage().getName());
            }
 			if (event.getIntroImage() != null){
 				eventForm.setIntroImageCode(event.getIntroImage().getCode());
                eventForm.setIntroImageName(event.getIntroImage().getName());
            }
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
         EventForm eventForm = (EventForm) form;

 		 this.saveItem(request, eventForm, messages);
 		 
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
         EventForm eventForm = (EventForm) form;

 		 this.saveItem(request, eventForm, messages);
 		 
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
    		 EventForm eventForm,
    		 ActionMessages messages)
     throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		Event event = null;
  		Catalogue category = null;
  		Image mainImage = null;
 		Image introImage = null;

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (eventForm.getCode() != null && eventForm.getCode().longValue() != 0L) {
  				event = (Event) sess.load(Event.class, eventForm.getCode());
  			} else {
  				event = new Event();
  				event.setCreation(Calendar.getInstance());
  				eventForm.setCode(null);
  			}
  			
  			if (event.getCategory() == null || (!event.getCategory().getCode().equals(eventForm.getCategoryCode()))) {
  				category = (Catalogue) sess.load(Catalogue.class, eventForm.getCategoryCode());
 				event.setCategory(category);
 			}
  			
  			if (eventForm.getMainImageCode() != null && eventForm.getMainImageCode().longValue() > 0L) {
  				mainImage = (Image) sess.load(Image.class, eventForm.getMainImageCode());
  				event.setMainImage(mainImage);
  			}  				

 			if (eventForm.getIntroImageCode() != null && eventForm.getIntroImageCode().longValue() > 0L) {
 				introImage = (Image) sess.load(Image.class, eventForm.getIntroImageCode());
 				event.setIntroImage(introImage);
 			} 				
  			
  			PropertyUtils.copyProperties(event, eventForm);
 			
 			
 			//El lenguaje se setea por default el de la aplicación
 			HttpSession session = request.getSession();
			Language language = (Language)session.getAttribute(Constants.LANGUAGE_KEY);
			event.setLanguage(language);
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(event);			
  			tx.commit();
  			
  			//Audit Transaction
			if ( eventForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, event, event.getTitle(), Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, event, event.getTitle(), Globals.AUDIT_UPDATE);
			}
  			
  			eventForm.setCode(event.getCode());
  			
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
		EventForm eventForm = (EventForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Event event = null;

		try {
		    Long codes[] = eventForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	event = (Event) sess.load(Event.class, codes[i]);
					sess.delete(event);
					SysAuditHelper.audit(this, request, event, event.getTitle(), Globals.AUDIT_DELETE, sess);			        
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.event", this.getLocale(request)), event.getTitle(), null);
			
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
     public ActionForward listDocuments (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
    	 
    	 EventForm listForm = (EventForm) form;
         Long code = listForm.getCode();
         HttpSession session = request.getSession();
         if (code != null){
        	 session.setAttribute("eventCode", code);
         }
         code = (Long)session.getAttribute("eventCode");
         DocumentAssignFacade facade = new DocumentAssignFacade(this, request);

         List results = facade.getAssignedDocuments(code,null,Event.class, request);
         
  		 listForm.setCode(code);     
      	// Save the List of results in request scope
      	request.setAttribute("documentList", results);

 		return mapping.findForward(Globals.FORWARD_LIST_DOCUMENTS);
     }

}
