<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/Ajax.js"></script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<c:set var="systemMenuLabel" ><bean:message key="icaro.flight.frequency.flight" bundle="systemMenu" /></c:set>
			${systemMenuLabel}
		</td>
		<td align="right">
			<bean:message key="icaro" bundle="systemMenu" /> &gt;
			<bean:message key="icaro.flight" bundle="systemMenu" /> &gt;
			<bean:message key="icaro.flight.frequency" bundle="systemMenu" /> &gt;
			${systemMenuLabel}
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
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:if test="${!empty routeForm.code && routeForm.code != 0 && !empty routeForm.name}">
				<bean:message key="label.route" bundle="messages" />: ${routeForm.name}
			</c:if>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

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
			<td align="center">		
				<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="subtitle">
							<c:if test="${!empty frequencyForm.code && frequencyForm.code != 0 && !empty frequencyForm.name}">
								<bean:message key="label.frequency" bundle="messages" />: ${frequencyForm.name}
							</c:if>
						</td>
					</tr>
				</table>
				<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
				<tr>
					<td>
						<tiles:insert definition="tabs.system.frequency">
							<tiles:put name="code" value="${frequencyForm.code}" />
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
								  	<html:form action="/system/portal/route/frequency/flight">	
									<html:hidden property="action" value="" />
									<html:hidden property="code" />
									<html:hidden property="frequencyCode" value="${frequencyForm.code}" />
									<html:hidden property="orderField" />
									<html:hidden property="orderAsc" />
									<html:hidden property="tab" value="${param.tab}" />
									<html:hidden property="subTab" value="${param.subTab}" />
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
													<th width="120"><bean:message key="label.number" bundle="messages" /></th>
													<th width="100"><bean:message key="label.departureTime" bundle="messages" /></th>
													<th width="100"><bean:message key="label.arrivalTime" bundle="messages" /></th>
													<th width="120"><bean:message key="label.frequency" bundle="messages" /></th>
													<th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
													<th ><bean:message key="label.notes" bundle="messages" /></th>
												</tr>
												<!--fin cabecera lista-->
												<!--inicio codigo repetitivo de registros lista-->			
												<c:forEach var="item" items="${flightList}" varStatus="status">
													<c:url value="/system/portal/route/frequency/flight.do" var="urlRead">
														<c:param name="action" value="read" />
														<c:param name="code" value="${item.code}" />
														<c:param name="tab" value="${param.tab}" />
														<c:param name="subTab" value="${param.subTab}" />
													</c:url> 	

													<c:set value="row${status.count % 2}" var="sclass" />
									      			<tr class="<c:out value="${sclass}"/>">
														<td align="right">&nbsp;&nbsp;${status.count}&nbsp;&nbsp;</td>
														<td style="padding-left:5px"><html:checkbox property="codes" value="${item.code}" />	</td>
														<td><a href="${urlRead}" class="item">${item.number}</a></td>
														<td>${item.departureTime}</td>
														<td>${item.arrivalTime}</td>
														<td>${item.frequency.name}</td>
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
														<td align="left">${item.notes}</td>
													</tr>	
												</c:forEach>	
												<c:if test="${empty flightList }">
													<tr>
														<td height="20" align="center" colspan="8" class="message">No hay vuelos registrados  para la frecuencia ${frequencyForm.name}</td>
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
					<tr>
						<td background="${pageContext.request.contextPath}/images/tables/file_bottom.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="7" border="0" alt=""></td>
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
			
