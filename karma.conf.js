module.exports = function(config){
  config.set({

    basePath : './',

    files : [
      'src/main/resources/static/components/angular.js',
      'src/main/resources/static/components/angular-route.js',
      'src/main/resources/static/components/angular-mocks.js',
      'src/main/resources/static/components/**/*.js',
      'src/main/resources/static/components/view*/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};