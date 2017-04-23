var app = angular.module("hackatonApp",["ngRoute",  'ui.bootstrap']);

app.config(function($routeProvider) {
   $routeProvider
       .when('/login', {
          templateUrl : 'scripts/login/login.html',
           controller : 'LoginController',
           withLogin : false
       })
       .when('/restrict',{
           controller : "MainController",
           templateUrl : "scripts/main/main.html",
           withLogin : true
       })
       .otherwise({
          redirectTo:'/',
          controller : "MainController",
          templateUrl : "scripts/main/main.html",
           withLogin : false
       });
});


app.run(function($rootScope, AuthService, $location){
    $rootScope.$on('$routeChangeStart', function(event, current) {
        // $rootScope.pageTitle = current.$$route.title;
        if (!AuthService.isLoggedIn() && current.$$route.withLogin ) {
            event.preventDefault();
            $location.path('/login');
        }
    });
});
