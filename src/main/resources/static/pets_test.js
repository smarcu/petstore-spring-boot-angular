'use strict';

describe('myApp.pets module', function() {

	var scope;
	var PetsServiceMock;
	var PetsController;

	beforeEach(module('myApp.pets'));

	beforeEach( function() {
		PetsServiceMock = {
			getPets: function(status, callback) {
				callback( [{id:1, name:'cat'}] );
			},
			getCategories: function(callback) {
				callback( [{id:1, name:'cat1'}, {id:2, name:'cat2'}] );
			}
		}
	});
  
	beforeEach(inject(function($rootScope, $controller, $log){
		scope = $rootScope.$new();
		PetsController = $controller('PetsCtrl', {$scope:scope, $log:$log, Pets:PetsServiceMock});
	}));

	describe('pets controller', function(){
		it('should ....', function() {
			expect(PetsController).toBeDefined();
			
			expect(scope.petTags).toBe("tag1,tag2");
			
			expect(scope.categories).toEqual([{id:1, name:'cat1'}, {id:2, name:'cat2'}]);
			
			expect(scope.pets).toEqual( [{id:1, name:'cat'}] );
		});
	});

});