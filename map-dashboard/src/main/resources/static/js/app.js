'use strict';

var module = angular.module("MapApp", ['ngRoute', 'ui.bootstrap', 'dateModule', 'adminModule']).config(function($routeProvider, $httpProvider) {
    
    $routeProvider
    .when('/home', {
        templateUrl : 'partials/index.html'
    })
    .when('/courses', {
        name : "Courses",
        templateUrl : 'partials/courses/index.html',
    })
    .when('/courses/kids', {
        name : "Courses-Kids",
        templateUrl : 'partials/courses/kids/index.html',
    })
    .when("/courses/teachers",  {
        name : "Courses-Teachers",
        templateUrl : "partials/courses/teachers/index.html",
    })
    .when("/courses/details",  {
        name : "Courses-Details",
        templateUrl : "partials/courses/details/index.html",
    })
    .when("/courses/suggest",  {
        name : "Courses-Suggest",
        templateUrl : "partials/courses/suggest/index.html",
    })
    .when("/courses/register",  {
        name : "Courses-Register",
        templateUrl : "partials/courses/register/index.html",
    })
    .when("/courses/register/sucess",  {
        name : "Courses-Register-Sucess",
        templateUrl : "partials/courses/register/success.html",
    })
    .when("/about",  {
        name : "About",
        templateUrl : "partials/about/index.html",
    })
    .when("/contact",  {
        name : "Contact",
        templateUrl : "partials/contact/index.html",
    })
    .when("/admin",  {
        name : "Admin-Area",
        templateUrl : "partials/admin/index.html",
        controller: 'AdminAreaCtrl'    
    })
    .otherwise('/home');
    
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}).controller('navigation',

function($rootScope, $scope, $http, $location) {
    
    $scope.vm = {
        submitted: false,
        errorMessages: []
    };    

    $scope.preparePostData = function () {
        
        if($scope.credentials === undefined){
            return null;
        }
        
        var username = $scope.credentials.userName != undefined ? $scope.credentials.userName : '';
        var password = $scope.credentials.password != undefined ? $scope.credentials.password : '';
        var email = $scope.credentials.email != undefined ? $scope.credentials.email : '';

        return 'username=' + username + '&password=' + password + '&email=' + email;
    }
    
    var authenticate = function(credentials, callback) {
                
        var postData = $scope.preparePostData();
        
        $http({
            method: 'POST',
            url: '/authenticate',
            data: postData,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "X-Login-Ajax-call": 'true'
            }
        })
        .then(function(response) {
            if (response.data == 'ROLE_USER') {
                $rootScope.authenticated = true;
                $rootScope.userRole = response.data;
                $location.path("/reports");
            }
            else if (response.data == 'ROLE_ADMIN') {
                $rootScope.authenticated = true;
                $rootScope.userRole = response.data;
                $location.path("/admin");
            }
            else {
                $scope.vm.errorMessages = [];
                $scope.vm.errorMessages.push({description: 'Access denied'});
                $rootScope.authenticated = false;
            }
            callback && callback();
        });
    }

    $scope.credentials = {};
    $scope.login = function() {
        authenticate($scope.credentials, function() {
        	console.log($rootScope.userRole)
            if ($rootScope.authenticated) {
            	if($rootScope.userRole == 'ROLE_USER'){
	                $location.path("/reports");
	                $('#loginAlert').html("<div></div>");
	                $("#modalLogin").modal('hide');
	                $scope.error = false;
            	}else if($rootScope.userRole == 'ROLE_ADMIN'){
            		$location.path("/admin");
            		$('#loginAlert').html("<div></div>");
	                $("#modalLogin").modal('hide');
            		$scope.error = false;
            	}
            }
            else {
                $('#loginAlert').html("<div class='alert alert-danger' role='alert'>Login Failed</div>");
                $('#loginAlert').show();
                $scope.error = true;
            }
        });
    };
    
    $scope.register = function(course) {
       
        $http({
            method: 'POST',
            url: '/user',
            data: $scope.credentials,
            params: {'id': course.id},
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain"
            }
        })
        .then(function (response) {
            if (response.status == 200) {
            	$location.path("/courses/register/sucess");
            }
            else {
                $scope.vm.errorMessages = [];
                $scope.vm.errorMessages.push({description: response.data});
                $rootScope.authenticated = false;
                console.log("failed user creation: " + response.data);
            }
        });        
    };

    $scope.logout = function() {
        
        $http({
            method: 'POST',
            url: '/logout'
        })
        .then(function (response) {
            if (response.status == 200) {
                $rootScope.authenticated = false;
                $location.path("/");
               
                console.log("Successfully logged out");
            }
            else {
                $rootScope.authenticated = false;
                console.log("Logout failed!");
            }
        });        
    };
    
});

module.controller('CourseCtrl',

	function($rootScope, $scope, $http, $location) {
			
			// TODO das sollte in eine Methode und auch über Methode aufgerufen werden
			// in der Methode muss noch der Pfad übergeben werden
			// /courses/pfad z.B. /courses/kids
			// dann muss aber wahrscheinlich der Controller verschoben werden
			//$scope.listCourses = function(path) {
		        
		        $http({
		            method: 'GET',
		            url: '/listCourses'
		        })
		        .then(function (response) {
		            if (response.status == 200) {
		                
		            	var courses = response.data;
		                
		                if(courses !== undefined){
		                  for(var i = 0; i < courses.length; i++){
		                      var course = courses[i];
		                      var schedules = course.schedules;
		                      if(schedules !== undefined){
		                        for(var j = 0; j < schedules.length; j++){
		                            var schedule = schedules[j];
		                            schedule.begin = formatTimeStamp(schedule.begin);
		                            schedule.end = formatTimeStamp(schedule.end);  
		                            var days = schedule.days;
		                            schedule.daysAsString  = "";
		                            if( days !== undefined) {
		          	                  for(var l = 0; l < days.length; l++){
		          	                     schedule.daysAsString += days[l];
		          	                  }
		                            }
		                        }
		                      }          
		                  }
		                  $scope.courses = courses; 
		                }
		            	
		                $location.path("/courses/kids");
		               
		                console.log("Successfully loaded courses");
		            }
		            else {
		                console.log("Logout failed!");
		            }
		        });        
		    //};
		  
		        $scope.showDetails = function(course) {
		            
		            $http({
		                method: 'GET',
		                url: '/course/details',
		                params: {'id': course.id}		                
		            })
		            .then(function (response) {
		                if (response.status == 200) {
		                	console.log("Successfully loaded details");
		                	$rootScope.course = course;
			            	$rootScope.course.details = response.data;
			            	// TODO hier sollte auch das Routing stattfinden
			            	$location.path("/courses/details");
		                }
		                else {
		                    console.log("loading details failed");
		                }
		            });        
		        };
		        
		        $scope.registerForCourse = function(course) {
		        	$rootScope.course = course;
		        	// TODO hier sollte auch das Routing stattfinden
		        	console.log("switch to register page")
	            	$location.path("/courses/register");
		        };
	}
);

function formatTimeStamp(timeStamp){    
    if(timeStamp === undefined){
        return "";
    }
    return timeStamp;
}

