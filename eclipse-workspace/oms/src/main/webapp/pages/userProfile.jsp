<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
		<c:set var="userid" scope="session" value="${ user.userId }"></c:set>
		<c:set var="username" scope="session" value="${ user.username }"></c:set>
		<c:set var="email" scope="session" value="${ user.email }"></c:set>
		<c:set var="roleid" scope="session" value="${ user.roleId }"></c:set>
		<c:set var="userStatus" scope="session" value="${ user.userStatus }"></c:set>
<!--	<section class="vh-100">
		<c:set var="userid" scope="session" value="${ user.userId }"></c:set>
		<c:set var="username" scope="session" value="${ user.username }"></c:set>
		<c:set var="email" scope="session" value="${ user.email }"></c:set>
		<c:set var="roleid" scope="session" value="${ user.roleId }"></c:set>
		<c:set var="userStatus" scope="session" value="${ user.userStatus }"></c:set>
	
		  <div class="container py-5 h-100">
		    <div class="row d-flex justify-content-center align-items-center h-100">
		      <div class="col col-lg-6 mb-4 mb-lg-0">
		        <div class="card mb-3" style="border-radius: .5rem;">
		          <div class="row g-0">
		            <div class="col-md-4 gradient-custom text-center bg-primary text-white"
		              style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
		              <img src="${pageContext.request.contextPath}/img/profpic.png"
		                alt="Avatar" class="img-fluid my-5" style="width: 120px;" />
		              <div class="col-md-12 p-2"  >
		              <h5>Order Management</h5>
		              <p>System</p>
		              </div>
		            </div>
		            <div class="col-md-8">
		              <div class="card-body p-4">
		              	<div class="row g-3 needs-validation" novalidate>
		                <h5>Information</h5>
		                <hr class="mt-0 mb-4">
		                <div class="row pt-1">
		                <div class="alert text-center" role="alert">${message}</div>
			               <div class="col-12 mb-3">
			                   <h6 class="required">User Id</h6>
							   <input type="number" id="userid" class="form-control" value="${userid}" disabled required>
			               </div>
		                  <div class="col-12 mb-3">
		                    <h6 class="required">Email</h6>
						    <input type="email" class="form-control" id="email" value="${email}" disabled required>
		                  </div>
		                  
		                  <div class="col-12 mb-3">
		                    <h6 class="required">Username</h6>
		                    <input type="text" class="form-control" id="username" value="${username}" disabled required>
		                  </div>
		                  
		                  <div class="col-12 mb-3">
		                    <h6 class="required">Old Password</h6>
		                    <input type="password" class="form-control" id="oldpassword" disabled required>
		                  </div>
		                  
		                  <div class="col-12 mb-3">
		                    <h6 class="required">New Password</h6>
		                    <input type="password" class="form-control" id="newpassword" disabled required>
		                  </div>
		                </div>
		                
		                <hr class="mt-0 mb-4">
		                <div class="row pt-1">
		                  <div class="d-grid mb-2">
		                    <input type="button" id="editBtn" class="btn btn-primary" value="Edit"></input>
		                  </div>
		                  <div class="d-grid mb-2">
						    <input class="btn btn-primary" id="changepassword" type="button" value="Change Password"></input>
						  </div>
		                  <div class="d-grid mb-2">
						    <input class="btn btn-warning" id="saveBtn" type="button" onClick="validation()" value="Save" disabled></input>
						  </div>
						  <div class="d-grid mb-2">
						    <input class="btn btn-primary" id="cancelBtn" type="button" value="Back"></input>
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
	</section> -->
	
	<div class="row d-flex justify-content-center align-items-center h-100">
	<div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="row container d-flex justify-content-center">
<div class="col-xl-6 col-md-12">
    <div class="card user-card-full">
        <div class="row m-l-0 m-r-0">
            <div class="col-sm-4 bg-c-lite-green user-profile">
                <div class="card-block text-center text-white">
                    <div class="m-b-25">
                        <img src="${pageContext.request.contextPath}/img/profpic.png" class="img-radius" alt="User-Profile-Image">
                    </div>
                    <h6 class="f-w-600"><c:out value="${username}"/></h6>
                    
                    <c:if test="${roleid == 1}">
                    	<p>Administrator</p>
	              	 </c:if>
	              	 <c:if test="${roleid == 2}">
	              	 	<p>Producer</p>	
	              	 </c:if>
	              	 <c:if test="${roleid == 3}">
	              	 	<p>Order Taker</p>
	              	 </c:if>
	              	 <c:if test="${roleid == 4}">
	              	 	<p>Auditor</p>
	              	 </c:if>
                    
                    
                    
                    <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="card-block">
                    <h6 class="m-b-20 p-b-5 b-b-default f-w-600">Information</h6>
                    <div class="row">
                    
                        <div class="col-sm-6">
                        	<p class="m-b-10 f-w-600"></p>
                        	<div class="form-floating mb-3">
                        		<input type="number" id="userid" class="form-control" placeholder="User ID" value="${userid}" disabled required>
                        		<label for="floatingInput">User ID</label>
                        	</div>
                        </div>
                        
                        <div class="col-sm-6">
                            <p class="m-b-10 f-w-600"></p>
                            <div class="form-floating mb-3">
                            	<input type="text" class="form-control" id="username" placeholder="Username" value="${username}" disabled required>
                        		<label for="floatingInput">Username</label>
                        	</div>
                            
                        </div>
                        
                        <div class="col-sm-12">
                            <p class="m-b-10 f-w-600"></p>
                            <div class="form-floating mb-3">
                            	<input type="email" class="form-control" id="email" value="${email}" placeholder="Email" maxlength="250" disabled required>
                        		<label class="required" for="floatingInput">Email</label>
                        	</div>
                            
                        </div>
                        
                        <div class="col-sm-6">
                        	<p class="m-b-10 f-w-600"></p>
                            <input type="button" id="editBtn" class="btn btn-primary" value="Edit"></input>
                        </div>
                        <div class="col-sm-6">
                        	<p class="m-b-10 f-w-600"></p>
                            <input class="btn btn-warning" style="display: none; visibility: visible;" id="saveBtn" type="button" onClick="validation()" value="Save" disabled></input>
                        </div>
                        
                    </div>
                    <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Change password</h6>
                    <div class="row">
                    
                        <div class="col-sm-12">
                        	<p class="m-b-10 f-w-600"></p>
                        	<div class="form-floating mb-3">
                            	<input type="password" class="form-control" id="oldpassword" placeholder="Current password" maxlength="20" disabled required>
                        		<label class="required" for="floatingInput">Current password</label>
                        	</div>  
                        </div>
                        
                        <div class="col-sm-6">
                            <p class="m-b-10 f-w-600"></p>
                            <div class="form-floating mb-3">
                            	<input type="password" class="form-control" id="newpassword" placeholder="New password" maxlength="20" disabled required>
                        		<label class="required" for="floatingInput">New password</label>
                        	</div> 
                        </div>
                        
                        <div class="col-sm-6">
                            <p class="m-b-10 f-w-600"></p>
                            <div class="form-floating mb-3">
                            	<input type="password" class="form-control" placeholder="Re-enter new password" id="reenterpassword" maxlength="20" disabled required>
                        		<label class="required" for="floatingInput">Re-enter new password</label>
                        	</div>
                        </div>
                        
                        <div class="col-sm-6">
                            <p class="m-b-10 f-w-600"></p>
                            <input class="btn btn-primary" id="changepassword" type="button" value="Change Password"></input>
                        </div>
                        
                        <div class="col-sm-6">
                        	<p class="m-b-10 f-w-600"></p>
                            <input class="btn btn-warning" style="display: none; visibility: visible;" id="changepassbtn" type="button" onClick="validation()" value="Proceed"></input>
                        </div>
                        
                    </div>
                    <ul class="social-link list-unstyled m-t-40 m-b-10">
                        <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="facebook" data-abc="true"></i></a></li>
                        <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="twitter" data-abc="true"></a></li>
                        <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="instagram" data-abc="true"></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
 </div>
    </div>
</div>
</div>
<script type="text/javascript">
	$("#editBtn").click(function(){
		if($('#editBtn').val() == "Edit"){
			$('#editBtn').val("Cancel");
			email = $('#email').prop('disabled', false);
			saveBtn = $('#saveBtn').show();
//			username = $('#username').prop('disabled', false);
//			oldpassword = $('#oldpassword').prop('disabled', false);
//			newpassword = $('#newpassword').prop('disabled', false);
//			changepass = $('#changepassword').prop('disabled', true);
			saveBtn = $('#saveBtn').prop('disabled', false);
//			backBtn = $('#cancelBtn').prop('disabled', true);
		}
		else{
			$('#editBtn').val("Edit");
			email = $('#email').prop('disabled', true);
//			username = $('#username').prop('disabled', true);
			oldpassword = $('#oldpassword').prop('disabled', true);
//			newpassword = $('#newpassword').prop('disabled', true);
			changepass = $('#changepassword').prop('disabled', false);
			saveBtn = $('#saveBtn').hide();
			backBtn = $('#cancelBtn').prop('disabled', false);
		}
	});
	
	$("#changepassword").click(function(){
		if($('#changepassword').val() == "Change Password"){
			$('#changepassword').val("Cancel");
//			email = $('#email').prop('disabled', false);
//			username = $('#username').prop('disabled', false);
			oldpassword = $('#oldpassword').prop('disabled', false);
			newpassword = $('#newpassword').prop('disabled', false);
			newpassword = $('#reenterpassword').prop('disabled', false);
//			changepass = $('#changepassword').prop('disabled', false);
//			$('#saveBtn').val("Change Password");
//			$('#editBtn').prop('disabled', true);
			changepass = $('#changepassbtn').show();
			backBtn = $('#cancelBtn').prop('disabled', true);
		}
		else{
			$('#changepassword').val("Change Password");
//			email = $('#email').prop('disabled', true);
//			username = $('#username').prop('disabled', true);
			oldpassword = $('#oldpassword').prop('disabled', true);
			newpassword = $('#newpassword').prop('disabled', true);
			reenterpassword = $('#reenterpassword').prop('disabled', true);
//			saveBtn = $('#saveBtn').prop('disabled', true);
//			$('#saveBtn').val("Save");
//			backBtn = $('#cancelBtn').prop('disabled', false);
			changepass = $('#changepassbtn').hide();
			$('#editBtn').prop('disabled', false);
		}
	});
	
	$( document ).ready(function(){
		initEditProfile();
		initChangePassword();
		initCancel();
	});	
</script>
</html>