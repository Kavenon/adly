module.exports = function(module) {
    
    require('components/dao/Beacon.js')(module);
    
    module.controller('BeaconDiscoverEventController', function($scope, AdlyDaoBeacon){
        $scope.selected.event = '.BeaconDiscoverEvent';
        $scope.beacons = AdlyDaoBeacon.query({id:'all'});
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