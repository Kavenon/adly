module.exports = function(module) {
    
    module.controller('SendPlainPushActionController', function($scope, $state, $stateParams, $uibModal){


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