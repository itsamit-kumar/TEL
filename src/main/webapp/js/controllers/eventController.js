define(
		[ 'entity/serviceManager', 'utils/utility', 'utils/environment', 'cacheManager','ngFileUpload','utils/constants'],
		function(serviceManager, util, environment, cacheManager,ngFileUpload,constants) {

			var exports = function($scope, $http, $routeParams, $rootScope,$location, NgTableParams, $filter,shareData,Upload,$timeout) {
				
				$('#startDate').datetimepicker({
					//minDate : tstr,
					format:"d-M-Y H:i",
					step : '30'
				});
				
				$('#endDate').datetimepicker({
					//minDate : tstr,
					format:"d-M-Y H:i",
					step : '30'
				});
		    	    
		    	    $scope.createEvent = function(file) {
		    	    	Upload.upload({
		    	            url:'http://localhost:8080/event/eventServices/createEvent',
		    	            fields: {'eventInfo': $scope.createParameter()}, // additional data to send
		    	            file: file
		    	        }).progress(function (evt) {
		    	            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
		    	            console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
		    	        }).success(function (response, status, headers, config) {
		    	        	$rootScope.hideLoader();
		    	            if(response.header.overallStatus === "SUCCESS"){
		    	            	$rootScope.showAlert("event successfull submited");
								 window.location.href = constants.DASHBOARD_PAGE;
							}else if(response.header.overallStatus === "FAILURE") {
								 $rootScope.showAlert(response.header.statusMessage);
					         }else{
					        	 $rootScope.showAlert("Please Try After some time");
					         }
		    	        });
			    	    }
		    	    
		    	    console.log("$scope.createParameter "+JSON.stringify($scope.createParameter));
		    	    $scope.createParameter= function(){
						$rootScope.showLoader();
						/*var industryId = $scope.industry ? $scope.industry.industryTypeId.toString() : "";
						var countryId = $scope.country ? $scope.country.countryId.toString() : "";
						var cityId = $scope.city ? $scope.city.cityId.toString() : "";
						var eventTypeId = $scope.eventType ? $scope.eventType.industryTypeId.toString() : "";*/
						var industryId = $scope.industry ? $scope.industry.industryTypeId+"" : "";
						var countryId = $scope.country ? $scope.country.countryId+"": "";
						var cityId = $scope.city ? $scope.city.cityId+"" : "";
						var eventTypeId = $scope.eventType ? $scope.eventType.eventTypeId+"" : "";
						console.log("1 "+$scope.industry.industryTypeId);
						console.log("2 "+$scope.country.countryId);
						console.log("3 "+$scope.city.cityId);
						console.log("3 "+$scope.eventType.industryTypeId);
	    	    		return serviceManager.createEvent({ 
	    	    			userId:$rootScope.globals.currentUser.username,
	    	            	eventName:$scope.eventName,
	    	            	industryType:industryId,
	    	            	country:countryId,
	    	            	city:cityId,
						    eventType:eventTypeId,
	    	            	eStartDate:$scope.startDate,
	    	            	eEndDate:$scope.endDate,
		    	            webAddress:$scope.webURL});
	    	    	}
				
		}
			exports.$inject = [ '$scope', '$http', '$routeParams','$rootScope', '$location', 'NgTableParams', '$filter','shareData','Upload','$timeout'];
			return exports;

		});