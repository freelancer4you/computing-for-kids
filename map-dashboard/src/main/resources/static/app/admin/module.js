'use strict';

var adminModule = angular.module('adminModule', []);

adminModule.controller('AdminAreaCtrl', ['$scope', '$http', function ($scope, $http) {
	
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
	
	$scope.printBill = function(user) {
		console.log("Rechnung Drucken ...");
		console.log(user);
	}
	
}
]);