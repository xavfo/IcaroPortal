/*
 * Created Jul 8, 2006
 *	SessionFactoryContainer.java
 */
package com.yage.hibernate;

import org.hibernate.SessionFactory;

/**
 * Contenedor de objetos SessionFactory que se registran en
 * SessionFactoryManager para manejar las conexiones de hibernate.
 *  
 * @author YAGE (hernan)
 * @version 1.0
 *
 */
public class SessionFactoryContainer {

	SessionFactory sessionFactory;
	
	String dialectClassName;
	
	Integer jdbcBatchSize;
	
	/**
	 * 
	 */
	public SessionFactoryContainer() {
		super();
		this.clear();
		
	}

	
	/**
	 * @param sessionFactory
	 * @param dialectClassName
	 */
	public SessionFactoryContainer(SessionFactory sessionFactory, String dialectClassName, Integer jdbcBatchSize) {
		super();
		this.sessionFactory = sessionFactory;
		this.dialectClassName = dialectClassName;
		this.jdbcBatchSize   = jdbcBatchSize;
		
	}


	private void clear () {
		this.sessionFactory = null;
		this.dialectClassName = null;
		this.jdbcBatchSize = null;
		
	}


	/**
	 * @return Returns the dialectClassName.
	 */
	public String getDialectClassName() {
		return dialectClassName;
	}


	/**
	 * @return Returns the jdbcBatchSize.
	 */
	public Integer getJdbcBatchSize() {
		return jdbcBatchSize;
	}


	/**
	 * @return Returns the sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/**
	 * @param dialectClassName The dialectClassName to set.
	 */
	public void setDialectClassName(String dialectClassName) {
		this.dialectClassName = dialectClassName;
	}


	/**
	 * @param jdbcBatchSize The jdbcBatchSize to set.
	 */
	public void setJdbcBatchSize(Integer jdbcBatchSize) {
		this.jdbcBatchSize = jdbcBatchSize;
	}


	/**
	 * @param sessionFactory The sessionFactory to set.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	
}
