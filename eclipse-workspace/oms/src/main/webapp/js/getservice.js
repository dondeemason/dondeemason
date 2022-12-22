function initbackLandingPage(){
	$("#backLandingPageBtn").click(function(){
		backLandingPage();
	});
}

function backLandingPage(){
	$.ajax({
		url: contextPath + "checksessioncontroller",
		method: "GET",
		
		success: function(result){
			$("#maindiv").html(result);
		}
	});
}

function initCancel(){
	$("#cancelBtn").click(function(){
		cancel();
	});
}

function cancel(){
	$.ajax({
		url: contextPath + "checksessioncontroller",
		method: "GET",
		
		success: function(result){
			$("#maindiv").html(result);
		}
	});
}


function initopenLoginPage(){
	$("#openLogin").click(function(){
		openLoginPage();
	});
}

function openLoginPage(){
	$.ajax({
		url: contextPath + "signin",
		method: "GET",
		success: function(result){
			console.log(result);
			$("#maindiv").html(result);
		}
	});
}

function initopenForgotPassPage(){
	$("#openForgotPassword").click(function(){
		openForgotPassPage();
	});
}

function openForgotPassPage(){
	$.ajax({
		url: contextPath + "forgotpasswordcontroller",
		method: "GET",
		
		success: function(result){
			$("#maindiv").html(result);
		}
	});
}


function initOpenAddUser(){
	$("#addUserBtn").click(function(){
		openAddUserPage();
	});
}


function openAddUserPage(){
	$.ajax({
		url: contextPath + "addnewusercontroller",
		method: "GET",
		
		success: function(result){
			$("#dashboardbody").html(result);
		}
	});
}

// automatic 
function initViewAllUser(){
	openViewAllUser(1, 5, "user_id", "DESC");
}

// open for navigation
function initOpenViewAllUser(){
	$("#viewUsers").click(function(){
		openViewAllUser(1, 5, "user_id", "ASC");
		$(".viewUsers").addClass('active'); // ito ang active
		$(".viewprofile").removeClass('active');
		$(".viewProduct").removeClass('active');
		$(".viewOrders").removeClass('active');
		
	});
}

function openViewAllUser(page, recordsperpage, orderby, sortby){
	$.ajax({
		url: contextPath + "viewallusercontroller",
		method: "GET",
		data:{
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			$("#dashboardbody").html(result);
		}
	});
}


function initOpenEditPage(userid){
	openEditUser(userid);
}

function openEditUser(userid){
	$.ajax({
		url: contextPath + "editusercontroller",
		method:"GET",
		data:{
			userId:userid
		},
		success: function(result){
			$("#dashboardbody").html(result);
		}
	})
}


//function initOpenViewProfile(){
//	openViewProfile(userid);
//}

// open profile
function openViewProfile(username){
	$(".viewUsers").removeClass('active');
	$(".viewprofile").addClass('active'); // ito ang active
	$(".viewProduct").removeClass('active');
	$(".viewOrders").removeClass('active');
	$(".viewExpectedOrders").removeClass('active');
	$(".viewProductProduced").removeClass('active');
	
	
	$.ajax({
		url: contextPath + "editprofilecontroller",
		method:"GET",
		data:{
			username:username
		},
		success: function(result){
			$("#dashboardbody").html(result);
		}
	})
}


//view all products
function initViewAllProduct(){
	$("#viewProduct").click(function(){
		openViewAllProduct(1, 5, "product_id", "DESC");
		$(".viewUsers").removeClass('active');
		$(".viewprofile").removeClass('active');
		$(".viewProduct").addClass('active'); // ito ang active
		$(".viewOrders").removeClass('active');
	});
}

function openViewAllProduct(page, recordsperpage, orderby, sortby){
	$.ajax({
		url: contextPath + "viewallproductcontroller",
		method: "GET",
		data:{
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			console.log(result)
			$("#dashboardbody").html(result);
		}
	});
}


// add product button trigger
function initOpenAddProductPage(){
	
	$("#addproduct").click(function(){
		openAddProductPage();
	});
}

function openAddProductPage(){
	$.ajax({
		url: contextPath + "addnewproductcontroller",
		method: "GET",
		
		success: function(result){
			console.log(result)
			$("#dashboardbody").html(result);
		}
	});
}

function getEditProduct(productId){
	$.ajax({
		url: contextPath + "editproductcontroller",
		method:"GET",
		data:{
			productId: productId
		},
		success: function(result){
			$("#dashboardbody").html(result);
		}
	})
}

function initAllorder(){
	openViewAllOrders(1, 5, "order_date", "DESC");
}

function initViewAllOrders(){
	$("#viewOrders").click(function(){
		openViewAllOrders(1, 5, "order_date", "DESC");
		$(".viewUsers").removeClass('active');
		$(".viewprofile").removeClass('active');
		$(".viewProduct").removeClass('active'); 
		$(".viewOrders").addClass('active'); // ito ang active
	});
}

function openViewAllOrders(page, recordsperpage, orderby, sortby){
	$.ajax({
		url: contextPath + "viewallordertakercontroller",
		method: "GET",
		data:{
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			console.log(result)
			$("#dashboardbody").html(result);
		}
	});
}

//view all products in customer page
function initCustomerProductPage(){
	$(".orderNow").click(function(){
		openCustomerProductPage(1, 4, "product_status", "ASC");
	});
}

function openCustomerProductPage(page, recordsperpage, orderby, sortby){
	$.ajax({
		url: contextPath + "viewallproductcustomercontroller",
		method: "GET",
		data:{
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			$("#root").html(result);
		}
	});
}
var today = new Date();
var dd = today.getDate();

var mm = today.getMonth()+1; 
var yyyy = today.getFullYear();
if(dd<10) 
{
    dd='0'+dd;
} 

if(mm<10) 
{
    mm='0'+mm;
} 


var globalDate = mm+'/'+dd+'/'+yyyy;


function initExpectedOrder(){
	openViewAllExpectedOrder(1, 5, "pd.product_id", "ASC");	
}
function initViewAllExpectedOrder(){
	$("#viewExpectedOrders").click(function(){
		openViewAllExpectedOrder(1, 5, "pd.product_id", "ASC");	
		$(".viewExpectedOrders").addClass('active');
		$(".viewProductProduced").removeClass('active');
		$(".viewprofile").removeClass('active');
	});
}


function openViewAllExpectedOrder(page, recordsperpage, orderby, sortby){
	
	$.ajax({
		url: contextPath + "viewexpectedorders",
		method: "GET",
		data:{
			query:"SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE TO_CHAR(od.delivery_date, 'mm/dd/yyyy') = '" + globalDate + "' AND ot.product_id = pd.product_id AND  ot.order_id = od.order_id",
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			console.log(result)
			$("#dashboardbody").html(result);
		}
	});
}

function initViewProduction(){
	$("#viewProductProduced").click(function(){
		openViewProduction(1, 5, "product_name", "DESC");	
		$(".viewProductProduced").addClass('active');
		$(".viewExpectedOrders").removeClass('active');
		$(".viewprofile").removeClass('active');
	});
}

function openViewProduction(page, recordsperpage, orderby, sortby){
	$.ajax({
		url: contextPath + "viewallproductsforproduction",
		method: "GET",
		data:{
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby,
			query:"SELECT product_name, product_picture, TO_CHAR(CURRENT_TIMESTAMP, 'yyyy-MM-dd') AS date_produced FROM product_1"
		},

		success: function(result){
			console.log(result)
			$("#dashboardbody").html(result);
		}
	});
}

function initViewPm(){
	$("#pm").click(function() {
		viewPm(1, 5, "pd.product_id", "ASC");
	});
}



function viewPm(page, recordsperpage, orderby, sortby){
	if(globalDate==""){
		$.ajax({
		url: contextPath + "viewexpectedorders",
		method:"GET",
		data:{
			query:"SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE TO_CHAR(od.delivery_date, 'hh24:mi:ss') > '12:00:00' AND TO_CHAR(od.delivery_date, 'hh24:mi:ss') < '24:00:00' AND od.order_status < 3 AND ot.product_id = pd.product_id AND ot.order_id = od.order_id",
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			$("#dashboardbody").html(result);
		}
	});
	}else{
		$.ajax({
		url: contextPath + "viewexpectedorders",
		method:"GET",
		data:{
			query:"SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE TO_CHAR(od.delivery_date, 'mm/dd/yyyy') = '" + globalDate + "' AND TO_CHAR(od.delivery_date, 'hh24:mi:ss') > '12:00:00' AND TO_CHAR(od.delivery_date, 'hh24:mi:ss') < '24:00:00' AND od.order_status < 3 AND ot.product_id = pd.product_id AND ot.order_id = od.order_id",
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			$("#dashboardbody").html(result);
		}
	})
	}

}


function initViewAm(){
	$("#am").click(function() {
		viewAm(1, 5, "pd.product_id", "ASC");
	});
}

function viewAm(page, recordsperpage, orderby, sortby){
	if(globalDate==""){
		$.ajax({
			url: contextPath + "viewexpectedorders",
			method:"GET",
			data:{
				query:"SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE TO_CHAR(od.delivery_date, 'hh24:mi:ss') >= '00:00:00' AND TO_CHAR(od.delivery_date, 'hh24:mi:ss') < '12:00:00' AND od.order_status < 3 AND ot.product_id = pd.product_id AND ot.order_id = od.order_id",
				offset:page,
				nOfRecord:recordsperpage,
				orderby:orderby,
				sortby:sortby
			},
			success: function(result){
				$("#dashboardbody").html(result);
			}
		});
	}else{
		$.ajax({
			url: contextPath + "viewexpectedorders",
			method:"GET",
			data:{
				query:"SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE TO_CHAR(od.delivery_date, 'mm/dd/yyyy') = '" + globalDate + "' AND TO_CHAR(od.delivery_date, 'hh24:mi:ss') >= '00:00:00' AND TO_CHAR(od.delivery_date, 'hh24:mi:ss') < '12:00:00' AND od.order_status < 3 AND ot.product_id = pd.product_id AND ot.order_id = od.order_id",
				offset:page,
				nOfRecord:recordsperpage,
				orderby:orderby,
				sortby:sortby
			},
			success: function(result){
				$("#dashboardbody").html(result);
			}
		});
	}

}

function viewDate(){
       alert(document.getElementById("selectVal").value);
}

function initSearchProduction(){
	$("#searchBtn").click(function(){
		searchProduct(1, 5, "product_id", "ASC");	
	});
}

function searchProduct(page, recordsperpage, orderby, sortby){
		var search = $("#searchBar").val();
			$.ajax({
			url: contextPath + "viewallproductsforproduction",
			method:"GET",
			data:{
				offset:page,
				nOfRecord:recordsperpage,
				orderby:orderby,
				sortby:sortby,
				query:"SELECT product_name, product_picture, TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MM:SS AM') AS date_produced FROM product_1 WHERE LOWER(product_name) like '%" + search.toLowerCase() + "%'"
			},
			success: function(result){
				$("#dashboardbody").html(result);
			}

		});

}

function initSearchOrder(){
	$("#appybtn").click(function(){
		searchOrder(1, 5, "order_date", "DESC");
	});
}

function searchOrder(page, recordsperpage, orderby, sortby){
		var search = $("#searchBar").val();
			$.ajax({
			url: contextPath + "viewallordertakercontroller",
			method:"GET",
			data:{
				offset:page,
				nOfRecord:recordsperpage,
				orderby:orderby,
				sortby:sortby,
				query:"SELECT * FROM orders_1 WHERE order_status < 3 AND LOWER(product_name) LIKE '%" + search.toLowerCase() + "%' order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only"
			},
			success: function(result){
				$("#dashboardbody").html(result);
			}

		});

}

function getDate(){
	$("#date_btn").click(function(){
		var getDate = $("#datepicker").val();
		globalDate = getDate;
		if(getDate == ""){
			alert("Please choose date");
		}else{
			$.ajax({
				url: contextPath + "viewexpectedorders",
				method:"GET",
				data:{
					query:"SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE TO_CHAR(od.delivery_date, 'mm/dd/yyyy') = '" + globalDate + "' AND od.order_status < 3 AND ot.product_id = pd.product_id AND ot.order_id = od.order_id",
					offset:1,
					nOfRecord:5,
					orderby:"pd.product_id",
					sortby:"ASC"
				},
				success: function(result){
					$("#dashboardbody").html(result);
				}

			});
		}

	});
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}





function initViewAllReports() {
	$("#viewReports").click(function(){
		let today = new Date().toISOString().slice(0, 10);	// Kunin date ngayon
	
		openViewAllReports(today, 1, 5, "od.product_id", "ASC");
		$(".viewUsers").removeClass('active');
		$(".viewprofile").removeClass('active');
		$(".viewProduct").removeClass('active');
		$(".viewOrders").removeClass('active'); 
		$(".viewReports").addClass('active');	// ito ang active
	});
}

function openViewAllReports(today, page, recordsperpage, orderby, sortby){
	$.ajax({
		url: contextPath + "viewordersummarycontroller",
		method: "GET",
		data: {
			date : today,
			offset:page,
			nOfRecord:recordsperpage,
			orderby:orderby,
			sortby:sortby
		},
		success: function(result){
			console.log(result)
			$("#dashboardbody").html(result);
		}
	});
}

function openEditOrderStatus(getPaymentStatus, getOrderStatus, getOrderId, remarks){
	$.ajax({
		url: contextPath + "editorderstatuscontroller",
		method:"POST",
		data:{
			orderStatus:getOrderStatus,
			paymentStatus:getPaymentStatus,
			orderId:getOrderId,
			remarks:remarks
		},
		success: function(result){
			alert(result);
			$("#myModal .close").click();
			openViewAllOrders(1, 5, "order_date", "DESC");
				
		}
	})
}
var getPaymentStatus;
var getOrderStatus;
var getOrderId;
var getRemarks;

function initGetOrder(orderid, orderStatus, paymentStatus){
	
	$("#userId").text(orderid);
	$('#orderStatusModal').val(orderStatus);
	$('#paymentStatus').val(paymentStatus);
	$("#myModal").modal('show');
	
	$('.saveBtn').click(function (){
		getRemarks = $('textarea#message-text').val();
		getOrderStatus = $( "#orderStatusModal option:selected" ).val();
		getPaymentStatus = $( "#paymentStatus option:selected" ).val();
		getOrderId = orderid;
		openEditOrderStatus(getPaymentStatus, getOrderStatus, getOrderId, getRemarks);
	});
}



