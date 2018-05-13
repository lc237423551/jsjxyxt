/**
 * 
 */

app.controller('trainSubject', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter','$q',
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter,$q){
	editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    $scope.person = {};
    //获取所有题目信息信息
    $http.get('/jsjxyxt/subjectname/get.do').success(function(data){
    	$scope.subjects= data.result;
    	
    })
    $scope.statuses = [
                       {value: '已审核',name:'已审核'},
                       {value: '未审核',name:'未审核'},
                     ];
    $scope.statuses1 = [
                       {value: '校内',name:'校内'},
                       {value: '校外',name:'校外'},
                     ];
  //审核下拉框
    $scope.showStatus = function(item) {
          if(item.sub_state && $scope.statuses.length) {
            var selected = $filter('filter')($scope.statuses, {ustatus:item.sub_state})
            return selected.length ? selected[0].value : '';
          }else{
          	return item.sub_state || '';
          } 
       };
       //状态下拉框
       $scope.showStatus1 = function(item) {
             if(item.sub_state1 && $scope.statuses1.length) {
               var selected = $filter('filter')($scope.statuses1, {ustatus:item.sub_state1})
               return selected.length ? selected[0].value : '';
             }else{
             	return item.sub_state1 || '';
             } 
          };
     //修改题目和指导教师
     $scope.saveUser = function(change, id) {
     	var url ='/jsjxyxt/subjectname/update2.do'
     	var data = {
     			"id": id,
     			"name": change.name,
     			"tno": change.tno,
     			"sub_state":change.sub_state,
     			"sub_state1":change.sub_state1
     		}
     	$http.post(url, data).success(function(data){
     		if(data.errmsg=="0"){
     			toaster.pop('error', '系统提示','教师编号不存在');
     			$timeout(function(){
        			$scope.$apply(function(){
        				$scope.subjects = data.result;
        			})
        		})
     			
     		}else{
     			toaster.pop('success', '系统提示','修改成功');
     			$timeout(function(){
        			$scope.$apply(function(){
        				$scope.subjects = data.result;
        			})
        		})
     		}
 			
     })
    }
    /////
//      
//// 删除题目
   
    $scope.removeSubject = function(id) {
    	$scope.isAlertShow = true;
        var remove = $scope.$on('clickOk',function(event, data){
        	 $scope.wait=1;
        		var url = '/jsjxyxt/subjectname/delete.do',
	    		data = {
	    			"id": id
	    		}
        		$http.post(url,data).success(function(data){
		    		if(data.errmsg=="0"){
		    			toaster.pop('error', '删除失败', '该题目正在被使用');
		    		}else{
		    		    toaster.pop('success', '系统提示','删除成功');   		
		    		$timeout(function(){
		    			$scope.$apply(function(){
		    				$scope.subjects= data.result;
		    			})
		    		})
		    		
		    		}    		
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

}]);
