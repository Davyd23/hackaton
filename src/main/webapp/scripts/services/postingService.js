app.service( 'PostingService', function($http) {
    return {
        findAll: function() {
            return $http.get("/hackaton/postings").then(function(response){
                return response.data;
            },function(err){
                return err;
            });
        }
    };
});
