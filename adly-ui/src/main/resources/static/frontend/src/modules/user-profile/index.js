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


    $scope.deleteProperty = function(item, $index) {
        item.$delete(function() {
            $scope.userProperties.splice($index, 1);
        });
    };

});
module.controller('UserProfileFormController', function ($scope, $stateParams, UserProperty, $state) {

    if($stateParams.propertyId === ""){
        $scope.property = new UserProperty();
        // @todo: create directive for property types (input json -> output form)
        $scope.property.type = {
            type: '.SimplePropertyType'
        };
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
    return $resource('/api/profile/predefined-property');
});


module.factory('UserProperty', function($resource) {
    return $resource('/api/profile/user-property/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
});

