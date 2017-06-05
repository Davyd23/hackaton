app.controller("PostingApplicantController", ['$scope', 'PostingService', 'posting', '$uibModalInstance', function($scope, PostingService, posting, $uibModalInstance){

    $scope.posting = posting;
    $scope.applicants = [];
    PostingService.getApplicants($scope.posting).then(function(response){
        $scope.applicants = response.data;
    });

    $scope.close=function(){
        $uibModalInstance.close();
    }
}]);