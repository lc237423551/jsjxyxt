
app.controller('managerStu', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter', '$window', 'FileUploader', '$q', 
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter, $window, FileUploader, $q){
    editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    $scope.newsInsert = "0";
  //获取所有学生
    $http.get('/jsjxyxt/student/get.do').success(function(data){
    	$scope.students = data.result;
    	$scope.nums=data.nums
    })

  //班级和专业初始数据
    $scope.dropdowncp = []
    $scope.dropdownspec = []
    $scope.hasChooseSpec =true;//在没选择专业前禁止选择班级
    $http.get('/jsjxyxt/speciality/get.do').success(function(data){
		$scope.dropdownspec = data.result
	})
    
    //专业下拉框值改变
    $scope.changeSpec = function(spec){
    	var data={
    			"specid":spec.specid
    	}
    	$http.post('/jsjxyxt/cla/get.do',data).success(function(data){
    		$scope.dropdowncp = data.result
    	})
    	$scope.hasChooseSpec = false;   	
    }
    $scope.cla={
    		cname:'',
    }
    //新加入的学生的标记
    //根据下拉框获取学生
    $scope.selectStu = function(){
    	var data = {
    		"cno": $scope.cla.classes.cno, 
    	}
    	var url = '/jsjxyxt/student/get.do'
    	$http.post(url,data).success(function(data){
    		$scope.nums=data.nums    		
    		$timeout(function(){
    			$scope.$apply(function(){
    				$scope.students = data.result
    				
    			})
    		})
    		console.log($scope.students)
    	})
    }
    
    //-------------表格--------------------------//
    $scope.specgroups = []
    $scope.loadspecGroups = function(){
    	return $scope.specgroups.length ? null: $http.get('/jsjxyxt/speciality/get.do').success(function(data){
    		$scope.specgroups = data.result
    	})
    }
    $scope.classgroups = []
    $scope.loadclassGroups = function(){
    	return $scope.classgroups.length ? null: $http.get('/jsjxyxt/cla/get.do').success(function(data){
    		$scope.classgroups = data.result
    	})
    }
    
    $scope.showspecGroup = function(item) {	
        if(item.cla.speciality.specname && $scope.specgroups.length) {
          var selected = $filter('filter')($scope.specgroups, {specname: item.cla.speciality.specname})
          return selected.length ? selected[0].specname : 'Not set';
        }else{
        	return item.cla.speciality.specname || 'Not set';
        } 
     };
     $scope.showclassGroup = function(item) {
    	 if(item.cla.cname && $scope.classgroups.length) {
           var selected = $filter('filter')($scope.classgroups, {cname: item.cla.cname})
           return selected.length ? selected[0].cname : item.cla.cname;
         }else{
         	return item.cla.cname || item.cla.cname;
         }     
     };
      //
    $scope.changeSpeces = function(specname){
    	var data = {
    		"specname": specname,
    	}
    	var url = '/jsjxyxt/cla/getBySpecname.do';
    	$http.post(url, data).success(function(data){
    		$timeout(function(){
    			$scope.$apply(function(){
    				$scope.classgroups = data.result
    			})
    		})
    	})
    }  
      //修改 ——————————————————————————————————————————————————————————————————————————————————————————————————————
    $scope.saveUser = function(change, user, $event) {
    	var url ='/jsjxyxt/student/update.do'
    	var data = {
    			"sname": change.sname,
    			"sno": user,
    			"cname": change.cname,
    			"sphone": change.sphone,
    			"semail":change.semail,
    		}
    	$http.post(url, data).success(function(data){
    		toaster.pop("success","系统提示","修改成功");	
    	})
    }
    // 删除 ——————————————————————————————————————————————————————————————————————————————————————————————————————
    
    $scope.removeUser = function(sno) {
    	$scope.isAlertShow = true;
        var remove = $scope.$on('clickOk',function(event, data){
        	 var url='/jsjxyxt/student/delete.do';
        	 var data={
        			 "sno": sno
     		}
		    	$http.post(url,data).success(function(data){
		    		if(data.errmsg==0){
		    			toaster.pop("error","系统提示","删除失败");
		    		}else{
		    			toaster.pop("success","系统提示","删除成功");
		    		}
		    		$timeout(function(){
		    			$scope.$apply(function(){
		    				$scope.students = data.result;
		    			})
		    		})
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
    
 

  
    //搜索——————————————————————————————————————————————————————————————————————————————————————————————————————
    $scope.selectUser = function() {
    	var url = '/jsjxyxt/student/search.do',
		data = {
			"search": $scope.search
		}
    	$http.post(url,data).success(function(data){
    		if(data.errmsg=="0"){
    			$scope.students = null;
    			$scope.stuNum = data.result.length;
    			toaster.pop("warning", "系统提示", "未查找到记录");
    			$scope.nums=0;
    		}else{
    			$scope.students = data.result;
    			toaster.pop("success", "系统提示", data.line+'条记录');
    			$scope.nums=data.line;
    		}
    	})
   };
   
   //重置密码——————————————————————————————————————————————————————————————————————————————————————————————————————
   $scope.reset=function(users){
	   var data={
			   'users':users
	   }
		$http.post('/jsjxyxt/user/resetPassword.do',data).success(function(data){
			toaster.pop('success', '成功','密码初始化成功');
		}) 
   }
   //弹窗--------------------------------------------------------------------
   $scope.isAlertShow = false
   

}]);
