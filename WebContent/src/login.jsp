<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.java.listener.CountLineListener"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="/jsjxyxt/src/img/shixun.ico" />
  <title>用户登录 - 实训过程管理系统</title>  
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="X-UA-Compatible" content="chrome=1">
  <link rel="stylesheet" href="/jsjxyxt/libs/jquery/bootstrap/dist/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="/jsjxyxt/libs/assets/font-awesome/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="/jsjxyxt/libs/assets/simple-line-icons/css/simple-line-icons.css" type="text/css" />
  <!-- jQuery -->
  <script type="text/javascript">
   window.onload = function(){ 
		   if(ietest()) {
			   document.write("<p>您的浏览器版本太低啦，建议您更换浏览器，或者下载最新的IE浏览器!!!!!!!!</p>");
			   
		   } 
		
			
   }
	
</script>
  
  <script src="/jsjxyxt/libs/jquery/jquery/dist/jquery.js"></script>
  <!-- build:css css/app.min.css -->
  <link rel="stylesheet" href="/jsjxyxt/src/css/font.css" type="text/css" />
  <link rel="stylesheet" href="/jsjxyxt/src/css/app.css" type="text/css" />
   <link rel="stylesheet" href="/jsjxyxt/src/css/Longin.css" type="text/css" />
   <link rel="stylesheet" href="/jsjxyxt/libs/angular/angularjs-toaster/toaster.css" type="text/css" /> 
  <!-- endbuild -->
  	<script>
		function change(){
			$("#image").attr('src', "/jsjxyxt/image.do?" + new Date().getTime());
			$scope.userRoldSelected = true;
		}
		function ietest() {	
			return window.attachEvent && !window.addEventListener;
		
		}
	</script>
</head>
<!-- build:js js/app.angular.js -->


  <!-- Angular -->
  <script src="/jsjxyxt/libs/angular/angular/angular.js"></script>
  <script src="/jsjxyxt/libs/angular/angularjs-toaster/toaster.js"></script>
  


  <!-- App -->
  <script src="/jsjxyxt/src/js/loginApp.js"></script>
  <script src="/jsjxyxt/src/js/controllers/signin.js"></script>
  <!-- endbuild -->
  <!-- Lazy loading -->

</head>
<body>

<div id="loginBoxContainer" ng-controller="SigninFormController" ng-init="app.settings.container = false;" >
   <toaster-container toaster-options="{'position-class': 'toast-top-right', 'close-button':true}"></toaster-container>
    <h2 class="ietest" id="ietest">您的浏览器版本太低啦，建议您更换浏览器，或者下载最新的IE浏览器!!!!!!!!</h2>
	<h1>计算机学院实训管理系统</h1>  
	
    <img id="imgRoleFace" src="" class="img-circle">  
    
    <form name="form" class="opacity-wrapper-8">
         <select class="form-control" ng-model="user.role" ng-change="changeimage()" required>
            <option value="" disabled>请选择身份</option>
            <option value="管理员">管理员</option>
            <option value="辅导员">辅导员</option>
            <option value="教师">教师</option>
            <option value="学生">学生</option>
          </select> 
           <div class="text-danger " style="">
		        <!--<div ng-show='$scope.submitted && !$scope.userRoldSelected'>
		        	<i class="fa fa-exclamation-circle "></i> 请选择身份   
	        	</div>--> 
	        </div>        
          <input type="text" name="username" class="form-control bg-lightblack" style="color: #111;" placeholder="账号" ng-model="user.name"  required >
       
        
        <div class="text-danger " style="">
	        <div ng-show='(form.username.$invalid && form.username.$touched)'>
	        	<i class="fa fa-exclamation-circle "></i> 账号不能为空   
        	</div>
        </div>        
       
        <input type="password" name="password" placeholder="密码" class="form-control bg-lightblack" style="color: #111;" ng-model="user.password"   required>
       
        
        <div class="text-danger" >
	        <div ng-show='(form.password.$invalid && form.password.$touched)'>
	        	<i class="fa fa-exclamation-circle "></i> 密码不能为空   
        	</div>
        </div>
        
        <div class="CaptchaRow clearfix">
        	<input type="text" name="code" autocomplete="off" placeholder="验证码" class="form-control bg-lightblack" style="width: 12em" ng-minlength="4" ng-maxlength="4" ng-model="user.code" required >   
           	<img src="/jsjxyxt/image.do" id="image" class="img-responsive" onclick="change();"/>   
        </div>
        <div class="text-danger">
	        <div ng-show='form.code.$invalid && form.code.$touched'>
	        	<i class="fa fa-exclamation-circle "></i> 验证码为4位
        	</div>
        </div>		
      
      <button type="submit" class="btn btn-lg btn-primary btn-block" ng-disabled="form.$invalid" ng-click="Login()">登录</button>
    </form>
   
</div>
 <footer>
 <span class="h4">  当前在线：${userCount }人 </span></br>
<p>&copy;2017&nbsp;计算机科学与技术学院 软件工程教研室<br>山东建筑大学</br></p>
<p><span class="ideal">建议使用以下浏览器来获取更好的体验。如果使用360或QQ浏览器请在地址栏后方开启极速模式</span></p>
<p><img  src="/jsjxyxt/src/img/chrome.png" width="25px" height="25px"/><a href="http://www.google.cn/chrome/browser/index.html?hl=zh-CN&standalone=1" target="_blank"><span class="chrome_target">&nbsp;&nbsp;&nbsp;谷歌浏览器下载</span></a></p>
</footer>
</body>
</html>