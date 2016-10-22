'use strict';

require('angular');

var adlyApp = angular.module('adlyApp', [
    'adlyApp.core',
    'adlyApp.dashboard',
    'adlyApp.beacon'
]);

require('modules/core');
require('modules/dashboard');
require('modules/beacon');



