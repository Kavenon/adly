module.exports = function(module) {

    require('components/dao/Survey.js')(module);

    module.controller('SendSurveyActionController', function($scope, AdlyDaoUserSurvey){
        $scope.selected.action = '.SendSurvey';
        $scope.surveys = AdlyDaoUserSurvey.query();
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