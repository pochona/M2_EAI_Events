/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('eventApp', ['ngMaterial'])

.controller('AppCtrl', function($http) {


})

.controller('CreerReservationCtrl', function($scope, $http) {

	$scope.addReservation = function () {
		console.log($scope.reservation)
		var url = "http://localhost:27369/ProjetEvents-war/webresources/reservation";
		$http.post(url, $scope.reservation)
		.then(function mySuccess(response) {
			$scope.return = response.data;
		}, function myError(response) {
			$scope.return = response.statutText;
		});
	};
    
});