<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messenger Регистрация</title>
  </head>
  <body>
    <h1>Зарегистрируйтесь!</h1>

    <c:if test="${!empty error}" >
      <div style="color: red;">${error}</div>
    </c:if>
    
    <form method="POST" action="<c:url value="/registration" />" >
      <table>
        <tr>
          <td align="right">Логин:</td>
          <td><input type="text" name="login" 
                     value="<% if (request.getParameter("login") != null) { out.print(request.getParameter("login")); } %>" 
                     /></td>
        </tr>
        <tr>
          <td align="right">Пароль:</td>
          <td><input type="password" name="password" /></td>
        </tr>
        <tr>
          <td colspan="2" align="right"><input type="submit" value="Регистрация" />
            <input type="reset" value="Reset" /></td>
        </tr>
      </table>
      <input type="hidden" name="submit" value="1" />
    </form>
  </body>
</html>
