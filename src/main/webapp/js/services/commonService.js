/**
 * 
 */

define(function() {
	var lifecycle = "Dev";
	return {
		contentTypeJSON : "application/json",
		contentTypeText : "text/plain",
		contentTypeFormEncoded : "application/x-www-form-urlencoded",
		APP_ID : "EVENT_WEB_APP",
		timeOut : 90000,
		EVENT_DETAILS_PAGE:"/event/#eventList",
		EVENT_LIST_PAGE:"",
		DASHBOARD_PAGE:"",
		FORGOT_PASSWORD_PAGE:"",
		ERROR_PAGE:""
		
	};
});

app.service('commonService', function() {

    var data = {};

    this.getData = function () {
        return data;
    }

    this.setData = function (dataToSet) {
        data = dataToSet;
    }

});