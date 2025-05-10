package com.iportal.biz.system.portal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Language;
import com.iportal.model.portal.Content;
import com.iportal.model.portal.Menu;
import com.iportal.model.portal.MenuLanguage;
import com.yage.Globals;

public class MenuFacade extends BaseBussinessLogic{

	private static Log logger = LogFactory.getLog(MenuFacade.class);
	
    private Action action;
    //private HttpServletRequest request;
    
	public MenuFacade() {
		super();
	}
    
    public MenuFacade( Action action) {
        super();
      //  this.request = request;
        this.action = action;
    }

	public List<MenuBean> getAll(MenuForm menuForm, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		List result = null;
		List<MenuBean> menuList = null;
		try {
			HttpSession session = request.getSession();
			Language language = (Language)session.getAttribute(Constants.LANGUAGE_KEY);
			
			StringBuffer sql = new StringBuffer();
		//	List<Object> params = new ArrayList<Object>();
		//	List<NullableType> types = new ArrayList<NullableType>();
			
			sql.append("FROM Menu as menu ");
			
			/*
			 * Tratar de hacer esto con una consulta solamente
			types.add(Hibernate.LONG);
			params.add(language.getCode());
			
			sess = getHibernateSession();
			Query query = sess.createQuery(sql.toString());
			
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, params.get(i), (Type) types.get(i));
			}*/
			
			sess = getHibernateSession();
			Query query = sess.createQuery(sql.toString());
			
			result = query.list();
			if (result!= null && result.size()>0){
				menuList = new ArrayList<MenuBean>();
				Iterator it = result.iterator();
				while (it.hasNext()) {
					MenuBean item = new MenuBean();
					Menu currentMenu = (Menu) it.next();
					PropertyUtils.copyProperties(item, currentMenu);
					MenuLanguage mLang = currentMenu.getMenuLanguage(language);
					if (mLang != null){
						item.setName(mLang.getName());
					}
					menuList.add(item);
				}
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Menues");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return menuList;
	}
	
	public Menu getByCode(MenuForm menuForm, HttpServletRequest request) 
	throws Exception{
		Session sess = null;
		MenuLanguage menuLanguage = null;
		Menu menu = null;
		try {
			Long code = null;
			
			if( menuForm.getCode() != null && menuForm.getCode().longValue() > 0L ) //The item come from a tab.				
				code = menuForm.getCode();
			else 	
				code = menuForm.getCodes()[0];
				
			if ( code==null )
				throw new Exception("Null is not a valid value to code in Menu Object");
			
			if (code.intValue() < 1) 
				throw new Exception(code + " is not a valid value to code in Menu Object");
			
			if ( code==null )
				throw new Exception("Null is not a valid value to code in Menu Object");
			
			HttpSession session = request.getSession();
			Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
			
			if ( language == null )
				throw new Exception("Language not defined");
			
			
			sess = getHibernateSession();
			
			menu = (Menu) sess.get(Menu.class, code);
			
			if (menu == null )
				throw new Exception("Does not exist Menu data for the code : " + code);
			
			PropertyUtils.copyProperties(menuForm, menu);			
			
			menuLanguage = menu.getMenuLanguage(language);
			
			/*
			 * Fill the menuLanguage Info 
			 */
			if ( menuLanguage != null ){
				menuForm.setMenuLanguageCode(menuLanguage.getCode());
				menuForm.setName(menuLanguage.getName());
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

		return menu;
	}
	
	public void save(MenuForm menuForm, HttpServletRequest request) 
	throws Exception{
		Menu menu = null;
		MenuLanguage menuLang = null;
		Transaction tx = null;
		Session sess = null;
		try {
			HttpSession session = request.getSession();
			Language language =  (Language)session.getAttribute(Constants.LANGUAGE_KEY);
			
			if ( language == null )
				throw new Exception("Language not defined");
			
			sess = getHibernateSession();
			if (menuForm.getCode()!=null && menuForm.getCode().longValue() > 0 ){
				menu = (Menu)sess.get(Menu.class, menuForm.getCode());
			} else {
				menu = new Menu();
				menuForm.setCode(null);
			}
			PropertyUtils.copyProperties(menu, menuForm);
			
			if (menuForm.getMenuLanguageCode()!= null && menuForm.getMenuLanguageCode().longValue() >0) {
				menuLang = (MenuLanguage) sess.get(MenuLanguage.class, menuForm.getMenuLanguageCode());
			} else {
				menuLang = new MenuLanguage();
				menuForm.setMenuLanguageCode(null);
			}
			
			menuLang.setName(menuForm.getName());
			menuLang.setLanguage(language);
			
			
			tx = sess.beginTransaction();
				sess.saveOrUpdate(menu);
				menuLang.setMenu(menu);
				sess.save(menuLang);
			tx.commit();

            // Audit Transaction
            if (menuForm.getCode() == null ) {
                SysAuditHelper.audit(action, request, menuLang, menuLang.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(action, request, menuLang, menuLang.getName(), Globals.AUDIT_UPDATE);
            }

		} catch (Exception e) {
			if (tx != null) {
 			    tx.rollback();
 			}
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to save Content");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
	}
	
	
	public void delete(Long[] codes) 
	throws Exception{
		
		Transaction tx = null;
		Session sess = null;
		Content content = null;
		try {
			if ( codes != null && codes.length > 0) {
				sess = getHibernateSession();
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	content = (Content) sess.load(Content.class, codes[i]);
					sess.delete(content);
			    }
				tx.commit();
		    }
		} catch (Exception e) {
			if (tx != null) {
 			    tx.rollback();
 			}
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to delete Contents");
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
