<div class="header">
    <nav>
        <div class="logo">No game no learn</div>
        <ul class="navbar-item-list">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
            <li><a id="btnInventory">Inventory</a></li>
            <li><a href="#">About</a></li>
            <li><a href="${pageContext.request.contextPath}/course/library">Course Library</a></li>
            <li class="dropdown">
                <a href="#">Setting</a>
                <div class="drop-down">
                    <ul class="navbar-dropdown-item-list">
                        <li><a href="${pageContext.request.contextPath}/profile">User profile</a></li>
                        <c:if test="${requestScope.user.role == 3}">
                            <li><a href="${pageContext.request.contextPath}/auth">Authorization</a></li>
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/chgpwd">Change password</a></li>
                        <li><a href="${pageContext.request.contextPath}/login">Log out</a></li>
                        <li></li>
                    </ul>
                </div>
            </li>
        </ul>
    </nav>
</div>