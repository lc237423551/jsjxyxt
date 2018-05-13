
/**
 * 拖拽
 */


//------------单条录入(学生)-------------------------------------
  app.controller('ModalAddCtrl', ['$scope', '$modalInstance', '$location', 'students','$http','$timeout',function($scope, $modalInstance, $location, students,$http,$timeout) {
	  $scope.reset = function(form) {		
			 form.$setPristine();
		 }  
	//阻止冒泡，防止拖动输入框内容是，弹出框拖动
	    $scope.clearDown = function(event) {
	    		event.stopPropagation();
	    	}
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
    //保存学生
    $scope.stu={
    		sno:'',
    		sname:'',
    		ssex:'',
    		spec:'',
    		classess:''
    }
    $scope.closeModal = function(){
    	$modalInstance.close(students);
    	console.log(students)
    }
    $scope.saveStudent = function(form){
    	if(form.$invalid){
    		return;
    	}
    	var data = {
    			"sno":$scope.stu.sno,
    			"sname":$scope.stu.sname,
    			"ssex":$scope.stu.ssex,
    			"cno":$scope.stu.classes.cno,
    	}
    	var url = '/jsjxyxt/student/insert.do'
    	$http.post(url, data).success(function(data){
    		$modalInstance.close(data);
    	})
    };
  }]); 
  //（添加学生）模态框
  app.controller('ModalAdd', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location, toaster) {
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAdd.html',
        controller: 'ModalAddCtrl',
        size: size,
        resolve: {
          students: function () {
            return $scope.students;
          }
        }
      });
      modalInstance.result.then(function (data) {
          if(data.errmsg=='0'){
          	toaster.pop('error', '系统提示', '该账号存在');	
          	$scope.$parent.students = data.result;
          	$scope.$parent.newsInsert = data.newsInsert;
          }else if(data.errmsg=="1"){
          	toaster.pop('success', '系统提示', '添加成功');	
          	$scope.$parent.students = data.result;
          	$scope.$parent.newsInsert = data.newsInsert;
          }else{
          	$scope.$parent.students = data;
          	
          }
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
//------------单条录入(教师)-------------------------------------
app.controller('ModalAddTea', ['$scope', '$modal', '$log', '$location', 'toaster', function($scope, $modal, $log, $location, toaster) {
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddTea.html',
        controller: 'ModalAddTeaCtrl',
        size: size,
        resolve: {
          teachers: function () {
            return $scope.teachers;
          }
        }
      });
        modalInstance.result.then(function (data) {
        	if(data.errmsg=='0'){
            	toaster.pop('error', '系统提示', '教师存在');	
            	$scope.$parent.teachers = data.result;
            	$scope.$parent.newsInsert = data.newsInsert;
            }else if(data.errmsg=="1"){
            	toaster.pop('success', '系统提示', '保存成功');	
            	$scope.$parent.teachers = data.result;
            	$scope.$parent.newsInsert = data.newsInsert;
            }else{
            	$scope.$parent.teachers = data;
            }
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
 //模态框控制器（添加教师） 
app.controller('ModalAddTeaCtrl', ['$scope', '$modalInstance', '$location','$http','$timeout', 'teachers', function($scope, $modalInstance, $location, $http,$timeout, teachers) {
	$scope.reset = function(form) {		
		 form.$setPristine();
	 }
	
	//阻止冒泡，防止拖动输入框内容是，弹出框拖动
    $scope.clearDown = function(event) {
    		event.stopPropagation();
    	}
    
	$scope.dropdowndept = []
    $scope.zhchs=[];
    $scope.xws=[];
    $http.get('/jsjxyxt/sycode/getDept.do').success(function(data){
		$scope.dropdowndept = data.result
	})
	$http.get('/jsjxyxt/sycode/getPost.do').success(function(data){
		$scope.zhchs = data.result
	})
	$http.get('/jsjxyxt/sycode/getDegree.do').success(function(data){
		$scope.xws = data.result
	})
    //保存教师
    $scope.tea={
    		tno:'',
    		tname:'',
    		tsex:'',
    		dept:'',
    		post:'',
    		xw:'',
    }
    $scope.saveTea = function(form){
    	if(form.$invalid){
    		return;
    	}
    	var data = {
    			"tno":$scope.tea.tno,
    			"tname":$scope.tea.tname,
    			"tsex":$scope.tea.tsex,
    			"tdept":$scope.tea.dept.codecontent,
    			"tpost":$scope.tea.post.codecontent,
    			'tdegree':$scope.tea.xw.codecontent
    	}
    	var url="/jsjxyxt/teacher/insert.do"
    		$http.post(url, data).success(function(data){
        		$modalInstance.close(data);
        	})
    };
    $scope.closeModal = function(){
    	$modalInstance.close(teachers);
    }
  }]); 
//------------单条录入(专业)-------------------------------------
  app.controller('ModalAddSpecCtr', ['$scope','$modalInstance','$location', '$http', 'specs', function($scope, $modalInstance, $location, $http, specs) {
	  $scope.reset = function(form) {		
			 form.$setPristine();
		 }  
	  $scope.deptgroups = [];
	    $scope.teachers=[];
	    $scope.spec={
	    		specid:'',
	    		specname:'',
	    		teacher:'',
	    }
	  //阻止冒泡，防止拖动输入框内容是，弹出框拖动
	    $scope.clearDown = function(event) {
	    		event.stopPropagation();
	    	}
	  $http.get('/jsjxyxt/teacher/get.do').success(function(data){
		$scope.teachers= data.result;	
	})
	    //保存专业
	    $scope.saveSpec= function(form){
	    	if(form.$invalid){
	    		return;
	    	}
	    	var spec={
	    		"specid":$scope.spec.specid,
	    		"specname":$scope.spec.specname,
	    		"tno":$scope.spec.teacher.tno,
	    	}
	    	var url="/jsjxyxt//speciality/addSpeciality.do";
	    	$http.post(url,spec).success(function(data){
	    		$modalInstance.close(data);
	    	})
	    	
	    }
	    $scope.closeModal = function(){
	    	$modalInstance.close(specs);
	    }
	 
	  }]); 
	  
	  //（添加专业）模态框
	    app.controller('ModalAddSpec', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location,toaster) {
	    $scope.open = function (size) {
	      var modalInstance = $modal.open({
	        templateUrl: 'myModalAddSpec.html',
	        controller: 'ModalAddSpecCtr',
	        size: size,
	        resolve: {
	            specs: function () {
	              return $scope.specs;
	            }
	          }
	        });
	          modalInstance.result.then(function (data) {
	          	if(data.errmsg=='0'){
	              	toaster.pop('error', '系统提示', '专业存在');	
	              	$scope.$parent.specs = data.result;
	              }else if(data.errmsg=="1"){
	              	toaster.pop('success', '系统提示', '保存成功');	
	              	$scope.$parent.specs = data.result;
	              }else{
	              	$scope.$parent.specs = data;
	              }
	        }, function () {
	        $log.info('Modal dismissed at: ' + new Date());
	      });
	    };
	  }])
//------------单条录入(班级)-------------------------------------
	   //模态框控制器（添加班级）
  app.controller('ModalAddclassCtrl', ['$scope', '$modalInstance', '$location', 'classes','$http','$timeout', function($scope, $modalInstance, $location, classes,$http,$timeout) {
	 $scope.reset = function(form) {		
		 form.$setPristine();
	 }
	//阻止冒泡，防止拖动输入框内容是，弹出框拖动
	    $scope.clearDown = function(event) {
	    		event.stopPropagation();
	    	}
	  //班级和专业初始数据
    $scope.dropdowncp = []
    $scope.dropdownspec = []
    $scope.hasChooseSpec =true;
    $http.get('/jsjxyxt/speciality/get.do').success(function(data){
		$scope.dropdownspec = data.result
	})
	//教研室和教师初始数据
    $scope.teachers=[];
    $http.get('/jsjxyxt/teacher/get.do').success(function(data){
    	$scope.teachers= data.result;	
    })
    //保存班级
    $scope.c={
    		cname:'',
    		spec:'',
    		teacher:'',
    }
    $scope.saveClass = function(form){
    	if(form.$invalid){
    		return;
    	}
    	var data = {
    			"cname":$scope.c.cname,
    			"specid":$scope.c.spec.specid,
    			"tno":$scope.c.cteacher.tno,		
    	}
    	var url = '/jsjxyxt/cla/addCla.do'
    	$http.post(url, data).success(function(data){
    		$modalInstance.close(data);
    	})
    	
    };
	
    $scope.closeModal = function(){
    	$modalInstance.close(classes);
    }
  }])
  ; 
  //（添加）模态框
  app.controller('ModalAddclass', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location,toaster) {

    $scope.items = ['item1', 'item2', 'item3'];
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddclass.html',
        controller: 'ModalAddclassCtrl',
        size: size,
        resolve: {
          classes: function () {
            return $scope.classes;
          }
        }
      });
      modalInstance.result.then(function (data) {
        	if(data.errmsg=='0'){
            	toaster.pop('error', '系统提示', '班级存在');	
            	$scope.$parent.classes = data.result;
            }else if(data.errmsg=="1"){
            	toaster.pop('success', '系统提示', '保存成功');	
            	$scope.$parent.classes = data.result;
            }else{
            	$scope.$parent.classes = data;
            }
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
//------------单条录入(用户)-------------------------------------
    app.controller('ModalAddUserCtrl', ['$scope', '$modalInstance', '$location', 'uses', '$http','$timeout', 'toaster', function($scope, $modalInstance, $location, uses, $http, $timeout, toaster) {
    $scope.reset = function(form) {		//重置后验证回复
   		 form.$setPristine();
   	 }
    	$scope.user={
    		users:'',
    		username:'',
    		role:'管理员'
    }
    	
    	//阻止冒泡，防止拖动输入框内容是，弹出框拖动
    $scope.clearDown = function(event) {
    		event.stopPropagation();
    	}
    $scope.closeModal = function(){
    	$modalInstance.close(uses);
    }
    $scope.saveUser = function(form){
    	if(form.$invalid){
    		return;
    	}
    	var data = {
    			"users":$scope.user.users,
    			"username":$scope.user.username,
    			"role":$scope.user.role,
    	}
    	var url = '/jsjxyxt/user/insert.do'
    	$http.post(url, data).success(function(data){
    		if(data.errmsg=='0'){
            	toaster.pop('error', '系统提示', '该账号存在');	
            	$scope.$parent.uses = data.result;
            	$scope.$parent.newsInsert = data.newsInsert;
            	return ;
            }
    		$modalInstance.close(data);
    	})
    	
    };
  }])
  ; 
    
  app.controller('ModalAddUser', ['$scope', '$modal', '$log', '$location', 'toaster', function($scope, $modal, $log, $location ,toaster) {
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddUser.html',
        controller: 'ModalAddUserCtrl',
        size: size,
        resolve: {
        uses: function () {
            return $scope.uses;
          }
        }
      });

      modalInstance.result.then(function (data) {
        
         if(data.errmsg=="1"){
        	toaster.pop('success', '系统提示', '添加成功');	
        	$scope.$parent.uses = data.result;
        	$scope.$parent.newsInsert = data.newsInsert;
        }else{
        	$scope.$parent.uses = data;
        	
        }
        
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])   
 //------------单条录入(单位)-------------------------------------
   app.controller('ModalAddCompanyCtr', ['$scope','$modalInstance','$location','$http', 'units', function($scope, $modalInstance, $location, $http ,units) {
	   $scope.reset = function(form) {		
			 form.$setPristine();
		 }
	   $scope.company={
    		uname:'',
    		uadress:'',
    		uphone:'',
    		upeople:'',
    		ucity:'',
    		ustatus:'已审核'
    }
	   
	 //阻止冒泡，防止拖动输入框内容是，弹出框拖动
	    $scope.clearDown = function(event) {
	    		event.stopPropagation();
	    	}

    $scope.saveCompany= function(form){
    	if(form.$invalid){
    		return;
    	}
    	var company={
    		"uname":$scope.company.uname,
    		"uadress":$scope.company.uadress,
    		"uphone":$scope.company.uphone,
    		"upeople":$scope.company.upeople,
    		"ucity":$scope.company.ucity,
    	}
    	
    	var url="/jsjxyxt/unit/insert.do";
    	$http.post(url, company).success(function(data){
    		$modalInstance.close(data);
    	})
    }
    $scope.closeModal = function(){
    	$modalInstance.close(units);
    }
  }]); 
  
  //（添加公司）模态框
    app.controller('ModalAddCompany', ['$scope', '$modal', '$log', '$location', 'toaster', function($scope, $modal, $log, $location, toaster) {
    $scope.items = ['item1', 'item2', 'item3'];
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddCompany.html',
        controller: 'ModalAddCompanyCtr',
        size: size,
        resolve: {
            units: function () {
              return $scope.units;
            }
          
        }
      });
      modalInstance.result.then(function (data) {
      	if(data.errmsg=='0'){
          	toaster.pop('error', '系统提示', '该单位存在');	
          	$scope.$parent.units = data.result;
          }else if(data.errmsg=="1"){
          	toaster.pop('success', '系统提示', '保存成功');	
          	$scope.$parent.units = data.result;
          	$scope.$parent.newsInsert = data.newsInsert;
          }else{
          	$scope.$parent.units = data;
          }
    }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
  
  
//------------单条录入(公告)-------------------------------------
   app.controller('ModalAddAdviceCtrl', ['$scope','$modalInstance','$location','$http', 'advices','FileUploader', function($scope, $modalInstance, $location, $http ,advices,FileUploader) {
	   $scope.reset = function(form) {		
			 form.$setPristine();
		 }
	   $scope.advice={
			title:'',
    		content:''
	   }	   
	 //阻止冒泡，防止拖动输入框内容是，弹出框拖动
	    $scope.clearDown = function(event) {
	    		event.stopPropagation();
	    	}
	   
    $scope.saveAdvice= function(form){
    	if(form.$invalid){
    		return;
    	}
    	var advice={
    		"title":$scope.advice.title,
    		"content":$scope.advice.content
    	}
    	console.log(advice)
    	var url="/jsjxyxt/advice/insert.do";
    	$http.post(url, advice).success(function(data){
    		$modalInstance.close(data);
    	})
    }

    
    //上传文件
    $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
      };
      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
      $scope.isShow=false;
      var uploader = $scope.uploader = new FileUploader({
    	  url: '/jsjxyxt/advice/insert.do'
      });
      uploader.filters.push({
          name: 'queueLimit',
          fn: function(item /*{File|FileLikeObject}*/, options) {
              return this.queue.length < 2;
          }
      });
      var isXlsx = true
  	uploader.filters.push({
          name: 'isRar',
          fn: function(item /*{File|FileLikeObject}*/, options) {
          	var arr = item.name.split('.')
          	if(arr[arr.length-1] == 'rar'){
          		isRar = true
          	}else{
          		isRar = false
          		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
          	}
              return arr[arr.length-1] == 'rar';
          }
      })

      uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
      	
      };
      uploader.onSuccessItem = function(fileItem, response, status, headers) {
//          console.info('onSuccessItem', fileItem, response, status, headers);
//          $scope.advices = response
//          advices = response.result
//          
//          $modalInstance.close(response);
    	  toaster.pop("success", "成功信息", "上传成功");
      };
      uploader.onErrorItem = function(fileItem, response, status, headers) {
          console.info('onErrorItem', fileItem, response, status, headers);
      };
      uploader.onCancelItem = function(fileItem, response, status, headers) {
          console.info('onCancelItem', fileItem, response, status, headers);
      };
      uploader.onCompleteItem = function(fileItem, response, status, headers) {
          console.info('onCompleteItem', fileItem, response, status, headers);
          $modalInstance.close(response);

      };
    
    
    
    $scope.closeModal = function(){
    	$modalInstance.close(advices);
    }
  }]); 
  
  //（添加公告）模态框
    app.controller('ModalAddAdvice', ['$scope', '$modal', '$log', '$location', 'toaster', function($scope, $modal, $log, $location, toaster) {
//    $scope.items = ['item1', 'item2', 'item3'];
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddAdvice.html',
        controller: 'ModalAddAdviceCtrl',
        size: size,
        resolve: {
        	advices: function () {
              return $scope.advices;
            }
        }
      });
      modalInstance.result.then(function (data) {
      	if(data.errmsg=='0'){
          	toaster.pop('error', '系统提示', '错误');	
          	$scope.$parent.advices = data.result;
          }else if(data.errmsg=="1"){
          	toaster.pop('success', '系统提示', '保存成功');	
          	$scope.$parent.advices = data.result;
          	$scope.$parent.newsInsert = data.newsInsert;
          }else{
          	$scope.$parent.advices = data;
          }
    }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
  
  
  
  //------------编辑公告-------------------------------------
   app.controller('ModalAddAdviceeditCtrl', ['$scope','$modalInstance','$location','$http', 'advices','toaster','$timeout','advice_id','content','FileUploader', function($scope, $modalInstance, $location, $http ,advices,toaster,$timeout,advice_id,content,FileUploader) {
	    $scope.reset = function(form) {		
			 form.$setPristine();
		 }
	    
	   $scope.advice={
		advice_id:'',
		content: content,
   		filename:'',
	   }
	 //阻止冒泡，防止拖动输入框内容是，弹出框拖动s
	    $scope.clearDown = function(event) {
	    		event.stopPropagation();
	    	}
	  //更新公告
    $scope.UpdateAdvice= function(form){
        	var advice = {
        			"advice_id":advice_id,
        			"content": $scope.advice.content,
        		}
    	if(form.$invalid){
    		return;
    	}
    	
    	var url="/jsjxyxt/advice/update.do";
    	$http.post(url, advice).success(function(data){
    		$modalInstance.close(data);
//    		toaster.pop('success', '系统提示','修改成功');
//			 $timeout(function(){
//	    			$scope.$apply(function(){
//	    				$scope.advice = data.result;
//	    			})
//	    		});
    	})
    } 
    $scope.closeModal = function(){
    	$modalInstance.close(advices);
    }
  }]); 
  
  //（编辑公告）模态框
    app.controller('ModalAddAdviceedit', ['$scope', '$modal', '$log', '$location', 'toaster','$timeout',function($scope, $modal, $log, $location, toaster) {

    $scope.open = function (size,advice_id,content) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddAdviceedit.html',
        controller: 'ModalAddAdviceeditCtrl',
        size: size,
        resolve: {
        	advices: function () {
              return $scope.advices;
            },
            advice_id:function(){
            	return advice_id;
            },
            content:function(){
            	return content;
            }
        }
      });
      modalInstance.result.then(function (data) {
        	if(data.errmsg=='0'){
            	toaster.pop('error', '系统提示', '错误');	
            	$scope.$parent.advices = data.result;
            }else if(data.errmsg=="1"){
            	toaster.pop('success', '系统提示', '修改成功');	
            	$scope.$parent.advices = data.result;
            	$scope.$parent.newsInsert = data.newsInsert;
            }else{
            	$scope.$parent.advices = data;
            }
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
  //------------公告上传附件-------------------------------------
 app.controller('ModalAdviceUploadCtrl', ['$scope', '$modalInstance', '$location', 'advices', 'FileUploader', '$timeout','toaster','filename', function($scope, $modalInstance, $location, advices, FileUploader, $timeout,toaster,filename) {
	 	
	$scope.closeModal = function(){
    	$modalInstance.close(advices);
    }
	 $scope.ok = function () {
	        $modalInstance.close($scope.selected.item);
	      };
	      $scope.cancel = function () {
	        $modalInstance.dismiss('cancel');
	      };
	      $scope.isShow=false;
	      var uploader = $scope.uploader = new FileUploader({
	    	  url: '/jsjxyxt/uploadAdvice/advicefile.do',
	    	  formData:{
	    		  filename:'filename'
	    	  }
	      });
	      uploader.filters.push({
	          name: 'queueLimit',
	          fn: function(item /*{File|FileLikeObject}*/, options) {
	              return this.queue.length < 2;
	          }
	      });
	      var isRar = true
	  	uploader.filters.push({
	          name: 'isRar',
	          fn: function(item /*{File|FileLikeObject}*/, options) {
	          	var arr = item.name.split('.')
	          	if(arr[arr.length-1] == 'rar'){
	          		isRar = true
	          	}else{
	          		isRar = false
	          		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
	          	}
	              return arr[arr.length-1] == 'rar';
	          }
	      })

	      uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
	      	
	      };
	      uploader.onSuccessItem = function(fileItem, response, status, headers) {
//	          console.info('onSuccessItem', fileItem, response, status, headers);
//	          $scope.advices = response
//	          advices = response.result
//	          
//	          $modalInstance.close(response);
	    	  toaster.pop("success", "成功信息", "上传成功");
	      };
	      uploader.onErrorItem = function(fileItem, response, status, headers) {
	          console.info('onErrorItem', fileItem, response, status, headers);
	      };
	      uploader.onCancelItem = function(fileItem, response, status, headers) {
	          console.info('onCancelItem', fileItem, response, status, headers);
	      };
	      uploader.onCompleteItem = function(fileItem, response, status, headers) {
	          console.info('onCompleteItem', fileItem, response, status, headers);
	          $modalInstance.close(response);

	      };
    console.info('uploader', uploader);
  }])
    //上传附件模态框
  app.controller('ModalAdviceUpload', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location,toaster) {
	
    $scope.open = function (size,filename) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddAdviceUpload.html',
        controller: 'ModalAdviceUploadCtrl',
        size: size,
        resolve: {
        	advices: function () {
            return $scope.advices;
          },
          filename:function(){
        	  return filename;
          }
        }
      });
      modalInstance.result.then(function (data) {
        	if(data.errmsg=='0'){
            	toaster.pop('error', '系统提示', '添加失败');	
            	$scope.$parent.students = data.result;
            }else if(data.errmsg=="1"){
            	toaster.pop('success', '系统提示', '保存成功');	
            	$scope.$parent.students = data.result;
            	$scope.$parent.succ = true;
            }else{
            	$scope.$parent.students = data;
            }
      }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        })
	}
  }])
  
  
  
 
  
//------------批量录入(学生)-------------------------------------
 app.controller('ModalUploadCtr', ['$scope', '$modalInstance', '$location', 'students', 'FileUploader', '$timeout','toaster', function($scope, $modalInstance, $location, students, FileUploader, $timeout,toaster) {
	$scope.closeModal = function(){
    	$modalInstance.close(students);
    }
    $scope.ok = function () {
      $modalInstance.close($scope.selected.item);
    };
    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    $scope.isShow=false;
    var uploader = $scope.uploader = new FileUploader({
        url: '/jsjxyxt/uploadInfo/student.do'
    });
    uploader.filters.push({
        name: 'queueLimit',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            return this.queue.length < 2;
        }
    });
    var isXlsx = true
	uploader.filters.push({
        name: 'isExcel',
        fn: function(item /*{File|FileLikeObject}*/, options) {
        	var arr = item.name.split('.')
        	if(arr[arr.length-1] == 'xlsx'){
        		 isXlsx = true
        	}else{
        		isXlsx = false
        		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
        	}
            return arr[arr.length-1] == 'xlsx';
        }
    })

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
    	
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
        $scope.students = response
        students = response.result
        
        $modalInstance.close(response);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
        $modalInstance.close(response);

    };
    console.info('uploader', uploader);
  }])
    //上传模态框
  app.controller('ModalUpload', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location,toaster) {
	
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalUpload.html',
        controller: 'ModalUploadCtr',
        size: size,
        resolve: {
          students: function () {
            return $scope.students;
          }
        }
      });
      modalInstance.result.then(function (data) {
        	if(data.errmsg=='0'){
            	toaster.pop('error', '系统提示', '添加失败');	
            	$scope.$parent.students = data.result;
            }else if(data.errmsg=="1"){
            	toaster.pop('success', '系统提示', '保存成功');	
            	$scope.$parent.students = data.result;
            	$scope.$parent.succ = true;
            }else{
            	$scope.$parent.students = data;
            }
      }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        })
	}
  }])
  
//------------批量录入(教师)-------------------------------------  
  app.controller('ModalUploadCtrTea', ['$scope', '$modalInstance', '$location', 'teachers', 'FileUploader', '$timeout', function($scope, $modalInstance, $location, teachers, FileUploader, $timeout) {
      $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
      };
      
      
      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
      //上传
      var uploader = $scope.uploader = new FileUploader({
          url: '/jsjxyxt/uploadInfo/teacher.do'
      });
      uploader.filters.push({
          name: 'queueLimit',
          fn: function(item /*{File|FileLikeObject}*/, options) {
              return this.queue.length < 2;
          }
      });
      var isXlsx = true
  	uploader.filters.push({
          name: 'isExcel',
          fn: function(item /*{File|FileLikeObject}*/, options) {
          	var arr = item.name.split('.')
          	console.log(arr[arr.length-1])
          	if(arr[arr.length-1] == 'xlsx'){
          		 isXlsx = true
          	}else{
          		isXlsx = false
          		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
          	}
              return arr[arr.length-1] == 'xlsx';
          }
      })
      $scope.closeModal = function(){
      	$modalInstance.close(teachers);
      }
      // CALLBACKS
      uploader.onSuccessItem = function(fileItem, response, status, headers) {
          console.info('onSuccessItem', fileItem, response, status, headers);
          $scope.teachers = response
          teachers = response.result
          $modalInstance.close(response);
          
      };
      uploader.onErrorItem = function(fileItem, response, status, headers) {
          console.info('onErrorItem', fileItem, response, status, headers);
      };
      uploader.onCancelItem = function(fileItem, response, status, headers) {
          console.info('onCancelItem', fileItem, response, status, headers);
      };
      uploader.onCompleteItem = function(fileItem, response, status, headers) {
          console.info('onCompleteItem', fileItem, response, status, headers);
          $modalInstance.close(response);
      };
      console.info('uploader', uploader);
    }])

      //上传教师模态框
    app.controller('ModalUploadTea', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location, toaster) {
  	console.log(console.log($location.url()))
      $scope.items = ['item1', 'item2', 'item3'];
      $scope.open = function (size) {
        var modalInstance = $modal.open({
          templateUrl: 'myModalUploadTea.html',
          controller: 'ModalUploadCtrTea',
          size: size,
          resolve: {
              teachers: function () {
                return $scope.teachers;
              }
            }
          });
          modalInstance.result.then(function (data) {
            	if(data.errmsg=='0'){
                	toaster.pop('error', '系统提示', '添加失败');	
                	$scope.$parent.teachers = data.result;
                }else if(data.errmsg=="1"){
                	toaster.pop('success', '系统提示', '保存成功');	
                	$scope.$parent.teachers = data.result;
                }else{
                	$scope.$parent.teachers = data;
                }
          }, function () {
            $log.info('Modal dismissed at: ' + new Date());
          })
      };
    }]) 
      
//批量添加（单位）------------------------------------------------------------------------------------------------------------------------
      app.controller('ModalUploadCom', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location,toaster) {
    	console.log(console.log($location.url()))
        $scope.items = ['item1', 'item2', 'item3'];
        $scope.open = function (size) {
          var modalInstance = $modal.open({
            templateUrl: 'myModalUploadCom.html',
            controller: 'ModalUploadCtrCom',
            size: size,
            resolve: {
              units: function () {
                return $scope.units;
              }
            }
          });
          modalInstance.result.then(function (data) {
            	if(data.errmsg=='0'){
                	toaster.pop('error', '系统提示', '保存失败');	
                	$scope.$parent.units = data.result;
                }else if(data.errmsg=="1"){
                	toaster.pop('success', '系统提示', '保存成功');	
                	$scope.$parent.units = data.result;
                }else{
                	$scope.$parent.units = data;
                }
          }, function () {
              $log.info('Modal dismissed at: ' + new Date());
            })
        };
      }])
      //（上传单位文件）模态框控制器
      
      app.controller('ModalUploadCtrCom', ['$scope', '$modalInstance', '$location', 'units', 'FileUploader', '$timeout', function($scope, $modalInstance, $location, units, FileUploader, $timeout) {
    	  $scope.cancel = function () {
    	        $modalInstance.dismiss('cancel');
    	      };
    	      //上传
    	      var uploader = $scope.uploader = new FileUploader({
    	          url: '/jsjxyxt/uploadInfo/unit.do'
    	      });
    	      uploader.filters.push({
    	          name: 'queueLimit',
    	          fn: function(item /*{File|FileLikeObject}*/, options) {
    	              return this.queue.length < 2;
    	          }
    	      });
    	      var isXlsx = true
    	  	uploader.filters.push({
    	          name: 'isExcel',
    	          fn: function(item /*{File|FileLikeObject}*/, options) {
    	          	console.log(item.name)
    	          	var arr = item.name.split('.')
    	          	console.log(options)
    	          	console.log(arr[arr.length-1])
    	          	if(arr[arr.length-1] == 'xlsx'){
    	          		 isXlsx = true
    	          	}else{
    	          		isXlsx = false
    	          		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
    	          	}
    	              return arr[arr.length-1] == 'xlsx';
    	          }
    	      })
    	      $scope.closeModal = function(){
    	      	$modalInstance.close(units);
    	      }
    	      // CALLBACKS
    	      uploader.onSuccessItem = function(fileItem, response, status, headers) {
    	          console.info('onSuccessItem', fileItem, response, status, headers);
    	          $scope.units = response
    	          units = response.result
    	          $modalInstance.close(response);
    	          
    	      };
    	      uploader.onErrorItem = function(fileItem, response, status, headers) {
    	          console.info('onErrorItem', fileItem, response, status, headers);
    	      };
    	      uploader.onCancelItem = function(fileItem, response, status, headers) {
    	          console.info('onCancelItem', fileItem, response, status, headers);
    	      };
    	      uploader.onCompleteItem = function(fileItem, response, status, headers) {
    	          console.info('onCompleteItem', fileItem, response, status, headers);
    	          $modalInstance.close(response);
    	      };

        console.info('uploader', uploader);
      }])
      
//----------------------------------------------实习公司人数详情
  
  //模态框控制器（实习公司人数详情）
  
  app.controller('ModalStuDetailCtr', ['$scope', '$modalInstance', '$location', 'uid','$http','$timeout', function($scope, $modalInstance, $location, uid, $http, $timeout) {
	  $http.get('/jsjxyxt/trainInfo/getByUid.do', {params: {uid: uid}}).success(function(data){
		  $scope.students = data.result;
		  $scope.number = data.result.length;
	  })
	  $scope.closeModal = function(){
	    	$modalInstance.close();
	    }
  }])
  ; 
  //（实习公司人数详情）模态框
  app.controller('ModalStuDetail', ['$scope', '$modal', '$log', '$location', function($scope, $modal, $log, $location) {
    $scope.open = function (size,id) {
      var modalInstance = $modal.open({
        templateUrl: 'stuDetail.html',
        controller: 'ModalStuDetailCtr',
        size: size,
        resolve: {
          uid: function () {
            return id;
          }
        }
      });

      modalInstance.result.then(function (data) {
        $scope.$parent.students = data;
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
 //实训题目下的学生
  
  
   app.controller('ModalStuDetailCtrsub', ['$scope', '$modalInstance', '$location', 'id','$http','$timeout', function($scope, $modalInstance, $location, id, $http, $timeout) {
	  $http.get('/jsjxyxt/subjectname/getBySubject.do', {params: {id: id}}).success(function(data){
		  $scope.subjects = data.result;
		  $scope.num = data.num;
	  })
	  $scope.closeModal = function(){
	    	$modalInstance.close();
	    }
  }])
  ; 
  app.controller('ModalStuDetailsub', ['$scope', '$modal', '$log', '$location', function($scope, $modal, $log, $location) {
    $scope.open = function (size,id) {
      var modalInstance = $modal.open({
        templateUrl: 'stuTaskDetail.html',
        controller: 'ModalStuDetailCtrsub',
        size: size,
        resolve: {
          id: function () {
            return id;
          }
        }
      });

      modalInstance.result.then(function (data) {
        $scope.$parent.subjects = data;
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
  //实训题目添加学生
   app.controller('subjectstuCrt', ['$scope', '$modalInstance', '$location', 'id','$http','$timeout', 'toaster', function($scope, $modalInstance, $location, id, $http, $timeout, toaster) {
	  $scope.trainStu = [];//实训学生数组;负责存储学生，再删除添加时从这里面拿值负责展示
	  $scope.trainStuL = [];//左边用于显示的学生数组(中间数据，负责临时存储)
	  $scope.trainStuR = [];//右边添加的学生数组(中间数据，负责临时存储)
	  $scope.showRightStu = [];//负责展示右边学生(负责展示)
	   $http.get('/jsjxyxt/trainInfo/get.do').success(function(data){
		  $scope.subjects = data.result;
		  $scope.num = data.num;
		  var students = [].slice.call($scope.subjects)
		  students.forEach(function(value,index) {
			  var data = {
					  'sname': value.student.sname,
					  'index': index,
					  'id': value.student.sno
			  }
			  $scope.trainStu[data.index] = data;
			  $scope.trainStuL[data.index] = data;
		  })
//		  console.log($scope.trainStu)
	  })
	  
	  //选中学生
	  
	  $scope.checkStu = function(event, item) {	
//		   console.log(event)
		   var index = item.index;
//		   var id = item.id;
//		   console.log(index)
		   //正则验证是否选中，切换状态
		   var patterninfo = /bg-info/;
		   var patternwhite = /bg-white/
		   teststr = ''+event.currentTarget.attributes.class.nodeValue;
		   if(patterninfo.test(teststr)) {//如果取消选中
			   $scope.trainStuL[index] = $scope.trainStu[index]; 
			   delete $scope.trainStuR[index];
			   
			  var newteststr =  teststr.replace(/bg-info/,'bg-white');
			   event.currentTarget.attributes.class.nodeValue = newteststr;			   
		   }else if (patternwhite.test(teststr)){//如果再次选中
			   $scope.trainStuR[index] = $scope.trainStuL[index];
			   delete $scope.trainStuL[index]
			   
			   var newteststr =  teststr.replace(/bg-white/,'bg-info');
			   event.currentTarget.attributes.class.nodeValue = newteststr;	
		   } else {
			   $scope.trainStuR[index] = $scope.trainStuL[index];//添加到右边的
			   delete $scope.trainStuL[index];//再把zuo边的删除
//			   console.log($scope.trainStuR)
			   var newteststr =  teststr.replace(/bg-white/,'bg-info');
			   event.currentTarget.attributes.class.nodeValue += ' bg-info' ;
		   }

	  }
	  
	   //将选中学生放入右边，同时更新左边数组
	   $scope.addStuToRight = function() {
		   var _trainStuL = [];
		   var _trainStuR = [];
		   for(var i=0;i<$scope.trainStuL.length;i++){
			    if(typeof($scope.trainStuL[i])!='undefined'){
			    	_trainStuL.push($scope.trainStuL[i]);
			    	
			    }
			}
		   for(var i=0;i<$scope.trainStuR.length;i++){
			    if(typeof($scope.trainStuR[i])!='undefined'){
			    	_trainStuR.push($scope.trainStuR[i]);
			    	
			    }
			}
		   $scope.trainStu = _trainStuL;
		   $scope.showRightStu = _trainStuR;
//		   console.log($scope.trainStu)
		   
	   }
	   
	   //撤销
	   $scope.cancelToLeft = function(index) {			
		   //进行增删
		   $scope.trainStuL[index] = $scope.trainStuR[index]
		   delete $scope.trainStuR[index];
		   console.log($scope.trainStuL)
		   console.log($scope.trainStuR)
		   //格式化
		   var _trainStuL = [];
		   var _trainStuR = [];
		   for(var i=0;i<$scope.trainStuL.length;i++){
			    if(typeof($scope.trainStuL[i])!='undefined'){
			    	_trainStuL.push($scope.trainStuL[i]);
			    	
			    }
			}
		   for(var i=0;i<$scope.trainStuR.length;i++){
			    if(typeof($scope.trainStuR[i])!='undefined'){
			    	_trainStuR.push($scope.trainStuR[i]);
			    	
			    }
			}
		   //展示
//		   console.log(_trainStuL)
		   $scope.trainStu = _trainStuL
		   $scope.showRightStu = _trainStuR
	   }
	   
	   
	  $scope.id=id;
	  //
	  //添加 
	  $scope.saveStu = function(){
		  var _showRightStu = []
		  var right = [].slice.call($scope.showRightStu);
		  right.forEach(function(value, index){
			  _showRightStu.push(value.id)
		  })
		  var data={
				  'snos':_showRightStu,
				  'subjectid':id,
		  }
	    	console.log(data)
	    	var url="/jsjxyxt/trainInfo/toStu.do";
	    	$http.post(url,data).success(function(data){
	    		toaster.pop('success', '系统提示', '添加成功');	
	    		$modalInstance.close();
	    	})
	    	
	    }
	  
	  
	  
	  $scope.closeModal = function(){
	    	$modalInstance.close();
	    }
  }])
  ; 
  app.controller('subjectstu', ['$scope', '$modal', '$log', '$location', function($scope, $modal, $log, $location) {
    $scope.open = function (size,id) {
      var modalInstance = $modal.open({
        templateUrl: 'subjectstu.html',
        controller: 'subjectstuCrt',
        size: size,
        resolve: {
          id: function () {
            return id;
          }
        }
      });

      modalInstance.result.then(function (data) {
        $scope.$parent.subjects = data;
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  
  
  
  
  
  
  
  
  
  
  
  //------------添加题目-------------------------------------
  app.controller('ModalAddSubjectCtr', ['$scope', '$modalInstance', '$location', 'subjects', '$http', '$timeout',function($scope, $modalInstance, $location, subjects,$http,$timeout) {
	 
	   //初始题目
	    $scope.subject={
	    		name: '',
	    		teacher: ''
	    }

	    $scope.teachers=[];
	    $http.get('/jsjxyxt/teacher/get.do').success(function(data){
	    	console.log(data.result);
	    	$scope.teachers= data.result;	
	    })
	    //保存题目
	    $scope.saveSubject= function(form){
	    	if(form.$invalid){
	    		return;
	    	}
	    	var subj={
	    		"name": $scope.subject.name,
	    		"tno": $scope.subject.teacher.tno,
	    	}
	    	console.log(subj)
	    	var url="/jsjxyxt/subjectname/save.do";
	    	$http.post(url,subj).success(function(data){
	    		$modalInstance.close(data);
	    	})
	    	
	    }
	    $scope.closeModal = function(){
	    	$modalInstance.close(subjects);
	    }
  }]); 
  //（添加）模态框
  app.controller('ModalAddSubject', ['$scope', '$modal', '$log', '$location','toaster', function($scope, $modal, $log, $location, toaster) {
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalAddSubject.html',
        controller: 'ModalAddSubjectCtr',
        size: size,
        resolve: {
        	subjects: function () {
            return $scope.subjects;
          }
        }
      });
      modalInstance.result.then(function (data) {
          if(data.errmsg=="1"){
          	toaster.pop('success', '系统提示', '添加成功');	
          	$scope.$parent.subjects = data.result;
          }else{
          	$scope.$parent.subjects = data;
          	
          }
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  //-------------查询模态框
    app.controller('ModalSearchCtr', ['$scope', '$modalInstance', '$location', 'sname','$http','$timeout', function($scope, $modalInstance, $location, sname, $http, $timeout) {
	 console.log(sname)
	 var data={
		 sname:sname,
	 }
    	$http.post('/jsjxyxt/trainInfo/search.do', data).success(function(data){
		console.log(data.result);
		$scope.sname=sname;
		$scope.students=data.result;
	  })
	  $scope.closeModal = function(){
	    	$modalInstance.close();
	    }
  }])
  ; 
  app.controller('ModalSearch', ['$scope', '$modal', '$log', '$location', function($scope, $modal, $log, $location) {
    $scope.open = function (size,sname) {
      var modalInstance = $modal.open({
        templateUrl: 'search.html',
        controller: 'ModalSearchCtr',
        size: size,
        resolve: {
        	sname: function () {
            return sname;
          }
        }
      });

      modalInstance.result.then(function (data) {
        $scope.$parent.subjects = data;
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  //-------------查询模态框
        
//填写撤销理由
    app.controller('BackModalCtrl1', ['$scope', '$modalInstance', 'vid','toaster','$http','$timeout', function($scope, $modalInstance, vid,toaster,$http,$timeout) {
    	$scope.write={
    			v_backreason:""	
    	}
    	var data={
		   		 "v_id":vid,
		   	 }
	$http.post("/jsjxyxt/apply/findById.do",data).then(function(data){
		console.log(data.data.result.v_backreason)
		$scope.write.v_backreason=data.data.result.v_backreason
		 
		
	},function(e){
		toaster.pop('error','失败',e);
	});
    
    	
    	
    	
    $scope.ok = function () {
    	var url="/jsjxyxt/apply/backreason.do";
    	  var data={
    		   		 "v_id":vid,
    		   		 "v_backreason":$scope.write.v_backreason
    		   	 }
    	
    	$http.post(url,data).then(function(data){
    		toaster.pop('success','','成功');
    		$modalInstance.close();
//    			$http.get('/jsjxyxt/apply/getapply.do').success(function(data){
//    				console.log(data.result	)
//    				$modalInstance.close(data.result);
//				})
		},function(e){
			toaster.pop('error','失败',e);
		});
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('BackModalCtrl', ['$scope', '$modal', '$log','$timeout','$http', function($scope, $modal, $log, $timeout,$http) {
	  $scope.open = function (size,vid) {
	      var modalInstance = $modal.open({
	        templateUrl: 'backreason.html',
	        controller: 'BackModalCtrl1',
	        vid: vid,
	        resolve: {
	        	vid: function () {
	            return vid;
	          }
	        }
	      });

	      modalInstance.result.then(function (data) {
////	    	  console.log($scope.$parent.vacates,data)
////	          	$timeout(function(){
////	          		 $scope.$apply(function() {  
////	          			$scope.$parent.vacates = [1,2,3,4,5];
////	                   });  
////	          	},2000)
////	    	  $http.get('/jsjxyxt/apply/getapply.do').success(function(data){
////	    			$scope.$parent.vacates =data.result;
////				})
//	    	  console.log( $scope.$parent.vacates)
//	    	  $scope.$parent.vacates=[]
	      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])

  