'use strict';

var LCMSApp = angular.module('LCMSApp', ['ui.router','ui.bootstrap','ui.bootstrap.tpls','ngAnimate','TT-UI.Navigation','TT-UI.Table']);

LCMSApp.controller('MainController', ['$scope','$rootScope','$http','$state', function($scope,$rootScope,$http,$state) {

	// fake user stuff
	$scope.userAuthorized = true;
	
	$scope.User = {
		getUserName: function () {
			return 'Admin';
		},
		getUserPhoto: function () {
			return 'images/avatar.png';
		}
	};

	
}]);



LCMSApp.config(['$stateProvider', 'navigationProvider', function($stateProvider, navigationProvider) {
	$stateProvider.state('home', {
		url: 'home',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = 'home';
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = undefined;
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = undefined;
		}]
	})
	// --- settings / configuration
	.state('settings', {
		url: '/settings/',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = 'settings';
		}],
		onExit: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = undefined;
		}]
	})
	.state('configuration', {
		url: 'configuration',
		parent: 'settings',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'configuration';
		}],
		onExit: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = undefined;
		}]
	})

//--------------1st Main Menu-----------------//
	.state('P1', {
		url: '/offering/',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = 'P1';
		}],
		onExit: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = undefined;
		}],
		views: {
			'main@': {
				controller: 'menu11Controller',
				templateUrl: 'resources/tamplates/menu1Tpls/menu11.html'
			}
		}
	})
	.state('P11', {
		parent: 'P1',
		url: 'P11',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P11';
		}],
		onExit: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = undefined;
		}],
		views: {
			'main@': {
				controller: 'menu11Controller',
				templateUrl: 'resources/tamplates/menu1Tpls/menu11.html'
			}
		}
	})
	.state('P12', {
		parent: 'P1',
		url: 'P12',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P12';
		}],
		views: {
			'main@': {
				controller: 'menu12Controller',
				templateUrl: 'resources/tamplates/menu1Tpls/menu12.html'
			}
		}
	})
	.state('P13', {
		parent: 'P1',
		url: 'P13',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P13';
		}],
		views: {
			'main@': {
				controller: 'menu13Controller',
				templateUrl: 'resources/tamplates/menu1Tpls/menu13.html'
			}
		}
	})
	.state('P14', {
		parent: 'P1',
		url: 'P14',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P14';
		}],
		views: {
			'main@': {
				controller: 'menu14Controller',
				templateUrl: 'resources/tamplates/menu1Tpls/menu14.html' 
			}
		}
	})

//--------------2nd Main Menu-----------------//
	.state('P2', {
		url: '/resource/',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = 'P2';
		}],
		onExit: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = undefined;
		}],
		views: {
			'main@': {
				controller: 'menu21Controller',
				templateUrl: 'resources/tamplates/menu2Tpls/menu21.html'
			}
		}
	})
	.state('P21', {
		parent: 'P2',
		url: 'P21',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P21';
		}],
		views: {
			'main@': {
				controller: 'menu21Controller',
				templateUrl: 'resources/tamplates/menu2Tpls/menu21.html'
			}
		}
	})
	.state('P22', {
		parent: 'P2',
		url: 'P22',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P22';
		}],
		views: {
			'main@': {
				controller: 'menu22Controller',
				templateUrl: 'resources/tamplates/menu2Tpls/menu22.html'
			}
		}
	})
	.state('P23', {
		parent: 'P2',
		url: 'P23',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P23';
		}],
		views: {
			'main@': {
				controller: 'menu23Controller',
				templateUrl: 'resources/tamplates/menu2Tpls/menu23.html'
			}
		}
	})
	.state('P24', {
		parent: 'P2',
		url: 'P24',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P24';
		}],
		views: {
			'main@': {
				controller: 'menu24Controller',
				templateUrl: 'resources/tamplates/menu2Tpls/menu24.html'
			}
		}
	})
	
//--------------3rd Main Menu-----------------//
	.state('P3', {
		url: '/commercial/',
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = 'P3';
		}],
		onExit: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateMenu = $rootScope.navigate.activeMenu = undefined;
		}],
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
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P31';
		}],
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
		onEnter: ['$rootScope', function($rootScope) {
			$rootScope.navigate.activeStateSubMenu = $rootScope.navigate.activeSubMenu = 'P32';
		}],
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


}]);



