<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/service.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getservice.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<div class="row my-5">
	<div class="col">
		<div class="container">
		    <div class="row">
		      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
		        <div class="card border-2 shadow rounded-3 my-5">
		          <div class="card-body p-4 p-sm-5">
		            <h5 class="card-title text-center mb-1 fs-5 text-primary">Registration form</h5>
		            
		             <hr class="mb-5">
		             
		            <div class="row g-3 needs-validation" novalidate>
		              <div class="form-floating mb-3">
		                <input id="username" type="text" class="form-control" placeholder="floatingUsername" maxlength="20" required>
		                <label class="required" for="floatingInput">Username</label>
		              </div>
		              
		              <div class="form-floating mb-3">
		                <input id="email" type="email" class="form-control" placeholder="name@example.com" maxlength="120" required>
		                <label class="required" for="floatingInput">Email Address</label>
		              </div>
		              
		              <div class="form-floating mb-3">
		                <input id="password" type="password" class="form-control" placeholder="Password" maxlength="20" required>
		                <label class="required" for="floatingPassword">Password</label>
		              </div>
		              
					  
		      		  <div class="form-floating mb-3">
		              	<select id="role" class="form-select required" required>
		              	  <option value="1" selected>Administrator</option>
						  <option value="2">Producer</option>
						  <option value="3">Order Taker</option>
						  <option value="4">Auditor</option>
						 </select>
						 <label class="required">Role:</label>
					  </div>
		              
		              <div class="d-grid">
		                <button id="addnewuserbtn" class="btn btn-success btn-login text-uppercase fw-bold">Add User
		              </button>
		              <hr class="my-4">
		              <div class="d-grid">
						<button class="btn btn-primary btn-login text-uppercase fw-bold" id="backLandingPageBtn">Dashboard</button>
			  		  </div>
		              </div> 
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
	</div>
</div>
<%-- <script type="text/javascript">
	$(document).ready(function(){
		initbackLandingPage();
	});
</script> --%>

<script type="text/javascript">
		$( document ).ready(function(){
			initAddNewUser();
			initbackLandingPage();
		});	
</script>
</html>