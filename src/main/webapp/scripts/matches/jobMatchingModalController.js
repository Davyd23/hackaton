app.controller('JobMatchingModalController',['$scope', 'job', 'postingIndex', '$uibModalInstance', 'PostingService', function($scope, job, postingIndex, $uibModalInstance, PostingService){
    $scope.job = job;
    console.log(job);

    $scope.close = function(){
        $uibModalInstance.dismiss();
    };

    $scope.apply = function(){
        PostingService.apply(job).then(function(response){
           if(response.status === 200){
               $uibModalInstance.close(postingIndex);
           }
        });
    };
}]);