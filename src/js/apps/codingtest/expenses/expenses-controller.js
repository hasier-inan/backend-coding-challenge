"use strict";

/******************************************************************************************

Expenses controller

******************************************************************************************/

var app = angular.module("expenses.controller", []);

app.controller("ctrlExpenses", ["$rootScope", "$scope", "config", "restalchemy", "currencyRate",
  function ExpensesCtrl($rootScope, $scope, $config, $restalchemy, $currencyRate) {

	// Update the headings
	$rootScope.mainTitle = "Expenses";
	$rootScope.mainHeading = "Expenses";

	// Update the tab sections
	$rootScope.selectTabSection("expenses", 0);

	var restExpenses = $restalchemy.init({ root: $config.apiroot }).at("expenses");

	$scope.dateOptions = {
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd/mm/yy"
	};

	$scope.vatCalculation = {};

	var loadExpenses = function() {
		// Retrieve a list of expenses via REST
		restExpenses.get().then(function(expenses) {
			$scope.expenses = expenses;
		});
	};

  $scope.saveExpense = function() {
    if ($scope.expensesform.$valid) {
      // Post the expense via REST
      restExpenses.post($scope.updatedAmountExpense()).then(function() {
        // Reload new expenses list
        loadExpenses();
      });
    }
  };

  $scope.calculateVAT = function() {
    $scope.vatCalculation = $currencyRate.calculateAmountInGbp($scope.newExpense.amount) * 0.2;
  };

	$scope.clearExpense = function() {
		$scope.newExpense = {};
    $scope.vatCalculation = {};
	};

	$scope.updatedAmountExpense = function() {
    var createdExpense = angular.copy($scope.newExpense);
    createdExpense.amount = $currencyRate.calculateAmountInGbp($scope.newExpense.amount);
    return createdExpense;
  };

	// Initialise scope variables
	loadExpenses();
	$scope.clearExpense();
	$currencyRate.cacheCurrencyRate();
}]);
