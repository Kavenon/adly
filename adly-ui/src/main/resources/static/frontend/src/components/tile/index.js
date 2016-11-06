module.exports = function(module){
    "use strict";
    module.directive('liveTile', function liveTile(){
        return {
            restrict: 'EAC',
            link: function (scope, $el, attrs){
                $($el).css('height', attrs.height).liveTile();
                // remove onResize timeouts if present
                scope.$on('$stateChangeStart', function(){
                    $($el).liveTile('destroy', true);
                });
            }
        }
    });
};
