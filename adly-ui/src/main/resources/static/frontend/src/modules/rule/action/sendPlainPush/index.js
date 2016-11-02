module.exports = function(module) {
    
    module.controller('SendPlainPushActionController', function($scope, $state, $stateParams, $uibModal){
        $scope.selected.action = '.SendPlainPush';

    });
    
    return {
        name: '.SendPlainPush',
        body: {
            templateUrl: require('./sendPlainPushAction.html'),
            controller: 'SendPlainPushActionController',
            icon: 'fa-comment'
        }
    }
   
};