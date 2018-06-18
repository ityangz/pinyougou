/**显示用户名*/
app.controller('indexController',function ($scope,baseService) {
    /** 读取当前用户名*/
    $scope.showLoginName = function () {
        baseService.sendGet("/login/username").then(function (res) {
           $scope.loginName =  res.data.loginName;
        });
    }
});