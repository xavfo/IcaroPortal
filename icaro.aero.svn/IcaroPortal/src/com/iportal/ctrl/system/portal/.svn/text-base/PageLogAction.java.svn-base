/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

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
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.PageLog;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * Maneja y genera reportes del log de página.
 * 
 * @author andresg
 * @version 1.0
 * @see com.yage.struts.action.BaseDispatchAction
 */
public class PageLogAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(PageLogAction.class);
    
    /**
     * List Action<BR>
     * Lista los recursos del tipo (sección, inscripción a charla, inscripción a plan de seguro, inscripción a red vitacard, 
     * resumen de charla articulo de interés, comentarios y sugerencias, reclamos, líneas de productos o producto) 
     * ({@link com.iportal.ctrl.system.portal.PageLogForm#getResourceType()}) seleccionado en la forma 
     * {{@link com.iportal.ctrl.system.portal.PageLogForm}, con el número de visita de cada uno, en un intervalo de tiempo 
     * ({@link com.iportal.ctrl.system.portal.PageLogForm#getFromDate()} - {@link com.iportal.ctrl.system.portal.PageLogForm#getToDate()}).
     * Reporte de recurso mas visitado. 
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
   	 
   	 if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        
   	 	PageLogForm listForm = (PageLogForm) form;
        
        Session sess = null;
        List results = null;

        try {
        	
        	if ( listForm.getSearch().booleanValue() ) {
        		
        		StringBuffer sql = new StringBuffer();
        		ArrayList<Object> params = new ArrayList<Object>();
                ArrayList<NullableType> types = new ArrayList<NullableType>();
        		
                sql.append(" select new com.iportal.biz.RowItem (pageLog.resourceCode, pageLog.resourceDescription, pageLog.resourceType, count(pageLog.code)) ");
    			sql.append(" from PageLog as pageLog ");
				sql.append(" where 1 = 1 ");
       		
    
    			if (listForm.getFromDate() != null && listForm.getFromDate().length() > 0) {
    				sql.append(" and  pageLog.date >= ? ");
    				Calendar from = new GregorianCalendar();
    				from.setTime(listForm.getFrom().getTime());
    				from.set(Calendar.HOUR,0);
    				from.set(Calendar.MINUTE,0);
    				from.set(Calendar.SECOND,0);
    				from.set(Calendar.MILLISECOND,0);	
    				params.add(from);
    				types.add(Hibernate.CALENDAR);									
   					}
       		
    			if (listForm.getToDate() != null && listForm.getToDate().length() > 0) {
    				sql.append(" and pageLog.date < ? ");
    				Calendar to = new GregorianCalendar();
    				to.setTime(listForm.getTo().getTime());
    				to.set(Calendar.HOUR,0);
    				to.set(Calendar.MINUTE,0);
    				to.set(Calendar.SECOND,0);
    				to.set(Calendar.MILLISECOND,0);
    				to.add(Calendar.DAY_OF_MONTH,1);
    				params.add(to);
    				types.add(Hibernate.CALENDAR);									
    			}
				
				if (listForm.getResourceType() != null && listForm.getResourceType().length() != 0L ) {
					String action = "%" + listForm.getResourceType() + "%";
					sql.append(" and pageLog.resourceType like ? ");
					params.add(action);
					types.add(Hibernate.STRING);
				}
				
				sql.append(" group by pageLog.resourceCode, pageLog.resourceDescription, pageLog.resourceType  ");
				sql.append(" order by 4 desc, 2 desc");
            		
           		sess = getHibernateSession();

        		Query query = sess.createQuery(sql.toString());
                   
                for (int i = 0; i < types.size(); i++) {
                       query.setParameter(i, params.get(i), types.get(i));
                }
        		results = query.list();
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
		request.setAttribute("pageLogList", results);
		request.setAttribute("fromDate", listForm.getFromDate());
		request.setAttribute("toDate", listForm.getToDate());

		return mapping.findForward(Globals.FORWARD_LIST);
    }

    /**
     * ListAll Action<BR>
     * Lista todos los recursos del tipo (sección, inscripción a charla, inscripción a plan de seguro, inscripción a red vitacard, 
     * resumen de charla articulo de interés, comentarios y sugerencias, reclamos, líneas de productos o producto) y nombre 
     * ({@link com.iportal.ctrl.system.portal.PageLogForm#getResourceType()}, {@link com.iportal.ctrl.system.portal.PageLogForm#getResourceDescription()})
     * seleccionado en la forma {@link com.iportal.ctrl.system.portal.PageLogForm}, en un intervalo de tiempo 
     * ({@link com.iportal.ctrl.system.portal.PageLogForm#getFromDate()} - {@link com.iportal.ctrl.system.portal.PageLogForm#getToDate()}).
     *  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward listAll (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	PageLogForm listForm = (PageLogForm) form;
        
        Session sess = null;
        List results = null;

        try {
    		sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);

    		StringBuffer sql = new StringBuffer();
    		ArrayList<Object> params = new ArrayList<Object>();
            ArrayList<NullableType> types = new ArrayList<NullableType>();
    		
    		if ( listForm.getSearch().booleanValue() ) {
    			sql.append("from PageLog as pageLog ");
    			sql.append("where 1 = 1 ");

    			if (listForm.getFromDate() != null && listForm.getFromDate().length() > 0) {
    				sql.append(" and  pageLog.date >= ? ");
    				Calendar from = new GregorianCalendar();
    				from.setTime(listForm.getFrom().getTime());
    				from.set(Calendar.HOUR,0);
    				from.set(Calendar.MINUTE,0);
    				from.set(Calendar.SECOND,0);
    				from.set(Calendar.MILLISECOND,0);	
    				params.add(from);
    				types.add(Hibernate.CALENDAR);									
				}
   		
    			if (listForm.getToDate() != null && listForm.getToDate().length() > 0) {
    				sql.append(" and pageLog.date < ? ");
    				Calendar to = new GregorianCalendar();
    				to.setTime(listForm.getTo().getTime());
					to.set(Calendar.HOUR,0);
					to.set(Calendar.MINUTE,0);
					to.set(Calendar.SECOND,0);
					to.set(Calendar.MILLISECOND,0);
					to.add(Calendar.DAY_OF_MONTH,1);
					params.add(to);
					types.add(Hibernate.CALENDAR);									
    			}
				
				if (listForm.getResourceType() != null && listForm.getResourceType().length() != 0L ) {
					String action = "%" + listForm.getResourceType() + "%";
					sql.append("and pageLog.resourceType like ? ");
					params.add(action);
					types.add(Hibernate.STRING);
				}
    			
    			if (listForm.getResourceDescription()!= null && listForm.getResourceDescription().length() != 0L ) {
    				String action = "%" + listForm.getResourceDescription() + "%";
    				sql.append("and pageLog.resourceDescription like ? ");
    				params.add(action);
    				types.add(Hibernate.STRING);
    			}
       		    		
    			sql.append("order by pageLog.date ");
        		
    			sess = getHibernateSession();

     			Query query = sess.createQuery(sql.toString());
                
                for (int i = 0; i < types.size(); i++) {
                    query.setParameter(i, params.get(i), types.get(i));
                }
     			results = query.list();
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
		request.setAttribute("pageLogList", results);
		
		if ( listForm.getIsReport().booleanValue() ) {
			request.setAttribute("pageLog", listForm);
			return mapping.findForward(Globals.FORWARD_FORM);
		}else{
			return mapping.findForward(Globals.FORWARD_LIST);
		}
    }  
    
    /**
     * Read Action<BR>
     * Lee un recurso.<BR>
     * Toma el codigo del recursos de la forma {@link com.iportal.ctrl.system.portal.PageLogForm}
     * para cargalo en el objeto {@link com.iportal.model.PageLog} y ponerlo en request. 
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

		HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		PageLogForm pageLogForm = (PageLogForm) form;
		
		Session sess = null;
		PageLog pageLog = null;
		
		try {
			sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			
			pageLog = (PageLog) sess.load(PageLog.class, pageLogForm.getCode());
			
			request.setAttribute("isReport", pageLogForm.getIsReport().booleanValue()?new Integer(1):new Integer(0));
			request.setAttribute("fromDate", pageLogForm.getFromDate());
			request.setAttribute("toDate", pageLogForm.getToDate());
			request.setAttribute("pageLog", pageLog);
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
     * Delete Action<BR>
     * Elimina uno o varios recursos.<BR>  
     * Toma el o los codigos de los logs de página de la forma {@link com.iportal.ctrl.system.portal.PageLogForm}
     * para cargalo en el objeto {@link com.iportal.model.PageLog} y eliminarlos. 
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

		HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		PageLogForm pageLogForm = (PageLogForm) form;
		
		Session sess = null;
		Transaction tx = null;
		PageLog pageLog = null;

		try {
		    Long codes[] = pageLogForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	pageLog = (PageLog) sess.load(PageLog.class, codes[i]);
					sess.delete(pageLog);
					SysAuditHelper.audit(this, request, pageLog, pageLog.toString(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
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