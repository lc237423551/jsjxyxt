app.controller('applyStateSpecMaster', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter','$q',
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter,$q){
	editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    
    $http.get('/jsjxyxt/apply/getapply.do').success(function(data){
		$scope.vacates = data.result
	})
	
	
	
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
    	$http.post('/jsjxyxt/apply/getsearchmaster.do',searchsearch).success(function(data){
    		$scope.vacates = data.result	
    	})
    }
    //班级和专业初始数据
    $scope.dropdowncp = []
    $scope.dropdownspec = []
    $scope.hasChooseSpec =true;//在没选择专业前禁止选择班级
    $http.get('/jsjxyxt/speciality/getSpecialityByTno.do').success(function(data){
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
	
	//更新状态
    $scope.updateStatu=function(data,code){
    	if(code==4){
    		data.v_status=code;
    		var dat={
    				"v_id":data.v_id,
    				"v_status":4,
    				"v_strattime":data.v_strattime,
    				"v_endtime":data.v_endtime,
    				"companyid":data.unit.uid,
    				"pa_internname":data.practiceappli.pa_internname,
    				"ifgraduate":data.practiceappli.ifgraduate,
    				"sno":data.sno,
    				"sub_id":data.sub_id
    		}
    		if(data.unit.ustatus=='未审核'){
    			toaster.pop('warning', '系统提示','此学生实习公司未通过审核');
    		}
    		$http.post('/jsjxyxt/apply/dirupdatestatus.do', dat).success(function(data){
    			toaster.pop('success', '系统提示','审核通过');
    		})
    	}else{
    		data.v_status=code;
    		data.v_backreason="1"
    		var dat={
    				"v_id":data.v_id,
    				"v_status":1,
    				"v_strattime":data.v_strattime,
    				"v_endtime":data.v_endtime,
    				"companyid":data.unit.uid,
    				"pa_internname":data.practiceappli.pa_internname,
    				"ifgraduate":data.practiceappli.ifgraduate,
    				"sno":data.sno
    		}

    		$http.post('/jsjxyxt/apply/dirupdatestatus.do', dat).success(function(data){
    			toaster.pop('success', '系统提示','撤销成功');
    		})
    	}
    	
    }
    //验证通过时间
   $scope.inspecTime=function(data){

	   var nowdate=new Date();
	   var v_endtime=new Date(data);
	   if(nowdate.getTime() > v_endtime.getTime()){

	        return false;
	    } else {
	       return true;
	    }

   }
}]);