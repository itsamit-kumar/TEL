define(['utils/constants' ],
		function(constants) {
			'use strict';

			var lifecycle = "dev";
			var protocol = "http:";

			if (window.location.protocol === "https:") {
				protocol = "https:";
			}

			if (window.location.hostname.indexOf('localhost') != -1) {
				lifecycle = "local";
			} else if (window.location.hostname.indexOf('eventlabs-stg-b2x') != -1) {
				lifecycle = "stage";
			}else {
				lifecycle = "prod";
			}

			var constants = {

				lifecycle : "dev",

				APPID : "EVENTLABS_WEB_APP",

				prod : {
					serviceURI : "https://www.eventlabs.com/",
					logoutURI : "https://www.eventlabs.com/"
				},
				stage : {
					serviceURI : "https://www.eventlabs.com/",
					logoutURI : "https://www.eventlabs.com/"
				},

				dev : {
					serviceURI : "https://www.eventlabs.com/",
					logoutURI : "https://www.eventlabs.com/"
				},

				local : {
					serviceURI : "http://localhost:8080/event/",
					logoutURI : "http://localhost:8080/eventlabs/"
				}

			};

			var getUniqueId = function(inp) {
				var cipher = [ 'k', 's', 'z', 'h', 'x', 'b', 'p', 'j', 'v',
						'c', 'g', 'f', 'q', 'n', 't', 'm' ];
				var result = "";
				if (inp == null)
					inp = "";
				inp = encodeURIComponent(inp);
				for (var i = 0; i < inp.length; i++) {
					var cc = inp.charCodeAt(i);
					result += cipher[Math.floor(cc / 16)] + cipher[cc % 16];
				}
				return result;
			};

			var exports = {

				lifecycle : null,

				profile : null,

				user : null,

				role : null,

				email : null,
				
				userRole : null,
				
				industryList:null,
				countryList:null,
				cityList:null,
				eventTypeList:null,

				userRoleID : null,
				APPID : constants.APPID,

				AppVersion : "1.0",
				searchURI : "",

				sessionID : getUniqueId("EVENT"),

				serviceURI : constants[lifecycle].serviceURI,

				logoutURI : constants[lifecycle].logoutURI,
               //Here we can add number of user with different user role (if required) 
				isAdmin : function() {
					if (this.userRole == "Admin") {
						return true;
					} else {
						return false;
					}
				},

			};

			exports.init = function() {
				exports.APPID = constants.APPID;
				exports.sessionID = getUniqueId("EVENT");
				exports.profile = null;
			};

			return exports;
		});