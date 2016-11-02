module.exports = function(module) {


    module.controller('NotificationSentConditionController', function($scope){
        $scope.selected.condition = '.NotificationSentCondition';
    });
    
    return {
        name: '.NotificationSentCondition',
        body: {
            templateUrl: require('./notificationSentCondition.html'),
            controller: 'NotificationSentConditionController',
            icon: 'fa-paper-plane-o'
        }
    }
   
};