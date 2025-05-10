<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/mailer2" prefix="mt" %>
<c:if test="${!empty sendMailMail}">
<c:set var="mSubject">Nueva Cuenta de Usuario :: Sistema Administración Portal Comercial Icaro</c:set>
	<mt:mail type="text/html" subject="${mSubject}" server="${initParameters.SMTP_SERVER}" from="${initParameters.CMS_ADMINISTRATOR}" username="${initParameters.SMTP_USER}" password="${initParameters.SMTP_PASSWORD}" background="false">
	<mt:addrecipient type="to" name="${sysUserMail.name}">${sysUserMail.email}</mt:addrecipient>
    <html>
        <head>
          <title>Nueva Cuenta de Usuario :: Sistema Administraci&oacute;n Portal Comercial Icaro</title>
        </head>
	    <body>
            
			<table border="0" cellpadding="0" cellspacing="0" width="600" align="center">
				<tr>
					<td colspan="2">
						Estimado/a  <strong>${sysUserMail.firstName} ${sysUserMail.lastName}</strong>,
						<br><br>
						Su cuenta de usuario ha sido creada satisfactoriamente el ${today} , detallamos a continuaci&oacute;n los datos de su cuenta de usuario para acceder al sistema de administraci&oacute;n (CMS) del Portal Comercial Icaro:
						<br>
						<br>					
					</td>
				</tr>
				<tr>
                    <td><strong><bean:message key="prompt.username" /> :</strong></td>
				   <td>${sysUserMail.name}</td>
                </tr>
				<tr>
                    <td><strong>Rol de usuario asignado :</strong></td>
				   <td>
				   <c:choose>
				   	<c:when test="${sysUserMail.roleCode==2}">
				   		Administrador
				   	</c:when>
				   <c:when test="${sysUserMail.roleCode==3}">
				   		Webmaster
				   	</c:when>
					<c:otherwise>
					${sysUserMail.roleCode}
					</c:otherwise>
				   </c:choose>
				   </td>
                </tr>	
				<tr>
                    <td><strong><bean:message key="prompt.password" /> :</strong></td>
				   <td>${sysUserMail.password}</td>
                </tr>					
			</table>
			
        </body>
    </html>  
	</mt:mail>
	
	<c:set var="mSubject">Nueva Cuenta de Usuario :: Sistema Administración Portal Comercial Icaro</c:set>
	<mt:mail type="text/html" subject="${mSubject}" server="${initParameters.SMTP_SERVER}" from="${initParameters.CMS_ADMINISTRATOR}" username="${initParameters.SMTP_USER}" password="${initParameters.SMTP_PASSWORD}" background="false">
	<mt:addrecipient type="to">${initParameters.CMS_ADMINISTRATOR}</mt:addrecipient>
    <html>
        <head>
          <title>Nueva Cuenta de Usuario :: Sistema Administraci&oacute;n Portal Comercial Icaro</title>
        </head>
	    <body>
            
			<table border="0" cellpadding="0" cellspacing="0" width="600" align="center">
				<tr>
					<td colspan="2">
						Estimado/a  Administrador,
						<br><br>
						Le informamos que la cuenta de usuario ${sysUserMail.name} con rol <c:choose>
				   	<c:when test="${sysUserMail.roleCode==2}">
				   		Administrador
				   	</c:when>
				   <c:when test="${sysUserMail.roleCode==3}">
				   		Webmaster
				   	</c:when>
					<c:otherwise>
					${sysUserMail.roleCode}
					</c:otherwise>
				   </c:choose> ha sido creada el ${today}.
						
						<p>En caso de que esta cuenta de usuario no haya sido autorizada, por favor ingresar al sistema de administraci&oacute;n y deshabilitar la misma</p>
						<br>
						<br>					
					</td>
				</tr>
							
			</table>
			
        </body>
    </html>  
	</mt:mail>
</c:if>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="administration.user" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			<bean:message key="administration.user" bundle="systemMenu" />
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
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<!--inicio buscador-->
<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
			<html:form action="/system/admin/sysUser">
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
								<td width="130">
									<bean:message key="label.name" bundle="messages" /><br>
									<html:text property="name" styleClass="input" style="width: 200px;" maxlength="100" />
								</td>
								<td width="260">
									<bean:message key="label.status" bundle="messages" /><br>
									<html:select property="enabledOption" size="1" styleClass="input" style="width: 150px;">
										<html:option value="-1"><bean:message key="option.all" bundle="messages"/></html:option>																
										<html:option value="1"><bean:message key="prompt.enabled" /></html:option>
										<html:option value="0"><bean:message key="prompt.disabled" /></html:option>
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
									<html:option value="enabled"><bean:message key="label.status" bundle="messages" /></html:option>									
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


<html:form action="/system/admin/sysUser">
<html:hidden property="action" value="" />
<!-- Options -->
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
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th width="200"><bean:message key="label.user" bundle="messages" /></th>
		<th><bean:message key="label.name" bundle="messages" /></th>
		<th width="200"><bean:message key="label.role" bundle="messages" /></th>
		<th width="100"><bean:message key="label.enabled" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${sysUserList}" varStatus="status">
		<c:url value="/system/admin/sysUser.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
			<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
				<td>${status.count}</td>
				<td align="center">
					<c:if test="${item.role.code != 1}">
						<html:checkbox property="codes" value="${item.code}" />
					</c:if>
				</td>
				<td>
				<c:choose>
				<c:when test="${item.role.code != 1}">
					<a href="${urlRead}" class="item">${item.name}</a>
				</c:when>
				<c:otherwise>
					${item.name}
				</c:otherwise>
				</c:choose>
				</td>
				<td>${item.lastName} ${item.firstName}</td>
				<td>${item.role.name}</td>
				<td>
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
</table>
</html:form>