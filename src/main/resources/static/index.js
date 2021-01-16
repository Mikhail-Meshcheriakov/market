angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});