<!DOCTYPE html>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.aas.samples.customerproducts.model.CatalogueCategory"%>
<%@page import="com.aas.samples.customerproducts.model.Product"%>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="en">

	<jsp:include page="../fragments/htmlHeader.jsp"/>

	<!-- This need angular -->
	<body ng-app="myApp">
		<customerproducts:bodyHeader menuName="products"/>
		<div class="container-fluid">
		    <div ng-controller="selectionsController" class="container xd-container">
		        <h2>Subscriptions available for ${basket.customer.firstName} ${basket.customer.lastName}</h2>
		
				<c:forEach items="${categories}" var="category">
			        <div id="${category.category}" class="line_layout">
					    <datatables:table id="${category.category}_tb" data="${category.products}" row="product" cssClass="table table-striped"
			                          pageable="false" info="false" filterable="false" sortable="false">
				            <datatables:column title="${category.category}">
							    <input type="checkbox" id="${product.id}" value="${product.id}" ng-click="change(${product.id}, '${category.category}', '${product.name}')" /> <label for="${product.id}">${product.name}</label>
				            </datatables:column>
				       	</datatables:table>
			    	</div>
		        </c:forEach>
			    <div id="${basket.category}" class="line_layout">
				    <table id="${basket.category}_tb" class="table table-striped">
			            <thead>
			                <tr><th><c:out value="${basket.category}" /></th></tr>
			            </thead>
						<tbody>
			                <tr ng-repeat="product in basket.products | orderBy:sortingOrder:reverse">
			                    <td><label>- {{product.name}}</label></td>
			                </tr>
	                	</tbody>
	                </table>
	                <div id="footer" class="footer">
						<button type="submit" form="" value="Checkout" ng-click="sendPost(${basket.customer.id}); $event.stopPropagation();" ng-disabled="basket.products.length == 0" class="btn_bottom">Checkout</button>
	               	</div>
			    </div>
		
		        <customerproducts:footer/>
		    </div>
		</div>
		<jsp:include page="../fragments/footer.jsp"/>

		<script>
			<!-- Clean preset selections -->
			reset();
		</script>
	</body>
</html>
