require('angular-ui-router');
require('metrojs');

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

module.controller('DashboardController', DashboardController);

function DashboardController ($scope) {

}


