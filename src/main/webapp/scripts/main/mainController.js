app.controller('MainController', function($scope, PostingService, $uibModal){
    $scope.name = "In Main Controller";

    PostingService.findAll().then(function(response){
        console.log(response);
    })

    var modalInstance = $uibModal.open({
        templateUrl: 'scripts/register/registerView.html',
        controller: 'LogisticController',
        // size: size
    });
});