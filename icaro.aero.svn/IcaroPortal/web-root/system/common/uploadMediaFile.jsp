<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

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
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/validator.jsp"></script>
	
<script language="javascript">
var path = "${uploadMediaForm.uploadPath}";
var width = "${uploadMediaForm.width}";
var height = "${uploadMediaForm.height}";
var isFlash = "${uploadMediaForm.isFlash}";
var sid = "${uploadMediaForm.sid}";
	
	function changeUrl(update){
		var frame = document.getElementById("displayMedia");
		sid = sid + "1";
		var src = "?path=" + path;
		src = src + "&width=" + width;
		src = src + "&height=" + height;
		src = src + "&isFlash=" + isFlash;
		src = src + "&sid=" + sid;
		if (update == 1)
			src = src + "&update=true";
		frame.src = "${pageContext.request.contextPath}/system/common/displayMedia.jsp" + src;
	}
	
	function calculateSize(btn, widthControlName, heightControlName){
		width = "";
		height = "";
		changeUrl(1);
	}
	function updateValues(width, height){
		var frm = document.forms[0];
		var widthControl = frm.elements["width"];
		var heightControl = frm.elements["height"];
		widthControl.value = width;
		heightControl.value = height;
	}
	
	function changeSize(btn, widthControlName, heightControlName){
		var frm = btn.form;
		var widthControl = frm.elements[widthControlName];
		var heightControl = frm.elements[heightControlName];
		width = widthControl.value;
		height = heightControl.value;
		changeUrl();
	}
	
	function resetForm(btn, widthControlName, heightControlName){
		var frm = btn.form;
		frm.reset();
		changeSize(btn, widthControlName, heightControlName);
	}
	
	function Close(btn){
		var frm = btn.form;
		var frmTarget = frm.form.value;
		var objTargetCode = frm.propertyCode.value;
		var objTargetValue = frm.propertyName.value;
		if (opener.location.href != frm.parent.value) {
			alert("This window is out of context!");
			return;
		}
		opener.document.forms[frmTarget].elements[objTargetCode].value = frm.code.value;
		opener.document.forms[frmTarget].elements[objTargetValue].value = frm.name.value;
		imgPhoto = opener.document.getElementById('imgPhoto');
		if (imgPhoto != null && imgPath != null && imgPath.length > 0 ){
			imgPhoto.src = imgPath;
		}
		close();
	}
	
	
</script>
</head>

<body>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="480">
    <tr>
	    <td style="padding: 10px 10px 10px 10px;">
            <span class="title"><bean:message key="label.uploadFile" bundle="messages" /></span><br>
            &nbsp;<br>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
					<html:messages id="message" message="true">
						<div class="message"><c:out value="${message}" escapeXml="false" /></div>
					</html:messages>
					<div class="error"><html:errors/></div>
					</td>
				</tr>
			</table>
			<br>
            <html:form action="/popup/uploadMediaFile" method="POST" enctype="multipart/form-data" onsubmit="return validateUploadMediaForm(this);">
			<html:hidden property="code" />
            <html:hidden property="parent" />
            <html:hidden property="form" />
			<html:hidden property="path" />
			<html:hidden property="basePath" />
            <html:hidden property="propertyCode" />
			<html:hidden property="propertyName" />
			<html:hidden property="mediaType" />
			<html:hidden property="mediaName" />
			<html:hidden property="uploadPath" />
            <table border="0" cellpadding="0" cellspacing="2">
				<tr>
                    <td class="required">* Nombre : </td>
                    <td>
                        <html:text property="name" maxlength="200" style="width: 250px;" />                    </td>
                </tr>
				<tr>
                    <td class="required">*  Categor&iacute;a : </td>
                    <td >
						<html:select property="categoryCode" style="width: 250px;">
						<c:choose>
							<c:when test="${uploadMediaForm.mediaType == initParameters.MEDIA_TYPE_IMAGE}">
									<html:options collection="imageCategoryList" property="code" labelProperty="name" />
							</c:when>
							<%--
							<c:when test="${uploadMediaForm.mediaType == initParameters.MEDIA_TYPE_BANNER}">
									<html:options collection="bannerCategoryList" property="code" labelProperty="name" />
							</c:when>
							 --%>
						</c:choose>
						</html:select>
					</td>
                </tr>
				<tr>
                    <td class="required">* Archivo : </td>
                    <td ><html:file property="file" style="width: 250px;" /></td>
                </tr>
				<c:if test="${uploadMediaForm.mediaType == initParameters.MEDIA_TYPE_BANNER}">
					<tr>
         	           	<td class="optional">Url : </td>
        	            <td><html:text property="url" style="width: 250px;" /></td>
	                </tr>
				</c:if>
				<tr>
                    <td class="required">* Ancho : </td>
                    <td ><html:text property="width" value="100" maxlength="4" style="width: 50px;"/></td>
                </tr>
				<tr>
                    <td class="required">* Alto : </td>
                    <td ><html:text property="height" value="100" maxlength="4" style="width: 50px;"/></td>
                </tr>
				<c:if test="${!empty uploadMediaForm.code && uploadMediaForm.code>0}">
				<tr>
                    <td colspan="2">
						<c:if test="${!uploadMediaForm.isFlash}">
							<input type="button" class="button" name="btnCalculate" value="Calcular Tama&ntilde;o" style="width: 120px;" onClick="calculateSize(this, 'width', 'height');" onMouseOver="swapClass(this, 'button', 'buttonhi')" onMouseOut="swapClass(this, 'button', 'buttonhi')">
						</c:if>
						<input type="button" class="button" name="btnRefresh" value="Refrescar" style="width: 100px;" onClick="changeSize(this, 'width', 'height');"  onMouseOver="swapClass(this, 'button', 'buttonhi')" onMouseOut="swapClass(this, 'button', 'buttonhi')">
						<input type="button" class="button" name="btnReset" value="Resetear" style="width: 100px;" onClick="resetForm(this, 'width', 'height');" onMouseOver="swapClass(this, 'button', 'buttonhi')" onMouseOut="swapClass(this, 'button', 'buttonhi')">
						
					</td>
				</tr>
				</c:if>
                <tr>
                    <td colspan="2"><hr width="100%" size="1" color="#C0C0C0" noshade></td>
                </tr>
				<tr>
                    <td colspan="2">
						<c:choose>
							<c:when test="${!empty uploadMediaForm.code && uploadMediaForm.code>0}">
								<html:submit style="width: 80px;">Modificar</html:submit>
								<input type="button" name="btnFinish" value="Selecionar Imagen" style="width: 160px;" onClick="Close(this)">
							</c:when>
							<c:otherwise>
								<html:submit style="width: 80px;">Cargar</html:submit>
							</c:otherwise>
						</c:choose>					</td>
                </tr>
				<tr>
                    <td colspan="2"><hr width="100%" size="1" color="#C0C0C0" noshade></td>
                </tr>
            </table>
            </html:form>
			<html:javascript formName="uploadMediaForm" dynamicJavascript="true" staticJavascript="false" />
        </td>
		<td>
		</td>
    </tr>
</table>
<c:if test="${!empty uploadMediaForm.code && uploadMediaForm.code>0}">
	<iframe name="displayMedia" id="displayMedia" src="${pageContext.request.contextPath}/system/common/displayMedia.jsp?path=${uploadMediaForm.uploadPath}&width=${uploadMediaForm.width}&height=${uploadMediaForm.height}&isFlash=${uploadMediaForm.isFlash}&sid=${uploadMediaForm.sid}" frameborder="1" width="100%" height="300" />
</c:if>
</body>
</html>