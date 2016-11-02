module.exports = function(module) {
    
    module.controller('BeaconDiscoverEventController', function($scope, $state, $stateParams, $uibModal){
        $scope.selected.event = '.BeaconDiscoverEvent';

    });
    
    return {
        name: '.BeaconDiscoverEvent',
        body: {
            templateUrl: require('./beaconDiscoverEvent.html'),
            controller: 'BeaconDiscoverEventController',
            icon: 'fa-bluetooth-b'
        }
    }
   
};