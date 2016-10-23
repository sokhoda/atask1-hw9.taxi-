<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script src="../../../js/angular.js"></script>--%>
<%--<script src="../../../js/jquery-1.12.2.min.js"></script>--%>
<%--AngularJS--%>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script src="../../../js/list.js"></script>
<script src="../../../js/angular-ui-bootstrap-modal.js"></script>

<%--<%@ page errorPage="/hw7.notes/pages/generalErrorPage.jsp" %>--%>
    
<html ng-app="listEntitiesJSP" ng-cloak style="margin-left: 1em">
<head>
    <%--Bootstrap--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <title>Client List</title>
    <style>
        <%@include file='../css/list.css' %>
    </style>
    <center><h1>Client List</h1></center>
    <br>
</head>
<body>


<div style="float: left;">
    <form action="/back2Menu.html">
        ${pageContext.request.contextPath}
        <input type="submit" class="but" value="&longleftarrow; to Dash">
    </form>
</div>
<%--<a href="/back2Menu.html"><button>&longleftarrow; to Dash</button></a>--%>

<div ng-controller="listEntitiesCtrl"
     ng-init='init(${sPortion}, ${totPages}, ${cnt})' class="container">
    <form>
        <div style="display: inline-block;">
            <button style="margin-left:8em" class="but"
                    ng-style="{'visibility': hidePrevious()};"
                    ng-click="doPreviousPortion()">&longleftarrow;
            </button>

            <label class="cntMark">{{cnt}} of {{totPages}}</label>

            <button class="but" ng-style="{'visibility': hideNext()};"
                    ng-click="doNextPortion()">&longrightarrow;
            </button>
            <label id="message" ng-style="{'width': '100%', 'margin-top': '10%',
            'color': message.mcolor, 'text-align' : 'center', 'font-size' : 'x-large'}">
                {{message.mtext}}
            </label>
        </div>

        <table class="table table-striped table-hover table-condensed"
               style="width:75%;">
            <thead>
            <tr>
                <%--<th class="shrink"><h3>ID</h3></th>--%>
                <th><h3>ID</h3></th>
                <th><h3>Name</h3></th>
                <th><h3>Surname</h3></th>
                <th><h3>Phone</h3></th>
                <th><h3>Address</h3></th>
                <th><h3>Last Order Date</h3></th>
                <th><h3>Order Amount</h3></th>
            </tr>
            </thead>
            <tbody>

            <%--<c:forEach var="cl" items="${clientList}" varStatus="count">--%>
            <tr ng-repeat="cl in clientList">
                <td class="shrink">{{cl.id}}</td>
                <td align="left">{{cl.name}}</td>
                <td align="left">{{cl.surname}}</td>
                <td align="left">{{cl.phone}}</td>
                <td align="left">{{cl.address}}</td>
                <td align="left">{{cl.lastOrderDate == null ? 'N/A' :
                    cl.lastOrderDate}}
                </td>
                <td align="left">{{cl.orderAmount}}</td>
                <td>
                    <button class="btn btn-primary btn-xs" ng-id="{{cl.id}}"
                            ng-click="doUpdateClient($event)">Update
                    </button>
                </td>
                <td>
                    <%--<button class="btn btn-warning btn-xs" ng-id="{{cl.id}}"--%>
                            <%--ng-click="doDeleteClient($event)">Delete--%>
                    <%--</button>--%>
                    <button class="btn btn-warning btn-xs" ng-id="{{cl.id}}"
                    ng-click="open($event)">Delete</button>

                </td>
            </tr>
            <%--</c:forEach>--%>
            </tbody>
        </table>
        <footer style="text-align: center">&copy;<spring:eval
                expression="@propertyConfigurer.getProperty('AllRights')"/></footer>
    </form>


    <!-- Modal -->
    <div modal-show modal="showModal" class="modal fade" id="myModal"
         tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         close="cancel()">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="myModalLabel">You are about to
                        delete element, id = {{deleteElementId}}.</h3>
                </div>
                <div class="modal-body">
                    Confirm deletion ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" ng-click="cancel()">Close
                    </button>
                    <button type="button" class="btn btn-primary save"
                            ng-click="ok()">Save
                        changes
                    </button>
                </div>
            </div>
        </div>
    </div>


    <%--<div modal="showModal" close="cancel()">--%>
        <%--<div class="modal-header">--%>
            <%--<h4>Modal Dialog</h4>--%>
        <%--</div>--%>
        <%--<div class="modal-body">--%>
            <%--<p>Example paragraph with some text.</p>--%>
        <%--</div>--%>
        <%--<div class="modal-footer">--%>
            <%--<button class="btn btn-success" ng-click="ok()">Okay</button>--%>
            <%--<button class="btn" ng-click="cancel()">Cancel</button>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>
</body>
</html>
