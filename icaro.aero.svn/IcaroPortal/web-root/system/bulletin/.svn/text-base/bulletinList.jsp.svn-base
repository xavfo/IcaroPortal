<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">${currentItem.name}<br/> - <span class="subtitle"><bean:message key="bulletin.bulletin" bundle="systemMenu" /></span></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="bulletin.topic" bundle="systemMenu" /> >
			<bean:message key="bulletin.bulletin" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
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

<%-- html:form action="/system/bulletin/bulletin">
<html:hidden property="action" value="list" />
<!-- Find -->
<table bgcolor="#CCCCCC" border="0" cellpadding="4" cellspacing="1" width="100%">
	<tr bgcolor="F3F3F3">
		<td>
			<img src="<%= request.getContextPath() %>/images/icons/find.gif" width="16" height="16" border="0" align="absmiddle"> <span class="subtitle"><bean:message key="label.search" bundle="messages" />:</span><br>
			&nbsp;
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="220">
						<bean:message key="prompt.subject" /><br>
						<html:text property="subject" styleClass="input" style="width: 200px;" />
					</td>
					<td width="140">
						<bean:message key="prompt.from" /><br>
						<html:text property="fromDate" styleId="fromDate" style="width: 100px;" /> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;">
					</td>
					<td width="140">
						<bean:message key="prompt.to" /><br>
						<html:text property="toDate" styleId="toDate" style="width: 100px;" /> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;">
					</td>
					<td width="10"></td>
					<td><br><html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form --%>
<!-- Options -->
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.bulletin">
				<tiles:put name="code" value="${currentItem.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/bulletin/bulletin">
			<html:hidden property="action" value="" />
			<html:hidden property="itemCode" value="${currentItem.code}" />
			<html:hidden property="tab" value="${param.tab}" />
			<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
				</tr>
				<tr>
					<td>
					
						<tiles:insert definition="system.navBar">
							<tiles:put name="add" 		value="true" />
							<tiles:put name="edit" 		value="true" />
							<tiles:put name="delete" 	value="true" />
						</tiles:insert>
						<!-- List data -->
						<table class="list" border="0" cellpadding="2" cellspacing="1" width="95%" align="center">
							<tr>
								<th width="30">#</th>
								<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
								<th><bean:message key="label.subject" bundle="messages" /></th>
								<th width="120"><bean:message key="label.sentBy" bundle="messages" /></th>
								<th width="120"><bean:message key="label.date" bundle="messages" /></th>
							</tr>
							<c:forEach var="item" items="${bulletinList}" varStatus="status">
								<c:url value="/system/bulletin/bulletin.do" var="urlRead">
									<c:param name="action" value="read" />
									<c:param name="code" value="${item.code}" />
									<c:param name="itemCode" value="${currentItem.code}" />
									<c:param name="tab" value="${param.tab}" />
								</c:url>
								<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
									<td width="30">${status.count}</td>
									<td width="30" align="center"><html:checkbox property="codes" value="${item.code}" /></td>
									<td><a href="${urlRead}" class="item">${item.subject}</a></td>
									<td width="120">${item.lastUpdateUser}</td>
									<td width="120"><fmt:formatDate value="${item.lastUpdateDate.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></td>
								</tr>
							</c:forEach>
					</table>
					
					</td>
				</tr>
			</table>
			</html:form>
		</td>
	</tr>
</table>







<%-- script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	: "fromDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "fromBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "toDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "toBtn"
		}
	);
</script --%>