require('angular-ui-router');
require('angular-resource');

var ruleTemplate = require('modules/rule/rule.html');
var ruleFormTemplate = require('modules/rule/ruleForm.html');


var module = angular.module('adlyApp.rule', [
    'ui.router',
    'ui.checkbox',
    'ngResource'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.rule', {
            url: '/rule',
            templateUrl: ruleTemplate,
            controller: 'RuleController'
        })
        .state('app.rule.upsert', {
            url: '/upsert/:ruleId',
            views:{
                "@app":{
                    templateUrl: ruleFormTemplate,
                    controller: 'RuleFormController'
                }
            }
        });
});

module.controller('RuleController', function ($scope, Rule) {

    $scope.rules = Rule.query();

    $scope.deleteRule = function(rule, $index) {
        rule.$delete(function() {
            $scope.rules.splice($index, 1);
        });
    };


});

module.controller('RuleFormController', function ($scope, $stateParams, Rule, $state) {

    if($stateParams.ruleId === ""){
        $scope.rule = new Rule({
            events: [],
            conditions: [],
            actions: []
        });
        $scope.upsert = function() {
            $scope.rule.$save(function() {
                $state.go('app.rule', {}, { reload: true});
            });
        };
    }
    else {
        $scope.rule = Rule.get({ id: $stateParams.ruleId });
        $scope.upsert = function() {
            $scope.rule.$update(function() {
                $state.go('app.rule', {}, { reload: true});
            });
        };
    }

});

module.factory('Rule', function($resource) {
    return $resource('/api/rule/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
});

