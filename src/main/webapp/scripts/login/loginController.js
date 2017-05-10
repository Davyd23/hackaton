'use strict';
app.controller('LoginController', function($scope, Principal, $location, $http){
    $scope.credentials = {};
    $scope.error = false;

    $scope.login = function(){
        Principal.auth($scope.credentials).then(function(response){
            if(response.status === 200 ){
                $location.url("/");
            }

            $scope.error = true;
        })
    }
});