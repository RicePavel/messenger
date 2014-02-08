<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messenger сообщения</title>
  </head>
  <body>

  <%@ include file="/WEB-INF/jsp/menu.jsp" %>
  
  <h1>Последние сообщения</h1>
    
  <%@ include file="/WEB-INF/jsp/messagesTable.jsp" %>
  
</body>
</html>
