app.controller('RegisterController', function($scope, $uibModalStack, $hhtp){
    $scope.candidate = {};

    $scope.saveUser = function(){
        http.post("/hackaton/user/save", credentials).then(function(response){
            console.log(response);
        }, function(err){
            console.log(err);
        })
    }

    $scope.cancel = function(){
        var top = $uibModalStack.getTop();
        if (top) {
            $uibModalStack.dismiss(top.key);
        }

    }
});