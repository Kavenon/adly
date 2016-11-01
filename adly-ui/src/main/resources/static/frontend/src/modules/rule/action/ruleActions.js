module.exports = function(parentState){
    "use strict";

    var ruleActionsListTemplate = require('modules/rule/action/ruleActionsList.html');
    var ruleActionsModalTemplate = require('modules/rule/action/ruleActionsModal.html');
    var registeredActions = {};

    var registerAction = function(action){
        registeredActions[action.name] = action.body;
    };

    var module = angular.module('adlyApp.rule.actions',[
        'ui.router',
        'ui.bootstrap.modal'
    ]);

    registerAction(require('modules/rule/action/sendPlainPush')(module));

    module.config(function($stateProvider) {
        $stateProvider
            .state(parentState+'.action', {
                url: '/action',
                templateUrl: ruleActionsModalTemplate,
                controller: 'RuleActionListController'
            })
            .state(parentState+'.action.type', {
                url: '/:actionType',
                templateUrl: function ($stateParams){
                    return registeredActions[$stateParams.actionType].templateUrl;
                },
                controllerProvider: function($stateParams) {
                    return registeredActions[$stateParams.actionType].controller;
                },
                params : {
                    index: null
                }
            });
    });


    module.controller('RuleActionListController', function ($scope,$state) {

        var isEditingAction = function () {
            return $state.params.index != null;
        };

        var getEditingActionObject = function () {
            if (isEditingAction()) {
                return angular.copy($scope.rule.actions[$state.params.index]);
            }
            else {
                return {};
            }
        };

        $scope.selectedAction = $state.params.actionType;
        $scope.actions = registeredActions;
        $scope.editingAction = getEditingActionObject();

        $scope.save = function(){

            if(isEditingAction()){
                $scope.rule.actions[$state.params.index] = $scope.editingAction;
            }
            else {
                $scope.rule.actions.push({
                    type: $state.params.actionType,
                    config: $scope.editingAction.config
                })
            }
            $state.go(parentState);

        };

        $scope.back = function(){
            $state.go(parentState);
        }

    });


    module.directive('adlyRuleActions', function(){
        return {
            restrict: 'E',
            templateUrl: ruleActionsListTemplate,
            scope: {
                data: '='
            },
            controller : function($scope){

                $scope.getAction = function(actionType){
                    return registeredActions[actionType];
                };
                
                $scope.removeAction = function(action, $index){
                    $scope.data.actions.splice($index,1);
                }

            }
        };
    });

};
