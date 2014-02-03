<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messenger пользователи</title>
  </head>
  <body>
    <h1>Список пользователей</h1>
  <c:if test="${! empty userList}">
    <table>
      <c:forEach items="${userList}" var="user">
        <tr>
          <td> <%-- <a href="<c:url value="/messages?login=${user.login}" />" > ${user.login} </a> --%> ${user.login} </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  </body>
</html>
