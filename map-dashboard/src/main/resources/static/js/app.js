'use strict';

var module = angular.module("MapApp", ['ngRoute', 'adminModule'])
	.config(function($routeProvider, $httpProvider, $locationProvider) {
	
//	$locationProvider.html5Mode(true);	
		
    $routeProvider
    .when('/', {
		templateUrl : 'partials/index.html'		
	})
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
    .when("/bill",  {
        name : "Admin-Bill",
        templateUrl : "partials/admin/bill.htm"        
    })
    .when("/impressum",  {
        name : "Impressum",
        templateUrl : "partials/impressum/index.html"
    })
    .otherwise('/');
    
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

module.controller('LoginCtrl',

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
        
        return 'username=' + username + '&password=' + password;
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
        	console.log(response);
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
            	}
            	else if($rootScope.userRole == 'ROLE_ADMIN'){
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
});

module.controller('LogoutCtrl',
	function($rootScope, $scope, $http, $location) {
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
	}
);

module.controller('CoursesCtrl',

	function($scope, $http, $location, SharedCourses) {
			
		    $scope.$watch('courses', function (newValue, oldValue) {
		        if (newValue !== oldValue) SharedCourses.setCourses(newValue);
		    });
			
			if(SharedCourses.getCourses() !== undefined && SharedCourses.getCourses() !== '') {
				console.log("Kurse bereits geladen ...")
				$scope.courses = SharedCourses.getCourses();
			}	

			// TODO das sollte in eine Methode und auch über Methode aufgerufen werden
			// in der Methode muss noch der Pfad übergeben werden
			// /courses/pfad z.B. /courses/kids
			// dann muss aber wahrscheinlich der Controller verschoben werden
			//$scope.listCourses = function(path) {
			$scope.listCourses = function() {
				if($scope.courses == undefined){
			        $http({
			            method: 'GET',
			            url: '/listCourses'
			        })
			        .then(function (response) {
			            if (response.status == 200) {
			                
			            	var courses = response.data;
			                
			                if(courses !== undefined) {
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
			                  //SharedCourses.setCourses(courses);
			                  $location.path("/courses/kids");
			                }
			                else{
			                	console.log("Es konnten keine Kurse geladen werden");
			            		console.log(response.data);
			                }
			            }
			            else {
			                console.log("Fehler beim Laden der Kurse:");
			                console.log(response);
			            }
			        });
		        }
		        else{
		        	$location.path("/courses/kids");
		        }
		    };
	}
);

module.controller('CourseSelectionCtrl',

		function($scope, $http, $location, SharedCourses, SharedData) {
//			console.log("Setup CoursesCtrl");
//			console.log(SharedCourses.getCourses());
			$scope.course = '';
			
			$scope.$watch('course', function (newValue, oldValue) {
		        if (newValue !== oldValue) SharedData.setCourse(newValue);
		    });
			
			$scope.$watch('course.details', function (newValue, oldValue) {
		        if (newValue !== oldValue) SharedData.setCourseDetails(newValue);
		    });
			
			$scope.courses = SharedCourses.getCourses();
			
	        $scope.showDetails = function(course) {
	            $http({
	                method: 'GET',
	                url: '/course/details',
	                params: {'id': course.id}		                
	            })
	            .then(function (response) {
	                if (response.status == 200) {
	                	$scope.course = course;
		            	$scope.course.details = response.data;
		            	$location.path("/courses/details");
	                }
	                else {
	                    console.log("loading details failed");
	                }
	            });        
	        };
	        
	        $scope.registerForCourse = function(course) {
	        	$scope.course = course;
	        	$location.path("/courses/register");
	        };
		}
);

module.controller('CourseDetailsCtrl',

		function($scope, $http, $location, SharedData) {
			$scope.course = SharedData.getCourse();
			$scope.course.details = SharedData.getCourseDetails();
			
			$scope.cancel = function(course) {
				SharedData.setCourse(undefined);
				SharedData.setCourseDetails(undefined);
				$location.path("/courses/kids");
			}
			
			$scope.registerForCourse = function(course) {
	        	$scope.course = course;
	        	$location.path("/courses/register");
	        };
		}
);

module.controller('RegisterCtrl',

		function($scope, $http, $location, SharedData) {
			$scope.course = SharedData.getCourse();
			// Wenn benoetigt auskommentieren
			//$scope.course.details = SharedData.getCourseDetails();
			
			$scope.cancel = function(course) {
				SharedData.setCourse(undefined);
				SharedData.setCourseDetails(undefined);
				$location.path("/courses/kids");
			}
			
			$scope.$watch('credentials', function (newValue, oldValue) {
		        if (newValue !== oldValue) SharedData.setCredentials(newValue);
		    });
			
			$scope.defaultRegistration = function(course) {
				register('defaultRegistration', $scope.credentials,course);						
			};
			
			$scope.googleRegistration = function(course) {
				var params = {
						'clientid': '554536775328-40gntdhkh3ep0irr0t4is5g46m9t5if0.apps.googleusercontent.com',
						'cookiepolicy': 'single_host_origin',
						'callback': function(result){
							//console.log(result);
							if(result['status']['signed_in']){
								var request = gapi.client.plus.people.get(
										{
											'userId': 'me'
										}								
								);
								
								request.execute(function(resp){
									$scope.$apply(function(){
										var googleAccount = {
												'familyName' : resp.name.familyName,
												'gender' : resp.gender,
												'givenName' : resp.name.givenName,
												'language' : resp.language,
												'displayName' : resp.displayName,
												'email' : resp.emails[0].value,
												'imageUrl' : resp.image.url,												
										};
										console.log(googleAccount);
										register('googleRegistration', googleAccount, course);										
									});		
								});
							}
						},
						'approvalprompt': 'force',//'auto' Auto nicht verwenden, da ansonsten request.execute 2mal aufgerufen wird
						'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
				};
				gapi.auth.signIn(params);
			}
			
			function register(url, data, course) {
				$http({
					method: 'POST',
					url: '/' + url,
					data: data,
					params: {'id': course.id},
					headers: {
						"Content-Type": "application/json",
						"Accept": "application/json"
					}
				})
				.success(function (data, status, headers, config) {
					//$(".alert").alert('close');
					$location.path("/courses/register/sucess");
	            })
	            .error(function (data, status, header, config) {
	            	if(status == 409) {
	            		$('#alert_placeholder').html("<div class='alert alert-danger' role='alert'>" + data.message + "</div>");
	            	}
	            	else {
	            		$('#alert_placeholder').html("<div class='alert alert-danger' role='alert'>Unbekannter Fehler</div>");
	            	}
	            });
			}
		}
);


module.controller('VerificationCtrl',
	function($scope, $http, $location, SharedData) {
		$scope.course = SharedData.getCourse();
		$scope.credentials = SharedData.getCredentials();
	}
);

module.factory('SharedCourses', function () {

    var data = {
    		courses: ''
    };

    return {
        getCourses: function () {
            return data.courses;
        },
        setCourses: function (courses) {
            data.courses = courses;
        }
    };
});

module.factory('SharedData', function () {

    var data = {
    		course: '',
    		courseDetails: '',
    		credentials: ''
    };

    return {
        getCourse: function () {
            return data.course;
        },
        setCourse: function (course) {
            data.course = course;
        },        
        getCourseDetails: function () {
            return data.courseDetails;
        },
        setCourseDetails: function (details) {
            data.courseDetails = details;
        },        
        getCredentials: function () {
            return data.credentials;
        },
        setCredentials: function (credentials) {
            data.credentials = credentials;
        }
    };
});

function formatTimeStamp(timeStamp){    
    if(timeStamp === undefined){
        return "";
    }
    return timeStamp;
}

function printDiv(divName) {
	var div = document.getElementById(divName);
	console.log("print from :");
	console.log(divName);
    console.log(div);
    
    var printContents = div.innerHTML;
    var originalContents = document.body.innerHTML;
    console.log(originalContents);
    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}

function loadGoolgeApi() {
	gapi.client.setApiKey('AIzaSyD_Vd8fw0bJcWU9WyxioBCKin1YjlWTuBU');
	gapi.client.load('plus', 'v1', function() {});	
}
