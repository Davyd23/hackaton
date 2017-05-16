app.controller('NavbarController', function($scope, Principal){
    Principal.checkCredentials();
    $scope.roles = [];
    $scope.logged = false;

    $scope.$on("principalSet", function(event, args){
        $scope.roles = Principal.getAuthorities();
        $scope.logged = true;
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