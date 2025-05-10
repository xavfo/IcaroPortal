<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<jsp:useBean id="popupForm" scope="request" class="com.yage.struts.action.PopUpForm"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title><bean:message key="label.preview" bundle="messages" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
    
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
</head>

<body>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
	    <td style="padding: 10px 10px 10px 10px;">
            <span class="title"><bean:message key="label.preview" bundle="messages" /></span><br>
            &nbsp;<br>
            <hr width="100%" size="1" color="#C0C0C0" noshade>
        </td>
    </tr>
    <tr>
	    <td style="padding: 10px 10px 10px 10px;" align="center">
			<c:choose>
				<c:when test="${uploadMediaForm.mediaType == initParameters.MEDIA_TYPE_BANNER && media.isFlash}">
				  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="${media.width}" height="${media.height}">
                    <param name="flash_component" value="ImageViewer.swc">
                    <param name="movie" value="${pageContext.request.contextPath}/${media.path}">
                    <param name="quality" value="high">
                    <param name="FlashVars" value="flashlet={imageLinkTarget:'_blank',captionFont:'Verdana',titleFont:'Verdana',showControls:true,frameShow:false,slideDelay:5,captionSize:10,captionColor:#333333,titleSize:10,transitionsType:'Random',titleColor:#333333,slideAutoPlay:false,imageURLs:['img1.jpg','img2.jpg','img3.jpg'],slideLoop:false,frameThickness:2,imageLinks:['http://macromedia.com/','http://macromedia.com/','http://macromedia.com/'],frameColor:#333333,bgColor:#FFFFFF,imageCaptions:[]}">
                    <embed src="${pageContext.request.contextPath}/${media.path}" quality="high" flashvars="flashlet={imageLinkTarget:'_blank',captionFont:'Verdana',titleFont:'Verdana',showControls:true,frameShow:false,slideDelay:5,captionSize:10,captionColor:#333333,titleSize:10,transitionsType:'Random',titleColor:#333333,slideAutoPlay:false,imageURLs:['img1.jpg','img2.jpg','img3.jpg'],slideLoop:false,frameThickness:2,imageLinks:['http://macromedia.com/','http://macromedia.com/','http://macromedia.com/'],frameColor:#333333,bgColor:#FFFFFF,imageCaptions:[]}" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="${media.width}" height="${media.height}"> </embed>
			      </object>
				</c:when>
				<c:otherwise>
					<img src="${pageContext.request.contextPath}/${media.path}" border="0" title="${media.info}" width="${media.width}" height="${media.height}">
				</c:otherwise>
			</c:choose>        
		</td>
    </tr>
</table>



</body>
</html>


