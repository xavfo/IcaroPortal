<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/Ajax.js"></script>
<script>
	var thePath = '<%=request.getContextPath()%>';
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="65%">
			${currentContent.name} -
			<bean:message key="portal.sri.species.select" bundle="systemMenu" />
		</td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu"/> >
			<bean:message key="portal.category"  bundle="systemMenu"/> >
			<bean:message key="portal.sri.documents.options" bundle="systemMenu" /> >
			<bean:message key="prompt.form.request.others" /> 
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
		<tiles:insert definition="tabs.system.content.${currentContent.level}">
			<tiles:put name="code" value="${currentContent.code}" />
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
									<html:form action="/system/portal/content/species">
									<html:hidden property="action" value="listUnbound" />
									<html:hidden property="itemCode" value="${currentContent.code}"  />
									
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
															<bean:message key="label.title" bundle="messages" /><br>
															<html:text property="name" styleClass="input" style="width: 200px;" maxlength="100" />
														</td>
														<td>
															<span id="applications" class="normal" >													
															<table>
																<tr>
																	<td width="130">
																		<bean:message key="label.species" bundle="messages" />: <br>
																		<html:select property="typeCode" size="1" styleClass="input" style="width: 200px;" onchange="retrieveURL(thePath + '/system/portal/content/species.do?ask=COMMAND_NAME_1&action=listThemes&itemCode=${currentContent.code}', this.form);">
																			<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																			<c:forEach var="item" items="${speciesTypesList}">
																				<html:option value="${item.code}">${item.name}</html:option>
																			</c:forEach>
																		</html:select>
																	</td>	
																	<td width="130">
																		<bean:message key="label.themes" bundle="messages" />: <br>
																		<html:select property="themeCode" size="1" styleClass="input" style="width: 200px;" >
																			<html:option value="0"><bean:message key="option.all" bundle="messages" /></html:option>
																			<c:forEach var="item" items="${themesList}">
																				<html:option value="${item.code}">${item.name}</html:option>
																			</c:forEach>
																		</html:select>
																	</td>	
																	
																</tr>	
															</table>
															</span>
														</td>
																															
														<td width="110">
															<bean:message key="label.status" bundle="messages" /><br>
															<html:select property="enabledInt" size="1" styleClass="input" style="width: 100px;">
																<html:option value="2"><bean:message key="option.all" bundle="messages" /></html:option>
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
													<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
													<td>
														<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="submitOrder(this)">
															<html:option value="name"><bean:message key="label.title" bundle="messages" /></html:option>									
															<html:option value="isEnabled"><bean:message key="label.status" bundle="messages" /></html:option>
															<html:option value="speciesTypeLang.name"><bean:message key="label.type" bundle="messages" /></html:option>									
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
						<html:form action="/system/portal/content/species">
						<html:hidden property="action" value="" />
						<html:hidden property="code" />
						<html:hidden property="itemCode" value="${currentContent.code}"/>
						<table border="0" cellpadding="0" cellspacing="0" width="98%" >
							<tr>
								<td>
								</td>
								<td align="left">
									<tiles:insert definition="system.navBar">
										<tiles:put name="apply" 	value="true" />
										<tiles:put name="assign" 	value="true" />
										<tiles:put name="reset" 	value="true" />
										<tiles:put name="cancel" 	value="true" />
										<tiles:put name="formIndex" value="1" />
									</tiles:insert>
								</td>
								<td width="700">
									<c:url var="urlList" value="/system/portal/content/species.do">
										<c:param name="action" value="listBound"/>
										<c:param name="itemCode" value="${currentContent.code}"/>
									</c:url>
									<a href="${urlList}"><bean:message key="link.return.list" bundle="messages"/></a>
								</td>
							</tr>
						</table>
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<!-- List data -->
						<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
							<tr>
								<th width="50">#</th>
								<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
							  	<th width="382"><bean:message key="label.name" bundle="messages" /></th>
					  			<th width="200" align="center"><bean:message key="label.themes" bundle="messages" /></th>				
							  	<th width="80"><bean:message key="label.status" bundle="messages" /></th>
							  	<th width="80"><bean:message key="label.type" bundle="messages" /></th>
							</tr>
							<!--Fin Header-->
							<c:forEach var="item" items="${unboundSpeciesList}" varStatus="status">	
								<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
									<td align="center">${status.count}</td>
									<td align="center"><html:checkbox property="codes" value="${item.speciesLang.code}" /></td>
									<td>${item.speciesLang.name}</td>
									<td align="center">
										<table border="0" width="98%">
											<c:forEach var="theme" items="${item.themeLangs}">
												<tr>
													<td>${theme.name}</td>
												</tr>	
											</c:forEach>
										</table>
									</td>			
									<td>
										<c:choose>
											<c:when test="${item.speciesLang.species.isEnabled == true}">
												<bean:message key="prompt.enabled"  />
											</c:when>
											<c:otherwise>
												<bean:message key="prompt.disabled"  />
											</c:otherwise>
										</c:choose>
									</td>
									<td>${item.speciesLang.speciesTypeLang.name}</td>
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
