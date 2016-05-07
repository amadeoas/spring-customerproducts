<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="${language}">
	<fmt:setLocale value="${language}"/>
	<fmt:bundle basename="messages.messages">
	<c:set var="msg_category" scope="request">
		<fmt:message key="tbCategory"/>
	</c:set>
	<c:set var="msg_name" scope="request">
		<fmt:message key="tbName"/>
	</c:set>
	<c:set var="msg_location" scope="request">
		<fmt:message key="tbLocation"/>
	</c:set>
	<c:set var="msg_from" scope="request">
		<fmt:message key="timestamp"/>
	</c:set>
	<jsp:include page="../fragments/htmlHeader.jsp"/>

	<!-- This need angular -->
	<body onload="setLanguage('${language}');">
		<customerproducts:bodyHeader menuName="subscriptions"/>

		<div class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="currentSubscriptions"/> ${subscriptions.customer.firstName} ${subscriptions.customer.lastName}</h2>

        		<datatables:table id="products" data="${subscriptions.products}" row="sp" cssClass="table table-striped"
                          pageable="false" info="false">
		            <datatables:column title="${msg_category}">
			            <c:out value="${sp.product.category.name}"/>
			        </datatables:column>
			        <datatables:column title="${msg_name}">
			            <c:out value="${sp.product.name}"/>
			        </datatables:column>
		            <datatables:column title="${msg_location}">
		                <c:out value="${sp.product.location.name}"/>
		            </datatables:column>
		            <datatables:column title="${msg_from}">
		                <c:out value="${sp.created}"/>
		            </datatables:column>
			    </datatables:table>

		        <customerproducts:footer/>
		    </div>
		</div>

		<jsp:include page="../fragments/footer.jsp"/>
	</body>
	</fmt:bundle>
</html>
