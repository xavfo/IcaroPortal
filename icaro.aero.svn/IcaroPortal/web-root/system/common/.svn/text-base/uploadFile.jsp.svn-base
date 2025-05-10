<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title><bean:message key="label.uploadFile" bundle="messages" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
    
    <script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
	
	<script language="JavaScript1.2">
		
		function closeUpload(btn, srcPath, imgPath) {
		    var frm = btn.form;
		    var frmTarget = frm.form.value;
		    var objTarget = frm.property.value;
			var idx = frm.index.value;
		    
			if (opener.location.href != frm.parent.value) {
				alert(opener.location.href);
				alert(frm.parent.value);
				alert("This window is out of context!");
				return;
			}
			if ( !opener.document.forms[frmTarget] )
				var openerForm = opener.document.forms[0];
			else 
				var openerForm = opener.document.forms[frmTarget];
			if (openerForm.elements[objTarget][idx] != undefined) {
			    openerForm.elements[objTarget][idx].value = srcPath;
			} else {
			    openerForm.elements[objTarget].value = srcPath;
			}
		    //opener.document.forms[frmTarget].elements[objTarget].value = srcPath;
			imgPhoto = opener.document.getElementById('imgPhoto');
			if (imgPhoto != null && imgPath != null && imgPath.length > 0 ){
				imgPhoto.src = imgPath;
			}
		    close();
		}
	</script>
</head>

<body>
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="left">
<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>
<tr>
	<td class="subtitle">&nbsp;&nbsp;<bean:message key="label.uploadFile" bundle="messages" /><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td>
</tr>
<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>
<tr>
	<td valign="top">
		<table cellpadding="0" cellspacing="0" border="0" align="center" height="220" width="97%">		
		<tr>
			<td valign="top">
			<html:form action="/popup/uploadFile" method="POST" enctype="multipart/form-data" onsubmit="return preValidate(this);">
            <html:hidden property="parent" />           
            <html:hidden property="form" />
            <html:hidden property="property" />
			<html:hidden property="path" />
			<html:hidden property="prefix" />
			<html:hidden property="sequence" />
			<html:hidden property="index" />
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td width="250">
                        <span class="subtitle">1. Seleccione el archivo</span><br>
                        Selecione el archivo que desea cargar haciendo clic sobre el bot&oacute;n Examinar (Browse si su navegador estÇ&aacute; en ingl&eacute;s).<br>
                        &nbsp;
                    </td>
                    <td align="right">
                        <html:file property="file" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="subtitle">2. Cargue el archivo</span><br>
                        Presione el bot&oacute;n Cargar. Si existe un archivo ya cargada, &eacute;ste ser&aacute; reemplazado.<br>
                        &nbsp;
                    </td>
                    <td align="right"><html:submit style="width: 80px;">Cargar</html:submit></td>
                </tr>
                <tr>
                    <td>
                        <span class="subtitle">3. Espere unos segundos...</span><br>
                        Espere mientras el nombre de su archivo se despliega en el recuadro inferior.<br>
                        &nbsp;
                    </td>
                    <td align="right"><input type="button" name="btnFinish" value="Aceptar" style="width: 80px;" onClick="closeUpload(this, '<c:out value="${fileUrl}" />', '<c:out value="${pageContext.request.contextPath}${fileUrl}" />')"></td>
                </tr>
            </table>
            </html:form>
			</td>
		</tr>
		<c:if test="${!empty requestScope.fileUrl}">
		<tr><td colspan="4"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>
		<tr><td align="center" valign="top">${requestScope.fileUrl}</td></tr>
		</c:if>
		</table>		
   </td>
</tr>
<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>		
</table>
</body>
</html>

<script language="JavaScript1.2">
	
	function preValidate (frm) {
		var isOk = true;
    	if ( trim(frm.elements['file'].value).length == 0 ) {
			isOk = false;
			alertMsg = 'Primero debe seleccionar el archivo.';
		}
		 if (isOk == true) return true;
		 else alert(alertMsg);
		 return false;
	}
	
</script>

