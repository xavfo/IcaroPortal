/*
 * Created Jul 14, 2006
 *	FileManagerUtils.java
 */
package com.yage.file;

import java.io.File;

import javax.servlet.ServletContext;

/**
 * Manejador simple de archivos.
 * @author hernan
 *
 */
public class FileManagerUtils {
	
	protected ServletContext context;
	
	
	
	/**
	 * @param context
	 */
	public FileManagerUtils(ServletContext context) {
		super();
		this.context = context;
	}

	/**
	 * 
	 */
	public FileManagerUtils() {
		super();
		context = null;
	}

	/**
	 * @return Returns the context.
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * @param context The context to set.
	 */
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public boolean deleteFile (String filePath) {
		String realPath = context.getRealPath(filePath);
		
		File file = new File(realPath);
		
		return file.delete();
	}

}
