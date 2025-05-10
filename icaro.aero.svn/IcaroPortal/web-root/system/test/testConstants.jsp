<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" %>

<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Properties" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.iportal.Constants" %>
<%@ page import="org.apache.commons.beanutils.PropertyUtils" %>

<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>


<%@page import="com.iportal.cart.CartConstants"%>
<html>
<head>
	<title>Test Contants</title>
</head>

<body>
 <%= new java.util.Date() %><br><br>
Contenido de Objeto <strong><%= Constants.class.getName() %></strong>:<br />
<br><br>
<%--CONTEXT_PATH from pageContext:${pageContext.request.contextPath}<br>
Real Path: <%= request.getRealPath("/")  %><br>
CONTEXT_PATH: <%= Constants.CONTEXT_PATH %><br>
--%>
<table cellspacing="0" cellpadding="2" border="1">
<tr class="header" >
    <th>#&nbsp;</th>
    <th align="left">CONSTANTE&nbsp;</th>
    <th align="left">VALOR&nbsp; <%= Constants.class.getName() %></th>
	<th align="left">VALOR&nbsp; initParameters</th>
</tr>

<%  Properties props = (Properties)application.getAttribute("initParameters");
	Constants constants = new Constants ();
	CartConstants cartConst = new CartConstants ();
	
	Map constantProps = (Map) PropertyUtils.describe(constants); 
	Map cartProps = (Map) PropertyUtils.describe(cartConst); 
	Iterator iterCart = cartProps.entrySet().iterator();
	Iterator iter = constantProps.entrySet().iterator();
	int count = 0;
	while (iter.hasNext() ) {
		Entry entry = (Entry) iter.next();
%>
		
	<tr>
	    <td><%= ++count %>.-&nbsp;</td>
	    <td><strong><%= entry.getKey() %></strong>:</td>
	    <td><%= entry.getValue() %></td>
		<td>
			<% if ( props != null ) { %>
				<%= props.get(entry.getKey().toString()) %>
			<%	} %>
		</td>
	</tr>
<%	}  %>
	<tr>
	    <td colspan="4"> <strong>CONSTANTES DE CART</strong></td>
	</tr>

<%	while (iterCart.hasNext() ) {
		Entry entry = (Entry) iterCart.next();
%>
	<tr>
	    <td><%= ++count %>.-&nbsp;</td>
	    <td><strong><%= entry.getKey() %></strong>:</td>
	    <td><%= entry.getValue() %></td>
		<td>
			<% if ( props != null ) { %>
				<%=  props.get(entry.getKey().toString()) %>
			<%	} %>
		</td>
	</tr>
<%	} %>


</table>
			<%--1.-Constants.DATE_PATTERN: <%= Constants.DATE_PATTERN %> <br />
			2.-Constants.TIME_PATTERN: <%= Constants.TIME_PATTERN %> <br />
			
			3.-Constants.DATE_TIME_PATTERN: <%= Constants.DATE_TIME_PATTERN %> <br />
			4.-Constants.CURRENCY_FORMAT: <%= Constants.CURRENCY_FORMAT %> <br />
			5.-Constants.NUMBER_OF_DIGITS: <%= Constants.NUMBER_OF_DIGITS %> <br />
			6.-Constants.SMTP_SERVER: <%= Constants.SMTP_SERVER %> <br />
			7.-Constants.SMTP_FROM: <%= Constants.SMTP_FROM %> <br />
			8.-Constants.SMTP_BCC: <%= Constants.SMTP_BCC %> <br />
			9.-Constants.SESSION_FACTORY: <%= Constants.SESSION_FACTORY %> <br />

			10.-Constants.ACCOUNT_RESERVATION_DAYS: <%= Constants.ACCOUNT_RESERVATION_DAYS %> <br />
			11.-Constants.ROLE_ADMINISTRADOR: <%= Constants.ROLE_ADMINISTRADOR %> <br />
			12.-Constants.ROLE_COMERCIAL: <%= Constants.ROLE_COMERCIAL %> <br />
			13.-Constants.ROLE_DISTRIBUIDOR: <%= Constants.ROLE_DISTRIBUIDOR %> <br />
			14.-Constants.ROLE_VENDEDOR: <%= Constants.ROLE_VENDEDOR %> <br />
			
			15.-Constants.SALE_STATUS_IN_PROGRESS: <%= Constants.SALE_STATUS_IN_PROGRESS %> <br />
			16.-Constants.SALE_STATUS_COMPLETED: <%= Constants.SALE_STATUS_COMPLETED %> <br />
			17.-Constants.SALE_STATUS_EXPIRED: <%= Constants.SALE_STATUS_EXPIRED %> <br />
			
			18.-Constants.TASK_INICIAL: <%= Constants.TASK_INICIAL %> <br />
			19.-Constants.TASK_LOST_ACCOUNT: <%= Constants.TASK_LOST_ACCOUNT %> <br />--%>
			<br /><br /><br />
<hr>
Contenido de Objeto tipo Properties cargado en contexto:<br />
ID:initParameters : <br><br>
<table cellspacing="0" cellpadding="2" border="1">
<tr class="header" align="left">
    <th  align="right">#&nbsp;</th>
    <th>PROPIEDAD&nbsp;</th>
    <th>VALOR&nbsp;</th>
</tr>

<% 
	if ( props != null ) {
	
		Enumeration enumeration = props.keys();
		String key = "";
		count = 0;
		while (enumeration.hasMoreElements() ) {
			key = (String) enumeration.nextElement();%>
			<tr>
				<td><%= ++count %>.-&nbsp;</td>
				<td><strong><%= key %></strong>:</td>
				<td><%=  props.get(key) %></td>
			</tr>
	
			<%--<%= ++count %>.-&nbsp;<%= key %>:&nbsp;<%= props.getProperty(key) %><br>	--%>			
	<%	} %>
<%	} %>	
</table>
CATALOGUE_PRODUCTS_FEATURED:${initParameters.CATALOGUE_PRODUCTS_FEATURED}
</body>
</html>
