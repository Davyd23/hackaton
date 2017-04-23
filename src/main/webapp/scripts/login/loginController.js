app.controller('LoginController', function($scope, AuthService, $uibModalStack){
    $scope.loggedUser = true;
    $scope.login = function(){
        AuthService.login($scope.credentials).then(function(response){
            console.log(response);
        });
    }

    $scope.cancel = function(){
        var top = $uibModalStack.getTop();
        if (top) {
            $uibModalStack.dismiss(top.key);
        }
    }
});