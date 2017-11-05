define([ "angular", 'ngRoute', 'ngResource', 'ngAnimate', 'ngCookies','toastr','satellizer' ,'controllers/controllers','services/AuthenticationService' ,'template', 'angularCarousal','ngTable', 'utils/environment', 'ngRestrict','ngFileUpload'], 
		function(angular, ngRoute, ngResource, ngAnimate,ngCookies,toastr,satellizer, controllers, AuthenticationService,Template, angularCarousal, ngTable, environment, ngRestrict,ngFileUpload) {
	
	var app = angular.module('eventApp', ['ngResource', 'ngRoute', 'ngAnimate','ngCookies','toastr','ngTable', 'ui.bootstrap', 'ngPatternRestrict','satellizer','ngFileUpload']);
	
	//$rootScope.Imageicon=null;
	//Controller declarations - Start
	 app.controller('MainController', controllers.main);
	 app.controller('EventController', controllers.event);
	 app.controller('UserController', controllers.user);
	 app.controller('ErrorController', controllers.error);
	//Controller declarations - End
	 app.factory('AuthenticationService', AuthenticationService);
	 
	 app.service('shareData', function() {
		    return {
		        setData : setData,
		        getData : getData,
		        shared_data : {} 
		    }

		    function setData(data) {
		        this.shared_data = data
		    }

		    function getData() {
		        return this.shared_data
		    } 
		})
	
	app.config(function($routeProvider, $httpProvider,$authProvider) {

						
		$routeProvider.when("/home", {
					controller : "MainController",
					templateUrl : "html/home.html"
				});

		$routeProvider.when("/eventList", {
					//controller : "EventController",
					templateUrl : "html/event-list.html"
				});
		
		$routeProvider.when("/dashboard", {
			        controller : "EventController",
			        templateUrl : "html/dashboard.html"
		});
		
		$routeProvider.when("/eventDetails", {
	            	//controller : "eventController",
	            	templateUrl : "html/event-details.html"
        });
		
		$routeProvider.when("/forgotPassword", {
        	//controller : "eventController",
        	templateUrl : "html/forgot-password.html"
        });
        
		$routeProvider.when("/event-template-1", {
			controller : "EventController",
			templateUrl : "html/event-template-1.html"
		});
		
		$routeProvider.when("/event-template-2", {
			controller : "EventController",
			templateUrl : "html/event-template-2.html"
		});
		
		$routeProvider.when("/event-template-3", {
			controller : "EventController",
			templateUrl : "html/event-template-3.html"
		});
		
		$routeProvider.when("/event-template-4", {
			controller : "EventController",
			templateUrl : "html/event-template-4.html"
		});
		
		$routeProvider.when("/event-template-5", {
			controller : "EventController",
			templateUrl : "html/event-template-5.html"
		});
		
		$routeProvider.when("/createEvent", {
			controller : "EventController",
			templateUrl : "html/event-Form.html"
		});
		
		
		/**
	     *  Satellizer config
	     */
		//App ID: 1434310946872865
		//App ID: 581718418505045
		//App ID: 896688467030206
		//App ID: 1598852020370529
	    $authProvider.facebook({
	      clientId: '581718418505045'
	    });

	    $authProvider.google({
	      clientId: 'moonlit-bucksaw-119308'
	    });
		
		$routeProvider.otherwise({
					redirectTo : "/home"
				});

			}).run(function($rootScope, $location,$cookies, $http) {
				
				// keep user logged in after page refresh
			     $rootScope.globals = $cookies.getObject('globals') || {};
				  if ($rootScope.globals.currentUser) {
				            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
				        }
				 
				  $rootScope.$on('$locationChangeStart', function (event, next, current) {
				            // redirect to login page if not logged in and trying to access a restricted page
				            var restrictedPage = $.inArray($location.path(), ['/eventList','/eventDetails','/forgotPassword']) === -1;
				            var loggedIn = $rootScope.globals.currentUser;
				            if (restrictedPage && !loggedIn) {
				                $location.path('/home');
				            }
				             else if($location.path()=='/home'){
				            	 $location.path('/dashboard');
				             }
				        });
	});
	

	
	return {
	init : function() {
		angular.bootstrap(document, ['eventApp']);
	}
	};
});