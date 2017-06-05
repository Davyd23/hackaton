app.controller('JobMatchingModalController',['$scope', 'job', '$uibModalInstance', 'PostingService', function($scope, job, $uibModalInstance, PostingService){
    $scope.job = job;
    console.log(job);

    $scope.close = function(){
        $uibModalInstance.dismiss();
    };

    $scope.apply = function(){
        PostingService.apply(job).then(function(response){
           if(response.status === 200){
               $uibModalInstance.close();
           }
        });
    };
}]);