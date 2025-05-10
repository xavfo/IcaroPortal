<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>


<c:url value="/system/bulletin/step1.do" var="urlBack">
	<c:param name="action" value="read" />							
</c:url>

<script language="javascript">
function send(frm){	
	var button =  document.getElementById('inputSubmit');
	button.click();
}
function stepBack(){	
	setLocation('${urlBack}');
}
function cancel(frm) {
	var button =  document.getElementById('inputCancel');
	button.click();
}

</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="bulletin.sendBulletin" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="bulletin.bulletin" bundle="systemMenu" /> >
			<bean:message key="bulletin.sendBulletin" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<table border="0" cellpadding="2" cellspacing="2">
	<tr>
		<td>
			<table class="stepOff" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td>
						<bean:message key="label.step1" bundle="messages" /><br>Seleccionar Bolet&iacute;n
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>
		<td>
			<table class="step2" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td class="stepLabel">
						<bean:message key="label.step2" bundle="messages" /><br>Vista Previa/ Env&iacute;o
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>		
		<td>
			<table class="stepOff" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td>
						<bean:message key="label.step3" bundle="messages" /><br>Confirmaci&oacute;n de Env&iacute;o
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">

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
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<html:form action="/system/bulletin/step3">
<html:hidden property="action" value="step3" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle"></td>
		<td align="right">
			<span style="display:none"><html:submit styleId="inputSubmit" ><bean:message key="button.ok" bundle="messages" /></html:submit></span>
			<span style="display:none"><html:cancel styleId="inputCancel"><bean:message key="button.cancel" bundle="messages" /></html:cancel></span>
			<table border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td align="center"><a href="javascript: send(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/enviar.gif" alt="<bean:message key="button.send" bundle="messages" />" width="26" height="27" border="0" /></a></td>
					<td align="center"><a href="javascript: stepBack(); "><img src="${pageContext.request.contextPath}/images/system/icons/regresar.gif" width="27" height="26" border="0" alt=""></a></td>
					<td align="center"><a href="javascript: cancel(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="<bean:message key="button.cancel" bundle="messages" />" width="26" height="27" border="0" /></a></td>
				</tr>
				<tr>
					<td align="center"><a href="javascript: send(document.forms[0]);"><bean:message key="button.send" bundle="messages" /></a></td>
					<td align="center"><a href="javascript: stepBack(); "><bean:message key="button.back" bundle="messages" /></a></td>
					<td align="center"><a href="javascript: cancel(document.forms[0]);"><bean:message key="button.cancel" bundle="messages" /></a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
	  <td colspan="2" height="25"></td>
	</tr>
	<tr>
		<td class="optional">De :</td>
		<td>${initParameters.SMTP_FROM}</td>
	</tr>
	<c:if test="${ !empty (topicList) && bulletinBean.topicCode != initParameters.GENERAL_BULLETIN_CODE }">
	<tr>
		<td class="optional">Enviar a Suscritos en :</td>
		<td><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"> Seleccionar todos</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<c:forEach var="item" items="${topicList}">
		<html:checkbox property="codes" value="${item.code}" /> ${item.name}<br>
		</c:forEach>
		</td>
	</tr>
	</c:if>
	<tr><td colspan="2" height="25"></td></tr>
	<tr>
		<td colspan="2">
			<table width="75%" align="center" bgcolor="#ffffff">
				<tr>
					<td><c:import url="/system/bulletin/sendBulletin/previewBulletin.jsp" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="2" height="25"></td></tr>
</table>
</html:form>