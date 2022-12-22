<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>OMS</title>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}' + '/';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/service.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getservice.js"></script>

<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-bootpag/lib/jquery.bootpag.js"></script>
<title></title>
<style>
  .required:after {
    content:" *";
    color: red;
  }
</style>
</head>
<body id="maindiv">
</body>

<script type="text/javascript">
	$(document).ready(function(){
		checkingSession();
//		$.ajax({
//			url: contextPath + "checksessioncontroller", 
//			method: "POST",
//			success: function(result){
//				$("#maindiv").html(result);
//			}
//		});	
	});
</script>

</html>