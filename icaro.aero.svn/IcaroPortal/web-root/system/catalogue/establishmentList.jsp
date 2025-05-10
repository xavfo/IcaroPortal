<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="cart.establishment" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" />  &lt;
			<bean:message key="cart.establishment" bundle="systemMenu" />
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
			<html:form action="/system/catalogue/establishment">
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
						<table border="0" cellpadding="0" cellspacing="2" width="100%">
							<tr>
								<td width="130" valign="middle">
									<bean:message key="label.name" bundle="messages" /><br>
									<html:text property="name" styleClass="input" style="width: 120px;" maxlength="100" />
								</td>
					<td valign="bottom">
					<bean:message key="label.category" bundle="messages" /><br>
				<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;"  >
			<html:option value="">
				<bean:message key="option.none" bundle="messages" />
			</html:option>
		<c:if test="${!empty establishmentCategoryList}">
			<html:options collection="establishmentCategoryList" property="code" labelProperty="name"/>
		</c:if>
	</html:select>			
					</td>
					<td valign="bottom">
					<bean:message key="label.city" bundle="messages" /><br>
							<html:select property="cityCode" size="1" styleClass="input" style="width: 250px;"  >
			<html:option value="">
				<bean:message key="option.none" bundle="messages" />
			</html:option>
		<c:if test="${!empty cityList}">
			<html:options collection="cityList" property="code" labelProperty="name"/>
		</c:if>
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
									<html:option value="name"><bean:message key="label.name" bundle="messages" /></html:option>									
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


<html:form action="/system/catalogue/establishment">
<html:hidden property="action" value="" />
<html:hidden property="code" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
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
	  <th width="382"><bean:message key="label.category" bundle="messages" /></th>
	  <th width="382"><bean:message key="label.city" bundle="messages" /></th>
	  <th width="382"><bean:message key="label.phone" bundle="messages" /></th>
	  <th width="382"><bean:message key="label.contact" bundle="messages" /></th>
	  <th width="382"><bean:message key="label.enabled" bundle="messages" /></th>
	  
	</tr>
	<c:forEach var="item" items="${establishmentList}" varStatus="status">	
		<c:url value="/system/catalogue/establishment.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url> 	
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
			<td>${item.category.name}</td>
			<td>${item.city.name}</td>
			<td>${item.phone1}</td>
			<td>${item.contact.contactName}</td>
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
			
		</tr>
	</c:forEach>
	<c:if test="${empty requestScope.establishmentList && establishmentForm.listItems }">
		<tr>
			<td height="20" align="center" colspan="8" class="message"><bean:message key="message.empty.records" /></td>
		</tr>	
	</c:if>  

</table>
</html:form>