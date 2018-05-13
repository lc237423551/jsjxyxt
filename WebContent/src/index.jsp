<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <%@page import="com.java.entity.User" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
  <meta charset="utf-8" />
  <meta name="renderer" content="webkit">
  <title>计算机学院实训管理</title>
  <link rel="shortcut icon" href="/jsjxyxt/src/img/shixun.ico" />
  <meta name="description" content="Angularjs, Html5, Music, Landing, 4 in 1 ui kits package" />
  <meta name="keywords" content="AngularJS, angular, bootstrap, admin, dashboard, panel, app, charts, components,flat, responsive, layout, kit, ui, route, web, app, widgets" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="../libs/assets/animate.css/animate.css" type="text/css" />
  <link rel="stylesheet" href="../libs/assets/font-awesome/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="../libs/assets/simple-line-icons/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="../libs/jquery/bootstrap/dist/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="../libs/jquery/jquery-pagewalkthrough/dist/css/jquery.pagewalkthrough.min.css" type="text/css" />
  
  <!-- build:css css/app.min.css -->
  <link rel="stylesheet" href="css/font.css" type="text/css" />
  <link rel="stylesheet" href="css/app.css" type="text/css" />
  <!-- endbuild -->
  
</head>
<body ng-controller="AppCtrl">
  <div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}" ui-view></div>

<!-- build:js js/app.angular.js -->
  <!-- jQuery -->
  <script src="../libs/jquery/jquery/dist/jquery.js"></script>
  <!-- 引导层 -->
  <c:if test="${user.loginnum == 1}" var="condition" scope="page">
  	  <script src="../libs/jquery/jquery-pagewalkthrough/dist/jquery.pagewalkthrough.min.js"></script>
	  
	  <c:if test="${user.role=='学生'}" var="condition" scope="page">
	  <script src="../src/js/app/pagewalkthrough/pagewalker_stu.js"></script>
	  </c:if>
	  <c:if test="${user.role=='管理员'}" var="condition" scope="page">
	  <script src="../src/js/app/pagewalkthrough/pagewalker_manager.js"></script>
	  </c:if>
	  <c:if test="${user.role=='教师'}" var="condition" scope="page">
	  <script src="../src/js/app/pagewalkthrough/pagewalker_tea.js"></script>
	  </c:if>
  
  </c:if>
  <!-- Bootstrap -->
  <script src="../libs/jquery/bootstrap/dist/js/bootstrap.js"></script>

  <!-- Angular -->
  <script src="../libs/angular/angular/angular.js"></script>
  <script src="../libs/jquery/pjpdf/pdfobject.js"></script>
  <script src="../libs/angular/angular-animate/angular-animate.js"></script>
  <script src="../libs/angular/angular-aria/angular-aria.js"></script>
  <script src="../libs/angular/angular-cookies/angular-cookies.js"></script>
  <script src="../libs/angular/angular-messages/angular-messages.js"></script>
  <script src="../libs/angular/angular-resource/angular-resource.js"></script>
  <script src="../libs/angular/angular-sanitize/angular-sanitize.js"></script>
  <script src="../libs/angular/angular-touch/angular-touch.js"></script>
  <script src="angular-locale_zh-cn.js"></script>
  
  <script src="../libs/angular/angular-ui-router/release/angular-ui-router.js"></script> 
  <script src="../libs/angular/ngstorage/ngStorage.js"></script>
  <script src="../libs/angular/angular-ui-utils/ui-utils.js"></script>

  <!-- bootstrap -->
  <script src="../libs/angular/angular-bootstrap/ui-bootstrap-tpls.js"></script>
  <!-- lazyload -->
  <script src="../libs/angular/oclazyload/dist/ocLazyLoad.js"></script>
  <!-- translate -->
  <script src="../libs/angular/angular-translate/angular-translate.js"></script>
  <script src="../libs/angular/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
  <script src="../libs/angular/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
  <script src="../libs/angular/angular-translate-storage-local/angular-translate-storage-local.js"></script>

  <!-- App -->
  <script src="js/app.js"></script>
  <script src="js/config.js"></script>
  <script src="js/config.lazyload.js"></script>
  <script src="js/config.router.js"></script>
  <script src="js/main.js"></script>
  <script src="js/services/ui-load.js"></script>
  <script src="js/filters/fromNow.js"></script>
  <script src="js/directives/setnganimate.js"></script>
  <script src="js/directives/ui-butterbar.js"></script>
  <script src="js/directives/ui-focus.js"></script>
  <script src="js/directives/ui-fullscreen.js"></script>
  <script src="js/directives/ui-jq.js"></script>
  <script src="js/directives/ui-module.js"></script>
  <script src="js/directives/ui-nav.js"></script>
  <script src="js/directives/ui-scroll.js"></script>
  <script src="js/directives/ui-shift.js"></script>
  <script src="js/directives/ui-toggleclass.js"></script>
  <script src="js/controllers/bootstrap.js"></script>
  <script src="js/controllers/SystemMag/password.js"></script>
  <script src="js/controllers/modal.js"></script>
  <script src="js/controllers/SystemMag/syargu.js"></script>
  
  
<!-- endbuild -->
  <!-- Lazy loading -->
</body>
</html>
