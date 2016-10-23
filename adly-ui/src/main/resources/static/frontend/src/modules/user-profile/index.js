require('angular-ui-router');
require('angular-resource');

var userProfileTemplate = require('modules/user-profile/profile.html');
var userProfileForm = require('modules/user-profile/profileForm.html');

var module = angular.module('adlyApp.userProfile', [
  'ui.router',
  'ngResource'
]);

module.config(function($stateProvider) {
    $stateProvider
        .state('app.user-profile', {
            url: '/user-profile',
            templateUrl: userProfileTemplate,
            controller: 'UserProfileController'
        })
        .state('app.user-profile.upsert', {
            url: '/upsert/:propertyId',
            templateUrl: userProfileForm,
            controller: 'UserProfileFormController'
        })
});

module.controller('UserProfileController', function ($scope, PredefinedProperty, UserProperty) {

    $scope.predefinedProperties = PredefinedProperty.query();
    $scope.userProperties = UserProperty.query();

});
module.controller('UserProfileFormController', function ($scope, $stateParams, UserProperty, $state) {

    if($stateParams.propertyId === ""){
        $scope.property = new UserProperty();
        $scope.upsert = function() {
            $scope.property.$save(function() {
                $state.go('app.user-profile', {}, { reload: true});
            });
        };
    }
    else {
        $scope.property = UserProperty.get({ id: $stateParams.propertyId });
        $scope.upsert = function() {
            $scope.property.$update(function() {
                $state.go('app.user-profile', {}, { reload: true});
            });
        };
    }

});
module.factory('PredefinedProperty', function($resource) {
    return $resource('/api/predefined/property');
});


module.factory('UserProperty', function($resource) {
    return $resource('/api/user/property/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
});

