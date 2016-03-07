module.exports = function(config){
  config.set({

    basePath : './../../../',

    files : [
      'src/main/resources/static/components/angular.min.js',
      'src/main/resources/static/components/angular-route.js',
      'src/main/resources/static/components/angular-resource.js',
      'src/main/resources/static/**/*.js',

      'src/test/javascript/unit/**/*.js'
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
