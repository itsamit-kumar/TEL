require.config({
	urlArgs : "bust=" /* + (new Date()).getTime() */,
	paths : {
		'angular' : 'lib/angular.min',
		'ngResource' : 'lib/angular-resource.min',
		'ngCookies' : 'lib/angular-cookies.min',
		'ngTouch' : 'lib/angular-touch.min',
		'ngRoute' : 'lib/angular-route.min',
		'ngAnimate' : 'lib/angular-animate.min',
		'angularCarousal' : "lib/ui-bootstrap-tpls-0.14.3.min",
		'uiBootstrapTpls' : "lib/ui-bootstrap-tpls-0.14.3.min",
		'jQuery' : 'lib/jquery.min',
		'amplify' : 'lib/amplify',
		'ngTable' : 'lib/ng-table',
		'ngRestrict' : 'lib/ng-pattern-restrict.min',
		'cacheManager' : "lib/cacheManager",
		'ngMessages' : 'lib/angular-messages',
		'ngSanitize' : 'lib/angular-sanitize',
		'uirouter' : 'lib/angular-ui-router',
		'toastr' : "lib/angular-toastr.tpls",
		'satellizer' : 'lib/satellizer',
        'ngFileUpload':'lib/ng-file-upload-all',
        'ngFileUploadShim':'lib/ng-file-upload-shim'
	},
	shim : {
		angular : {
			exports : 'angular'
		},
		ngResource : [ 'angular' ],
		ngCookies : [ 'angular' ],
		ngTouch : [ 'angular' ],
		ngRoute : [ 'angular' ],
		ngAnimate : [ 'angular' ],
		toastr : [ 'angular' ],
		satellizer : [ 'angular' ],
		ngMessages : [ 'angular' ],
		ngTable : [ 'angular' ],
		uiBootstrapTpls : [ 'angular' ],
		angularCarousal : [ 'angular', 'ngTouch' ],
		ngRestrict : [ 'angular' ],
		satellizer : [ 'angular' ],
		jQuery : {
			exports : 'jQuery'
		},
		amplify : {
			exports : 'amplify'
		},
		//amplify:['jQuery'],
		cacheManager :{
			exports : 'CacheManager'
		},
		ngFileUpload : [ 'angular' ],
		ngFileUploadShim : [ 'angular' ],
	},

	baseUrl : 'js'
});

require(
		[ "utils/environment", "app", 'api/dataResouce',
				'entity/serviceManager', 'data/local/en-US', "utils/utility", "cacheManager" ],
		function(environment, app, dataResouce, serviceManager, localData, util, cacheManager) {
            var a = environment.init();
			var b = dataResouce.init();
			app.init();
			//
			var showLoader = function() {
				$('#pageloader').show();
			};
			var hideLoader = function() {
				$('#pageloader').hide();
			};
			var showAlert = function(str, type) {
				$('#alertPop .notification').removeClass('error');
				if (type != undefined && type != "undefined") {
					$('#alertPop .notification').addClass(type);
				}
				$('#alertPop .notification').html(str);
				$('#alertPop').popup({
					autozindex : true
				});
				$('#alertPop').popup('show');
			};

			//showLoader();
			/*console.log(" main .js environment.industryList",environment.industryList);
			serviceManager.getMasterData().then(function(response) {
				console.log(">>>>>>>>response inside mainJs : ");
				hideLoader();
				if(response.header.overallStatus === "SUCCESS"){
				//	console.log(">>>>>>>>response : "+JSON.stringify(response.data));
					var masterData=response.data;
					environment.industryList=masterData.industryList;
					environment.countryList=masterData.countryList;
					environment.cityList=masterData.cityList;
					environment.eventTypeList=masterData.eventTypeList;
					console.log("environment.industryList",environment.industryList);
				}else if(response.header.overallStatus === "FAILURE") {
					 $rootScope.showAlert(response.header.statusMessage);
		         }else{
					$rootScope.showAlert("message:::",'error');
		         }
				});*/
		});
