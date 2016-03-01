var petServices = angular.module('petServices', ['ngResource']);

petServices.factory('Pets', ['$resource', '$log', function($resource, $log){
	
	return {
		getPets: function(status, callback) {
     		$resource('/pet/findByStatus?status=:status', {status:status}).
     			query(
					function(results) {
						callback(results);
					}
 				);
		},
		getPet: function(petId, callback, error) {
     		$resource('/pet/:petId', {petId:petId}).
     			get(
					function(result) { callback(result); },
					function(result) { error(result); }
 				);
		},
		addPet: function(pet, callback) {
			var rest = $resource('/pet', null, null,
				{
					'update': { method:'POST' }
				});
			rest.save(pet, function(results) {
				callback(results);
			});
		},
		deletePet: function(petId, callback) {
			var rest = $resource('/pet/:petId', {petId:petId}, null,
				{
					'delete': { method:'DELETE' }
				});
			rest.delete(petId, function(results) {
				callback(results);
			});
		},
		
		getCategories: function(callback) {
     		$resource('/category/all', {}).
     			query(
					function(results) {
						callback(results);
					}
 				);
		},
		
	}
	
}]);
