app.controller("PostingDeleteModalController",[
    '$scope', '$uibModalInstance', 'PostingService', 'items',
    function($scope, $uibModalInstance, PostingService, items){
    $scope.items = items;

    $scope.confirmDelete = function(){
        PostingService.delete($scope.items).then(function(response){
            console.log(response);
            $uibModalInstance.close(response);

        })
    };

    $scope.close = function(){
        $uibModalInstance.dismiss();
    };
}]);