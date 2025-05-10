<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/Ajax.js"></script>
<script>
	var thePath = '<%=request.getContextPath()%>';
	
	function submitForm(theForm){
		theForm.action.value='list';
		theForm.submit();
	}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">		
			<bean:message key="administration.assign.users" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="administration.assign.users" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			<bean:message key="administration.assign.users" bundle="systemMenu" />>
			
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
								<html:form action="/system/store/ldap">
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
														<bean:message key="label.name" bundle="messages" /><br>
														<html:text property="username" styleClass="input" style="width: 200px;" maxlength="100" />
													</td>
													<td width="110">
														<bean:message key="label.registered" bundle="messages" /><br>
														<html:select property="registered" size="1" styleClass="input" style="width: 100px;">
															<html:option value="3"><bean:message key="option.all" bundle="messages" /></html:option>
															<html:option value="1"><bean:message key="label.yes" bundle="messages" /></html:option>
															<html:option value="0"><bean:message key="label.no" bundle="messages" /></html:option>
														</html:select>
													</td>
													
													<td width="260">
														<bean:message key="label.status" bundle="messages" /><br>
														<html:select property="status" size="1" styleClass="input" style="width: 100px;">
															<html:option value="3"><bean:message key="option.all" bundle="messages"/></html:option>																
															<html:option value="1"><bean:message key="prompt.enabled" /></html:option>
															<html:option value="0"><bean:message key="prompt.disabled" /></html:option>
															<html:option value="2"><bean:message key="label.no.apply" bundle="messages"/></html:option>
														</html:select>
													</td>	
													
													<td>									
														<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<span id="applications" class="normal" >
															<table>
																<tr>
																	<td width="110">
																		<bean:message key="label.city" bundle="messages" /><br>
																		<%--
																		<html:select property="cityCode" size="1" styleClass="input" style="width: 100px;" onchange="retrieveURL(thePath + '/system/store/ldap.do?ask=COMMAND_NAME_1&action=list',this.form);">
																	--%>																		
																		<html:select property="cityCode" size="1" styleClass="input" style="width: 100px;" onchange="submitForm(this.form);">
																			<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>																
																			<html:options collection="ldapCitiesList" property="distinguisedName" labelProperty="name"/>
																		</html:select>
																	</td>	
																	<td>
																		<bean:message key="label.department" bundle="messages" /><br>
																		<html:select property="departmentCode" size="1" styleClass="input"   style="width: 350px;">
																			<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>																
																			<html:options collection="ldapDepartmentsList" property="distinguisedName" labelProperty="name"/>
																		</html:select>
																	</td>	
																	
																</tr>
															</table>
														</span>	
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
														<html:option value="name"><bean:message key="label.name" bundle="messages" /></html:option>									
														<html:option value="status"><bean:message key="label.status" bundle="messages" /></html:option>									
														<html:option value="registered"><bean:message key="label.registered" bundle="messages" /></html:option>									
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
					<html:form action="/system/store/ldap" onsubmit="return preValidate(this);">
					<html:hidden property="action" value="" />
					<html:hidden property="username" />

						
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td align="left" style="padding-left:20px">
									<tiles:insert definition="system.navBar">
										<tiles:put name="assign" 		value="true" />
										<tiles:put name="formIndex" value="1" />
									</tiles:insert>
								</td>
							</tr>
						</table>
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
					
						<!-- List data -->
						<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
							<c:if test="${!empty ldapUsersList}">						
							<tr>
								<th width="50">#</th>
								<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'usernames', this.checked)"></th>
							  	<th width="382"><bean:message key="label.name" bundle="messages" />&nbsp;<bean:message key="prompt.username"/></th>
							  	<th width="162"><bean:message key="label.registered" bundle="messages" /></th>
							  	<th width="50"><bean:message key="label.status" bundle="messages" /></th>
							</tr>
							<!--Fin Header-->
							<c:forEach var="item" items="${ldapUsersList}" varStatus="status">	
								<c:url var="urlRead" value="/system/admin/sysUser.do">
									<c:param name="action" value="read"/>
									<c:param name="code" value="${item.code}"/>
								</c:url>
								<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
									<td>${status.count}</td>
									<td align="center"><html:checkbox property="usernames" value="${item.username}" /></td>
									<td>
										<c:if test="${!empty item.code}">
											<a href="${urlRead}">${item.username}</a>
										</c:if>
										<c:if test="${empty item.code}">
											${item.username}
										</c:if>
									</td>
									<td>
										<c:choose>
											<c:when test="${item.registered == 0}">
												<bean:message key="label.no" bundle="messages" />
											</c:when>
											<c:otherwise>
												<bean:message key="label.yes" bundle="messages" />
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${item.registered == 0}">
												<bean:message key="label.no.apply" bundle="messages" />
											</c:when>
											<c:otherwise>
												<c:if test="${item.status == 0}">
													<bean:message key="prompt.disabled" />
												</c:if>
												<c:if test="${item.status == 1}">
													<bean:message key="prompt.enabled" />
												</c:if>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
						
						
						</td>			            
						</tr>						
						</c:if>         
						<c:if test="${empty ldapUsersList}">
							<tr>
								<td height="20" class="message"><bean:message key="message.empty.records" /></td>
							</tr>	
						</c:if>   
						</html:form>  										
						</table>             
				</td>
			</tr>
			<tr>
				<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
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
	function preValidate (frm) { 
		var count = getSelectedCheckBoxValues(frm,'usernames');
		if (count == 0){
			alert('Debe seleccionar al menos un usuario para la asignaci\u00F3n');
			return false;
		}else {
     	  var msg = 'Est\u00E1 seguro que desea asignar los usuarios seleccionados?';
		  if( confirm (msg)){
			  return true;
		  } else { 
		  	return false; 
		  }
		}
	}
</script>
