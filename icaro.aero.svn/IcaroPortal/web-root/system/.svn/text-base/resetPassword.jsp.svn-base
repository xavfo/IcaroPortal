<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!------------------------------------------------------------------------>
<!-- Copyright © Yage. All Rights Reserved.                             -->
<!--                                                                    -->
<!-- YAGE evolucion digital                                             -->
<!-- Av. Brasil 951 y Mariano Echeverría                                -->
<!-- Quito-Ecuador                                                      -->
<!-- www.yage.com.ec                                                    -->
<!------------------------------------------------------------------------>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title><bean:message key="window.title" bundle="systemHelp" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
	<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/dtree.css">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/favicon.ico">

	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/validator.jsp"></script>
    <style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
    </style>
</head>

<body>
	<table align="center" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
		<tr>
			<td bgcolor="#000000" width="1"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="450" border="0"></td>
			<td>
				<table bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="75"><img src="${pageContext.request.contextPath}/images/system/logo_yage.gif" width="158" height="75" border="0"></td>
								</tr>
							</table>
							<table background="${pageContext.request.contextPath}/images/system/dots.gif" border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="3"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="3" border="0"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
					  <td height="100%" valign="top" style="padding: 15px 10px 25px 10px;">							
					    <html:form action="/resetPassword" focus="email" >
						  <html:hidden property="action" value="find" />
						  <table width="70%">
						  <tr><td>Si olvid&oacute; su contrase&ntilde;a por favor ingrese su direcci&oacute;n de correo electr&oacute;nico. <br>El sistema generar&aacute; una <strong>contrase&ntilde;a nueva</strong> y la <strong>enviar&aacute; a su correo electr&oacute;nico</strong> en unos minutos. <br>Si necesita soporte adicional, por favor comun&iacute;quese con el departamento de sistemas.</td></tr></table>
							<table border="0" cellpadding="1" cellspacing="0">								
								<tr>
									<td width="400"><br>
										<table border="0" cellpadding="5" cellspacing="0" width="100%">
											<tr>
												<td align="right" width="192">Correo Electr&oacute;nico:</td>
											  <td width="188"><html:text property="email" styleClass="input" style="width: 150px;" /></td>
											</tr>											
											<tr>
												<td>&nbsp;</td>
												<td><html:submit styleClass="button" style="width: 180px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')">Solicitar Nuevo Password</html:submit></td>
											</tr>
											<tr>
											  <td colspan="2">&nbsp;											  </td>
										  </tr>
											<c:url value="/home/reset" var="urlDetail">
												<c:param name="setNew" value="funca" />
											</c:url>
									  </table>								  </td>
								</tr>
							</table>							
						    <p class="style1">
					          <html:messages id="message" message="true">
									<c:out value="${message}" escapeXml="false" /><br>
							  </html:messages>
							  <html:messages id="error">
								  <c:out value="${error}" escapeXml="false" /><br>
							  </html:messages>
						    </p>
					    </html:form>
						<html:javascript formName="loginForm" dynamicJavascript="true" staticJavascript="false" />		&nbsp;</td>
					</tr>
					<tr>
						<td>
							<c:import url="/system/layout/footer.jsp" /> 
						</td>
					</tr>
				</table>
			</td>
			<td bgcolor="#000000" width="1"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="1" border="0"></td>
		</tr>
	</table>
</body>
</html>
