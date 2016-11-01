require('angular-ui-router');
require('angular-resource');

var ruleTemplate = require('modules/rule/rule.html');
var ruleFormTemplate = require('modules/rule/ruleForm.html');

require('modules/rule/event/ruleEvents.js')('app.rule.upsert');
require('modules/rule/action/ruleActions.js')('app.rule.upsert');

var module = angular.module('adlyApp.rule', [
    'ui.router',
    'ui.checkbox',
    'ngResource',
    'adlyApp.rule.events',
    'adlyApp.rule.actions'
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
            templateUrl: ruleFormTemplate,
            controller: 'RuleFormController'
        });
     
});

module.controller('RuleController', function ($scope, Rule) {

    $scope.rules = Rule.query();

    $scope.deleteRule = function(rule, $index) {
        rule.$delete(function() {
            $scope.rules.splice($index, 1);
        });
    };

    $scope.eventsControl = {};


});

module.controller('RuleFormController', function ($scope, $stateParams, Rule, $state) {

    if($stateParams.ruleId === "" || $stateParams.ruleId == 0){
        $scope.rule = new Rule({
            events: [{
                type: '.BeaconDiscoverEvent',
                config: {
                    beaconId: 1
                }
            }],
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

