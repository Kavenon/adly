'use strict';

require('angular-ui-router');
require('angular-ui-bootstrap');
require('angular-animate');
require('ngstorage');


var coreTemplate = require('modules/core/core.html');
var core = angular.module('adlyApp.core', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'ngStorage'
]);

core.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
    $stateProvider
        .state('app', {
            url: '/app',
            abstract: true,
            templateUrl: coreTemplate
        });

    $urlRouterProvider.otherwise(function ($injector) {
        var $state = $injector.get('$state');
        $state.go('app.dashboard');
    });

    $httpProvider.interceptors.push(function($q, $injector) {

        return {
            'responseError': function(rejection){

                var defer = $q.defer();

                if(rejection.status == 401){
                    var $state = $injector.get('$state');
                    $state.go('app.login');
                }

                defer.reject(rejection);

                return defer.promise;

            }
        };
    });

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.common['Accept'] = 'application/json';

});

core.controller('App', function(config, $scope, $localStorage, $state, $http) {

    var vm = this;

    vm.title = config.appTitle;

    $scope.app = config;
    $scope.$state = $state;

    if (angular.isDefined($localStorage.state)){
        $scope.app.state = $localStorage.state;
    } else {
        $localStorage.state = $scope.app.state;
    }

    // == AUTH

    $http.defaults.headers.common['Authorization'] = 'Bearer ' + $localStorage.token;
    $http.get('/uaa/myId').then(function(data){
        $localStorage.userId = data;
    });

});


core.factory('$exceptionHandler', function($log, $window, $injector) {
    return function (exception, cause) {
        var errors = $window.JSON.parse($window.localStorage.getItem('adly-errors')) || {};
        errors[new Date().getTime()] = arguments;
        $window.localStorage.setItem('adly-errors', $window.JSON.stringify(errors));
        if ($injector.get('config').debug) {
            $log.error.apply($log, arguments);
            console.log('check errors');
        }
    };
});

require('modules/core/navigation')(core);
require('modules/core/config.js')(core);
require('modules/core/notifications')(core);






