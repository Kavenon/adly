module.exports = function(module) {

    require('components/dao/Survey.js')(module);

    module.controller('SendSurveyActionController', function($scope, AdlyDaoUserSurvey){
        $scope.selected.action = '.SendSurveyAction';
        $scope.surveys = AdlyDaoUserSurvey.query();
    });
    
    return {
        name: '.SendSurveyAction',
        body: {
            templateUrl: require('./sendSurveyAction.html'),
            controller: 'SendSurveyActionController',
            icon: 'fa-check-square-o'
        }
    }
   
};