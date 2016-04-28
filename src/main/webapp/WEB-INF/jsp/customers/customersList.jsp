<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="customerproducts" tagdir="/WEB-INF/tags" %>

<html lang="en">

<jsp:include page="../fragments/htmlHeader.jsp"/>

<body>
<customerproducts:bodyHeader menuName="customers"/>
<div class="container-fluid">
    <div class="container xd-container">
        <h2>Customers</h2>

        <datatables:table id="customers" data="${customers}" row="customer"
                          cssClass="table table-striped" pageable="false" info="false">
            <datatables:column title="Name">
                <a href="/customerproducts/catalogue/${customer.id}"><c:out value="${customer.firstName} ${customer.lastName}"/></a>
            </datatables:column>
            <datatables:column title="Location" property="location.name"/>
        </datatables:table>

        <customerproducts:footer/>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
