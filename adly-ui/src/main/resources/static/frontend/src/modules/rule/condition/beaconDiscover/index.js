module.exports = function(module) {

    require('components/dao/Beacon.js')(module);

    module.controller('BeaconDiscoverConditionController', function($scope, AdlyDaoBeacon){
        $scope.selected.condition = '.BeaconDiscoverCondition';
        $scope.beacons = AdlyDaoBeacon.query({id: 'all'});
    });
    
    return {
        name: '.BeaconDiscoverCondition',
        body: {
            templateUrl: require('./beaconDiscoverCondition.html'),
            controller: 'BeaconDiscoverConditionController',
            icon: 'fa-bluetooth-b'
        }
    }
   
};