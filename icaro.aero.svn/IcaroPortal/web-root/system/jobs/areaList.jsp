<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">			
			<bean:message key="information.jobs.area" bundle="systemMenu" />
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="information.jobs" bundle="systemMenu" /> >
			<bean:message key="information.jobs.area" bundle="systemMenu" />				
		</td>
	</tr>
</table>

<hr width="100%" size="1" color="#999999" noshade>
<html:form action="/system/jobs/area">
<html:hidden property="orderField" value="name" />
<html:hidden property="action" value="" />
<html:hidden property="code" />
<html:hidden property="tab" value="1" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
				<tiles:put name="formIndex" value="0" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>


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

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
	<td></td>
	</tr>
	<tr>
	<td valign="top">
	<!--inicio listado-->
		<table cellpadding="0" cellspacing="0" width="97%" border="0" align="center">
		<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="5" width="5"></td></tr>
		<tr>
			<td align="center">			
			<!-- Inicia tabla -->
			<table class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
			<!--Header-->
			<tr>
				<th width="30">#</th>
				<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
				<th><bean:message key="label.name" bundle="messages" /></th>
				<th><bean:message key="label.enabled" bundle="messages" /></th>								
			</tr>
			<!--Fin Header-->
			<c:forEach var="item" items="${areaList}" varStatus="status">
				<c:url value="/system/jobs/area.do" var="urlRead">
					<c:param name="action" value="read" />
					<c:param name="code" value="${item.code}" />					
					<c:param name="tab" value="1" />
				</c:url>                              				 
			<!-- detalle registros -->
			<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
				<td align="center" style="padding-left:5px">${status.count}</td>
				<td style="padding-left:5px"><html:checkbox property="codes" value="${item.code}" />	</td>
				<td style="padding-left:5px"><a href="${urlRead}" class="item">${item.name}</a></td>
				<td style="padding-left:5px">
					<c:choose>
						<c:when test="${item.isEnabled == true}">
							<bean:message key="label.yes" bundle="messages" />
						</c:when>
						<c:otherwise>
							<bean:message key="label.no" bundle="messages" />
						</c:otherwise>
					</c:choose>
				</td>									
			</tr>
			<!-- FIN detalle registros -->
			</c:forEach>
			</table>
			<!-- Fin tabla -->
			</td>
		</tr>
		</table>
		<!--fin listado-->
	</td>
	</tr>	
	<tr>
	<td><!--<a href="javascript: doAction(document.areaForm, 'action', 'create')"><img alt="<bean:message key="button.add" bundle="messages" />" src="${pageContext.request.contextPath}/images/icons/add_new.gif" width="16" height="16" border="0" alt=""><bean:message key="button.add" bundle="messages" /></a>--></td>
	</tr>
	<tr><td width="100%" height="2"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="5"></td></tr>							
</table>
</html:form>