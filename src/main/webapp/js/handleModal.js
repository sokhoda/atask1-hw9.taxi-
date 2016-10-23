/**
 * Created by s_okhoda on 01.07.2016.
 */
var app = angular.module("HandleModal", ["ui.bootstrap.modal"]);

app.controller("HandleModalCntr", function($scope) {

    $scope.open = function() {
        $scope.showModal = true;
    };

    $scope.ok = function() {
        $scope.showModal = false;
    };

    $scope.cancel = function() {
        $scope.showModal = false;
    };

});