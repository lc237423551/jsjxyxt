/**
 * 实训公司情况管理
 */
app.controller('checkCompany', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter', '$window', 'FileUploader','$q' ,
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter, $window, FileUploader,$q){
	editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    var ids = [];// 选中公司集合
    $scope.remainingCount =0//  点击的选项量
	  $scope.search1={
				place:'',
				companyname:''
			}
	//得到公司信息
	$http.get('/jsjxyxt/unit/getByStatus.do?uname=').success(function(data){
		$scope.units = data.result;
		for(var i = 0; i < $scope.nums; i++){
			data.result[i].completed = false
		}
	})
	$scope.select=function(){
		var data={
				"uname":$scope.uname,
		}
		var url="/jsjxyxt/unit/getByStatus.do";
		$http.post(url, data).success(function(data){
			 $scope.remainingCount =0;
      	   $scope.allChecked = false;
    		$scope.units = data.result;
    	})
	}
	 
 
	   
	 
		 
	 //点击checkbox选择公司
	     $scope.todoCompleted = function (company) {
	 		
	 		if(company.completed){
	 			ids.push(company.uid)
	 			
	 		}else{
	 			$scope.allChecked = false;
	 			var index = ids.indexOf(company.uid)
	 			ids.splice(index, 1)
	 			
	 		}
	    
	         $scope.remainingCount += company.completed ? 1 : -1;
	     };
	     //全选
	     $scope.markAll = function(allCheck) {
	     	allCheck?($scope.remainingCount=$scope.units.length):$scope.remainingCount=0;
	     	$scope.units.forEach(function(val,index) {
	     		if($scope.allChecked){
	     			ids.push(val.uid)
	     		}else {
	     			ids = [];
	     		}
	     		val.completed = $scope.allChecked;
	     	})

	     }
	     
	     $scope.update=function(){
	    	 var url = '/jsjxyxt/unit/updateStatus.do';
	        	var data = {
	    				"uids": ids,
	    			}
	        	console.log(data)
	           $http.post(url, data).success(function(data){
	        	  
	        	   $scope.units = data.result;
	        	   toaster.pop('success', '系统提示','审核成功');
	        	   $scope.remainingCount =0;
	        	   $scope.allChecked = false;
	           })
	     }
	
}])