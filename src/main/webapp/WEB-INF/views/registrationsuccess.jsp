<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>Registration Confirmation Page</title>
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css'/>" rel="stylesheet"/>
</head>
<body>
    <div class="generic-container">
        <div class="alert alert-success lead">
            ${success}
        </div>

        <span class="well floatRight">
            Go to <a href="<c:url value='/list' />">Users List</a>
        </span>
    </div>
</body>
</html>
