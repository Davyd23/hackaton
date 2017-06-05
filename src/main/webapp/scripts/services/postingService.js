app.service('PostingService', function($http){
    return {
        save : function(posting){
            return $http.post('/posting', posting).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        },
        apply : function(posting){
            return $http.post('/posting/apply', posting).then(function(response){
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
        },
        delete : function(posting){
            return $http.post('/posting/delete', posting).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        },
        getProfileMatches : function(){
            return $http.get('/posting/matching').then(function(response){
                return response;
            }, function(err){
                return err;
            })
        },
        getApplicants : function(posting){
            return $http.post('/posting/candidates', posting).then(function(response){
                return response;
            }, function(err){
                return err;
            })
        }
    }
})