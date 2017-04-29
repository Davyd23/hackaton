'use strict';
app.controller('LoginController', function($scope, $http){
    $scope.credentials = {};
    $scope.error = false;

    var authenticate = function(credentials) {

        var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

       /* $http.post("/login",$scope.credentials).then(function(response){
            console.log(response);
        },function(err){
            console.log(err);
        })*/
        $http.get('user', {headers : headers}).then(function(response) {
            console.log(response);
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
                $scope.error = true;
            }

        }, function(err) {
            console.log(err);
            $rootScope.authenticated = false;
            $scope.error = true;

        });

    }

    // authenticate();
    $scope.login = function(){
       /* if($scope.credentials.email!== "" && $scope.credentials.email !==null && $scope.credentials.email!== undefined &&
            $scope.credentials.password!== "" && $scope.credentials.password !==null && $scope.credentials.password!== undefined){*/

            authenticate($scope.credentials );
        /*}else{
            $scope.error = true;
        }*/
    }
});