LCMSApp.controller('menu11ModalController', function($scope,$http,$filter,$rootScope,$uibModalInstance) {
	
	$scope.cancel = function() {
		$scope.yes = function() {
	        $uibModalInstance.close();
	    };
		$uibModalInstance.dismiss('cancel');
	};
	

});
