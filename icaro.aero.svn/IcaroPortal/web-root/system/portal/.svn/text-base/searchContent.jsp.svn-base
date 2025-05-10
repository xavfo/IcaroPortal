<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<script language="javascript">

function setValues(code, description) {
	var frm = document.forms[0];
	var idx = frm.formIndex.value;
	var objTarget = frm.controlCode.value;
	var objLabel  = frm.controlDescription.value;

	opener.document.forms[idx].elements[objTarget].value = code;
	opener.document.forms[idx].elements[objLabel].value = description;
	close();
}

</script>

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<html:form action="/system/portal/searchContent/list" focus="title">
<html:hidden property="action" value="search" />
<html:hidden property="formIndex" />
<html:hidden property="controlCode" />
<html:hidden property="controlDescription" />
<html:hidden property="level" />
<html:hidden property="equal" />
<html:hidden property="actualCode" />
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>					
			<td width="100%" bgcolor="#EBEEF3">
				<table border="0" cellpadding="3" cellspacing="2" width="100%">
					<tr>
						<td width="130">
							<bean:message key="label.name" bundle="messages" />
							<html:text property="title" styleClass="input" style="width: 120px;" maxlength="100" />
						</td>
						<td width="130">
							<bean:message key="label.enabled" bundle="messages" /><br>
							<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
							<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
						</td>
						<td valign="bottom">
							<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<br>

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="30">#</th>
		<th width="150"><bean:message key="label.section" bundle="messages" /></th>
		<th width="150"><bean:message key="label.parent" bundle="messages" /></th>
		<th width="150"><bean:message key="label.order" bundle="messages" /></th>
		<th width="110"><bean:message key="prompt.enabled" /></th>
	</tr>
	<c:forEach var="item" items="${contentList}" varStatus="status">		
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td align="center">${status.count}</td>
			<td><a href="javascript:setValues(${item.code}, '${item.title}');">${item.menuAlias}</a></td>
			<td>
				&raquo;
				<c:choose>
					<c:when test="${!empty item.parent && !empty item.parent.parent }">
						${item.parent.parent.title} &raquo; ${item.parent.title}
					</c:when>
					<c:when test="${!empty item.parent}">
						${item.parent.title}
					</c:when>
				</c:choose>
			</td>
			<td align="center">${item.order}</td>
			<td align="center">
				<c:choose>
					<c:when test="${item.enabled == true}">
						<bean:message key="label.yes" bundle="messages" />
					</c:when>
					<c:otherwise>
						<bean:message key="label.no" bundle="messages" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table align="center">
	<tr>
		<td>
			<tiles:insert definition="system.pager">
				<tiles:put name="styleClass" value="pageItem" />
				<tiles:put name="selectedClass" value="pageSelItem" />
				<tiles:put name="totalPages" value="${contentForm.totalPages}" />
				<tiles:put name="currentPage" value="${contentForm.pageNumber}" />
				<tiles:put name="formIndex" value="0" />
			</tiles:insert>
		</td>
	</tr>
</table>
</html:form>