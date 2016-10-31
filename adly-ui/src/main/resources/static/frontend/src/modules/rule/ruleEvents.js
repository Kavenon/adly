
var eventTypeIcons = {
    '.BeaconDiscoverEvent' : 'fa-bluetooth-b'
};
 // todo: this could be hidden in translatons
var evenTypeNames = {
    '.BeaconDiscoverEvent' : 'Beacon discover'
};

var ruleEventsTemplate = require('modules/rule/ruleEvents.html');

var module = angular.module('adlyApp.rule.events',[]);

module.directive('adlyRuleEvents', function(){
    return {
        restrict: 'E',
        scope: {
            events: '=',
            control: '='
        },
        templateUrl: ruleEventsTemplate,
        link: function($scope){

            $scope.internalControl = $scope.control || {};
            $scope.internalControl.addEvent = function(){
                console.log('add eadfadvent');
            }

        },
        controller : function($scope){

            $scope.removeEvent = function(event, $index){
                $scope.events.splice($index,1);
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

