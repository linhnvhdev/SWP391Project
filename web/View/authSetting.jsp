<%-- 
    Document   : authSetting
    Created on : May 30, 2022, 8:42:00 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/header.css?version=2" rel="stylesheet" type="text/css"/>
        <link href="css/authSetting.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="main-content">
            <h2>Authorization Settings</h2>
            <div class="search">
                <form id="searchForm" action="auth" method="POST">
                    <div class="search">
                        Id: <input type="text" name="searchId" value="${requestScope.searchId}">
                        Username <input type="text" name="searchUsername" value="${requestScope.searchUsername}">
                        Name: <input type="text" name="searchName" value="${requestScope.searchName}">
                        Role: <select name="searchRole">
                                <option value="All" selected>All</option>
                                <c:forEach var="role" items="${requestScope.roleList}">
                                    <option value="${role}" ${role.equals(requestScope.searchRole)?"selected":""}>
                                        ${role}
                                    </option>
                                </c:forEach>
                        </select>
                        Status: <select name="searchStatus">
                            <option value="-1" selected>All</option>
                            <option value="2" ${requestScope.searchStatus.equals("2")?"selected":""}>Pending</option>
                            <option value="1" ${requestScope.searchStatus.equals("1")?"selected":""}>Active</option>
                            <option value="0" ${requestScope.searchStatus.equals("0")?"selected":""}>Inactive</option>
                        </select>
                        <input class="search-button" type="submit" name="isSearch" value="Search">
                        <input class="search-button" type="submit" name="isSearch" value="Reset">
                    </div>
                </form>
            </div>
            <div class="auth-table">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Gmail</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th col="2">Action</th>
                    </tr>
                    <c:forEach var="account" items="${requestScope.accountList}">
                        <form action="auth" method="POST">
                            <input type="hidden" name="isSearch" value="Search">
                            <input type="hidden" name="searchId" value="${requestScope.searchId}">
                            <input type="hidden" name="searchName" value="${requestScope.searchName}">
                            <input type="hidden" name="searchRole" value="${requestScope.searchRole}">
                            <input type="hidden" name="searchStatus" value="${requestScope.searchStatus}">
                            <input type="hidden" name="searchUsername" value="${requestScope.searchUsername}">
                            <input type="hidden" name="username" value="${account.username}">
                            <tr>
                                <td>${account.user.id}</td>
                                <td>${account.username}</td>
                                <td>${account.user.name}</td>
                                <td>${account.user.gmail}</td>
                                <td>
                                    <select name="role" onchange="this.form.submit()">
                                        <c:forEach var="role" items="${requestScope.roleList}">
                                            <option value="${role}" ${role.equals(account.user.getRoleName())?"selected":""}>
                                                ${role}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>${account.getStatus()}</td>
                                <td>
                                    <c:if test="${account.isActive() == null}">
                                        <input class="approve" type="submit" name="action" value="Approve">
                                    </c:if>
                                    <c:if test="${account.isActive() == true}">
                                        <input class="inactive" type="submit" name="action" value="Inactive">
                                    </c:if>
                                    <c:if test="${account.isActive() == false}">
                                        <input class="active" type="submit" name="action" value="Active">
                                    </c:if>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>
            </div>    
        </div>
        <%@ include file="inventory.jsp" %>
    </body>
</html>
