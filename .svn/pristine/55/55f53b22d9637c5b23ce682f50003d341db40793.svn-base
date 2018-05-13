app.controller('applyStateTeacher', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter','$q',
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter,$q){
	editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    $http.get('/jsjxyxt/apply/getapplybytno.do').success(function(data){
		$scope.vacates = data.result
	})
	//******************搜索
	$scope.search={};
	$scope.status='5';
    $scope.selectInfo=function(data){
    	var searchsearch={
    			"v_status":$scope.status,
    			"specname":'全部',
    			"cname":'全部'
    	}
    	if($scope.search.searchclass!=undefined){
    		searchsearch.cname=$scope.search.searchclass.cname
    	}
    	if($scope.search.searchspec!=undefined){
    		searchsearch.specname=$scope.search.searchspec.specname
    	}
    	$http.post('/jsjxyxt/apply/getsearchtno.do',searchsearch).success(function(data){
    		$scope.vacates = data.result	
    	})
    }
    //班级和专业初始数据
    $scope.dropdowncp = []
    $scope.dropdownspec = []
    $scope.hasChooseSpec =true;//在没选择专业前禁止选择班级
    $http.get('/jsjxyxt/speciality/getwithAll.do').success(function(data){
		$scope.dropdownspec = data.result;
	})
    
    //专业下拉框值改变
    $scope.changeSpec = function(spec){
    	
    	var data={
    			"specid":spec.specid
    	}
    	$http.post('/jsjxyxt/cla/getwithAll.do',data).success(function(data){
    		$scope.dropdowncp = data.result
//    		console.log(data.result)
    		
    	})
    	
    	$scope.hasChooseSpec = false;   	
    }
	
    //***************更新
	$scope.updateStatu=function(data,code){
    	if(code==3){
    		data.v_status=3;
    		var dat={
    				"v_id":data.v_id,
    				"v_status":3
    		}
    		$http.post('/jsjxyxt/apply/teaupdatestatus.do', dat).success(function(data){

    		})
    		if(data.unit.ustatus=='未审核'){
    			toaster.pop('warning', '系统提示','此学生实习公司未通过审核');
    		}
    	}else{
    		data.v_status=1;
    		data.v_backreason="1"
    		var dat={
    				"v_id":data.v_id,
    				"v_status":1
    		}
    		$http.post('/jsjxyxt/apply/teaupdatestatus.do', dat).success(function(data){
    		})
    	}
    	
    }
}]);