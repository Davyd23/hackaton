app.controller('MainController', function($scope, $http){
    $scope.name = "In Main Controller";

    $http.get('user').then(function(response){
        console.log(response);
    })
});