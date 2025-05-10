<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="400">			
			<c:choose>
				<c:when test="${param.docTypeCode eq initParameters.DOCUMENT_TYPE_DOCUMENTS}">
					<bean:message key="document.document" bundle="systemMenu" />
					<c:set var="systemMenuLabel" ><bean:message key="document.document" bundle="systemMenu" /></c:set>
				</c:when>
				<c:otherwise>
					<bean:message key="information.selection.documents" bundle="systemMenu" />
					<c:set var="systemMenuLabel" ><bean:message key="information.selection.documents" bundle="systemMenu" /></c:set>
				</c:otherwise>
			</c:choose>
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="news.news" bundle="systemMenu" /> >
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
			<tiles:insert definition="tabs.system.news">
				<tiles:put name="code" value="${newsForm.code}" />
				<tiles:put name="tab" value="2" />
			</tiles:insert>
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/news/assign/document">
			<html:hidden property="action" value="list" />
			<html:hidden property="listItems" value="true" />
			<html:hidden property="docTypeCode" value="${param.docTypeCode}"  />
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
									<html:text property="title" styleClass="input" style="width: 230px;" maxlength="100" />
								</td>
								<td width="260">
									<bean:message key="label.category" bundle="messages" /><br>
									<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;">
										<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
										<html:options collection="categoryList" property="code" labelProperty="name"/>
									</html:select>
								</td>	
								<td width="110">
									<bean:message key="label.status" bundle="messages" /><br>
									<html:select property="enabledOption" size="1" styleClass="input" style="width: 100px;">
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
									<html:option value="title"><bean:message key="label.name" bundle="messages" /></html:option>									
									<html:option value="isEnabled"><bean:message key="label.status" bundle="messages" /></html:option>
									<html:option value="category.name"><bean:message key="label.category" bundle="messages" /></html:option>									
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


<html:form action="/system/news/assign/document/save" >
<html:hidden property="action" value="" />
<html:hidden property="code" />
<html:hidden property="docTypeCode" value="${param.docTypeCode}"  />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>	
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				  	<tiles:insert definition="system.navBar">
						<tiles:put name="save" 		value="true" />
						<tiles:put name="apply"		value="true" />
						<tiles:put name="reset" 	value="true" />
						<tiles:put name="cancel" 	value="true" />
						<tiles:put name="formIndex" value="1" />
					</tiles:insert>
			</tiles:insert>
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
	  <th width="162"><bean:message key="label.category" bundle="messages" /></th>
	  <th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
	  <th width="50"><bean:message key="label.view" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${assignDocumentList}" varStatus="status">	
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td>${item.title}</td>
			<td>${item.category.name}</td>
			<td>
				<c:choose>
					<c:when test="${item.isEnabled == true}">
						<bean:message key="label.yes" bundle="messages" />
					</c:when>
					<c:otherwise>
						<bean:message key="label.no" bundle="messages" />
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:if test="${!empty item.path}">
					<a href="${pageContext.request.contextPath}${item.path}" target="_blank"><img src="${pageContext.request.contextPath}${item.pathIcon}" border="0" alt="${item.path}"></a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</html:form>
--%>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="400">			
			<c:choose>
				<c:when test="${param.docTypeCode eq initParameters.DOCUMENT_TYPE_DOCUMENTS}">
					<bean:message key="document.document" bundle="systemMenu" />
					<c:set var="systemMenuLabel" ><bean:message key="document.document" bundle="systemMenu" /></c:set>
				</c:when>
				<c:otherwise>
					<bean:message key="information.selection.documents" bundle="systemMenu" />
					<c:set var="systemMenuLabel" ><bean:message key="information.selection.documents" bundle="systemMenu" /></c:set>
				</c:otherwise>
			</c:choose>
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="news.news" bundle="systemMenu" /> >
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
			<ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.news">
			<tiles:put name="code" value="${newsForm.code}" />
			<tiles:put name="tab" value="2" />
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
									<html:form action="/system/news/assign/document">
									<html:hidden property="action" value="list" />
									<html:hidden property="listItems" value="true" />
									<html:hidden property="docTypeCode" value="${param.docTypeCode}"  />
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
															<bean:message key="label.title" bundle="messages" /><br>
															<html:text property="title" styleClass="input" style="width: 230px;" maxlength="100" />
														</td>
														<td width="140">
															<bean:message key="label.category" bundle="messages" /><br>
															<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;">
																<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
																<html:options collection="categoryList" property="code" labelProperty="name"/>
															</html:select>
														</td>
														<td width="140">
															<bean:message key="label.status" bundle="messages" /><br>
															<html:select property="enabledOption" size="1" styleClass="input" style="width: 100px;">
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
															<html:option value="title"><bean:message key="label.name" bundle="messages" /></html:option>									
															<html:option value="isEnabled"><bean:message key="label.status" bundle="messages" /></html:option>
															<html:option value="category.name"><bean:message key="label.category" bundle="messages" /></html:option>									
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
						<html:form action="/system/news/assign/document/save" >
						<html:hidden property="action" value="" />
						<html:hidden property="code" />
						<html:hidden property="docTypeCode" value="${param.docTypeCode}"  />
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td>
								</td>
								<td align="left">
								  	<tiles:insert definition="system.navBar">
										<tiles:put name="save" 		value="true" />
										<tiles:put name="apply"		value="true" />
										<tiles:put name="reset" 	value="true" />
										<tiles:put name="cancel" 	value="true" />
										<tiles:put name="formIndex" value="1" />
									</tiles:insert>
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
							  <th width="162"><bean:message key="label.category" bundle="messages" /></th>
							  <th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
							  <th width="50"><bean:message key="label.view" bundle="messages" /></th>
							</tr>
							<!--Fin Header-->
								<c:forEach var="item" items="${assignDocumentList}" varStatus="status">	
									<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
										<td>${status.count}</td>
										<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
										<td>${item.title}</td>
										<td>${item.category.name}</td>
										<td>
											<c:choose>
												<c:when test="${item.isEnabled == true}">
													<bean:message key="label.yes" bundle="messages" />
												</c:when>
												<c:otherwise>
													<bean:message key="label.no" bundle="messages" />
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:if test="${!empty item.path}">
												<a href="${pageContext.request.contextPath}${item.path}" target="_blank"><img src="${pageContext.request.contextPath}${item.pathIcon}" border="0" alt="${item.path}"></a>
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
