/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('eventApp', ['ngMaterial'])

.controller('AppCtrl', function($http) {
    console.log("ok")
    $scope.val = {value : "ok"};
    var url = "http://localhost:29201/ProjetEvents-war/webresources/reservation";
    $http({
    method : "POST",
    url : url,
    data : $scope.val,
    headers : {
        'Content-Type' : 'application/json'
    }
    }).then( _success, _error );
});