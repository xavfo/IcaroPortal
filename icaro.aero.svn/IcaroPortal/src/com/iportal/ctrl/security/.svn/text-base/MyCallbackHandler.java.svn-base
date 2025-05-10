/*
 * Created on Mar 4, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
 
/**
 * @author martha
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

package com.iportal.ctrl.security;
import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * CallBack interface for JAAS LoginModules that must prompt for input. Provides
 * the specified username and password when prompted by the Callback.
 */
public class MyCallbackHandler implements CallbackHandler {
	/**
	 * The username to be provided when prompted.
	 */
	private String username;

	/**
	 * The password to be provided when prompted.
	 */
	private String password;

	/**
	 * Create a new NamePasswordCallbackHandler (required Cams default 
	 * constructor that is dynamically called by the authentication
	 * server).
	 */
	public MyCallbackHandler(){
		this.username = null;
		this.password = null;
	}

	 
	/**
	 * Create a new NamePasswordCallbackHandler (optional constructor
	 * used to facilitate testing).
	 *
	 * @param username the username to provide when prompted.
	 * @param password the password to provide when prompted.
	 */
	public MyCallbackHandler(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	
	/**
	 * Set the username.
	 *
	 * @param username the username to be provided to a NameCallback.
	 */
	public void setUsername(String username) {
		if (username == null){
			this.username = "";
			return;
		}

		this.username = username;
	}

	/**
	 * Set the password.
	 *
	 * @param password the password to be provided to a PasswordCallback.
	 */
	public void setPassword(String password) {
		if (password == null) {
			this.password = "";
			return;
		}

		this.password = password;
	}

	/**
	 * Retrieve or display the information requested in the provided Callbacks. 
	 * The handle method implementation checks the instance(s) of the Callback 
	 * object(s) passed in to retrieve or display the requested information. 
	 *
	 * @param callbacks an array of Callback objects provided by an underlying 
	 * security service which contains the information requested to be retrieved 
	 * or displayed.  
	 *
	 * @exception IOException if an input or output error ocurrs
	 * @exception UnsupportedCallbackException if the implementation of this 
	 * method does not support one or more of the Callbacks specified in the 
	 * callbacks parameter 
	 */
	public void handle(Callback[] callbacks)
		throws IOException, UnsupportedCallbackException {
		// Loop over all Callbacks
		for (int i = 0; i < callbacks.length; i++) {
			Callback cb = callbacks[i];

			if (cb instanceof NameCallback) {	
				
				((NameCallback)cb).setName(username);
			}
	
			else if (cb instanceof PasswordCallback){
				
				// JAAS specifies that the password is a char[]
				((PasswordCallback)cb).setPassword(password.toCharArray());
			}
			else {
				throw new UnsupportedCallbackException(
					cb, "Unrecognized Callback");
			}
		}
	}
}  // End of class: NamePasswordCallbackHandler


