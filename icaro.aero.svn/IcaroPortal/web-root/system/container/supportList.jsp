<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="document.document" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="container.container" bundle="systemMenu" /> >
			<bean:message key="document.document" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.document" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<html:form action="/system/container/support">
<html:hidden property="action" value="list" />
<!-- Find -->
<table align="center" bgcolor="#CCCCCC" border="0" cellpadding="4" cellspacing="1" width="95%">
	<tr bgcolor="F3F3F3">
		<td>
			<img src="<%= request.getContextPath() %>/images/icons/find.gif" width="16" height="16" border="0" align="absmiddle"> <span class="subtitle"><bean:message key="label.search" bundle="messages" />:</span><br>
			&nbsp;
			<table border="0" cellpadding="4" cellspacing="0">
				<tr>
					<td width="220">
						<bean:message key="prompt.name" /><br>
						<html:text property="title" styleClass="input" style="width: 200px;" />					
					</td>
					<td width="140" colspan="2">
						<bean:message key="prompt.documentType" /><br />
						<html:select property="docTypeCode" size="1" styleClass="input" style="width: 180px;">
							<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
							<html:options collection="supportTypeList" property="code" labelProperty="name"/>
						</html:select>
					</td>
					<!--td width="140">
						<bean:message key="prompt.from" /><br>
						<html:text property="fromDate" styleId="fromDate" style="width: 100px;" readonly="true"/> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;">					
					</td-->
					<!--td width="140">
						<bean:message key="prompt.to" /><br>
						<html:text property="toDate" styleId="toDate" style="width: 100px;" readonly="true" /> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;">					
					</td-->
					<td width="10"></td>
					<td><br><html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" />
					</html:submit></td>
				</tr>
				<tr>
				  <td>
				  	<bean:message key="prompt.category" /><br />
					<html:select property="categoryCode" size="1" styleClass="input" style="width: 180px;">
						<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
						<html:options collection="supportCategoryList" property="code" labelProperty="name"/>
					</html:select>
				  </td>
				  <td>&nbsp;</td>
				  <td>
				  	<bean:message key="prompt.orderBy" /><br />
					<html:select property="orderField" size="1" styleClass="input" style="width: 120px;">
						<html:option value="name"><bean:message key="prompt.name" /></html:option>
						<html:option value="category"><bean:message key="prompt.category" /></html:option>
						<html:option value="isEnabled"><bean:message key="prompt.enabled" /></html:option>
						<html:option value="docTypeCode"><bean:message key="prompt.documentType" /></html:option>
					</html:select>
					<html:select property="orderAsc" size="1" styleClass="input" style="width: 120px;">
						<html:option value="1">ASC</html:option>
						<html:option value="0">DEC</html:option>
					</html:select>
				  </td>
				  <td></td>
				  <td>&nbsp;</td>
			  </tr>
		  </table>
		</td>
	</tr>
</table>
</html:form>
<br>
<html:form action="/system/container/support">
<html:hidden property="action" value="" />
<!-- Options -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doAction(document.forms[1], 'action', 'create')"><img src="${pageContext.request.contextPath}/images/system/icons/anadir.gif" alt="Add" width="26" height="27" border="0" /></a></td>
    <td align="center"><a href="javascript: doActionN(document.forms[1], 'action', 'read', 'codes')"><img src="${pageContext.request.contextPath}/images/system/icons/editar.gif" alt="Edit" width="26" height="27"  border="0"/></a></td>
    <td align="center"><a href="javascript: doActionN(document.forms[1], 'action', 'delete', 'codes')"><img src="${pageContext.request.contextPath}/images/system/icons/eliminar.gif" alt="Delete" width="26" height="27" border="0" /></a></td>
</tr>
<tr>
	<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doActionN(document.forms[1], 'action', 'create')"><bean:message key="button.add" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(document.forms[1], 'action', 'read', 'codes')"><bean:message key="button.edit" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(document.forms[1], 'action', 'delete', 'codes')"><bean:message key="button.delete" bundle="messages" /></a></td>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>			
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<!-- FIN Options -->

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.title" bundle="messages" /></th>
		<th width="150"><bean:message key="label.section" bundle="messages" /></th>
		<th width="110"><bean:message key="label.from" bundle="messages" /></th>
		<th width="110"><bean:message key="label.to" bundle="messages" /></th>
		<th width="110"><bean:message key="prompt.enabled" /></th>
		<th width="110"><bean:message key="label.documentType" bundle="messages" /></th>
		<th width="110">&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${supportContainerList}" varStatus="status">		
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td align="center">${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td>${item.title}</td>
			<td align="center">${item.category.name}</td>
			<td align="center"><fmt:formatDate value="${item.from.time}" pattern="${appLanguage.dateFormat}"/></td>
			<td align="center"><fmt:formatDate value="${item.to.time}" pattern="${appLanguage.dateFormat}"/></td>
			<td align="center">
				<c:choose>
					<c:when test="${item.isEnabled == true}">
						<bean:message key="label.yes" bundle="messages" />
					</c:when>
					<c:otherwise>
						<bean:message key="label.no" bundle="messages" />
					</c:otherwise>
				</c:choose>
			</td>
			<td align="center"><img src="${pageContext.request.contextPath}${item.type.icon}" alt="${item.type.name}"></td>
			<td align="center"><a href="${pageContext.request.contextPath}${item.path}" target="_blank">Ver</a></td>
		</tr>
	</c:forEach>
</table>
</html:form>