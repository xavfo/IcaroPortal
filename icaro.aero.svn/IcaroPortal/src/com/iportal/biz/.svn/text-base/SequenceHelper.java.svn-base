/*
 * Created Mar 24, 2006
 *	SequenceHelper.java
 */
package com.iportal.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.id.IdentifierGenerationException;

import com.iportal.Constants;

/**
 * Generador de Secuencias para archivos, documentos e imagenes
 * @author hernan
 * @version 1.0
 */
public class SequenceHelper extends BaseHelper {

	private static Log logger = LogFactory.getLog(SequenceHelper.class);

	public SequenceHelper() {
		super();
	}
	
	public static synchronized Long getNextValue (String sequenceName )  {
		Long result = null;
		Session sess = null;
		try {
			sess = getHibernateSession();
			result = getNextValue(sequenceName, sess);
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);			
		} finally {
			sess.clear();
			try {
				sess.close();
			} catch (HibernateException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return result;
	}
	
	
	public static synchronized Long getNextValue (String sequenceName, Session sess) {
		Long result = null;
		
		StringBuffer sqlSelect = new StringBuffer ();
		sqlSelect.append("select ");
		sqlSelect.append(Constants.SEQUENCE_COLUMN_VALUE);
		sqlSelect.append(" from ");
		sqlSelect.append(Constants.SEQUENCE_TABLE_NAME);
		sqlSelect.append(" where ");
		sqlSelect.append(Constants.SEQUENCE_COLUMN_ID);
		sqlSelect.append(" = ?   ");
		
		StringBuffer sqlUpdate = new StringBuffer ();
		sqlUpdate.append("update ");
		sqlUpdate.append(Constants.SEQUENCE_TABLE_NAME);
		sqlUpdate.append(" set ");
		sqlUpdate.append(Constants.SEQUENCE_COLUMN_VALUE);
		sqlUpdate.append(" = ? ");
		sqlUpdate.append(" where ");
		sqlUpdate.append(Constants.SEQUENCE_COLUMN_ID);
		sqlUpdate.append(" = ? ");
		Transaction tx = null;
		if (sess.isConnected()) {
			try {
				tx = sess.beginTransaction();
				Connection conn = sess.connection();
				int rows;
					do {
						// The loop ensures atomicity of the
						// select + update even for no transaction
						// or read committed isolation level
						PreparedStatement qps = conn.prepareStatement(sqlSelect.toString());
						try {
							qps.setString(1, sequenceName);
							ResultSet rs = qps.executeQuery();
							if ( !rs.next() ) {
								StringBuffer buffer = new StringBuffer ();
								buffer.append ("could not read a hi value - you need to populate the table: ");
								buffer.append(Constants.SEQUENCE_TABLE_NAME);
								buffer.append (" quey: ");
								buffer.append (sqlSelect.toString());
								buffer.append (" columnId: ");
								buffer.append (sequenceName);
								logger.error(buffer.toString());
								
								throw new IdentifierGenerationException(buffer.toString());
							}
							result = new Long(rs.getLong(1));
							rs.close();
						}
						catch (SQLException sqle) {
							logger.error("could not read a hi value", sqle);
							throw sqle;
						}
						finally {
							qps.close();
						}
						PreparedStatement ups = conn.prepareStatement(sqlUpdate.toString());
						try {
							ups.setLong( 1, result.longValue() + 1 );
							ups.setString( 2, sequenceName );
							rows = ups.executeUpdate();
						}
						catch (SQLException sqle) {
							logger.error("could not update hi value in: " + Constants.SEQUENCE_TABLE_NAME, sqle);
							throw sqle;
						}
						finally {
							ups.close();
						}

					}
					while (rows==0);
					tx.commit();
			} catch (Exception e) {
				if (tx != null) {
					try {
						tx.rollback();
					} catch (HibernateException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
				logger.error("an Error ocurred while trying to query table : " + Constants.SEQUENCE_TABLE_NAME, e);

			}
		}
		

		return result;
	}

}
