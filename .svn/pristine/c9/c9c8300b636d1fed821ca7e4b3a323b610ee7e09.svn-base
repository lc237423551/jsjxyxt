
app.controller('managerTea', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter','$q',
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter, $q){
    editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    
    $scope.newsInsert='0';
    $http.get('/jsjxyxt/teacher/get.do').success(function(data){
    	$scope.teachers = data.result;
    	
    })
    $scope.deptgroups = []
    $scope.loaddeptGroups = function(){
    	return $scope.deptgroups.length ? null: $http.get('/jsjxyxt/sycode/getDept.do').success(function(data){
    		$scope.deptgroups = data.result
    	})
    }
    

      
    $scope.showdeptGroup = function(item) {
       if(item.codecontent && $scope.deptgroups.length) {
          var selected = $filter('filter')($scope.deptgroups, {tdept: item.codecontent})   
          //console.log(selected.length+" A"+selected[0].tdept);
          return selected.length ? selected[0].codecontent : 'Not set';
       }else{
      //	console.log(1111)
        	return item.tdept || 'Not set';
       } 
     };
     
    $scope.saveUser = function(change, user) {
    	var url ='/jsjxyxt/teacher/update.do'
    	var data = {
    			"tname": change.tname,
    			"tno": user,
    			"tdept": change.tdept,
    			"tphone": change.tphone,
    		}
    	$http.post(url, data).success(function(data){
			    $scope.toaster = {
			            type: 'success',
			            title: '系统提示',
			            text: '修改成功'
			        };
    		toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
    	})
    }

    //删除
    $scope.removeUser = function(tno) {
    	$scope.isAlertShow = true;
        var remove = $scope.$on('clickOk',function(event, data){
        	var url = '/jsjxyxt/teacher/delete.do';
        	 var data={
        			 "tno": tno
     		}
		    	$http.post(url,data).success(function(data){
		    		if(data.errmsg==0){
		    			toaster.pop("error","系统提示","删除失败");
		    		}else{
		    			toaster.pop("success","系统提示","删除成功");
		    		}
		    		
		    		$timeout(function(){
		    			$scope.$apply(function(){
		    				$scope.teachers = data.result;
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
    
    

    $scope.selectUser = function() {
    	var url = '/jsjxyxt/teacher/search.do',
		data = {
			"search": $scope.search
		}
	$http.post(url,data).success(function(data){
		if(data.errmsg=="0"){
			$scope.teachers = data.result;
			toaster.pop("warning", "系统提示", "未查找到记录");
		}else{
			$scope.teachers = data.result;
			toaster.pop("success", "系统提示", data.line+'条记录');
		}
	})
   };   
   $scope.reset=function(users){
	   var data={
			   'users':users
	   }
		$http.post('/jsjxyxt/user/resetPassword.do',data).success(function(data){
			toaster.pop('success', '成功','密码初始化成功');
		}) 
   }


}]);