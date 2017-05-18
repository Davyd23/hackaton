app.controller('JobMatchingController', ['$scope', 'PostingService', function($scope, PostingService){
    $scope.postings = [];
    $scope.totalRowsNumber = 0;
    $scope.elementsPerRow = 3;

    PostingService.getProfileMatches().then(function(response){
        console.log(response);
        $scope.postings = response.data;
        $scope.totalRowsNumber = Math.ceil( $scope.postings.length / $scope.elementsPerRow ) ;
    });

    $scope.rowElements = function(rowNumber){
        var remainingPostings = $scope.postings.length - (rowNumber * $scope.elementsPerRow);
        return remainingPostings>3? 3: remainingPostings;
    };

    $scope.getPostingId = function(rowNumber, colNumber){
        return rowNumber*3 + colNumber;
    };

    $scope.range = function(n) {
        return new Array(n);
    };
}])