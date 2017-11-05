define([ 'api/eventAPI', 'amplify', 'utils/environment' ], function(eventAPI,
		amplify, environment) {
	
	var exports = {};

	exports.searchEvent = function(params) {
		console.log("Search event:: ::")
		var obj = eventAPI.searchEvent();
		for ( var a in params) {
			obj.payload.parameters[a] = params[a];
		}
		console.log("Search event:: resourceID ::" +obj.resourceID);
		console.log("Search event:: resourceID ::" +amplify)
		return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;

				}).promise();

	};

	exports.getEventDetails = function(params) {
		console.log("getEventDetails :: ::");
		var obj = eventAPI.getEventDetails();
		for ( var a in params) {
			obj.payload.parameters[a] = params[a];
		}
		return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;

				}).promise();

	};

	exports.validateUser = function(params) {
		console.log("validateUser :: ::"+params);
		var obj = eventAPI.validateUser();
		for ( var a in params) {
			obj.payload.parameters[a] = params[a];
		}
		return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;

				}).promise();
	};

	exports.registerUser = function(params) {
		console.log("registerUser :: ::"+JSON.stringify(params));
		var obj = eventAPI.registerUser();
		for ( var a in params) {
			obj.payload.parameters[a] = params[a];
		}

		return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;
				}).promise();

	};

	exports.createEvent = function(params) {
		console.log("createEvent :: ::");
		var obj = eventAPI.createEvent();
		for ( var a in params) {
			obj.payload.parameters[a] = params[a];
		}

		/*return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;
				}).promise();*/
		
		return JSON.stringify(obj.payload);

	};

	exports.forgotPassword = function() {
		console.log("forgotPassword :: ::");
		var obj = eventAPI.forgotPassword();
		return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;

				}).promise();

	};
	
	exports.getMasterData = function(params) {
		console.log("getMasterData :: ::"+JSON.stringify(params));
		var obj = eventAPI.getMasterData();
		for ( var a in params) {
			obj.payload.parameters[a] = params[a];
		}

		return amplify.request(obj.resourceID, JSON.stringify(obj.payload))
				.then(function(response) {
					return response;
				}).promise();

	};

	return exports;
});