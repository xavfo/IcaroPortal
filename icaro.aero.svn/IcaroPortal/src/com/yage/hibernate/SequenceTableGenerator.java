/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.TransactionHelper;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

/**
 * 
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class SequenceTableGenerator extends TransactionHelper 
	implements PersistentIdentifierGenerator, Configurable {

    private static Log logger = LogFactory.getLog(SequenceTableGenerator.class);
    
	private String tableName;
	
	private String sequenceName;
	
	private String columnId;
	
	private String columnValue;
	
	private String query;
	
	private String update;
	
	
	public void configure(Type typetype, Properties params, Dialect dialect) {
		
		this.tableName = PropertiesHelper.getString("table", params, "sequence");
		this.sequenceName = PropertiesHelper.getString("sequence", params, "id");
		this.columnId = PropertiesHelper.getString("columnId", params, "name");
		this.columnValue = PropertiesHelper.getString("columnValue", params, "next_val");

		String schemaName = params.getProperty(SCHEMA);
		if ( schemaName!=null && tableName.indexOf('.')<0 )
			tableName = schemaName + '.' + tableName;
		
		query = "select " + columnValue + " from " + tableName + " where " +  columnId + " = ?";
		//No aplicable en hibernate 3
		//if ( dialect.supportsForUpdate() ) query += " for update";
		update = "update " + tableName + " set " + columnValue + " = ? where " + columnId +  " = ?";
	}

	public synchronized Serializable generate(SessionImplementor session, Object object)
		throws HibernateException {
		long result = ( (Long) doWorkInNewTransaction(session) ).intValue();
		return new Long(result);	
	}


	public Serializable doWorkInCurrentTransaction(Connection conn, String sql)
	throws SQLException {
		
		// This has to be done using a different connection to the
		// containing transaction because the new hi value must
		// remain valid even if the containing transaction rolls
		// back
		long result;
		int rows;
		
		do {
			// The loop ensures atomicity of the
			// select + update even for no transaction
			// or read committed isolation level
			
			PreparedStatement qps = conn.prepareStatement(query);
			
			try {
				qps.setString(1, sequenceName);
				ResultSet rs = qps.executeQuery();
				if ( !rs.next() ) {
					String err = "could not read a hi value - you need to populate the table: " + tableName;
					logger.error(err);
					throw new IdentifierGenerationException(err);
				}
				result = rs.getLong(1);
				rs.close();
			}
			catch (SQLException sqle) {
				logger.error("could not read a hi value", sqle);
				throw sqle;
			}
			finally {
				qps.close();
			}
			
			PreparedStatement ups = conn.prepareStatement(update);
			try {
				ups.setLong( 1, result + 1 );
				ups.setString( 2, sequenceName );
				rows = ups.executeUpdate();
			}
			catch (SQLException sqle) {
				logger.error("could not update hi value in: " + tableName, sqle);
				throw sqle;
			}
			finally {
				ups.close();
			}
		}
		while (rows==0);
		
		//conn.commit();
			
		return new Long(result);
	}


	public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
		// Auto-generated method stub
		return null;
	}


	public String[] sqlDropStrings(Dialect dialect) throws HibernateException {
		//  Auto-generated method stub
		return null;
	}


	public Object generatorKey() {
		return tableName;
	}
	
	
	
}
