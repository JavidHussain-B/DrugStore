drugApp.controller('menu35Controller', function($scope,$http,$rootScope,$uibModal) {
	
	
	
	//$scope.validateformvalues =	ValidationFactory.validateformvalues;
 
	$scope.data = [{
	   				name: 'Sunday',
	   				description: 'Description of aaa',
	   				timeArray:[]
	   			},{
	   				name: 'Monday',
	   				description: 'Description of aaa bbb ccc ddd',
	   				timeArray:[]
	   			},{
	   				name: 'Tuesday',
	   				description: 'Description of bbb',
	   				timeArray:[]
	   			},{
	   				name: 'Wednesday',
	   				description: 'Description of ccc',
	   				timeArray:[]
	   			},{
	   				name: 'Thursday',
	   				description: 'Description of ccc',
	   				timeArray:[]
	   			},{
	   				name: 'Friday',
	   				description: 'Description of ccc',
	   				timeArray:[]
	   			},{
	   				name: 'Saturday',
	   				description: 'Description of ccc',
	   				timeArray:[]
	   			}];
   	
	
	$scope.EditDetails = function() {
		
	};


	$scope.timeSlotTypes = ["STANDARD TIME","PEAK TIME","LUNCH TIME","PARTY TIME"];
	$scope.timeSlotColors = ["#009688","#a2b800","#1e72ba","#85c5e3"];
	$scope.addSlot=[];
	$scope.addSlot.color="";
	$scope.addSlot.timeType = "";
	
	$scope.timeArray = [/*{
    	timeSlotName : "Standard Time",
    	startTime : 0,
    	endTime : 43200,
    	color:"red"
    },{
    	timeSlotName : "Standard Time",
    	startTime : 43200,
    	endTime : 86400,
    	color:"green"
    }*/];
	
	$scope.paramvalue = {};
    $scope.inputvalue = null;

    $scope.checkSlideValidations = function() {
		if ($scope.addSlot.color == undefined || $scope.addSlot.color == "") {
            inputvalue = 'addSlotColor';
            $scope.error = "Please Select Color";
            $scope.paramvalue[inputvalue] = $scope.error;
            return false;
        } else if ($scope.addSlot.timeType == undefined || $scope.addSlot.timeType == "") {
            inputvalue = 'addSlotTimeType';
            $scope.error = "Please Select Time Type";
            $scope.paramvalue[inputvalue] = $scope.error;
            return false;
        } else {
            return true;
        }
    };
    
	$scope.addNewTimeSlot = function(type,color,item) {
		var goFurther = $scope.checkSlideValidations();
		if(goFurther) {
			if(item.timeArray.length == 0) {			
				item.timeArray.push({
			    	timeSlotName : type,
			    	startTime : 0,
			    	endTime : 86399,
			    	color:color,
			    });			
			} else {
				if(item.timeArray[item.timeArray.length -1].endTime != 86399) {
					var newSlot = {
				    	timeSlotName : type,
				    	startTime : (item.timeArray[item.timeArray.length -1].endTime+1),
				    	endTime : 86399,
				    	color:color
				    };
					item.timeArray.push(newSlot);
				}
			};
			angular.forEach(item.timeArray,function(slot) {
				slot.width = ((slot.endTime - slot.startTime)/86400)*100 + "%";
			});
			$scope.addSlot.color="";
			$scope.addSlot.timeType = "";
		}
	};

});

drugApp.filter('gtime', function() {
    return function(t) {
    	var start = new Date();
    	start.setHours(0,0,0,0);
  
   
        return start.getTime() + (t*1000);
    };
});


//To manage the resize
drugApp.directive("timeResize", function($document,$timeout) {
	return {
		scope:{
			solt:"=",
			slotindex:"=",
			timearray:"=",
		},
		link:function(scope, $element, $attr) {
			
	        // Function to manage resize right event
			var resizeRight = function($event) {
				
				$timeout(function(){
					var width = $event.pageX - $element[0].offsetLeft;
					var parentWidth = angular.element(document.getElementById("dragSlider")).width();
					var w_percentage = width/parentWidth*100;
							 
					if ($event.pageX > $element[0].offsetLeft) {
						scope.solt.width = w_percentage + "%";
						var prvslot = scope.slotindex - 1;

						if(scope.slotindex == 0){
							if(( w_percentage*86400/100)<86398){
								scope.solt.endTime = w_percentage*86400/100;
								if(scope.solt.endTime < 82900){
									var perfectHour = Math.floor(scope.solt.endTime/3600);
									var perfectRangeupper = perfectHour*3600 + 60;
									var perfectRangelower = perfectHour*3600 - 60;
									if(scope.solt.endTime<perfectRangeupper &&  scope.solt.endTime> perfectRangelower){
										var roundTime = Math.floor(scope.solt.endTime/60);
										scope.solt.endTime = roundTime*60;
									}
								}
							} else {
								scope.solt.endTime = 86399;
							}
						}
						
						if(scope.timearray[prvslot]){
							if((scope.timearray[prvslot].endTime + w_percentage*86400/100)<86398){
								scope.solt.endTime = scope.timearray[prvslot].endTime + w_percentage*86400/100;
								if(scope.solt.endTime < 82900){
									var perfectHour = Math.floor(scope.solt.endTime/3600);
									var perfectRangeupper = perfectHour*3600 + 60;
									var perfectRangelower = perfectHour*3600 - 60;
									if(scope.solt.endTime<perfectRangeupper &&  scope.solt.endTime> perfectRangelower){
										var roundTime = Math.floor(scope.solt.endTime/60);
										scope.solt.endTime = roundTime*60;
									}
								}
							} else {
								scope.solt.endTime = 86399;
							}
						}
									
						var nxtslot = scope.slotindex + 1;
						
						if(scope.timearray[nxtslot]){
							scope.timearray[nxtslot].startTime = scope.solt.endTime + 1;
						};
					} else {
						scope.solt.width =  "10px";
					}
					
					angular.forEach(scope.timearray,function(slot){
						slot.width = (slot.endTime - slot.startTime)/86400*100 + "%";
					});
				},100);
			};

			
	        // Create a div to catch resize right event
			newElement = angular.element('<div class="e-resize"></div>');
			$element.append(newElement);
			newElement.on("mousedown", function() {
				$document.on("mousemove", mousemove);
		        $document.on("mouseup", mouseup);
		        
		        function mousemove($event) {
		        	$event.preventDefault();
		        	resizeRight($event);
		        }
		        function mouseup() {
		        	$document.off("mousemove", mousemove);
		        	$document.off("mouseup", mouseup);
		        }
			});
		}
	};
});
