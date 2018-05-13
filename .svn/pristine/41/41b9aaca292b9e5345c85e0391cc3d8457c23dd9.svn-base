/**
 * 学生实训情况
 */
app.controller('trainStudentState', ['$scope', '$http', function($scope, $http){

	$scope.classes=[];
	$scope.status = ['全部','校内','校外'];
	//班级下拉框
	$http.get('/jsjxyxt/cla/getClaByTno.do').success(function(data){
		$scope.classes = data.result;
		console.log(data)
		$scope.specname=data.result[1].speciality.specname;
	})
	$scope.length=0;
	 //加载动态图控制
    $scope.wait=0;
	//获取学生实训情况
	$scope.cla={
	     classes:''
	    }
	$scope.stus = {
		status: ''
	}
//根据下拉框获取学生
$scope.selectStu = function(){
		$scope.wait=1;
	var data = {
		"cno": $scope.cla.classes.cno,
		"state": $scope.stus.status
	}
	console.log(data)
	var url = '/jsjxyxt/trainInfo/get.do'
	$http.post(url,data).success(function(data){
		$scope.wait=0;
		$scope.allMsys = data.result;
		$scope.length=data.result.length
	})

}
$scope.selectStuByName = function() {
	$scope.wait=1;
	var data = {
		"sname": $scope.sname
	}
	var url = '/jsjxyxt/trainInfo/get.do';
	$http.post(url,data).success(function(data){
		$scope.wait=0;
		$scope.allMsys = data.result;
	})
}
	//-----------------------------------------获取学生实训情况
}])