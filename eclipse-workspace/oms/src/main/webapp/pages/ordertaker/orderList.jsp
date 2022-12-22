<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="ISO-8859-1">
<fmt:setLocale value="en_PH"/>
<div class="row my-5">
	<div class="col-12 mb-4"><h3 class="fs-4 m-0 text-warning">Order List</h3></div>
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
		<button id="appybtn" class="btn btn-warning " id="filter">Apply</button>
	</div>
	<div class="col-12 col-sm-4 col-md-2 mb-3">
		
	</div>
	
	<div class="col-12 col-sm-12 col-md-4 mb-3 d-flex align-items-end">
		<div class="d-flex" role="search">
	        <input id="searchBar" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-warning">Search</button>
      	</div>
	</div>
	<div class="col text-center">
		<div class="table-responsive bg-light">
			<table class="table table-hover table-bordered table-sm">
				<thead  class="table-dark">
				   <tr>
				      <th scope="col">Order Id</th>
				      <th scope="col">Customer Firstname</th>
				      <th scope="col">Customer Lastname</th>
				      <th scope="col">Customer Mobile Number</th>
				      <th scope="col">Product Name</th>
				      <th scope="col">Quantity</th>
				      <th scope="col">Source Name</th>
				      
				      <th scope="col">Order Date</th>
				      
				      <th scope="col">Order Status</th>
				      <th scope="col">Payment Status</th>
				      <th scope="col">Order Discount</th>
				      <th scope="col">order Price</th>
				      <th scope="col">Delivery Date</th>
				      <th scope="col">Order Remarks</th>
				      <th scope="col">Edit Order</th>
				    </tr>
				 </thead> 
				    <tbody class="table-light">
			          <c:forEach var="order" items="${allOrderTaker}">
			          	<tr>
			          	  <td id="orderId"><c:out value="${ order.orderId }"/></td>
			              <td><c:out value="${ order.customerFn }"/></td>
			              <td><c:out value="${ order.customerLn }"/></td>
			              <td><c:out value="${ order.mobileNumber }"/></td>
			              <td><c:forEach var="orderdetails" items="${order.orderDetails}">
			               <c:out value="${ orderdetails.productName }"/>
			               </c:forEach></td>
			              <td><c:forEach var="orderdetails" items="${order.orderDetails}">
			               <c:out value="${ orderdetails.quantity }"/>
			      			
			              </c:forEach></td>
			              <td><c:out value="${ order.sourceName }"/></td>
			              
			              
			              <td><c:out value="${ order.orderDateStr }"/></td>
			              
			              <td>
			              <c:choose>
						  	<c:when test='${ order.orderStatus==1 }'>
							  	<c:out value="Pending"/>
						  	</c:when>
						  	<c:when test='${ order.orderStatus==2 }'>
							  	<c:out value="Ready for Pick Up"/>
						  	</c:when>
						  	<c:when test='${ order.orderStatus==3 }'>
							  	<c:out value="Completed"/>
						  	</c:when>	
						  	<c:when test='${ order.orderStatus==4 }'>
							  	<c:out value="Cancelled"/>
						  	</c:when>
						  	<c:when test='${ order.orderStatus==5 }'>
							  	<c:out value="Rejected"/>
						  	</c:when>
						  </c:choose>
						  </td>
						  <td>   
			              	
			              	<c:choose>
						  	<c:when test='${ order.paymentStatus==1 }'>
							  	<c:out value="Not Paid"/>
						  	</c:when>
						  	<c:when test='${ order.paymentStatus==2 }'>
							  	<c:out value="Paid"/>
						  	</c:when>
							</c:choose>
						  </td>
			              <td><c:out value="${ order.discount }"/></td>
			              <td><fmt:formatNumber type="currency" value="${ order.price }" /></td>
			              <td><c:out value="${ order.deliveryDateStr }"/></td>
			              <td><c:out value="${ order.remarks }"/></td>
		              	  <td><button class="submitBtn btn btn-primary" onclick="initGetOrder(${ order.orderId }, ${ order.orderStatus }, ${ order.paymentStatus })">edit</button></td>
			            </tr>
			          </c:forEach>
			         </tbody>
			 	                   
			</table>
	 	</div>
	</div>
	
	<nav class="mt-2">
		<ul id="show_paginator" class="pagination"></ul>
	 </nav>	
	 
	 
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Edit Order</h5>
	        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	          			<div class="form-group">
						    <label style="font-weight: bold" for="OrderId">Order Id:</label>
						    <label id="userId"></label>
						</div>
					  	<div class="form-group">
						    <label style="font-weight: bold" >Order Status:</label>
						    <select id="orderStatusModal">
						    	<option value="1">Pending</option>
				              	<option value="2">Ready For Pick Up</option>
				              	<option value="3">Completed</option>
				              	<option value="4">Cancelled</option>
				              	<option value="5">Rejected</option>
				            </select>
					  	</div>
					  	<div class="form-group" style="font-weight: bold">
						    <label style="font-weight: bold">Payment Status:</label>
						    <select id="paymentStatus">
							    <option value="1">Not Paid</option>
				              	<option value="2">Paid</option>
			              	</select>
					  	</div>

	          			<div class="form-group">
						    <label for="OrderId" style="font-weight: bold">Products:</label>
						    <label id="productId"></label>
						</div>
						
						<div class="form-group">
						    <label for="OrderId" style="font-weight: bold">Quantity:</label>
						    <label id="quantityId"></label>
						</div>
					  	
					  	<div class="form-group mb-3">
						    <label style="font-weight: bold">Remarks</label>
						    <textarea id="message-text" class="form-control" type="text" rows="3"></textarea>
					  	</div>				
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="button" class="saveBtn btn btn-primary">Save Changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	 
</div>
	
<script type="text/javascript">
let currentpage = ${currentPage};
let totalpage = ${totalpage};
$( document ).ready(function(){
	initSearchOrder();
});

$('#filter').click(function(){
	entry = $("#entry").val();
 	orderby = $("#orderby").val();
 	sortby = $("#sortby").val();
 	openViewAllOrders(currentpage, entry, "order_date", sortby);
});

$('#show_paginator').bootpag({
    total: totalpage,
    page: currentpage,
    maxVisible: 5,
}).on("page", function(event, num){
	entry = $("#entry").val();
 	orderby = $("#orderby").val();
 	sortby = $("#sortby").val();

 	openViewAllOrders(num, entry, "order_date", sortby);

 
});

$('a').addClass('page-link');
</script>