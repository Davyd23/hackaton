var app = angular.module("hackatonApp",["ngRoute"]);

app.config(function($routeProvider) {
   $routeProvider
       .when('/test', {
          templateUrl : 'scripts/main/main.html', //o sa fie sterse, doar de test
          controller : 'TestController'
       })
       .otherwise({
          redirectTo:'/',
          controller : "MainController",
          templateUrl : "scripts/main/main.html"
       });
});

