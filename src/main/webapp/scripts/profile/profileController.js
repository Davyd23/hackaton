app.controller('ProfileController', function($scope, UserService, Principal){
    $scope.user = {};
    $scope.user.fullName = "David Dedu";
    $scope.workExperienceOptions = ['0+','1+','2+','3+','4+','5+','6+','7+','8+','9+','10+'];

    $scope.profile = {};
    console.log(Principal.getPrincipal());

    $scope.save = function(){
        UserService.saveProfile($scope.profile).then(function(response){
            console.log(response);
        })
    };

    UserService.getProfile().then(function(response){
        console.log(response);
        if(response.status === 200){//alt status inseamna ca nu si-a setat inca profilul
            $scope.profile = response.data;
       }
    });

    $scope.checkWorkExperienceSelected = function(workExperience){
        return $scope.profile.workExperience && $scope.profile.workExperience.indexOf(workExperience) === 0;
    };
});