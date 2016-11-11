module.exports = function(module){
    "use strict";

    module.factory('AdlyDaoBeacon', function($resource) {
        return $resource('/api/beacon/:id', { id: '@id' }, {
            update: {
                method: 'PUT'
            },
        });
    });

};