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
				<!--inicio seccion general information-->
				<table border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
				  <td align="right">
						<c:url value="/system/cart/customerOrder.do" var="urlBack">
							<c:param name="action" value="list" />
							<c:param name="tab" value="${param.tab}" />
						</c:url> 	
						<table border="0" cellpadding="2" cellspacing="0">
							<tr>
								<td align="center"><a href="${urlBack}" class="back"><img src="${pageContext.request.contextPath}/images/system/icons/atras.gif" alt="<bean:message key="button.back" bundle="messages" />" width="26" height="27" border="0" /><br /></a>	</td>
							</tr>
							<tr>
								<td align="center"><a href="${urlBack}" class="back"><bean:message key="button.back" bundle="messages" /></a></td>							
							</tr>
						</table>								
				  </td>
				</table>
				<c:choose>				
				<c:when test="${order.sale==false}">				
				
                <table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
                	<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                	<tr>
						<td class="subtitle" colspan="4"  height="30">Detalle de Reserva # <strong>${order.recordLocator}</strong></td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                	<tr>
                		<td class="label"><bean:message key="label.transactionNumber" bundle="messages" /> :</td>
                		<td><div class="box" style="width: 120px;"><fmt:formatNumber value="${order.code}" pattern="#00000000"/></div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
                	<tr>
                		<td class="label">Generado por :</td>
                		<td><div class="box" style="width: 200px;">${order.customerType.name} - ${order.customerReference}</div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
                	<tr>
                        <td class="label"> <bean:message key="prompt.status" /> :</td>
                        <td><div class="box" style="width: 200px;">${order.status.name}</div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
					<tr>
                        <td class="label">Reserva Valida Hasta :</td>
                        <td><div class="box" style="width: 120px;"><fmt:formatDate value="${order.effectiveTo.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
                	<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
					<tr>
						<td class="subtitle" colspan="4"  height="30">Itinerario de Vuelo</td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                	<tr>
                		<td colspan="4">
                			<table align="center" border="0" cellpadding="4" cellspacing="0" width="80%">
                			<tr>
                				<td>		
                				<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
                					<tr>
                						<th>Fecha</th>
    									<th>Salida</th>
										<th>Llegada</th>
										<th>Aerol&iacute;nea</th>
										<th>Vuelo/CLS</th>
                					</tr>
<c:forEach var="itineraryItem" items="${order.itineraries}" varStatus="status">
  <tr class="colorTr5">
  	<td><fmt:formatDate value="${itineraryItem.departureDateTime.time}" pattern="${initParameters.DATE_TIME_FORMAT}" /></td>
  	<td>${itineraryItem.departureAirport.city.name}-${itineraryItem.departureAirport.city.state.country.name}</td>
  	<td>${itineraryItem.arrivalAirport.city.name}-${itineraryItem.arrivalAirport.city.state.country.name}</td>
  	<td>${itineraryItem.airplane}</td>
  	<td>${itineraryItem.flightNumber}</td>
  	</tr>
	</c:forEach>
								</table>  
		           				</td>		
                			</tr>
                		</table>
                		</td>
                	</tr>
					<tr>
						<td class="subtitle" colspan="4"  height="30">Listado de Pasajeros</td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
					<tr>
					<td colspan="4">
						<div style="text-align: right;"><strong>Nota IVA:</strong> <fmt:formatNumber value="${order.ivaPercentage}" type="percent"/></div>
						<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%" align="center">
							<tr>
							    <th><bean:message key="label.passenger" bundle="messages"/></th>
							    <th><bean:message key="prompt.id"/></th>
							    <th><bean:message key="prompt.paxType" /></th>
							    <th><bean:message key="prompt.nationality"/></th>
							    <th><bean:message key="label.birthdate" bundle="messages"/></th>
							    <th><bean:message key="label.email" bundle="messages"/></th>
							    <th><bean:message key="label.phone" bundle="messages"/></th>
							    <th><bean:message key="prompt.frequentFlyer"/></th>
							    <th><bean:message key="prompt.fare" /></th>
							    <th><bean:message key="label.flow.taxes" bundle="messages"/></th>
							    <th>Total</th>
							</tr>
						  <c:forEach var="passengerItem" items="${order.passengers}" varStatus="status">
						  	<tr>
							  	<td>${passengerItem.firstName}&nbsp;${passenger.lastName}</td>
							  	<td nowrap="nowrap">${passengerItem.idType.name}: ${passengerItem.identity}</td>
							    <td>
   							      	<c:choose><c:when test="${appLanguage.locale eq 'es'}">${passengerItem.paxType.name}</c:when>
									<c:otherwise>${passengerItem.paxType.enName}</c:otherwise></c:choose>
							    </td>
							  	<td>${passengerItem.nationality}</td>
							  	<td><fmt:formatDate value="${passengerItem.birthdate}" pattern="${initParameters.DATE_FORMAT}" /></td>
							  	<td>${passengerItem.email}</td>
							  	<td>
							  		<c:if test="${!empty passengerItem.phone}">
								  		tefl:${passengerItem.phone}<br/>
							  		</c:if>
							  		<c:if test="${!empty passengerItem.mobile}">
								  		cel:${passengerItem.mobile}
							  		</c:if>
							  	</td>
							  	
							  	<td>${passengerItem.frequenTravelNumber}</td>
								<td>${passengerItem.fareAmount}</td>
								<td><fmt:formatNumber value="${passengerItem.totalAmount-passengerItem.fareAmount}" maxFractionDigits="2" minFractionDigits="2" /></td>
								<td><fmt:formatNumber value="${passengerItem.totalAmount}" maxFractionDigits="2" minFractionDigits="2" />	</td>
						  	</tr>
						</c:forEach>
							<tr>
							    <td colspan="10" align="right"><strong>Total</strong></td>
							    <td><fmt:formatNumber value="${order.totalAmount}" maxFractionDigits="2" minFractionDigits="2" /></td>
							</tr>
						</table>

					</td>
					</tr>
                	<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                </table>
				</c:when>
				<c:when test="${order.sale==true}">
				
				   <table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                	<tr>
						<td class="subtitle" colspan="4"  height="30">Detalle de Compra de Reserva # <strong>${order.recordLocator}</strong></td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                	<tr>
                		<td class="label"><bean:message key="label.transactionNumber" bundle="messages" /> :</td>
                		<td><div class="box" style="width: 120px;"><fmt:formatNumber value="${order.code}" pattern="#00000000"/></div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
                	<tr>
                		<td class="label">Generado por :</td>
                		<td><div class="box" style="width: 120px;">${order.customerType.name} - ${order.customerReference}</div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
                	<tr>
                        <td class="label"> <bean:message key="prompt.status" /> :</td>
                        <td><div class="box" style="width: 200px;">${order.status.name}</div></td>
                        <td colspan="2">&nbsp;</td>
                	</tr>
                	<c:if test="${ ! empty order.ticketNumbers}">
	  					<tr>
	                        <td class="label"> eTickets De la Orden :</td>
    	                    <td><div class="box" style="width: 200px;">${order.ticketNumbers}</div></td>
        	                <td colspan="2">&nbsp;</td>
	  					</tr>
  					</c:if>
                	
					
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
					<tr>
						<td class="subtitle" colspan="4"  height="30">Informaci&oacute;n de Factura</td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
					                	<tr>
                		<td colspan="4">
                			<table align="center" border="0" cellpadding="4" cellspacing="0" width="80%">
                			<tr>
								<td>
									<c:if test="${!empty order.payment.type.logo}">
									<strong>Forma de Pago</strong>
									<br />
									<img alt="${order.payment.type.name}" src="${pageContext.request.contextPath}${order.payment.type.logo}"  border="0">
									</c:if>
								</td>
                				<td>		
                				<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
                					<tr>
                						<tr>
										<th>Nombre / Raz&oacute;n Social</th>
										<th>Identificaci&oacute;n</th>
										<th>Direcci&oacute;n</th>
										<th>Tel&eacute;fono</th>
    
                					</tr>                					
                					<tr>
									 	<td>${order.billInfo.name}</td>
									 	<td>${order.billInfo.identity}</td>
									 	<td>${order.billInfo.address}</td>
									 	<td>${order.billInfo.phone}</td>
									</tr>
                                    
                				</table>                				</td>		
                			</tr>
                		</table>
                		</td>
                	</tr>
					
                	<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
					<tr>
						<td class="subtitle" colspan="4"  height="30">Itinerario de Vuelo</td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                	<tr>
                		<td colspan="4">
                			<table align="center" border="0" cellpadding="4" cellspacing="0" width="80%">
                			<tr>
                				<td>		
                				<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
                					<tr>
                						<th>Fecha</th>
    									<th>Salida</th>
										<th>Llegada</th>
										<th>Aerol&iacute;nea-Nave</th>
										<th>Vuelo/CLS</th>
                					</tr>
									<c:forEach var="itineraryItem" items="${order.itineraries}" varStatus="status">
										<tr class="colorTr5">
										  	<td><fmt:formatDate value="${itineraryItem.departureDateTime.time}" pattern="${initParameters.DATE_TIME_FORMAT}" /></td>
										  	<td>${itineraryItem.departureAirport.city.name}-${itineraryItem.departureAirport.city.state.country.name}</td>
										  	<td>${itineraryItem.arrivalAirport.city.name}-${itineraryItem.arrivalAirport.city.state.country.name}</td>
										  	<td>${itineraryItem.airlineCode}-${itineraryItem.airplane}</td>
										  	<td>${itineraryItem.flightNumber}</td>
									  	</tr>
									</c:forEach>
								</table>                  				</td>		
                			</tr>
                		</table>
                		</td>
                	</tr>
					<tr>
						<td class="subtitle" colspan="4"  height="30">Listado de Pasajeros</td>
					</tr>
					<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>


                	<tr>
                		<td colspan="4">
						<div style="text-align: right;"><strong>Nota IVA:</strong> <fmt:formatNumber value="${order.ivaPercentage}" type="percent"/></div>
						<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%" align="center">
							<tr>
							    <th><bean:message key="label.passenger" bundle="messages"/></th>
							    <th><bean:message key="prompt.id"/></th>
							    <th><bean:message key="prompt.paxType" /></th>
							    <th><bean:message key="prompt.nationality"/></th>
							    <th><bean:message key="label.birthdate" bundle="messages"/></th>
							    <th><bean:message key="label.email" bundle="messages"/></th>
							    <th><bean:message key="label.phone" bundle="messages"/></th>
							    <th><bean:message key="prompt.frequentFlyer"/></th>
							    <th><bean:message key="prompt.fare" /></th>
							    <th><bean:message key="label.flow.taxes" bundle="messages"/></th>
							    <th>Total</th>
							</tr>
						  <c:forEach var="passengerItem" items="${order.passengers}" varStatus="status">
						  	<tr>
							  	<td>${passengerItem.firstName}&nbsp;${passenger.lastName}</td>
							  	<td nowrap="nowrap">${passengerItem.idType.name}: ${passengerItem.identity}</td>
							    <td>
   							      	<c:choose><c:when test="${appLanguage.locale eq 'es'}">${passengerItem.paxType.name}</c:when>
									<c:otherwise>${passengerItem.paxType.enName}</c:otherwise></c:choose>
							    </td>
							  	<td>${passengerItem.nationality}</td>
							  	<td><fmt:formatDate value="${passengerItem.birthdate}" pattern="${initParameters.DATE_FORMAT}" /></td>
							  	<td>${passengerItem.email}</td>
							  	<td>
							  		<c:if test="${!empty passengerItem.phone}">
								  		tefl:${passengerItem.phone}<br/>
							  		</c:if>
							  		<c:if test="${!empty passengerItem.mobile}">
								  		cel:${passengerItem.mobile}
							  		</c:if>
							  	</td>
							  	
							  	<td>${passengerItem.frequenTravelNumber}</td>
								<td>${passengerItem.fareAmount}</td>
								<td><fmt:formatNumber value="${passengerItem.totalAmount-passengerItem.fareAmount}" maxFractionDigits="2" minFractionDigits="2" /></td>
								<td><fmt:formatNumber value="${passengerItem.totalAmount}" maxFractionDigits="2" minFractionDigits="2" />	</td>
						  	</tr>
						</c:forEach>
							<tr>
							    <td colspan="10" align="right"><strong>Total</strong></td>
							    <td><fmt:formatNumber value="${order.totalAmount}" maxFractionDigits="2" minFractionDigits="2" /></td>
							</tr>
						</table>

						</td>
					</tr>
                	<tr>
                		<td colspan="4"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
                	</tr>
                </table>
				</c:when>
				</c:choose>
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
