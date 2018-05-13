/**
 * 
 */
/**
 * 学生实训情况
 */
app.controller('trainStudentStateClass', ['$scope', '$http', 'toaster', function($scope, $http, toaster){

	//获取学生实训情况
	$http.get('/jsjxyxt/trainInfo/getByTno.do').success(function(data){
		$scope.allMsys = data.result
		$scope.cname=data.result[0].student.cla.cname;
	})
	//查询
	$scope.select = function() {
    	var url = '/jsjxyxt/trainInfo/searchByUname.do',
		data = {
			"uname": $scope.uname
		}
	$http.post(url,data).success(function(data){
		var length=data.result.length;
		console.log(length)
		if(length==0){
			toaster.pop("error", "系统提示", "未查找到记录");
		}else{
			$scope.allMsys = data.result;
		}
	})
   }; 
}])