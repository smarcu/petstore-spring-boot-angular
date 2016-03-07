# petstore-spring-boot-angular
A simple petstore application using spring boot and angularJS


##Unit tests on java code: using junit
	mvn test
 
##Unit tests on angularJs: using karma
	karma start

###End to end tests on angularJs: using protractor
	// Start the app
	mvn spring-boot:run

	// To start the webdriver manager
	webdriver-manager start 

	// Run the protractor test
	protractor src\test\javascript\protractor-conf.js
