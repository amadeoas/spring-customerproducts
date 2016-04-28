<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="en">
<jsp:include page="fragments/htmlHeader.jsp"/>

<body>
<customerproducts:bodyHeader menuName="error"/>
<div class="container-fluid">
    <div class="container xd-container">
		<div class="left">
			<div>
		        <spring:url value="/resources/images/customerproducts.png" var="cpImage"/>
		        <img src="${cpImage}"/>
	        </div>
        </div>

		<div class="right">
	        <h2>Success</h2>
	
	        <p>The selected products were received and processed.</p>
	  	</div>
    </div>
    <customerproducts:footer/>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>

</html>
