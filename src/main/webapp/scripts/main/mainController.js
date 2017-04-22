app.controller('MainController', function($scope, PostingService){
    $scope.name = "In Main Controller";

    PostingService.findAll().then(function(response){
        console.log(response);
    })
});