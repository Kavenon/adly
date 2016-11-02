module.exports = function(module) {

    var propertyOperators = {
        '.SimplePropertyType' : function(property){
            switch(property.type.simpleType){
                case 'TEXT':
                    return ['EQUAL', 'NOT_EQUAL', 'CONTAINS', 'NOT_CONTAINS'];
                break;
                case 'INTEGER':
                    return ['EQUAL', 'NOT_EQUAL', 'LESS_THAN', 'MORE_THAN'];
                break;
                case 'DATE':
                    return ['BEFORE', 'AFTER', 'TODAY'];
                break;
            }
        }
    };
    require('components/dao/ProfileProperty.js')(module);

    module.controller('UserProfileConditionController', function($scope, AdlyDaoProperty, $filter){
        $scope.selected.condition = '.UserProfileCondition';
        AdlyDaoProperty.get().then(function(promise){
            $scope.profileProperties = promise;
        });

        $scope.addProperty = function(){

            if(!$scope.editingCondition.config.checks){
                $scope.editingCondition.config.checks = [];
            }
            $scope.editingCondition.config.checks.push({});
        };

        var getPropertyById = function(propertyId){
            return $filter('filter')($scope.profileProperties, {id: propertyId}, true);
        };
        
        $scope.getProperty = function(propertyId){
            return getPropertyById(propertyId);
        };

        $scope.getOperators = function(propertyId){
            var property = getPropertyById(propertyId);
            if(property.length){
                return propertyOperators[property[0].type.type](property[0]);
            }
            else {
                return [];
            }
        }
    });
    
    return {
        name: '.UserProfileCondition',
        body: {
            templateUrl: require('./userProfileCondition.html'),
            controller: 'UserProfileConditionController',
            icon: 'fa-user'
        }
    }
   
};