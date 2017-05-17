app.controller("LogoutController", ['$scope', 'Principal', '$location', '$rootScope', function($scope, Principal, $location, $rootScope){

    $scope.logout = function(){
        Principal.logout();
        $location.url('/');
    };
    $scope.cancel = function(){
        $location.url("/");
    };
}]);