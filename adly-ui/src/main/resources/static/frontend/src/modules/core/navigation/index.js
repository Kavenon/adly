
var sidebarTemplate = require('modules/core/navigation/sidebar.html');
var navbarTopTemplate = require('modules/core/navigation/navbar-top.html');

var Slimscroll = require('slimscroll');

'use strict';

module.exports = function(module){

  module
    .directive('snAction', snAction)
    .directive('snNavigation', snNavigation)
    .directive('snNavbar', snNavbar)
  ;
  /* ========================================================================
   * Sing App actions. Shortcuts available via data-sn-action attribute
   * ========================================================================
   */
  
  function snNavbar(){
      return {
        templateUrl: navbarTopTemplate
      }
  };
  
  snAction.$inject = ['$rootScope', '$timeout'];
  function snAction($rootScope, $timeout){
    var singActions = {
      'toggle-navigation-state': function(e, scope){
        scope.app.state['nav-static'] = !scope.app.state['nav-static'];
      },
      'toggle-navigation-collapse-state': function(){
        $rootScope.toggleNavigationCollapseState();
      },
      'toggle-chat-sidebar-state': function(){
        //remove notification sign on a first click
        jQuery(this).find('.chat-notification-sing').remove();
        $rootScope.toggleChatSidebarState();


      }
    };
    return {
      restrict: 'A',
      link: function (scope, $_el, attrs){
          var $el = jQuery($_el);

        if (angular.isDefined(attrs.snAction) && attrs.snAction !== '') {
          $el.on('click', function(e) {
            scope.$apply(function(){
              singActions[attrs.snAction].call($el[0], e, scope);
            });
            e.preventDefault();
          });
        }

        if (angular.isDefined(attrs.tooltip) && attrs.snAction !== ''){
          $el.tooltip();
        }
      }
    }
  }

  /* ========================================================================
   * Sing App Navigation (Sidebar)
   * ========================================================================
   */
  snNavigation.$inject = ['$timeout', '$rootScope', '$state', '$window'];
  function snNavigation($timeout, $rootScope, $state, $window){
    var SnNavigationDirective = function($el, scope){
      this.$el = jQuery($el);
      this.scope = scope;
      this.helpers = scope.app.helpers;

      // publish method to global scope to allow navigation collapsing via api
      $rootScope.toggleNavigationCollapseState = jQuery.proxy(this.toggleNavigationCollapseState, this);
    };
    SnNavigationDirective.prototype = { // a set of reusable directive private functions
      expandNavigation: function(){
        //this method only makes sense for non-static navigation state
        if (this.isNavigationStatic() && (this.helpers.isScreen('md') || this.helpers.isScreen('lg'))) return;

        jQuery('body').removeClass('nav-collapsed');
        this.$el.find('.active .active').closest('.collapse').collapse('show')
          .siblings('[data-toggle=collapse]').removeClass('collapsed');
      },

      collapseNavigation: function(){
        //this method only makes sense for non-static navigation state
        if (this.isNavigationStatic() && (this.helpers.isScreen('md') || this.helpers.isScreen('lg'))) return;

        jQuery('body').addClass('nav-collapsed');
        this.$el.find('.collapse.in').collapse('hide')
          .siblings('[data-toggle=collapse]').addClass('collapsed');
      },


      /**
       * Check and set navigation collapse according to screen size and navigation state
       */
      checkNavigationState: function(){
        if (this.isNavigationStatic()){
          if (this.helpers.isScreen('sm') || this.helpers.isScreen('xs')){
            this.collapseNavigation();
          }
        } else {
          if (this.helpers.isScreen('md') || this.helpers.isScreen('lg')){
            var view = this;
            $timeout(function(){
              view.collapseNavigation();
            }, this.scope.app.settings.navCollapseTimeout);
          } else {
            this.collapseNavigation();
          }
        }
      },

      isNavigationStatic: function(){
        return this.scope.app.state['nav-static'] === true;
      },

      changeActiveNavigationItem: function(event, toState, toParams){

        var $newActiveLink =  this.$el.find('a').filter(function(i, a){
              return $state.href(toState, toParams).indexOf(jQuery(a).attr('href')) !== -1;
          });

        if (!$newActiveLink.is('.active > .collapse > li > a')){
          this.$el.find('.active .active').closest('.collapse').collapse('hide');
        }
        this.$el.find('.sidebar-nav .active').removeClass('active');

        $newActiveLink.closest('li').addClass('active')
          .parents('li').addClass('active');

        // uncollapse parent
        $newActiveLink.closest('.collapse').addClass('in').siblings('a[data-toggle=collapse]').removeClass('collapsed');
      },

      toggleNavigationCollapseState: function(){
        if (jQuery('body').is('.nav-collapsed')){
          this.expandNavigation();
        } else {
          this.collapseNavigation();
        }
      },

      enableSwipeCollapsing: function(){
        var d = this;
        jQuery('.content-wrap').swipe({
          swipeLeft: function(){
            //this method only makes sense for small screens + ipad
            if (d.helpers.isScreen('lg')) return;

            if (!jQuery('body').is('.nav-collapsed')){
              d.collapseNavigation();
            }
          },
          swipeRight: function(){
            //this method only makes sense for small screens + ipad
            if (d.helpers.isScreen('lg')) return;

            // check if navigation is collapsing. exiting if true
            if (jQuery('body').is('.nav-busy')) return;

            if (jQuery('body').is('.nav-collapsed')){
              d.expandNavigation();
            }
          },
          threshold: this.helpers.isScreen('xs') ? 100 : 200
        });
      },

      collapseNavIfSmallScreen: function(){
        if (this.helpers.isScreen('xs') || this.helpers.isScreen('sm')){
          this.collapseNavigation();
        }
      },

      _sidebarMouseEnter: function(){
        if (this.helpers.isScreen('md') || this.helpers.isScreen('lg')){
          this.expandNavigation();
        }
      },
      _sidebarMouseLeave: function(){
        if (this.helpers.isScreen('md') || this.helpers.isScreen('lg')){
          this.collapseNavigation();
        }
      }
    };
    return {
      replace: true,
      templateUrl: sidebarTemplate ,
      link: function (scope, $el){
        var d = new SnNavigationDirective($el, scope);

        $el.on('mouseenter', jQuery.proxy(d._sidebarMouseEnter, d));
        $el.on('mouseleave', jQuery.proxy(d._sidebarMouseLeave, d));

        // wait until all includes included
        $timeout(function(){
          // set active navigation item
          d.changeActiveNavigationItem({}, $state.$current, $state.params);

          d.checkNavigationState();
        });

        /**
         * open navigation if collapsed sidebar clicked
         */
        $el.on('click', function(){
          if (jQuery('body').is('.nav-collapsed')){
            d.expandNavigation();
          }
        });

        scope.$watch('app.state["nav-static"]', function(newVal, oldVal){
          if (newVal === oldVal) return; // shouldn't they fix it?
          if (!newVal){ // if navigation state is collapsing
            d.collapseNavigation();
          }

          // let angular finish doing its stuff so all animation are applied to trigger an event on a ready DOM
          $timeout(function(){
            jQuery($window).trigger('sn:resize');
          })
        });

        // change active navigation item when state change
        $rootScope.$on('$stateChangeStart', jQuery.proxy(d.changeActiveNavigationItem, d));
        $rootScope.$on('$stateChangeSuccess', jQuery.proxy(d.collapseNavIfSmallScreen, d));

        // scroll to top manually after page change. seems that it doesn't work out of the box because
        // the actual app state is not changed - it remain app.page - only params changed.
        $rootScope.$on('$stateChangeSuccess', function(){
          $window.scrollTo(0, 0);
        });

        // enable swipe navigation collapsing
        ('ontouchstart' in $window) && d.enableSwipeCollapsing();

        /* reimplementing bs.collapse data-parent here as we don't want to use BS .panel*/
        $el.find('.collapse').on('show.bs.collapse', function(e){
          // execute only if we're actually the .collapse element initiated event
          // return for bubbled events
          if (e.target !== e.currentTarget) return;

          var $triggerLink = jQuery(this).prev('[data-toggle=collapse]');
          jQuery($triggerLink.data('parent')).find('.collapse.in').not(jQuery(this)).collapse('hide');
        })
          /* adding additional classes to navigation link li-parent for several purposes. see navigation styles */
          .on('show.bs.collapse', function(e){
            // execute only if we're actually the .collapse element initiated event
            // return for bubbled events
            if (e.target !== e.currentTarget) return;

            jQuery(this).closest('li').addClass('open');
          }).on('hide.bs.collapse', function(e){
            // execute only if we're actually the .collapse element initiated event
            // return for bubbled events
            if (e.target !== e.currentTarget) return;

            jQuery(this).closest('li').removeClass('open');
          });

        function initSidebarScroll(){
          var $sidebarContent = $el.find('.js-sidebar-content');
          if ($el.find('.slimScrollDiv').length !== 0){
              new Slimscroll({
                  destroy: true
              }, $sidebarContent)
          }
          new Slimscroll({
              height: $window.innerHeight,
              size: '4px'
          }, $sidebarContent);
        }

        jQuery($window).on('sn:resize', initSidebarScroll);
        initSidebarScroll();
      }
    }
  }

  }
