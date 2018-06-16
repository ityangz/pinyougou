/** 定义公共的基础控制器层 */
/** 定义分页配置信息对象 */
app.controller("baseController", function ($scope) {
    //发送异步请求
    $scope.paginationConf = {
        currentPage: 1, // 当前页码
        totalItems: 0,  // 总记录数
        itemsPerPage: 10, // 每页显示的记录数   [每页大小]
        perPageOptions: [10, 20, 30], // 页码下拉列表框
        onChange: function () { // 改变事件
            $scope.reload(); //重新加载
        }
    };
//重新加载页表数据   传当前页和每页大小
    $scope.reload = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    //********************************************************************
    //定义选中的id数组
    $scope.ids = [];
    //为复选框添加绑定事件
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            //如果选中进入..将id的值push到定义的数组中
            $scope.ids.push(id);
        } else {
            //如果取消，则从数组中删除取消的id值
            $scope.ids.splice($scope.ids.indexOf(id), 1);
        }
    };

    //提取json某个属性,返回拼接字符串,逗号分隔
    $scope.json2Str = function (jsonStr,key) {
        //key :text
        var json = JSON.parse(jsonStr);
        var arr = new Array();
        for(var i = 0; i<json.length; i++){
            //alert(json[i][key]);   联想-->三星
            arr.push(json[i][key]);
        }
        return arr.join(",");
    };
});