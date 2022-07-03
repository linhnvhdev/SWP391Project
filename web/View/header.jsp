<div class="header">
    <nav>
        <div class="logo"><a style="text-decoration: none;" href="home">No Game No Learn</a></div>
        <ul class="navbar-item-list">
            <li><a href="${pageContext.request.contextPath}/course/library">Course Library</a></li>
            <li><a href="${pageContext.request.contextPath}/course/add">Create Course</a></li>
            <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
            <li><a id="btnInventory">Inventory</a></li>
           
            <li class="dropdown">
              
             <img class="avatar"src="${pageContext.request.contextPath}/img/char.png" alt=""/>  <i class="arrow down"></i>
                
                  
             
                <div class="drop-down">
                    <ul class="navbar-dropdown-item-list">
                        
                        <c:if test="${requestScope.user.role == 3}">
                            <li><a href="${pageContext.request.contextPath}/auth">Authorization</a></li>
                        </c:if>
                            <li><a href="${pageContext.request.contextPath}/profile">Edit Profile</a><li>
                        <li><a href="${pageContext.request.contextPath}/chgpwd">Change password</a></li>
                        <li><a href="${pageContext.request.contextPath}/login">Log out</a></li>
                        <li></li>
                    </ul>
                </div>
            </li>
        </ul>
    </nav>
</div>