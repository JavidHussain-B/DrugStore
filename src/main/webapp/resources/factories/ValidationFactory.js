LCMSApp.factory('ValidationFactory', ['$scope', function($scope){
	var ValidationFactory={};
	ValidationFactory.validateformvalues = function(Parameter, modalvalue, minlength, maxlength, patternvalue) {
        $scope.error = '';
        if (modalvalue == '' || modalvalue == undefined) {
            $scope.error = '';
            $scope.paramvalue[Parameter] = $scope.error;
            return 1;
        } else if(Number.isInteger(modalvalue)){
        	$scope.error = "";
            $scope.paramvalue[Parameter] = $scope.error;
        	return 0;
        } else if (modalvalue.length > 0) {
            var modallength = modalvalue.length;
            if (modallength < minlength) {
                $scope.error = Parameter + " is too short";
                $scope.paramvalue[Parameter] = $scope.error;
                return 0;
            } else if (modallength > maxlength) {
                $scope.error = Parameter + " is too long";
                $scope.paramvalue[Parameter] = $scope.error;
                return 0;
            } else if (patternvalue == 'Alphanumeric') {
                if (!modalvalue.match(/^[0-9a-zA-Z]+$/)) {
                    $scope.error = "Special characters are not allowed";
                    $scope.paramvalue[Parameter] = $scope.error;
                    return 0;
                }
            } else if (patternvalue == 'Decimal') {
                if (!modalvalue.match(/^[0-9]\d*(\.\d+)?$/)) {
                    $scope.error = "Value should be number only";
                    $scope.paramvalue[Parameter] = $scope.error;
                    return 0;
                } else {
                	$scope.error = '';
                	$scope.paramvalue[Parameter] = $scope.error;
                	return 1;
                }
            } else if (patternvalue == 'Numeric') {
                if (!modalvalue.match(/^[0-9]*$/)) {
                    $scope.error = "Value should be numeric number";
                    $scope.paramvalue[Parameter] = $scope.error;
                    return 0;
                } else {
                	$scope.error = '';
                	$scope.paramvalue[Parameter] = $scope.error;
                	return 1;
                }
            } else if (patternvalue == 'UTC') {
                if (!modalvalue.match(/^([+]((14|(14[:]?00))|([0][0-9]|[1][01])|([0][0-9]|[1][01])[:]?[0-5][0-9]))|([-]((12|(12[:]?00))|([0][0-9]|[1][01])|([0][0-9]|[1][01])[:]?[0-5][0-9]))$/)) {
                    $scope.error = "Enter a valid UTC in +/- HH:MM format";
                    $scope.paramvalue[Parameter] = $scope.error;
                    return 0;
                }
            } else
                $scope.error = '';
        	$scope.paramvalue[Parameter] = $scope.error;
        	return 1;
        }
    };
    
    return ValidationFactory;
    
}]);