/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.List;
import java.util.Locale;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * LanguageAction
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class LanguageAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(LanguageAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        LanguageForm listForm = (LanguageForm) form;
        
        Session sess = null;
        List results = null;

        try {
    		sess = getHibernateSession();

    		StringBuffer sql = new StringBuffer();
    		

    		sql.append("from Language as language ");
    		if ( listForm.getOrderField() != null ) {
        		sql.append("order by language.");
        		sql.append(listForm.getOrderField());
        		if ( listForm.getOrderAsc().booleanValue() ) {
        		    sql.append(" asc ");
        		} else {
        		    sql.append(" desc ");
        		}
    		}
    		
    		results = sess.createQuery(sql.toString()).list();
    		
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
		request.setAttribute("languageList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }


    public ActionForward create (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        LanguageForm languageForm = (LanguageForm) form;
        languageForm.reset(mapping, request);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }


    public ActionForward read (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		LanguageForm languageForm = (LanguageForm) form;
		
		Session sess = null;
		Language language = null;
		
		try {
			sess = getHibernateSession();
			
			language = (Language) sess.load(Language.class, languageForm.getCode());
			
			PropertyUtils.copyProperties(languageForm, language);
			languageForm.setLocaleLang( language.getLocale().getLanguage() );
			
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



    public ActionForward save (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		LanguageForm languageForm = (LanguageForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Language language = null;

		try {
		    sess = getHibernateSession();
		    
			if (languageForm.getCode() != null && languageForm.getCode().longValue() != 0L) {
				language = (Language) sess.load(Language.class, languageForm.getCode());
			} else {
				language = new Language();
				languageForm.setCode(null);
			}
			
			PropertyUtils.copyProperties(language, languageForm);
			language.setLocale( new Locale(languageForm.getLocaleLang()) );

			//Persist data
			tx = sess.beginTransaction();
			sess.saveOrUpdate(language);
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


    public ActionForward delete (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		LanguageForm languageForm = (LanguageForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Language language = null;

		try {
		    Long codes[] = languageForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
					language = (Language) sess.load(Language.class, codes[i]);
					sess.delete(language);			        
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



    public ActionForward setDefault (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		LanguageForm languageForm = (LanguageForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Language language = null;
		List languages = null;

		try {
		    sess = getHibernateSession();
		    
			languages = sess.createQuery("from Language as lang").list();
			
			if (languages != null && languages.size() > 0) {
			    int size = languages.size();

				tx = sess.beginTransaction();
				for (int i = 0; i < size; i++) {
			        language = (Language) languages.get(i);
			        
			        if ( language.getCode().equals(languageForm.getDefaultCode()) ) {
			            language.setDefault(Globals.TRUE);
			        } else {
			            language.setDefault(Globals.FALSE);
			        }
			        
			        sess.saveOrUpdate(language);
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
