package com.iportal.biz.portal.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;

import com.iportal.biz.BaseBussinessLogic;
import com.iportal.model.event.Event;
import com.yage.Globals;

public class EventFacade extends BaseBussinessLogic {

	private static Log logger = LogFactory.getLog(EventFacade.class);

	public EventFacade() {
		super();
	}

	public EventFacade(Session openedSession) {
		super(openedSession);
	}
	
	public List<Event> getEvents(EventForm listForm){
		List<Event> results = null;
		boolean eventsLoaded;
		Session sess = null;
   	 try {

   		StringBuffer hql = new StringBuffer();
   		ArrayList<Object> params = new ArrayList<Object>();
 		ArrayList<NullableType> types = new ArrayList<NullableType>();
 		
 		
 		/*if ((listForm.getCityCode() != null && listForm.getCityCode().intValue() > 0)
					|| (listForm.getFromDate() != null && listForm.getFromDate().length() > 0)
					|| (listForm.getToDate() != null && listForm.getToDate().length() > 0)) {
    		*/			
 			
				//Selecciona los codigos de eventos que cumplen con las condiciones
 				eventsLoaded = false;
 						hql.append(" select distinct eventDate.event.code  ");
 						hql.append(" from EventDate eventDate ");
 						hql.append(" where 1 = 1 ");
				
				
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
			/*	
			} else {
				hql.append(" from Event as event ");
				hql.append("where 1 = 1 ");
			}
			*/
 		
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
 		        		
 		
	 		hql.append(" and event.enabled = ? ");
			params.add( Globals.TRUE );
			types.add(Hibernate.BOOLEAN);

 		
 		if (listForm.getIsEvent() != null) {
 			hql.append(" and event.seminary = ? ");
 			params.add( Globals.FALSE );
			types.add(Hibernate.BOOLEAN);
 		}

 		//solo ordenar si ya se esta cargando eventos
 		if ( listForm.getOrderField() != null && eventsLoaded) {
 			System.out.println("aqui"+listForm.getOrderField() );
 			hql.append(" order by event.");
 			hql.append(listForm.getOrderField());
 			if ( listForm.getOrderAsc().booleanValue() ) {
 				hql.append(" asc ");
 			} else {
 				hql.append(" desc ");
 			}
 			
 		} /*else {
 			hql.append("order by event.index asc");
   	 }*/
 		
  		sess = getHibernateSession();
  		Query query = sess.createQuery(hql.toString());
			
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, params.get(i), types.get(i));
			}
			
			query.setCacheable(true);
			results = (ArrayList<Event>)query.list ();
			
			if (results.size()!=0) {
			if (!eventsLoaded ) { //se trata de una lista de codigos de eventos en que ocurren en la ciudad indicada
				//debe cargar esos eventos a memoria
				hql = hql.delete(0, hql.length());
				System.out.println("aca");
				hql.append(" from Event as event where event.code in (:codes) ");
				if ( listForm.getOrderField() != null && eventsLoaded) {
     			hql.append(" order by event.index");
     			hql.append(listForm.getOrderField());
	     			if ( listForm.getOrderAsc().booleanValue() ) {
	     				hql.append(" asc ");
	     			} else {
	     				hql.append(" desc ");
	     			}
     			}
				else {
		 			hql.append("order by event.index asc");
				}
     			results = (ArrayList<Event>)sess.createQuery(hql.toString()).setParameterList("codes", results, Hibernate.LONG).list();
			}
				for (int i=0; i < results.size(); i++) {
					Event event = (Event) results.get(i);
					Hibernate.initialize(event.getDates());
				}
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
		
		return results;
	}

	
	public Event read(EventForm form) 
    throws Exception {
	
	Session sess = null;
	
	try {
	
		//Long[] codes = form.getCodes();
		Long code = null;
		
		/*for (int i = 0; i < codes.length; i++) {
	    	if(codes[i].longValue() > 0)  //A veces llega un code basura '0' que debe ser ignorado.
	    		code = codes[i]; 
		}*/
		
		code = form.getCode();
		
		if (this.openedSession != null && this.openedSession.isOpen()) {
			//Si la session esta abierta solo debe usarse una referencia a la conexion
			sess = this.openedSession; 
		} else { //Abrir una nueva conexion q debe cerrarse luego
			sess = this.getHibernateSession();
		}
		Event event = (Event) sess.get(Event.class, code);
		Hibernate.initialize(event.getDates());
		Hibernate.initialize(event.getImages());
		
       	Query query = sess.createFilter(event.getDocuments(),"select count(this.code) where this.isEnabled=? ").setBoolean(0, Globals.TRUE);
		event.setDocSize((Long)query.uniqueResult());
		
		//return (Event) sess.get(Event.class, code);
		return event;

	}catch (Exception e) {
		logger.error(e.getMessage(),e);
		throw new Exception("Unexpected error while trying to read Event");
		
	} finally {
		if (sess != null)
			try {
				sess.clear();
				sess.close();
			} catch (Exception e) {
			}
	}
}


}
