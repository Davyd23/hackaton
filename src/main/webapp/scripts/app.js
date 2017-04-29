var app = angular.module("hackatonApp",["ngRoute"]);

// register the interceptor as a service
app.factory('myHttpInterceptor', function($q, $location) {
    return {
        // optional method
        'response': function(response) {
            if(response.status === 401){
                $location.path("/login");
            }else{
                return response;
            }

        },

        // optional method
        'responseError': function(rejection) {
            if(rejection.status === 401){
                $location.path("/login");
            }else {
                return $q.reject(rejection);
            }

        }
    };
});



app.config(function($routeProvider, $httpProvider) {
   $routeProvider
       /*.when('/test', {
          templateUrl : 'scripts/main/main.html',
          controller : 'MainController'
       })*/
       .when('/login', {
          templateUrl : 'scripts/login/login.html',
          controller : 'LoginController'
       })
       .otherwise({
          redirectTo:'/',
          controller : "MainController",
          templateUrl : "scripts/main/main.html"
       });
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    $httpProvider.interceptors.push('myHttpInterceptor');
});

