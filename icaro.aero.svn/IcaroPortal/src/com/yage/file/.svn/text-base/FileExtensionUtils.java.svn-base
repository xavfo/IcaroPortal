/*
 * Created Jun 14, 2006
 *	FileExtensionUtils.java
 */
package com.yage.file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase para manejo de extgension de archivos
 * @author hernan
 *
 */
public class FileExtensionUtils {

	private final static String notFound ="notFound";
	
	private static Log logger = LogFactory.getLog(FileExtensionUtils.class);

    private static Properties fileExtentions = new Properties();

	
    /**
     * Recupera el nombre del archivo con el icono buscado
     * 
     * @param key extension del archivo que se necesita el icono
     * @return el nombre de la imagen con el icono correspondiente
     */
    public static synchronized String getExtensionIcon(String key) {
        String extension = null;
        extension = fileExtentions.getProperty(key);
        if ( extension == null ) {
            return fileExtentions.getProperty(notFound);
        }
        
        return extension;
    }

    /**
	 * 
	 */
	public FileExtensionUtils() {
		super();
		
	}
	
    public static synchronized void addExtention (String id, String extension) {
    	fileExtentions.put(id, extension);
    }

    public static synchronized void removeExtention(String id) {
    	fileExtentions.remove(id);
    }
    
    public static synchronized Properties getFileExtentions() {
        return fileExtentions;
    }
    
	
    public static  String getFileIcon (String fileName) {
    	String resp = null;
    	if (fileName != null) {
        	String key = fileName.substring(fileName.lastIndexOf('.')+1);
        	resp = getExtensionIcon(key.toLowerCase());
    	}
    	return resp;
    }
    
    public static synchronized void loadFileExtentionsAvailable (String iconFolder, String parametersId, ServletContext context ) throws  IllegalAccessException, InvocationTargetException, IOException {
		
		//Load from file
		String filePath = context.getRealPath(iconFolder)+"/";
		
		String contextPath = iconFolder+"/";
		
		File dir = new File(filePath);

		if (dir.isDirectory()) {
           if (!dir.exists()) {
                throw new IOException("Error: directory does not exists");
            }
            String[] files = dir.list();
            String extention = null;
            String icon = null;
	        //limpia el objeto
            fileExtentions.clear();	
            for(int i=0;i<files.length;i++) {
            	icon = files[i];
            	extention = icon.substring(0,icon.indexOf('.'));
            	//obtener la extension y guardar en el Properies
            	addExtention(extention, contextPath+icon);
            }
            
            if(parametersId != null && context != null) {
            	context.removeAttribute(parametersId);
            	context.setAttribute(parametersId, fileExtentions);	
            }
            
            logger.info("FileExtensionIcons initilized.....ID:"+parametersId);
        }
    }
}
