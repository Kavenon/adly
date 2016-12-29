require('angular-ui-router');
require('angular-resource');

var loginFormTemplate = require('modules/login/loginForm.html');

var module = angular.module('adlyApp.login', [
  'ui.router',
  'ngResource'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.login', {
            url: '/login',
            views : {
                '@' : {
                    templateUrl: loginFormTemplate,
                    controller: 'LoginFormTemplate'
                }
            }
        });
});

module.controller('LoginFormTemplate', function ($scope, $http, $localStorage,$httpParamSerializerJQLike, $state) {

    $scope.auth = {
        username: '',
        password: '',
        scope: 'ui',
        grant_type: 'password'
    };
    
    $scope.login = function(){
        $http({
            method: 'POST',
            url: '/uaa/oauth/token',
            data: $httpParamSerializerJQLike($scope.auth),
            headers: {
                'Authorization': 'Basic YnJvd3Nlcjo=',
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function(data){
            $localStorage.token = data.data.access_token;
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $localStorage.token;
            $state.go('app.dashboard');
        }, function(){
            $localStorage.token = null;
        });
    };
    
});

