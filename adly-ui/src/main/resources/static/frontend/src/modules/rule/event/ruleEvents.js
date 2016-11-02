module.exports = function(parentState){
    "use strict";

    var ruleEventsListTemplate = require('modules/rule/event/ruleEventsList.html');
    var ruleEventsModalTemplate = require('modules/rule/event/ruleEventsModal.html');
    var registeredEvents = {};

    var registerEvent = function(event){
        registeredEvents[event.name] = event.body;
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
                templateUrl: ruleEventsModalTemplate,
                controller: 'RuleEventListController'
            })
            .state(parentState+'.event.type', {
                url: '/:eventType',
                templateUrl: function ($stateParams){
                    return registeredEvents[$stateParams.eventType].templateUrl;
                },
                controllerProvider: function($stateParams) {
                    return registeredEvents[$stateParams.eventType].controller;
                },
                params : {
                    index: null
                }
            });
    });


    module.controller('RuleEventListController', function ($scope,$state) {

        var isEditingEvent = function () {
            return $state.params.index != null;
        };

        var getEditingEventObject = function () {
            if (isEditingEvent()) {
                return angular.copy($scope.rule.events[$state.params.index]);
            }
            else {
                return {};
            }
        };

        $scope.selected = $scope.selected || {};
        $scope.selected.event = $state.params.eventType;

        $scope.events = registeredEvents;
        $scope.editingEvent = getEditingEventObject();

        $scope.save = function(){

            if(isEditingEvent()){
                $scope.rule.events[$state.params.index] = $scope.editingEvent;
            }
            else {
                $scope.rule.events.push({
                    type: $state.params.eventType,
                    config: $scope.editingEvent.config
                })
            }
            $state.go(parentState);

        };

        $scope.back = function(){
            $state.go(parentState);
        }

    });


    module.directive('adlyRuleEvents', function(){
        return {
            restrict: 'E',
            templateUrl: ruleEventsListTemplate,
            scope: {
                data: '='
            },
            controller : function($scope){

                $scope.getEvent = function(eventType){
                    return registeredEvents[eventType];
                };
                
                $scope.removeEvent = function(event, $index){
                    $scope.data.events.splice($index,1);
                }

            }
        };
    });

};
