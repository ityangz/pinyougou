/** 定义控制器层 */
app.controller('sellerController', function ($scope, $controller, baseService) {

    /** 指定继承baseController */
    $controller('baseController', {$scope: $scope});

    /** 添加 */
    $scope.saveOrUpdate = function () {
        /** 发送post请求 */
        baseService.sendPost("/seller/save", $scope.seller)
            .then(function (response) {
                if (response.data) {
                    /** 重新加载数据 */
                    location.href = "/shoplogin.html";
                } else {
                    alert("操作失败！");
                }
            });
    };

});