/**
 * 老师端任务书管理
 */
angular.module('app').controller('renwushu_contact',['$scope','$http', function($scope, $http){
	/*
	 * 得到任务书题目
	 */
	  $http.get('/jsjxyxt/subjectname/getByTea.do').success(function (data) {
		   $scope.subjects=data.result;
	  });
}])