drugApp.controller('addDrugController', function($scope,$http,$rootScope,$uibModal) {
 
	

	$scope.EditDetails = function() {
		$scope.modalInstance = $uibModal.open({
			templateUrl: 'resources/tamplates/storeTpls/menu11Modal.html',
            controller: 'menu11ModalController',
            scope: $scope,
            backdrop: 'static',
            windowClass: 'app-modal-window-md',
            keyboard: false
		});
	};
});
    
