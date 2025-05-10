/*
 * Created on May 9, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.biz;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.iportal.Constants;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author hleon
 * 
 * Clase de negocio para ejecutar operaciones y servicios 
 * en la aplicaci�n como cambios en objetos del modelo persisten 
 * o servicio de registros de auditoria para llamadas a 
 * Servicios Web entre otros
 */
public class BaseHelper {

	private static ResourceBundle applicationBundleES = null;
	
	private static ResourceBundle applicationBundleEN = null;
	
	private static ResourceBundle messageBundleES = null;
	
	private static ResourceBundle messageBundleEN = null;
	
	private static ResourceBundle databaseBundle = null;
	
	/**
	 * Gets a hibernate session from the SessionFactoryManager object.
	 * 
	 * @return	A hibernate session
	 * @throws HibernateException
	 */
	public static Session getHibernateSession() throws HibernateException {
		return getHibernateSession(HibernatePlugIn.DEFAULT_ID);
	}


	/**
	 * Gets a hibernate session from the SessionFactoryManager object.
	 * 
	 * @param id	The attribute under wich the SessionFactory object is stored
	 * 				in the application scope.
	 * @return		A hibernate session
	 * @throws HibernateException
	 */
	public static Session getHibernateSession(String id) throws HibernateException {

		return SessionFactoryManager.getSession(id);
	}
	
	
	/**
	 * Devuelve un mensaje del archivo de Recursos Application con el locale 
	 * de la session conectada
	 * @param key clave o entrada del archivos de recursos cuyo mensaje se desea obtener
	 * @param arguments argumentos para concatenar con el mensaje de ser necesario
	 * @return el mensaje formateado para presentar en pantalla
	 */
	public static String getApplicationBundleMessage(String key, Object[] arguments) {
	 	
		if(applicationBundleES == null){
			
			applicationBundleES = ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE, new Locale("es"));
		}
	
		return MessageFormat.format(applicationBundleES.getString(key), arguments);
	}

	
	
	/**
	 * Devuelve un mensaje del archivo de Recursos Application con el locale 
	 * de la session conectada
	 * @param key clave o entrada del archivo com.iportal.res.Application de recursos cuyo mensaje se desea obtener
	 * @return el mensaje del archivo de mensajes para presentar en pantalla
	 */
	public static String getApplicationBundleMessage(String key) {
	 	
		if(applicationBundleES == null){
			applicationBundleES = ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE, new Locale("es"));
		}
		return applicationBundleES.getString(key);
	}
	
	private static ResourceBundle getApplictionBundle (Locale locale) {
		
		if(applicationBundleES == null && locale.toString().equals("es")){
			applicationBundleES = ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE, locale);
		}
			
		if(applicationBundleEN == null && locale.toString().equals("en")){
			applicationBundleEN = ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE_EN, locale);
		}
		
		return ("en".equals(locale))?applicationBundleEN:applicationBundleES;

	}
	
	private static ResourceBundle getMessageBundle (Locale locale) {
		
		if(messageBundleES == null && locale.toString().equals("es")){
			messageBundleES = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_ES, locale);
		}
			
		if(messageBundleEN == null && locale.toString().equals("en")){
			messageBundleEN = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_EN, locale);
		}
		
		return ("en".equals(locale.toString()))?messageBundleEN:messageBundleES;

	}

	public static String getApplicationBundleMessage(String key, Locale locale) {
	 	
		if (locale != null) {
			ResourceBundle bundle = getApplictionBundle(locale);
			
			return bundle.getString(key);
		} else {
			return getApplicationBundleMessage (key);
		}
	}
	
	public static String getMessageBundleMessage(String key, Locale locale) {
	 	
		if (locale != null) {
			ResourceBundle bundle = getMessageBundle(locale);
			
			return bundle.getString(key);
		} else {
			return getMessageBundleMessage (key);
		}
	}
	
	
	
	
	
	/**
	 * Devuelve un mensaje del archivo de Recursos Application con el locale 
	 * de la session conectada
	 * @param key clave o entrada del archivo com.iportal.res.Application de recursos cuyo mensaje se desea obtener
	 * @return el mensaje del archivo de mensajes para presentar en pantalla
	 */
	public static String getApplicationBundleMessageEs(String key) {
	 	
		if(applicationBundleES == null){
			applicationBundleES = ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE_ES);
		}
	
		return applicationBundleES.getString(key);
	}
	
	
	
	
	
	

	/**
	 * Devuelve un mensaje del archivo de Recursos Messages con el locale 
	 * de la session conectada
	 * @param key clave o entrada del archivo com.iportal.res.Messages de recursos cuyo mensaje se desea obtener
	 * @param arguments argumentos para concatenar con el mensaje de ser necesario
	 * @return el mensaje formateado para presentar en pantalla
	 */
	public static String getMessagesBundleMessage(String key, Object[] arguments) {
	 	
		if(messageBundleES == null){
			messageBundleES = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE, new Locale("es"));
		}
		return MessageFormat.format(messageBundleES.getString(key), arguments); 

	}
	
	/**
	 * Devuelve un mensaje del archivo de Recursos Messages con el locale 
	 * de la session conectada
	 * @param key clave o entrada del archivo com.iportal.res.Messages de recursos cuyo mensaje se desea obtener
	 * @return el mensaje del archivo de mensajes para presentar en pantalla
	 */
	public static String getMessageBundleMessage(String key) {
	 	
		if(messageBundleES == null){
			messageBundleES = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE, new Locale("es"));
		}
		return messageBundleES.getString(key);
	}
	
	public static String getDatabaseBundleMessage(String key, String suffix) {
		boolean isValid = true;
		String result = new String();
		StringBuffer bundleName = new StringBuffer(Constants.DATABASE_BUNDLE);
		
		if (!suffix.equals(Constants.ENGLISH_LOCALE)){
			bundleName.append("_");
			bundleName.append(suffix);
		}
		
		if(databaseBundle == null){
			isValid = false;
		} else if (!databaseBundle.getLocale().toString().equals(suffix)){
			isValid = false;
		}
		if (!isValid)
			try {
				databaseBundle = ResourceBundle.getBundle(bundleName.toString());
			} catch (Exception e) {
				try {
					databaseBundle = ResourceBundle.getBundle(Constants.DATABASE_BUNDLE);
				} catch (Exception e1) {
				}
			}
		if (databaseBundle == null)
			result = "";
		else
			result = databaseBundle.getString(key);
		
		return result;
	}
}
