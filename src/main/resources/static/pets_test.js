'use strict';

describe('myApp.pets module', function() {

  beforeEach(module('myApp.pets'));

  describe('pets controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var view1Ctrl = $controller('PetsCtrl');
      expect(view1Ctrl).toBeDefined();
    }));

  });
});