//$(document).ready(function (cnt, totPages) {
//    alert('cnt == totPages');
//    if (cnt == totPages){
//        $("#nextInput").css('visibility','hidden');
//    }
//    if (cnt == 1){
//        $("#previousInput").hide();
//    }
//});

//$('.save').click(function(){
//    alert('save pres!!!');
//    $('#myModal').modal('toggle');
//});

var app = angular.module('listEntitiesJSP', ['ui.bootstrap.modal']);

app.controller("listEntitiesCtrl", function ($scope, $window, $http) {

    //$scope.showDialogue = function(item){
    //    var cid = item.currentTarget.getAttribute("ng-id");

    var getCurrentPortion = function () {
        $http.get('/showClientsByPortion.json?portionSize=' + $scope.sPortion + '&cnt=' + $scope.cnt)
            .success(
                function (data, status, headers, config) {
                    $scope.clientList = data;
                    //console.log(JSON.stringify(data));
                })
            .error(
                function (data, status, headers, config) {
                    alert('AJAX failed!\n' + data + '\nstatus=' + status);
                });
    };

    $scope.init = function (sPortion, totPages, cnt) {
        $scope.sPortion = sPortion;
        $scope.totPages = totPages;
        $scope.cnt = cnt;
        $scope.showDialog = false;
        //console.log('sPortion=' + $scope.sPortion + ', totPages=' + $scope.totPages + 'cnt=' + $scope.cnt);
        getCurrentPortion();
    };

    $scope.doPreviousPortion = function () {
        if ($scope.cnt > 1) {
            $scope.cnt--;
        }
        //console.log('sPortion=' + $scope.sPortion + ', totPages=' + $scope.totPages + 'cnt=' + $scope.cnt);
        getCurrentPortion();
    };

    $scope.doNextPortion = function () {
        if ($scope.cnt < $scope.totPages) {
            $scope.cnt++;
        }
        //console.log('sPortion=' + $scope.sPortion + ', totPages=' + $scope.totPages + 'cnt=' + $scope.cnt);
        getCurrentPortion();
    };

    $scope.doUpdateClient = function (item) {
        var cid = item.currentTarget.getAttribute("ng-id");
        console.log('cis=' + cid);
        $window.location.href =
            '/editClient.html?cid=' + cid + '&portionSize=' + $scope.sPortion +
            '&cnt=' + $scope.cnt + '&totPages=' + $scope.totPages;
    };

    $scope.doDeleteClient = function (cid) {
        //console.log('\'' + $scope.login + '\'');
        $scope.showConfirm(this);
        //var responsePromise = $http.get('/doDeleteClient.json?cid=' + cid + '&portionSize=' + $scope.sPortion +
        //    '&cnt=' + $scope.cnt + '&totPages=' + $scope.totPages);
        //
        //responsePromise.success(
        //    function (data, status, headers, config) {
        //        $scope.message = data;
        //        console.log(JSON.stringify(data));
        //    });
        //
        //responsePromise.error(
        //    function (data, status, headers, config) {
        //        alert('AJAX failed!\n' + data + '\nstatus=' + status);
        //    });
    };

    $scope.hidePrevious = function () {
        if ($scope.cnt == 1) {
            return 'hidden';
        }
        else {
            return 'visible';
        }
    };

    $scope.hideNext = function () {
        if ($scope.cnt == $scope.totPages) {
            return 'hidden';
        }
        else {
            return 'visible';
        }
    };


    $scope.open = function(item) {
        var cid = item.currentTarget.getAttribute("ng-id");
        $scope.deleteElementId = cid;
        $scope.showModal = true;
    };

    $scope.ok = function() {
        $scope.showModal = false;
    };

    $scope.cancel = function() {
        $scope.showModal = false;
    };

});
