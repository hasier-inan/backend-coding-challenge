"use strict";

/**
 * Created by hasiermetal on 09/01/2018.
 * Service to consume an open ws to get GBP/EUR currency rate information
 */
var app = angular.module("currency.rate", []);

app.service("currencyRate", ["$http", "$timeout", function($http, $timeout) {
  const currencyApiUrl = "https://api.fixer.io/latest?symbols=GBP,EUR";
  var currencyClient = {};
  var gbpRate;
  currencyClient.cacheCurrencyRate = function() {
    $http.get(currencyApiUrl).then(function(response) {
      if (response.status === 200)
        gbpRate = response.data.rates.GBP;
    });

    //update rate every 30 minutes
    $timeout(function() {
      currencyClient.cacheCurrencyRate();
    }, 1000 * 60 * 30);
  };

  currencyClient.calculateAmountInGbp = function(rawAmount) {
    return (rawAmount && rawAmount.indexOf("EUR") >= 0) ? Number(rawAmount.replace("EUR", "")) * gbpRate : rawAmount;
  };

  return currencyClient;
}]);
