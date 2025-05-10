<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<c:set var="systemMenuLabel" ><bean:message key="icaro.flight.ticketRate" bundle="systemMenu" /></c:set>
			${systemMenuLabel}
		</td>
		<td align="right">
			<bean:message key="icaro" bundle="systemMenu" /> &gt;
			<bean:message key="icaro.flight" bundle="systemMenu" /> &gt;
			${systemMenuLabel}
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
			</ul>
			<div class="error"><html:errors/></div>			
		</td>
	</tr>
</table>

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">
			<c:if test="${!empty routeForm.code && routeForm.code != 0 && !empty routeForm.name}">
				<bean:message key="label.route" bundle="messages" />: ${routeForm.name}
			</c:if>
		</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.route">
			<tiles:put name="code" value="${routeForm.code}" />
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
						<!--inicio seccion general information-->
						<table cellpadding="0" cellspacing="0" align="center"  width="100%">
							<tr>
								<td>
								  <html:form action="/system/portal/route/ticketRate">	
									<html:hidden property="action" value="" />
									<html:hidden property="code" />
									<html:hidden property="routeCode" value="${routeForm.code}" />
									<html:hidden property="orderField" />
									<html:hidden property="orderAsc" />
									<html:hidden property="tab" value="${param.tab}" />
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
										<td valign="top">
										<!--inicio listado-->
											<table cellpadding="0" cellspacing="0" width="97%" border="0" align="center">
											<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="5" width="5"></td></tr>
											<tr>
												<td align="center">
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
												<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
												<!--inicio cabecera lista-->								
										        <tr>
													<th width="50">#</th>						
													<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
													<th ><bean:message key="label.paxType" bundle="messages" /></th>
													<th width="120"><bean:message key="label.route" bundle="messages" /></th>
													<th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
													<th width="80"><bean:message key="label.specialFare" bundle="messages" /></th>
													<th width="80"><bean:message key="label.fare" bundle="messages" /></th>
													<th width="80"><bean:message key="label.ivaRate" bundle="messages" /></th>
													<th width="80"><bean:message key="label.tax1" bundle="messages" /></th>
													<th width="80"><bean:message key="label.tax2" bundle="messages" /></th>
													<th width="80"><bean:message key="prompt.applyTax3"  /></th>
													<th width="80"><bean:message key="label.tax3" bundle="messages" /></th>
												</tr>
												<!--fin cabecera lista-->
												<!--inicio codigo repetitivo de registros lista-->			
												<c:forEach var="item" items="${ticketRateList}" varStatus="status">
													<c:url value="/system/portal/route/ticketRate.do" var="urlRead">
														<c:param name="action" value="read" />
														<c:param name="code" value="${item.code}" />
														<c:param name="tab" value="${param.tab}" />
													</c:url> 	
													<c:set value="row${status.count % 2}" var="sclass" />
									      			<tr class="<c:out value="${sclass}"/>">
														<td align="right">&nbsp;&nbsp;${status.count}&nbsp;&nbsp;</td>
														<td style="padding-left:5px"><html:checkbox property="codes" value="${item.code}" />	</td>
														<td><a href="${urlRead}" class="item">${item.paxType.name}</a></td>
														<td>${item.route.name}</td>
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
														<td style="padding-left:5px">
															<c:choose>
																<c:when test="${item.specialFare == true}">
																	<bean:message key="label.yes" bundle="messages" />
																</c:when>
																<c:otherwise>
																	<bean:message key="label.no" bundle="messages" />
																</c:otherwise>
															</c:choose>
														</td>		
														<td align="right"><fmt:formatNumber value="${item.fare}" pattern="${initParameters.CURRENCY_FORMAT}"/></td>
														<td align="right"><fmt:formatNumber value="${item.ivaRate}" pattern="${initParameters.CURRENCY_FORMAT}"/></td>
														<td align="right"><fmt:formatNumber value="${item.tax1}" pattern="${initParameters.CURRENCY_FORMAT}"/></td>
														<td align="right"><fmt:formatNumber value="${item.tax2}" pattern="${initParameters.CURRENCY_FORMAT}"/></td>
														<td align="left">
															<c:choose>
																<c:when test="${item.applyTax3 == true}">
																	<bean:message key="label.yes" bundle="messages" />
																</c:when>
																<c:otherwise>
																	<bean:message key="label.no" bundle="messages" />
																</c:otherwise>
															</c:choose>
														
														</td>
														<td align="right"><fmt:formatNumber value="${item.tax3}" pattern="${initParameters.CURRENCY_FORMAT}"/></td>
														
														
													</tr>	
												</c:forEach>	
												<c:if test="${empty ticketRateList }">
													<tr>
														<td height="20" align="center" colspan="11" class="message">No hay tarifas relacionadas a la ruta ${routeForm.name}</td>
													</tr>	
												</c:if>  

												
												<!--fin codigo repetitivo de registros lista-->
												</table>
												</td>
											</tr>
											</table>
											<!--fin listado-->
										</td>
										</tr>	
										<tr><td width="100%" height="2"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="5"></td></tr>								
									</table>
									</html:form>														
								</td>
							</tr>
						</table>
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

