<%-- 
    Document   : notification
    Created on : Jul 21, 2022, 9:52:48 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Community</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
        <link href="css/header.css?version=4" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Notifications</h1>
        <form action="notification" method="Post" class="form">
            <input type="hidden" name="action" value="readAll">
            <input type="hidden" name="id" value="${notify.id}">
            <input type="submit" value="Mark all as read">
        </form>
        <table class="table mt-3">
            <tbody class="">
                <c:forEach items="${requestScope.notifications}" var="notify">
                    <tr class="${notify.isRead ? "table-active" : ""}">
                        <td>${notify.date}</td>
                        <td>
                            <form action="notification" method="Post" class="form">
                                <input type="hidden" name="action" value="gotolink">
                                <input type="hidden" name="link" value="${notify.link}">
                                <input type="hidden" name="id" value="${notify.id}">
                                <a href="#" class="gotolink">${notify.description}</a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
    <script>
        $(document).ready(function () {
            console.log("script run at least?");
            $('.gotolink').on('click', function (event) {
                console.log("ok????");
                var form = $(this).parent();
                var link = form.find('input[name="link"]').attr("value");
                if (link !== "none") {
                    $(this).closest('form').submit();
                }
            });
        });
    </script>
</html>
