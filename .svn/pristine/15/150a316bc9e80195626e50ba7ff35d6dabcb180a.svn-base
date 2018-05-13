// 实训指导教师管理

app.factory('weekStorage', function () {
    var STORAGE_ID = 'weeksum';
    
    return {
    	
        get: function () {
            return JSON.parse(localStorage.getItem(STORAGE_ID) || '[]');
        },

        put: function (weeksums) {
            localStorage.setItem(STORAGE_ID, JSON.stringify(weeksums));
        }
        
    };
    
});

app.controller('outputWeekSum', ['$scope', '$http', '$timeout', 'toaster','$filter', 'weekStorage',
  function($scope, $http, $timeout, toaster, $filter, weekStorage){
	
	$scope.remainingCount =0//  点击的选项量
	$scope.students = []//  本地数据
    $scope.dropdowncp = []
    $scope.dropdownspec = []
    
    var ids = []//存选中的学生ID
    
    $scope.checkAllBtn = function(){
    	this.checkAll = true
    	$scope.checkChange = 'info'
    }
    $scope.uncheckAllBtn = function(){
    	this.checkAll = false
    	$scope.checkChange = ''
    }

//    $http.get('/jsjxyxt/cla/getClaByTno.do').success(function(data){
//		$scope.dropdowncp = data.result
//	})
   
    //获得列表
    $http.get('/jsjxyxt/trainInfo/getByTno.do').success(function(data){
		var length = data.result.length
		for(var i = 0; i<length; i++){
			data.result[i].completed = false
		}
		$scope.students = data.result
	})

		$scope.cla={
    	     classes:''
		    }
//	 //根据下拉框获取学生
//    $scope.selectStu = function(){
//    	var data = {
//    		"cno": $scope.cla.classes.cno,
//    	}
//    	console.log(data);
//    	var url = '/jsjxyxt/trainInfo/get.do'
//    	$http.post(url,data).success(function(data){
//    		console.log(data.result)
//    		$timeout(function(){
//    			$scope.$apply(function(){
//    				$scope.students = data.result
//    			})
//    		})
//    	})
//    }

	

	
	
	
	//本地存储具体操作
	 
	 //观察数量如果为0
	 $scope.$watch('remainingCount == $scope.students.length', function (val) {
	        $scope.allChecked = val;
	    });
	   //点击
	$scope.todoCompleted = function (student) {
		if(student.completed){
//			ids.push({
//	            id: student.sno,
////	            completed: false
//	        });
//		   weekStorage.put(ids);
			ids.push(student.student.sno)
			$scope.nums=ids.join(",");
		}else{
			$scope.allChecked = false;
			var index = ids.indexOf(student.student.sno)
			ids.splice(index, 1)
			$scope.nums=ids.join(",");
		}
   
        $scope.remainingCount += student.completed ? 1 : -1;
        weekStorage.put($scope.students);

    };
    //导出选中

        //全选
    $scope.markAll = function (completed) {
        $scope.students.forEach(function (student) {
        	student.completed = completed;
            if(completed&&ids.indexOf(student.student.sno) == -1){
            	ids.push(student.student.sno)
            	
            }
            if(!completed){
            	ids =[]
            }
        });
    	$scope.nums=ids.join(",");
        $scope.remainingCount = !completed ? 0 : $scope.students.length;
      //  weekStorage.put($scope.students);
    };
	//----------本地存储结束
    
//    $scope.down=function(){
//    	var data={
//        		"snos":ids
//        }
//    	console.log(data)
//       $http.post("/jsjxyxt/importWeekwork/down.do", data).success(function(data){
//    	   console.log(1111111)
//       })
//    	
//    }
    
}]);

























