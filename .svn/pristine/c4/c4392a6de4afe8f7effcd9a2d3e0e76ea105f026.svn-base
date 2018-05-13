/**
设置实训指导教师
 * 
 */
app.factory('todoStorage', function () {
    var STORAGE_ID = 'todos';

    return {
        get: function () {
            return JSON.parse(localStorage.getItem(STORAGE_ID) || '[]');
        },

        put: function (todos) {
            localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
        }
    };
});
app.controller('managerTrainLeader',['$scope', '$http','todoStorage', function($scope, $http, todoStorage){
	//本地存储选取
	var todos = $scope.todos = todoStorage.get();
	$scope.todoCompleted = function (todo) {
        $scope.remainingCount += todo.completed ? -1 : 1;
        todoStorage.put(todos);
    };
	//打开界面获取到所有的信息
	$http.get('').success(function(data){
		$scope.students = data.result;
	})
	//删除功能
	var ids = []//存选中的学生ID
	//删除按钮
	$scope.deleteStu = function(){
		var data = {
				"students": ids
			}
		var url = "http://localhost:8080/jsjxy/StudentServlet?mode=DeleteStudent"
		$http.post(url,data).success(function(data){
			$timeout(function(){
				$scope.$apply(function(){
					$scope.students = data.result;
				})
			})
		})
	}
	$scope.CheckChangeColor = function($event,id){
		//console.log(id)
		//console.log($event)
		var checkbox = $event.target;
		var action = (checkbox.checked?'bg-black':'');
		this.checkChange = action;
		//判断是否有改元素id，并是否被选中
		if(ids.indexOf(id) == -1&& checkbox.checked){
			ids.push(id);
			console.log(ids)
		}
		if(!checkbox.checked){
			console.log(!checkbox.checked)
			ids.splice(ids.indexOf(id),1);
		}
//		console.log(action)
//		console.log(checkbox.attributes.value.value)
		
//		if(checkbox.checked){
//			$scope.checkChange =action;
//		}
//		
	}
	$scope.markAll = function (completed) {
        todos.forEach(function (todo) {
            todo.completed = completed;
        });
        $scope.remainingCount = !completed ? todos.length : 0;
        todoStorage.put(todos);
    };
}])











