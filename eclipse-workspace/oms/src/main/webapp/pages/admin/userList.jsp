<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row my-5">
	<div class="col-6"><h3 class="fs-4 m-0 text-warning">User Lists</h3></div>
	<div class="col-6 d-flex justify-content-end"><input id="addUserBtn" type="button" class="btn btn-primary mb-4" value="Add User"/></div>
	
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
		  	<c:when test='${ orderby.equals("user_id")}'>
			  	<option value="user_id" selected>User ID</option>
		  		<option value="username">Username</option>
		  		<option value="role_id">Role</option>
		  	</c:when>
		  	<c:when test='${ orderby.equals("username")}'>
			  	<option value="user_id" >User ID</option>
		  		<option value="username" selected>Username</option>
		  		<option value="role_id">Role</option>
		  	</c:when>
		  	<c:when test='${ orderby.equals("role_id")}'>
			  	<option value="user_id">User ID</option>
		  		<option value="username">Username</option>
		  		<option value="role_id" selected>Role</option>
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
	
	<div class="col-12 text-center">

		
		<div class="table-responsive bg-light">
		<table class="table table-hover table-bordered">
			<thead class="table-dark">
			   <tr>
			      <th scope="col" width="50">User ID</th>
			      <th scope="col">Username</th>
			      <th scope="col">Role</th>
			      <th scope="col">Status</th>
			      <th scope="col">Edit</th>
			    </tr>
			</thead>
			    <tbody class="table-light">
		          <c:forEach var="user" items="${usersList}">
		          	<tr>
		          	  <td><c:out value="${ user.userId }"/></td>
		              <td><c:out value="${ user.username }"/></td>
		              <c:if test="${user.roleId == 1}">
	              	  	<td>Administrator</td>
	              	  </c:if>
	              	  <c:if test="${user.roleId == 2}">
	              	  	<td>Producer</td>
	              	  </c:if>
	              	  <c:if test="${user.roleId == 3}">
	              	  	<td>Order Taker</td>
	              	  </c:if>
	              	  <c:if test="${user.roleId == 4}">
	              	  	<td>Auditor</td>
	              	  </c:if>
	              	  
	              	  <c:if test="${user.userStatus == 0}">
	              	  	<td>Enabled</td>
	              	  </c:if>
	              	  <c:if test="${user.userStatus == 1}">
	              	  	<td>Disabled</td>
	              	  </c:if>
		              <td><button class="editbtn btn btn-primary" onclick="initOpenEditPage(${ user.userId })">edit</button></td>
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
		console.log(totalpage)
		$( document ).ready(function(){
			initOpenAddUser();
//			initViewAllUser();
		});
		
		$('#filter').click(function(){
			entry = $("#entry").val();
		 	orderby = $("#orderby").val();
		 	sortby = $("#sortby").val();
			openViewAllUser(currentpage, entry, orderby, sortby);
		});
		
		$('#show_paginator').bootpag({
		    total: totalpage,
		    page: currentpage,
		    maxVisible: 5,
		}).on("page", function(event, num){
		   // $(".page-link").html("Page " + num); // or some ajax content loading...
		 	entry = $("#entry").val();
		 	orderby = $("#orderby").val();
		 	sortby = $("#sortby").val();
		 	
		 	openViewAllUser(num, entry, orderby, sortby);
		    // ... after content load -> change total to 10
		    //$(this).bootpag({total: 10, maxVisible: 10});
		 
		});
		
		$('a').addClass('page-link');
</script>