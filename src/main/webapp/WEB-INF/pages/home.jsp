<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" data-ng-app="drugApp">

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>DrugStore</title>
	<link rel="icon" type="image/png" href="resources/assets/images/favicon.ico">
        
<!--*********** CSS Styles ***************-->
	<link href="resources/assets/css/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel='stylesheet' type='text/css'>
	<link href="resources/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/assets/css/custom.css" rel="stylesheet">
    <link href="resources/assets/css/accordionCss.css" rel="stylesheet">
    <link href="resources/assets/css/responsive.css" rel="stylesheet">
 <!--*********** CSS Styles ***************-->	
				
</head>

<body data-ng-controller="MainController">
    <div class="container-fluid">
	    <div class="row">
			<header>
				<div class="header-logo">
					<a ng-click="toggleClick()"><img class="img-responsive" src="resources/assets/images/logo-drugstore.png" /></a>
				</div>
				
				<div class="header-content">
					<nav class="navbar navbar-default">
  						<div class="container-fluid">
	    					<div class="navbar-header">
      							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        							<span class="sr-only">Toggle navigation</span>
        							<span class="icon-bar"></span>
        							<span class="icon-bar"></span>
        							<span class="icon-bar"></span>
      							</button>
    						</div>

	    					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      							<ul class="nav navbar-nav navbar-right">
        							<li><a href="#" class="cart_style"><i class="fa fa-shopping-cart"></i> CART <span class="cart_count" data-reactid="59">0</span></a></a></li>
        							<li class="dropdown">
          								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          								<ul class="dropdown-menu">
            								<li><a href="#">Settings</a></li>
								            <li role="separator" class="divider"></li>
								            <li><a href="#" ng-click="logOut()">Logout</a></li>
          								</ul>
        							</li>
      							</ul>
    						</div>
  						</div>
					</nav>		
				</div>
			</header>
			
		
			<section>
				<div class="left-menu-bar" ng-class="{'left-menu-bar-thin' : toggleVrbl}">
  					<nav>
    					<div id="menu" class="menu">
      						<div class="menu-header">
								<span>
									<img class="img-responsive img-circle" src="resources/assets/images/user-logo.png" />
								</span>
							</div>
      						<ul>
        						<li class="active"><a ui-sref="P1"><i class="fa fa-tachometer"></i>Dashboard</a></li>
        						<li><a ui-sref="P2"><i class="fa fa-cubes"></i>Store</a>
          							<ul class="submenu">
            							<li><a ui-sref="P21"><i class="fa fa-cart-plus"></i>Add Drug</a></li>
            							<li><a ui-sref="P22"><i class="fa fa-gear"></i>Modify Drug</a></li>
          							</ul>
						        </li>
	        					<li><a href="#"><i class="fa fa-folder-open"></i>View Orders</a></li>
	        					<li><a href="#"><i class="fa fa-cc-visa"></i>Transactions</a>
	          						<ul class="submenu">
	            						<li><a href="#"><i class="fa fa-pie-chart"></i>My Order status</a></li>
	          						</ul>
	        					</li>
	        					<li><a href="#"><i class="fa fa-medkit"></i>Purchase Medicines</a></li>
	        					<li><a href="#"><i class="fa fa-user-secret"></i>Administration</a></li>
	      					</ul>
      						<!-- <div class="menu-footer"> @ Test</div> -->
    					</div>
  					</nav>
				</div>
				
				<div class="main-content" ng-class="{'main-content-full' : toggleVrbl}">
					<ui-view name="main"></ui-view>
				</div>
			</section>
			
			<footer>
				Footer Part
			</footer>
		</div>
	</div>
</body>

<!--*********** JavaScript Libraries ***************-->
    <script src="resources/assets/js/jquery.min.js" type="text/javascript"></script>
    <script src="resources/assets/js/bootstrap.min.js"></script>
    <script src="resources/assets/js/accordionScript.js" type="text/javascript"></script>
    <script src="resources/assets/js/angularjs/angular.min.js" type="text/javascript"></script>
	<script src="resources/assets/js/angularjs/angular-ui-router.min.js" type="text/javascript"></script>
	<script src="resources/assets/js/angularjs/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
    <script src="resources/assets/js/angularjs/angular-animate.min.js" type="text/javascript"></script>
	<script src="resources/assets/js/angularjs/angular-translate.min.js" type="text/javascript"></script>
	<script src="resources/assets/js/angularjs/angular-resource.min.js" type="text/javascript"></script>
	<script src="resources/assets/js/angularjs/ui-bootstrap.min.js" type="text/javascript"></script>
	<!-- <script src="resources/assets/js/bootstrap.min.js" type="text/javascript"></script> -->
<!--*********** JavaScript Libraries ***************-->

<!--*********** Controllers ***************-->	
	<script src="resources/appController.js"></script>
	
	<script src="resources/controllers/dashboardController/dashboardController.js"></script>
	<script src="resources/controllers/storeControllers/addDrugController.js"></script>
	<script src="resources/controllers/storeControllers/menu11ModalController.js"></script>
	<script src="resources/controllers/storeControllers/modifyDrugController.js"></script>	
	<script src="resources/controllers/menu2Controllers/menu21Controller.js"></script>
	<script src="resources/controllers/menu2Controllers/menu22Controller.js"></script>
	<script src="resources/controllers/menu2Controllers/menu23Controller.js"></script>
	<script src="resources/controllers/menu2Controllers/menu24Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu3Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu31Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu32Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu33Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu34Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu35Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu36Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu37Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu38Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu39Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu310Controller.js"></script>
	<script src="resources/controllers/menu3Controllers/menu311Controller.js"></script>
	
<!--*********** Controllers ***************-->
</html>