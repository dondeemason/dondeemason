<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userid" scope="session" value="${ user.userId }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getservice.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/service.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>Order Dashboard</title>
</head>

<body>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar -->
        <div class="bg-dark" id="sidebar-wrapper">
            <div class="sidebar-heading text-center text-warning py-4 fs-4 fw-bold text-uppercase border-bottom"><i
                    class="fas fa-cookie" style='color: white'></i>MS</div>
            <div class="list-group list-group-flush my-3">        
                <a class="list-group-item list-group-item-action bg-transparent second-text fw-bold" href="#" id="viewOrders"><i
                        class="fas fa-project-diagram me-2"></i>Orders</a>
               
                <a class="logoutbtn list-group-item list-group-item-action bg-transparent text-danger fw-bold" href="#"><i
                        class="fas fa-power-off me-2"></i>Logout</a>
            </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left text-white fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-2 m-0">Dashboard</h2>
                </div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle fw-bold" href="#" id="navbarDropdown"
                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user me-2"></i>${user.username}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li onclick="openViewProfile('${user.username}')"><a class="dropdown-item">Profile</a></li>
                                <li class="logoutbtn"><a class="dropdown-item">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
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
			initViewAllOrders();
			initAllorder();
			initlogoutpage();
		});
</script>
</html>