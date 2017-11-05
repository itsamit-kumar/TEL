define([ 'entity/serviceManager','utils/environment'], function(serviceManager, environment) {
	var exports = function($scope, $http, $routeParams) {
		console.log('error controller........', environment);
		$('#pageloader').hide(); 
		if(!environment.profile.userFName){
			$('#profileLink').hide();
		}
		$('#transportLogo').hide();
	};

	
	exports.$inject = [ '$scope', '$http', '$routeParams' ];
	return exports;
});