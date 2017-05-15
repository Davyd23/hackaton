app.service('PostingService', function($http){
    return {
        save : function(posting){
            return $http.post('/posting', posting).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        },
        getAll : function(){
            return $http.get('/posting').then(function(response){
                return response;
            }, function(err){
                return err;
            })
        }
    }
})