<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script language="JavaScript1.2">
<!--		
	function emptyData() {
		document.forms[0].cityCode.value = "";
		document.forms[0].countryCode.value = "";
		document.forms[0].cityName.value = "";
		document.forms[0].countryName.value = "";
	}
//-->
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">			
			<bean:message key="event.event" bundle="systemMenu" />
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="event.event" bundle="systemMenu" />
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
			<html:form action="/system/event/event">
			<html:hidden property="action" value="list" />
			<html:hidden property="listItems" value="true" />
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
									<bean:message key="label.title" bundle="messages" /><br>
									<html:text property="title" styleClass="input" style="width: 140px;" maxlength="100" />
								</td>	
								<td width="350">
									<bean:message key="label.city" bundle="messages" /><br>
									<html:hidden property="cityCode" />
									<html:hidden property="countryCode"/>
									<html:text property="cityName"     styleClass="input" style="width: 120px;" maxlength="100" readonly="true"/> - <html:text property="countryName"     styleClass="input" style="width: 120px;" maxlength="100" readonly="true"/>
									<a href="javascript: openSearch('/popup/searchCity.do', 'contactFormRequestForm', new Array('cityCode','countryCode'),new Array('cityName','countryName'), 0)" class="button"><bean:message key="button.find" bundle="messages" /></a>
									<a href="javascript: emptyData()" class="button"><bean:message key="button.clean" bundle="messages" /></a>
								</td>
								<td width="210">
									<bean:message key="label.category" bundle="messages" /><br>
									<html:select property="categoryCode" size="1" styleClass="input" style="width: 200px;">
										<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
										<html:options collection="eventCategoryList" property="code" labelProperty="name"/>
									</html:select>
								</td>	
								<td width="120">
									<bean:message key="label.type" bundle="messages" /><br>
									<html:select property="seminaryOption" size="1" styleClass="input" style="width: 110px;">
										<html:option value="-1"><bean:message key="option.all" bundle="messages" /></html:option>
										<html:option value="1"><bean:message key="label.seminary" bundle="messages" /></html:option>
										<html:option value="0"><bean:message key="label.event" bundle="messages" /></html:option>
									</html:select>
								</td>	
							</tr>
						</table>	
						<table border="0" cellpadding="0" cellspacing="2" width="100%">
							<tr>
								<td width="140">
									<bean:message key="label.from" bundle="messages" /><br>
									<html:text property="fromDate" styleId="fromDate" style="width: 100px;" readonly="true"/> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;">
								</td>
								<td width="140">
									<bean:message key="label.to" bundle="messages" /><br>
									<html:text property="toDate" styleId="toDate" style="width: 100px;" readonly="true"/> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;">
								</td>
								<td width="120">
									<bean:message key="label.status" bundle="messages" /><br>
									<html:select property="enabledOption" size="1" styleClass="input" style="width: 110px;">
										<html:option value="-1"><bean:message key="option.all" bundle="messages" /></html:option>
										<html:option value="1"><bean:message key="label.enabled" bundle="messages" /></html:option>
										<html:option value="0"><bean:message key="label.notEnabled" bundle="messages" /></html:option>
									</html:select>
								</td>						
								<td>									
									<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
								</td>
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
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="10" border="0" alt=""></td></tr>
				<tr>
					<td align="right">
						<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td><strong><bean:message key="label.orderBy" bundle="messages" />:</strong></td>
							<TD><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
							<td>
								<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="submitOrder(this)">
									<html:option value="title"><bean:message key="label.title" bundle="messages" /></html:option>									
									<html:option value="category.name"><bean:message key="label.category" bundle="messages" /></html:option>
									<html:option value="type.name"><bean:message key="label.type" bundle="messages" /></html:option>
									<html:option value="enabled"><bean:message key="label.status" bundle="messages" /></html:option>
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
			</table>
			</html:form>
		</td>
	</tr>
</table>
<!--fin buscador-->
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>


<html:form action="/system/event/event">
<html:hidden property="action" value="" />
<html:hidden property="code" />
<html:hidden property="tab" value ="1" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
				<tiles:put name="formIndex" value="1" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>


<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.title" bundle="messages" /></th>
		<th width="65"><bean:message key="label.type" bundle="messages" /></th>
		<th width="180"><bean:message key="label.category" bundle="messages" /></th>
		<th width="260"><bean:message key="label.date" bundle="messages" /></th>
		<th width="220"><bean:message key="label.country" bundle="messages" /> - <bean:message key="label.state" bundle="messages" /> - <bean:message key="label.city" bundle="messages" /></th>
		<th width="50"><bean:message key="label.enabled" bundle="messages" /></th>
	</tr>
	<!--Fin Header-->
	<c:forEach var="item" items="${eventList}" varStatus="status">
		<c:url value="/system/event/event.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
			<c:param name="tab" value="1" />
		</c:url> 	
	<!-- detalle registros -->
	<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
		<td align="center" style="padding-left:5px">${status.count}</td>
		<td style="padding-left:5px"><html:checkbox property="codes" value="${item.code}" />	</td>
		<td style="padding-left:5px"><a href="${urlRead}" class="item">${item.title}</a></td>
		<td style="padding-left:5px">
			<c:choose>
				<c:when test="${item.seminary == true}">
					<bean:message key="label.seminary" bundle="messages" />
				</c:when>
				<c:otherwise>
					<bean:message key="label.event" bundle="messages" />
				</c:otherwise>
			</c:choose>
		</td>	
		<td style="padding-left:5px">${item.category.name}</td>		
		<td style="padding-left:5px">
			<c:forEach var="eventDate" items="${item.dates}">
				<fmt:formatDate value="${eventDate.from.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/> - <fmt:formatDate value="${eventDate.to.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/><br>
			</c:forEach>
		</td>	
		<td style="padding-left:5px">
			<c:forEach var="eventDate" items="${item.dates}">
				${eventDate.city.state.country.name} - ${eventDate.city.state.name} - ${eventDate.city.name}<br>
			</c:forEach>
		</td>	
		<td style="padding-left:5px">
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
	<!-- FIN detalle registros -->
	</c:forEach>
</table>
</html:form>

<c:remove var="eventForm" />

<script language="JavaScript1.2">
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
	
	initPage();
</script>