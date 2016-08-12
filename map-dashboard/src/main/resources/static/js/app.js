'use strict';

angular.module("MapApp", ['ngRoute','ui.bootstrap','dateModule']).config(function($routeProvider, $httpProvider) {
    
    $routeProvider
    .when('/', {
        templateUrl : 'partials/index.html',
//        controller : 'navigation'
    })
    .when('/home', {
        templateUrl : 'partials/index.html'
    })
    .when('/reports', {
        name : "Reports",
        templateUrl : 'partials/reports/index.html',
//        controller : 'navigation'
    })
    .when("/editor",  {
        name : "Editor",
        templateUrl : "partials/editor/index.html",
//        controller: 'DashBoardCtrl'    
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
        	//console.log(response.data)
            if (response.data == 'ROLE_USER') {
                console.log("logged in successfully ...");
                $rootScope.authenticated = true;
                $rootScope.userRole = response.data;
                $location.path("/reports");
            }
            else if (response.data == 'ROLE_ADMIN') {
                console.log("logged in successfully ...");
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
                //console.log(response.headers);
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
        
//        var token = getCookie("XSRF-TOKEN");
//        console.log(token);
        
        $http({
            method: 'POST',
            url: '/logout'
//            headers: {
//                "X-CSRF-TOKEN": token
//            }
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
    
    /*function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
        }
        return "";
    }*/

});