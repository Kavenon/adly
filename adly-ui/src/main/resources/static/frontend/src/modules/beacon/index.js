require('angular-ui-router');
require('angular-resource');

var beaconTemplate = require('modules/beacon/beacon.html');
var beaconFormTemplate = require('modules/beacon/beaconForm.html');


var module = angular.module('adlyApp.beacon', [
  'ui.router',
    'ngResource'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.beacon', {
            url: '/beacon',
            templateUrl: beaconTemplate,
            controller: 'BeaconController'
        })
        .state('app.beacon.upsert', {
            url: '/upsert/:beaconId',
            templateUrl: beaconFormTemplate,
            controller: 'BeaconFormController'
        })
});

module.controller('BeaconController', function ($scope, Beacon) {

    $scope.beacons = Beacon.query();

    $scope.deleteBeacon = function(beacon, $index) {
        beacon.$delete(function() {
            $scope.beacons.splice($index, 1);
        });
    };


});

module.controller('BeaconFormController', function ($scope, $stateParams, Beacon, $state) {

    if($stateParams.beaconId === ""){
        $scope.beacon = new Beacon();
        $scope.upsert = function() {
            $scope.beacon.$save(function() {
                $state.go('app.beacon', {}, { reload: true});
            });
        };
    }
    else {
        $scope.beacon = Beacon.get({ id: $stateParams.beaconId });
        $scope.upsert = function() {
            $scope.beacon.$update(function() {
                $state.go('app.beacon', {}, { reload: true});
            });
        };
    }

});

module.factory('Beacon', function($resource) {
    return $resource('/api/beacon/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
});

