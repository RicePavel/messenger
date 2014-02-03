<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<security:authentication property="principal.username" var="login"/> 

<div style="magin:50px;">
  <a style="margin-right:20px;" href="<c:url value="/recentMessages" />">Последние сообщения</a>
  <a style="margin-right:20px;" href="<c:url value="/messages?login=${login}" />">Мои сообщения</a>
  <a style="margin-right:20px;" href="<c:url value="/addMessage" />" >Добавить сообщение</a>
  <a style="margin-right:20px;" href="<c:url value="/users" />" >Список пользователей</a>
  <a style="margin-right:20px;" href="<c:url value="/logout" />" >Выход</a>
</div>
