module.exports = function(module) {
    
    module.controller('SendPlainPushActionController', function($scope, $state, $stateParams, $uibModal){
        $scope.selected.action = '.SendPlainPushAction';

    });
    
    return {
        name: '.SendPlainPushAction',
        body: {
            templateUrl: require('./sendPlainPushAction.html'),
            controller: 'SendPlainPushActionController',
            icon: 'fa-comment'
        }
    }
   
};