'use strict';
app.controller('LoginController', function($scope, $http, $cookies){
    $scope.credentials = {};
    $scope.error = false;

    var authenticate = function(credentials) {
        $http({
            method: 'POST',
            url: "/login",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data: credentials
        }).then(function(response){
            console.log(response);
        },function(err){
            console.log(err);
        });
        /*
         *when there is no form login
         */
        /*
         var headers = credentials ? {authorization : "Basic "
         + btoa(credentials.username + ":" + credentials.password)
         } : {};

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

        });*/

    }
    // authenticate();

    $scope.login = function(){
       /* if($scope.credentials.email!== "" && $scope.credentials.email !==null && $scope.credentials.email!== undefined &&
            $scope.credentials.password!== "" && $scope.credentials.password !==null && $scope.credentials.password!== undefined){*/

        console.log($cookies);
        authenticate($scope.credentials );
        /*}else{
            $scope.error = true;
        }*/
    }
});