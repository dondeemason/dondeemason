<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userid" scope="session" value="${ user.userId }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-bootpag/lib/jquery.bootpag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getservice.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/service.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<title>Admin Dashboard</title>
</head>

<body>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar -->
        <div class="bg-dark" id="sidebar-wrapper">
            <div class="sidebar-heading text-center text-warning py-4 fs-4 fw-bold text-uppercase border-bottom"><i
                    class="fas fa-cookie" style='color: white'></i>MS</div>
            <div class="list-group list-group-flush my-3">
                <a href="#" id="viewUsers" class="viewUsers active list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                        class="fas fa-tachometer-alt me-2"></i>User List</a>
                <a href="#" id="viewProduct" class="viewProduct list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                        class="fas fa-gift me-2"></i>Products</a>
                <a href="#" id="viewOrders" class="viewOrders list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                        class="fas fa-project-diagram me-2"></i>Orders</a>
                <!--<a href="#" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                        class="fas fa-chart-line me-2"></i>Production</a>
                -->
                <a href="#" id="viewReports" class="viewReports list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                        class="fas fa-paperclip me-2"></i>Reports</a>
               <a href="#" onclick="openViewProfile('${user.username}')" class="viewprofile list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                        class="fas fa fa-user me-2"></i>Profile</a>
                <a href="#" class="logoutbtn list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
                        class="fas fa-power-off me-2"></i>Logout</a>
            </div>
        </div>
        <!-- /#sidebar-wrapper -->
		<!--  -->
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <nav class="navbar navbar-expand-lg navbar-dark bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left text-light fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-5 m-0 text-warning">Dashboard</h2>
                </div>
<!--  
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
-->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- 
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-text">
     
                            
                            
                            <a href="#" class="nav-link text-warning fw-bold" href="#" id="navbarDropdown" role="button" aria-expanded="false">
                            	${user.username}
                            </a>
                            
                             
                            <!-- <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li onclick="openViewProfile('${user.username}')"><a href="#" class="dropdown-item">Profile</a></li>
                                <li class="logoutbtn"><a href="#" class="dropdown-item">Logout</a></li>
                            </ul> 
                            
                        </li>
                        
                    </ul>
                    -->
                </div>
                <span class="navbar-text text-warning">
        			Signed in as: ${user.username}
      			</span>
            </nav>

            <div class="container-fluid px-4"> 
				<div id="dashboardbody"></div>
                
                
  <%--               <div class="row my-5">
                    <div class="col">
                        <div class="text-center" id="editUser"></div>
                    </div>
                </div> --%>	

            </div>
        </div>
    </div>
    

<%-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>    --%>	
    <script>
        var el = document.getElementById("wrapper");
        var toggleButton = document.getElementById("menu-toggle");

        toggleButton.onclick = function () {
            el.classList.toggle("toggled");
        };
    </script>
</body>


    
<script type="text/javascript">
		$( document ).ready(function(){
			// logout
			initlogoutpage();
			
			//click view all user records
			initOpenViewAllUser(); // sort by desc
			
			// add user
			initOpenAddUser();
			
			//init view all user records
			initViewAllUser(); // sort by desc
			
			// all product
			initViewAllProduct();
			
			// all orders
			initViewAllOrders();
			
			//all report
			initViewAllReports();
		});
</script>
</html>