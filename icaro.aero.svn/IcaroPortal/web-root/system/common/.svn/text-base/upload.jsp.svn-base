<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title><bean:message key="label.uploadImage" bundle="messages" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
    
    <script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
</head>

<body>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="480">
    <tr>
	    <td style="padding: 10px 10px 10px 10px;">
            <span class="title"><bean:message key="label.uploadImage" bundle="messages" /></span><br>
            &nbsp;<br>
            <html:form action="/popup/upload" method="POST" enctype="multipart/form-data">
            <html:hidden property="parent" />           
            <html:hidden property="form" />
            <html:hidden property="property" />
			<html:hidden property="path" />
			<html:hidden property="prefix" />
			<html:hidden property="sequence" />
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td width="250">
                        <span class="subtitle">1. Seleccione la imagen</span><br>
                        Selecione la imagen que desea cargar haciendo clic sobre el bot&oacute;n Examinar (Browse si su navegador est&aacute; en ingl&eacute;s).<br>
                        &nbsp;
                    </td>
                    <td align="right">
                        <html:file property="file" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="subtitle">2. Cargue la imagen</span><br>
                        Presione el bot&oacute;n Cargar. Si existe una imagen ya cargada, &eacute;sta ser&aacute; reemplazada.<br>
                        &nbsp;
                    </td>
                    <td align="right"><html:submit style="width: 80px;">Cargar</html:submit></td>
                </tr>
                <tr>
                    <td>
                        <span class="subtitle">3. Espere unos segundos...</span><br>
                        Espere mientras su imagen se despliega en el recuadro inferior.<br>
                        &nbsp;
                    </td>
                    <td align="right"><input type="button" name="btnFinish" value="Aceptar" style="width: 80px;" onClick="closeUpload(this, '<c:out value="${fileUrl}" />', '<c:out value="${pageContext.request.contextPath}${fileUrl}" />')"></td>
                </tr>
            </table>
            </html:form>
            <hr width="100%" size="1" color="#C0C0C0" noshade>            
            <c:if test="${!empty requestScope.fileUrl}">
				<img src="${pageContext.request.contextPath}${requestScope.fileUrl}" border="0">
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
