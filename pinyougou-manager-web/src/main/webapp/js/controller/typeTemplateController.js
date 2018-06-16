/** 定义控制器层 */
app.controller('typeTemplateController', function($scope, $controller, baseService){

    /** 指定继承baseController */
    $controller('baseController',{$scope:$scope});

    /** 定义搜索对象 */
    $scope.searchEntity = {};
    /** 分页查询 */
    $scope.search = function(page, rows){
        baseService.findByPage("/typeTemplate/findByPage", page, 
			rows, $scope.searchEntity)
            .then(function(response){
                $scope.dataList = response.data.rows;
                /** 更新总记录数 */
                $scope.paginationConf.totalItems = response.data.total;
            });
    };

    /** 添加或修改 */
    $scope.saveOrUpdate = function(){
        var url = "save";
        if ($scope.entity.id){
            url = "update";
        }
        /** 发送post请求 */
        baseService.sendPost("/typeTemplate/" + url, $scope.entity)
            .then(function(response){
                if (response.data){
                    /** 重新加载数据 */
                    $scope.reload();
                }else{
                    alert("操作失败！");
                }
            });
    };

    /** 显示修改 */
    $scope.show = function(entity){
        $scope.entity = JSON.parse(JSON.stringify(entity));
       //将品牌列表转换为json格式
        $scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
        //将规格列表转换为json格式
        $scope.entity.specIds = JSON.parse($scope.entity.specIds);

        //将扩展属性json字符串转换为json数组
        $scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
    };

    /** 批量删除 */
    $scope.delete = function(){
        if ($scope.ids.length > 0){
            baseService.deleteById("/typeTemplate/delete", $scope.ids)
                .then(function(response){
                    if (response.data){
                        $scope.reload();
                    }else{
                        alert("删除失败！");
                    }
                });
        }
    };

    //测试select2下拉列表 格式键固定{data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};
    //$scope.brandList = {data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};
    //查询所有品牌
    $scope.findBrandList = function () {
        baseService.sendGet("/brand/selectBrandList").then(function (res) {
            $scope.brandList = {data : res.data};
        });
    };
    //查询所有规格
    $scope.findSpecList = function () {
        baseService.sendGet("/specification/selectSpecList").then(function (res) {
            $scope.specList = {data : res.data};
        });
    };
    //添加一行
    $scope.addTableRow = function () {
        $scope.entity.customAttributeItems.push({ });
    };
    //删除一行
    $scope.deleTableRow = function (index) {
        $scope.entity.customAttributeItems.splice(index,1);
    };

});