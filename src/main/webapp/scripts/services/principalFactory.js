app.factory('Principal', function( $http, $rootScope){

    var principal = {};

    return{
        auth : function(credentials){
            return $http({
                method: 'POST',
                url: "/login",
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: credentials
            }).then(function(response){
                return response;
            });
        },
        checkCredentials : function() {
            return $http.get('user').then(function(response){
                if(response.data !== '' ){ // user-ul e logat
                    principal = response.data.principal;
                    $rootScope.$broadcast("principalSet");
                }
                return response;
            });
        },
        logout : function(){
            $http.get("/logout").then(function(response){

            });
            principal = {};
        },
        isLogged : function(){
            if(principal && principal.username !== null && principal.username!== undefined){
                return true;
            }
            return false;
        },
        getAuthorities : function(){
            if(principal.authorities !== null && principal.authorities!== undefined){
                return principal.authorities;
            }
            return null;
        },
        getPrincipal : function(){
            return principal;
        }

    }
});