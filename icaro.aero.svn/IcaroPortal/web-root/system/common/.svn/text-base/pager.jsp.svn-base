<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<script language="javascript">
function sendPage(frm, page){
	frm.action.value="list";
	frm.pageNumber.value=page;
	var button =  document.getElementById('inputSubmitPage');
	button.click();
}
</script>
<html:hidden property="pageNumber" />
<span style="display:none"><html:submit styleId="inputSubmitPage" ><bean:message key="button.ok" bundle="messages" /></html:submit></span>
<c:set value="1" var="isFirstItem" />
<c:set value="${styleClass}" var="style" />

<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<c:forEach var="item" items="${pages}" varStatus="status">
			<c:choose>
				<c:when test="${item == currentPage }">
					<c:set value="${selectedClass}" var="style" />
				</c:when>
				<c:otherwise>
					<c:set value="${styleClass}" var="style" />
				</c:otherwise>
			</c:choose>
			<c:if test="${isFirstItem == 0}">
			<td align="center">&bull;&nbsp;</td>
			</c:if>
			<td align="center"><a href="javascript: sendPage(document.forms[${formIndex}], ${item})" class="${style}">${item}</a>&nbsp;</td>
			<c:if test="${isFirstItem == 1}">
				<c:set value="0" var="isFirstItem" />
			</c:if>
		</c:forEach>
		
	</tr>
</table>