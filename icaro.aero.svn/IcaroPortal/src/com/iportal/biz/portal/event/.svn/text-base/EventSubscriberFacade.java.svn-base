package com.iportal.biz.portal.event;

import java.util.GregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseBussinessLogic;
import com.iportal.model.EventSubscriber;
import com.iportal.model.event.Event;
import com.iportal.model.event.EventDate;

public class EventSubscriberFacade extends BaseBussinessLogic {

	private static Log logger = LogFactory.getLog(EventSubscriberFacade.class);

	public EventSubscriber save(EventSubscriberForm form)
	throws Exception {
		
		Session sess = null;
 		Transaction tx = null;
 		EventSubscriber eventSubscriber = new EventSubscriber();
 		Event event= null;
 		EventDate eventDate = null;
 		 		
		try {
			
			sess = getHibernateSession();
			GregorianCalendar calendar = new GregorianCalendar();
			event = (Event) sess.load(Event.class, form.getEventCode());
			eventDate = (EventDate) sess.load(EventDate.class, form.getEventDateCode());
			
			PropertyUtils.copyProperties(eventSubscriber, form);
			eventSubscriber.setCode(null);
			eventSubscriber.setEvent(event);
			eventSubscriber.setEventDate(eventDate);
			eventSubscriber.setSystemDate(calendar);
			
			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(eventSubscriber);
 			tx.commit();
 			
 			Hibernate.initialize(eventSubscriber.getEvent().getDates());
 			
		}catch (Exception e) {
 			if (tx != null) {
 			    tx.rollback();
 			}
			logger.error(e.getMessage(),e);
			throw new Exception("Unexpected error while trying to save Event");
			
		} finally {
 			if (sess != null)
 				try {
 					sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}

		
		return eventSubscriber;
		
	}
	

}
