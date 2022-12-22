
$(function() {
	 $('div#admin-content').hide();
  $('a#product').click(function(event){
    event.preventDefault();
    $('div#admin-content').toggle();
  });
});   

function myAdmin() {
  var ac = document.getElementById("admin-content");
  var pc = document.getElementById("product-content");
  var oc = document.getElementById("orders-content");
  var pcc = document.getElementById("production-content");
  var rc = document.getElementById("reporting-content");
  document.getElementById("header-content").innerHTML = "Admin User";
  
    ac.style.display = "block";
    pc.style.display = "none";
    oc.style.display = "none";
    pcc.style.display = "none";
    rc.style.display = "none";
 
}

function myProduct() {
  var ac = document.getElementById("admin-content");
  var pc = document.getElementById("product-content");
  var oc = document.getElementById("orders-content");
  var pcc = document.getElementById("production-content");
  var rc = document.getElementById("reporting-content");
  document.getElementById("header-content").innerHTML = "Product List";
 
    ac.style.display = "none";
    pc.style.display = "block";
    oc.style.display = "none";
    pcc.style.display = "none";
    rc.style.display = "none";
  
}

function myOrders() {
  var ac = document.getElementById("admin-content");
  var pc = document.getElementById("product-content");
  var oc = document.getElementById("orders-content");
  var pcc = document.getElementById("production-content");
  var rc = document.getElementById("reporting-content");
  
 
    ac.style.display = "none";
    pc.style.display = "none";
    oc.style.display = "block";
    pcc.style.display = "none";
    rc.style.display = "none";
 
}

function myProduction() {
  var ac = document.getElementById("admin-content");
  var pc = document.getElementById("product-content");
  var oc = document.getElementById("orders-content");
  var pcc = document.getElementById("production-content");
  var rc = document.getElementById("reporting-content");
  
  
    ac.style.display = "none";
    pc.style.display = "none";
    oc.style.display = "none";
    pcc.style.display = "block";
    rc.style.display = "none";
  
}

function myReport() {
  var ac = document.getElementById("admin-content");
  var pc = document.getElementById("product-content");
  var oc = document.getElementById("orders-content");
  var pcc = document.getElementById("production-content");
  var rc = document.getElementById("reporting-content");
  
 
    ac.style.display = "none";
    pc.style.display = "none";
    oc.style.display = "none";
    pcc.style.display = "none";
    rc.style.display = "block";
  
}

function validation() {
	  'use strict'

	  var forms = document.querySelectorAll('.needs-validation')

	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	};
	
	
function editclick(){
	
	document.getElementById("gmailText").disabled = false;
	document.getElementById("userText").disabled = false;
	document.getElementById("passText").disabled = false;
	document.getElementById("saveBtn").disabled = false;
	
	}
	
	
function editUser(){
	document.getElementById("usernameEditUser").disabled = false;
	document.getElementById("emailEditUser").disabled = false;
}
	
