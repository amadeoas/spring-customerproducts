var myApp = angular.module('myApp', []);

myApp.controller('selectionsController', ['$scope', '$http', '$window', function($scope, $http, $window) {
		$scope.basket = {
				category: "Basket", 
				products: []};
		
		$scope.change = function(productId, category, productName) {
			var index = -1;

			for (var i = 0; i < $scope.basket.products.length; i++) {
	    		if ($scope.basket.products[i].id == productId) {
	    			index = i;
	    			break;
	    		}
	    	}
		
	    	if (index == -1) {
	    		// Add
	    		var product = {id: productId, name: productName, category: {id: -1, name: category}};

	    		$scope.basket.products.push(product);
		    } else {
	    		// Remove
		    	$scope.basket.products.splice(index, 1);
	    	}
	    };
			    
	    $scope.sendPost = function() {
	    	// Send the request
	        var data = $scope.basket;
		
	    	$http.post("/customerproducts/order", data).then(function successCallback() {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
		    	$window.location.href = '/customerproducts/order/success';
	    	}, function errorCallback(response) {
	    	    // This callback will be called asynchronously an error occurs
	    	    // or server returns response with an error status.
		    	$window.location.href = '/customerproducts';
	    	});
	    };
}]);

// Reset all check boxes
reset = function() {
	$('input[type=checkbox]').removeAttr('checked');
};
