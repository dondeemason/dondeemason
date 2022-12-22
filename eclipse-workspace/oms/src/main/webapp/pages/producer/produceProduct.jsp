<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row my-1">
	<div class="col-12"><h1 class="fs-4 m-0 mb-4 text-warning">Product Production</h1></div>
		
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
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="searchBar">
	        <button class="btn btn-warning" id="searchBtn">Search</button>
      	</div>
	</div>
	
	<div class="col-12 text-center">


		<table id="tbl" class="table bg-white rounded shadow-sm  table-hover">
			<thead>
			   <tr>			 
	              <th scope="col">Product Name</th>
	              <th scope="col">Product Picture</th>
	              <th scope="col">Production Date</th>
	              <th scope="col">Quantities</th>
            	</tr>
            </thead> 

			    <tbody>
		          <c:forEach var="production" items="${viewProducts}">
		          	<tr>
		              <td class="getProdName"><c:out value="${ production.productName }" /></td>
		              <td><img src="${ production.url}" width="120" height="70"></td>
		              <td class="getDate"><c:out value="${ production.date }"/></td>
		              <td><input type ="text" class="inputQuantities" onkeypress="return isNumberKey(event);"></input></td>
					  
		            </tr>
				
		          </c:forEach>
		          		
		         </tbody>     

		</table>
		       
	<div class="col-6">
		
	</div>
	<input id="getRowTable" type="button" class="btn btn-warning col-6" value="Add Quantity" style="margin-right: 0px;"></input>
	 <nav class="mt-2">
		<ul id="show_paginator" class="pagination"></ul>
	 </nav>		
	
	 <div id="myModal" class="modal fade">
       <div class="modal-dialog w-auto" style="min-width: 300px;">
            <div class="modal-content">
            	<div class="modal-header">
					<h4 class="modal-title mx-auto">Quantities Added</h4>
				</div>
                <table id="here_table" class="table borderless mt-2 mx-auto w-auto">

                </table>
  			<div class="d-flex mb-2" style="margin-left: 280px;">
   				<input class="btn btn-primary" type="submit" value="Confirm" id="confirmModal" style="margin-right: 10px;"></input>
  				<input class="btn btn-primary" type="submit" value="Cancel" id="cancelModal" onclick="hideModal()"></input>
  			</div>
            </div>
        </div>
        
    </div>	
</div>
			
<script type="text/javascript">
	let currentpage = ${currentPage};
	let totalpage = ${totalpage};
	$( document ).ready(function(){
		getAllRow();
		initSearchProduction();
		initOpenAddProductPage();
		$('#myModal').modal({
		    backdrop: 'static',
		    keyboard: false  // to prevent closing with Esc button (if you want this too)
		})
		
		$("#myModal").on("hidden.bs.modal", function() {
		    $("#here_table").html("");
		  });
	});
	
	$('#filter').click(function(){
		getAll();
		entry = $("#entry").val();
	 	//orderby = $("#orderby").val();
	 	sortby = $("#sortby").val();
	 	openViewProduction(currentpage, entry, "product_name", sortby);
	});
	
	$('#show_paginator').bootpag({
	    total: totalpage,
	    page: currentpage,
	    maxVisible: 5,
	}).on("page", function(event, num){
		entry = $("#entry").val();
	 	//orderby = $("#orderby").val();
	 	sortby = $("#sortby").val();
	 	
	 	
	 	getAll();

	 	openViewProduction(num, entry, "product_name", sortby);
	    // ... after content load -> change total to 10
	    //$(this).bootpag({total: 10, maxVisible: 10});
	 
	});
	
	$('a').addClass('page-link');
		
</script>