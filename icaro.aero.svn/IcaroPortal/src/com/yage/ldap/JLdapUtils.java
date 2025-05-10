package com.yage.ldap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPReferralException;
import com.novell.ldap.LDAPSearchResults;


public class JLdapUtils {
    private static Log logger = LogFactory.getLog(JLdapUtils.class);
	public JLdapUtils()
	{
	}
    
    public static ArrayList returnList() {
        //int ldapPort = LDAPConnection.DEFAULT_PORT;
        String port = System.getProperty("com.yage.userservice.ldap.port");
        int ldapPort = 0;
        try {
            ldapPort = Integer.parseInt(port);
        }catch (NumberFormatException nfe){
            ldapPort = LDAPConnection.DEFAULT_PORT;
        }
        
        int searchScope = LDAPConnection.SCOPE_SUB;
        int ldapVersion  = LDAPConnection.LDAP_V3;;
        boolean attributeOnly = false;
        //String loginDN = Constants.USER_LDAP;
        String loginDN = System.getProperty("com.yage.userservice.ldap.security_principal");
        //String password = Constants.PASSWORD_LDAP;
        String password = System.getProperty("com.yage.userservice.ldap.security_credential");
        //String searchBase="dc=generacion,dc=local";
        String searchBase = System.getProperty("com.yage.userservice.ldap.base");
        //String searchFilter="(&(objectClass=user)(telephoneNumber=222))";
        //String searchFilter="(objectClass=user)";
        String searchFilter = System.getProperty("com.yage.userservice.ldap.search.filter");
        //java.lang.String[] attrs={LDAPConnection.NO_ATTRS};
        ArrayList<UserLdap> resp = null;
       

        LDAPConnection lc = new LDAPConnection();
        try
        {
            //lc.connect( Constants.HOST_LDAP, ldapPort );
            lc.connect( System.getProperty("com.yage.userservice.ldap.host"), ldapPort );
            lc.bind( ldapVersion, loginDN, password.getBytes("UTF8") );
            LDAPSearchResults searchResults =
                lc.search(  searchBase,      // container to search
                            searchScope,     // search scope
                            searchFilter,    // search filter
                            null,           // "1.1" returns entry name only
                            attributeOnly);  // no attributes are returned
            resp = new ArrayList<UserLdap>();
            // print out all the objects
            while ( searchResults.hasMore()) {
                LDAPEntry nextEntry = null;
                try {
                    nextEntry = searchResults.next();
                    
                    boolean hasData=false;
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    UserLdap userLdap=new UserLdap();
                    Iterator allAttributes = attributeSet.iterator();
                    while(allAttributes.hasNext()) 
                    {
                        LDAPAttribute attribute =
                        (LDAPAttribute)allAttributes.next();
                        String attributeName = attribute.getName();
                        if (attributeName.equals("userPrincipalName"))
                        {
                            hasData=true;
                            userLdap.setEmail(attribute.getStringValue());
                            userLdap.setUsername(attribute.getStringValue());
                        }
                        if (attributeName.equals("displayName"))
                        {
                            userLdap.setFirstName(attribute.getStringValue());
                        }
                    }
                    if (hasData)
                        resp.add(userLdap);
                        
                    
                }
                catch(LDAPException e) {
                    logger.error(e.getMessage(), e);

                    // Exception is thrown, go for next entry
                    continue;
                }

                
            }
            // disconnect with the server
            lc.disconnect();



        }catch( LDAPException e ) {
                System.out.println( "Error: " + e.toString() );
                logger.error(e.getMessage(), e);
        }
        catch( UnsupportedEncodingException e ) {
                System.out.println( "Error: " + e.toString() );
                logger.error(e.getMessage(), e);
         }
        return resp;

    }

    
	public static ArrayList<UserLdap> returnList(String idCity,String idDepartment) 
	{
		int ldapPort=LDAPConnection.DEFAULT_PORT;
		int searchScope = LDAPConnection.SCOPE_SUB;
        int ldapVersion  = LDAPConnection.LDAP_V3;
        boolean attributeOnly = false;
        //String loginDN = Constants.USER_LDAP;
        String loginDN = System.getProperty("com.yage.userservice.ldap.security_principal");
        //String password = Constants.PASSWORD_LDAP;
        String password = System.getProperty("com.yage.userservice.ldap.security_credential");
        String searchBase="";
        
        if ((idCity.equals("0"))&&(idDepartment.equals("0")))
        	//searchBase="ou=SRI,dc=generacion,dc=local";
            searchBase = System.getProperty("com.yage.userservice.ldap.base");
        else
        {
        	if (idDepartment.equals("0"))
        		searchBase=idCity;
        	else	
        		searchBase=idDepartment;
        }
        
        String searchFilter="";
//        java.lang.String[] attrs={LDAPConnection.NO_ATTRS};
        ArrayList<UserLdap> resp=null;
       

        LDAPConnection lc = new LDAPConnection();
        try
        {
            String host = System.getProperty("com.yage.userservice.ldap.host");    
        	//lc.connect( Constants.HOST_LDAP, ldapPort );
            lc.connect(host, ldapPort );
        	lc.bind( ldapVersion, loginDN, password.getBytes("UTF8") );
//        	LDAPConstraints cons=lc.getConstraints();
        	
        
        	LDAPSearchResults searchResults =
                lc.search(  searchBase,      // container to search
                            searchScope,     // search scope
                            searchFilter,    // search filter
                            null,           // "1.1" returns entry name only
                            attributeOnly);  // no attributes are returned
        	resp=new ArrayList<UserLdap>();
            // print out all the objects
            while ( searchResults.hasMore()) {
                LDAPEntry nextEntry = null;
                try {
                    nextEntry = searchResults.next();
                    
                    boolean hasData=false;
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    UserLdap userLdap=new UserLdap();
                    Iterator allAttributes = attributeSet.iterator();
                    while(allAttributes.hasNext()) 
                    {
                    	LDAPAttribute attribute =
                    	(LDAPAttribute)allAttributes.next();
                    	String attributeName = attribute.getName();
                    	
                    	if (attributeName.equals("userPrincipalName"))
                    	{
                    		hasData=true;
	                    	userLdap.setEmail(attribute.getStringValue());
	                    	userLdap.setUsername(attribute.getStringValue());
                    	}
                    	if (attributeName.equals("displayName"))
                    	{
	                    	userLdap.setFirstName(attribute.getStringValue());
                    	}
                    }
                    if (hasData)
                		resp.add(userLdap);
                    	
                    
                }
                catch(LDAPReferralException e) {
                    continue;
                }
                catch(LDAPException e) {
                    continue;
                }

                //System.out.println("\n" + nextEntry.getDN());
            }
 
            lc.disconnect();



        }catch( LDAPException e ) {
            
                System.out.println( "Error: " + e.toString() );
        }
        catch( UnsupportedEncodingException e ) {
                System.out.println( "Error: " + e.toString() );
         }
        return resp;

	}
	public static ArrayList<CityLdap> returnCities()
	{
		int ldapPort=LDAPConnection.DEFAULT_PORT;
		int searchScope = LDAPConnection.SCOPE_ONE;
        int ldapVersion  = LDAPConnection.LDAP_V3;
        boolean attributeOnly = false;
        String loginDN = System.getProperty("com.yage.userservice.ldap.security_principal");
        String password = System.getProperty("com.yage.userservice.ldap.security_credential");
        //String searchBase="ou=SRI,dc=generacion,dc=local";
        String searchBase = System.getProperty("com.yage.userservice.ldap.base");
        ArrayList<CityLdap> resp=null;
        String searchFilter="";
        LDAPConnection lc = new LDAPConnection();
        try
        {   
            String host = System.getProperty("com.yage.userservice.ldap.host");
        	lc.connect( host, ldapPort );
        	lc.bind( ldapVersion, loginDN, password.getBytes("UTF8") );
        
        	LDAPSearchResults searchResults =
                lc.search(  searchBase,      // container to search
                            searchScope,     // search scope
                            searchFilter,    // search filter
                            null,           // "1.1" returns entry name only
                            attributeOnly);  // no attributes are returned
        	resp=new ArrayList<CityLdap>();
            // print out all the objects
            while ( searchResults.hasMore()) {
                LDAPEntry nextEntry = null;
                try {
                    nextEntry = searchResults.next();
                    
                    boolean hasData=false;
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    CityLdap cityLdap=new CityLdap();
                    Iterator allAttributes = attributeSet.iterator();
                    while(allAttributes.hasNext()) 
                    {
                    	LDAPAttribute attribute =
                    	(LDAPAttribute)allAttributes.next();
                    	String attributeName = attribute.getName();
                    	//System.out.println("\n Attribute--> "+attribute.getName());
                    	//System.out.println(" Value--> "+attribute.getStringValue());
                    	if (attributeName.equals("ou"))
                    	{
                    		hasData=true;
                    		cityLdap.setName(attribute.getStringValue());
                    	}
                    	if (attributeName.equals("distinguishedName"))
                    	{
	                    	cityLdap.setDistinguisedName(attribute.getStringValue());
                    	}
                    }
                    if (hasData)
                		resp.add(cityLdap);
                    	
                    
                }
               
                catch(LDAPException e) {
                    

                    // Exception is thrown, go for next entry
                    continue;
                }

                
            }
            lc.disconnect();
            
        } catch( LDAPException e ) {
                
        } catch( UnsupportedEncodingException e ) {
                
        }

        return resp;
	}
	public static ArrayList<DepartmentLdap> returnDepartment(String distinguishedName)
	{
		int ldapPort=LDAPConnection.DEFAULT_PORT;
		int searchScope = 0;
		String searchBase="";
		if (distinguishedName.equals("0"))
		{
			searchScope=LDAPConnection.SCOPE_SUB;
			//searchBase="ou=SRI,dc=generacion,dc=local";
            searchBase = System.getProperty("com.yage.userservice.ldap.base");
		}
		else{
			searchScope=LDAPConnection.SCOPE_ONE;
			searchBase=distinguishedName;
		}
        int ldapVersion  = LDAPConnection.LDAP_V3;
        boolean attributeOnly = false;
        String loginDN = System.getProperty("com.yage.userservice.ldap.security_principal");
        String password = System.getProperty("com.yage.userservice.ldap.security_credential");
        ArrayList<DepartmentLdap> resp=null;
        String searchFilter="";
//        java.lang.String[] attrs={LDAPConnection.NO_ATTRS};
        LDAPConnection lc = new LDAPConnection();
        try
        {
            String host = System.getProperty("com.yage.userservice.ldap.host");    
        	//lc.connect( Constants.HOST_LDAP, ldapPort );
            lc.connect( host, ldapPort );
        	lc.bind( ldapVersion, loginDN, password.getBytes("UTF8") );
//        	LDAPConstraints cons=lc.getConstraints();
        	
        
        	LDAPSearchResults searchResults =
                lc.search(  searchBase,      // container to search
                            searchScope,     // search scope
                            searchFilter,    // search filter
                            null,           // "1.1" returns entry name only
                            attributeOnly);  // no attributes are returned
        	resp=new ArrayList<DepartmentLdap>();
            // print out all the objects
            while ( searchResults.hasMore()) {
                LDAPEntry nextEntry = null;
                try {
                    nextEntry = searchResults.next();
                    String part[]=nextEntry.getDN().toUpperCase().split("OU=");
                    boolean hasData=false;
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    DepartmentLdap departmentLdap=new DepartmentLdap();
                    Iterator allAttributes = attributeSet.iterator();
                    while(allAttributes.hasNext()) 
                    {
                    	LDAPAttribute attribute =
                    	(LDAPAttribute)allAttributes.next();
                    	String attributeName = attribute.getName();
                    	
                    	if (distinguishedName.equals("0"))
                    	{
                    		if (part.length>3)
                    		{
		                    	if (attributeName.equals("ou"))
		                    	{
		                    		hasData=true;
		                    		departmentLdap.setName(attribute.getStringValue());
		                    	}
		                    	if (attributeName.equals("distinguishedName"))
		                    	{
		                    		departmentLdap.setDistinguisedName(attribute.getStringValue());
		                    	}
                    		}
                    	}
                    	else
                    	{
                    		if (attributeName.equals("ou"))
	                    	{
	                    		hasData=true;
	                    		departmentLdap.setName(attribute.getStringValue());
	                    	}
	                    	if (attributeName.equals("distinguishedName"))
	                    	{
	                    		departmentLdap.setDistinguisedName(attribute.getStringValue());
	                    	}
                    	}
                    }
                    if (hasData)
                		resp.add(departmentLdap);
                    	
                    
                }
               
                catch(LDAPException e) {
                    
                    continue;
                }

                //System.out.println("\n" + nextEntry.getDN());
            }
            lc.disconnect();

        } catch( LDAPException e ) {
                System.out.println( "Error: " + e.toString() );
        } catch( UnsupportedEncodingException e ) {
                System.out.println( "Error: " + e.toString() );
        }

        return resp;
		
	}
}
