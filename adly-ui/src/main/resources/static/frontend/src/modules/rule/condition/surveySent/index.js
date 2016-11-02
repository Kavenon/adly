module.exports = function(module) {

    require('components/dao/Survey.js')(module);

    module.controller('SurveySentConditionController', function($scope, AdlyDaoUserSurvey){
        $scope.selected.condition = '.SurveySentCondition';
        $scope.surveys = AdlyDaoUserSurvey.query();
    });
    
    return {
        name: '.SurveySentCondition',
        body: {
            templateUrl: require('./surveySentCondition.html'),
            controller: 'SurveySentConditionController',
            icon: 'fa-check-square-o'
        }
    }
   
};