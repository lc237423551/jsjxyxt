'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;        
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG', 
      function ($stateProvider,   $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {
          var layout = "tpl/app.jsp";
          if(window.location.href.indexOf("material") > 0){
            layout = "tpl/blocks/material.layout.html";
            $urlRouterProvider
              .otherwise('/app/dashboard-v3');
          }else{
            $urlRouterProvider
              .otherwise('/app/dashboard-v1');
          }
          
          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: layout
              })
              .state('app.dashboard-v1', {
                  url: '/dashboard-v1',
                  templateUrl: 'tpl/app_dashboard_v1.html',
                  resolve: load(['xeditable','ui.select' ])
                
              })
              .state('app.docs', {
                  url: '/docs',
                  templateUrl: 'tpl/docs.jsp',
                
              })
              //基本信息管理
              .state('app.baseMsg', {
                  url: '/table',
                  template: '<div ui-view class="fade-in-down"></div>'
              })
           
              .state('app.baseMsg.managerStu', {
                  url: '/managerStu',
                  templateUrl: 'tpl/SystemMag/managerStu.html',
                  controller: 'managerStu',
                  resolve: load(['xeditable','ui.select', 'toaster', 'angularFileUpload', 'js/controllers/SystemMag/managerStu.js', 'js/directives/ngAlert.js', 'js/directives/draggable.js'])
              })
              .state('app.baseMsg.managerTea', {
                  url: '/managerTea',
                  templateUrl: 'tpl/SystemMag/managerTea.html',
                  controller: 'managerTea',
                  resolve: load(['xeditable', 'toaster','ui.select','angularFileUpload','js/controllers/SystemMag/managerTea.js', 'js/directives/ngAlert.js', 'js/directives/draggable.js'])
              })
              //实训管理

              .state('apps', {
                  abstract: true,
                  url: '/apps',
                  templateUrl: 'tpl/layout.html'
              })
             .state('app.mydoc', {
                  url: '/mydoc',
                  templateUrl: 'tpl/PracticeMag/mydoc.html',
                  controller: 'mydoc',
                  resolve: load( ['toaster','js/controllers/PracticeMag/mydoc.js', 'moment','angularFileUpload'] )
              })
              .state('app.buttons', {
            	  url:'/buttons',
            	  templateUrl: 'tpl/PracticeMag/buttons.html',
            	  resolve: load(['toaster','ui.select', 'angularFileUpload'])
              })
                .state('app.widgets', {
                  url: '/widgets',
                  templateUrl: 'tpl/PracticeMag/ui_widgets.html'
                	  
              })          
                 .state('app.grid', {
                  url: '/grid',
                  templateUrl: 'tpl/PracticeMag/grid.html'
                	  
              }) 
             .state('app.bootstrap', {
                  url: '/bootstrap',
                  templateUrl: 'tpl/PracticeMag/bootstrap.html'
                	  
              })
               .state('app.history', {
            	  url:'/history',
            	  templateUrl: 'tpl/PracticeMag/history.html',
            	  controller:'history',
            	  resolve: load(['toaster','ui.select','js/controllers/PracticeMag/history.js'])
              })
              .state('app.icons', {
            	  url: '/icons',
            	  templateUrl: 'tpl/PracticeMag/icons.html',
            	  resolve: load(['toaster','ui.select'])
              })
              //教师界面
              //专业负责人
              .state('app.specMasterTeacher', {
            	  url: '/teacher',
            	  template: '<div ui-view class="fade-in"></div>'
              })
              .state('app.specMasterTeacher.trainManagerLeader', {
            	  url:'/trainManagerLeader',
            	  templateUrl: 'tpl/PracticeMag/trainManagerLeader.html',
            	  controller:'trainManagerLeader',
            	  resolve: load(['toaster','ui.select', 'xeditable','js/controllers/PracticeMag/trainManagerLeader.js', 'js/directives/ngAlert.js', 'js/directives/btFixed.js'])
              })
              .state('app.specMasterTeacher.trainSubject', {
            	  url:'/trainSubject',
            	  templateUrl: 'tpl/PracticeMag/trainSubject.html',
            	  controller:'trainSubject',
            	  resolve: load(['toaster','ui.select', 'xeditable', 'js/controllers/PracticeMag/trainSubject.js', 'js/directives/ngAlert.js', 'js/directives/btFixed.js'])
              })
              .state('app.specMasterTeacher.trainCompanyState', {
            	  url:'/trainCompanyState',
            	  templateUrl: 'tpl/PracticeMag/trainCompanyState.html',
            	  controller: 'trainCompanyState',
            	  resolve: load(['toaster','ui.select', 'angularFileUpload', 'js/controllers/PracticeMag/trainCompanyState.js'])
              })
              .state('app.specMasterTeacher.trainStudentState', {
            	  url: '/trainStudentState',
            	  templateUrl: 'tpl/PracticeMag/trainStudentState.html',
            	  controller: 'trainStudentState',
            	  resolve: load(['toaster','ui.select','js/controllers/PracticeMag/trainStudentState.js'])
              })
              .state('app.specMasterTeacher.applyStateSpecMaster', {
            	  url: '/applyStateSpecMaster',
            	  templateUrl: 'tpl/PracticeMag/shenqingSpecMaster.html',
            	  controller: 'applyStateSpecMaster',
            	  resolve: load(['xeditable', 'toaster','ui.select','js/controllers/PracticeMag/applyStateSpecMaster.js','js/directives/ngAlert.js'])
              })
              //班级负责人
              .state('app.classMasterTeacher', {
            	  url: '/teacher',
            	  template: '<div ui-view class="fade-in"></div>'
              })
              .state('app.classMasterTeacher.outputWeekSum', {
            	  url:'/outputWeekSum',
            	  templateUrl: 'tpl/PracticeMag/outputWeekSum.html',
            	  controller:'outputWeekSum',
            	  resolve: load(['toaster','ui.select','js/controllers/PracticeMag/outputWeekSum.js'])
              })
              .state('app.classMasterTeacher.practicePdf', {
            	  url:'/practicePdf',
            	  templateUrl: 'tpl/PracticeMag/practicePdf.html',
            	  controller:'practicePdf',
            	  resolve: load(['toaster','ui.select','js/controllers/PracticeMag/practicePdf.js'])
              })
              .state('app.classMasterTeacher.trainCompanyState', {
            	  url:'/trainCompanyState',
            	  templateUrl: 'tpl/PracticeMag/trainCompanyState.html',
            	  controller: 'trainCompanyState',
            	  resolve: load(['toaster','ui.select', 'angularFileUpload', 'js/controllers/PracticeMag/trainCompanyState.js'])
              })
              .state('app.classMasterTeacher.trainStudentStateClass', {
            	  url: '/trainStudentStateClass',
            	  controller: 'trainStudentStateClass',
            	  templateUrl: 'tpl/PracticeMag/trainStudentStateClass.html',
            	  resolve: load(['toaster','ui.select','js/controllers/PracticeMag/trainStudentStateClass.js'])
              })
              //实训指导教师
              .state('app.trainMasterTeacher', {
            	  url: '/teacher',
            	  template: '<div ui-view class="fade-in"></div>'
              })
             
              .state('apps.renwushu', {
            	  url:'/renwushu',
            	  templateUrl: 'tpl/PracticeMag/renwushu_contact.html',
            	  controller:'renwushu_contact',
            	  resolve: load(['js/controllers/PracticeMag/renwushu_contact.js'])
              })
              .state('apps.renwushu.detail', {
            	  url:'/renwushu?id',
            	  templateUrl: 'tpl/PracticeMag/renwushu.html',
            	  controller: 'renwushu',
                  resolve: load(['toaster','ui.select','js/controllers/PracticeMag/renwushu.js', 'js/directives/ngAlert.js'])
              })
              .state('app.trainMasterTeacher.applyStateTeacher', {
            	  url: '/applyStateTeacher',
            	  templateUrl: 'tpl/PracticeMag/shenqingTeacher.html',
            	  controller: 'applyStateTeacher',
            	  resolve: load(['xeditable', 'toaster','ui.select','js/controllers/PracticeMag/applyStateTeacher.js','js/directives/ngAlert.js'])
              })
              //辅导员
              .state('app.Counselor', {
            	  url: '/counselor',
            	  template: '<div ui-view class="fade-in"></div>'
              })
              //辅导员确认申请
              .state('app.Counselor.apply',{
            	  url:'/applyCounselor',
            	  templateUrl:'tpl/PracticeMag/shenqingCounselor.html',           	  
            	  controller:'applyCounselor',
            	  resolve: load(['xeditable', 'toaster','ui.select','js/controllers/PracticeMag/applyCounselor.js','js/directives/ngAlert.js', 'js/directives/draggable.js'])
              })
               .state('app.Counselor.checkCompany', {
            	  url:'/checkCompany',
            	  templateUrl: 'tpl/PracticeMag/checkCompany.html',
            	  controller: 'checkCompany',
            	  resolve: load(['toaster','ui.select', 'xeditable', 'angularFileUpload',  'js/directives/ngAlert.js', 'js/directives/draggable.js', 'js/controllers/PracticeMag/checkCompany.js'])
              })
                .state('app.Counselor.shixunCompany', {
            	  url:'/shixunCompany',
            	  templateUrl: 'tpl/PracticeMag/shixunCompany.html',
            	  controller: 'shixunCompany',
            	  resolve: load(['toaster','ui.select', 'xeditable', 'angularFileUpload', 'js/controllers/PracticeMag/shixunCompany.js', 'js/directives/ngAlert.js', 'js/directives/draggable.js'])
              })
              
          function load(srcs, callback) {
            return {
                deps: ['$ocLazyLoad', '$q',
                  function( $ocLazyLoad, $q ){
                    var deferred = $q.defer();
                    var promise  = false;
                    srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                    if(!promise){
                      promise = deferred.promise;
                    }
                    angular.forEach(srcs, function(src) {
                      promise = promise.then( function(){
                        if(JQ_CONFIG[src]){
                          return $ocLazyLoad.load(JQ_CONFIG[src]);
                        }
                        angular.forEach(MODULE_CONFIG, function(module) {
                          if( module.name == src){
                            name = module.name;
                          }else{
                            name = src;
                          }
                        });
                        return $ocLazyLoad.load(name);
                      } );
                    });
                    deferred.resolve();
                    return callback ? promise.then(function(){ return callback(); }) : promise;
                }]
            }
          }


      }
    ]
  );
