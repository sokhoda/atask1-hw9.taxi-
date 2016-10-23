<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 19.01.2016
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Menu</title>
    <style>
        <%@include file='../css/menu.css' %>
    </style>
</head>
<body>

<c:set var="defPortion" value="5"/>
<c:set var="defQuant" value="15"/>
<c:set var="defAmount" value="500"/>
<c:set var="defSumMin" value="500"/>
<c:set var="defSumMax" value="2000"/>


<%--<center><img id="MenuImg"--%>
             <%--src="../../../../src/main/resources/img/taxi_clients.jpg">--%>
<%--</center>--%>
<h1 align="center">Welcome to the TAXI Service</h1>
<div>
    <label align="left" style="font-size: larger; font-weight: bold;
              color: firebrick">Please, make your choice:
    </label>
    <label id="message" style="width: 100%; color:${messageColor};
            text-align: center; font-size:x-large">${messageText}
    </label>
</div>

<table width="100%">
    <thead>
    </thead>

    <tbody>
    <tr>
        <form action="/registerClient.html">
        <td class="col0">
            <input type="submit" name="crClient"
                   value="1. Register client">
        </td>
        </form>
    </tr>
    <tr>
        <form action="/clients.html">
            <td class="col0">
                <input type="submit" name="listClientsByPortion"
                       value="2. List Clients by Portion">
            </td>

        <td class="colA">
            <div class="cellIn">
                <label class="smallSign">Portion:</label>
                <input type="text" name="listClientsByPortionPortion"
                       value="${defPortion}">
            </div>
        </td>
        </form>
    </tr>
    <tr>
        <td class="col0">
            <input type="submit" name="listClientsGtSum"
                   value="3. List Clients with order Sum >="
                   formaction="/showClientsGtSum.html">
        </td>
        <td class="colA">
            <div class="cellIn">
                <label class="smallSign">Amount:</label>
                <input type="text" name="listClientsGtSumSum"
                       value="${defAmount}">
            </div>
        </td>
    </tr>
    <tr>
        <td class="col0">
            <input type="submit" name="listClientsLastMonth"
                   value="4. List clients last month"
                   formaction="/showClientsLastMonth.html">
        </td>
    </tr>
    <tr>
        <form action="/registerOrder.html">
            <td class="col0">
                <input type="submit" name="crOrder"
                       value="5. Create order">
            </td>
        </form>
    </tr>
    <tr>
        <td class="col0">
            <input type="submit" name="edOrder"
                   value="6. Edit order"
                   formaction="/editOrder.html">
        </td>
    </tr>
    <tr>
        <td class="col0">
            <input type="submit" name="listOrdersSumInRange"
                   value="7. List orders with sum in range"
                   formaction="/showOrdersSumInRange.html">
        </td>
        <td class="colA">
            <div class="cellIn">
                <label class="smallSign">Min sum:</label>
                <input type="text" name="minSum"
                       value="${defSumMin}">
            </div>
        </td>
        <td class="colA">
            <div class="cellIn">
                <label class="smallSign">Max sum:</label>
                <input type="text" name="maxSum"
                       value="${defSumMax}">
            </div>
        </td>
    </tr>
    <tr>
        <td class="col0">
            <input type="submit" name="listOrdersByPortion"
                   value="8. List orders by portion"
                   formaction="/showOrdersByPortion.html">
        </td>
        <td class="colA">
            <div class="cellIn">
                <label class="smallSign">Portion:</label>
                <input type="text" name="listOrdersByPortionPortion"
                       value="${defPortion}">
            </div>
        </td>
    </tr>
    <tr>
        <td class="col0">
            <%--<input type="submit" name="exit" class="but"--%>
            <%--value=">10. Exit">--%>
            <button name="exit" onclick="self.close()" class="but">9.
                Exit
            </button>
        </td>
    </tr>
    </tbody>

</table>

<form>
<footer style="text-align: center">&copy;<spring:eval expression="@propertyConfigurer.getProperty('AllRights')"/></footer>
</form>
</body>
</html>
