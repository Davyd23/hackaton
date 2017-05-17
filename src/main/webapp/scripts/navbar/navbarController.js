app.controller('NavbarController', function($scope, Principal){
    Principal.checkCredentials();
    $scope.roles = [];
    $scope.logged = false;

    $scope.$on("login", function(event, args){
        $scope.roles = Principal.getAuthorities();
        $scope.logged = true;
    });

    $scope.$on("logout", function(event, args){
        $scope.roles = [];
        $scope.logged = false;
    });

    $scope.hasRole = function(role ){
        if($scope.roles !== null ){
            for(var i in $scope.roles){
                if($scope.roles[i].authority === role){
                    return true;
                }
            }
        }
        return false;
    };
});