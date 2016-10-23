var app = angular.module('clientUpdate', []);


app.controller("updClientCtrl", function ($scope, $window, $http) {
//console.log
    $scope.init = function(serialClient){
        //console.log(serialClient);
        var client = angular.fromJson(serialClient);
        $scope.cid = client.id;
        $scope.name = client.name;
        $scope.surname = client.surname;
        $scope.phone = client.phone;
        $scope.address = client.address;
    };

    $scope.back2dashboard = function() {
        $window.location.href = '/back2Menu.html';
        //console.log('back2Menu.html');
    };

    var setScopeMessage = function(value) {
        $scope.message =  { "mcolor" : "red", "mtext": value};
    };

    var undefinedAddressChange = function() {
        if (typeof ($scope.address) == 'undefined' ) {
            $scope.address = '';
        }

    };

    var emptyCheck = function(value, name) {
        if (typeof (value) == 'undefined' || value == '') {
            setScopeMessage(name + " can not have ZERO length.");
            return false;
        }
        return true;
    };

    var spaceCheck = function(value, name) {
        if (value.indexOf(' ') >= 0) {
            setScopeMessage(name + " can not contain SPACES.");
            return false;
        }
        return true;
    };

    var minLenCheck = function(value, name, minLen) {
        if (value.length < minLen) {
            setScopeMessage(name + " can not contain < " + minLen + " symbols.");
            return false;
        }
        return true;
    };

    var digitOnlyCheck = function(value, name) {
        if (!/^\d+$/.test(value)) {
            setScopeMessage(name + " must contain digits ONLY.");
            return false;
        }
        return true;
    };

    var phoneStringCheck = function(value, name) {
        if (/^[ \-0-9.]+$/.test(value)) {
            return true;
        }
        setScopeMessage(name + " can contain ' ', '0-9', '-', '.'");
        return false;
    };

    var nameCheck = function(value, name) {
         return emptyCheck(value, name);
    };

    var phoneCheck = function(value, name, minLen) {
        if (emptyCheck(value, name)) {
            if (phoneStringCheck(value, name))
                return minLenCheck(value, name, minLen);
            else
                return false;
        }
        return false;
    };

    $scope.doToClientList = function(sPortion, cnt, totPages) {


        if (!nameCheck($scope.name, 'Name')) {
            return;
        }
        if (!nameCheck($scope.surname, 'Surname')) {
            return;
        }
        if (!phoneCheck($scope.phone, 'Phone', 10)) {
            return;
        }
        //console.log('\'' + $scope.name + '\', \'' + $scope.surname + '\''+ '\', \'' + $scope.address + '\'');
        undefinedAddressChange();

        $window.location.href =
            '/showCurrentClientPortion.html?portionSize='+ sPortion +'&cnt='+ cnt +'&totPages=' + totPages;
    };

    $scope.doSaveClient = function() {
        //console.log($scope.cid  + ', \'' + $scope.name + '\', \'' + $scope.surname + '\', \'' + $scope.address + '\'');
        var responsePromise = $http.get('/updateClient.json?cid=' + $scope.cid +'&name=' + $scope.name +
            '&surname=' + $scope.surname + '&phone=' + $scope.phone + '&address=' + $scope.address);

        responsePromise.success(
            function(data, status, headers, config) {
                $scope.message = data;
                console.log(JSON.stringify(data));
            });

        responsePromise.error(
            function(data, status, headers, config) {
                alert('AJAX failed!\n' + data + '\nstatus=' + status);
            });
    };
});
