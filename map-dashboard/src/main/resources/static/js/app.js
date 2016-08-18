'use strict';

var module = angular.module("MapApp", ['ngRoute', 'ui.bootstrap', 'dateModule', 'adminModule']).config(function($routeProvider, $httpProvider) {
    
    $routeProvider
    .when('/', {
        templateUrl : 'partials/index.html',
    })
    .when('/home', {
        templateUrl : 'partials/index.html'
    })
    .when('/courses/', {
        name : "Courses",
        templateUrl : 'partials/courses/index.html',
    })
    .when('/courses/kids', {
        name : "Kids",
        templateUrl : 'partials/courses/kids/index.html',
    })
    .when("/courses/teachers",  {
        name : "Teachers",
        templateUrl : "partials/courses/teachers/index.html",
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
    .otherwise('/');
    
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
    
    $scope.register = function() {
       
        $http({
            method: 'POST',
            url: '/user',
            data: $scope.credentials,
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain"
            }
        })
        .then(function (response) {
            if (response.status == 200) {
                $("#modalSingup").modal('hide');
                $scope.login();
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

module.controller('CarouselDemoCtrl', function ($scope) {
  $scope.myInterval = 5000;
  var slides = $scope.slides = [];
  $scope.addSlide = function(count) {
    slides.push({
      image: '/img/carousel/p' + count + '.jpg',
      text: ['CLAWBOT','REX','IKE','ARMBOT','3D'][slides.length % 5] 
    });
  };
  for (var i = 1; i <= 5; i++) {
    $scope.addSlide(i);
  }
});

module.factory('DetailsData', function () {
  return { course: '' };
});

module.controller('CourseCtrl', ['$scope','$http','DetailsData', function ($scope, $http, detailsData) {
    $http.get('/listCourses').then(function(response) {
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
                  for(var l = 0; l < days.length; l++){
                     schedule.daysAsString += days[l];
                  }
              }
            }          
        }
        $scope.courses = courses; 
      }
    });
    
    $scope.toggle = function(state) {
      $scope.results.forEach(function(e) {
        e.open = state;
      });
    };
    
    $scope.showDetails = function(courseId) {
        //console.log("Search for course with id:" + courseId);
        
       // detailsService.clear();
        
        // TODO store course data in localstore and first try to load it from there
        // if it can not be found load from remote
        
        $http.get('/computing/webresources/courses/' + courseId).then(function(response) {
                detailsData.course = response.data;
                //console.log("Details:" +response.data.name)
               //detailsService.addCourse(response);
               //TODO do not animate, just scroll to the top
               jQuery('body,html').animate({scrollTop:0},800);
            } 
        );        
    };    
  }
]);

module.controller('DetailsCtrl', ['$scope', 'DetailsData', function ($scope, detailsData) {
    $scope.course = detailsData;
  }
]);

function formatTimeStamp(timeStamp){    
    if(timeStamp === undefined){
        return "";
    }
    var datePart = timeStamp.split("T");
    return datePart[0];
}

module.controller('TabsCtrl', ['$scope', 'DetailsData', function ($scope, detailsData) {
  $scope.tabs = [
    { title:'Description', content:detailsData },
    { title:'Content', content:detailsData },
    { title:'Schedules', content:detailsData },
    { title:'Costs', content:detailsData }
  ];
  
  $scope.showDescription = function(message) {
        return "Description" === message;
    }
    $scope.showContent= function(message) {
        return "Content" === message;
    }
    $scope.showCosts= function(message) {
        return "Costs" === message;
    }
    $scope.showSchedules= function(message) {
        return "Schedules" === message;
    }
}]);


