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
			<bean:message key="cart.customer" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="cart.customer" bundle="systemMenu" /></c:set>
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
			<ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			</ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>
<table>
	<tr>
		<td class="subtitle">${customerForm.firstName} ${customerForm.lastName}</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.customer">
			<tiles:put name="code" value="${customerForm.code}" />
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


					<!--inicio buscador-->
					<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td>
								<html:form action="/system/cart/customerOrder">
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
													<td width="130">
														<bean:message key="label.orderNumber" bundle="messages" /><br>
														<html:text property="recordLocator" styleClass="input" style="width: 80px;" maxlength="11" />
													</td>
												
													<td width="170">
														<bean:message key="label.orderStatus" bundle="messages" /><br>
														<html:select property="statusCode" size="1" styleClass="input" style="width: 150px;">
															<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
															<html:options collection="statusList" property="code" labelProperty="name"/>
														</html:select>
													</td>
													<td width="170">
														<bean:message key="label.paymentType" bundle="messages" /><br>
														<html:select property="paymentTypeCode" size="1" styleClass="input" style="width: 150px;">
															<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
															<html:options collection="paymentTypeList" property="code" labelProperty="name"/>
														</html:select>
													</td>
													
													<td>
                                  <bean:message key="label.order" bundle="messages"/> <bean:message key="label.from" bundle="messages"/><br>
                                  <html:text property="fromDate" styleClass="input" styleId="fromDate" style="width: 80px;" maxlength="11"  />  <html:button property="fromBtn" value="..." styleId="fromBtn" style="width: 20px;"/>
                                </td>
                                <td>
                                  <bean:message key="label.order" bundle="messages"/> <bean:message key="label.to" bundle="messages"/><br>
                                  <html:text property="toDate" styleClass="input" styleId="toDate" style="width: 80px;" maxlength="11"  />  <html:button property="toBtn" value="..." styleId="toBtn" style="width: 20px;"/>
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
													<html:select property="orderField" size="1" styleClass="input" style="width: 145px;" onchange="submitOrder(this)">
													<html:option value="recordLocator"><bean:message key="label.orderNumber" bundle="messages" /></html:option>
														<html:option value="status.name"><bean:message key="label.orderStatus" bundle="messages" /></html:option>
														<html:option value="payment.type.name"><bean:message key="label.paymentType" bundle="messages" /></html:option>
														<html:option value="creation"><bean:message key="label.creation" bundle="messages" /></html:option>
														
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
	
	
					<html:form action="/system/cart/customerOrder">
					<html:hidden property="action" value="read" />
					<html:hidden property="code" />
					<html:hidden property="tab" value="${param.tab}" />
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
					</table>
					<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
					
					
					<!-- List data -->
					<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
						<tr>
							<th width="50">#</th>
						  	<th><bean:message key="label.transactionNumber" bundle="messages" /></th>
							<th><bean:message key="label.orderNumber" bundle="messages" /></th>
							
							<th><bean:message key="label.creation" bundle="messages" /></th>
							<th><bean:message key="label.itinerary" bundle="messages" /></th>
							<th><bean:message key="label.transactionType" bundle="messages" /></th>
							
							<th><bean:message key="label.paymentType" bundle="messages" /></th>
							<th><bean:message key="label.orderStatus" bundle="messages" /></th>
							<th><bean:message key="label.total" bundle="messages" /></th>
							
						</tr>
						<c:forEach var="item" items="${orderList}" varStatus="status">	
							<c:url value="/system/cart/customerOrder.do" var="urlRead">
								<c:param name="action" value="load" />
								<c:param name="code" value="${item.code}" />
								<c:param name="tab" value="${param.tab}" />
							</c:url> 	
							<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
								<td>${status.count}</td>			
								<td><a href="${urlRead}" class="item"><fmt:formatNumber value="${item.code}" pattern="#00000000"/></a></td>
								<td>${item.recordLocator}</td>
								<td><fmt:formatDate value="${item.creation.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></td>
								<td>
								
								<table width="100%" border="0">
  <c:forEach var="orderItineraryItem" items="${item.itineraries}" varStatus="status">
  <tr>  
    <td><fmt:formatDate value="${orderItineraryItem.departureDateTime.time}" pattern="${initParameters.DATE_TIME_FORMAT}" /></td>
	<td>${orderItineraryItem.departureAirport.city.name}</td>
  </tr>
  <tr>  
    <td><fmt:formatDate value="${orderItineraryItem.arrivalDateTime.time}" pattern="${initParameters.DATE_TIME_FORMAT}" /></td>
	<td>${orderItineraryItem.arrivalAirport.city.name}</td>
  </tr>
</c:forEach> 
</table>								
								
								
								</td>
								<td>
								<c:choose>
								<c:when test="${item.sale}">
								Compra
								</c:when>
								<c:otherwise>
								Reserva
								</c:otherwise>
								</c:choose>
								</td>
								<td>${item.payment.type.name}</td>
								<td>${item.status.name}</td>
								<td>${item.totalAmount}</td>
								
							</tr>
						</c:forEach>
						<c:if test="${empty requestScope.orderList && orderForm.listItems }">
							<tr>
								<td height="20" align="center" colspan="9" class="message"><bean:message key="message.empty.records" /></td>
							</tr>	
						</c:if>  
						
					</table>
					</html:form>
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
<script type="text/javascript" language="JavaScript1.2">
  Calendar.setup(
    {
      inputField    : "fromDate",
      ifFormat      : "%Y-%m-%d",
      dateFormat    : "%Y-%m-%d",
      weekNumbers   : false,
      align         : "Bl",
      button        : "fromBtn"
    }
  );  
  Calendar.setup(
    {
      inputField    : "toDate",
      ifFormat      : "%Y-%m-%d",
      dateFormat    : "%Y-%m-%d",
      weekNumbers   : false,
      align         : "Bl",
      button        : "toBtn"
    }
  );  
</script>

