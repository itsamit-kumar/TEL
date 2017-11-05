define([ "utils/environment", "utils/constants", "utils/utility" ],
		function(environment, constants, util) {
			'use strict';

			/*
			 * this function use to send date to server
			 */
			function getDateTimeStamp(controlParam) {

				var date = new Date();
				if ('activityEnd' == controlParam) {
					date.setDate(date.getDate() + 1);
				}
				if ('activityStart' == controlParam) {
					date.setDate((date.getDate() + 1) - 365);
				}
				var day = (date.getUTCDate() < 10 ? '0' : '')
						+ date.getUTCDate();
				var mon = ((date.getUTCMonth() + 1) < 10 ? '0' : '')
						+ (date.getUTCMonth() + 1);
				var yr = date.getUTCFullYear();
				var hr = date.getUTCHours();
				var mts = date.getUTCMinutes();
				var sec = date.getUTCSeconds();
				return (yr + "-" + mon + "-" + day + "T" + hr + ":" + mts + ":"
						+ sec + "Z");
			}

			/*
			 * Get all information of client info from util method for loginfo
			 */
			var client = util.client();
			return {

				searchEvent : function() {
					return {
						resourceID : 'searchEvent',
						uri : environment.serviceURI,
						payload : {
							serviceName : "Search_Event",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								requestorUserId : environment.user,
								returnRequestID : ""
							}

						}
					};

				},

				getEventDetails : function() {
					return {
						resourceID : 'getEventDetails',
						uri : environment.serviceURI,
						payload : {
							serviceName : "EVENT_DETAIL",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								roleID : '',
							}

						}
					};

				},

				validateUser : function() {
					return {
						resourceID : 'validateUser',
						uri : environment.serviceURI,
						payload : {
							serviceName : "",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								Event_Id : ""

							}

						}
					};
				},

				registerUser : function() {
					return {
						resourceID : 'registorNewUser',
						uri : environment.serviceURI,
						payload : {
							serviceName : "USER_ADD_SERVICE",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								roleID : '',
							}

						}
					};

				},

				createEvent : function() {
					return {
						resourceID : 'createEvent',
						uri : environment.serviceURI,
						payload : {
							serviceName : "USER_ADD_SERVICE",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								roleID : '',
								/*listOfUserIds : '',
								isTransporter : '',
								transporterId : ''*/
							}

						}
					};

				},

				forgotPassword : function() {
					return {
						resourceID : 'forgotPassword',
						uri : environment.serviceURI,
						payload : {
							serviceName : "USER_ADD_SERVICE",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								roleID : '',
							}

						}
					};

				},
				
				getMasterData : function() {
					return {
						resourceID : 'getMasterData',
						uri : environment.serviceURI,
						payload : {
							serviceName : "GET_MASTER_DATA_SERVICE",
							header : {
								requestId : environment.sessionID,
								requestTime : getDateTimeStamp(),
								appId : environment.APPID,
								requestedBy : environment.user,
								deviceName : client.deviceName,
								deviceOSName : client.os,
								deviceOSVersion : client.osVersion,
								deviceNetworkType : "",
								version : environment.AppVersion
							},
							parameters : {
								roleID : '',
							}

						}
					};

				},
			};
		});