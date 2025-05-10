<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mailer2" prefix="mt" %>

    <c:set var="mSubject">Nueva Cuenta de Usuario - Sistema de Administración CompraDeUna.com</c:set>
    <mt:mail type="text/html" subject="${mSubject}" server="${initParameters.SMTP_SERVER}" from="${initParameters.SMTP_FROM}" username="${initParameters.SMTP_USER}" password="${initParameters.SMTP_PASSWORD}" background="false">
    <mt:addrecipient type="to" name="${newUser.fullName}">${newUser.email}</mt:addrecipient>
    <html>
        <head>
          <title>Nueva Cuenta de Usuario - Sistema de Administraci&oacute;n CompraDeUna.com</title>
        </head>
        <body>
            <jsp:include flush="true" page="/jsp/common/mailHeader.jsp" />
            <p>Estimado/a ${newUser.fullName}:</p>
            
            <p>Su cuenta de usuario ha sido creada satisfactoriamente el ${date}, 
              detallamos a continuaci&oacute;n los datos de su cuenta de usuario para acceder al sistema 
              de administraci&oacute;n (CMS) de CompraDeUna.com:</p>
              
            <table border="0" cellpadding="0" cellspacing="0" width="600" align="center">
              <tr>
                <td>Cuenta Usuario:</td>
                <td>${newUser.name}</td>
              </tr>
              <tr>
                <td>Rol de usuario asignado:</td>
                <td>${newUser.role.name}</td>
              </tr>
              <tr>
                <td>Clave de acceso:</td>
                <td>${newPassword}</td>
              </tr>
            </table>
            <!-- tiles:insert definition="footer.mail"/ -->
            <jsp:include flush="true" page="/jsp/common/mailFooter.jsp" />
        </body>
    </html>  
    </mt:mail>
    <mt:mail type="text/html" subject="${mSubject}" server="${initParameters.SMTP_SERVER}" from="${initParameters.SMTP_FROM}" username="${initParameters.SMTP_USER}" password="${initParameters.SMTP_PASSWORD}" background="false">
    <mt:addrecipient type="to" name="${initParameters.SMTP_FROM}">${initParameters.SMTP_FROM}</mt:addrecipient>
    <html>
        <head>
          <title>Nueva Cuenta de Usuario - Sistema de Administraci&oacute;n CompraDeUna.com</title>
        </head>
        <body>
            <jsp:include flush="true" page="/jsp/common/mailHeader.jsp" />
            <p>Estimado/a Administrador/a:</p>
            
            <p>Le informamos que la cuenta de usuario ${newUser.name} con rol ${newUser.role.name} ha sido 
                creada el ${date}.</p>

            <p>En caso de que esta cuenta de usuario no haya sido autorizada, por favor ingresar al sistema 
                de administraci&oacute;n y deshabilitar la misma.</p>
              
            <jsp:include flush="true" page="/jsp/common/mailFooter.jsp" />
        </body>
    </html>  
    </mt:mail>
    