define([ "utils/environment","utils/constants" ], function(environment, constants) {
    'use strict';
    
	var initDataResources = function(){
		var res = [{name:'searchEvent', servicePath:'eventServices/searchEvent', type:'post'}, 
		           {name:'getEventDetails', servicePath:'eventServices/eventDetails', type:'post'},
		           {name:'createEvent', servicePath:'eventServices/createEvent', type:'post'},
		           {name:'getEventList', servicePath:'eventServices/getEventList', type:'post'},
		           {name:'registorNewUser',servicePath:'userServices/registration',type:'post'},
		           {name:'validateUser', servicePath:'userServices/validateUser', type:'post'},
		           {name:'forgotPassword', servicePath:'userServices/forgotPassword', type:'post'},
		           {name:'getMasterData', servicePath:'eventServices/getMasterData', type:'post'},
		          ];
	
		var getContentType=function(p){
			if(p.contentTypeFlag){
				return  p.contentType
			}
			else{
			return constants.contentTypeJSON;
			}
		};
		
		for(var i=0; i<res.length; i++){
			//console.log("res[i].name " +res[i].name);
			//console.log("url " +environment.serviceURI +res[i].servicePath);
			//console.log("amplify  " +amplify);
			amplify.request.define(res[i].name, "ajax", {
			    url : environment.serviceURI +res[i].servicePath,
			   
			    dataType : "json",
			    contentType : getContentType(res[i]),
			    xhrFields : {
				withCredentials : true
			    },
			    /*Origin:'*',*/
			    type : res[i].type,
			    timeout : constants.timeOut,
			    decoder: function ( data, status, xhr, success, error ) {
			    	console.log("decoder 1: ", data);
			    	console.log("decoder 2: ", status);
			    	console.log("decoder 3: ", xhr);
			    	
				        if ( status === "success" ) {
				            success( data, xhr );
				        } else {
				        	amplify.publish( "eventAPIError", { status: status, xhr:xhr});
				            error( status, xhr );
				        }
			    }
			});
		}
		

	};
	
	return {
		init : function(){
			initDataResources();
			return true;
		}
	};

 });