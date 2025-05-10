<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td class="title" width="300"><bean:message key="cart.order" bundle="systemMenu" /></td>
        <td align="right">
            <bean:message key="cart" bundle="systemMenu" /> &gt;
            <bean:message key="cart.order" bundle="systemMenu" />
        </td>
    </tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td><ul>
            <html:messages id="message" message="true">
                <li class="message"><c:out value="${message}" escapeXml="false" /></li>
            </html:messages>
            </ul>
            <span class="error"><html:errors/></span>        
        </td>
    </tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>

<!--inicio buscador-->
<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td>
            <html:form action="/system/cart/order">
            <html:hidden property="action" value="list" />
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
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tr>
                            <td>
																		<table border="0" cellpadding="0" cellspacing="2" width="100%">
												<tr>
													<td width="130">
														<bean:message key="label.orderNumber" bundle="messages" /><br>
														<html:text property="recordLocator" styleClass="input" style="width: 80px;" maxlength="11" />
													</td>
													
													<td width="170">
														Tipo de usuario<br>
														<html:select property="customerType" size="1" styleClass="input" style="width: 150px;">
															<html:option value="0"><bean:message key="option.all" bundle="messages"/></html:option>
															<html:options collection="customerTypeList" property="code" labelProperty="name"/>
														</html:select>
													</td>
													
													<td>
                                  <bean:message key="label.name" bundle="messages" /> <bean:message key="label.customerOf" bundle="messages" /><br>
                                  <html:text property="customerReference" styleClass="input" style="width: 150px;" maxlength="400" />
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
													
													
													
												</tr>
												<tr>
												<td>
                                  <bean:message key="label.order" bundle="messages"/> <bean:message key="label.from" bundle="messages"/><br>
                                  <html:text property="fromDate" styleClass="input" styleId="fromDate" style="width: 80px;" maxlength="11"  />  <html:button property="fromBtn" value="..." styleId="fromBtn" style="width: 20px;"/>
                                </td>
                                <td>
                                  <bean:message key="label.order" bundle="messages"/> <bean:message key="label.to" bundle="messages"/><br>
                                  <html:text property="toDate" styleClass="input" styleId="toDate" style="width: 80px;" maxlength="11"  />  <html:button property="toBtn" value="..." styleId="toBtn" style="width: 20px;"/>
                                </td>
												<td colspan="3" align="right">									
														<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
												  </td>
												</tr>
											</table>
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
                            <td><strong><bean:message key="label.orderBy" bundle="messages" />:</strong> </td>
                            <td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
                            <td>
                                <html:select property="orderField" size="1" styleClass="input" style="width: 145px;" onchange="submitOrder(this)">
								<html:option value="customer.firstName">Tipo de Usuario</html:option>

								<html:option value="customer.firstName"><bean:message key="label.user" bundle="messages" /></html:option>

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


<html:form action="/system/cart/order">
<html:hidden property="action" value="" />
<html:hidden property="code" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
        </td>
        <td align="left">
            <tiles:insert definition="system.navBar">
                <%--<tiles:put name="add"         value="true" />--%>
                <tiles:put name="edit"         value="false" />
                <%--<tiles:put name="delete"     value="true" />--%>
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
						  	<th><bean:message key="label.transactionNumber" bundle="messages" /></th>
							<th><bean:message key="label.orderNumber" bundle="messages" /></th>
							<th><bean:message key="label.creation" bundle="messages" /></th>
							<th><bean:message key="label.user" bundle="messages" /></th>
							<th>Tipo de Usuario</th>
							<th><bean:message key="label.itinerary" bundle="messages" /></th>
							<th><bean:message key="label.transactionType" bundle="messages" /></th>
							
							<th>boleto/s</th>
						    <th><bean:message key="label.passengers" bundle="messages"/></th>
							
							<th><bean:message key="label.paymentType" bundle="messages" /></th>
							<th><bean:message key="label.orderStatus" bundle="messages" /></th>
							<th><bean:message key="label.total" bundle="messages" /></th>
							
						</tr>
						<c:forEach var="item" items="${orderList}" varStatus="status">	
							<c:url value="/system/cart/order.do" var="urlRead">
								<c:param name="action" value="load" />
								<c:param name="code" value="${item.code}" />
								<c:param name="tab" value="${param.tab}" />
							</c:url> 	
							<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
								<td>${status.count}</td>			
								<td><a href="${urlRead}" class="item"><fmt:formatNumber value="${item.code}" pattern="#00000000"/></a></td>
								<td><a href="${urlRead}" class="item">${item.recordLocator}</a></td>
								<td><fmt:formatDate value="${item.creation.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></td>
								<td>${item.customerType.name}</td>
								<td>${item.customerReference}</td>
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
								<td>${item.ticketNumbers}</td>
								<td>
								<c:forEach var="passengerItem" items="${item.passengers}" varStatus="status">
									${passengerItem.firstName}&nbsp;${passengerItem.lastName} - ${passengerItem.idType.name}: ${passengerItem.identity}<br/>
								</c:forEach>
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
<c:remove var="customerForm" />
<c:remove var="orderForm" />
<script type="text/javascript" language="JavaScript1.2" src="${pageContext.request.contextPath}/js/calendar/calendar.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="${pageContext.request.contextPath}/js/calendar/lang/calendar-${appLanguage.locale}.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="${pageContext.request.contextPath}/js/calendar/calendar-setup.js"></script>
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
