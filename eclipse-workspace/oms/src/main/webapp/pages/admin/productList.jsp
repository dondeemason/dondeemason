<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="ISO-8859-1">
<fmt:setLocale value="en_PH"/>

<div class="row my-5">
	<div class="col-6"><h3 class="fs-4 m-0 text-warning">Product List</h3></div>
	<div class="col-6 d-flex justify-content-end"><input id="addproduct" type="button" class="btn btn-primary mb-4" value="Add Products"></input></div>
	<div class="col-12 col-sm-4 col-md-2 mb-3">
		<span class="badge rounded-pill bg-warning text-dark mb-1">Entry</span>
		<select class="form-select form-control-sm" aria-label="Default select example" id="entry">
		  <c:choose>
		  	<c:when test="${ entry == 5}">
			  	<option value="5" selected>5</option>
			  	<option value="10">10</option>
			  	<option value="15">15</option>
		  	</c:when>
		  	<c:when test="${ entry == 10}">
			  	<option value="5" >5</option>
			  	<option value="10" selected>10</option>
			  	<option value="15">15</option>
		  	</c:when>
		  	<c:when test="${ entry == 15}">
			  	<option value="5" >5</option>
			  	<option value="10">10</option>
			  	<option value="15" selected>15</option>
		  	</c:when>
		  </c:choose>
		</select>
	</div>
	<div class="col-12 col-sm-4 col-md-2 mb-3">
		<span class="badge rounded-pill bg-warning text-dark mb-1">Order</span>
		<select class="form-select form-control-sm" aria-label="Default select example" id="orderby">
		  <c:choose>
		  	<c:when test='${ orderby.equals("product_id")}'>
			  	<option value="product_id" selected>Product ID</option>
		  		<option value="product_name">Product name</option>
		  		<option value="product_status">Product status</option>
		  	</c:when>
		  	<c:when test='${ orderby.equals("product_name")}'>
			  	<option value="product_id" >Product ID</option>
		  		<option value="product_name" selected>Product name</option>
		  		<option value="product_status">Product status</option>
		  	</c:when>
		  	<c:when test='${ orderby.equals("product_status")}'>
			  	<option value="product_id" >Product ID</option>
		  		<option value="product_name" >Product name</option>
		  		<option value="product_status" selected>Product status</option>
		  	</c:when>
		  </c:choose>
		</select>
	</div>
	<div class="col-12 col-sm-4 col-md-2  mb-3">
		<span class="badge rounded-pill bg-warning text-dark mb-1">Sort</span>
		<select class="form-select form-control-sm" aria-label="Default select example" id="sortby">
			<c:choose>
			  	<c:when test='${ sortby.equals("DESC")}'>
				  	<option value="DESC" selected>Descending</option>
		  			<option value="ASC">Ascending</option>
			  	</c:when>
			  	<c:when test='${ sortby.equals("ASC")}'>
				  	<option value="DESC">Descending</option>
		  			<option value="ASC" selected>Ascending</option>
			  	</c:when>
			 </c:choose> 
		</select>
	</div>
	<div class="col-12 col-sm-4 col-md-2 mb-3 d-flex align-items-end">
		<button class="btn btn-warning " id="filter">Apply</button>
	</div>
	<div class="col-12 col-sm-12 col-md-4 mb-3 d-flex align-items-end">
		<div class="d-flex" role="search">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-warning">Search</button>
      	</div>
	</div>
	<div class="col text-center">
	
	<div class="table-responsive bg-light">
		<table class="table table-hover table-bordered table-sm">
			<thead class="table-dark">
			   <tr>
			   	  <th>Product ID</th>
	              <th>Product Name</th>
	              <th>Description</th>
	              <th>Image</th>
	              <th>Price</th>
	              <th>Status</th>
	              <th>Edit</th>
            	</tr>
            </thead> 
		    <tbody class="table-light">
	          <c:forEach var="product" items="${allProductList}">
	          	<tr>
	          	  <td><c:out value="${ product.productId }"/></td>
	              <td><c:out value="${ product.productName }"/></td>
	              <td class="text-capitalize text-break text-start"><c:out value="${ product.productDescription }"/></td>
	              <td><img src="${ product.productPicture}" width="120" height="70" alt="image not available"></td>
	              <td class="text-end"><fmt:formatNumber type="currency" value="${ product.price }" /></td>
	              <td><c:out value="${ product.statusDescription }"/></td>
	              
	              <td><button class="editbtn btn btn-primary" onclick="getEditProduct('${product.productId}')">edit</button></td>
	            </tr>
	          </c:forEach>
	         </tbody>
		 </table>
	</div>
	
	<nav class="mt-2">
		<ul id="show_paginator" class="pagination"></ul>
	</nav>
	
		
		
	 </div>
</div>
<script type="text/javascript">
		let currentpage = ${currentPage};
		let totalpage = ${totalpage};
		$( document ).ready(function(){
			initOpenAddProductPage();
		});
		
		$('#filter').click(function(){
			entry = $("#entry").val();
		 	orderby = $("#orderby").val();
		 	sortby = $("#sortby").val();
		 	openViewAllProduct(currentpage, entry, orderby, sortby);
		});
		
		$('#show_paginator').bootpag({
		    total: totalpage,
		    page: currentpage,
		    maxVisible: 5,
		}).on("page", function(event, num){
			entry = $("#entry").val();
		 	orderby = $("#orderby").val();
		 	sortby = $("#sortby").val();
		 	
		 	openViewAllProduct(num, entry, orderby, sortby);
		    // ... after content load -> change total to 10
		    //$(this).bootpag({total: 10, maxVisible: 10});
		 
		});
		
		$('a').addClass('page-link');
</script>