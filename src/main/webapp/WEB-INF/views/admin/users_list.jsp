<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="admin_header.jsp" %>

<div class="admin-row">
    <div><h2>Lista Administratorów</h2></div>
    <div>
        <c:forEach items="${admins}" var="a">
           <h3 style="font-size: 20px; text-align: center">
                Nazwa: ${a.userName}   |  email: ${a.email}
            </h3>
            <h3 style="font-size: 16px; text-align: center">
                <a href="/admin/user-details/${a.id}">Szczegóły</a>
                <a> | </a>
                <a href="/admin/user-edit/${a.id}">Edytuj</a>
                <a> | </a>
                <a href="/admin/user-del/${a.id}">Usuń</a>
                <a> | </a>
                <a href="/admin/admin-user/${a.id}">Admin OFF</a>
            </h3>
            <h3></br></h3>
        </c:forEach>
    </div>
</div>

<div class="user-row">
    <div><h2>Lista Użytkowników</h2></div>
    <div>
        <c:forEach items="${users}" var="u">
            <h3 style="font-size: 20px; text-align: center">
                Nazwa: ${u.userName}  |  email: ${u.email}
            </h3>
            <h3 style="font-size: 16px; text-align: center">
                <a href="/admin/user-details/${u.id}">Szczegóły</a>
                <a> | </a>
                <a href="/admin/user-edit/${u.id}">Edytuj</a>
                <a> | </a>
                <a href="/admin/user-del/${u.id}">Usuń</a>
                <a> | </a>
                <a href="/admin/ban-user/${u.id}">Zablokuj</a>
                <a> | </a>
                <a href="/admin/user-admin/${u.id}">Admin ON</a>
            </h3>
            <h3></br></h3>
        </c:forEach>
    </div>
</div>
<div class="ban-row">
    <div><h2>Lista Zablokowanych Użytkowników</h2></div>
    <div>
        <c:forEach items="${bannedUsers}" var="bu">
            <h3 style="font-size: 20px; text-align: center">
                Nazwa: ${bu.userName}  |  email: ${bu.email}
            </h3>
            <h3 style="font-size: 16px; text-align: center">
                <a href="/admin/user-details/${bu.id}">Szczegóły</a>
                <a> | </a>
                <a href="/admin/user-edit/${bu.id}">Edytuj</a>
                <a> | </a>
                <a href="/admin/user-del/${bu.id}">Usuń</a>
                <a> | </a>
                <a href="/admin/unban-user/${bu.id}">Odblokuj</a>
            </h3>
            <h3></br></h3>
        </c:forEach>
    </div>
</div>


<%@include file="admin_footer.jsp" %>
