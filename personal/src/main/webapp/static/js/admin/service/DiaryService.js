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
                throwError(arguments);
            });
            return deferred.promise;
        };
        this.getDetailById = function(id){
            var deferred = $q.defer();
            if(id - 0 === 0){
                deferred.resolve({});
            }
            $http.get("/diary/single.do?id="+id).success(function(data){
                deferred.resolve(data);
            }).error(function(){
                throwError(arguments);
            });
            return deferred.promise;
        };
        this.save = function(diary){
            var deferred = $q.defer();
            if(diary){
                $http.post("/diary/saveOrUpdate.do",diary).success(function(data){
                    deferred.resolve(data);
                }).error(function(){
                    deferred.resolve(null);
                    throwError(arguments);
                });
            }
            return deferred.promise;
        };
        this.delete = function(ids){
            var deferred = $q.defer();
            $http.get("/diary/delete.do?ids="+ids).success(function(data){
                deferred.resolve(data);
            }).error(function(){
                deferred.resolve(null);
                throwError(arguments);
            });
            return deferred.promise;
        }
    }]);

    function throwError(arguments) {
        var code = arguments[1];
        var errMsg;
        switch (code) {
            case 404:
                errMsg = "Page Not Found";
                break;
        }
        throw new Error(errMsg);
    }
});