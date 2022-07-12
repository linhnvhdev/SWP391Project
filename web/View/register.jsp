<%-- 
    Document   : login
    Created on : May 12, 2022, 10:03:49 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="${pageContext.request.contextPath}/css/register.css?version=2" rel="stylesheet" type="text/css"/>


    </head>
    <body>
        <div id="tsparticles">  </div>
        <div class="login-box">
            <form action="register" onsubmit="return validateForm()" method="POST" id="registerForm">
                <div id="error" style="color: red">
                    <c:if test="${requestScope.errorMessage != null}">
                        <div style="color: red">${requestScope.errorMessage}</div>
                    </c:if>
                    <c:if test="${requestScope.successMessage != null}">
                        <div style="color: Green">${requestScope.successMessage}</div>
                    </c:if>
                </div>
                <div class="user-box">
                    <h3> Username:</h3><input type="text" name="username" required>
                    <h3> Password:</h3><input type="password" name="password" required>
                    <h3> Confirm Password:</h3> <input type="password" name="repassword" required>
                    <h3>Name:</h3> <input type="text" name="name" required>
                    <h3>Gender</h3>
                    <h4>Male: <input type="radio" class="radio" id="male" name="gender" value="True" checked></h4>
                    <h4>Female:<input type="radio" class="radio" id="female" name="gender" value="False"></h4>
                    <h3>Gmail: </h3><input type="gmail" name="gmail" required>
                    <h3>DOB:</h3><input type="date" name="dob" required>
                    <h3>Role:</h3> 
                    <div class="select-dropdown">
                        <select name="role">
                            <option value="1">Learner</option>
                            <option value="2">Course Creator</option>
                        </select> 
                    </div>
                </div>
                <div>
                    <a href="">
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        <input type="submit" value="Register"><br>
                    </a>
                    <a href="login">
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        <input type="button" value="Back to login"><br>
                    </a>
                </div>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/tsparticles@1.18.3/dist/tsparticles.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/backgroundparticles.js" type="text/javascript"></script>
        <script>
                const pass_regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})");
                const email_regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
                function validateForm() {
                    var errorDiv = document.getElementById("error");
                    var form = document.forms["registerForm"];
                    var password = form["password"].value;
                    var repassword = form["repassword"].value;
                    var gmail = form["gmail"].value;
                    
                    if(password !== repassword){
                        errorDiv.innerHTML = "Confirm password not match";
                        
                        return false;
                    }
                    if(!pass_regex.test(password)){
                        errorDiv.innerHTML = "Password must include at least 8 character"+
             "and include at least a lowercase character, uppercase character or number";
                        return false;
                    }
                    if(!email_regex.test(gmail)){
                        errorDiv.innerHTML = "Your email is in invalid form";
                        return false;
                    }
                    return true;
                }
        </script>
    </body>
</html>
