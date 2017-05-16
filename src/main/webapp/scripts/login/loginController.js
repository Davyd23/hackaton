'use strict';
app.controller('LoginController', function($scope, Principal, $location){
    $scope.credentials = {};
    $scope.error = false;

    if(Principal.isLogged()){
        $location.url("/");
    }

    $scope.login = function(){
        Principal.auth($scope.credentials).then(function(response){
            if(response.status === 200 ){
                Principal.checkCredentials().then(function(response){
                    $location.url("/");
                });
            }else{
                $scope.error = true;
            }
        });
    }
});