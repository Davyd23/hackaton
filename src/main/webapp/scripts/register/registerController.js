app.controller('RegisterController', function($scope, UserService, $location){
    $scope.registeringUser = {};
    $scope.invalidForm = false;
    $scope.emailExists = false;

    $scope.register = function(form){

        if(form.$valid && form.$dirty){
            UserService.register($scope.registeringUser).then(function(response){
                console.log(response);
                if(response.status === 200){
                    $location.url("/login");
                }else{
                    $scope.emailExists = true;
                }
            });
        }else{
            $scope.invalidForm = true;
        }
    }

    $scope.isFieldValid = function(field){
        if($scope.invalidForm){
            if(field!== null && field!== undefined && field!=='' ){
                return true;
            }
            return false;
        }

        return true;
    }

});