'use strict';

describe('myApp.pets module', function() {

  beforeEach(module('myApp.pets'));
  
  var $controller;
  
  beforeEach(inject(function(_$controller_){
	  $controller = _$controller_;
  }));

  describe('pets controller', function(){

    it('should ....', function() {
	  var $scope = {};
	  var $log = {};
	  var Pets = {
		getPets() {
			return [{id:1, name:'cat'}];
		},
		getCategories() {
			return [{id:1, name:'cat1'}, {id:2, name:'cat2'}]
		}
	  };
	  var petsCtrl = $controller('PetsCtrl', {$scope:$scope, $log:$log, Pets:Pets});
	  expect(petsCtrl).toBeDefined();
	  
	  expect($scope.petTags).toBe("tag1,tag2");

      
    });

  });
});