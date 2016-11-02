module.exports = function(parentState){
    "use strict";

    var ruleConditionsListTemplate = require('modules/rule/condition/ruleConditionsList.html');
    var ruleConditionsModalTemplate = require('modules/rule/condition/ruleConditionsModal.html');
    var registeredConditions = {};

    var registerCondition = function(condition){
        registeredConditions[condition.name] = condition.body;
    };

    var module = angular.module('adlyApp.rule.conditions',[
        'ui.router',
        'ui.bootstrap.modal'
    ]);

    registerCondition(require('modules/rule/condition/notificationSent')(module));
    registerCondition(require('modules/rule/condition/beaconDiscover')(module));
    registerCondition(require('modules/rule/condition/surveySent')(module));
    registerCondition(require('modules/rule/condition/userProfile')(module));

    module.config(function($stateProvider) {
        $stateProvider
            .state(parentState+'.condition', {
                url: '/condition',
                templateUrl: ruleConditionsModalTemplate,
                controller: 'RuleConditionListController'
            })
            .state(parentState+'.condition.type', {
                url: '/:conditionType',
                templateUrl: function ($stateParams){
                    return registeredConditions[$stateParams.conditionType].templateUrl;
                },
                controllerProvider: function($stateParams) {
                    return registeredConditions[$stateParams.conditionType].controller;
                },
                params : {
                    index: null
                }
            });
    });


    module.controller('RuleConditionListController', function ($scope,$state) {

        var isEditingCondition = function () {
            return $state.params.index != null;
        };

        var getEditingConditionObject = function () {
            if (isEditingCondition()) {
                return angular.copy($scope.rule.conditions[$state.params.index]);
            }
            else {
                return {config:{}};
            }
        };

        $scope.selected = $scope.selected || {};
        $scope.selected.condition = $state.params.conditionType;

        $scope.conditions = registeredConditions;
        $scope.editingCondition = getEditingConditionObject();
        $scope.saveCondition = function(){

            if(isEditingCondition()){
                $scope.rule.conditions[$state.params.index] = $scope.editingCondition;
            }
            else {
                $scope.rule.conditions.push({
                    type: $state.params.conditionType,
                    config: $scope.editingCondition.config
                })
            }
            $state.go(parentState);

        };

        $scope.back = function(){
            $state.go(parentState);
        }

    });


    module.directive('adlyRuleConditions', function(){
        return {
            restrict: 'E',
            templateUrl: ruleConditionsListTemplate,
            scope: {
                data: '='
            },
            controller : function($scope){

                $scope.getCondition = function(conditionType){
                    return registeredConditions[conditionType];
                };
                
                $scope.removeCondition = function(condition, $index){
                    $scope.data.conditions.splice($index,1);
                }

            }
        };
    });

};
