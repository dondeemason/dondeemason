<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css">
<div class="row my-5">
		<div class="col-7  mb-4"><h3 class="fs-4 m-0 text-warning">Expected Order List</h3></div>
		
	<div class = "d-flex justify-content-end text-warning col-5">
			<h1 id="dateNow"></h1>
		</div>
	<div class="col-12 d-flex mt-1 justify-content-end">'		
		<input id="am" type="button" class="btn btn-primary mb-4 me-1" value="AM"></input>
		<input id="pm" type="button" class="btn btn-primary mb-4" value="PM"></input>
	</div>
	
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
		<button class="btn btn-warning " id="filter">Apply</button>
	</div>
	<div class="col-12 col-sm-4 col-md-2 mb-3 d-flex align-items-end">

	</div>
	<div class="col-12 col-sm-12 col-md-4 mb-3 d-flex align-items-end">
		<div class="d-flex" role="search">
	        <input class="date-pick" id="datepicker" data-provide="datepicker" name="date_selected" data-date-format="mm/dd/yyyy" placeholder="Select date" style="height: 39px; margin-top: 17px;">
	        <button class="btn btn-warning mt-3" id="date_btn" style="height: 39px; margin-left: 10px;">Search</button>
      	</div>
	</div>
	<div class="col text-center">
		<table class="table table-striped table-light">
			<thead>
			   <tr>

			      <th scope="col">Product Name</th>
			      <th scope="col">Delivery Date</th>
			      <th scope="col">Quantity</th>

			    </tr>
			    <tbody>
		          <c:forEach var="expectedOrder" items="${expectedOrder}">
		          	<tr>
		          	  <td><c:out value="${ expectedOrder.productName }"/></td>
		              <td><c:out value="${ expectedOrder.deliveryDate }"/></td>
		              <td><c:out value="${ expectedOrder.quantities }"/></td>

		            </tr>
		          </c:forEach>
		         </tbody>
		 	</thead>                    
		</table>
		
		<nav class="mt-2">
			<ul id="show_paginator" class="pagination"></ul>
		</nav>
	 </div>
</div>
<script type="text/javascript">
		let currentpage = ${currentPage};
		let totalpage = ${totalpage};
		$( document ).ready(function(){
			initViewPm();
			initViewAm();		
			getDate();
			$('#dateNow').text(globalDate);
		});
		
		$('#filter').click(function(){
			entry = $("#entry").val();
		 	orderby = $("#orderby").val();
		 	sortby = $("#sortby").val();
		 	openViewAllExpectedOrder(currentpage, entry, "pd.product_id", sortby);
		});
		
		$('#show_paginator').bootpag({
		    total: totalpage,
		    page: currentpage,
		    maxVisible: 5,
		}).on("page", function(event, num){
			entry = $("#entry").val();
		 	orderby = $("#orderby").val();
		 	sortby = $("#sortby").val();
		 	
		 	openViewAllExpectedOrder(num, entry, "pd.product_id", sortby);
		    // ... after content load -> change total to 10
		    //$(this).bootpag({total: 10, maxVisible: 10});
		 
		});
		
		$('a').addClass('page-link');
		
</script>