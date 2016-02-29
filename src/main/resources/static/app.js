'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.pets',
  'petServices'
]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/pets', {
			templateUrl: 'pets.html',
			controller: 'PetsCtrl'
		}).
  otherwise({redirectTo: '/pets'});
}]);