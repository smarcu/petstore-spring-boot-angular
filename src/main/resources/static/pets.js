'use strict';

var appControllers = angular.module('myApp.pets', ['ngRoute'])


appControllers.controller('PetsCtrl', ['$scope', '$log', 'Pets', 
                                       function($scope, $log, Pets) {

	$scope.petStatuses = ['available', 'pending', 'sold'];
	$scope.pets;
	$scope.categories;
	$scope.selectedCategoryId;
	$scope.pet = {'name': 'test'};
	$scope.petTags = "tag1,tag2";
	
	function refreshPets() {
		Pets.getPets({}, function(data){
			$log.log("received pets: "+data);
			$scope.pets = data;
		});
	};
	refreshPets();

	function refreshCategories() {
		Pets.getCategories(function(data){
			$log.log("received categories: "+data);
			$scope.categories = data;
		});
	}
	refreshCategories();
	
	function tagsStringToList(tagStr) {
		var tagItems = tagStr.split(',');
		var tagObjects = [];
		for (var i=0; i<tagItems.length; i++) {
			if (tagItems[i]) {
				tagObjects.push({'name':tagItems[i]});
			}
		}
		return tagObjects;
	}
	
	function getCategory(id) {
		for(var i=0; i<$scope.categories.length; i++) {
			if (id == $scope.categories[i].id) {
				return $scope.categories[i];
			}
		}
	}
	
	$scope.addPet = function(pet) {
		
		$log.log("adding pet ... "+pet);

		pet.tags = tagsStringToList($scope.petTags);
		pet.category = getCategory($scope.selectedCategoryId)
		
		Pets.addPet($scope.pet, function(data) {
			$log.log("pet added "+data)
			refreshPets();
		}, function(error) {
			$log.log("error "+error);
		});
		
	}
}]);