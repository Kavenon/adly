require('angular-ui-router');

var module = angular.module('adlyApp.dashboard', [
  'ui.router'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.dashboard', {
            url: '/dashboard',
            template: require('modules/dashboard/dashboard.html'),
            controller: 'DashboardController'
        })
});

module.controller('DashboardController', DashboardController);

function DashboardController ($scope) {

}


