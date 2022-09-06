<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="admin_header.jsp" %>

<form:form modelAttribute="user" action="/admin/user-edit-result" method="post">

<div class="form-group">
    <form:input path="userName" value="${user.userName}" placeholder="Nazwa Użytkownika"/>
    <form:errors path="userName" cssClass="error" cssStyle="color: #bc1212"/>
</div>
<div class="form-group">
    <form:input path="email" value="${user.email}" placeholder="Email"/>
    <form:errors path="email" cssClass="error" cssStyle="color: #bc1212"/>
</div>
    <div class="form-group">
        <form:input path="password" value="${user.password}" placeholder="Hasło"/>
        <form:errors path="password" cssClass="error" cssStyle="color: #bc1212"/>
    </div>
    <form:input path="id" type="hidden" value="${user.id}"/>
    <button class="btn" type="submit">Edytuj</button>

</form:form>
<%@include file="admin_footer.jsp" %>