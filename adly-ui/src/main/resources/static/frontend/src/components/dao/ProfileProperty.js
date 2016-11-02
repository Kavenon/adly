module.exports = function(module){
    "use strict";
    
    module.factory('AdlyDaoPredefinedProperty', function($resource) {
        return $resource('/api/profile/predefined-property');
    });

    module.factory('AdlyDaoUserProperty', function($resource) {
        return $resource('/api/profile/user-property/:id', { id: '@id' }, {
            update: {
                method: 'PUT'
            }
        });
    });

    module.factory('AdlyDaoProperty', function(AdlyDaoPredefinedProperty, AdlyDaoUserProperty) {
        return {
            get: function() {
                return AdlyDaoPredefinedProperty.query().$promise.then(function (predefinedProperties) {
                    return AdlyDaoUserProperty.query().$promise.then(function(userProperties){
                        return predefinedProperties.concat(userProperties);
                    });
                });
            }
        }
    });

};