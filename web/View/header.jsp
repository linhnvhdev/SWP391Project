<%@page import="Model.Account"%>
<%@page import="Model.User"%>
<div class="header">
    <nav>
        <a class="logo" href="${pageContext.request.contextPath}/home">No game no learn</a>
        <ul class="navbar-item-list">
            <% int role = ((Account) request.getSession().getAttribute("account")).getUser().getRole();
                if(role == 2){%>
                <li class="this-is-li"><a href="${pageContext.request.contextPath}/course/add">Create course</a></li>
            <%}%>
            <li><a href="${pageContext.request.contextPath}/community">Community</a></li>
            <li><a href="${pageContext.request.contextPath}/course/library">Course Library</a></li>
            <li><a href="#">About</a></li>
            <li class="dropdown">
                <a href="#">Setting</a>
                <div class="drop-down">
                    <ul class="navbar-dropdown-item-list">
                        <li><a href="${pageContext.request.contextPath}/profile">User profile</a></li>
                        <%if(role == 3){%>
                            <li><a href="${pageContext.request.contextPath}/auth">Authorization</a></li>
                        <%}%>
                        <li><a href="${pageContext.request.contextPath}/chgpwd">Change password</a></li>
                        <li><a href="${pageContext.request.contextPath}/login">Log out</a></li>
                        <li></li>
                    </ul>
                </div>
            </li>
        </ul>
    </nav>
</div>
