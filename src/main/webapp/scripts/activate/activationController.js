app.controller('ActivationController',['$scope', 'UserService', '$routeParams', '$location', function($scope, UserService, $routeParams, $location){
    $scope.valid = false;

    console.log($routeParams);
    UserService.activate($routeParams.code).then(function(response){
        if(response.status=== 200){
            $scope.valid = true;
            $location.url('/login');
        }

    });
}]);