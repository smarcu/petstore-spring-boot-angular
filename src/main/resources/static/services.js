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
		
		addPet: function(pet, callback) {
			var rest = $resource('/pet', null, null,
				{
					'update': { method:'POST' }
				});
			rest.save(pet, function(results) {
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
