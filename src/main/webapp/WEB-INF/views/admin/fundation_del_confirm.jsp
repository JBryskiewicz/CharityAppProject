<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="admin_header.jsp" %>

<h2>Czy chcesz usunąć Fundacje : ${institution.name}?</h2>
</<br>
<h3><a href="/admin/fundation-del-result/${institution.id}">Potwierdź</a></h3>
<h3><a href="/admin/fundation-list">Anuluj</a></h3>

<%@include file="admin_footer.jsp" %>
