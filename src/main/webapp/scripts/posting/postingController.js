app.controller('PostingController', function($scope, PostingService, $uibModal){
    $scope.selectedPostings = [];
    $scope.posting = {};
    $scope.workExperienceOptions = ['0+','1+','2+','3+','4+','5+','6+','7+','8+','9+','10+'];
    $scope.displayForm = false;
    $scope.deletePosting = {};
    $scope.modalInstance = null;

    PostingService.getAll().then(function(response){
        console.log(response);
        $scope.postings = response.data;
    });

    $scope.addOrRemovePosting = function(postingId){
        var elementIndex = $scope.selectedPostings.indexOf(postingId);
        if(elementIndex > -1 ){
            $scope.selectedPostings.splice(elementIndex, 1);
        }else{
            $scope.selectedPostings.push(postingId);
        }
    };

    $scope.checkOnlyOnePostingSelected = function(){
        if($scope.selectedPostings.length === 1){
            return false;
        }
        return true;
    };

    $scope.checkOneOrMoreSelected = function(){
        if($scope.selectedPostings.length > 0){
            return false;
        }
        return true;
    };

    $scope.openPosting = function( event ){
        if(event === 'new'){
            $scope.posting = {
                uuid : generateUUID()
            };
        }else if(event === 'edit' ){
            $scope.posting = $scope.postings[ $scope.selectedPostings[0] ];
        }
        $scope.displayForm = true;
    };

    $scope.savePosting = function(){
        PostingService.save($scope.posting).then(function(response){
            console.log(response);

            if($scope.postings.indexOf($scope.posting) === -1){
                $scope.postings.push($scope.posting);
            }
        })
    };

    $scope.delete = function(){
        $scope.deletePosting = [];

        for(var i =0; i < $scope.selectedPostings.length; i++){
            $scope.deletePosting.push($scope.postings[ $scope.selectedPostings[i] ]);
        }

        var modalInstance = $uibModal.open({
            templateUrl: 'scripts/posting/postingDeleteModal.html',
            controller: 'PostingDeleteModalController',
            resolve: {
                items: function () {
                    return $scope.deletePosting;
                }
            }
        });

        modalInstance.result.then(function(result){
            console.log(result);
            $scope.selectedPostings = [];
            if(result.status === 200) {
                var index = -1;
                for(var i =0; i < $scope.deletePosting.length; i++){
                    index = $scope.postings.indexOf($scope.deletePosting[i] );
                    $scope.postings.splice(index, 1);
                }
            }
        });
    };

    $scope.viewApplicants = function(){


        var modalInstance = $uibModal.open({
            templateUrl: 'scripts/posting/postingApplicant.html',
            controller: 'PostingApplicantController',
            resolve: {
                posting: function () {
                    return $scope.postings[ $scope.selectedPostings[0] ];
                }
            }
        });

        modalInstance.result.then(function(result){
            console.log(result);
        },function(result){
            console.log(result);
        });
    };

    $scope.checkWorkExperienceSelected = function(workExperience){
        return $scope.posting.profile.workExperience.indexOf(workExperience) === 0;
    };
});

function generateUUID () { // Public Domain/MIT
    var d = new Date().getTime();
    if (typeof performance !== 'undefined' && typeof performance.now === 'function'){
        d += performance.now(); //use high-precision timer if available
    }
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}