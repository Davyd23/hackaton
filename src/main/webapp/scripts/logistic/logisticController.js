app.controller("LogisticController", function($scope, $uibModal, $uibModalStack){

    $scope.openCandidateRegistration = function(){
        $uibModal.open({
            templateUrl: 'scripts/register/registerCandidate.html',
            controller: 'RegisterController',
        });
    }

    $scope.openRecruiterRegistration = function(){
        $uibModal.open({
            templateUrl: 'scripts/register/registerRecruiter.html',
            controller: 'RegisterController',
        });
    }

    $scope.close = function(){
        $uibModalStack.dismissAll();
    }
});