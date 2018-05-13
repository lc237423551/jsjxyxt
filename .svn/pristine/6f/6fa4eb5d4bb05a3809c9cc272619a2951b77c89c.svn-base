'use strict';
/* Controllers */
  // signin controller
angular.module('app').controller('password', ['$scope', '$http', '$window', function($scope, $http, $window) {
	$scope.change=function(){
		var password={
				"old":$scope.old,
				"n1":$scope.n1,
				"n2":$scope.n2,
		}
		var url="/jsjxyxt/user/updatePassword.do";
		$http.post(url,password).success(function(data){
			if(data.errmsg==0){
				alert("和原密码不一样")
			}else{
				$window.location.href ='/jsjxyxt/src/login.jsp'
				
			}
			
		})
			
	}
  }]);
