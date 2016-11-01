
var eventTypeIcons = {
    '.BeaconDiscoverEvent' : 'fa-bluetooth-b'
};
 // todo: this could be hidden in translatons
var evenTypeNames = {
    '.BeaconDiscoverEvent' : 'Beacon discover'
};

var ruleEventsTemplate = require('modules/rule/ruleEvents.html');

module.exports = function(parentState){
    "use strict";

    var ruleEventFormTemplate = require('modules/rule/ruleEventForm.html');

    var events = {};

    var eventNames = [];
    var registerEvent = function(event){
        "use strict";
        events[event.name] = event.body;
        eventNames.push(event.name);
    };


    var module = angular.module('adlyApp.rule.events',[
        'ui.router',
        'ui.bootstrap.modal'
    ]);

    registerEvent(require('modules/rule/event/beaconDiscoverEvent')(module));


    module.config(function($stateProvider) {
        $stateProvider
            .state(parentState+'.event', {
                url: '/event',
                templateUrl: ruleEventFormTemplate,
                controller: 'RuleEventListController'
            })
            .state(parentState+'.event.type', {
                url: '/:eventType',
                templateUrl: function ($stateParams){
                    return events[$stateParams.eventType].templateUrl;
                },
                controllerProvider: function($stateParams) {
                    return events[$stateParams.eventType].controller;
                },
                params : {
                    event: null
                }
            });
    });


    module.controller('RuleEventListController', function ($scope,$state, $uibModal) {

        $scope.selectedEvent = $state.params.eventType;
        $scope.events = events;


        $scope.back = function(){
            $state.go(parentState);
        }
    });


    module.directive('adlyRuleEvents', function(){
        return {
            restrict: 'E',
            templateUrl: ruleEventsTemplate,
            scope: {
                data: '='
            },
            controller : function($scope){

                $scope.removeEvent = function(event, $index){
                    $scope.data.events.splice($index,1);
                }

            }
        };
    });

    module.filter('eventIcon', function(){
        return function(eventType) {
            return eventTypeIcons[eventType] || '';
        };
    });

    module.filter('eventName', function(){
        return function(eventType) {
            return evenTypeNames[eventType] || '';
        };
    });


}
