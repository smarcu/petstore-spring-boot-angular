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
	  

	  it('should add pet', function() {
		    browser.get('');

		    element(by.id('addPetButton')).click();

		    expect(element(by.id('addForm')).isDisplayed()).toBeTruthy();
		    
		    var generatedName = 'test'+Date.now();
		    var imgUrl = 'testUrl';
		    var inputTags = 'tag1,tag2';
		    
		    element(by.model('pet.name')).clear().sendKeys(generatedName);
		    element(by.model('petImgUrl')).clear().sendKeys(imgUrl);
		    element(by.model('petTags')).clear().sendKeys(inputTags);
		    element(by.cssContainingText('option', 'pending')).click();
		    
		    element(by.buttonText('Add Pet')).click();
		    
		    browser.waitForAngular();
		    
		    // search for the new pet
		    element.all(by.repeater('pet in pets')).then(function(pets) {
			    var titleElement = pets[pets.length-1].element(by.className('petName'));
			    expect(titleElement.getText()).toEqual(generatedName);
			    var deleteButton = pets[pets.length-1].element(by.className('deleteButton'));
			    
			    deleteButton.click();

		    });
		    
		  });

});