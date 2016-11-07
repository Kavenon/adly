require('angular-ui-router');
require('metrojs');
require('flot');
require('components/flot/flot.time.js')($.plot);

var dashboardTemplate = require('modules/dashboard/dashboard.html');

var module = angular.module('adlyApp.dashboard', [
  'ui.router'
]);

require('components/tile')(module);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.dashboard', {
            url: '/dashboard',
            templateUrl: dashboardTemplate,
            controller: 'DashboardController'
        })
});

module.controller('DashboardController', function ($scope) {

    $scope.data1 = [
        [1, 20000],
        [2, 20000],
        [3, 40000],
        [4, 30000],
        [5, 40000],
        [6, 35000],
        [7, 47000]
    ];
});

module.directive('flotChart', function(){
    return {
        link: function (scope, $el, attrs) {
            $.plot($el, scope[attrs.ngModel], {
                xaxis: {
                    tickLength: 0,
                    tickDecimals: 0,
                    min: 2,
                    mode: "time",
                    timeformat: "%Y-%d-%m",
                    font: {
                        lineHeight: 13,
                        weight: "bold",
                        color: scope.app.settings.colors['gray-semi-light']
                    }
                },
                yaxis: {
                    tickDecimals: 0,
                    tickColor: "#f3f3f3",
                    font: {
                        lineHeight: 13,
                        weight: "bold",
                        color: scope.app.settings.colors['gray-semi-light']
                    }
                },
                grid: {
                    backgroundColor: {colors: ["#fff", "#fff"]},
                    borderWidth: 1,
                    borderColor: "#f0f0f0",
                    margin: 0,
                    minBorderMargin: 0,
                    labelMargin: 20,
                    hoverable: true,
                    clickable: true,
                    mouseActiveRadius: 6
                },
                legend: false
            });
        }
    }
});


