

module.exports = function(module) {
    
    module.controller('BeaconDiscoverEventController', function($scope, $stateParams){
        $scope.config = $stateParams.config;
    });
    
    return {
        name: '.BeaconDiscoverEvent',
        body: {
            templateUrl: require('./beaconDiscoverEvent.html'),
            controller: 'BeaconDiscoverEventController'
        }
    }
   
};