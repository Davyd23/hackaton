app.controller('NavbarController', function($scope, Principal){
    Principal.checkCredentials();
    $scope.roles = [];
    $scope.logged = false;
    $scope.principal = {};

    $scope.$on("login", function(event, args){
        $scope.roles = Principal.getAuthorities();
        $scope.logged = Principal.isLogged();
        $scope.principal = Principal.getPrincipal();
    });

    $scope.$on("logout", function(event, args){
        $scope.roles = [];
        $scope.logged = false;
        $scope.principal = {};
    });

    $scope.hasRole = function(role ){
        if($scope.roles !== null ){
            for(var i in $scope.roles){
                if($scope.roles[i].role === role){
                    return true;
                }
            }
        }
        return false;
    };
});