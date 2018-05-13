//我的周总结
app.controller('NoteCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {
//  $http.get('js/app/note/notes.json').then(function (resp) {
//    $scope.notes = resp.data.notes;
//    // set default note
//    $scope.note = $scope.notes[0];
//    $scope.notes[0].selected = true;
//  
//  });
//改变列表点击颜色
  $scope.bg = []
  var index = parseInt($location.search().week)
  $scope.bg[index-1] = 'bg-list'
  console.log($scope.bg)
  
  $scope.colors = ['primary', 'info', 'success', 'warning', 'danger', 'dark'];
//  $scope.weeks = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14'
//                 , '15', '16', '17', '18'];
  $scope.weeks = [
              {week: '1', canWrite: "dark", canLook: "false"},
              {week: '2', canWrite: "dark", canLook: "false"},
              {week: '3', canWrite: "dark", canLook: "false"},
              {week: '4', canWrite: "dark", canLook: "false"},
              {week: '5', canWrite: "dark", canLook: "false"},
              {week: '6', canWrite: "dark", canLook: "false"},
              {week: '7', canWrite: "dark", canLook: "false"},
              {week: '8', canWrite: "dark", canLook: "false"},
              {week: '9', canWrite: "dark", canLook: "false"},
              {week: '10', canWrite: "dark", canLook: "false"},
              {week: '11', canWrite: "dark", canLook: "false"},
              {week: '12', canWrite: "dark", canLook: "false"},
              {week: '13', canWrite: "dark", canLook: "false"},
              {week: '14', canWrite: "dark", canLook: "false"},
              {week: '15', canWrite: "dark", canLook: "false"},
              {week: '16', canWrite: "dark", canLook: "false"},
              {week: '17', canWrite: "dark", canLook: "false"},
              {week: '18', canWrite: "dark", canLook: "false"},
              
       ]
  var length = $scope.weeks.length
//		  console.log($scope.weeks)
  $scope.selectNote = function(index){
	  $scope.bg = []
      $scope.bg[index] = 'bg-list';
  }

  //获取可编辑周
  $http.get('/jsjxyxt/weekwork/getweeknum.do').success(function(data){
	    $scope.canWriteWeek = data.weeknums.join(',');
	    $scope.tab = data.tab;
	    console.log(data.tab)
		data.weeknums.forEach(function(value){
			
			for(var i = 0; i<length; i++){
				if($scope.weeks[i].week == value){
					$scope.weeks[i].canWrite = "info"
				    return 
				}
			}
		})
	})
}]);









