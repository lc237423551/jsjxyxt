/**
 * 
 */
app.controller('personalStu', ['$scope', '$filter', '$http', 'editableOptions', 'editableThemes', 'toaster',
  function($scope, $filter, $http, editableOptions, editableThemes, toaster){
    editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
    $scope.user = {
       
    }; 
    $scope.statuses = [
                       {value: '男'},
                       {value: '女'},
                     ];
 
    var url = '/jsjxyxt/student/getStudentInfo.do'
    $http.get(url).success(function(data){
    	$scope.user = data.result
    })
   
      $scope.showStatus = function(item) {
          if(item.ssex && $scope.statuses.length) {
            var selected = $filter('filter')($scope.statuses, {ssex:item.ssex})
            return selected.length ? selected[0].value : '未填写';
          }else{
          	return item.ssex || '未填写';
          } 
       };

    $scope.toaster = {
	            type: 'wait',
	            title: 'Title',
	            text: 'Message'
	        };
 
    $scope.saveUser = function(change, user) {
    	var url ='/jsjxyxt/student/update.do'
    	var data = {
    		    "sno":user.sno,
    			"ssex":user.ssex,
    			"sid":user.sid,
    			"semail": user.semail,
    			"sphone": user.sphone,
    			"sadress": user.sadress,
    			"sremark": user.sremark
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


}]);
