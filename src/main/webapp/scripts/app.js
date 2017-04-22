var app = angular.module("hackatonApp",["ngRoute"]);

app.config(function($routeProvider) {
   $routeProvider
       .when('/login', {
          templateUrl : 'scripts/login/login.html',
           controller : 'LoginController'
       })
       .otherwise({
          redirectTo:'/',
          controller : "MainController",
          templateUrl : "scripts/main/main.html"
       });
});

