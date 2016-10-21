'use strict';

require('angular');
// require("!!bootstrap-webpack!./bootstrap.config.js");

var adlyApp = angular.module('adlyApp', [
    'adlyApp.core',
    'adlyApp.dashboard'
]);

require('modules/core');
require('modules/dashboard');



