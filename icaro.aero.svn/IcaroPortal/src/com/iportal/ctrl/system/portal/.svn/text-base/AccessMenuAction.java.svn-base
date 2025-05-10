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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.system.portal.MenuBean;
import com.iportal.model.Access;
import com.iportal.model.AccessUrl;
import com.iportal.model.Language;
import com.iportal.model.portal.AccessMenu;
import com.iportal.model.portal.DisplayMode;
import com.iportal.model.portal.Menu;
import com.iportal.model.portal.MenuLanguage;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * java comment AccessMenuAction
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 */
public class AccessMenuAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(AccessMenuAction.class);
        
    
    protected MenuBean getMenuBean (Long languageCode, Long menuCode, Session sess ) {
    	
			StringBuffer hql = new StringBuffer();
			
			hql.append(" select new com.iportal.biz.system.portal.MenuBean (   ");
			hql.append(" menuLang.menu.code, menuLang.menu.enabled, menuLang.name)  ");
			hql.append(" FROM MenuLanguage as menuLang ");
			hql.append(" WHERE menuLang.menu.code = ? ");
			hql.append(" AND menuLang.language.code = ? ");
			
			Query query = sess.createQuery(hql.toString());
			query.setLong(0, menuCode);
			query.setLong(1, languageCode);
			
			MenuBean menuBean = (MenuBean) query.uniqueResult();
			
			return menuBean;

    }
    
    protected MenuLanguage getMenuLanguage (Long languageCode, Long menuCode, Session sess ) {
    	
		StringBuffer hql = new StringBuffer();
		
		hql.append(" FROM MenuLanguage as menuLang ");
		hql.append(" WHERE menuLang.menu.code = ? ");
		hql.append(" AND menuLang.language.code = ? ");
		
		Query query = sess.createQuery(hql.toString());
		query.setLong(0, menuCode);
		query.setLong(1, languageCode);
		
		MenuLanguage menuLanguage = (MenuLanguage) query.uniqueResult();
		
		return menuLanguage;

}
    
    @SuppressWarnings("unchecked")
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	List<AccessMenu> results = null;
    	Session sess = null; 
		Language language = (Language) request.getSession()
			.getAttribute(Constants.LANGUAGE_KEY);

        try {
        	AccessMenuForm accessMenuForm = (AccessMenuForm) form;
    		
			sess = getHibernateSession();

			StringBuffer hql = new StringBuffer();
			
			hql.append(" FROM AccessMenu as accessMenu ");
			hql.append(" WHERE accessMenu.menuLanguage.menu.code = ? ");
			hql.append(" AND accessMenu.menuLanguage.language.code = ? ");
			hql.append(" Order by accessMenu.order ");
			
			Long menuCode = (accessMenuForm.getItemCode()!= null && accessMenuForm.getItemCode().longValue() > 0L)?accessMenuForm.getItemCode():accessMenuForm.getCode();
			Query query = sess.createQuery(hql.toString());
			query.setLong(0, menuCode);
			query.setLong(1, language.getCode());
			
			results = query.list();
			
			MenuBean menuBean = null;
			if (results.size()> 0) {
				AccessMenu accessMenu = (AccessMenu)results.get(0);
				menuBean = new MenuBean (accessMenu.getMenuLanguage().getMenu().getCode(), accessMenu.getMenuLanguage().getMenu().getEnabled(), accessMenu.getMenuLanguage().getName() );
			} else {
				// NO hay menu items debe cargar el menu con el lenguage correspondiente
				
				menuBean = this.getMenuBean(language.getCode(), menuCode, sess);
				
			}

        	
        	
    		request.setAttribute("currentMenu", menuBean);
    		
        } catch (Exception e) {
        	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}

        
		// Save the List of results in request scope
		request.setAttribute("accessMenuList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }


    public ActionForward create (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	AccessMenuForm accessMenuForm = (AccessMenuForm) form;
    	accessMenuForm.reset(mapping, request);
        
        ActionMessages messages = new ActionMessages();
		Language language = (Language) request.getSession()
		.getAttribute(Constants.LANGUAGE_KEY);

        Session sess = null;
        MenuBean menuBean = null;
        try {
        	sess = getHibernateSession();
        	menuBean = this.getMenuBean(language.getCode(), accessMenuForm.getItemCode(), sess);
        	if (menuBean != null) {
        		request.setAttribute("currentMenu", menuBean);
        	}
        } catch (Exception e) {
        	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
        	
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }

    public ActionForward read (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ActionMessages messages = new ActionMessages();
    	AccessMenuForm accessMenuForm = (AccessMenuForm) form;
		
		Session sess = null;
		AccessMenu accessMenu = null;
		
		MenuBean menuBean = null;
		try {
			Long code = null;
			
			if( accessMenuForm.getCode() != null && accessMenuForm.getCode().longValue() > 0L ) //The item come from a tab.				
				code = accessMenuForm.getCode();
			else 	
				code = accessMenuForm.getCodes()[0];
			
			sess = getHibernateSession();
			
			accessMenu = (AccessMenu) sess.load(AccessMenu.class, code);
			
			PropertyUtils.copyProperties(accessMenuForm, accessMenu);
			
			if (accessMenu.getAccess() != null ) {
				accessMenuForm.setAccessCode(accessMenu.getAccess().getCode());
				accessMenuForm.setAccessDescription(accessMenu.getAccess().getName());
				//guarda el codigo del AccessMenu para no perderlo en el copyPropeties
				code = accessMenu.getCode();
				PropertyUtils.copyProperties(accessMenuForm, accessMenu.getAccess());
				if (accessMenu.getAccess().getAccessUrl() != null) {
					accessMenuForm.setAccessUrlCode(accessMenu.getAccess().getAccessUrl().getCode());
				}
				accessMenuForm.setCode(code);
			}
			
			if (accessMenu.getDisplayMode() != null) {
				accessMenuForm.setDisplayModeCode(accessMenu.getDisplayMode().getCode());
			}
			if(accessMenu.getMenuLanguage() != null) {
				accessMenuForm.setMenuLanguageCode(accessMenu.getMenuLanguage().getCode());
				Menu menu = accessMenu.getMenuLanguage().getMenu();
				menuBean = new MenuBean (menu.getCode(), menu.getEnabled(), accessMenu.getMenuLanguage().getName());
			} else {
				Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);

				menuBean = this.getMenuBean(language.getCode(), accessMenuForm.getItemCode(), sess );
			}
			
     		if (menuBean != null) { 
     			request.setAttribute("currentMenu", menuBean);
     		}

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Contents");
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
        
        ActionMessages messages = new ActionMessages();  		
        
       	//Gets the action form
        AccessMenuForm accessMenuForm = (AccessMenuForm) form;

		this.saveItem(accessMenuForm, messages, request);
       
		 
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
        AccessMenuForm accessMenuForm = (AccessMenuForm) form;

		this.saveItem(accessMenuForm, messages, request);
       
		 
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
		AccessMenuForm accessMenuForm = (AccessMenuForm) form;
		
		Session sess = null;
		Transaction tx = null;
		AccessMenu accessMenu = null;

		try {
		    Long codes[] = accessMenuForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	accessMenu = (AccessMenu) sess.load(AccessMenu.class, codes[i]);
					sess.delete(accessMenu);
                    SysAuditHelper.audit(this, request, accessMenu, accessMenu.getAccess().getName(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.accessMenu", this.getLocale(request)),accessMenu.getAccess().getName(), null);
			
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
    
    
	public void saveItem (AccessMenuForm accessMenuForm, ActionMessages messages, HttpServletRequest request) 
	throws Exception{
		AccessMenu accessMenu = null;
		Access access = null;
		DisplayMode displayMode = null;
		MenuLanguage menuLanguage = null;
		Long accessCode =  null;
		Session sess = null;
		Transaction tx = null;
		MenuBean menuBean = null;
		try {
					
			Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
			
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			
			if( accessMenuForm.getCode() != null && accessMenuForm.getCode().longValue() > 0L ) {				
				accessMenu = (AccessMenu) sess.load(AccessMenu.class, accessMenuForm.getCode());
				Menu menu = accessMenu.getMenuLanguage().getMenu();
				menuBean = new MenuBean (menu.getCode(), menu.getEnabled(), accessMenu.getMenuLanguage().getName());
			} else {
				accessMenu = new AccessMenu();
				accessMenuForm.setCode(null);
				menuLanguage = this.getMenuLanguage(language.getCode(), accessMenuForm.getItemCode(), sess);
				accessMenu.setMenuLanguage(menuLanguage);
				menuBean = new MenuBean (menuLanguage.getMenu().getCode(), menuLanguage.getMenu().getEnabled(), menuLanguage.getName());
				
			}
			
			PropertyUtils.copyProperties(accessMenu, accessMenuForm);
		
			
			if (accessMenuForm.getAccessCode()!= null && accessMenuForm.getAccessCode().longValue()>0L){
				access = (Access) sess.load(Access.class, accessMenuForm.getAccessCode());
				accessCode = access.getCode();
			} else {
				access = new Access ();
			}

			PropertyUtils.copyProperties(access, accessMenuForm);

			if (access.getAccessUrl() == null || (!access.getAccessUrl().getCode().equals(accessMenuForm.getAccessUrlCode()))) {
				AccessUrl accessUrl = (AccessUrl) sess.load(AccessUrl.class, accessMenuForm.getAccessUrlCode());
  				access.setAccessUrl(accessUrl); 				
 			}
			access.setCode(accessCode);
			
			sess.saveOrUpdate(access);
			
			accessMenu.setAccess(access);

			
			if (accessMenuForm.getDisplayModeCode()!= null && accessMenuForm.getDisplayModeCode().longValue()>0L){
				displayMode = (DisplayMode) sess.load(DisplayMode.class, accessMenuForm.getDisplayModeCode());
				accessMenu.setDisplayMode(displayMode);
			}

			
			
			sess.saveOrUpdate(accessMenu);
			tx.commit();
            
			request.setAttribute("currentMenu", menuBean);
			
            // Audit Transaction
            if (accessMenuForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, accessMenu, accessMenu.getAccess().getName(),Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, accessMenu, accessMenu.getAccess().getName(), Globals.AUDIT_UPDATE);
            }
			
			if (accessMenuForm.getCode() == null) {
				sess.refresh(accessMenu);
				accessMenuForm.setCode(accessMenu.getCode());
			}
			
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get save AccessMenu");
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
