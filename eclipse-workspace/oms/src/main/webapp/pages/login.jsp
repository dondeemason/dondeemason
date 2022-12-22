<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<header>
	
</header>
<body>
	
	
	<div class="container">
	
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card border-2 shadow rounded-3 my-5">
          <div class="card-body p-4 p-sm-5">
<!--           <div class="alert text-center" role="alert">${loginMessage}</div>
          <div class="alert text-center" role="alert">${forgotPassword}</div>
          <div class="alert text-center" role="alert">${message}</div> -->
            <h5 class="card-title text-center mb-5 fw-light fs-5">Sign In</h5>
             <hr class="mb-5">
            
              <div class="form-floating mb-3">
                <input id="username" type="text" class="form-control" placeholder="Username" maxlength="20">
                <label for="floatingInput">Username</label>
              </div>
              <div class="form-floating mb-3">
                <input id="password" type="password" class="form-control" id="floatingPassword" placeholder="Password" maxlength="20">
                <label for="floatingPassword">Password</label>
              </div>

              <div class="form-check mb-3">
      
              </div>
              <div class="d-grid">
                <input class="btn btn-primary btn-login text-uppercase fw-bold mb-2" id="loginButton" value="Sign in" type="button" />
              </div>
              <!--  <div class="d-grid">
				<button class="btn btn-primary btn-login text-uppercase fw-bold" id="backLandingPageBtn">back</button>
			  </div>-->
              <hr class="my-4">
              <div class="d-grid mb-2">
                <button id="openForgotPassword" class="btn btn-google btn-login text-uppercase fw-bold">
                  <i class="fab fa-google me-2"></i> Forgot Password
                </button>
              </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		initLoginPage();
		initopenForgotPassPage();
		initbackLandingPage();
	});
</script>
</html>