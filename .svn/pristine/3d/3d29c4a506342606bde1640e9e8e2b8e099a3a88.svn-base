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

app.controller('practicePdf', ['$scope', '$http', '$timeout', 'toaster','$filter', 'weekStorage',
  function($scope, $http, $timeout, toaster, $filter, weekStorage){
//------------------------------------------------预览
	$scope.isSee = true
		$scope.showPdf= function(item){
		var url = '/jsjxyxt/shixunInfo/updatesxpdfState.do';
    	var data = {
				"sno": item.student.sno,
				"state":'2',
//				"cno": $scope.cla.classes.cno,
			}
		$http.post(url,data).success(function(data){
		    $scope.students = data.result
			var $pdf = $("#pdf");
			if(PDFObject.supportsPDFs){
			} else {
			}		
			if($scope.isSee){
				$pdf.show()
				$scope.isSee = !$scope.isSee
			}else{
				$pdf.hide()
				$scope.isSee = !$scope.isSee
			}
			var absUrl = (function(){
				var curWwwPath=window.document.location.href;
				var pathName=window.document.location.pathname;
				var pos=curWwwPath.indexOf(pathName);
			 	var localhostPaht=curWwwPath.substring(0,pos);
			    return localhostPaht+'/ms/file/upload/report/' +item.student.sno +".pdf";
			})()
			PDFObject.embed(absUrl, $pdf)
			
		})
			
	}

//	
//------------------------------------------------	
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
		$scope.cname=data.result[0].student.cla.cname;
		$scope.cno=data.result[0].student.cla.cno;
		$scope.students = data.result
		
	})

//		$scope.cla={
//    	     classes:''
//		    }
//	 //根据下拉框获取学生
//    $scope.selectStu = function(){
//    	var data = {
//    		"cname": $scope.cla.classes.cname,
//    	}
//    	var url = '/jsjxyxt/PracticeServlet?mode=getByClass'
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
			ids.push(student.studentBean.sno)
			$scope.nums=ids.join(",");
		}else{
			$scope.allChecked = false;
			var index = ids.indexOf(student.studentBean.sno)
			ids.splice(index, 1)
			$scope.nums=ids.join(",");
		}
   
        $scope.remainingCount += student.completed ? 1 : -1;
        weekStorage.put($scope.students);

    };

        //全选
    $scope.markAll = function (completed) {
        $scope.students.forEach(function (student) {
        	student.completed = completed;
            if(completed&&ids.indexOf(student.studentBean.sno) == -1){
            	ids.push(student.studentBean.sno)
            	
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
    $scope.news='';
    //实训报告状态的改变
    $scope.operate1=function(sno){//不合格
    	$scope.news=sno;
    	var url = '/jsjxyxt/shixunInfo/updatesxpdfState.do';
    	var data = {
				"sno": sno,
				"state":'3',
//				"cno": $scope.cla.classes.cno,
			}
       $http.post(url, data).success(function(data){
    	   $timeout(function(){
   			$scope.$apply(function(){
   				$scope.students = data.result
   			})
   		})
    	   toaster.pop('success', '系统提示',sno+'修改状态为不合格');
       })	
    }
    $scope.operate2=function(sno){//归档
    	$scope.news=sno;
    	var url = '/jsjxyxt/shixunInfo/updatesxpdfState.do';
    	var data = {
				"sno": sno,
				"state":'4',
//				"cno": $scope.cla.classes.cno,
			}
       $http.post(url, data).success(function(data){
    	   $scope.students = data.result
    	   toaster.pop('success', '系统提示',sno+'修改状态为归档');
       })	
    }
    $scope.operate3=function(sno){//撤销归档
    	$scope.news=sno;
    	var url = '/jsjxyxt/shixunInfo/updatesxpdfState.do';
    	var data = {
				"sno": sno,
				"state":'5',
//				"cno": $scope.cla.classes.cno,
			}
       $http.post(url, data).success(function(data){
    	   $scope.students = data.result
    	   toaster.pop('success', '系统提示',sno+'修改状态撤销归档');
       })	
    }
//    $scope.operate4=function(sno){//未提交
//    	console.log(sno);
//    	$scope.news=sno;
//    	var url = '/jsjxyxt/shixunInfo/updatesxpdfState.do';
//    	var data = {
//				"sno": sno,
//				"state":'0',
//				"time":'--',
////				"cno": $scope.cla.classes.cno,
//			}
//    	console.log(data)
//       $http.post(url, data).success(function(data){
//    	   $scope.students = data.result
//    	   toaster.pop('success', '系统提示',sno+'修改状态未提交');
//       })	
//    }

}]);

























