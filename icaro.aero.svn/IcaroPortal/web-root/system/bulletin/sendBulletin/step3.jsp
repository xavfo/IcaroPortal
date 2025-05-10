<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/mailer" prefix="mt" %>

<script language="javascript">
function send(frm){	
	var button =  document.getElementById('inputSubmit');
	button.click();
}
function cancel(frm) {
	var button =  document.getElementById('inputCancel');
	button.click();
}
</script>


<c:set value="${initParameters.HOST_URL}" var="appPath" />
<c:set value="${appPath}${pageContext.request.contextPath}" var="appPath" />
<c:forEach var="item" items="${bulletinBean.emailBeans}">
	<c:forEach var="emailItem" items="${item.mailList}">
		<mt:mail>
			<mt:server>${initParameters.SMTP_SERVER}</mt:server>
			<mt:from>${initParameters.SMTP_FROM}</mt:from>
			<mt:setrecipient type="to">${emailItem.email}</mt:setrecipient>		
			<mt:subject>${bulletinBean.bulletin.subject}</mt:subject>
			<%--<mt:attach file="/templates/images/popup%20copy-01.jpg"/>--%>
			<mt:message type="html">
			
				<style type="text/css">				
					.Estilo1 {
						font-family: Arial, Helvetica, sans-serif;
						color: #1A2A8C;
						font-size: 14px;
						font-weight: bold;
					}
					.Estilo2 {
						font-size: 14px;
						font-weight: bold;
						font-family: Arial, Helvetica, sans-serif;
					}
					.Estilo3 {
						font-family: Arial, Helvetica, sans-serif;
						font-size: 12px;
					}
				</style>
				
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				  <tr>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="607" height="1" border="0" alt=""/></td>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="1" height="1" border="0" alt=""/></td>
				  </tr>
				  <tr>
				   <td>
				     <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#1A2A8C">
				       <tr>
				         <td width="176"><img name="header" src="${appPath}/images/bulletin/layout/header.gif" width="165" height="51" border="0" alt=""/></td>
				         <td width="100%" bgcolor="#1A2A8C">&nbsp;</td>
				         <td width="197" align="right"><img src="${appPath}/images/bulletin/layout/boletin.gif" width="15" height="51"/></td>
				       </tr>
				     </table></td>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="1" height="51" border="0" alt=""/></td>
				  </tr>
				  <tr>
				   <td style="background:url('${appPath}/images/bulletin/layout/sep.gif');"><img src="${appPath}/images/bulletin/layout/sep.gif" width="2" height="13"/></td>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="1" height="13" border="0" alt=""/></td>
				  </tr>
				  <tr>
				   <td><img name="sep2" src="${appPath}/images/bulletin/layout/sep2.gif" width="607" height="4" border="0" alt=""/></td>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="1" height="4" border="0" alt=""/></td>
				  </tr>
				  <tr>
				    <td><table width="100%" style="height:91px;" border="0" cellpadding="0" cellspacing="0">
				   	<c:choose>
						<c:when test="${!empty bulletin.image}">
							<tr>
						       <td width="18%" rowspan="2"><img src="${appPath}${bulletin.image}"/></td>
						       <td colspan="2"><span class="Estilo1">${bulletinBean.bulletin.topic.name}</span></td>
						    </tr>
						</c:when>
						<c:otherwise>
							<tr>
						       <td colspan="2"><span class="Estilo1">${bulletinBean.bulletin.topic.name}</span></td>
						     </tr>
						</c:otherwise>
					</c:choose>     
				     <tr>
				       <td width="4%">&nbsp;</td>
				       <td width="78%"><span class="Estilo2">${bulletinBean.bulletin.title}</span><br/>
				         <span class="Estilo3">${bulletinBean.bulletin.content}</span></td>
				     </tr>
				   </table></td>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="1" height="167" border="0" alt=""/></td>
				  </tr>
				  <tr>
				   <td><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
				     <tr>
				       <td width="176"><img name="footer" src="${appPath}/images/bulletin/layout/footer.gif" width="203" height="22" border="0" alt=""/></td>
				       <td width="100%" bgcolor="#FFFFFF">&nbsp;</td>
				       <td width="197" align="right"><img src="${appPath}/images/bulletin/layout/sri.gif" width="126" height="22"/></td>
				     </tr>
				   </table></td>
				   <td><img src="${appPath}/images/bulletin/layout/spacer.gif" width="1" height="22" border="0" alt=""/></td>
				  </tr>
				</table>
			</mt:message>
			<mt:send />
		</mt:mail>
	</c:forEach>	
</c:forEach>	

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="bulletin.sendBulletin" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> &gt;
			<bean:message key="bulletin.bulletin" bundle="systemMenu" /> &gt;
			<bean:message key="bulletin.sendBulletin" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" style="color:#999999" noshade/>

<table border="0" cellpadding="2" cellspacing="2">
	<tr>
		<td>
			<table class="stepOff" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td>
						<bean:message key="label.step1" bundle="messages" /><br/>Seleccionar Bolet&iacute;n
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>
		<td>
			<table class="stepOff" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td>
						<bean:message key="label.step2" bundle="messages" /><br/>Vista Previa / Env&iacute;o
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>		
		<td>
			<table class="step3" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td class="stepLabel">
						<bean:message key="label.step3" bundle="messages" /><br/>Confirmaci&oacute;n de Env&iacute;o
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"/>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>appPath${appPath}
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
			<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"/><br/>

<html:form action="/system/bulletin/step1">
<html:hidden property="action" value="create" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle"></td>
		<td align="right">
			<span style="display:none"><html:submit styleId="inputSubmit" ><bean:message key="button.ok" bundle="messages" /></html:submit></span>
			<span style="display:none"><html:cancel styleId="inputCancel"><bean:message key="button.cancel" bundle="messages" /></html:cancel></span>
			<table border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td align="center"><a href="javascript: send(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/nuevoboletin.gif" alt="<bean:message key="button.newBulletin" bundle="messages" />" width="26" height="27" border="0" /></a></td>
					<td align="center"><a href="javascript: cancel(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/salir.gif" alt="<bean:message key="button.exit" bundle="messages" />" width="26" height="27" border="0" /></a></td>
				</tr>
				<tr>
					<td align="center"><a href="javascript: send(document.forms[0]);"><bean:message key="button.newBulletin" bundle="messages" /></a></td>
					<td align="center"><a href="javascript: cancel(document.forms[0]);"><bean:message key="button.exit" bundle="messages" /></a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"/><br/>
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr><td colspan="2" height="25"></td></tr>
	<tr>
		<td class="optional">De :</td>
		<td>${initParameters.SMTP_FROM}</td>
	</tr>
	<tr>
		<td class="optional" valign="top">Bolet�n enviado a suscritos en :</td>
		<td>
		<c:set value="0" var="total" />
		<c:forEach var="item" items="${bulletinBean.emailBeans}">
			${item.groupName} (${item.count})<br/>
			<c:set value="${total + item.count}" var="total" />
		</c:forEach>
		</td>
	</tr>	
	<tr>
		<td class="optional">Total de usuarios a enviar :</td>
		<td>${total}</td>
	</tr>
	<tr><td colspan="2" height="25"></td></tr>
	<tr>
		<td colspan="2">
			<table width="75%" align="center" bgcolor="#ffffff">
				<tr>
					<td><c:import url="/system/bulletin/sendBulletin/previewBulletin.jsp" /></td>
				</tr>
			</table>			
		</td>
	</tr>
	<tr><td colspan="2" height="25"></td></tr>
</table>
</html:form>

<c:remove var="bulletinBean" />