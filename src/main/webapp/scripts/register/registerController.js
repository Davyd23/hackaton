app.controller('RegisterController', function($scope, $uibModalStack){
    $scope.candidate = {};
    /*var modalInstance = $uibModal.open({
     templateUrl: 'scripts/main/jobView.html',
     // controller: 'ModalInstanceCtrl',
     // size: size
     });*/
    // $dismiss('test');


    $scope.close = function(){
        $uibModalStack.dismissAll();
    }
});