require('angular-ui-router');

var dashboardTemplate = require('modules/dashboard/dashboard.html');

var module = angular.module('adlyApp.dashboard', [
  'ui.router'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.dashboard', {
            url: '/dashboard',
            templateUrl: dashboardTemplate,
            controller: 'BeaconController'
        })
});

module.controller('DashboardController', DashboardController);

function DashboardController ($scope) {

}


