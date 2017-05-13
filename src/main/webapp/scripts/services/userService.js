app.service('UserService', function($http){
    return {
        register : function(user){
            return $http.post('/user/register', user).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        },
        saveProfile : function(profile){
            return $http.post('/user/profile', profile).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        },
        getProfile : function(){
            return $http.get('/user/profile').then(function(response){
                return response;
            }, function(err){
                return err;
            })
        }
    }
})