<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<script language="javascript">
function changeName(selectObj, selectTarget, arrayList, search) { 
	var frm = selectObj.form; 
	var trg = frm.elements[selectTarget]; 
	var optionList = new Array(); 
	var index = 0; 
	if (search == true) { 
		var opt = new Array(); 
		opt[0] = ''; 
		opt[1] = '[<bean:message key="option.all" bundle="messages"/>]'; 
		optionList[index] = opt; 
		index ++; 
	} 
	for (var i=0; i<arrayList.length; i++) { 
		if(arrayList[i] != 'All'){
			var opt = new Array();
			opt[0] = arrayList[i];
			opt[1] = arrayList[i];
			optionList[index] = opt;
			index ++; 
		}
	}
	
	trg.length = index-1;
	trg.options[0].value = optionList[0][0];
	trg.options[0].text = optionList[0][1];
	for (var i=1; i<index-1; i++) {
			var optName;
			var indexName;
			trg.options[i].value = optionList[i][0];
			optName = optionList[i][1].substring(18);
			indexName = optName.indexOf('.');
			if(indexName == -1){
				trg.options[i].text = optName;
			}else{
				trg.options[i].text = optName.substring(indexName+1);
			}
	}
	trg.selectedIndex = 0;
}

var classNameList = [ 
	<c:forEach var="item" items="${classList}"> 
		 '<c:out value="${item.name}" />',
	 </c:forEach> 
	 'All',
];
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="audit.adminAudit" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="audit" bundle="systemMenu" /> >
			<bean:message key="audit.adminAudit" bundle="systemMenu" />
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
			<html:form action="/system/admin/sysAudit" focus="userName">
			<html:hidden property="action" value="list" />
			<html:hidden property="search" value="true" />
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
								<td width="220">
									<bean:message key="prompt.username" /><br>
									<html:text property="userName" styleClass="input" style="width: 200px;" onfocus="changeName(this, 'className', classNameList, true);"/>
								</td>
								<td width="150">
									<bean:message key="prompt.from" /><br>
									<html:text property="fromDate" styleId="fromDate" style="width: 100px;" readonly="true"/> <input type="button" name="dateBtn" id="dateBtn" value="..." style="width: 20px;">
								</td>
								<td width="150">
									<bean:message key="prompt.to" /><br>
									<html:text property="toDate" styleId="toDate" style="width: 100px;" readonly="true"/> <input type="button" name="dateBtn2" id="dateBtn2" value="..." style="width: 20px;">
								</td>	
								<td><bean:message key="prompt.action" /><br>
										<html:select property="actionString" size="1" styleClass="input" style="width: 220px;">
										<html:option value="">[<bean:message key="option.all" bundle="messages" />]</html:option>
										<html:option value= "insert"><bean:message key="label.insert" bundle="messages" /></html:option>
										<html:option value= "update"><bean:message key="label.update" bundle="messages" /></html:option>
										<html:option value= "delete"><bean:message key="label.delete" bundle="messages" /></html:option>
									</html:select>  
								</td>
							</tr>
						</table>	
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>	
								<td width="200">
								    <bean:message key="prompt.class" /><br>
									<html:select property="className" size="1" styleClass="input" style="width: 200px;">
										<html:option value="">[<bean:message key="option.all" bundle="messages" />]</html:option>
									</html:select>  
								</td>
								<td width="10"></td>
								<td><br><html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit></td>
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
			<%--<table cellpadding="0" cellspacing="0" width="100%">
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
									<html:option value="isEnabled"><bean:message key="label.status" bundle="messages" /></html:option>
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
			</table>--%>
			</html:form>
		</td>
	</tr>
</table>
<!--fin buscador-->
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>


<html:form action="/system/admin/sysAudit">
<html:hidden property="action" value="" />
<%--<table border="0" cellpadding="0" cellspacing="0" width="100%">
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
--%>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
	<tr>
		<th width="4%">#</th>
<!--		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th> -->
		<th width="19%"><bean:message key="label.user" bundle="messages" /></th>
		<th width="19%"><bean:message key="label.role" bundle="messages" /></th>
		<th width="19%"><bean:message key="label.date" bundle="messages" /></th>
		<th width="10%"><bean:message key="label.action" bundle="messages" /></th>
		<th width="10%"><bean:message key="label.service.function" bundle="messages" /></th>
		<th width="19%"><bean:message key="label.name" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${sysAuditList}" varStatus="status">
		<c:url value="/system/admin/sysAudit.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<%--
			<td><a href="${urlRead}" class="item">${item.log.userName}</a></td>
			<td>${item.log.roleName}</td>
			<td><fmt:formatDate value="${item.date.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></td>
			<td>${item.action}</td>
			<td>${item.className}</td>
			--%>
			<td><a href="${urlRead}" class="item">${item.user}</a></td>
			<td>${item.role}</td>
			<td><fmt:formatDate value="${item.date.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></td>
			<td>${item.action}</td>
			<td>
				<bean:message key="${item.nameValue}"/>
				<%--
				<c:if test="${!empty item.level}">
					<c:if test="${item.level == 1}">
						- <bean:message key="prompt.section"/>
					</c:if>
					<c:if test="${item.level == 2}">
					   -  <bean:message key="prompt.category"/>
					</c:if>
					<c:if test="${item.level == 3}">
					   -  <bean:message key="prompt.contentItem"/>
					</c:if>
				</c:if>
				--%>
			</td>
			<td>${item.entityName}</td>
			
		</tr>
	</c:forEach>
</table>
</html:form>

<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	:"fromDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "dateBtn"
		}
	);
</script>
<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	:"toDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "dateBtn2"
		}
	);
</script>