module.exports = function(module) {
    
    module.controller('SendUrlNotificationController', function($scope, $state, $stateParams, $uibModal){
        $scope.selected.action = '.SendUrlNotification';

    });
    
    return {
        name: '.SendUrlNotification',
        body: {
            templateUrl: require('./sendUrlNotificationAction.html'),
            controller: 'SendUrlNotificationController',
            icon: 'fa-link'
        }
    }
   
};