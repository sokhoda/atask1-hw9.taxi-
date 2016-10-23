<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 20.01.2016
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/node_modules/angular/angular.js"></script>
<script src="../../../js/registerOrderAjax.js"></script>

<link rel="stylesheet"
      href="/node_modules/bootstrap/dist/css/bootstrap.min.css">
<script src="/node_modules/jquery/dist/jquery.min.js"></script>
<script src="/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>

<html ng-app="regOrderJSP">
<head>
    <title>Order Registration Form</title>
    <style>
        <%@include file='../css/addNotebook.css' %>
        <%@include file='../css/createOrder.css' %>
    </style>
</head>

<body>
<div ng-controller="regOrderCtrl" ng-init='init(${selectedClientId})'>
    <form>
        <%--{{date | date:'dd.MM.yyyy'}}--%>
        <img <%--height="100px" width="100px" --%>
                src="../../img/newUser.jpg" align="left"
                style="margin-right: 20px">
        <table border="2">
            <col align="left">
            <col align="left">
            <col align="right">
            <tr>
                <th style=" text-align: center" colspan="2">
                    Order Info
                </th>
            </tr>
            <tr>
                <td style="text-align: left">
                    <label>DATE:</label>
                    <input type="text" name="date" ng-model="date"><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>CLIENT:</label>
                    <select size="1" ng-model="selectedClient"
                            ng-options="x as x.name  + ' ' + x.surname for x in clients track by x.id" >
                    </select>
                </td>
            </tr>
            <%--Client info--%>
            <tr>
                <td></td>
                <td>
                    <label>NAME:</label>
                    <input type="text" name="name" readonly
                           value="{{selectedClient.name}}"><br>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <label>SURNAME:</label>
                    <input type="text" name="surname" readonly
                           value="{{selectedClient.surname}}"><br>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <label>PHONE:</label>
                    <input type="text" name="phone" readonly
                           value="{{selectedClient.phone}}"><br>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <label>ADDRESS:</label>
                    <input type="text" name="address" readonly
                           value="{{selectedClient.address}}"><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>SUM:</label>
                    <input type="text" name="sum" ng-model="sum"><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>START ADDRESS:</label>
                    <input type="text" name="startaddress"
                           ng-model="startaddress"><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>END ADDRESS:</label>
                    <input type="text" name="endaddress"
                           ng-model="endaddress"><br>
                </td>
            </tr>
            <br>
        </table>
        <div ng-controller="regOrderCtrl">
            <input type="submit" value="&longleftarrow; to Dash"
                   ng-click="back2dashboard();">

            <input type="submit" value="Create"
                   ng-click="doRegisterOrder()">

            <br><br>
            <label id="message"
                   ng-style="{'width': '100%', 'margin-top': '10%',
            'color': message.mcolor, 'text-align' : 'center', 'font-size' : 'x-large'}">
                {{message.mtext}}
            </label>

        </div>
        <footer style="text-align: center">&copy;<spring:eval
                expression="@propertyConfigurer.getProperty('AllRights')"/></footer>
    </form>
</div>
</body>
</html>
