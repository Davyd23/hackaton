app.controller('RegisterController', function($scope, UserService, $location, Principal){
    $scope.registeringUser = {};
    $scope.invalidForm = false;
    $scope.emailExists = false;

    if(Principal.isLogged()){
        $location.url("/");
    }

    $scope.register = function(form){

        if(form.$valid && form.$dirty){
            UserService.register($scope.registeringUser).then(function(response){
                console.log(response);
                if(response.status === 200){
                    $scope.login();
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

    $scope.login = function(){ // trebuie implementat server de e-mail pentru confirmare pe mail
        Principal.auth({
            username : $scope.registeringUser.email,
            password : $scope.registeringUser.password
        }).then(function(response){
            if(response.status === 200 ){
                Principal.checkCredentials().then(function(response){
                    $location.url("/profile");
                });
            }
        });
    }
});