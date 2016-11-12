'use strict';

require('./app.scss');
require('angular');
require('parsleyjs');
require('angular-bootstrap-checkbox');
require('bootstrap-webpack');

var adlyApp = angular.module('adlyApp', [
    'adlyApp.core',
    'adlyApp.login',
    'adlyApp.dashboard',
    'adlyApp.beacon',
    'adlyApp.userProfile',
    'adlyApp.survey',
    'adlyApp.rule'
]);

require('modules/core');
require('modules/login');
require('modules/dashboard');
require('modules/beacon');
require('modules/user-profile');
require('modules/survey');
require('modules/rule');

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

adlyApp.directive('convertToNumber', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function(val) {
                return val != null ? parseInt(val, 10) : null;
            });
            ngModel.$formatters.push(function(val) {
                return val != null ? '' + val : null;
            });
        }
    };
});
