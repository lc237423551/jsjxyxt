/**
 * 
 */
app.controller('mydoc', ['$scope', '$http', '$location', 'FileUploader', 'toaster', function($scope, $http, $location, FileUploader, toaster ){
	var uploader = $scope.uploader = new FileUploader({
        url: '/jsjxyxt/upload/reportpdf.do'
    });
	var flag_pdf = false;
	var flag_rar_zip_war = false;
	uploader.filters.push({
        name: 'queueLimit',
        fn: function(item /*{File|FileLikeObject}*/, options) {
        	
        	var arr = item.name.split('.')
        	if(arr[arr.length-1] == 'pdf' || arr[arr.length-1] == 'zip' || arr[arr.length-1] == 'rar' || arr[arr.length-1] == 'war'){
        		
        		if(arr[arr.length-1] == 'pdf') {
        			flag_pdf = true;
        		}else {
        			flag_rar_zip_war = true;
        		}
        		
        		if(this.queue.length == 0 && (flag_pdf || flag_rar_zip_war)) {
        			
        			return true;
        		}
        		
        		if(this.queue.length == 1) {
        			console.log(flag_pdf && flag_rar_zip_war)
        			if(!(flag_pdf && flag_rar_zip_war)){
        				toaster.pop("error", "失败信息", "上传不符合要求，请注意下方提示");
        			}
            		return flag_pdf && flag_rar_zip_war;
            	}
        		
        	}else {
        		if(this.queue.length > 2){
        			toaster.pop("error", "失败信息", "上传不符合要求，请注意下方提示");
        		}else {
        			toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
        		}
        		
        	}
        	
        	
            
        }
    });
	var isXlsx = true
//	uploader.filters.push({
//        name: 'isPdf',
//        fn: function(item /*{File|FileLikeObject}*/, options) {
//        	var arr = item.name.split('.')
//        	if(arr[arr.length-1] == 'pdf'){
//        		 isXlsx = true
//        	}else{
//        		isXlsx = false
//        		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
//        	}
//            return arr[arr.length-1] == 'pdf';
//        }
//    })
//    uploader.filters.push({
//        name: 'isRarOrZip',
//        fn: function(item /*{File|FileLikeObject}*/, options) {
//        	var arr = item.name.split('.')
//        	if(arr[arr.length-1] == 'rar' || arr[arr.length-1] == 'zip'){
//        		 isXlsx = true
//        	}else{
//        		isXlsx = false
//        		toaster.pop("error", "失败信息", "文件类型不符合，请注意下方提示");
//        	}
//            return arr[arr.length-1] == 'rar' || arr[arr.length-1] == 'zip';
//        }
//    })
    //提交的上传显示标志
    $scope.isShow=true;
	$scope.show=true;
    
    // CALLBACKS
	uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
    			toaster.pop("success", "成功信息", "上传成功");
    			$scope.status="已提交，可重复提交"
    			$scope.time=response.time;
				$scope.isShow=true;
			    $scope.show=true;
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
    };
    
    var url="/jsjxyxt/shixunInfo/getBySession.do";
	 $http.get(url).success(function(data){
			$scope.time=data.result.sxpdfTime;
			if(data.result.sxpdfState=="0"){
 				$scope.status="未提交,可重复提交"
 				$scope.isShow=true;
 				$scope.show=false;
 			}else if(data.result.sxpdfState=="1"){
 				$scope.status="已提交，可重复提交"
 	 				$scope.isShow=true;
 				    $scope.show=true;
 	 		}else if(data.result.sxpdfState=="2"){
 				$scope.status="老师已预览，禁止提交"
 				$scope.isShow=false;
 			}else if(data.result.sxpdfState=="3"){
 				$scope.status="不合格，请重新提交"
 					$scope.isShow=true;
 			}else if(data.result.sxpdfState=="4"){
 				$scope.status="已归档"
 				$scope.isShow=false;
 			}else if(data.result.sxpdfState=="5"){
 				$scope.status="撤销归档，重新提交"
 					$scope.isShow=true;
 	 		}
			
		})
}])