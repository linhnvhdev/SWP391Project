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
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Gmail</td>
                <td>Role</td>
                <td>Status</td>
                <td col="2">Action</td>
            </tr>
            <c:forEach var="account" items="${requestScope.accountList}">
                <form action="auth" method="POST">
                    <input type="hidden" name="username" value="${account.username}">
                    <tr>
                        <td>${account.user.id}</td>
                        <td>${account.user.name}</td>
                        <td>${account.user.gmail}</td>
                        <td>${account.user.getRoleName()}</td>
                        <td>${account.getStatus()}</td>
                        <td>
                            <c:if test="${account.isActive() == null}">
                                <input type="submit" name="action" value="Approve">
                            </c:if>
                            <c:if test="${account.isActive() == true}">
                                <input type="submit" name="action" value="Inactive">
                            </c:if>
                            <c:if test="${account.isActive() == false}">
                                <input type="submit" name="action" value="Active">
                            </c:if>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </body>
</html>
