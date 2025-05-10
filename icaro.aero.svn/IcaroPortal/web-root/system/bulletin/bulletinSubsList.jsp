<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="bulletin.bulletin" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="bulletin.bulletin" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.bulletin.bulletin" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
		<td align="right">
			<c:url value="/system/bulletin/bulletin.do" var="urlBack">
				<c:param name="action" value="read" />
				<c:param name="code" value="${bulletin.code}" />
			</c:url>
			<bean:message key="label.backTo" bundle="messages" />:<a href="${urlBack}" class="back"> ${bulletin.subject}</a>
		</td>			
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="right">
			<c:url value="/system/bulletin/bulletin.do" var="urlBack">
				<c:param name="action" value="list" />
			</c:url>
			<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="bulletin.bulletin" bundle="systemMenu" /></a>
		</td>			
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">			
			<bean:message key="message.bulletinSubcribers" bundle="systemHelp" arg0="${bulletin.subject}" />				
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>


<html:form action="/system/bulletin/sendList">
<html:hidden property="action" value="list" />
<html:hidden property="bulletinCode" value="${bulletin.code}" />
<!-- Find -->
<table bgcolor="#CCCCCC" border="0" cellpadding="4" cellspacing="1" width="100%">
	<tr bgcolor="F3F3F3">
		<td>
			<img src="<%= request.getContextPath() %>/images/icons/find.gif" width="16" height="16" border="0" align="absmiddle"> <span class="subtitle"><bean:message key="label.search" bundle="messages" />:</span><br>
			&nbsp;
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="220">
						<bean:message key="prompt.name" /><br>
						<html:text property="firstName" styleClass="input" style="width: 200px;" />
					</td>
					<td width="220">
						<bean:message key="prompt.country" /><br>
						<html:select property="countryCode" size="1" styleClass="input" style="width: 200px;">
 							<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
							<html:options collection="countryList" property="code" labelProperty="name"/>
						</html:select>
					</td>
					<td width="220">
						<bean:message key="prompt.city" /><br>
						<html:text property="city" styleClass="input" style="width: 200px;" />
					</td>
					<td width="10"></td>
					<td><br><html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<br>
<html:form action="/system/bulletin/sendBulletin">
<html:hidden property="action" value="" />
<html:hidden property="bulletinCode" value="${bulletin.code}" />
<!-- Options -->
<div>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'send')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.send" bundle="messages" /></html:button>
</div>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.name" bundle="messages" /></th>
		<th width="100"><bean:message key="label.enabled" bundle="messages" /></th>
		<th width="100"><bean:message key="label.country" bundle="messages" /></th>
		<th width="100"><bean:message key="label.city" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${subscriberList}" varStatus="status">
		<c:url value="/system/bulletin/subscriber.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td>${item.user.lastName} ${item.user.secondLastName} ${item.user.firstName}</td>			
			<td>${item.enabled}</td>
			<td>${item.user.country.name}</td>
			<td>${item.user.city}</td>
		</tr>
	</c:forEach>
</table>
</html:form>