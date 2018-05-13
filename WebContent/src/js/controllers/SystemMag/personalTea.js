/**
 * 
 */
/**
 * 
 */
app.controller('personalTea', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes', 'toaster',
  function($scope, $filter, $http, editableOptions, editableThemes, toaster){
    editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    $scope.user = {
        	
    }; 
    var url = '/jsjxyxt/teacher/getTeacherInfo.do'
    $http.get(url).success(function(data){
    	$scope.user = data.result
    })
   // 获取学位，教研室 ， 职称下拉框
    $scope.xws=[];
    $scope.depts=[];
    $scope.zhchs=[];
    $scope.statuses = [
                       {value: '男'},
                       {value: '女'},
                     ];
    //获取学位
    var url='/jsjxyxt/sycode/getDegree.do';
    $http.get(url).success(function(data){
    	$scope.xws = data.result
    })
    //获得教研室
     var url='/jsjxyxt/sycode/getDept.do';
    $http.get(url).success(function(data){
    	$scope.depts = data.result
    })
    //获得职称
    var url='/jsjxyxt/sycode/getPost.do';
    $http.get(url).success(function(data){
    	$scope.zhchs = data.result
    })
    //性别下拉框
    $scope.showStatus = function(item) {
          if(item.ssex && $scope.statuses.length) {
            var selected = $filter('filter')($scope.statuses, {ssex:item.ssex})
            return selected.length ? selected[0].value : '未填写';
          }else{
          	return item.ssex || '未填写';
          } 
       };
       //教研室下拉框
       $scope.showDepts = function(item) {
             if(item.tdept && $scope.depts.length) {
               var selected = $filter('filter')($scope.depts, {tdept:item.tdept})
               return selected.length ? selected[0].codecontent : '未填写';
             }else{
             	return item.tdept || '未填写';
             } 
          };
       
    //职称下拉框
          $scope.showZhchs = function(item) {
              if(item.tpost && $scope.zhchs.length) {
                var selected = $filter('filter')($scope.zhchs, {tdept:item.tpost})
                return selected.length ? selected[0].codecontent : '未填写';
              }else{
              	return item.tpost || '未填写';
              } 
           };  
     //学位下拉框
           $scope.showXws = function(item) {
               if(item.xw && $scope.xws.length) {
                 var selected = $filter('filter')($scope.xws, {tdept:item.xw})
                 return selected.length ? selected[0].codecontent : '未填写';
               }else{
               	return item.xw || '未填写';
               } 
            };  
    
    $scope.toaster = {
	            type: 'wait',
	            title: 'Title',
	            text: 'Message'
	        };
 
    $scope.saveUser = function(change, user) {
    	var url ='/jsjxyxt/teacher/updateByTeacher.do'
    	var data = {
    			"tno":user.tno,
    			"tsex": user.tsex,
    			"tdept": user.tdept,
    			"tpost":user.tpost,
    			"tdegree":user.tdegree,
    			"tstudy": user.tstudy,
    			"temail": user.temail,
    			"tphone": user.tphone,
    			"tremark": user.tremark
    		}
    	$http.post(url, data).success(function(data){
			    $scope.toaster = {
			            type: 'success',
			            title: '成功提示',
			            text: '修改成功'
			        };
    		toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
    	})
    };
    //初始化密码
	$scope.resetUser=function(users){
		var data={
				"users":users
		}
		var url='/jsjxyxt/UserServlet?mode=ResetPassword';
		$http.post(url,data).success(function(data){
			toaster.pop('success', '成功', '密码初始化成功');	
	    })
	    
	}


}]);
