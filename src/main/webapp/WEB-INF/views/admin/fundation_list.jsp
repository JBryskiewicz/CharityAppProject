<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="admin_header.jsp" %>
<h2><a href="/admin/fundation-creator">Dodaj fundacje</a></h2>
<ul>
    <c:forEach items="${institution}" var="inst">
        <li><h2>
            ${inst.name} -
            <a href="/admin/fundation-details/${inst.id}">Szczegóły</a>
                <a> | </a>
            <a href="/admin/fundation-edit/${inst.id}">Edytuj</a>
                <a> | </a>
            <a href="/admin/fundation-delete/${inst.id}">Usuń</a>
            </h2>
        </li>
    </c:forEach>
</ul>

<%@include file="admin_footer.jsp" %>