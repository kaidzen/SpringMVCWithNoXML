<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
    <div class="generic-container">
        <div class="panel panel-default">
            <!- Panel contents ->
            <div class="panel-heading"><span class="lead">Lists of Users </span></div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>SSO</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.ssoId}</td>
                        <td><a href="<c:url value='/edit-user-${user.ssoId}'/>" class="btn btn-success custom-width">edit</a></td>
                        <td><a href="<c:url value='/delete-user-${user.ssoId}'/>" class="btn btn-danger custom-width">delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="well">
            <a href="<c:url value='/newuser' />">Add New User</a>
        </div>
    </div>
</body>
</html>
