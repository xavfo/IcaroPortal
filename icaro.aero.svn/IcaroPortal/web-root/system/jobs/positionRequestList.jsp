<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/Ajax.js"></script>
<script>
	var thePath = '<%=request.getContextPath()%>';
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="information.application.list" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >			
			<bean:message key="information.jobs" bundle="systemMenu" /> >	
			<bean:message key="information.jobs.area" bundle="systemMenu" />  >
			<bean:message key="information.application.list" bundle="systemMenu" />
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
		<tiles:insert definition="tabs.positions">
			<tiles:put name="code" value="${positionRequestForm.areaCode}" />
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
									<html:form action="/system/jobs/position/request">
									<html:hidden property="action" value="list" />
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
														<td width="130">
															<bean:message key="label.position" bundle="messages" /><br>
															<html:select property="positionCode" size="1" styleClass="input" style="width: 120px;" >
																<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:options collection="positionList" property="code" labelProperty="name"/>
															</html:select>
														</td>
														<td width="260">
															<span id="applications" class="normal" >													
															<table>
																	<td width="130">
																		<bean:message key="label.country" bundle="messages" /><br>
																		<html:select property="countryCode" size="1" styleClass="input" style="width: 120px;" onchange="retrieveURL(thePath + '/system/jobs/position/request.do?ask=COMMAND_NAME_1&action=listCities',this.form);">
																			<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																			<html:options collection="countryList" property="code" labelProperty="name"/>
																		</html:select>
																	</td>
																	
																	<td width="130">
																		<bean:message key="label.city" bundle="messages" /><br>
																		<html:select property="cityCode" size="1" styleClass="input" style="width: 120px;" >
																			<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																			<html:options collection="cityList" property="code" labelProperty="name"/>
																		</html:select>
																	</td>
															</table>
															</span>
														</td>
														<td width="110">
															<bean:message key="label.from" bundle="messages" /><br>
															<html:text property="fromDate" styleClass="input" style="width: 80px;" maxlength="100" readonly="true"/>
															<input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;" >
														</td>
														<td width="110">
															<bean:message key="label.to" bundle="messages" /><br>
															<html:text property="toDate" styleClass="input" style="width: 80px;" maxlength="100" readonly="true"/>
															<input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;"  >
														</td>
														<td>
															<bean:message key="label.study.branch" bundle="messages" /><br>
															<html:select property="studyBranchCode" size="1" styleClass="input" style="width: 120px;" >
																<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:options collection="studyBranchList" property="code" labelProperty="name"/>
															</html:select>
														</td>
														<td>
															<bean:message key="label.salary.aspiration" bundle="messages" /><br>
															<html:select property="salaryAspirationCode" size="1" styleClass="input" style="width: 120px;" >
																<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:options collection="salaryAspirationList" property="code" labelProperty="name"/>
															</html:select>
														</td>
													</tr>
													<tr>
														<td>
															<bean:message key="label.position" bundle="messages" /><br>
															<html:select property="positionCode" size="1" styleClass="input" style="width: 120px;" >
																<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:options collection="positionList" property="code" labelProperty="name"/>
															</html:select>
														</td>
														<td>
															<table>
																<tr>
																	<td>
																		<bean:message key="label.maritalStatus" bundle="messages" /><br>
																		<html:select property="maritalStatusCode" size="1" styleClass="input" style="width: 120px;" >
																			<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																			<html:options collection="maritalStatusList" property="code" labelProperty="name"/>
																		</html:select>
																	</td>
																	<td>
																		<bean:message key="label.experience" bundle="messages" /><br>
																		<html:select property="experienceOption" size="1" styleClass="input" style="width: 100px;">
																			<html:option value="-1"><bean:message key="option.all" bundle="messages" /></html:option>
																			<html:option value="1"><bean:message key="label.yes" bundle="messages" /></html:option>
																			<html:option value="0"><bean:message key="label.no" bundle="messages" /></html:option>
																		</html:select>
																	</td>
																</tr>
															</table>
														</td>
														<td>
															<bean:message key="label.working" bundle="messages" /><br>
															<html:select property="workingOption" size="1" styleClass="input" style="width: 100px;">
																<html:option value="-1"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:option value="1"><bean:message key="label.yes" bundle="messages" /></html:option>
																<html:option value="0"><bean:message key="label.no" bundle="messages" /></html:option>
															</html:select>
														</td>
														<td>
															<bean:message key="label.instruction.level" bundle="messages" /><br>
															<html:select property="instructionLevelCode" size="1" styleClass="input" style="width: 120px;" onchange="retrieveURL(thePath + '/system/jobs/position/request.do?ask=COMMAND_NAME_1&action=listCities','positionRequestForm');">
																<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:options collection="instructionLevelList" property="code" labelProperty="name"/>
															</html:select>
														</td>
														<td>
															<bean:message key="label.study.branch" bundle="messages" /><br>
															<html:select property="studyBranchCode" size="1" styleClass="input" style="width: 120px;" >
																<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																<html:options collection="studyBranchList" property="code" labelProperty="name"/>
															</html:select>
														</td>	
														<td align="center">									
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
													<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
													<td>
														<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="this.document.forms[0].submit()">
															<html:option value="position"><bean:message key="label.position" bundle="messages" /></html:option>									
															<html:option value="dateCreated"><bean:message key="label.date" bundle="messages" /></html:option>									
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
						<html:form action="/system/jobs/position/request">
						<html:hidden property="action" value="" />
						<html:hidden property="code" />
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<!-- List data -->
						<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
							<tr>
								<th width="30">#</th>
							    <th><bean:message key="label.name" bundle="messages" /></th>
							    <th width="80"><bean:message key="label.position" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.country" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.city" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.phone" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.address" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.maritalStatus" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.instruction.level" bundle="messages"/></th>
							  	<th width="40">Exp.</th>
							  	<th width="40">Trab.</th>
							</tr>
							<!--Fin Header-->
							<c:forEach var="item" items="${positionRequestList}" varStatus="status">	
								<c:url value="/system/information/country.do" var="urlRead">
									<c:param name="action" value="read" />
									<c:param name="code" value="${item.code}" />
								</c:url> 	
								<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
									<td>${status.count}</td>
									<td>${item.requester.lastName}&nbsp;${item.requester.firstName}</td>
									<td>${item.position.name}</td>
									<td>${item.requester.city.country.name}</td>
									<td>${item.requester.city.name}</td>
									<td>${item.requester.phoneNumber}</td>
									<td>${item.requester.address}</td>
									<td>${item.requester.maritalStatus.name}</td>
									<td>${item.requester.instructionLevel.name}</td>
									<td>
										<c:if test="${!empty item.experience}">
											<c:if test="${item.experience}">
												<bean:message key="label.yes" bundle="messages" />
											</c:if>
											<c:if test="${!item.experience}">
												<bean:message key="label.no" bundle="messages" />
											</c:if>
										</c:if>
										<c:if test="${empty item.experience}">
											&nbsp;
										</c:if>
									</td>
									<td>
										<c:if test="${!empty item.requester.isWorking}">
											<c:if test="${item.requester.isWorking}">
												<bean:message key="label.yes" bundle="messages" />
											</c:if>
											<c:if test="${!item.requester.isWorking}">
												<bean:message key="label.no" bundle="messages" />
											</c:if>
										</c:if>
										<c:if test="${empty item.requester.isWorking}">
											&nbsp;
										</c:if>
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
</script>
