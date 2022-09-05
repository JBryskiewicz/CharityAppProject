<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="admin_header.jsp" %>

<h2>Nazwa Fundacji: ${institution.name}</h2>
<h3>Opis Fundacji: ${institution.description}</h3>
<h3>ID Fundacji: ${institution.id}</h3>

<%@include file="admin_footer.jsp" %>