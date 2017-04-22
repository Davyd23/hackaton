app.controller('LoginController', function($scope, AuthService){
    $scope.login = function(){
        AuthService.login($scope.credentials).then(function(response){
            console.log(response);
        });
    }

});