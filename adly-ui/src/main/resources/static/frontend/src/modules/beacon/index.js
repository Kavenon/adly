require('angular-ui-router');

var beaconController = require('modules/beacon/beacon.html');

var module = angular.module('adlyApp.beacon', [
  'ui.router'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.beacon', {
            url: '/beacon',
            templateUrl: beaconController,
            controller: 'BeaconController'
        })
});

module.controller('BeaconController', function ($scope) {

});




