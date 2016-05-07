<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="${language}">
	<jsp:include page="fragments/htmlHeader.jsp"/>
	
	<body onload="setLanguage('${language}');">
		<fmt:setLocale value="${language}"/>
		<fmt:bundle basename="messages.messages">
		<customerproducts:bodyHeader menuName="success"/>

		<div class="container-fluid">
		    <div class="container xd-container">
				<div class="left">
					<div>
				        <spring:url value="/resources/images/customerproducts.png" var="cpImage"/>
				        <img src="${cpImage}"/>
			        </div>
		        </div>
		
				<div class="right">
			        <h2><fmt:message key="success"/></h2>
			
			        <p><fmt:message key="successMsg"/></p>
			  	</div>
		    </div>
		    <customerproducts:footer/>
		</div>

		<jsp:include page="fragments/footer.jsp"/>
		</fmt:bundle>
	</body>
</html>
