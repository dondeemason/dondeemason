<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OMS</title>
    
    <!-- All CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    
    <script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}' + '/';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/service.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/getservice.js"></script>
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-bootpag/lib/jquery.bootpag.js"></script>
</head>

<body id="root">
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
          <a class="navbar-brand" href="#"><i class="fas fa-cookie" style='color: white'></i><span class="text-warning">MS</span></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="#team">Our Bakers</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/css/img/cookies.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption">
              <h5>Break it, crack it, dip it, lick it!</h5>
                              <p>Smell of freshly cut grass; summer breeze; freshly baked cookies- perfect summer noon!</p>
                              <p><a href="#" class="orderNow btn btn-warning mt-3">Order Now</a></p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/css/img/cookies2.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption">
              <h5>It’s fresh or you won’t find it here</h5>
                              <p>If you see cookie crumbs around my lips, let me be happy. #happyvibes #cookies_make_you_smile</p>
                              <p><a href="#" class="orderNow btn btn-warning mt-3">Order Now</a></p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/css/img/cookies3.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption">
              <h5>Just another attempt to ‘B’ake your day better.</h5>
                              <p>Every cookie waits for its owner. Come collect yours!</p>
                              <p><a href="#" class="orderNow btn btn-warning mt-3">Order Now</a></p>
            </div>
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		//initopenLoginPage();
		initCustomerProductPage();
	});	
</script>
</html>