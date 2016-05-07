var myApp = angular.module('myApp', []);

myApp.controller('selectionsController', ['$scope', '$http', '$window', function($scope, $http, $window) {
		$scope.basket = {
				category: "Basket", 
				customer: {id: -1},
				hasChanged: false,
				products: []};

		$scope.change = function(productId, category, productName) {
			var index = -1;

	    	$scope.basket.hasChanged = true;
			for (var i = 0; i < $scope.basket.products.length; i++) {
	    		if ($scope.basket.products[i].id == productId) {
	    			// Remove
			    	$scope.basket.products.splice(i, 1);

			    	return;
	    		}
	    	}
		
	    	// Add
	    	var subscription = {id: productId, name: productName, category: {id: -1, name: category}};

	    	$scope.basket.products.push(subscription);
	    };
			    
	    $scope.sendPost = function(customerId) {
	    	// Send the request
	    	$scope.basket.customer.id = customerId;
	    	
	        var data = $scope.basket;

	    	$scope.basket.hasChanged = false;
	    	$http.post('/customerproducts/subscriptions?lang=' + $window.language, data).then(function successCallback(response) {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
		    	$window.location.href = '/customerproducts/subscriptions/success/?lang=' + $window.language;
	    	}, function errorCallback(response) {
	    	    // This callback will be called asynchronously an error occurs
	    	    // or server returns response with an error status.
		    	$window.location.href = '/customerproducts/?lang=' + $window.language;
	    	});
	    };
	    
	    $scope.isChecked = function (productId) {
	    	for (var i = 0; i < $scope.basket.products.length; ++i) {
	    		if ($scope.basket.products[i].id == productId) {
	    			return true;
				}
			}
	    	
	    	return false;
	    }
			    
	    $scope.getBasket = function(customerId) {
	    	// Send the request
	    	$scope.basket.customer.id = customerId;
		
	    	$http.get('/customerproducts/subscriptions/data/' + customerId)
	    	.then(function successCallback(response) {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
	    		$scope.basket = response.data;
	    	}, function errorCallback(response) {
		   	    // This callback will be called asynchronously an error occurs
		   	    // or server returns response with an error status.
		    	$window.location.href = '/customerproducts/?lang=' + $window.language;
	    	});
	    };
}]);

setLanguage = function(language) {
	window.language = language;
}
