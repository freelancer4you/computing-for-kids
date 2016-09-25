'use strict';

var adminModule = angular.module('adminModule', []);

adminModule.controller('AdminAreaCtrl',  function ($scope, $http) {
	
	$http({
        method: 'GET',
        url: '/listUsers'
    })
    .then(function (response) {
    	console.log(response.data);
        if (response.status == 200) {
        	$scope.users = response.data;        	   
            console.log("Successfully loaded users");
        }
        else {
            $rootScope.authenticated = false;
            console.log("Loading users failed!");
        }
    });        	
	
	$http({
        method: 'GET',
        url: '/visitorsCount'
    })
    .then(function (response) {
    	console.log(response.data);
        if (response.status == 200) {
        	$scope.visitorsCount = response.data;        	   
            console.log("Successfully loaded visitorsCount");
        }
        else {
            $rootScope.authenticated = false;
            console.log("Loading users failed!");
        }
    });
	
	$scope.openModalBill = function(courseRegistration) {
		$scope.date = new Date();
		$scope.courseRegistration = courseRegistration;
		$("#modalBill").modal();
	}	
	
	$scope.printBill = function() {
		console.log($scope.course);
	}	
});