define([ 'controllers/mainController','controllers/eventController', 
         'controllers/errorController','controllers/userController'], 
         function(main, event, error, user) {

	return {
    	main : main,
    	event : event,
    	error: error,
    	user:user
    };
    
});