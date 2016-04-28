<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="fragments/htmlHeader.jsp"/>

<body>
<customerproducts:bodyHeader menuName="home"/>

<div class="container-fluid">
    <div class="container xd-container">
        <h2><fmt:message key="welcome"/></h2>
        <div class="row">
            <div class="col-md-12 left">
                <spring:url value="/resources/images/customerproducts.png" htmlEscape="true" var="petsImage"/>
                <img class="img-responsive" src="${petsImage}"/>
            </div>

			<div class="right">
		        <p>This page is the introduction to this simple Customer Products web app.</p>
		        <br />
		        <h2>How to use this simple tool</h2>
		        <ul>
		        	<li>Press the 'Customers' from the menu above, which will show the list of all the customers avaliable.</li>
		        	<li>From the list of all the available customers select one by clicking on its name.</li>
		        	<li>The customer's products view is displayed. Select the required products.
			        	<ul>
			        		<li>Note that all the selected products are displayed in the last column.</li>
			        	</ul>
		        	</li>
		        	<li>Once all the required products have been selected press the 'Checkout' button.</li>
		        </ul>
		  	</div>
        </div>
    </div>
	<customerproducts:footer/>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>

</html>
