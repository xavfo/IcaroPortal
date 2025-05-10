/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import com.iportal.biz.AuditRowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.system.SysAudit;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author andresg
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysAuditAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(SysAuditAction.class);


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
     @SuppressWarnings("unchecked")
    public ActionForward list (
             ActionMapping mapping,
             ActionForm form,
             HttpServletRequest request,
             HttpServletResponse response)
     throws Exception {

         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }

         SysAuditForm listForm = (SysAuditForm) form;

         Session sess = null;
         List<AuditRowItem> results = null;

         try {

             StringBuffer sql = new StringBuffer();
             ArrayList<Object> params = new ArrayList<Object>();
            ArrayList<NullableType> types = new ArrayList<NullableType>();

             if ( listForm.getSearch().booleanValue() ) {
               sql.append("select new com.iportal.biz.AuditRowItem(sysAudit.code, sysAudit.log.userName, " +
                    "sysAudit.log.roleName, sysAudit.date, sysAudit.action, sysAudit.entityName, sysAudit.className, sysAudit.entity) ");
                 sql.append("from SysAudit as sysAudit ");
                sql.append("where 1 = 1 ");

                if (listForm.getUserName() != null && listForm.getUserName().length() != 0L ) {
                    String userName = "%" + listForm.getUserName() + "%";
                    sql.append("and sysAudit.log.userName like ? ");
                    params.add(userName);
                    types.add(Hibernate.STRING);
                }

                if (listForm.getFromDate() != null && listForm.getFromDate().length() > 0) {
                    sql.append(" and  sysAudit.date >= ? ");
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
                    sql.append(" and sysAudit.date < ? ");
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

                if (listForm.getActionString() != null && listForm.getActionString().length() != 0L ) {
                    String action = "%" + listForm.getActionString() + "%";
                    sql.append("and sysAudit.action like ? ");
                    params.add(action);
                    types.add(Hibernate.STRING);
                }

                if (listForm.getClassName() != null && listForm.getClassName().length() != 0L ) {
                    String className = "%" + listForm.getClassName() + "%";
                    sql.append("and sysAudit.className like ? ");
                    params.add(className);
                    types.add(Hibernate.STRING);
                }

                sql.append("order by sysAudit.log.userName");

                sess = getHibernateSession();

                 Query query = sess.createQuery(sql.toString());

                for (int i = 0; i < types.size(); i++) {
                    query.setParameter(i, params.get(i), types.get(i));
                }
                 results = query.list();
             }
             /*
            if (results != null){
                for (int i = 0; i < results.size(); i++){
                    AuditRowItem auditItem = (AuditRowItem)results.get(i);
                    String nameValue = auditItem.getNameValue();
                    if (nameValue != null && nameValue.equals(Globals.CONTENT)){
                        Long entityCode = auditItem.getEntityCode();
                        Content content = (Content)sess.load(Content.class, entityCode);
                        Integer level = content.getLevel();
                        if (level != null){
                            auditItem.setLevel(level);
                        }
                    }
                }
            }
            */

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
         request.setAttribute("sysAuditList", results);

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
         SysAuditForm sysAuditForm = (SysAuditForm) form;
         sysAuditForm.reset(mapping, request);

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

//         HttpSession session = request.getSession();
         ActionMessages messages = new ActionMessages();

         // Gets the action form
         SysAuditForm sysAuditForm = (SysAuditForm) form;

         Session sess = null;
         SysAudit sysAudit = null;

         try {
             sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);

             sysAudit = (SysAudit) sess.load(SysAudit.class, sysAuditForm.getCode());
             request.setAttribute("sysAudit",sysAudit);

             PropertyUtils.copyProperties(sysAuditForm,sysAudit);

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

//        HttpSession session = request.getSession();
        ActionMessages messages = new ActionMessages();

        // Gets the action form
        SysAuditForm sysAuditForm = (SysAuditForm) form;

        Session sess = null;
        Transaction tx = null;
        SysAudit sysAudit = null;

        try {
            Long codes[] = sysAuditForm.getCodes();

            if ( codes != null && codes.length > 0) {
                sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);

                tx = sess.beginTransaction();
                for (int i = 0; i < codes.length; i++) {
                    sysAudit = (SysAudit) sess.load(SysAudit.class, codes[i]);
                    sess.delete(sysAudit);
                    SysAuditHelper.audit(this, request, sysAudit, Globals.AUDIT, Globals.AUDIT_DELETE, sess);
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
