<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messenger сообщения</title>
  </head>
  <body>
    <h1>Просмотр сообщений</h1>
  <c:if test="${! empty messages}">
    <table>
      <tr>
        <th>ИД пользователя</th>
        <th>Сообщение</th>
      </tr>
      <c:forEach items="messages" var="message">
        <tr>
          <td>${message.userId}</td>
          <td>${message.text}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  </body>
</html>
