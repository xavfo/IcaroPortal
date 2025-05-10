/*
 * Created 09/02/2007
 *	QueryHelper.java
 */
package com.iportal.biz.helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.RowItem;
import com.iportal.cart.model.cart.Order;
import com.iportal.model.icaro.Airport;
import com.yage.Globals;

/**
 * @author hernan
 *
 */
public class QueryHelper extends BaseHelper {
	
	private QueryHelper() {
		// only static access
	}

	/**
	 * @param countryCode
	 * @param sess
	 * @param orderField
	 * @param orderAsc
	 * @return
	 */
	public static List<RowItem> queryStatesByCountry(Session sess, Long countryCode,  String orderField, Boolean orderAsc) {
		List<RowItem> resp = null;

		//	defualt values
        if (orderAsc == null) {
        	orderAsc = Globals.TRUE;
        }
        if (orderField == null) {
        	orderField = "name";
        }

        StringBuffer hql = new StringBuffer();
        hql.append("select new com.iportal.biz.RowItem (state.code, state.country.code, state.name) ");
        hql.append(" from State as state ");
        if (countryCode != null && countryCode.longValue() > 0L){
        	hql.append(" where state.country.code = ? ");
        }
        hql.append(" order by state.");
        hql.append(orderField);
        if ( orderAsc) {
 			hql.append(" asc ");
 		} else {
 			hql.append(" desc ");
 		}
        
        
        Query query = sess.createQuery(hql.toString());
        if (countryCode != null && countryCode.longValue() > 0L ){
            query.setParameter(0, countryCode, Hibernate.LONG);
        }
        query.setCacheable(true);
        resp = (ArrayList<RowItem>)query.list();
        
        return resp;
	}
	
	public static List<RowItem> queryStatesByCountry( Long countryCode,  String orderField, Boolean orderAsc) throws Exception {
		Session sess= null;
		List<RowItem> resp = null;
		try {
			sess = getHibernateSession();
			resp = QueryHelper.queryStatesByCountry(sess, countryCode, orderField,orderAsc);
        } catch(Exception e) {
            throw e;

        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }
		
		return resp;
	}
	
	
	public static List<RowItem> queryCitiesByState(Session sess, Long stateCode,  String orderField, Boolean orderAsc, Boolean homeDelivery) {
		List<RowItem> resp = null;

		//	defualt values
        if (orderAsc == null) {
        	orderAsc = Globals.TRUE;
        }
        if (orderField == null) {
        	orderField = "name";
        }
        
        StringBuffer hql = new StringBuffer();
        hql.append("select new com.iportal.biz.RowItem (city.code,city.state.code,city.name) ");
        hql.append(" from City as city ");
        if (stateCode != null && stateCode.longValue() > 0L){
        	hql.append(" where city.state.code = ? ");
        }
        if (homeDelivery != null ){
            if (hql.toString().contains("where") ) {
                hql.append(" and city.homeDelivery = ? ");
            } else {
                hql.append(" where city.homeDelivery = ? ");
            }
        }
        hql.append(" order by city.");
        hql.append(orderField);
        if ( orderAsc) {
 			hql.append(" asc ");
 		} else {
 			hql.append(" desc ");
 		}
        
        Query query = sess.createQuery(hql.toString());
        int i=0;
        if (stateCode != null && stateCode.longValue() > 0L ){
            query.setParameter(i, stateCode, Hibernate.LONG);
            i++;
        }
        if (homeDelivery != null ){
            query.setParameter(i, homeDelivery, Hibernate.BOOLEAN);
            i++;
        }
        query.setCacheable(true);
        resp = (ArrayList<RowItem>)query.list();
        
        return resp;
	}
	
	public static List<RowItem> queryCitiesByState( Long stateCode,  String orderField, Boolean orderAsc) throws Exception {
		Session sess= null;
		List<RowItem> resp = null;
		try {
			sess = getHibernateSession();
			resp = QueryHelper.queryCitiesByState(sess, stateCode, orderField,orderAsc, null);
        } catch(Exception e) {
            throw e;

        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }
		
		return resp;
	}
	
    public static List<RowItem> queryCitiesByState( Long stateCode,  String orderField, Boolean orderAsc, Boolean homeDelivery) throws Exception {
        Session sess= null;
        List<RowItem> resp = null;
        try {
            sess = getHibernateSession();
            resp = QueryHelper.queryCitiesByState(sess, stateCode, orderField,orderAsc, homeDelivery);
        } catch(Exception e) {
            throw e;

        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }
        
        return resp;
    }


    
    
	public static Airport airportByIataCode(Session sess, String iataCode) {
		Airport resp = null;

        
        StringBuffer hql = new StringBuffer();
        
        hql.append(" from Airport as airport ");
        hql.append(" where airport.iataCode = ? ");
        
        Query query = sess.createQuery(hql.toString());
        query.setString(0, iataCode);
        query.setCacheable(true);
        resp = (Airport)query.list().get(0);
        
        return resp;
	}
	
	public static Airport airportByIataCode( String iataCode) throws Exception {
		Session sess= null;
		Airport resp = null;
		try {
			sess = getHibernateSession();
			resp = airportByIataCode(sess, iataCode);
        } catch(Exception e) {
            throw e;

        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }
		
		return resp;
	}
	

	public static Order orderByTransCode(Session sess, Long transactionCode) {
	    Order resp = null;

	    StringBuffer hql = new StringBuffer();
    
	    hql.append(" from Order as order ");
	    hql.append(" where order.order = ? ");
    
	    Query query = sess.createQuery(hql.toString());
	    query.setLong(0, transactionCode);
	    query.setCacheable(true);
	    resp = (Order) query.uniqueResult();

	    return resp;
	}

    public static Order orderByTransCode (Long transactionCode) throws Exception {
	Session sess = null;
	Order resp = null;
	try {
	    sess = getHibernateSession();
	    resp = orderByTransCode(sess, transactionCode);
	} catch (Exception e) {
	    throw e;

	} finally {
	    if (sess != null)
		try {
		    sess.clear();
		    sess.close();
		} catch (Exception e) {
		}
	}

	return resp;
    }

	
}
