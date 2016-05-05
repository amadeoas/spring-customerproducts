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

<html lang="${language}">

	<jsp:include page="../fragments/htmlHeader.jsp"/>

	<!-- This need angular -->
	<body ng-app="myApp">
		<customerproducts:bodyHeader menuName="products"/>
		<div class="container-fluid">
		    <div id="selectionsController" ng-controller="selectionsController" class="container xd-container" ng-init="getBasket(${basket.customer.id})">
		        <h2><fmt:message key="subscriptionsAvaliable"/>${basket.customer.firstName} ${basket.customer.lastName}</h2>
		
				<c:forEach items="${categories}" var="category">
			        <div id="${category.category}" class="line_layout">
					    <datatables:table id="${category.category}_tb" data="${category.products}" row="product" cssClass="table table-striped"
			                          pageable="false" info="false" filterable="false" sortable="false">
				            <datatables:column title="${category.category}">
							    <input type="checkbox" id="${product.id}" value="${product.id}" ng-click="change(${product.id}, '${category.category}', '${product.name}')" ng-checked="isChecked(${product.id})" /> <label for="${product.id}">${product.name}</label>
				            </datatables:column>
				       	</datatables:table>
			    	</div>
		        </c:forEach>
			    <div id="Basket" class="line_layout">
				    <table id="Basket_tb" class="table table-striped">
			            <thead>
			                <tr><th><fmt:message key="basket"/></th></tr>
			            </thead>
						<tbody>
			                <tr ng-repeat="product in basket.products | orderBy:sortingOrder:reverse">
			                    <td><label>- {{product.name}}</label></td>
			                </tr>
	                	</tbody>
	                </table>
	                <div id="footer" class="footer">
						<button type="submit" form="" value="Checkout" ng-click="sendPost(${basket.customer.id}); $event.stopPropagation();" ng-disabled="!basket.hasChanged" class="btn_bottom"><fmt:message key="checkout"/></button>
	               	</div>
			    </div>
		
		        <customerproducts:footer/>

//				<script>
//					getBasket("${basket.customer.id}");
//				</script>
		    </div>
		</div>
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
</html>
