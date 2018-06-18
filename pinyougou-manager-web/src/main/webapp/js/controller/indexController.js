app.controller('indexController',function ($scope,baseService) {
    /**获取当前当前登录用户的用户名*/
    $scope.showLoginName = function () {
      baseService.sendGet("/login/username").then(function (res) {
          $scope.username = res.data.loginName;
      });
    };
});