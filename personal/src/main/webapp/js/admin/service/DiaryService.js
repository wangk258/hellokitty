/**
 * Created by ahpeng on 2016/6/29.
 */
define(function(){
    app.service("DiaryService",["$q","$http",function($q,$http){
        this.getlist = function(pageIndex){
            var deferred = $q.defer();
            $http.get("/diary/diaries.do?pageSize=15&currentPage="+pageIndex).success(function(data){
                deferred.resolve(data);
            }).error(function(){
                console.log(arguments);
            });
            return deferred.promise;
        };
        this.getDetailById = function(id){
            var deferred = $q.defer();
            if(id === 0){
                deferred.resolve({});
            }
            $http.get("/diary/single.do?id="+id).success(function(data){
                deferred.resolve(data);
            }).error(function(){
                console.log(arguments);
            });
            return deferred.promise;
        };
    }]);
});