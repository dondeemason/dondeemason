<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/service.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getservice.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<div class="container">
	<c:set var="userid" scope="session" value="${ user.userId }"/>
	<c:set var="username" scope="session" value="${ user.username }"/>
	<c:set var="email" scope="session" value="${ user.email }"/>
	<c:set var="roleid" scope="session" value="${ user.roleId }"/>
	<c:set var="userStatus" scope="session" value="${ user.userStatus }"/>
	
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card border-2 shadow rounded-3 my-5">
          <div class="card-body p-4 p-sm-5">
            <h5 class="card-title text-center mb-1 fw-primary fs-5">Edit User</h5>
             <hr class="mb-5">
            <div class="row g-3 needs-validation" novalidate> 
	          	         
              
              
              <div class="form-floating mb-3">
	          	<input type="text" class="form-control" value="${username}" id="usernameEditUser" maxlength="20" required disabled>
              	<label for="floatingInput" class="required">Username: </label>
              </div>
              <div class="form-floating mb-3">
              	<input type="email" class="form-control" id="emailEditUser" value="${email}" maxlength="120" required>
              	<label for="floatingInput" class="required">Email Adress: </label>
              </div>
              
              <div class="form-floating mb-3">
              	<select class="form-select mb-4" id="roleEditUser" required>
              	  <c:if test="${roleid == 1}">
              	  	<option value="${roleid}" selected>Administrator</option>
              	  </c:if>
              	  <c:if test="${roleid == 2}">
              	  	<option value="${roleid}" selected>Producer</option>
              	  </c:if>
              	  <c:if test="${roleid == 3}">
              	  	<option value="${roleid}" selected>Order</option>
              	  </c:if>
              	  <c:if test="${roleid == 4}">
              	  	<option value="${roleid}" selected>Auditor</option>
              	  </c:if>
				  <option value="1">Administrator</option>
				  <option value="2">Producer</option>
				  <option value="3">Order Taker</option>
				  <option value="4">Auditor</option>
				 </select> 
				 <label for="floatingInput" class="required">Role:</label>
              </div>
              	
              
              
              <div class="d-grid mb-2">
                <button id="saveEditUser" class="btn btn-success btn-login text-uppercase fw-bold" onclick="editUserSave(${ userid })">
                	Save 
              	</button>
              </div> 
              <div class="d-grid">
	              <c:if test="${userStatus == 0}">
	              	<button id="disableUser" class="btn btn-danger btn-login text-uppercase fw-bold" onclick="editUserDisable('${ userid }', '${userStatus}')">
	                	Disable User
	              	</button>
	              </c:if>
	              <c:if test="${userStatus == 1}">
	              	<button id="enableUser" class="btn btn-success btn-login text-uppercase fw-bold" onclick="editUserDisable('${ userid }', '${userStatus}')">
	                	Enable User
	              	</button>
	              </c:if>
              </div>
              <div class="d-grid mb-2">
                <button id="cancelBtn" class="btn btn-primary btn-login text-uppercase fw-bold">
                	Dashboard 
              	</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  

 <script type="text/javascript">
	$( document ).ready(function(){
//		initEditUserSave();
//		initEditUserDisable();
		initCancel();
	});	
</script>