<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>


<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="link.bulletin.subscriber" bundle="messages" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="link.bulletin.subscriber" bundle="messages" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			<ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.bulletin">
			<tiles:put name="code" value="${subscriberListForm.code}" />
			<tiles:put name="tab" value="3" />
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
								    <html:form action="/system/bulletin/subscriber" onsubmit="return validate(this);">
									<html:hidden property="action" value="list" />
									<html:hidden property="code" value="${subscriberListForm.code}" />
									<html:hidden property="itemCode" value="" />
									<html:hidden property="search" value="true" />
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_iz.gif" width="7" height="7" alt="" border="0"></td>
											<td background="${pageContext.request.contextPath}/images/search/tile_superior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
											<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_der.gif" width="7" height="7" alt="" border="0"></td>
										</tr>
										<tr>
											<td background="${pageContext.request.contextPath}/images/search/tile_iz.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" alt="" border="0"></td>
											<td width="100%" bgcolor="#EBEEF3">
												<table border="0" cellpadding="0" cellspacing="0">
													<tr><td class="subtitle">&nbsp;<img src="${pageContext.request.contextPath}/images/icons/find.gif" width="16" height="16" border="0" alt=""> <bean:message key="label.search" bundle="messages" /></td></tr>
												</table>
												<table border="0" cellpadding="0" cellspacing="2">
													<tr>
														<td width="130">
															<bean:message key="label.name" bundle="messages" /><br>
															<html:text property="name" styleClass="input" style="width: 200px;" maxlength="100" />
														</td>
														<td width="130">
															<bean:message key="label.email" bundle="messages" /><br>
															<html:text property="email" styleClass="input" style="width: 200px;" maxlength="100" />
														</td>
														<td width="130">
															<bean:message key="label.city" bundle="messages" /><br>
															<html:text property="city" styleClass="input" style="width: 200px;" maxlength="100" />
														</td>
													</tr>
													<tr>
														<td colspan="3">
															<table  border="0" cellpadding="0" cellspacing="2">
																<tr>
																	<td width="110">
																		<bean:message key="label.from" bundle="messages" /><br>
																		<html:text property="fromDate" styleClass="input" style="width: 80px;" maxlength="100" readonly="true"/>
																		<input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;" >
																	</td>
																	<td width="110">
																		<bean:message key="label.to" bundle="messages" /><br>
																		<html:text property="toDate" styleClass="input" style="width: 80px;" maxlength="100" readonly="true"/>
																		<input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;" >
																	</td>
																	<td>
																		<bean:message key="label.country" bundle="messages" /><br>
																		<html:select property="countryCode" size="1" styleClass="input" style="width: 250px;">
																			<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
																			<html:options collection="countryList" property="code" labelProperty="name"/>
																		</html:select>
																	</td>
																	<td valign="bottom">									
																		<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
											<td background="${pageContext.request.contextPath}/images/search/tile_der.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" alt="" border="0"></td>
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
													<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
													<td>
														<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="submitOrder(this)">
															<html:option value="lastName"><bean:message key="label.lastName" bundle="messages" /></html:option>									
															<html:option value="country"><bean:message key="label.country" bundle="messages" /></html:option>									
															<html:option value="city"><bean:message key="label.city" bundle="messages" /></html:option>									
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
						<html:form action="/system/bulletin/subscriber">
						<html:hidden property="action" value="" />
						<html:hidden property="code" />
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
							<tr>
								<th width="30">#</th>
								<%--
								<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
								--%>
							  	<th><bean:message key="label.name" bundle="messages" /></th>
							  	<th width="50"><bean:message key="prompt.occupation" /></th>
							  	<th width="80"><bean:message key="prompt.address" /></th>
							  	<th width="100"><bean:message key="label.country" bundle="messages" />/<bean:message key="label.state" bundle="messages" />/<bean:message key="label.city" bundle="messages" /></th>
							  	<th width="100"><bean:message key="label.phone" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.email" bundle="messages" /></th>
								<th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
								<th width="80"></th>
							</tr>
							<!--Fin Header-->
							<c:forEach var="item" items="${subscriberList}" varStatus="status">	
								<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
									<td>${status.count}</td>
									<%--
									<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
									--%>
									<td>${item.subscriber.firstName}&nbsp;${item.subscriber.lastName}</td>
									<td>${item.subscriber.occupation}</td>
									<td>${item.subscriber.address}</td>
									<td>${item.subscriber.country.name} /  <c:if test="${!empty item.subscriber.state}">${item.subscriber.state} / </c:if>${item.subscriber.city}</td>
									<td>${item.subscriber.phoneCountryCode} / ${item.subscriber.phoneCityCode} / ${item.subscriber.phone}</td>
									<td>${item.subscriber.email}</td>
									<td align="center">
										<c:choose>
											<c:when test="${item.subscriber.enabled}">
												<bean:message key="label.yes" bundle="messages" />
											</c:when>
											<c:otherwise>
												<bean:message key="label.no" bundle="messages" />
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a href="javascript:sendUpdate(${item.subscriber.code}, '${item.subscriber.firstName} ${item.subscriber.lastName}');">
										<c:choose>
											<c:when test="${item.subscriber.enabled}">
												deshabilitar
											</c:when>
											<c:otherwise>
												habilitar
											</c:otherwise>
										</c:choose>
										</a>
									</td>
								</tr>
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
			align			: "B2",
			button      	: "toBtn"
		}
	);
	function sendUpdate(code, name){
		if ( !confirm ('Esta seguro que desea deshabilitar a "'+name+'"') )
			return
		var frm = document.forms[0];
		frm.itemCode.value = code;
		frm.action.value = "changeStatus";
		frm.submit();

	}
	
	function validate(frm) {
		if (trim(frm.name.value).length == 0 &&  trim(frm.email.value).length == 0 ) {
			alert('<bean:message key="errors.required" arg0="\"Nombre o E-mail\"" />');
			frm.name.focus();
			return false
		}
		return true
	}
</script>