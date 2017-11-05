define([ 'data/local/en-US', 'entity/serviceManager', 'utils/environment', 'utils/constants','toastr'],
		
		function(localData, serviceManager, environment,constants,toastr) {
			var exports = function($scope, $http,$filter,$routeParams, $rootScope, $location, $document,$auth,shareData,AuthenticationService) {
				/*
				 * show preloader
				 */
				$rootScope.showLoader = function() {
					$('#pageloader').show();
				};

				/*
				 * hides preloader
				 */
				$rootScope.hideLoader = function() {
					$('#pageloader').hide();
				};
				$rootScope.showLoader();
					serviceManager.getMasterData().then(function(response) {
						//console.log(">>>>>>>>response : "+JSON.stringify(response.data));
						$rootScope.hideLoader();
						if(response.header.overallStatus === "SUCCESS"){
							//var masterData=response.data;
							/*environment.industryList=masterData.industryList;
							environment.countryList=masterData.countryList;
							environment.cityList=masterData.cityList;
							environment.eventTypeList=masterData.eventTypeList;*/
							console.log(response.data.eventTypeList)
							$scope.$apply(function () {
								$scope.industryList=response.data.industryList;
								$scope.countryList=response.data.countryList;
								$scope.cityList=response.data.cityList;
								$scope.eventTypeList=response.data.eventTypeList;
					        });
						}else if(response.header.overallStatus === "FAILURE") {
							 $rootScope.showAlert(response.header.statusMessage);
				         }else{
							$rootScope.showAlert("message:::",'error');
				         }
						});
				  // console.log("$scope.industryList ",$scope.industryList)
				
				/* $scope.industryList = [
				                         {'name':'Aerospace','id':'1'},
						                    {'name':'Agriculture','id':'2'},
						                    {'name':'Fishing','id':'3'},
						                    {'name':'Timber','id':'4'},
						                    {'name':'Tobacco','id':'5'},
						                    {'name':'Chemical','id':'6'},
						                    {'name':'Pharmaceutical','id':'7'},
						                    {'name':'Computer','id':'8'},
						                    {'name':'Software','id':'9'},
						                    {'name':'Construction','id':'10'}];*/
				
				 /*$scope.countryList = [ 
						                   {'name':'Australia','id':'1'},
						                   {'name':'China','id':'2'},
						                   {'name':'Canada','id':'3'},
						                   {'name':'France','id':'4'},
						                   {'name':'Germany','id':'5'},
						                   {'name':'India','id':'6'},
						                   {'name':'Indonesia','id':'7'},
						                   {'name':'Italy','id':'8'},
						                   {'name':'Japan','id':'9'},
						                   {'name':'Netherlands','id':'10'},
						                   {'name':'Norway','id':'11'},
						                   {'name':'Singapore','id':'12'},
						                   {'name':'United Arab Emirates','id':'13'},
						                   {'name':'UK','id':'14'},
						                   {'name':'USA','id':'15'}];*/
				
				   
				  /*$scope.cityList = [ 
					            		{'name':'Melbourne','id':'1','cid':'1'},
					            		{'name':'Perth','id':'2','cid':'1'},
					            		{'name':'Beijing','id':'3','cid':'2'},
					            		{'name':'Shanghai','id':'4','cid':'2'},
					            		{'name':'Alberta','id':'5','cid':'3'},
					            		{'name':'Nova Scotia','id':'6','cid':'3'},
					            		{'name':'Paris','id':'7','cid':'4'},
					            		{'name':'Strasbourg','id':'8','cid':'4'},
					            		{'name':'Berlin','id':'9','cid':'5'},
					            		{'name':'Hannover','id':'10','cid':'5'},
					            		{'name':'Australia11','id':'11','cid':'6'},
					            		{'name':'Australia12','id':'12','cid':'6'},
					            		{'name':'Australia13','id':'13','cid':'7'},
					            		{'name':'Australia14','id':'14','cid':'7'},
					            		{'name':'Australia15','id':'15','cid':'8'},
					            		{'name':'Australia16','id':'16','cid':'8'},
					            		{'name':'Australia17','id':'17','cid':'9'},
					            		{'name':'Australia18','id':'18','cid':'9'},
					            		{'name':'Australia19','id':'19','cid':'10'},
					            		{'name':'Australia20','id':'20','cid':'10'},
					            		{'name':'Australia21','id':'21','cid':'11'},
					            		{'name':'Australia22','id':'22','cid':'11'},
					            		{'name':'Australia23','id':'23','cid':'12'},
					            		{'name':'Australia24','id':'24','cid':'12'},
					            		{'name':'Australia25','id':'25','cid':'13'},
					            		{'name':'Australia26','id':'26','cid':'13'},
					            		{'name':'Australia27','id':'27','cid':'14'},
					            		{'name':'Australia28','id':'28','cid':'14'},
					            		{'name':'Australia29','id':'29','cid':'15'},
					            		{'name':'Australia30','id':'30','cid':'15'}];*/
				  
				  
				 /* $scope.EventTypeList = [{'name':'Select Event','id':'0'},
				                         {'name':'Aerospace','id':'1'},
						                    {'name':'Agriculture','id':'2'},
						                    {'name':'Fishing','id':'3'},
						                    {'name':'Timber','id':'4'},
						                    {'name':'Tobacco','id':'5'},
						                    {'name':'Chemical','id':'6'},
						                    {'name':'Pharmaceutical','id':'7'},
						                    {'name':'Computer','id':'8'},
						                    {'name':'Software','id':'9'},
						                    {'name':'Construction','id':'10'}];*/

				 // $scope.country=$scope.countryList[0];
				 // $scope.city=$scope.cityList[0];
				 // $scope.industry=$scope.industryList[0];
				 // $scope.eventType=$scope.EventTypeList[0];
				  
				  $scope.getcityes = function(selectedCountry) {
					  //console.log("selectedCountry  "+selectedCountry);
					 // alert(selectedCountry!=0)
					  if(selectedCountry!=0){
						  //alert("inside...")
						    var filteredCity = $filter('filter')($scope.cityList, {'countryId':selectedCountry},true);
						   // console.log("filteredCity ::>> "+filteredCity)
						    return filteredCity;
					  }else{
						  return $scope.cityList
					  }
					  
					  };
				
				$rootScope.showAlert = function(str, type) {
					$('#alertPop .notification').removeClass('error');
					$('#alertPop .notification').removeClass('info');
					if (type !== undefined && type !== "undefined") {

						$('#alertPop .notification').addClass(type);
					}
					$('#alertPop .notification').html(str);
					$('#alertPop').popup({
						autozindex : true,
						escape : false
					});
					$('#alertPop').popup('show');
				};
				
				
				$scope.signOut = function() {
					AuthenticationService.ClearCredentials();
					 $location.path('/home');
					/*serviceManager.signOut().then(function(response) {
						    window.location.href = environment.logoutURI;
									});*/
				};
				

				$scope.searchEvent = function() {
					console.log(JSON.stringify($scope.industry));
					//console.log(JSON.stringify($scope.country));
					//console.log(JSON.stringify($scope.city));
					$rootScope.showLoader();
					var industryId = $scope.industry ? $scope.industry.industryTypeId.toString() : "";
					var countryId = $scope.country ? $scope.country.countryId.toString() : "";
					var cityId = $scope.city ? $scope.city.cityId.toString() : "";
					serviceManager.searchEvent({
						industry : industryId,
						country : countryId,
						city : cityId
					}).then(function(response) {
						$rootScope.hideLoader();
						if (response.header.overallStatus === "SUCCESS") {
							$scope.eventLists=response.data;
						    window.location.href = constants.EVENT_SEARCH_PAGE;
						}else if(response.header.overallStatus === "FAILURE") {
							 $rootScope.showAlert(response.header.statusMessage);
				         }else {
							$rootScope.showAlert("message:::", 'error');
						}

					});
				};
				
				$scope.verifyUser = function() {
					console.log(""+$scope.userId);
					console.log(" $scope.password   "+$scope.password);
					$rootScope.showLoader();
					serviceManager.validateUser({
						userId:$scope.userId,
						userPassword:$scope.password,
						loginSource:constants.THEEVENTLABS
					}).then(function(response) {
						$rootScope.hideLoader();
						if(response.header.overallStatus === "SUCCESS" && response.data.isUserLoggedIn==="Y"){
							AuthenticationService.SetCredentials(response.data.userId,$scope.password);
							    window.location.href = constants.DASHBOARD_PAGE;
						}else if(response.header.overallStatus === "FAILURE") {
							 $rootScope.showAlert(response.header.statusMessage);
				         }else {
							$rootScope.showAlert(response.data.errorMessages);
							//alert("Error Occure")
				}
						
					});
				};
				
				$scope.forgotPassword = function() {
					console.log("forgotPassword");
					$rootScope.showAlert("Work in progress : : ");
					/*serviceManager.forgotPassword({
						emailId:$scope.emailId,
					}).then(function(response) {
						   // window.location.href = environment.logoutURI;
									});*/
				};
				
				/*$scope.authenticate = function(provider) {
					console.log("Authenticate");
		        	 $auth.authenticate(provider).then(function() {
		        		// console.log("data>>>>>",responce.data);
		        		// window.location.href = constants.DASHBOARD_PAGE;
			          })
				};*/
				
				$scope.authenticate = function(provider) {
					console.log("Authenticate");
					$rootScope.showAlert("Work in progress : : ");
					/* $auth.authenticate(provider) .then(function() {
						 serviceManager.validateUser({
								loginSource:provider
							}).then(function(response) {
								  //  window.location.href = constants.DASHBOARD_PAGE;
											});
				          })*/
				          
				};
				
				/*$scope.authenticate = function(provider) {
					console.log("Authenticate>>>>>>>>>>>>>>>");
		        	 $auth.authenticate(provider) .then(function() {
			           // toastr.success('You have successfully signed in with ' + provider + '!');
			            $location.path('/');
			          })
				};*/
				
				

					$scope.registorNewUser = function() {
					$rootScope.showLoader();
					serviceManager.registerUser({
								userFName : $scope.fName,
								userLName : $scope.lName,
								userPassword : $scope.rPassword,
								userEmailId : $scope.emailId,
								phoneNumber : $scope.phoneNumber,
								loginSource : constants.THEEVENTLABS,
								userId : $scope.userId,
								userType : $scope.userType
							}).then(function(response) {
										$rootScope.hideLoader();
										if (response.header.overallStatus === "SUCCESS"
												&& response.data.isUserLoggedIn === "Y") {
											AuthenticationService
													.SetCredentials(
															response.data.userId,
															$scope.password);
											window.location.href = constants.DASHBOARD_PAGE;
										}else if(response.header.overallStatus === "FAILURE") {
											 $rootScope.showAlert(response.header.statusMessage);
								         } else {
											$rootScope
													.showAlert(response.data.errorMessages);
										}
									});
				};

				

				/*$rootScope.getConfigVal = function(_key) {
					var ret = null;
					environment.profile.configValues.forEach(function(element) {
						if (element.configName == _key) {
							ret = element.ConfigValue;
							return ret;
						}
					});
					return ret;
				};
				
				if (environment.profile == null) {
					$rootScope.errorPageMessage = $rootScope.localData.You_are_not_authorized_to_access_this_page;
					$location.path('/error');
				} else {
					$scope.tabs = environment.profile.menuItems;
					var userProfile = {};
					userProfile.FirstName = environment.profile.userFName;
					userProfile.LastName = environment.profile.userLName;
					userProfile.Email = environment.profile.userEmail;
					$rootScope.updateName(userProfile);
				}*/
				
			};

			exports.$inject = [ '$scope', '$http','$filter', '$routeParams', '$rootScope', '$location', '$document','$auth','shareData','AuthenticationService'];

			return exports;
		});