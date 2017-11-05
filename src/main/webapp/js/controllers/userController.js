/*define(
	['entity/serviceManager', 'utils/utility', 'utils/environment', 'cacheManager'],
	function (serviceManager, util, environment, cacheManager) {
	var exports = function ($scope, $http, $routeParams, $rootScope, $location, NgTableParams, $filter, ngTableEventsChannel) {

		$scope.$parent.mainActiveTab = 'um';
		$scope.totalRecords = 0;
		$scope.roleID = '';
		$scope.listOfUserIds = '';
		$scope.isTransporter = '';
		$scope.transporterId = '';
		$scope.globalSearchTerm = '';
		$scope.tempUser = '';
		$scope.userRoles = environment.profile.cureRoles;		
		$scope.role = '';
		$scope.statusSelect = [{status : ''}, {status : 'Active'}, {status : 'Inactive'}];
		$scope.responseUserData = [];
		$scope.statusValue = 'Y';
		$scope.aiCheckbox = false;
		
		$scope.statusOriginator = 'Y';
		$scope.orgCheckbox = false;
		
		$scope.tablePageSize = 20;
		$scope.viewFirstTime = true;
		$scope.eventType = 'PageView';
		$scope.defaultParams = {
				pageIndex : 1,
				count : 100,
				sorting : {
					userID : 'asc'
				},
				keyword : "",
				showInactiveUsers : false,
				showOriginator : false
			};
		
		$scope.cm = cacheManager.getInstance();
				
		$scope.role1="";		
		$('#userRoleSel').change(function () {				
			$scope.role1 = $('#userRoleSel :selected').val();			
		});
		
		$('.popup').popup({	transition : 'all 0.3s', escape : false	});		
		$scope.$parent.showLoader();
		var fltrs = $scope.cm.get("userMasterFilters");
		if(fltrs){
			$scope.defaultParams.pageIndex = fltrs.pageIndex;
			$scope.defaultParams.sorting = fltrs.sorting;
			if(fltrs.keyword){
				$scope.defaultParams.keyword = fltrs.keyword;
				$scope.globalSearchTerm = $scope.defaultParams.keyword;
			}
			$scope.aiCheckbox = fltrs.showInactiveUsers;
			$scope.orgCheckbox = fltrs.showOriginator;
		}
		
		$scope.roleFilter = function (_uRole) {
			return _uRole.id !== "CURL00000004";
		};
		
		var validateUserId = function (userId) {
			var isSuccess = true;
			var re = /^[a-zA-Z0-9@._-]+$/i;
			isSuccess = re.test(userId);

			if ((userId.length === 4) || (userId.length > 4)) {}
			else {
				isSuccess = false;
			}

			if ((userId.length > 50)) {
				isSuccess = false;
			}
			return isSuccess;
		};
		function validateENSU(value) {
		    return !value;
		}
		//------$scope.role = $scope.userRoles[0].id;
		$scope.role = $scope.role1;
		console.log("$scope.role1 from Select >> "+$scope.role1);
		$scope.addUser = function () {
			if (validateENSU($scope.role1) || validateENSU($scope.role)){
				$rootScope
				.showAlert(
					$scope.localData.Please_select_valid_user_role,
					"error");
				return false;
			}
			else{
				//--$scope.role = $scope.userRoles[0].id;		
				$scope.roleID = $scope.role;
				console.log("addUser() $scope.role >> "+$scope.role);
				console.log("addUser() $scope.roleID >> "+$scope.roleID);
				if ($scope.roleID == 'CURL00000003') {
					$scope.isTransporter = 'Y';
					$scope.transporterId = 'CTID00000001';
				} else {
					$scope.isTransporter = 'N';
				}
				var tmpids = $scope.listOfUserIds.replace(/\s+/g, '').split(",");
				var tmpFlag = true;
			
			
				for (var i = 0; i < tmpids.length; i++) {					
					if (!validateUserId(tmpids[i])) {
						tmpFlag = false;
						break;
					}
				}			
			
				if (!tmpFlag) {
					$rootScope.showAlert($rootScope.localData.Please_enter_valid_user_id, 'error');		
				} else {
					$scope.$parent.showLoader();
					serviceManager.adduser({
						roleID : ($scope.roleID),
						listOfUserIds : ($scope.listOfUserIds),
						isTransporter : ($scope.isTransporter),
						transporterId : ($scope.transporterId)
					}).then(function (response) {
						$scope.$parent.hideLoader();
						if (response.header.overallStatus === "SUCCESS") {
	
							var msg = "";
							var alertFlag = false;
							var m1 = "";
							var m2 = "";
							var s1 = "";
							var f1 ="";
							if ((response.data.ExistingUsers).length > 0) {
	
								for (var j = 0; j < ((response.data.ExistingUsers).length); j++) {
									alertFlag = true;
									var tempResponse = response.data.ExistingUsers[j];
	
									for (i = 0; i < (tempResponse.length); i++) {
	
										if (tempResponse[i].status == "Active") {
											if (m1 == "") {
												m1 += ("&#8226; "
														+ tempResponse[i].userId
														+ " ("
														+ tempResponse[i].roleName
														+ ")"+ $rootScope.localData.Already_exist_Active+"<br/>");
											} else {
												m1 += ("&#8226; "
														+ tempResponse[i].userId
														+ " ("
														+ tempResponse[i].roleName + 
														")" + $rootScope.localData.Already_exist_Active+"<br/>");
											}
										} else if (tempResponse[i].status == "Inactive") {
	
											if (m2 == "") {
												m2 += ("&#8226; "
														+ tempResponse[i].userId
														+ " ("
														+ tempResponse[i].roleName
														+ ")" + $rootScope.localData.Already_exist_Inactive + "<br/>");
											} else {
												m2 += ("&#8226; "
														+ tempResponse[i].userId
														+ " ("
														+ tempResponse[i].roleName 
														+ ")" + $rootScope.localData.Already_exist_Inactive + "<br/>");
											}
	
										}
									}
								}
							}
							
							if ((response.data.SuccessUsers).length > 0) {
								for (var j = 0; j < ((response.data.SuccessUsers).length); j++) {
									alertFlag = true;
									var tempResponse1 = response.data.SuccessUsers[j];						
										
										s1 +=("&#8226; "
												+ $rootScope.localData.User_added_successfullyUserId 
												+" "
												+ tempResponse1.userId
												+" "
												+ $rootScope.localData.User_added_successfullyUIdwithEmail 
												+" "
												+ tempResponse1.userEmail
												+"<br/>"
												);									
								}
							}
			
							if ((response.data.SuccessUsers) != "") {
								alertFlag = true;
								$("#userRoleSel").val($("#userRoleSel option:first").val());
								$scope.role1 ='';
								msg +=($rootScope.localData.User_added_successfullyHd+"<br>"
										+s1
										);
								$scope.displayUsers();
							}
							if ((m1 != "") || (m2 != "")) {		
								$("#userRoleSel").val($("#userRoleSel option:first").val());
								$scope.role1 ='';
								msg += ($rootScope.localData.Already_exist_ActiveHd+"<br>"
										+ m1
										+ m2
										);
							}
							
							var tempResponseF1 = response.data.FailedUsers;
							for (i = 0; i < (tempResponseF1.length); i++) {								
								f1 += ("&#8226; "
										+ tempResponseF1[i]
										+ "<br/>");
							}
	
							if ((response.data.FailedUsers).length > 0) {	
								$("#userRoleSel option:selected").val($scope.role);									
								alertFlag = true;
								///-msg += $rootScope.localData.Profile_not_found_cicso+ response.data.FailedUsers
								msg += ($rootScope.localData.Profile_not_found_cicso+"<br>"
										+ f1
										);
							}
	
							if (alertFlag) {
								if ((response.data.SuccessUsers == 0)
									 || (response.data.SuccessUsers == '0')) {
	
									$rootScope.showAlert(
										msg, 'error');
								} else {
	
									$rootScope.showAlert(
										msg, 'info');
								}
	
							}
	
							$scope.listOfUserIds = '';
							$scope.isTransporter = '';
							$scope.transporterId = '';
							$scope.roleID = '';
							//--$scope.role = $scope.userRoles[0].id;								
							$scope.$apply();
	
						} else {
							$rootScope.showAlert($rootScope.localData.Server_responded_error, 'error');
							$scope.listOfUserIds = '';
							$scope.isTransporter = '';
							$scope.transporterId = '';						
							//--$scope.role = $scope.userRoles[0].id;	
							$scope.role='';
							$scope.role1='';
							$("#userRoleSel").val($("#userRoleSel option:first").val());
							$scope.$apply();
						}
					});
				}
			}
		};
		
		$scope.checkInactive = function (user) {
			$scope.tempUser = user;
			$('#confirmPop .notification').html($rootScope.localData.Are_You_sure_Deactivate_User);
			$('#confirmPop').popup({
				autozindex : true,
				escape : false
			});
			$('#confirmPop').popup('show');
		};
		$scope.checkActive = function (user) {
			$scope.tempUser = user;
			$('#confirmPop .notification').html($rootScope.localData.Are_You_sure_Activate_User);
			$('#confirmPop').popup({
				autozindex : true,
				escape : false
			});
			$('#confirmPop').popup('show');
		};
		$scope.confirmAction = function () {
			$scope.activeInactive($scope.tempUser);
			$('#confirmPop').popup('hide');
		};

		$scope.activeInactive = function (user) {
			$scope.$parent.showLoader();
			var role = user.roleName;
			if (user.isActive == "Y") {
				$scope.isActive = "N";
			} else if (user.isActive == "N") {
				$scope.isActive = "Y";
			}
			$scope.userId = user.userID;
			if ((role == 'Admin') || (role == 'Operator')) {
				$scope.isTransporter = 'N';
			} else {
				$scope.isTransporter = 'Y';
			}
			$scope.id = user.id;

			serviceManager.activeInactive({
				id : ($scope.id),
				isTransporter : ($scope.isTransporter),
				isActive : ($scope.isActive),
				userId : ($scope.userId)
			}).then(function (response) {
				$scope.$parent.hideLoader();
				if ((response.header.overallStatus === "SUCCESS")
					 && (response.data != null)
					 && (response.data != 'null')
					 && (response.data != "")) {
					if ($scope.isActive == 'Y') {
						$rootScope
						.showAlert(response.data
							 + $rootScope.localData.Successfully_Activated);
					} else {
						$rootScope
						.showAlert(response.data
							 + $rootScope.localData.Successfully_Deactivated);
					}
					$scope.displayUsers();
				} else {
					$rootScope.showAlert($rootScope.localData.Server_responded_error, 'error');
				}
			});
		};
		$scope.cancel = function () {
			$scope.listOfUserIds = '';
			//--$scope.role = $scope.userRoles[0].id;
			$("#userRoleSel").val($("#userRoleSel option:first").val());	
			$scope.role1 ='';
		};

		$scope.applyGlobalSearch = function (ai, orig) {
			if (ai == undefined) {
				ai = false;
			} else if(orig == undefined){
				orig = false;
			}
			$scope.statusValue = '';
			$scope.statusOriginator = '';
			var searchKeyWord = angular.lowercase($scope.globalSearchTerm);
			if (ai) {
				$scope.statusValue = 'N';
			} else {
				$scope.statusValue = 'Y';
			}
			
			if (orig) {
				$scope.statusOriginator = 'Originator';
			} else {
				$scope.statusOriginator = 'Y';
			}

			var filteredRecords = [];

			angular.forEach($scope.responseUserData, function (row, index) {
				if ((angular.lowercase(row.userID)
						.indexOf(searchKeyWord) !== -1
						 || angular.lowercase(
							row.userEmail).indexOf(
							searchKeyWord) !== -1
						 || angular.lowercase(
							row.roleName).indexOf(
							searchKeyWord) !== -1
						 || angular
						.lowercase(row.status)
						.indexOf(searchKeyWord) !== -1 || angular
						.lowercase(row.userEmail)
						.indexOf(searchKeyWord) !== -1)
					 && (row.isActive == $scope.statusValue || row.isActive == "Y")
					 && (row.roleName == "Admin" || row.roleName == "Operator" || row.roleName == "Transport" || row.roleName == "Receiving" || row.roleName == $scope.statusOriginator)
					) {
					filteredRecords.push(row);
				}
			});

			$scope.totalRecords = filteredRecords.length;
			$scope.tableParamsuser = $scope.getTableSetting(filteredRecords);
			$scope.eventType = 'Event';
		};

		$scope.showAll = function () {
			$scope.aiCheckbox = true;
			$scope.orgCheckbox = true;
			$scope.globalSearchTerm = "";
			$scope.defaultParams.keyword = "";
			$scope.defaultParams.sorting = {
				userID : 'asc'
			};
			$scope.eventType = 'Event';
			$scope.tableParamsuser = $scope.getTableSetting($scope.responseUserData);
		};
		
		$scope.displayUsers = function () {
			serviceManager.displayuser({eventType : $scope.eventType}).then(function (response) {

				if (response.header.overallStatus === "SUCCESS") {

					$scope.responseUserData = response.data;
					$scope.tableParamsuser = $scope.getTableSetting($scope.responseUserData);
					$scope.applyGlobalSearch($scope.aiCheckbox, $scope.orgCheckbox);
					$scope.viewFirstTime = false;
					var scr = $(".box-container")
						.niceScroll({
							cursorcolor : "#9e9ea2",
							cursorborder : "0 none"
						});
					setTimeout(function () {
						$('#' + scr.id).addClass(
							'imphide');
					}, 100);
					$scope.$parent.hideLoader();
					$scope.$apply();
				} else {
					$scope.$parent.hideLoader();
					$rootScope
					.showAlert(
						$rootScope.localData.Server_responded_error,
						'error');
				}
			});
		};
		$scope.getAllUserRoles = function () {
			serviceManager
			.getAllUserRoles()
			.then(
				function (response) {
				if (response.header.overallStatus === "SUCCESS") {
					$scope.$parent.hideLoader();
					$scope.$apply();
				} else {
					$scope.$parent.hideLoader();
					$rootScope
					.showAlert(
						$rootScope.localData.Server_responded_error,
						'error');
				}
			});
		};

		if (environment.profile == null || environment.isTransport()
			 || environment.isReceiving()) {
			$rootScope.errorPageMessage = $rootScope.localData.You_are_not_authorized_to_access_this_page;
			$location.path('/error');
		} else {
			$scope.displayUsers();

		}
		$scope.getTableSetting = function (response) {
			if(!$scope.viewFirstTime){
				$scope.defaultParams.pageIndex = 1;
			}
			
			var data = response;
			var initialParams = {
					page : $scope.defaultParams.pageIndex,	
					count : $scope.defaultParams.count,
					sorting : $scope.defaultParams.sorting,
					filter: {$ : $scope.defaultParams.keyword }
			};
			
			var initialSettings = {
				counts : [],
				paginationMaxBlocks : 10,
				paginationMinBlocks : 2,
				dataset : data
			};			
			ngTableEventsChannel.onAfterReloadData(function(ngparams){
					console.log("globalSearchTerm >> "+$scope.globalSearchTerm);					
					var term = $scope.globalSearchTerm;
					if(term == undefined || $scope.aiCheckbox == 'false' || $scope.orgCheckbox == 'false')
					{									
						$scope.showAll();
						$scope.applyGlobalSearch($scope.aiCheckbox, $scope.orgCheckbox);
					}else if (term.charAt(0) === '!') {
						term = " " + term;
					}
					
				$scope.defaultParams.sorting = ngparams.sorting();
				$scope.cm.insert("userMasterFilters", {
					pageIndex : ngparams.page(),
					sorting : ngparams.sorting(),
					keyword : term,
					showInactiveUsers : $scope.aiCheckbox,
					showOriginator :	$scope.orgCheckbox 
					}, 100);	
			}, $scope);
			
			return new NgTableParams(initialParams, initialSettings);
		};

		$scope.getSerachButtonFlag = function (a, b) {
			if (a || b.length < 1) {
				return true;
			}
			return false;
		};
	};
	exports.$inject = ['$scope', '$http', '$routeParams', '$rootScope', '$location', 'NgTableParams', '$filter', 'ngTableEventsChannel'];
	return exports;

});
*/