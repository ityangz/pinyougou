/** 定义基础服务层*/
app.service('baseService',function ($http) {
    /**发送get请求（带请求参数获取不带请求参数）*/
    this.sendGet = function (url,data) {
        if(data){
           url = url + "?" + data;
        }
        return $http.get(url);
    };

    /**发送post请求(带请求参数或者不带请求参数)*/
    this.sendPost = function (url,data) {
        if(data){
            return $http.post(url,data);
        }
        return $http.post(url);
    };
    /**根据主键id查询*/
    this.findOne = function (url,id) {
        return this.sendGet(url,"id="+id);
    };
    /**分页查询(带条件或者不带条件查询)*/
    this.findByPage = function (url,page,rows,data) {
        /**定义分页url*/
        url = url +"?page="+page+"&rows="+rows;
        if(data && JSON.stringify(data) != "{ }"){
            return $http({
                method:'get',
                url:url,
                params:data
            });
        }else{
            return this.sendGet(url);
        }
    };
    /**删除或者批量删除*/
    this.deleteById = function (url,ids) {
        /**判断ids是否是数组*/
        if(ids instanceof Array){
            return this.sendGet(url,"ids="+ids);
        }else{
            return this.sendGet(url,"id="+ids);
        }
    };

});