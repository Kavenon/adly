

module.exports = function(module) {
    
    module.controller('BeaconDiscoverEventController', function($scope, $stateParams, $uibModal){


    });
    
    return {
        name: '.BeaconDiscoverEvent',
        body: {
            templateUrl: require('./beaconDiscoverEvent.html'),
            controller: 'BeaconDiscoverEventController'
        }
    }
   
};