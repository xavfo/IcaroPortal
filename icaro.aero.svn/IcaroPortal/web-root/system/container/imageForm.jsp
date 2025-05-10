<%@ page language="java" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<script language="javascript">	
	function calculateSize(btn, widthControlName, heightControlName){
		var frm = btn.form;
		var widthControl = frm.elements[widthControlName];
		var heightControl = frm.elements[heightControlName];
		var img = document.getElementById("image");
		widthControl.value = img.width;
		heightControl.value = img.height;
	}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">			
			<bean:message key="information.container.image" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="information.container.image" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="container.container" bundle="systemMenu" /> >
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<html:form action="/system/container/image/save" method="POST" enctype="multipart/form-data" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="code" />
<html:hidden property="parent" />
<html:hidden property="form" />
<html:hidden property="path" />
<html:hidden property="basePath" value="/generales" />
<html:hidden property="propertyCode" />
<html:hidden property="propertyName" />
<html:hidden property="mediaType" value="${initParameters.MEDIA_TYPE_IMAGE}" />
<html:hidden property="mediaName" />
<html:hidden property="uploadPath" />

<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td align="right">
  	<tiles:insert definition="system.navBar">
		<tiles:put name="save" 		value="true" />
		<tiles:put name="apply"		value="true" />
		<tiles:put name="reset" 	value="true" />
		<tiles:put name="cancel" 	value="true" />
	</tiles:insert>
  </td>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			<ul>
			<div class="error"><html:errors/></div>		

<%--		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		<div class="error"><html:errors/></div>	
--%>		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty uploadMediaForm.code && uploadMediaForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${uploadMediaForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" maxlength="200" /></td>
	</tr>	
	<tr>
		<td class="required">* <bean:message key="prompt.category" /> :</td>
		<td>
			<html:select property="categoryCode" style="width: 250px;">
				<html:options collection="categoryList" property="code" labelProperty="name" />
			</html:select> 
		</td>
	</tr>
	<c:if test="${!empty uploadMediaForm.uploadPath }">
		<tr>
			<td class="label"></td>
			<td>${ uploadMediaForm.uploadPath }</td>
		</tr>	
	</c:if>
	<tr>
		<td class="required">* <bean:message key="prompt.image" />:</td>
		<td><html:file property="file" style="width: 250px;" /> </td>
	</tr>	
	<c:if test="${!empty uploadMediaForm.code && uploadMediaForm.code>0}">
	<tr>
        <td></td>
        <td ><input type="button" name="btnCalculate" value="Calcular Tama&ntilde;o" style="width: 160px;" onClick="calculateSize(this, 'width', 'height');"></td>
	</tr>
	</c:if>
	<tr>
		<td class="required">* <bean:message key="prompt.width" />:</td>
        <td ><html:text property="width" maxlength="4" style="width: 50px;"/></td>
    </tr>
	<tr>
		<td class="required">* <bean:message key="prompt.height" />:</td>
        <td ><html:text property="height" maxlength="4" style="width: 50px;"/></td>
    </tr>
	<%--<tr>
		<td class="required">* <bean:message key="prompt.enabled" /></td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>		
	</tr>--%>
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
</table>
</html:form>
<html:javascript formName="uploadMediaForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty uploadMediaForm.code && uploadMediaForm.code != 0}">
			var msg = 'Se actualizar\u00E1 la ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear una nueva ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateUploadMediaForm(frm);
	  } else { return false; }
	}
</script>

