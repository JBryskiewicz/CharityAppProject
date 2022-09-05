<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="admin_header.jsp" %>

<form:form modelAttribute="institution" action="/admin/fundation-edit-result" method="post">

<div class="form-group">
    <form:input path="name" value="${institution.name}" placeholder="Nazwa Fundacji"/>
    <form:errors path="name" cssClass="error" cssStyle="color: #bc1212"/>
</div>
<div class="form-group">
    <form:input path="description" type="textarea" value="${institution.description}" placeholder="Opis Fundacji"/>
    <form:errors path="description" cssClass="error" cssStyle="color: #bc1212"/>
</div>
    <form:input path="id" type="hidden" value="${institution.id}"/>
    <button class="btn" type="submit">Edytuj</button>

</form:form>
<%@include file="admin_footer.jsp" %>