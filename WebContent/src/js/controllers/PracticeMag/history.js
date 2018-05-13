/**
 * 
 */
app.controller('history', ['$scope', '$http', function($scope, $http){
	
	
	$http.get('/jsjxyxt/history/get.do').success(function(data){
		$scope.students = data.result;
	})
	$scope.selectStudent=function(){
		var data={
				'sname':$scope.sname,
				'year':$scope.year
		}
		$http.post('/jsjxyxt/history/search.do',data).success(function(data){
			$scope.students = data.result;
		})
	}
	$scope.Reset=function(){
		$http.get('/jsjxyxt/history/reset.do').success(function(data){
			$scope.students = data.result;
		})
	}
	
	
}])