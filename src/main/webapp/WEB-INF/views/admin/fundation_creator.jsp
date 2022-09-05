<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="admin_header.jsp" %>

<form:form modelAttribute="institution" action="/admin/fundation-creator-result" method="post">

<div class="form-group">
    <form:input path="name" placeholder="Nazwa Fundacji"/>
    <form:errors path="name" cssClass="error" cssStyle="color: #bc1212"/>
</div>
<div class="form-group">
    <form:input path="description" type="textarea" placeholder="Opis Fundacji"/>
    <form:errors path="description" cssClass="error" cssStyle="color: #bc1212"/>
</div>

    <button class="btn" type="submit">Utwórz</button>

</form:form>
<%@include file="admin_footer.jsp" %>