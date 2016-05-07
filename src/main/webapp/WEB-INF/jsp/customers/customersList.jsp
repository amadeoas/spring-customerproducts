<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="${language}">
	<fmt:setLocale value="${language}"/>
	<fmt:bundle basename="messages.messages">
	<c:set var="msg_name" scope="request">
		<fmt:message key="tbName"/>
	</c:set>
	<c:set var="msg_location" scope="request">
		<fmt:message key="tbLocation"/>
	</c:set>
	<c:set var="msg_actions" scope="request">
		<fmt:message key="tbActions"/>
	</c:set>
	<c:set var="msg_viewSubsc" scope="request">
		<fmt:message key="viewSubsc"/>
	</c:set>
	<c:set var="msg_changeSubsc" scope="request">
		<fmt:message key="changeSubsc"/>
	</c:set>
	<c:set var="msg_search" scope="request">
		<fmt:message key="search"/>
	</c:set>
	<jsp:include page="../fragments/htmlHeader.jsp"/>
	
	<body onload="setLanguage('${language}', '${msg_search}', 'customers');">
		<customerproducts:bodyHeader menuName="customers"/>

		<div class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="customers"/></h2>
		
		        <datatables:table id="customers" data="${customers}" row="customer"
		                          cssClass="table table-striped" pageable="false" info="false">
		            <datatables:column title="${msg_name}">
		                <c:out value="${customer.firstName} ${customer.lastName}"/>
		            </datatables:column>
		            <datatables:column title="${msg_location}" property="location.name"/>
		            <datatables:column title="${msg_actions}">
		            	<a href="/customerproducts/subscriptions/view/${customer.id}" title="${msg_viewSubsc}"><span class="glyphicon glyphicon-th-list icon-blue"></span></a> <a href="/customerproducts/catalogue/${customer.id}" title="${msg_changeSubsc}"><span class="glyphicon glyphicon-pencil icon-blue"></span></a>
		            </datatables:column>
		        </datatables:table>
		
		        <customerproducts:footer/>
		    </div>
		</div>
		
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
	</fmt:bundle>
</html>
