<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- Created by IntelliJ IDEA.
 User: s_okhoda
 Date: 20.01.2016
 Time: 17:52
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="../../../js/taxi.js" type="text/javascript">    </script>
<script src="../../../js/angular.js"></script>
<script src="../../../js/registerAjax.js"></script>

<html ng-app="regJSP">
<html>
    <head>
        <title>Registration Form</title>
        <style>
            <%@include file='../css/addNotebook.css' %>
        </style>
    </head>

    <body>
    <%--action="/testJson.json">--%>
    <form >
        <img <%--height="100px" width="100px" --%>
                src="../../../img/newUser.jpg" align="left"
                style="margin-right: 20px">
        <label for="login">LOGIN: </label>
        <input  type="text" id="login" ng-model="login"><br>

        <label for="identifier">IDENTIFIER: </label>
        <input  type="text" id="identifier" ng-model="identifier"><br>

        <label for="pass">PASSWORD: </label>
        <input  type="password" value="" id="pass" ng-model="pass"><br>

        <label for="confirmPass">CONFIRM PASSWORD: </label>
        <input  type="password" value="" id="confirmPass"
                ng-model="confirmPass">
        <br>
        <br>
        <div ng-controller="RegisterCtrl">
            <input type="submit" value="&longleftarrow; to Login"
                                      ng-click="back2Login()">
            <input type="submit" value="Register" ng-click="doRegister()" >

        <br><br>
        <label id="message"
               ng-style="{'width': '100%', 'margin-top': '10%',
            'color': message.mcolor, 'text-align' : 'center', 'font-size' : 'x-large'}">
            {{message.mtext}}
        </label>

        </div>
        <footer style="text-align: center">&copy;<spring:eval expression="@propertyConfigurer.getProperty('AllRights')"/></footer>
    </form>
    </body>
</html>
