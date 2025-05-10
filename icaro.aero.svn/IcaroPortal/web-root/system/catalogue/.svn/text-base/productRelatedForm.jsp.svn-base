<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<script language="javascript">
function clean(frm, objTargetName, objLabelName, txtDesc){
  if (typeof txtDesc=='undefined')
    txtDesc="";
  frm.elements[objTargetName].value = "";
  frm.elements[objLabelName].value = txtDesc;
}
</script>

<script language="javascript">
function send(frm, action){
	if ( typeof(action) == 'undefined' )
		action="saveAll";
	frm.action.value=action;
	var button =  document.getElementById('inputSubmit');
	button.click();
}
function cancel(frm) {
	frm.action.value="save";
	var button =  document.getElementById('inputCancel');
	button.click();
}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="cart.product" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="cart.product" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" /> &gt;
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
		</td>
	</tr>
</table>

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">
			<c:if test="${!empty productForm.code && productForm.code != 0 && !empty productForm.name}">
				${productForm.name}
			</c:if>
		</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.product">
			<tiles:put name="code" value="${productForm.code}" />
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
						<!--inicio buscador-->
						<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td>
									<html:form action="/system/catalogue/productRelatedList">
									<html:hidden property="action" value="findRelated" />
									<html:hidden property="tab" value="${param.tab}" />
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
															<bean:message key="label.name" bundle="messages" /><br>
															<html:text property="productName" styleClass="input" style="width: 140px;" maxlength="100" />
														</td>		
                                                        <td width="230">
                                                          <bean:message key="label.brand" bundle="messages" /><br>
                                                          <html:select property="brandCode" size="1" styleClass="input" style="width: 200px;">
                                                            <html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
                                                            <html:options collection="brandList" property="code" labelProperty="name"/>
                                                          </html:select>
                                                        </td>
                                                        <td width="260">
                                                          <bean:message key="label.category" bundle="messages" /><br>
                                                          <html:hidden property="categoryCode" styleClass="input" style="width: 20px;" />
                                                          <html:text property="categoryName" styleClass="input" style="width: 160px;" readonly="true" />
                                                          <input type="button" value="..." onclick="openSearch('/popup/searchCategory.do', 'categoryForm', new Array('categoryCode'),new Array('categoryName'), 0)" />
                                                          <input type="button" value="Vaciar" onclick="clean(this.form, 'categoryCode', 'categoryName');" />
                                                        </td>
                                                        <c:choose>
                                                         <c:when test="${initParameters.SYS_ROLE_SELLER == sysUser.role.code }">
                                                          <html:hidden property="sellerCode" value="${sysUser.seller.code}" />
                                                         </c:when>
                                                         <c:otherwise>
                                                          <td width="230">
                                                            <bean:message key="label.seller" bundle="messages" /><br>
                                                            <html:select property="sellerCode" size="1" styleClass="input" style="width: 200px;">
                                                              <html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
                                                              <html:options collection="sellerList" property="code" labelProperty="name"/>
                                                            </html:select>
                                                          </td>
                                                         </c:otherwise>
                                                        </c:choose>
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
															<html:option value="name"><bean:message key="label.name" bundle="messages" /></html:option>																																	
															<html:option value="brand.name"><bean:message key="label.brand" bundle="messages" /></html:option>									
															<html:option value="category.name"><bean:message key="label.category" bundle="messages" /></html:option>									
															<html:option value="seller.name"><bean:message key="label.seller" bundle="messages" /></html:option>									
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
					</td>
				</tr>
				<tr>
					<td>
			            <table cellpadding="0" cellspacing="0" border="0" width="100%" >
						<tr>
						<td valign="top">
						<!--inicio seccion general information-->
						<html:form action="/system/catalogue/productRelated/save">
						<html:hidden property="action" value="saveAll" />
						<html:hidden property="productCode" value="${productForm.code}" />
						<html:hidden property="tab" value="${param.tab}" />		
						
						<span style="display:none"><html:submit styleId="inputSubmit" ><bean:message key="button.ok" bundle="messages" /></html:submit></span>
						<span style="display:none"><html:cancel styleId="inputCancel"><bean:message key="button.cancel" bundle="messages" /></html:cancel></span>
						<span style="display:none"><html:reset styleId="inputReset" ><bean:message key="button.reset" bundle="messages" /></html:reset></span>

						<table border="0" cellpadding="4" cellspacing="0" width="100%">
						<tr>
						  <td align="right">
							<table border="0" cellpadding="2" cellspacing="0">
								<tr>
									<td align="center"><a href="javascript: send(document.forms[1]);"><img src="${pageContext.request.contextPath}/images/system/icons/guardar.gif" alt="<bean:message key="button.save" bundle="messages" />" width="26" height="27" border="0" /></a></td>
									<td align="center"><a href="javascript: document.forms[1].inputReset.click(); "><img src="${pageContext.request.contextPath}/images/system/icons/restaurar.gif" alt="<bean:message key="button.reset" bundle="messages" />" width="26" height="27" border="0" /></a></td>
									<td align="center"><a href="javascript: cancel(document.forms[1]);"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="<bean:message key="button.cancel" bundle="messages" />" width="26" height="27" border="0" /></a></td>
								</tr>
								<tr>
									<td align="center"><a href="javascript: send(document.forms[1]);"><bean:message key="button.save" bundle="messages" /></a></td>
									<td align="center"><a href="javascript: document.forms[1].inputReset.click();"><bean:message key="button.reset" bundle="messages" /></a></td>
									<td align="center"><a href="javascript: cancel(document.forms[1]);"><bean:message key="button.cancel" bundle="messages" /></a></td>
								</tr>
							</table>
						  </td>
						</table>
						<table cellpadding="0" cellspacing="0" align="center"  width="100%">
							<tr>
								<td>															
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
										<td valign="top">
										<!--inicio listado-->
											<table cellpadding="0" cellspacing="0" width="97%" border="0" align="center">
											<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="5" width="5"></td></tr>
											<tr>
												<td align="center">
												<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
												<!--inicio cabecera lista-->								
										        <tr>
													<th width="50">#</th>						
													<th><bean:message key="label.product" bundle="messages" /></th>
													<th width="120"><bean:message key="label.brand" bundle="messages" /></th>
													<th width="120"><bean:message key="label.category" bundle="messages" /></th>
													<th width="120"><bean:message key="label.seller" bundle="messages" /></th>
													<th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
													<th width="80"><bean:message key="label.assign" bundle="messages" /> <input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>											
												</tr>
												<!--fin cabecera lista-->
												
												<!--inicio codigo repetitivo de registros lista-->			
												<c:forEach var="item" items="${productList}" varStatus="status">
													<%--<c:url value="/system/information/supplierProduct.do" var="urlRead">
														<c:param name="action" value="read" />
														<c:param name="code" value="${item.code}" />
														<c:param name="tab" value="5" />
													</c:url>--%>
													<c:set value="row${status.count % 2}" var="sclass" />
									      			<tr class="<c:out value="${sclass}"/>">
														<td align="right">&nbsp;&nbsp;${status.count}&nbsp;&nbsp;</td>
														<td>&nbsp;&nbsp;${item.name}&nbsp;&nbsp;</td>
														<td>${item.brand.name}</td>
														<td>${item.category.name}</td>
														<td>${item.seller.name}</td>
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
														<td align="center"><html:multibox property="codes" value="${item.code}" /></td>																	
													</tr>	
												</c:forEach>	
												<c:if test="${empty requestScope.productList && productRelatedForm.listItems }">
													<tr>
														<td height="20" align="center" colspan="7" class="message"><bean:message key="message.empty.records" /></td>
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
								</td>
							</tr>
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

