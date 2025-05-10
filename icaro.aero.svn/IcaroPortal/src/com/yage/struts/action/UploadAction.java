/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.iportal.biz.SequenceHelper;
import com.yage.Globals;
import com.yage.commons.NumberFormatUtils;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class UploadAction extends BaseAction {
    
    private static Log logger = LogFactory.getLog(UploadAction.class);



	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
			
		ServletContext context = servlet.getServletContext();
		ActionMessages messages = new ActionMessages();

		StringBuffer filePath = null;
		StringBuffer webPath = null;
		Long size = null;

		if (form instanceof UploadForm) {
			
			

			UploadForm uploadForm = (UploadForm) form;

			// Base Path
			String basePath = uploadForm.getPath();
			
			//retrieve the file representation
			FormFile file = uploadForm.getFile();
          
			OutputStream bos = null;
			byte[] buffer = null;
			InputStream stream = null;
			try {
			
				//retrieve the file name
				String fileName= file.getFileName();
				String extension = fileName.substring(fileName.indexOf('.'));
				
				//change file name
				if ( uploadForm.getPrefix() != null && uploadForm.getPrefix().length() > 0 ) {
					StringBuffer prefix = new StringBuffer(); 
					prefix.append(uploadForm.getPrefix().concat("-"));
					
					Long nextCode = SequenceHelper.getNextValue(uploadForm.getSequence());  
					if (nextCode != null){
						NumberFormatUtils formatter = new NumberFormatUtils(); 
						prefix.append(formatter.format(nextCode.longValue(), "000000"));
					}
					prefix.append(extension);
					fileName = prefix.toString();					
				}
	
				//retrieve the content type
				//String contentType = file.getContentType();
				
	
				//retrieve the file size
				size = new Long(file.getFileSize());
				filePath = new StringBuffer(context.getRealPath(basePath));
				filePath.append("/").append(fileName);
				webPath = new StringBuffer(basePath).append("/").append(fileName);
	
				
				//retrieve the file data
				//ByteArrayOutputStream baos = new ByteArrayOutputStream();
				stream = file.getInputStream();
				
				//write the file to the file specified
				bos = new FileOutputStream(filePath.toString());
				int bytesRead = 0;
				buffer = new byte[8192];
				
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				bos.close();
				bos = null;
				buffer = null;

				//close the stream
				stream.close();
				stream = null;
			}
			catch (FileNotFoundException fnfe) {
			    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.fileNotFound"));
				logger.error(fnfe.getMessage(), fnfe);
			}
			catch (IOException ioe) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.io"));
				logger.error(ioe.getMessage(), ioe);
			} finally {
				bos = null;
				buffer = null;
				stream = null;
				if (filePath != null) {
					filePath.delete(0, filePath.length());
					filePath = null;
				}
				
			}

			Boolean maxLengthExceeded = (Boolean)
					request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
			if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.maxLengthExceeded"));
			}


			//destroy the temporary file created
			file.destroy();
			file = null;
		}

		// Report any errors we have discovered back to the original form
		if (!messages.isEmpty()) {
		    saveMessages(request, messages);
			return (mapping.getInputForward());
		}


		//place the data into the request for retrieval from display.jsp
		request.setAttribute("fileUrl", webPath.toString());
		request.setAttribute("fileSize", size);
		if (webPath != null) {
			webPath.delete(0, webPath.length());
			webPath = null;
		}
		
		return ( mapping.findForward(Globals.FORWARD_SUCCESS) );
	}
}
