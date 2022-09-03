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
            <li><a href="/app/dashboard" class="btn btn--without-border active">Panel Główny</a></li>
            <li><a href="/app/userStats" class="btn btn--without-border">Fundacje</a></li>
            <li><a href="/admin/login" class="btn btn--without-border">Kategorie</a></li>
            <li><a href="/app/list" class="btn btn--without-border">Wyloguj</a></li>
        </ul>
    </nav>
</header>