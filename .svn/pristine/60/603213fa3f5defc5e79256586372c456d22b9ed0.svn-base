app.controller('applyCounselor', ['$scope', '$filter', '$http', '$timeout', 'editableOptions', 'editableThemes', 'toaster','$filter','$q',
  function($scope, $filter, $http, $timeout, editableOptions, editableThemes, toaster, $filter,$q){
	editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    $http.get('/jsjxyxt/apply/getapplycou.do').success(function(data){
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
    	$http.post('/jsjxyxt/apply/getsearchcou.do',searchsearch).success(function(data){
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

    		
    	})
    	
    	$scope.hasChooseSpec = false;   	
    }
    
 
    //**************************更新公司
	$scope.updateStatu=function(data){
		
		if(data.unit.ustatus=='未审核'){
			toaster.pop('warning', '系统提示','此学生实习公司未通过审核不可通过，请首先去审核公司');
		}else{
			if(data.v_status==1){
	    		data.v_status=2;
	    		var dat={
	    				"v_id":data.v_id,
	    				"v_status":2
	    		}
	    		$http.post('/jsjxyxt/apply/couupdatecou.do', dat).success(function(data){

	    		})
	    		
	    	}else{
	    		data.v_status=1;
	    		data.v_backreason="1";
	    		var dat={
	    				"v_id":data.v_id,
	    				"v_status":1
	    		}
	    		$http.post('/jsjxyxt/apply/teaupdatestatus.do', dat).success(function(data){
	    		})
	    	}			
		} 	
    }
	//--------------公司修改编辑
	 $scope.companygroups = []
	 $scope.loadcompanyGroups = function() {
	      return $scope.companygroups.length ? null : $http.get('/jsjxyxt/unit/get.do').success(function(data) {
	        $scope.companygroups = data.result;	        
	      });
	 };	    
	    $scope.showcompanyGroup = function(item) {	
	        if(item.unit.uname && $scope.companygroups.length) {
	          var selected = $filter('filter')($scope.companygroups, {uname: item.unit.uname})
	          return selected.length ? selected[0].uname : ' ';
	        }else{
	        	return item.unit.uname || ' ';
	        } 
	     };
	     $scope.saveUser = function(data, id,uid) {
	         var companydata={
	        		 "uname":data.uname,
	        		 "pa_id":id,
	        		 "uid":uid
	         }
	         $http.post('/jsjxyxt/apply/couupdateunit.do', companydata).success(function(data){
	        	 if(data.message==0){
	    			}else{
	    				$scope.vacates = data.result
	    				//重新获取下拉框中的值
	    				$http.get('/jsjxyxt/unit/get.do').success(function(data) {
	    			        $scope.companygroups = data.result;	        
	    			      });
	    			}
	    		})
	    };   
    
}])