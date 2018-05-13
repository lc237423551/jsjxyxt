'use strict';
angular.module('app').controller('SigninFormController', ['$scope', '$http', 'toaster','$window', function($scope, $http, toaster,$window) {

	$scope.value='t0';
	$('#imgRoleFace').attr('src', '/jsjxyxt/src/img/' + $scope.value + '.jpg');
	
	$scope.submitted = false;
	$scope.userRoldSelected = false;
	$scope.changeimage = function(){
		if($scope.user.role=='管理员'){
			$scope.value='t1';
		}else if($scope.user.role=='学生'){
			$scope.value='t2';
		}else if($scope.user.role=="教师"){
			$scope.value='t3';
		}else if($scope.user.role=="辅导员"){
			$scope.value='t4';
		}else{
			$scope.value='t0';
		}
		$('#imgRoleFace').attr('src', '/jsjxyxt/src/img/' + $scope.value + '.jpg');
	};
	
	$scope.Login=function(){
		$scope.submitted = true;
		//alert($scope.user.role);
		if($scope.user.role == undefined) {
			//alert($scope.user.role);
			$scope.userRoldSelected = false;
			return;
		}
		var user = {
				"role": $scope.user.role,
				"name": $scope.user.name,
				"password": $scope.user.password,
				"code": $scope.user.code,
			};
		var url="/jsjxyxt/log/login.do";
		$http.post(url,user).success(function(data){
			$scope.result=data.result;
			if($scope.result=="1"){
				$scope.message="验证码错误";
			    $scope.toaster = {
			            type: 'error',
			            title: '错误提示',
			            text: '验证码错误'
			        };
			    $("#image").attr('src',"/jsjxyxt/image.do?") + new Date().getTime();
			} else if($scope.result=="5"){
				$scope.message="密码错误";
			    $scope.toaster = {
			            type: 'error',
			            title: '错误提示',
			            text: '密码错误'
			        };
			    $("#image").attr('src',"/jsjxyxt/image.do?") + new Date().getTime();
			}else if($scope.result=="6"){
				$scope.message="账号不存在";
			    $scope.toaster = {
			            type: 'warning',
			            title: '警告',
			            text: '账号不存在'
			        };
			    $("#image").attr('src',"/jsjxyxt/image.do?") + new Date().getTime();
			}else {
				
				$scope.toaster = {
			            type: 'success',
			            title: '验证通过',
			            text: '成功登陆系统'
			        };
				//var myDate=new Date();
				
			    $window.location.href ='/jsjxyxt/src/index.jsp';
			}
			toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
		});
	};
  }]);
