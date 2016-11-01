module.exports = function(module) {
    
    module.controller('SendSurveyActionController', function($scope, $state, $stateParams, $uibModal){
    });
    
    return {
        name: '.SendSurvey',
        body: {
            templateUrl: require('./sendSurveyAction.html'),
            controller: 'SendSurveyActionController',
            icon: 'fa-check-square-o'
        }
    }
   
};