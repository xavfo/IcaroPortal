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
			<bean:message key="event.event" bundle="systemMenu" /> - <bean:message key="event.event.dates" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="event.event" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="event.event" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">
			${currentItem.name}
		</td>	
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.events">
			<tiles:put name="code" value="${eventForm.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>		
		<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
		<tr>
			<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
		</tr>
		<tr>
		<td>		
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
			            <table cellpadding="0" cellspacing="0" border="0" width="100%" >
						<tr>
						<td valign="top">
						<!--inicio buscador-->
						<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td>
									<html:form action="/system/event/eventDate">
									<html:hidden property="action" value="list" />
									<html:hidden property="listItems" value="true" />
									<html:hidden property="tab" value="${param.tab}" />
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
														<td width="350">
															<bean:message key="label.city" bundle="messages" /><br>
															<html:hidden property="cityCode" />
															<html:hidden property="countryCode"/>
															<html:text property="cityName"     styleClass="input" style="width: 120px;" maxlength="100" readonly="true"/> - <html:text property="countryName"     styleClass="input" style="width: 120px;" maxlength="100" readonly="true"/>
															<a href="javascript: openSearch('/popup/searchCity.do', 'contactFormRequestForm', new Array('cityCode','countryCode'),new Array('cityName','countryName'), 0)" class="button"><bean:message key="button.find" bundle="messages" /></a>
															<a href="javascript: emptyData()" class="button"><bean:message key="button.clean" bundle="messages" /></a>
														</td>
														<td width="140">
															<bean:message key="label.from" bundle="messages" /><br>
															<html:text property="fromStr" styleId="fromStr" style="width: 100px;" readonly="true"/> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;">
														</td>
														<td width="140">
															<bean:message key="label.to" bundle="messages" /><br>
															<html:text property="toStr" styleId="toStr" style="width: 100px;" readonly="true"/> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;">
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
															<html:option value="from"><bean:message key="label.from" bundle="messages" /></html:option>									
															<html:option value="to"><bean:message key="label.to" bundle="messages" /></html:option>
															<html:option value="city.name"><bean:message key="label.city" bundle="messages" /></html:option>
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
						<html:form action="/system/event/eventDate">
						<html:hidden property="action" value="" />
						<html:hidden property="code" />
						<html:hidden property="tab" value="${param.tab}" />
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td>
								</td>
								<td align="left">
									<tiles:insert definition="system.navBar">
										<tiles:put name="add" 		value="true" />
										<%--<tiles:put name="edit" 		value="true" />--%>
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
								<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
								<th width="272"><bean:message key="label.date" bundle="messages" /></th>
								<th><bean:message key="label.schedule" bundle="messages" /></th>
								<th width="200"><bean:message key="label.location" bundle="messages" /></th>
								<th width="220"><bean:message key="label.country" bundle="messages" /> - <bean:message key="label.state" bundle="messages" /> - <bean:message key="label.city" bundle="messages" /></th>	
							</tr>
							<!--Fin Header-->
							<c:forEach var="item" items="${eventList}" varStatus="status">
								<c:url value="/system/event/eventDate.do" var="urlRead">
									<c:param name="action" value="read" />
									<c:param name="code" value="${item.code}" />
									<c:param name="tab" value="${param.tab}" />
								</c:url> 	
							<!-- detalle registros -->
							<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
								<td style="padding-left:5px"><html:checkbox property="codes" value="${item.code}" />	</td>
								<td style="padding-left:5px"><%--<a href="${urlRead}" class="item">--%><fmt:formatDate value="${item.from.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/> - <fmt:formatDate value="${item.to.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/><%--</a>--%></td>
								<td style="padding-left:5px">${item.schedule}</td>
								<td style="padding-left:5px">${item.location}</td>
								<td style="padding-left:5px">${item.city.state.country.name} - ${item.city.state.name} - ${item.city.name}</td>
							</tr>
							<!-- FIN detalle registros -->
							</c:forEach>
						</table>
						</html:form>
						</td>			            
						</tr>						
						</table>                           										
					</td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
				</tr>
			</table>	
		</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td background="${pageContext.request.contextPath}/images/tables/file_bottom.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="7" border="0" alt=""></td>
</tr>
<tr>
	<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
</tr>
</table>

<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	: "fromStr",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "fromBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "toStr",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "toBtn"
		}
	);
	
	initPage();
</script>
