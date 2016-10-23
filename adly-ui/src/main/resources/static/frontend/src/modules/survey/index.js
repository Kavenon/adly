require('angular-ui-router');
require('angular-resource');

var surveyTemplate = require('modules/survey/survey.html');
var surveyFormTemplate = require('modules/survey/surveyForm.html');

var module = angular.module('adlyApp.survey', [
  'ui.router',
  'ngResource'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.survey', {
            url: '/survey',
            templateUrl: surveyTemplate,
            controller: 'SurveyController'
        })
        .state('app.survey.upsert', {
            url: '/upsert/:surveyId',
            templateUrl: surveyFormTemplate,
            controller: 'SurveyFormController'
        })
});

module.controller('SurveyController', function ($scope, PredefinedProperty, UserSurvey, UserProperty) {

    $scope.userSurveys = UserSurvey.query();
    $scope.userProperties = UserProperty.query();

    $scope.deleteSurvey = function(item, $index) {
        item.$delete(function() {
            $scope.userSurveys.splice($index, 1);
        });
    };

});
module.controller('SurveyFormController', function ($scope, $stateParams, UserSurvey, $state) {

    if($stateParams.surveyId === ""){
        $scope.survey = new UserSurvey({fieldList: []});

        $scope.upsert = function() {
            $scope.survey.$save(function() {
                $state.go('app.survey', {}, { reload: true});
            });
        };
    }
    else {
        $scope.survey = UserSurvey.get({ id: $stateParams.surveyId });
        $scope.upsert = function() {
            $scope.survey.$update(function() {
                $state.go('app.survey', {}, { reload: true});
            });
        };
    }

    $scope.addField = function(){
        $scope.survey.fieldList.push({});
    };

    $scope.removeField = function($index){
        $scope.survey.fieldList.splice($index,1);
    };

});

module.factory('UserProperty', function($resource) {
    return $resource('/api/profile/user-property/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
});

module.factory('UserSurvey', function($resource) {
    return $resource('/api/profile/user-survey/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
});


