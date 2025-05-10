/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.SequenceHelper;
import com.yage.commons.NumberFormatUtils;

/**
 * @author YAGE (Jorge Lomas)
 * @version 1.0
 *
 */
public class ManageFileFacade extends BaseBussinessLogic{

    private static Log logger = LogFactory.getLog(ManageFileFacade.class);

    public static String upload(ActionForm form,
                           HttpServletRequest request,
                           ActionMessages messages,
                           Integer type, /*1:Image, 2:Access*/
                           String fileName,
                           ServletContext context
                        )
    throws Exception{

        String filePath = null;
        String webPath = null;
        Long size = null;
        String name = null;
        if (form instanceof UploadForm) {
            UploadForm uploadForm = (UploadForm) form;

            // Base Path
//            String basePath = uploadForm.getBasePath();

            //retrieve the file representation
            FormFile file = uploadForm.getFile();

            try {
                //retrieve the file size
                size = new Long(file.getFileSize());

                if (fileName != null && fileName.length()>0)
                    name = fileName;
                else
                    name = buildName(type, uploadForm.getName());

                String relativePath = buildRelativePath(type, uploadForm.getBasePath());
                /**
                 * TODO revisar que el path exista, sino crearlo
                 */

                filePath = context.getRealPath(relativePath) + "/" + name;
                webPath = relativePath+ "/" + name;

                //retrieve the file data
                InputStream stream = file.getInputStream();

                //write the file to the file specified
                OutputStream bos = new FileOutputStream(filePath);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];

                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                bos.close();

                //close the stream
                stream.close();
            }
            catch (FileNotFoundException fnfe) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.fileNotFound"));
                logger.error(fnfe.getMessage(), fnfe);
            }
            catch (IOException ioe) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.io"));
                logger.error(ioe.getMessage(), ioe);
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            Boolean maxLengthExceeded = (Boolean)
                    request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
            if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.maxLengthExceeded"));
            }

            //destroy the temporary file created
            file.destroy();
        }

        //place the data into the request for retrieval from display.jsp
        request.setAttribute("fileUrl", webPath);
        request.setAttribute("fileSize", size);
        if (messages.isEmpty())
            return webPath;
        else
            return null;
    }


    private static String buildRelativePath(Integer fileType, String basePath) {
        StringBuffer str = new StringBuffer();
        if (fileType.equals(Constants.FILE_TYPE_IMAGE)) {
            str.append(Constants.FILE_BASEPATH_IMAGE);
        } else if (fileType.equals(Constants.FILE_TYPE_BANNER)) {
            str.append(Constants.FILE_BASEPATH_BANNER);
        } else if (fileType.equals(Constants.FILE_TYPE_DOCUMENT)) {
            str.append(Constants.FILE_BASEPATH_DOCUMENT);
        } else if (fileType.equals(Constants.FILE_TYPE_GENERAL)) {
            str.append(Constants.FILE_BASEPATH_GENERAL);
        }
        str.append(basePath);
        return str.toString();
    }

    public static String buildName(Integer fileType, String realFileName) {
        StringBuffer str = new StringBuffer();
        NumberFormatUtils formatter = new NumberFormatUtils();
        String sequenceName = null;
        if (fileType.equals(Constants.FILE_TYPE_IMAGE)) {
            str.append(Constants.FILE_PREFIX_IMAGE_NAME);
            sequenceName = Constants.FILE_IMAGE_SEQUENCE_NAME;
        } else if (fileType.equals(Constants.FILE_TYPE_BANNER)) {
            str.append(Constants.FILE_PREFIX_BANNER_NAME);
            sequenceName = Constants.FILE_BANNER_SEQUENCE_NAME;
        } else if (fileType.equals(Constants.FILE_TYPE_DOCUMENT)) {
            str.append(Constants.FILE_PREFIX_DUCUMENT_NAME);
            sequenceName = Constants.FILE_DOCUMENT_SEQUENCE_NAME;
        } else if (fileType.equals(Constants.FILE_TYPE_GENERAL)) {
            str.append(Constants.FILE_PREFIX_GENERAL_NAME);
            sequenceName = Constants.FILE_GENERAL_SEQUENCE_NAME;
        }

        Long nextCode = SequenceHelper.getNextValue(sequenceName);
        if (nextCode != null  && nextCode >0){
            str.append("-");
            str.append(formatter.format(nextCode.longValue(), Constants.FILE_NUMBER_FORMAT));
        } else {
            return null;
        }
        String ext = realFileName.substring(realFileName.lastIndexOf('.'));
        str.append(ext);
        return str.toString();
    }
}
