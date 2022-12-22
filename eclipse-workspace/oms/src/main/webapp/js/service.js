function checkingSession(){
	$.ajax({
		url: contextPath + "checksessioncontroller", 
		method: "POST",
		success: function(result){
			$("#maindiv").html(result);
		}
	});
}

function initLoginPage(){
	$("#loginButton").click(function(){
		username = $('#username').val();
		password = $('#password').val();
		
		if(username == "" || password == ""){
			alert("Please type your username and password.")
		}
		else if(password.length < 8){
			alert("Password must be 8 characters or more.")
		}
		else{
			$("#loginButton").prop('disabled', true)
			login(username,password);
		}
		
	});
}

function login(username, password){
	$.ajax({
		url: contextPath + "signin",
		method: "POST",
		data : {
			username: username,
			password: password
		},
		success: function(result){
			if(result.length > 100){
				$("#maindiv").html(result);
			}
			else{
				$("#loginButton").prop('disabled', false)
				alert(result);
			}
		}
	});
}


function initlogoutpage(){
	$(".logoutbtn").click(function(){
		logout();
	});
}

function logout(){
	$.ajax({
		url: contextPath + "logoutcontroller",
		method:"POST",
		success: function(result){
			$("#maindiv").html(result);
			
		}
	})
}


function initForgotPage(){
		$("#forgotBtn").click(function(){
			
			username = $('#username').val();
			email = $('#email').val();
			
			
			if(username == "" || email == ""){
				alert("Please type your username and email to proceed.");
			}
			else if(!isEmailValid(email)){
				alert("Please enter a valid email address");
			}
			else{
				$("#forgotBtn").prop('disabled', true)
				forgotPassword(username, email);
			}
		
	});
}

function forgotPassword(username, email){
	$.ajax({
		url: contextPath + "forgotpasswordcontroller",
		method: "POST",
		data : {
			username: username,
			email: email
		},
		
		success: function(result){
			//$("#maindiv").html(result);
			$("#forgotBtn").prop('disabled', false)
			if(result == "Wrong credentials. Please try again"){
				alert(result);
			}
			else{
				alert(result);
				//checkingSession();
			}
		}
	});
}

function isEmailValid(email){
	let regexp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	return regexp.test(email.toLowerCase());
}

function isImageUrlValid(url){
	let regexp = /(https:\/\/)([^\s(["<,>/]*)(\/)[^\s[",><]*(.png|.jpg)(\?[^\s[",><]*)?/g;
	
	return regexp.test(url);
}

function isPriceValid(price){
	let pattern = /^\d+(\.\d{1,2})?$/g;
	
	return pattern.test(price);
}

function initAddNewUser(){
	$("#addnewuserbtn").click(function(){
		username = $('#username').val();
		email = $('#email').val();
		password = $('#password').val();
		role = $('#role').val();
		// TODO password lenght
		if(username == "" || email == ""||password == ""){
			alert("Please type all needed fields to proceed");
		}
		else if(password.length < 8){
			alert("Password must be 8 characters or more")
		}
		else if(!isEmailValid(email)){
				alert("Please enter a valid email address");
		}
		else{
			$("#addnewuserbtn").prop('disabled', true);
			addNewUser(username, email, password, role);
		}
	});
}

function addNewUser(username, email, password, role){
	$.ajax({
		url: contextPath + "addnewusercontroller",
		method:"POST",
		data:{
			username: username,
			email:email,
			password:password,
			role:role
		},
		success: function(result){
			//$("#dashboardbody").html(result);
			$("#addnewuserbtn").prop('disabled', false);
			alert(result);
			openAddUserPage();
		}
	})
}

/*function initEditUserSave(){
	$("#saveEditUser").click(function(){
		username = $('#usernameEditUser').val();
		email = $('#emailEditUser').val();
		role = $('#roleEditUser').val();
		// TODO password lenght
		if(username == "" || email == ""){
			alert("please type all needed fields to proceed.");
		}
		else if(!isEmailValid(email)){
				alert("please enter a valid email address");
		}
		else{
			editUserSave(username, email, role);
		}
	});
}
*/

function editUserSave(userid){
		$("#saveEditUser").prop('disabled', true)
		$("#disableUser").prop('disabled', true)
		username = $('#usernameEditUser').val();
		email = $('#emailEditUser').val();
		role = $('#roleEditUser').val();
		if(username == "" || email == ""){
			alert("please type all needed fields to proceed.");
		}
		else if(!isEmailValid(email)){
			alert("please enter a valid email address");
		}
		else{
			$.ajax({
				url: contextPath + "editusercontroller",
				method:"POST",
				data:{
					userId: userid,
					username: username,
					email:email,
					role:role
				},
				success: function(result){
							if(result.length > 100){
								// wala muna
								//$("#maindiv").html(result);
							}
							else{
								$("#saveEditUser").prop('disabled', false);
								$("#disableUser").prop('disabled', false)
								alert(result);
								openViewAllUser(1, 5, "user_id", "ASC");
							}
						}
			})
		}
	
}

/*function initEditUserDisable(){
		$("#disableUser").click(function(){
		username = $('#usernameEditUser').val();
		email = $('#emailEditUser').val();
		role = $('#roleEditUser').val();
		alert("role value:"+role)
		
		if(username == "" || email == ""){
			alert("please type all needed fields to proceed.");
		}
		else if(!isEmailValid(email)){
				alert("please enter a valid email address");
		}
		else{
			editUserDisable(username, email, role);
		}
	});
}
*/

function editUserDisable(userid, status){
		$("#disableUser").prop('disabled', true);
		$("#enableUser").prop('disabled', true);
		$("#saveEditUser").prop('disabled', true)
		username = $('#usernameEditUser').val();
		email = $('#emailEditUser').val();
		role = $('#roleEditUser').val();
		
		if(username == "" || email == ""){
			alert("Please type all needed fields to proceed.");
		}
		else if(!isEmailValid(email)){
				alert("Please enter a valid email address");
		}
		else{
			$.ajax({
				url: contextPath + "disableusercontroller",
				method:"POST",
				data:{
					userId: userid,
					username: username,
					email:email,
					role:role,
					status:status
				},
				success: function(result){
					//$("#maindiv").html(result);
					if(result == "Successfully disabled the user"){
						$("#saveEditUser").prop('disabled', false);
						$("#disableUser").prop('disabled', true);
						alert(result);
						openViewAllUser(1, 5, "user_id", "ASC");
					}
					else if(result == "Successfully enabled the user"){
						$("#saveEditUser").prop('disabled', false);
						$("#enableUser").prop('disabled', true);
						alert(result);
						openViewAllUser(1, 5, "user_id", "ASC");
					}
					else{
						$("#disableUser").prop('disabled', false);
						$("#enableUser").prop('disabled', false);
						$("#saveEditUser").prop('disabled', false);
						alert(result);
					}
				}
			})
		}
		
}

/*function editUserEnable(userid){
	$.ajax({
		url: contextPath + "disableusercontroller",
		method:"GET",
		data:{
			userId: userid,
		},
		success: function(result){
			$("#maindiv").html(result);
		}
	})
}
*/
// add product
function initAddProduct() {
	
    $("#addproduct").click(function(){

        productName = $('#productName').val();
        productDescription = $('#productDescription').val();
        productPicture = $('#photo').val();
        productPrice = $('#productPrice').val();
        productStatus = $('#productStatus').val();
        console.log(productStatus)
//        productId = $('#productId').val();
        //let file = $("#photo").get(0).files;
         //parseFloat(price)
        if(productName == "" || productDescription == "" || productPicture == "" || productPrice == "" || productStatus == ""){
			alert("please type all needed fields to proceed.");
		}
		else if(productPrice < 0){
			alert("Please make the price positive number")
		}
		else if(!isImageUrlValid(productPicture)){
			alert("Image URL is not valid image format. Please try again");
		}
		else if(!isPriceValid(productPrice)){
			alert("Please make the price 2 decimal places")
		}
		else{
        	AddProduct(productName,productDescription,productPrice,productStatus,productPicture);
		}

        
        
    });
}

function AddProduct(productName,productDescription,productPrice,productStatus,productPicture) {
	$("#addproduct").prop('disabled', true)
    $.ajax({
        url: contextPath + "addnewproductcontroller",
        method: "POST",
        data : {
			productName:productName,
			productDescription:productDescription,
			price:productPrice,
			productStatus:productStatus,
			productPicture:productPicture
		},
        success: function(result) {
			alert(result)
            //$("#dashboardbody").html(result);
            $("#addproduct").prop('disabled', false);
            openAddProductPage();
        }
    });
}

// TODO
// edit product
function initEditProduct() {
	
    $("#editproduct").click(function(){
        productName = $('#productName').val();
        productDescription = $('#productDescription').val();
        productPicture = $('#productPicture').val();
        productPrice = $('#productPrice').val();
        productStatus = $('#productStatus').val();
        productId = $('#productId').val();
        if(productName == "" || productDescription == "" || productPicture == "" || productPrice == "" || productStatus == ""){
			alert("Please type all needed fields to proceed.");
		}
		else if(productPrice < 0){
			alert("Please make the price positive number")
		}
		else if(!isImageUrlValid(productPicture)){
			alert("Image URL is not valid image format. Please try again");
		}
		else if(!isPriceValid(productPrice)){
			alert("Please make the price 2 decimal places")
		}
		else{
        	editProduct(productId, productName,productDescription,productPrice,productStatus,productPicture);
		}
    });
}

function editProduct(productId, productName,productDescription,productPrice,productStatus,productPicture) {
	$("#editproduct").prop('disabled', true)
    $.ajax({
        url: contextPath + "editproductcontroller",
        method: "POST",
        data :{
			productName:productName,
			productDescription:productDescription,
			price:productPrice,
			productStatus:productStatus,
			productPicture:productPicture,
			productId: productId
		},
        success: function(result) {
            //$("#dashboardbody").html(result);
            alert(result);
            openViewAllProduct(1, 5, "product_id", "ASC");
            //$("#editproduct").prop('disabled', false)
        }
    });
}

//saveBtn for edit profile userid
function initEditProfile(){
	 $("#saveBtn").click(function(){
			userid = $('#userid').val();
			email = $('#email').val();
			username = $('#username').val();
			oldpassword = $('#oldpassword').val();
			newpassword = $('#newpassword').val();
			
			if(userid == "" || email == "" || username == ""){
				alert("Please type all the needed fields to proceed.");
			}
			else if(!isEmailValid(email)){
				alert("Please enter a valid email address");
			}
			else{
				saveEditProfile("changeEmail", userid, username, email);
			}
	});
}

function initChangePassword(){
	 $("#changepassbtn").click(function(){
			userid = $('#userid').val();
			email = $('#email').val();
			username = $('#username').val();
			oldpassword = $('#oldpassword').val();
			newpassword = $('#newpassword').val();
			reenterpassword = $('#reenterpassword').val();
			
			if(oldpassword == "" || newpassword == ""){
				alert("Please type all the needed fields to proceed.");
			}
			else if(oldpassword.length < 8 || newpassword < 8){
				alert("Password fields must be 8 characters or more");
			}
			else if(newpassword !== reenterpassword){
				alert("Password does not matched to the new password")
			}
			else{
				isOkayToProceed = confirm("Are you sure you want to proceed? ");
				if(isOkayToProceed){
					changePasswordProfile("changePassword", userid, newpassword, oldpassword);
				}
				
			}
	});	
}

function saveEditProfile(action, userid, username, email){
	$.ajax({
        url: contextPath + "editprofilecontroller",
        method: "POST",
        data : {
			action:action,
			userId:userid,
			email:email,
//			password:oldpassword
		},
        success: function(result) {
			alert(result);
			alert(username);
			openViewProfile(username);
            //$("#maindiv").html(result);
        }
    });
}

function changePasswordProfile(action, userid, newpassword, oldpassword){
	$.ajax({
        url: contextPath + "editprofilecontroller",
        method: "POST",
        data : {
			action:action,
			userId:userid,
			oldPassword:oldpassword,
			newPassword:newpassword
		},
        success: function(result) {
            // $("#maindiv").html(result);
            alert(result);
            logout();
        }
    });
}
var totalrow = 0;
	var row = 0;
	var confirmArray = [];
	var addQuantity = [];
	var username = $('#username').text();
	var deliveryDate = [];
	var getQuantity = [];
	var getProductName;
	 	var trig = false;
	 	

function getAllRow(){
	
	
	$("#getRowTable").click(function() {
		
			getAll();

			var $table = $('<table/>');
			$table.append( '<tr><th>' + 'Product Name' + '</th><th>' + 'Added Quantites' + '</th></tr>');
			for(var i=0; i<confirmArray.length; i++){
			    $table.append( '<tr><td>' + confirmArray[i] + '</td><td>' + addQuantity[i] + '</td></tr>' );
			}

			$table.append( '<tr><td>' + 'Total Production' + '</td><td>' + totalrow + '</td></tr>');
			$('#here_table').append($table);

			 $("#myModal").modal('show');  

	});

	$("#confirmModal").click(function(){
		 
		AddProduction(confirmArray, addQuantity, username, deliveryDate);
		totalrow = 0;
		 row = 0;
		 confirmArray.length=0;
		 addQuantity.length=0;
		 deliveryDate.length=0;
		 getQuantity.length=0;
		 getProductName="";
	 	 trig = false;
	});

}

function hideModal(){
	$("#cancelModal").click(function(){
		totalrow = 0;
		 row = 0;
		 confirmArray.length=0;
		 addQuantity.length=0;
		 deliveryDate.length=0;
		 getQuantity.length=0;
		 getProductName="";
	 	 trig = false;
	 	 openViewProduction(1, 5, "product_name", "DESC");
		$('#myModal').modal('hide');   
	});
}

function AddProduction(confirmArray, addQuantity, username, deliveryDate) {
	$("#confirmModal").prop('disabled', true)
    $.ajax({
        url: contextPath + "addnewproductquantitiescontroller",
        method: 'POST',
		datatype: 'json',	
		data: {
			jsonProductName:confirmArray,
			jsonQuantity:addQuantity,
			jsonDeliveryDate:deliveryDate,
			username:username
			},
        success: function(result) {
			alert(result);
			openViewProduction(1, 5, "product_name", "DESC");
			$('#myModal').modal('hide');	
        }
    });
}


function initOpenOrderReport() {
	
	$("#orderSummary").click(function(){
		dateReport = $('#dateReport').val();
		
		openViewAllReports($("#dateReport").val(), 1, 5, "od.product_id", "ASC");
	});

}

function initSearchOrderReport() {

	$("#searchReport").click(function(){
		dateReport = $('#dateReport').val();
		
		openViewAllReports($("#dateReport").val(), 1, 5, "od.product_id", "ASC");
	});
}

function getAll(){
	$("table > tbody > tr").each(function () {
	 		if($(this).closest('tr').find('.inputQuantities').val()==""){

			}else{
				if(confirmArray.length==0){
	 				

	 				    if($(this).closest('tr').find('.inputQuantities').val()==""){
	 						row = 0;

	 					}else{

	 						row = parseInt($(this).closest('tr').find('.inputQuantities').val());
	 						confirmArray.push($(this).closest('tr').find('.getProdName').text());
	 						addQuantity.push($(this).closest('tr').find('.inputQuantities').val());
	 						deliveryDate.push($(this).closest('tr').find('.getDate').text());
	 						getQuantity.push($(this).closest('tr').find('.getProdName').text());
	 					}	
	 					totalrow += row;  
				  }else{
					  if($(this).closest('tr').find('.inputQuantities').val()==""){
	 						row = 0;

	 					}else{
	 						addQuantity.push($(this).closest('tr').find('.inputQuantities').val());
	 						row = parseInt($(this).closest('tr').find('.inputQuantities').val());
	 						getProductName = $(this).closest('tr').find('.getProdName').text();
	 						
	 					}	
	 					totalrow += row;  
	 					
					  for(var i=0; i<getQuantity.length; i++){
						  
							  if(getProductName==getQuantity[i]){
								 var before = addQuantity[i];
								 addQuantity[i] =  $(this).closest('tr').find('.inputQuantities').val();
								 totalrow = totalrow-before;
								  trig = true;
								  break;
						  	  }else{
						  		  trig = false; 
						  	  }
					  }
					  		
				  	if(trig==false){
		 				

		 				    if($(this).closest('tr').find('.inputQuantities').val()==""){
		 						row = 0;

		 					}else{
								getQuantity.push($(this).closest('tr').find('.getProdName').text());
		 						deliveryDate.push($(this).closest('tr').find('.getDate').text());
		 						confirmArray.push($(this).closest('tr').find('.getProdName').text());

		 					}	  
		 		
				  	}
				  	
				  }
			}
		});
}

