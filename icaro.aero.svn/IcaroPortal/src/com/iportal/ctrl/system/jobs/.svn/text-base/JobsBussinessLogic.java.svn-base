package com.iportal.ctrl.system.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.model.Language;
import com.yage.Globals;

public class JobsBussinessLogic extends BaseBussinessLogic {
	
	private static Log logger = LogFactory.getLog(JobsBussinessLogic.class);
	
	private Session sess = null;
	 
	
	/**
	 * Retrieves all the Areas
	 * @param form
	 * @return
	 */
	public List getAllAreas(AreaForm areaForm) {
		
		List results = null;
		
		try {
		
		sess = getHibernateSession();
		
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();			

		sql.append("from com.iportal.model.jobs.Area as area ");
		sql.append("where 1 = 1 ");
		    		
		if (areaForm.getName() != null && areaForm.getName().length() != 0L ) {
   		    String name = "%" + areaForm.getName() + "%";
   			sql.append("and area.name like ? ");
   		    params.add(name);
   		    types.add(Hibernate.STRING);  
		}
		
		
		if ( areaForm.getOrderField() != null ) {
    		sql.append("order by area.");
    		sql.append(areaForm.getOrderField());
    		if ( areaForm.getOrderAsc().booleanValue() ) {
    		    sql.append(" asc ");
    		} else {
    		    sql.append(" desc ");
    		}
		}
		
		if ( areaForm.getOrderField() == null ) {
    		sql.append("order by area.name asc");
		}
				
		Object[] arrayParams = params.toArray();
		Query query = sess.createQuery(sql.toString());
		for (int i = 0; i < types.size(); i++) {
			query.setParameter(i, arrayParams[i], (Type) types.get(i));
		}
					
		results = query.list();		
				
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
 			if (sess != null)
 				try {
 					sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}
		return results;		
	}
	
	/**
	 * Retrieves all the Position requests or user applications for a job  
	 * @param positionRequestForm
	 * @return
	 */
	public List getAllPositionRequests(PositionRequestForm positionRequestForm) {
		
		List results = null;
		Long areaCode = null;
		areaCode = positionRequestForm.getAreaCode();
		
		if (areaCode != null){
			try {
				sess = getHibernateSession();
				
				Language language = (Language)sess.load( Language.class, positionRequestForm.getLanguageCode());
				
				StringBuffer sql = new StringBuffer();
				ArrayList<Object> params = new ArrayList<Object>();
				ArrayList<NullableType> types = new ArrayList<NullableType>();			
		
				sql.append("from PositionRequest as positionRequest ");
				sql.append("where positionRequest.position.area.code = ? ");
	   		    params.add(areaCode);
	   		    types.add(Hibernate.LONG);  
                
                sql.append("and positionRequest.requester.briefApplication = ? ");
                params.add(Globals.FALSE);
                types.add(Hibernate.BOOLEAN);
				
				if (positionRequestForm.getPositionCode() != null && positionRequestForm.getPositionCode() != 0L ) {
		   			sql.append("and positionRequest.position.code = ? ");
		   		    params.add(positionRequestForm.getPositionCode());
		   		    types.add(Hibernate.LONG);  
				}
		
				if (positionRequestForm.getCityCode() != null && positionRequestForm.getCityCode() != 0L) {
		   			sql.append("and positionRequest.requester.city.code = ? ");
		   		    params.add(positionRequestForm.getCityCode());
		   		    types.add(Hibernate.LONG);  
				}
		
				if (positionRequestForm.getCountryCode() != null && positionRequestForm.getCountryCode() != 0L) {
		   			sql.append("and positionRequest.requester.city.state.country.code = ? ");
		   		    params.add(positionRequestForm.getCountryCode());
		   		    types.add(Hibernate.LONG);  
				}
	
				if (positionRequestForm.getMaritalStatusCode() != null && positionRequestForm.getMaritalStatusCode() != 0L) {
		   			sql.append("and positionRequest.requester.maritalStatus.code = ? ");
		   		    params.add(positionRequestForm.getMaritalStatusCode());
		   		    types.add(Hibernate.LONG);  
				}
	
				if (positionRequestForm.getInstructionLevelCode() != null && positionRequestForm.getInstructionLevelCode() != 0L) {
		   			sql.append("and positionRequest.requester.instructionLevel.code = ? ");
		   		    params.add(positionRequestForm.getInstructionLevelCode());
		   		    types.add(Hibernate.LONG);  
				}
	
				if (positionRequestForm.getStudyBranchCode() != null && positionRequestForm.getStudyBranchCode() != 0L) {
		   			sql.append("and positionRequest.requester.studyBranch.code = ? ");
		   		    params.add(positionRequestForm.getStudyBranchCode());
		   		    types.add(Hibernate.LONG);  
				}
	
				if (positionRequestForm.getSalaryAspirationCode() != null && positionRequestForm.getSalaryAspirationCode() != 0L) {
		   			sql.append("and positionRequest.requester.salaryAspiration.code = ? ");
		   		    params.add(positionRequestForm.getSalaryAspirationCode());
		   		    types.add(Hibernate.LONG);  
				}
				   
                /*
				if (positionRequestForm.getIsWorking() != null ) {
		   			sql.append("and positionRequest.requester.isWorking = ? ");
		   		    params.add(positionRequestForm.getIsWorking());
		   		    types.add(Hibernate.BOOLEAN);  
				}*/

                if (positionRequestForm.getWorkingOption() != null  && positionRequestForm.getWorkingOption().intValue()  > Constants.SEARCH_ALL_OPTION) {
                    sql.append("and positionRequest.requester.isWorking = ? ");
                    if ( positionRequestForm.getWorkingOption().intValue() == Constants.TRUE_INT.intValue() )
                        params.add( Globals.TRUE );
                    else
                        params.add( Globals.FALSE );
                    types.add(Hibernate.BOOLEAN);  
                }
                
                if (positionRequestForm.getExperienceOption() != null && positionRequestForm.getExperienceOption().intValue() > Constants.SEARCH_ALL_OPTION) {
                    sql.append("and positionRequest.experience = ? ");
                    if ( positionRequestForm.getExperienceOption().intValue() == Constants.TRUE_INT.intValue() )
                        params.add( Globals.TRUE );
                    else
                        params.add( Globals.FALSE );
                    types.add(Hibernate.BOOLEAN);
                }
                
				if (positionRequestForm.getFrom() != null && language != null) {
					
		    		sql.append("and to_char(positionRequest.requester.dateCreated,'" );
		    		sql.append(language.getDateFormat());
		    		sql.append("') >= to_char(?,'");
		    		sql.append(language.getDateFormat());
		    		sql.append("') ");
					Calendar from = new GregorianCalendar();
					from.setTime(positionRequestForm.getFrom().getTime());
					from.set(Calendar.HOUR,0);
					from.set(Calendar.MINUTE,0);
					from.set(Calendar.SECOND,0);
					from.set(Calendar.MILLISECOND,0);	
					params.add(from);
		   		    types.add(Hibernate.CALENDAR);  
				}
				
				if (positionRequestForm.getTo() != null && language != null) {
		    		sql.append("and to_char(positionRequest.requester.dateCreated,'" );
		    		sql.append(language.getDateFormat());
		    		sql.append("') <= to_char(?, '");
		    		sql.append(language.getDateFormat());
		    		sql.append("') ");
					Calendar to = new GregorianCalendar();
					to.setTime(positionRequestForm.getTo().getTime());
					to.set(Calendar.HOUR,0);
					to.set(Calendar.MINUTE,0);
					to.set(Calendar.SECOND,0);
					to.set(Calendar.MILLISECOND,0);	
					params.add(to);
		   		    types.add(Hibernate.CALENDAR);  
				}
				
	    		if ( positionRequestForm.getOrderAsc() != null  && positionRequestForm.getOrderField() != null) {
	         		sql.append("order by positionRequest");
	         		if (positionRequestForm.getOrderField().equals("position")){
	         			sql.append(".position.name");
	         		}else if (positionRequestForm.getOrderField().equals("country")){
	         			sql.append(".requester.city.country.name");
	         		}else if (positionRequestForm.getOrderField().equals("city")){
	         			sql.append(".requester.city.name");
	    			}else {
	    				sql.append(".position.name");
	    			}
	         		
	    			if ( positionRequestForm.getOrderAsc().booleanValue() ) {
	         		    sql.append(" asc ");
	         		} else {
	         		    sql.append(" desc ");
	         		}
	     		}
				
				
				if ( positionRequestForm.getOrderField() == null ) {
		    		sql.append("order by positionRequest.position.name asc");
				}
						
				Object[] arrayParams = params.toArray();
				Query query = sess.createQuery(sql.toString());
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, arrayParams[i], (Type) types.get(i));
				}
							
				results = query.list();		
				
			}catch (Exception e) {
				logger.error(e.getMessage(),e);
			} finally {
	 			if (sess != null)
	 				try {
	 					sess.clear();
	 					sess.close();
	 				} catch (Exception e) {
	 				}
	 		}
		}
		return results;		
	}


    public List getCurriculumList(PositionRequestForm positionRequestForm) {
        
        List results = null;
        Long areaCode = null;
        areaCode = positionRequestForm.getAreaCode();
        
        if (areaCode != null){
            try {
                sess = getHibernateSession();
                
                Language language = (Language)sess.load( Language.class, positionRequestForm.getLanguageCode());
                
                StringBuffer sql = new StringBuffer();
                ArrayList<Object> params = new ArrayList<Object>();
                ArrayList<NullableType> types = new ArrayList<NullableType>();          
        
                sql.append("from PositionRequest as positionRequest ");
                sql.append("where positionRequest.position.area.code = ? ");
                params.add(areaCode);
                types.add(Hibernate.LONG);
                
                sql.append("and positionRequest.requester.briefApplication = ? ");
                params.add(Globals.TRUE);
                types.add(Hibernate.BOOLEAN);
                
                if (positionRequestForm.getCountryCode() != null && positionRequestForm.getCountryCode() != 0L) {
                    sql.append("and positionRequest.requester.country.code = ? ");
                    params.add(positionRequestForm.getCountryCode());
                    types.add(Hibernate.LONG);  
                }

                if (positionRequestForm.getFrom() != null && language != null) {
                    
                    sql.append("and to_char(positionRequest.requester.dateCreated,'" );
                    sql.append(language.getDateFormat());
                    sql.append("') >= to_char(?,'");
                    sql.append(language.getDateFormat());
                    sql.append("') ");
                    Calendar from = new GregorianCalendar();
                    from.setTime(positionRequestForm.getFrom().getTime());
                    from.set(Calendar.HOUR,0);
                    from.set(Calendar.MINUTE,0);
                    from.set(Calendar.SECOND,0);
                    from.set(Calendar.MILLISECOND,0);   
                    params.add(from);
                    types.add(Hibernate.CALENDAR);  
                }
                
                if (positionRequestForm.getTo() != null && language != null) {
                    sql.append("and to_char(positionRequest.requester.dateCreated,'" );
                    sql.append(language.getDateFormat());
                    sql.append("') <= to_char(?, '");
                    sql.append(language.getDateFormat());
                    sql.append("') ");
                    Calendar to = new GregorianCalendar();
                    to.setTime(positionRequestForm.getTo().getTime());
                    to.set(Calendar.HOUR,0);
                    to.set(Calendar.MINUTE,0);
                    to.set(Calendar.SECOND,0);
                    to.set(Calendar.MILLISECOND,0); 
                    params.add(to);
                    types.add(Hibernate.CALENDAR);  
                }
                
                if ( positionRequestForm.getOrderAsc() != null  && positionRequestForm.getOrderField() != null) {
                    sql.append("order by positionRequest");
                    if (positionRequestForm.getOrderField().equals("position")){
                        sql.append(".position.name");
                    }else if (positionRequestForm.getOrderField().equals("country")){
                        sql.append(".requester.city.country.name");
                    }else if (positionRequestForm.getOrderField().equals("city")){
                        sql.append(".requester.city.name");
                    }else {
                        sql.append(".position.name");
                    }
                    
                    if ( positionRequestForm.getOrderAsc().booleanValue() ) {
                        sql.append(" asc ");
                    } else {
                        sql.append(" desc ");
                    }
                }
                
                
                if ( positionRequestForm.getOrderField() == null ) {
                    sql.append("order by positionRequest.position.name asc");
                }
                        
                Object[] arrayParams = params.toArray();
                Query query = sess.createQuery(sql.toString());
                for (int i = 0; i < types.size(); i++) {
                    query.setParameter(i, arrayParams[i], (Type) types.get(i));
                }
                            
                results = query.list();     
                
            }catch (Exception e) {
                logger.error(e.getMessage(),e);
            } finally {
                if (sess != null)
                    try {
                        sess.clear();
                        sess.close();
                    } catch (Exception e) {
                    }
            }
        }
        return results;     
    }

	/**
	 * Retrieves all the Cities corresponding to a countryCode  
	 * @param positionRequestForm
	 * @return
	 */
	public List getAllCities(Long countryCode) {
		List results = null;
    	StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();		
		
		if (countryCode != null){
	        try {
	        	sess = getHibernateSession();	
	        	sql.append("from City as city ");
	        	sql.append("where city.country.code = ? ");
	   		    params.add(countryCode);
	   		    types.add(Hibernate.LONG);  
	
	        	sql.append("order by city.name ");
	
				Object[] arrayParams = params.toArray();
				Query query = sess.createQuery(sql.toString());
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, arrayParams[i], (Type) types.get(i));
				}
							
				results = query.list();		
	    		    		
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
		}
		return results;		
	}
	
	/**
	 * Retrieves all the Positions 
	 * @param form
	 * @return
	 */
	public List getAllPositions(PositionForm positionForm) {
		
		List results = null;
		
		try {
		
			sess = getHibernateSession();
			
			StringBuffer sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
			ArrayList<NullableType> types = new ArrayList<NullableType>();
	
			sql.append("from Position as position ");
			sql.append("where 1 = 1 ");
			    		
			if (positionForm.getName() != null && positionForm.getName().length() != 0L ) {
	   		    String name = "%" + positionForm.getName() + "%";
	   			sql.append("and position.name like ? ");
	   		    params.add(name);
	   		    types.add(Hibernate.STRING);
			}
			
			if (positionForm.getAreaCode() != null){
				sql.append("and position.area.code = ? ");
	   		    params.add(positionForm.getAreaCode());
	   		    types.add(Hibernate.LONG);
			}
			
			if ( positionForm.getOrderField() != null ) {
	    		sql.append("order by position.");
	    		sql.append(positionForm.getOrderField());
	    		if ( positionForm.getOrderAsc().booleanValue() ) {
	    		    sql.append(" asc ");
	    		} else {
	    		    sql.append(" desc ");
	    		}
	    		
			}
			
			if ( positionForm.getOrderField() == null ) {
	    		sql.append("order by position.name asc");
			}
					
			Object[] arrayParams = params.toArray();
			Query query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i], (Type) types.get(i));
			}
			results = query.list();		
		;
		
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
 			if (sess != null)
 				try {
 					sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}
		return results;		
	}

	/**
	 * Retrieves all the entries of a catalogue type, for example all instruction levels
	 * @param form
	 * @return
	 */
	public List getAllCatalogues(Long code) {
		
		List results = null;
		
		try {
		
			sess = getHibernateSession();
			
			StringBuffer sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
			ArrayList<NullableType> types = new ArrayList<NullableType>();
	
			sql.append("from Catalogue as catalogue ");
			sql.append("where 1 = 1 ");
			    		
			if (code != null && code != 0L ) {
				sql.append("and catalogue.type.code = ? ");
	   		    params.add(code);
	   		    types.add(Hibernate.LONG);
			}
		
	    	sql.append("order by catalogue.name asc");
					
			Object[] arrayParams = params.toArray();
			Query query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i], (Type) types.get(i));
			}
			results = query.list();		
		;
		
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
 			if (sess != null)
 				try {
 					sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}
		return results;		
	}
	
}
