<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="drugLoginApp">

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>DrugStore</title>
	<link rel="icon" type="image/png" href="resources/assets/images/favicon.ico">
        
<!--*********** CSS Styles ***************-->
	<link href="resources/assets/css/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/assets/css/login-style.css" rel="stylesheet">
    <link href="resources/assets/css/responsive.css" rel="stylesheet">
 <!--*********** CSS Styles ***************-->	
				
</head>



<body id="index" ng-controller="LoginController">
	<div class="container-fluid">
		<div class="col-sm-12 col-lg-12 TitleHeading">
			<h1>Drug Store</h1>
		</div>
		
		<div class="col-xs-12 col-sm-6 col-lg-6 text-center">
			<img src="resources/assets/images/mainpic_slice.png" />
		</div>
		
		<div class="col-xs-12 col-sm-6 col-lg-6">
			<form name="loginForm">
				<div class="col-xs-12 col-sm-12 col-lg-12 form-group">
					<input type="text" class="form-control" placeholder="USER NAME / LOGIN ID" required ng-model="loginUserName"/> 
				</div>
				
				<div class="col-xs-12 col-sm-12 col-lg-12 form-group">
					<input type="password" class="form-control" placeholder="****" required ng-model="loginPassword"/>
				</div>
				
				<div class="col-xs-12 col-sm-12 col-lg-12 form-group">
					<p><a href="#">forget password? </a>
						<a href="#" target="_blank">CLICK HERE</a>
					</p>
				</div>
				
				<div class="col-xs-12 col-sm-12 col-lg-12 form-group">
					<button type="submit" class="btn" ng-click="authenticateUser()">LOGIN</button>
				</div>
			</form>
		</div>
	</div>
</body>
	

<script src="resources/assets/js/angularjs/angular.min.js" type="text/javascript"></script>
<script>
var drugLoginApp = angular.module('drugLoginApp', []);

drugLoginApp.controller('LoginController', ['$scope','$http', function($scope,$http) {

	$scope.getJSONData = function() {
		var JSONData = {
	            "userName": $scope.loginUserName,
	            "password" : $scope.loginPassword
	        };
		return JSONData;
	}
	
	$scope.authenticateUser = function() {
		var json = $scope.getJSONData();
		var response = $http.post('./authenticateUser', JSON.stringify(json));
        response.success(function(data) {
            if (data.responseCode == "fail") {
            	
                window.scrollTo(0, 0);
                alert('its gone');
            } else{
            	//alert('success');
            	window.location.href = './HOME';
            }
        });
        response.error(function() {
        	
        	alert('its gone bhai');
        });
	}
}]);
</script>
</html>