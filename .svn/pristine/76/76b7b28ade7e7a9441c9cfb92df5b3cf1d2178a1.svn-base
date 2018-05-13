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
        , 
         
    };
    
});
app.controller('trainManagerLeader', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter', '$window', '$q' ,'weekStorage', 
                                 function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter, $window, $q, weekStorage){
	editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
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
    		//console.log('11')
    }
    
    $http.get('/jsjxyxt/cla/getClaByTno.do').success(function(data){
//    	console.log(data.result)
		$scope.dropdowncp = data.result
	})
	//教师下拉框
      $scope.teachers=[];
    $http.get('/jsjxyxt/teacher/getwithall.do').success(function(data){
//    	console.log(data.result);
    	$scope.teacherss= data.result;	
    })
     $http.get('/jsjxyxt/teacher/get.do').success(function(data){
    	$scope.teachers= data.result;	
    })
    //加载动态图控制
    $scope.wait=0;
    $scope.length=0;
    //获得列表weekStorage 
    $http.get('/jsjxyxt/trainInfo/get.do').success(function(data){
		var length = data.result.length
		for(var i = 0; i<length; i++){
			data.result[i].completed = false
		}
  
	})

		$scope.search={
    	     classes:'',
    	     teachers:''
		    }
	 //根据下拉框获取学生
    $scope.selectStu = function(){
    	 $scope.wait=1;
    	var data = {
    		"cno": $scope.search.classes.cno,
    		'tname':$scope.search.teachers.tname,
    		
    	}
    	var url = '/jsjxyxt/trainInfo/select.do'
    	$http.post(url,data).success(function(data){
    		$timeout(function(){
    			$scope.$apply(function(){
    				 $scope.wait=0;
    				$scope.students = data.result;
    				$scope.length=data.result.length
    				 $scope.remainingCount =0;
    			})
    		})
    	})
    }
//单个修改
    $scope.saveStu=function(change,sno){
    	var data={
    			'tno':change.tno,
    			'sno':sno,
    			"cno": $scope.search.classes.cno,
        		'tname':$scope.search.teachers.tname,
    	}
    	var url = '/jsjxyxt/trainInfo/teaToStu.do'
    	$http.post(url,data).success(function(data){
    		if(data.errmsg==0){
    			toaster.pop('error', '系统提示','该教师不存在');
    		}else{
    			toaster.pop('success', '系统提示','修改成功');
    		}
    		$timeout(function(){
    			$scope.$apply(function(){
    				$scope.students = data.result;
    				$scope.length=data.result.length;
    				$scope.remainingCount =0;
    			})
    		})
    	})
    	
    }
	

	
	
	
	//本地存储具体操作
	 
	 
	 $scope.$watch('remainingCount', function (val) {
		 	//console.log($scope.students)
		   // console.log('qft',val)
		 console.log(val)
//		 	$scope.remainingCount = $scope.students.length;
//		 	console.log($scope.remainingCount)
//	        $scope.allChecked = val;
	    });
	   //点击
	$scope.todoCompleted = function (student) {
		
		if(student.completed){


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
 
    $scope.sea={
    		teacher:''
    }
    //
    $scope.toteacher = function() {
    	$scope.isAlertShow = true;
        var remove = $scope.$on('clickOk',function(event, data){
        	 $scope.wait=1;
         	var url = '/jsjxyxt/trainInfo/toTeacher.do';
         	var data = {
     				"snos": ids,
     				"tno":$scope.sea.teacher.tno,
     				"cno": $scope.search.classes.cno,
     	    		'tname':$scope.search.teachers.tname,
     			}
         	$scope.sea.teacher=$scope.search.teachers
            $http.post(url, data).success(function(data){
         	   $scope.allChecked = false;
         	   $scope.wait=0;
         	   $scope.students = data.result;
         	   $scope.length=data.result.length
         	   toaster.pop('success', '系统提示','实训分配成功');
         	   $scope.remainingCount =0;
            })
            }
        )
         $scope.$on('clickCanel',function(event, data){   
        	    if(remove())
        	    remove = null;
                return;
            }
        )
         $scope.$on('clickOk',function(event, data){   
        	 if(remove())
        	 remove = null;
              return;
          })
    };
    
    //
        
  
    $scope.markAll = function(allCheck) {
    	allCheck?($scope.remainingCount=$scope.students.length):$scope.remainingCount=0;
    	$scope.students.forEach(function(val,index) {
    		if($scope.allChecked){
    			ids.push(val.sno)
    		}else {
    			ids = [];
    		}
    		val.completed = $scope.allChecked;
    	})

    }
}]);

























