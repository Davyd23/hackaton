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
       .when('/', {
          templateUrl : 'scripts/main/main.html',
          controller : 'MainController',
          withLogin : false
       })
       .when('/login', {
          templateUrl : 'scripts/login/login.html',
          controller : 'LoginController',
           withLogin : false
       })
       .when('/register', {
          templateUrl : 'scripts/register/register.html',
          controller : 'RegisterController',
          withLogin : false
       })
       .when('/profile', {
          templateUrl : 'scripts/profile/profile.html',
          controller : 'ProfileController',
          withLogin : true
       })
       .when('/posting', {
          templateUrl : 'scripts/posting/posting.html',
          controller : 'PostingController',
          withLogin : false
       })
       .otherwise({
          redirectTo:'/'
       });

    $httpProvider.defaults.withCredentials = true;
    // $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';


    $httpProvider.interceptors.push('myHttpInterceptor');
    $httpProvider.interceptors.push('myCSRF');
});

app.run(['$http', '$cookies', 'Principal', '$rootScope', '$location', function($http, $cookies, Principal, $rootScope, $location) {
    $http.defaults.headers.post['X-CSRFToken'] = $cookies.csrftoken;

    $rootScope.$on('$routeChangeStart', function(event, current) {//TODO trebuie facuta o pagina de eroare si de implementat cu grad de acess
        // $rootScope.pageTitle = current.$$route.title;
        if ( current.$$route && current.$$route.withLogin ) { //current.$$route pt cazul in care e pus otherwise si nu exista when pe calea respectiva
            if(!Principal.isLogged() ){
                event.preventDefault();
                $location.path('/login');
            }
        }

        //if(current.$$route.withLogin)
    });
    }]
);