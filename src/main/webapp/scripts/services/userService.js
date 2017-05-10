app.service('UserService', function($http){
    return {
        register : function(user){
            return $http.post('/app/register', user).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        }
    }
})