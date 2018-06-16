/** 定义品牌控制器层 */
/**注入service*/
app.controller('brandController',function ($scope,$controller, baseService) {
        //指定继承baseController
        $controller("baseController",{$scope:$scope});

        //分页查询品牌信息
        // 定义json对象封装查询条件
        $scope.searchEntity = { };
        $scope.search = function (page,rows) {
            baseService.findByPage("/brand/findAll",page,rows,$scope.searchEntity).then(function (res) {
                $scope.list = res.data.rows;
                //更新总记录数
                $scope.paginationConf.totalItems = res.data.total;
            },function (reason) {
                //错误走到这
                alert("操作错误!!");
            });
        };
        //*************************添加或者修改*********************************************
        $scope.saveOrUpdate = function () {
            var url = "save";
            if($scope.entity.id){
                url = "update";
            }
            //发送异步
            baseService.sendPost(/brand/+url,$scope.entity).then(function (res) {
                if(res.data){
                    $scope.reload();
                }else{
                    alert("添加失败");
                }
            });
        };
        //***********************修改页面回显*******************************
        $scope.show = function (entity) {
            //已json格式来解析.然后再转换为json
            $scope.entity = JSON.parse(JSON.stringify(entity));
            // $scope.entity = entity;
        };
        //************************批量删除**************************************

        $scope.delete = function () {
            //判断ids中有没有值，是否选中
            if($scope.ids.length>0) {
                baseService.deleteById("/brand/delete", $scope.ids).then(function (res) {
                    if (res.data) {
                        //重新加载品牌数据
                        $scope.reload();
                    }
                });
            }else{
                alert("请选择要删除的品牌!");
            }
        }
});