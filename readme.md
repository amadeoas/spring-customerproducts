# Spring Customer Products Application

By Amadeo Asco, April 2016.

This is an implementation of a web app using Java and Spring that allows 
customers to select products which are available to them, based on their home 
location.


## Running customerproducts locally
``cd spring-customerproducts``
	
``./mvnw tomcat7:run``

You can then access customerproducts here: http://localhost:9966/customerproducts/

If the web app is started from within eclipse then access customerproducts 
here: http://localhost:8080/customerproducts/


## How to use customerproducts

There are three views accessible directly through the web app menu, which it is 
located at the top right hand side. The views are:

	1) 'Home'

	2) 'Customers'

	3) 'Products'

### 'Home' view
The 'Home' view give and introduction to the web app.

### 'Customers' view
The 'Customers' view list all the customer details in the data base. The 
customer can be sorted by any of the shown columns by clicking on the 
corresponding header. Each customer's full name can be clicked, which will 
bring up the product selection view for the customer with the products the 
customer can be subscribe to base on his/her location. This view allows 
selecting the desired products and submit them by clicking on the 'Checkout' 
button.

When the 'Checkout' button is pressed the selections are sent to the web server 
where they are printed out on the system out, and if this operation is 
successful the success page is displayed.

### 'Products' view
The 'Products' view list all the products in the database. The products can be 
sorted by any of the shown columns by clicking on the corresponding header.


## Overview
View: JSP with custom tags + bootstrap (CSS), Webjars and Dandelion

Controller: Sprint and MVC annotations with bean validation

Service: @Cacheable and @Transactional

Repository: 3 profiles - Sprint Data JPA, default (JPA) and jdbc

---

The Web Layer: Sprint MVC and third-party web libraries (Dandelion and Webjars).

	Dandelion: datatables (based on JQuery datatables and Bootstrap) with functionality to sort and filter
	
	Webjars: Allows CSS and JS libraries to be imported as Maven libraries (http://www.webjar.org/).

### Flow Diagram

<p align="center">
  <img alt="FD" src="docs/customerproducts_flowDiagram.png" width="400" title="Flow Diagram."/>
 </p>


## Database

<p align="center">
  <img alt="ERD" src="docs/customerproducts_erd.png" width="350" title="Entity Relation Diagram (ERD)."/>
 </p>

### Database configuration

In its default configuration, Customerproducts uses an in-memory database 
(HSQLDB) which gets populated at startup with data. A similar setup is provided 
for MySql in case a persistent database configuration is needed. Note that 
whenever the database type is changed, the data-access.properties file needs to 
be updated and the mysql-connector-java artifact from the pom.xml needs to be 
uncommented.

You may start a MySql database with docker:

``docker run -e MYSQL_ROOT_PASSWORD=customerproducts -e MYSQL_DATABASE=customerproducts -p 3306:3306 mysql:5.7.8``

## Working with Customerproducts in Eclipse/STS

### Prerequisites
The following items should be installed in your system:
* Maven 3

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/


### Steps:

1) In the command line
``copy project into your local storage``

2) Inside Eclipse
``File -> Import -> Maven -> Existing Maven project``


## Looking for something in particular?

<table border="1">
  <tr>
    <th width="300px">Java Config</th><th width="510px">Comments</th>
  </tr>
  <tr>
    <td>Java Config branch</td>
    <td>
      Customerproducts uses XML configuration by default. In case you'd like to 
      use Java Config instead.
    </td>
  </tr>
  <tr>
    <th width="300px">Inside the 'Web' layer</th><th width="300px">Files</th>
  </tr>
  <tr>
    <td>Spring MVC - XML integration</td>
    <td><a href="/src/main/resources/spring/mvc-view-config.xml">mvc-view-config.xml</a></td>
  </tr>
  <tr>
    <td>Spring MVC - ContentNegotiatingViewResolver</td>
    <td><a href="/src/main/resources/spring/mvc-view-config.xml">mvc-view-config.xml</a></td>
  </tr>
  <tr>
    <td>JSP custom tags</td>
    <td>
      <a href="/src/main/webapp/WEB-INF/tags">WEB-INF/tags</a>
    </td>
  </tr>
  <tr>
    <td>Bower</td>
    <td>
      Bower-install maven profile declaration inside <a href="/pom.xml">pom.xml</a> <br />
      JavaScript libraries are defined by the manifest file <a href="/bower.json">bower.json</a> <br />
      <a href="/.bowerrc">Bower configuration</a> using JSON <br />
      <a href="/src/main/resources/spring/mvc-core-config.xml#L30">Resource mapping</a> in Spring configuration <br />
	</td>
  </tr>
  <tr>
    <td>Dandelion-datatables</td>
    <td>
      <a href="/src/main/webapp/WEB-INF/jsp/customers/customersList.jsp">customersList.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/jsp/products/productList.jsp">productList.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/jsp/products/selectionList.jsp">selectionList.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/web.xml">web.xml</a> and 
      <a href="/src/main/resources/dandelion/datatables/datatables.properties">datatables.properties</a> 
   </td>
  </tr>
</table>
<br />
<table border="1">
  <tr>
    <th width="300px">'Service' and 'Repository' layers</th><th width="510px">Files</th>
  </tr>
  <tr>
    <td>Transactions</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>, 
      <a href="/src/main/java/org/springframework/samples/customerproducts/service/CatalogueServiceImpl.java">CatalogueServiceImpl.java</a>, 
      <a href="/src/main/java/org/springframework/samples/customerproducts/service/CustomerLocationServiceImpl.java">CustomerLocationServiceImpl.java</a> and  
      <a href="/src/main/java/org/springframework/samples/customerproducts/service/CustomerServiceImpl.java">CustomerServiceImpl.java</a>
    </td>
  </tr>
  <tr>
    <td>Cache</td>
      <td>
       <a href="/src/main/resources/spring/tools-config.xml">tools-config.xml</a>,
      <a href="/src/main/java/org/springframework/samples/customerproducts/service/CatalogueServiceImpl.java">CatalogueServiceImpl.java</a>, 
       <a href="/src/main/java/org/springframework/samples/customerproducts/service/CustomerLocationServiceImpl.java">CustomerLocationServiceImpl.java</a> and 
       <a href="/src/main/java/org/springframework/samples/customerproducts/service/CustomerServiceImpl.java">CustomerServiceImpl.java</a>
    </td>
  </tr>
  <tr>
    <td>Bean Profiles</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>, 
      <a href="/src/test/java/org/springframework/samples/customerproducts/service/CustomerServiceJdbcTests.java">CustomerServiceJdbcTests.java</a> and 
      <a href="/src/main/webapp/WEB-INF/web.xml">web.xml</a>
    </td>
  </tr>
  <tr>
    <td>JdbcTemplate</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a> and 
      <a href="/src/main/java/org/springframework/samples/customerproducts/repository/jdbc">jdbc folder</a>
    </td>
  </tr>
  <tr>
    <td>JPA</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a> and 
      <a href="/src/main/java/org/springframework/samples/customerproducts/repository/jpa">jpa folder</a>
    </td>
  </tr>
  <tr>
    <td>Spring Data JPA</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a> and 
      <a href="/src/main/java/org/springframework/samples/customerproducts/repository/springdatajpa">springdatajpa folder</a>
    </td>
  </tr>
</table>
