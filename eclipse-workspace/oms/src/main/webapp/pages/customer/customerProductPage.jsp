<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<meta charset="ISO-8859-1">

<style>

#btn-back-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: none;
}

#wrapper {
  overflow-x: hidden;
  background:url('./css/img/overlay.png'),url(./css/img/cookies2.jpg);
  background-position: top;
  background-size: cover;
  background-repeat: no-repeat;
}

.vertical-scrollable>.list {
            /* position: absolute; */
            /* top: 120px; */
            /* bottom: 100px; */
            /* left: 180px; */
            /* width: 50%; */
            height: 30%;
            overflow-y: auto;
        }

</style>
<fmt:setLocale value="en_PH"/>

<body>

<div id="wrapper">
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
     <div class="container">
       <a class="navbar-brand" href="#"><i class="fas fa-cookie" style='color: white'></i><span class="text-warning">MS</span></a>
       <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
       </button>
       <div class="collapse navbar-collapse" id="navbarSupportedContent">
         <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
           <li class="nav-item">
             <!-- <a class="nav-link" href="#team">Our Bakers</a> -->
           </li>
         </ul>
       </div>
     </div>
 </nav>
      <div class="container">
        <main>
          <!-- <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="/docs/5.2/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
            <h2>Checkout form</h2>
            <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
          </div> -->
			<div class="py-5 text-center"></div>
			
			<button
			        type="button"
			        class="btn btn-warning btn-floating btn-lg"
			        id="btn-back-to-top"
			        >
			  <i class="fa fa-arrow-up"></i>
			</button>
			
          <!-- cart view -->
          <div class="row g-5">
            <div class="col-md-5 col-lg-4 order-md-last vertical-scrollable">
              <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-warning">Your cart</span>
                <span class="badge bg-warning text-dark rounded-pill" id="count">0</span>
              </h4>
              	<ul class="list-group mb-3 list">
              
                	<div id="cartItem"></div>

      			</ul>
	              <div class="card border-0" style="background-color: transparent;">
		              <ul class="list-group mb-3">
		                <li class="list-group-item d-flex justify-content-between">
		                  <span>Total (₱)</span>
		                  <strong id="total"></strong>
		                </li>
	              	  </ul>
	                  <button class="btn btn-warning" type="submit">Continue to checkout</button>
	              </div> 
            </div>


            <!-- product view -->
            
            <div class="col-md-7 col-lg-8">
              <h4 class="mb-3 text-warning">OMS PRODUCT</h4>

              <div class="">

                <div class="row g-2">
                	
					<c:forEach var="product" items="${allProductList}">
					
						<c:choose>
							<c:when test="${ product.productStatus == 1}">
								<div class="col-sm-6 col-md-12 col-lg-6 d-flex justify-content-center">
				                    <div class="card" style="width: 18rem;">
				                    
				                      <img src="${product.productPicture}" class="card-img-top" alt="image not available" width="70" height="190">
				                      <div class="card-body">
				                        <h5 class="card-title text-muted"><c:out value="${ product.productName }"/></h5>
				                        <h6 class="card-subtitle mb-2 text-muted"><c:out value="${ product.statusDescription }"/></h6>
				                        <p class="card-text text-danger"><c:out value="Not Available"/></p>
				                        <h6 class="text-muted"><fmt:formatNumber type="currency" value="${ product.price }" /></h6>
				                        <!-- ${product.productId} -->
				                        <div class="row">
				
				                          <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2 d-flex">
				                            <button href="#" class="btn btn-outline-dark px-2 py-2 disabled" onclick="addtocart()"><i class="fa fa-cart-plus"></i></button>
				                          </div>
				
				                          <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 d-flex">
				                            <button class="btn btn-link-dark px-2 disabled" onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
				                              <i class="fas fa-minus"></i>
				                            </button>
				      
				                            <input id="form1" min="1" name="quantity" value="0" type="number"
				                              class="form-control form-control-sm " disabled/>
				      
				                            <button class="btn btn-link-dark px-2 disabled" onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
				                              <i class="fas fa-plus"></i>
				                          </button>
				                          </div>
				                        </div>
				                        
				                      </div>
				                    </div>
				                  </div>
							</c:when>
							
							<c:when test="${ product.productStatus == 0}">
								<div class="col-sm-6 col-md-12 col-lg-6 d-flex justify-content-center">
				                    <div class="card" style="width: 18rem;" >
				                      <img src="${product.productPicture}" class="card-img-top" alt="image not available" width="70" height="190">
				                      <div class="card-body">
				                        <h5 class="card-title"><c:out value="${ product.productName }"/></h5>
				                        <h6 class="card-subtitle mb-2 text-success"><c:out value="${ product.statusDescription }"/></h6>
				                        <p class="card-text"><c:out value="${ product.productDescription }"/></p>
				                        <h6><fmt:formatNumber type="currency" value="${ product.price }" /></h6>
				                        <!-- ${product.productId} -->
				                        <div class="row">
				
				                          <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2 d-flex">
				                            <button class="btn btn-outline-dark px-2 py-2" onclick="addtocart('${product.productId}', '${ product.productName }','${ product.price }')"><i class="fa fa-cart-plus"></i></button>
				                          </div>
				
				                          <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 d-flex">
				                            <button class="btn btn-link-dark px-2" onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
				                              <i class="fas fa-minus"></i>
				                            </button>
				      
				                            <input min="1" name="quantity" value="1" type="number" class="quantity form-control form-control-sm" key="${product.productId}"/>
				      
				                            <button class="btn btn-link-dark px-2" onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
				                              <i class="fas fa-plus"></i>
				                          	</button>
				                          </div>
				                        </div>
				                        
				                      </div>
				                    </div>
				                 </div>
			              </c:when>
						
						
						</c:choose>
					
						
						
						
						
	                  
                  	</c:forEach>

                  <!-- <div class="col-sm-6 col-md-12 col-lg-6 d-flex justify-content-center">
                    <div class="card" style="width: 18rem;">
                      <img src="https://thehappyhousewife.com/cooking/files/2008/04/chocolate-chip-cookie.jpg" class="card-img-top" alt="image not available" width="70" height="190">
                      <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="#" class="card-link">Card link</a>
                        <a href="#" class="card-link">Another link</a>
                      </div>
                    </div>
                  </div> -->
                  <hr class="my-2 text-warning">
                  
                  <nav class="mt-2">
					<ul id="show_paginator" class="pagination"></ul>
				  </nav>
      
                  <!-- pagination -->
              </div>
            </div>
          </div>
          
        </main>
      </div>
      
      <footer class="bg-dark p-2 text-center">
          <div class="container">
              <p class="text-white">All Right Reserved By OMS</p>
          </div>
      </footer>
 </div>
</body>
<script type="text/javascript">


let currentpage = ${currentPage};
let totalpage = ${totalpage};
$('#show_paginator').bootpag({
    total: totalpage,
    page: currentpage,
    maxVisible: 5,
}).on("page", function(event, num){
   // $(".page-link").html("Page " + num); // or some ajax content loading...
 	openCustomerProductPage(num, 4, "product_status", "ASC");
    // ... after content load -> change total to 10
    //$(this).bootpag({total: 10, maxVisible: 10});
 
});

$('a').addClass('page-link');



var cart = [];



function addtocart(id, productName, price){
	let quantity = null;
	$(".quantity").each(function () {  
		if($(this).attr('key') == id){
			quantity = $(this).val();
			console.log(quantity);
		}
    })
	console.log('quantity = '+quantity);
	newPrice = parseInt(quantity) * price;
	console.log(quantity)
	
	
	let isTrue = false;
	let index = null;
	
	for(let i = 0; i<cart.length; i++){
		if(cart[i].id == id){
			isTrue = true
			index = i;
			break;
		}
	}
	
	if(isTrue){
		updatedQuantity = parseInt(cart[index].quantity) + parseInt(quantity);
		insertPrice = price * parseInt(quantity);
		updatedPrice = cart[index].price + insertPrice;
		let product = {
				id:id,
				productName:productName,
				quantity: updatedQuantity,
				price: updatedPrice
		};
		
		cart[index] = product;
		displaycart();
	}
	else{
		let product = {
				id:id,
				productName:productName,
				quantity: quantity,
				price: newPrice
		};
		cart.push(product)
		displaycart();
	}
	
	quantity = $('.quantity').val("1");
//	console.log(cart);
	
}

function delElement(a){
    cart.splice(a, 1);
    displaycart();
}

 
function displaycart(){
    let j = 0;
    let total=0.00;
    document.getElementById("count").innerHTML=cart.length;
    if(cart.length==0){
        document.getElementById('cartItem').innerHTML = "<li class='list-group-item d-flex justify-content-between lh-sm'>Your cart is empty</li>"
        document.getElementById("total").innerHTML = "₱ "+0+".00";
    }
    else{
        document.getElementById("cartItem").innerHTML = cart.map((items)=>
        {
            var {id, productName, quantity, price} = items;
            total=total+price;
            let currency = Intl.NumberFormat('en-PH');
            
            document.getElementById("total").innerHTML = "₱ "+currency.format(total);
            return(
            		"<li class='list-group-item d-flex justify-content-between lh-sm'>"+
                    "<div>"+
                      "<h6 class='my-0'>"+productName+"</h6>"+
                      "<small class='text-muted'>Quantity: "+quantity+"</small>"+
                    "</div>"+
                    "<span class='text-muted'>₱ "+currency.format(price)+"</span>"+
                  "</li>"
            );
        }).join('');
    }

    
}

displaycart();





backToTop = $('#btn-back-to-top');

window.onscroll = function () {
	  scrollFunction();
};


function scrollFunction() {
	  if (
	    document.body.scrollTop > 20 ||
	    document.documentElement.scrollTop > 20
	  ) {
		  backToTop.css("display","block");
	  } else {
		  backToTop.css("display","none");
	  }
}

$(document).ready(function(){
	backToTop.click(function(){
		backtotop();
	});
});

function backtotop(){
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
}
</script>