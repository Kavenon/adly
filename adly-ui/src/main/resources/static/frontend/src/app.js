
angular.module('adlyApp', []).config(function($httpProvider) {

    // $routeProvider.when('/', {
    //     templateUrl : 'home.html',
    //     controller : 'home',
    //     controllerAs : 'controller'
    // }).otherwise('/');

    // $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    // $httpProvider.defaults.headers.common.Accept = 'application/json';

}).controller('App',

    function($rootScope, $http) {
        //
        // var self = this;
        //
        // self.tab = function(route) {
        //     return $route.current && route === $route.current.controller;
        // };
        console.log('fired');

        $http.get('user').then(function(response) {
            console.log('res', response);
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
        }, function(error) {
            if(error.status == 401){
                window.location = '/login';
            }
            $rootScope.authenticated = false;
        });
        
        $http.get('resource/accounts/1/surveys').then(function(response) {
            console.log(response);
        })
        //
        // self.credentials = {};
        //
        // self.logout = function() {
        //     $http.post('logout', {}).finally(function() {
        //         $rootScope.authenticated = false;
        //         $location.path("/");
        //     });
        // }

    });

