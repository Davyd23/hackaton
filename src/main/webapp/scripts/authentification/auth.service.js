app.service( 'AuthService', function($http) {
    var currentUser;

    return {
        login: function(loginCredentials) {
            return $http.post("/hackaton/login",loginCredentials).then(function(response){
                currentUser = response.data;
                return currentUser;
            },function(err){
                currentUser = null;
                return currentUser;
            });
        },
        logout: function() {

        },
        isLoggedIn: function() {
            if(currentUser!= undefined || currentUser !== null){
                return false;
            }
            return true;
        },
        currentUser: function() {
            return currentUser;
        }

    };
});
