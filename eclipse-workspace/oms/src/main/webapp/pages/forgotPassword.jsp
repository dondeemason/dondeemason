<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
</head>

<body>

<div class="card text-center my-5 mx-auto" style="width: 400px;">
    <div class="card-header h5 text-white bg-primary">Password Reset</div>
    <div class="card-body px-5">
        <p class="card-text py-2">
            We'll send you an email with generated password to reset your password.
        </p>
        <div class="form-outline">
        	<div class="form-floating mb-3">
	            <input placeholder="Email Address" type="email" id="email" class="form-control mb-1" maxlength="120"/>
	            <label for="floatingInput" class="required">Email Adress: </label>
	        </div>
        </div>
        
        <div class="form-outline">
        	<div class="form-floating mb-3">
	            <input placeholder="Username" type="text" id="username" class=" form-control mb-1" maxlength="20" />
	            <label for="floatingInput" class="required">Username: </label>
	        </div>
        </div>
        
        <div class="d-grid">
            <button class="btn btn-primary btn-login text-uppercase fw-bold mb-1" id="forgotBtn">
               Reset Password
            </button>
        </div>
        <div class="d-flex justify-content-center mt-4">
            <a class="text-center" href="#" id="openLogin">Sign in Again?</a>
         <!-- <a class="" href="#" id="backLandingPageBtn">Back</a> -->   
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
	$(document).ready(function(){
		initForgotPage();
		initopenLoginPage();
		initbackLandingPage();
	});
</script>
</html>