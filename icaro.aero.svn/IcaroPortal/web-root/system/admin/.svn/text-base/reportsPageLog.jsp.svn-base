<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="audit.reports" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="audit" bundle="systemMenu" /> >
			<bean:message key="audit.reports" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td><ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			<ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>

<!--inicio buscador-->
<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
			<html:form action="/system/admin/reportsPageLog" onsubmit="return validatePageLogForm(this);">
			<html:hidden property="action" value="list" />
			<html:hidden property="search" value="true" />
			<c:forEach var="item" items="${typeList}" varStatus="status">
				<c:set target="${item}" property="nameValue"><bean:message key="${item.nameValue}"/></c:set>
			</c:forEach>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_iz.gif" width="7" height="7" alt="" border="0"></td>
					<td background="${pageContext.request.contextPath}/images/search/tile_superior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_der.gif" width="7" height="7" alt="" border="0"></td>
				</tr>
				<tr>
					<td background="${pageContext.request.contextPath}/images/search/tile_iz.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" height="100%" alt="" border="0"></td>
					<td width="100%" bgcolor="#EBEEF3">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr><td class="subtitle">&nbsp;<img src="${pageContext.request.contextPath}/images/icons/find.gif" width="16" height="16" border="0" alt=""> <bean:message key="label.search" bundle="messages" /></td></tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="2" width="100%">
							<tr>
								<td width="150">
								<bean:message key="prompt.from" /><br>
								<html:text property="fromDate" styleId="fromDate" style="width: 100px;" readonly="true"/> <input type="button" name="dateBtn" id="dateBtn" value="..." style="width: 20px;">
							</td>
							<td width="150">
								<bean:message key="prompt.to" /><br>
								<html:text property="toDate" styleId="toDate" style="width: 100px;" readonly="true"/> <input type="button" name="dateBtn2" id="dateBtn2" value="..." style="width: 20px;">
							</td>	
							<td><bean:message key="prompt.resource" /><br>
							<html:select property="resourceType" size="1" styleClass="input" style="width: 200px;">
								<html:option value="">[<bean:message key="option.all" bundle="messages"/>]</html:option>
								<html:options collection="typeList" property="name" labelProperty="nameValue" filter="1"/>
							</html:select>
							</td>
							<td width="10"></td>
							<td><br><html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit></td>
							</tr>
						</table>	
					</td>
					<td background="${pageContext.request.contextPath}/images/search/tile_der.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" height="100%" alt="" border="0"></td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_inferior_iz.gif" width="7" height="7" alt="" border="0"></td>
					<td background="${pageContext.request.contextPath}/images/search/tile_inferior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_inferior_der.gif" width="7" height="7" alt="" border="0"></td>
				</tr>
			</table>
			<%--<table cellpadding="0" cellspacing="0" width="100%">
				<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="10" border="0" alt=""></td></tr>
				<tr>
					<td align="right">
						<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td><strong><bean:message key="label.orderBy" bundle="messages" />:</strong></td>
							<TD><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
							<td>
								<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="submitOrder(this)">
									<html:option value="name"><bean:message key="label.name" bundle="messages" /></html:option>									
									<html:option value="isEnabled"><bean:message key="label.status" bundle="messages" /></html:option>
								</html:select>				
							</td>
							<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
							<td>
								<html:select property="orderAsc" size="1" styleClass="input" style="width: 70px;" onchange="submitOrder(this)">
									<html:option value="true"><bean:message key="label.asc" bundle="messages" /></html:option>
									<html:option value="false"><bean:message key="label.desc" bundle="messages" /></html:option>
								</html:select>
							</td>
						</tr>
						</table>
					</td>
				</tr>
			</table>--%>
			</html:form>
			<html:javascript formName="pageLogForm" dynamicJavascript="true" staticJavascript="false" />
		</td>
	</tr>
</table>
<!--fin buscador-->
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>


<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
	<tr>
		<th width="4%">#</th>
		<th width="32%">Recurso</th>
		<th width="32%">Descripci&oacute;n</th>
		<th width="32%"># de Visitas o Peticiones</th>
	</tr>
	<c:set var="bandera" scope="page">0</c:set>		
	<c:forEach var="item" items="${pageLogList}" varStatus="status">
		<c:url value="/system/admin/reportsPageLog.do" var="urlRead">
			<c:param name="action" value="listAll" />
			<c:param name="resourceDescription" value="${item.name}" />
			<c:param name="resourceType" value="${item.nameValue}" />
			<c:param name="fromDate" value="${fromDate}" />
			<c:param name="toDate" value="${toDate}" />
			<c:param name="search" value="true" />
			<c:param name="isReport" value="true" />
		</c:url>
		<c:choose> <c:when test="${bandera == 0}">
			<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
				<td>MAX</td>
				<td style="menu2TD"><font style="text-transform : uppercase"><bean:message key="${item.nameValue}"/></font></td>
				<td><font style="text-transform : uppercase"><a href="${urlRead}" class="item">${item.name}</a></font></td>
				<td>${item.level}</td>
			</tr>
			<c:set var="bandera" scope="page">1</c:set>		
		</c:when> <c:otherwise>
			<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
				<td>${status.count -1}</td>
				<td><bean:message key="${item.nameValue}"/></td>
				<td><a href="${urlRead}" class="item">${item.name}</a></td>
				<td>${item.level}</td>
			</tr>
		</c:otherwise> </c:choose> 
	</c:forEach>
</table>

<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	:"fromDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "dateBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	:"toDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "dateBtn2"
		}
	);
</script>