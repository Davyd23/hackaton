var app = angular.module("hackatonApp",["ngRoute", "ngCookies"]);

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
                if($location.url()!=='/login') { // in caz ca login-ul a esuat
                    $location.url("/login");
                }else{
                    return rejection;
                }
            }else {
                return $q.reject(rejection);
            }

        }
    };
});
app.provider('myCSRF',[function(){
    var headerName = 'X-CSRFToken';
    var cookieName = 'csrftoken';
    var allowedMethods = ['GET'];

    this.setHeaderName = function(n) {
        headerName = n;
    }
    this.setCookieName = function(n) {
        cookieName = n;
    }
    this.setAllowedMethods = function(n) {
        allowedMethods = n;
    }
    this.$get = ['$cookies', function($cookies){
        return {
            'request': function(config) {
                if(allowedMethods.indexOf(config.method) === -1) {
                    // do something on success
                    config.headers[headerName] = $cookies[cookieName];
                }
                return config;
            }
        }
    }];
}]);


app.config(function($routeProvider, $httpProvider) {
   $routeProvider
       /*.when('/test', {
          templateUrl : 'scripts/main/main.html',
          controller : 'MainController'
       })*/
       .when('/login', {
          templateUrl : 'scripts/login/login.html',
          controller : 'LoginController'
       }).when('/register', {
          templateUrl : 'scripts/register/register.html',
          controller : 'RegisterController'
       })
       .otherwise({
          redirectTo:'/',
          controller : "MainController",
          templateUrl : "scripts/main/main.html"
       });

    $httpProvider.defaults.withCredentials = true;
    // $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';


    $httpProvider.interceptors.push('myHttpInterceptor');
    $httpProvider.interceptors.push('myCSRF');
});

app.run(['$http', '$cookies', function($http, $cookies) {
        $http.defaults.headers.post['X-CSRFToken'] = $cookies.csrftoken;
    }]
);
