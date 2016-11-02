module.exports = function(module) {
    
    module.controller('SendUrlNotificationController', function($scope, $state, $stateParams, $uibModal){
        $scope.selected.action = '.SendUrlNotificationAction';

    });
    
    return {
        name: '.SendUrlNotificationAction',
        body: {
            templateUrl: require('./sendUrlNotificationAction.html'),
            controller: 'SendUrlNotificationController',
            icon: 'fa-link'
        }
    }
   
};