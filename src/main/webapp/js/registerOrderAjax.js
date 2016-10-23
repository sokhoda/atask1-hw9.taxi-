var app = angular.module('regOrderJSP', []);

//app.factory("registerService", function(){
//    return{
//    };
//});


app.controller("regOrderCtrl", function ($scope, $window, $http, $filter) {


    $scope.onClientChange = function () {

        console.log('clientId=' +  $scope.selectedClientId + ' !!!!');

        var responsePromise = $http.get('/selectClient.json?id=' + $scope.selectedClientId);

        responsePromise.success(
            function (data, status, headers, config) {
                $scope.client = data;
                //console.log(JSON.stringify(data));
                //$scope.messageColor = data.messageC
                //alert('data=' + data.mText);
            });

        responsePromise.error(
            function (data, status, headers, config) {
                alert('AJAX failed!\n' + data + '\nstatus=' + status);
            });


    }

    var setSelectedClient  = function  (id){
        var responsePromiseClient = $http.get('/selectClient.json?id='  + id);

        responsePromiseClient.success(
            function (data, status, headers, config) {
                $scope.selectedClient = data;
            });

        responsePromiseClient.error(
            function (data, status, headers, config) {
                alert('AJAX failed!\n' + data + '\nstatus=' + status);
            });
    };

    var setAllClients  = function  (){
         var responsePromiseAllClients = $http.get('/selectAllClients.json');
        responsePromiseAllClients.success(
            function (data, status, headers, config) {
                $scope.clients = data;
            });

        responsePromiseAllClients.error(
            function (data, status, headers, config) {
                alert('AJAX failed!\n' + data + '\nstatus=' + status);
            });
    };

    $scope.init = function (selectedClientId) {
        setAllClients();
        setSelectedClient(selectedClientId);
        $scope.date =  $filter('date')(new Date(), 'dd.MM.yyyy');
    };

    $scope.back2dashboard = function () {
        $window.location.href = '/back2Menu.html';
        //console.log('back2Menu.html');
    };

    var setScopeMessage = function (value) {
        $scope.message = {"mcolor": "red", "mtext": value};
    };

    var undefinedAddressChange = function () {
        if (typeof ($scope.address) == 'undefined') {
            $scope.address = '';
        }

    };

    var emptyCheck = function (value, name) {
        if (typeof (value) == 'undefined' || value == '') {
            setScopeMessage(name + " can not have ZERO length.");
            return false;
        }
        return true;
    };

    var spaceCheck = function (value, name) {
        if (value.indexOf(' ') >= 0) {
            setScopeMessage(name + " can not contain SPACES.");
            return false;
        }
        return true;
    };

    var minLenCheck = function (value, name, minLen) {
        if (value.length < minLen) {
            setScopeMessage(name + " can not contain < " + minLen + " symbols.");
            return false;
        }
        return true;
    };

    var digitOnlyCheck = function (value, name) {
        if (!/^\d+$/.test(value)) {
            setScopeMessage(name + " must contain digits ONLY.");
            return false;
        }
        return true;
    };

    var phoneStringCheck = function (value, name) {
        if (/^[ \-0-9.]+$/.test(value)) {
            return true;
        }
        setScopeMessage(name + " can contain ' ', '0-9', '-', '.'");
        return false;
    };

    var nameCheck = function (value, name) {
        return emptyCheck(value, name);
    };

    var phoneCheck = function (value, name, minLen) {
        if (emptyCheck(value, name)) {
            if (phoneStringCheck(value, name))
                return minLenCheck(value, name, minLen);
            else
                return false;
        }
        return false;
    };

    $scope.doRegisterOrder = function () {


        if (!nameCheck($scope.name, 'Name')) {
            return;
        }
        if (!nameCheck($scope.surname, 'Surname')) {
            return;
        }
        if (!phoneCheck($scope.phone, 'Phone', 10)) {
            return;
        }
        console.log('\'' + $scope.name + '\', \'' + $scope.surname + '\'' + '\', \'' + $scope.address + '\'');

        undefinedAddressChange();
        console.log('\'' + $scope.name + '\', \'' + $scope.surname + '\'' + '\', \'' + $scope.address + '\'');

        var responsePromise = $http.get('/createOrder.json?name=' + $scope.name +
            '&surname=' + $scope.surname + '&phone=' + $scope.phone + '&address=' + $scope.address);

        responsePromise.success(
            function (data, status, headers, config) {
                $scope.message = data;
                console.log(JSON.stringify(data));
                //$scope.messageColor = data.messageC
                //alert('data=' + data.mText);
            });

        responsePromise.error(
            function (data, status, headers, config) {
                alert('AJAX failed!\n' + data + '\nstatus=' + status);
            });


    };


});
