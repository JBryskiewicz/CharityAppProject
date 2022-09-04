<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

<link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul>
            <li><a href="/admin/dashboard" class="btn btn--without-border">Panel Administratora</a></li>
            <li><a href="/app/fundation-list" class="btn btn--without-border">Fundacje</a></li>
            <li><a href="/app/category-list" class="btn btn--without-border">Kategorie</a></li>
            <li><a href="/app/your-donations" class="btn btn--without-border">Twoje Paczki</a></li>
            <li><a href="/app/profile" class="btn btn--without-border">Profil</a></li>
            <li><a href="/logout" class="btn btn--without-border">Wyloguj</a></li>
        </ul>
    </nav>
</header>