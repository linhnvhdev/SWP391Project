<%-- 
    Document   : effect
    Created on : Jul 24, 2022, 4:35:21 PM
    Author     : Bi
--%>

<div class="effect">
   
        <c:if test="${sessionScope.expBoost != null}">
            
            <img class="effect-img" src="${pageContext.request.contextPath}/img/upgrade.png" alt=""/>
            <div class="hide" style="font-weight: bold;">x2 Exp</div>
        </c:if>
        <c:if test="${sessionScope.sales != null}">
            
            <img class="effect-img" src="${pageContext.request.contextPath}/img/shopping-cart.png" alt=""/>
           <div class="hide" style="font-weight: bold;">50% shop price</div>
        </c:if>
    
</div>