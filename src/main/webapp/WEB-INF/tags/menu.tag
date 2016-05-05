<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>

<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of the active menu: home, customers, or products" %>

<%-- Static navbar --%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${name eq 'home'}">
                        <c:set var="cssMenu" value="active"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="cssMenu" value=""/>
                    </c:otherwise>
                </c:choose>
                <li class="${cssMenu}"><a href="<spring:url value="/" htmlEscape="true" />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><span> <fmt:message key="menuHome"/></span></a></li>
                <c:choose>
                    <c:when test="${name eq 'customers'}">
                        <c:set var="cssMenu" value="active"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="cssMenu" value=""/>
                    </c:otherwise>
                </c:choose>
                <li class="${cssMenu}"><a href="<spring:url value="/customers" htmlEscape="true" />"><span class="glyphicon glyphicon-search" aria-hidden="true"></span><span> <fmt:message key="menuCustomers"/></span></a></li>
                <c:choose>
                    <c:when test="${name eq 'products'}">
                        <c:set var="cssMenu" value="active"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="cssMenu" value=""/>
                    </c:otherwise>
                </c:choose>
                <li class="${cssMenu}"><a href="<spring:url value="/catalogue" htmlEscape="true" />"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span> <fmt:message key="menuProducts"/></span></a></li>
            </ul>
        </div> <%--/.nav-collapse --%>
    </div> <%--/.container-fluid --%>
</nav>
