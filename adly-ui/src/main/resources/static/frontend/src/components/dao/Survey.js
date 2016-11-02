module.exports = function(module){
    "use strict";

    module.factory('AdlyDaoUserSurvey', function($resource) {
        return $resource('/api/profile/user-survey/:id', { id: '@id' }, {
            update: {
                method: 'PUT'
            }
        });
    });

};