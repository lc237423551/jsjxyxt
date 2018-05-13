app.controller('contactCtrl', ['$scope', '$http', '$filter', '$location', '$timeout', function($scope, $http, $filter, $location, $timeout) {
  $scope.test = $location.search().cno
  $scope.isShow=true;
  $scope.filter = '';
  //获取所有班级
  $http.get('/jsjxyxt/cla/get1.do').success(function(data){
		console.log(data.result)
		$scope.groups = data.result
		$scope.group = $filter('orderBy')($scope.groups, 'cname')[0];
	})
  //获取学生班级
   $http.get('/jsjxyxt/trainInfo/findByTno.do').success(function (data) {
	    $scope.students = data.result;
	    console.log(data.result.length)
	    $scope.student = $filter('orderBy')($scope.students, 'sname')[0];
	    $scope.isShow=false;
	    $scope.student.selected = true;
  });
  

  $scope.selectGroup = function(item){    
    angular.forEach($scope.groups, function(item) {
      item.selected = false;
    });
    $scope.group = item;
    $scope.group.selected = true;
    $scope.filter = item.cname;
  };

  $scope.selectItem = function(item){    
    angular.forEach($scope.students, function(item) {
      item.selected = false;
//      item.editing = false;
    });
    $scope.student = item;
    $scope.student.selected = true;
    
//    $timeout(function(){
//    	$scope.$apply(function(){
//    		$scope.student = item;
//    	})
//    })
  };

 

}]);