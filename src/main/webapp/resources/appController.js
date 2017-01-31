'use strict';

var drugApp = angular.module('drugApp', ['ui.router','ui.bootstrap','ui.bootstrap.tpls','ngAnimate']);

drugApp.controller('MainController', ['$scope','$rootScope','$http','$state', function($scope,$rootScope,$http,$state) {

	 $http.get("./getMenus").success(function(data) {
		 console.log("main---" + JSON.stringify(data));
	 });
	 
	 $scope.getSubMenus = function() {
		 var subMenuUrl = './getSubMenus?parentId=' + $scope.Parent_Menu_Id;
		 $http.get(subMenuUrl).success(function(data) {
			 console.log("submain---" + JSON.stringify(data));
		 });
	 }

	$scope.toggleClick = function() {
		$scope.toggleVrbl = !$scope.toggleVrbl;
	}
}]);



drugApp.config(function($stateProvider, $urlRouterProvider) {

//--------------Dashboard Menu-----------------//
	$stateProvider.state('P1', {
		url : "/Dashboard",
		views: {
			'main@': {
				controller: 'dashboardController',
				templateUrl: 'resources/tamplates/dashboard/dashboard.html'
			}
		}
	})
	
//--------------Store Menu-----------------//
	.state('P2', {
		url: '/Store/',
		views: {
			'main@': {
				controller: 'addDrugController',
				templateUrl: 'resources/tamplates/storeTpls/addDrug.html'
			}
		}
	})
	.state('P21', {
		parent: 'P2',
		url: 'AddDrug',
		views: {
			'main@': {
				controller: 'addDrugController',
				templateUrl: 'resources/tamplates/storeTpls/addDrug.html'
			}
		}
	})
	.state('P22', {
		parent: 'P2',
		url: 'ModifyDrug',
		views: {
			'main@': {
				controller: 'modifyDrugController',
				templateUrl: 'resources/tamplates/storeTpls/modifyDrug.html'
			}
		}
	})

//--------------3rd Main Menu-----------------//
	.state('P3', {
		url: '/commercial/',
		views: {
			'main@': {
				controller: 'menu3Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu3.html'
			}
		}
	})
	.state('P31', {
		parent: 'P3',
		url: 'P31',
		views: {
			'masters@P3': {
				controller: 'menu31Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu31.html'
			}
		}
	})
	.state('P32', {
		parent: 'P3',
		url: 'P32',
		views: {
			'masters@P3': {
				controller: 'menu32Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu32.html'
			}
		}
	})
	.state('P33', {
		parent: 'P3',
		url: 'P33',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P33';
		}],
		views: {
			'masters@P3': {
				controller: 'menu33Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu33.html'
			}
		}
	})
	.state('P34', {
		parent: 'P3',
		url: 'P34',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P34';
		}],
		views: {
			'masters@P3': {
				controller: 'menu34Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu34.html'
			}
		}
	})
	.state('P35', {
		parent: 'P3',
		url: 'P35',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P35';
		}],
		views: {
			'masters@P3': {
				controller: 'menu35Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu35.html'
			}
		}
	})
	.state('P36', {
		parent: 'P3',
		url: 'P36',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P36';
		}],
		views: {
			'masters@P3': {
				controller: 'menu36Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu36.html'
			}
		}
	})
	.state('P37', {
		parent: 'P3',
		url: 'P37',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P37';
		}],
		views: {
			'masters@P3': {
				controller: 'menu37Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu37.html'
			}
		}
	})
	.state('P38', {
		parent: 'P3',
		url: 'P38',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P38';
		}],
		views: {
			'masters@P3': {
				controller: 'menu38Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu38.html'
			}
		}
	})
	.state('P39', {
		parent: 'P3',
		url: 'P39',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P39';
		}],
		views: {
			'masters@P3': {
				controller: 'menu39Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu39.html'
			}
		}
	})
	.state('P310', {
		parent: 'P3',
		url: 'P310',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P310';
		}],
		views: {
			'masters@P3': {
				controller: 'menu310Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu310.html'
			}
		}
	})
	.state('P311', {
		parent: 'P3',
		url: 'P311',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P311';
		}],
		views: {
			'masters@P3': {
				controller: 'menu311Controller',
				templateUrl: 'resources/tamplates/menu3Tpls/menu311.html'
			}
		}
	});


});



