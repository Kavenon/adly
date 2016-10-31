require('angular-ui-router');
require('angular-resource');

var ruleTemplate = require('modules/rule/rule.html');
var ruleFormTemplate = require('modules/rule/ruleForm.html');
var ruleEventFormTemplate = require('modules/rule/ruleEventForm.html');

var events = {};

var eventNames = [];
var registerEvent = function(event){
    "use strict";
    events[event.name] = event.body;
    eventNames.push(event.name);
};


require('modules/rule/ruleEvents.js');
var module = angular.module('adlyApp.rule', [
    'ui.router',
    'ui.checkbox',
    'ngResource',
    'adlyApp.rule.events'
]);

registerEvent(require('modules/rule/event/beaconDiscoverEvent')(module));

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
        })
        .state('app.rule.upsert.event', {
            url: '/event',
            templateUrl: ruleEventFormTemplate,
            controller: 'RuleEventListController'
        })
        .state('app.rule.upsert.event.type', {
            url: '/:eventType',
            templateUrl: function ($stateParams){
                return events[$stateParams.eventType].templateUrl;
            },
            controllerProvider: function($stateParams) {
                return events[$stateParams.eventType].controller;
            },
            params : {
                config: null
            },
            data: function($stateParams){
                console.log($stateParams);
                return {};
            }
        });
});


module.controller('RuleEventListController', function ($scope,$state) {

    $scope.selectedEvent = $state.params.eventType;
    $scope.events = events;

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

