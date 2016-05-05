<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="${language}">

	<jsp:include page="../fragments/htmlHeader.jsp"/>

	<!-- This need angular -->
	<body>
		<customerproducts:bodyHeader menuName="products"/>
		<div class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="currentSubscriptions"/> ${subscriptions.customer.firstName} ${subscriptions.customer.lastName}</h2>
						
        		<datatables:table id="products" data="${subscriptions.products}" row="sp" cssClass="table table-striped"
                          pageable="false" info="false">
		            <datatables:column title="Category">
			            <c:out value="${sp.product.category.name}"/>
			        </datatables:column>
			        <datatables:column title="Name">
			            <c:out value="${sp.product.name}"/>
			        </datatables:column>
		            <datatables:column title="Location">
		                <c:out value="${sp.product.location.name}"/>
		            </datatables:column>
		            <datatables:column title="From">
		                <c:out value="${sp.created}"/>
		            </datatables:column>
			    </datatables:table>
		
		        <customerproducts:footer/>
		    </div>
		</div>
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
</html>
