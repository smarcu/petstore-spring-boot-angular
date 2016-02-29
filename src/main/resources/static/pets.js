'use strict';

var appControllers = angular.module('myApp.pets', ['ngRoute'])


appControllers.controller('PetsCtrl', ['$scope', '$log', 'Pets', 
                                       function($scope, $log, Pets) {

	$scope.petStatuses = ['available', 'pending', 'sold'];
	
	$scope.pets;
	
	$scope.refreshPets = function() {
		Pets.getPets({}, function(data){
			$log.log("received pets: "+data);
			$scope.pets = data;
		});
	};
	$scope.refreshPets();
	
	
	$scope.pet = {'name': 'test'};
	$scope.petTags = "tag1,tag2";
	
	$scope.tagsStringToList = function(tagStr) {
		var tagItems = tagStr.split(',');
		var tagObjects = [];
		for (var i=0; i<tagItems.length; i++) {
			if (tagItems[i]) {
				tagObjects.push({'name':tagItems[i]});
			}
		}
		return tagObjects;
	}
	
	$scope.addPet = function(pet) {
		
		$log.log("adding pet ... "+pet);

		pet.tags = $scope.tagsStringToList($scope.petTags);
		
		Pets.addPet($scope.pet, function(data) {
			$log.log("pet added "+data)
			$scope.refreshPets();
		}, function(error) {
			$log.log("error "+error);
		});
		
	}
}]);