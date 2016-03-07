'use strict';

/* https://github.com/angular/protractor/blob/master/docs/toc.md */

describe('Protractor Demo App', function() {
	  it('should have a title', function() {
	    browser.get('');

	    expect(browser.getTitle()).toEqual('Petstore');

	  });
	  
	  it('should toggle add pet form', function() {
		    browser.get('');

		    expect(element(by.id('addForm')).isDisplayed()).toBeFalsy();
		    
		    element(by.id('addPetButton')).click();

		    expect(element(by.id('addForm')).isDisplayed()).toBeTruthy();
		  });
	  
	  
});