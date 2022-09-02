<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="header.jsp" %>

<section class="login-page">
    <h2>Załóż konto</h2>
        <form:form modelAttribute="user" action="/signup-result" method="post">
        <div class="form-group">
            <form:input path="userName" placeholder="Nazwa Użytkowinka"/>
            <form:errors path="userName" cssClass="error" cssStyle="color: #bc1212"/>
        </div>
        <div class="form-group">
            <form:input path="email" type="email" placeholder="name@domain.com"/>
            <form:errors path="email" cssClass="error" cssStyle="color: #bc1212"/>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło"/>
            <form:errors path="password" cssClass="error" cssStyle="color: #bc1212"/>
        </div>
<%--        <div class="form-group">--%>
<%--            <input type="password" name="password2" placeholder="Powtórz hasło" />--%>
<%--        </div>--%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@include file="footer.jsp" %>