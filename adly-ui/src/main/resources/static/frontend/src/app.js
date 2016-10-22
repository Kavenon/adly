'use strict';

require('./app.scss');
require('angular');
require('parsleyjs');


var adlyApp = angular.module('adlyApp', [
    'adlyApp.core',
    'adlyApp.dashboard',
    'adlyApp.beacon'
]);

require('modules/core');
require('modules/dashboard');
require('modules/beacon');

adlyApp.directive('validateForm', function() {
    return function(scope, elm, attr) {
        var $elem = $(elm);
        if($.fn.parsley){
            $elem.parsley().subscribe('parsley:form:validate', function (formInstance) {

                scope[attr.modelvalue]=formInstance.isValid();
                scope.$apply()
            })
        }
    };
});


