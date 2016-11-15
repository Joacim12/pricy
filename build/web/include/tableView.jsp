<%@page import="java.util.ArrayList"%>
<%@ page import="model.Control" %>
<h1>Choose country:</h1>
<form action="servlet" method="POST">
    <select name="test">
        <%
            Control c = new Control();
            for (String string : c.tempo) {
                out.println("<option>" + string + "</option>");
            }
        %>
    </select>
    <input class="btn info" type="submit" value="Search" />
</form>
<%
    String name = (String) request.getAttribute("test");
    if (name == null) {
        name = "Afghanistan";
    }
    out.println("<h1>" + name + "</h1>");
%>
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th>Weight</th>
        <th>Fedex Economy</th> 
        <th>Fedex Priority</th> 
        <th>UPS Saver</th>
        <th>UPS Express</th>
        <th>UPS Standard</th>
        <!--<th>GLS</th>-->
        <th>Cheapest</th>
    </tr>
    <%
        ArrayList<double[]> lists = new ArrayList();
        name = (String) request.getAttribute("test");
        if (name == null) {
            name = "Afghanistan";
        }
        lists = c.allLists.get(name);

        // Saves cheapest price in a new list
        double[] cheapList = new double[140];
        int[] cheapListCarrier = new int[140];
        for (int i = 0; i < cheapList.length; i++) {
            cheapList[i] = 9999999.9;
            cheapListCarrier[i] = (int) 9999999.9;
        }
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).length; j++) {
                if (lists.get(i)[j] < cheapList[j] && lists.get(i).length > 1) {
                    cheapList[j] = lists.get(i)[j];
                    cheapListCarrier[j] = i;
                }
            }
        }
        // Adds selected country + cheapest price to UI                  
        double[] noPrice = c.noPrice.clone();
        double[] fedexEconomy = lists.get(0).clone();
        double[] fedexPriority = lists.get(1).clone();
        double[] fedexExpress = lists.get(2).clone();
        double[] upsSaver = lists.get(3).clone();
        double[] upsExpress = lists.get(4).clone();

        String fedexNA = "";
        String fedexPriorityNA = "";
        String fedexExpressNA = "";
        String upsSaverNA = "";
        String upsExpressNA = "";

        if (fedexEconomy.length < 1) {
            fedexEconomy = noPrice.clone();
            fedexNA = "N/A";
        }
        if (fedexPriority.length < 1) {
            fedexPriority = noPrice.clone();
            fedexPriorityNA = "N/A";
        }
        if (fedexExpress.length < 1) {
            fedexExpress = noPrice.clone();
            fedexExpressNA = "N/A";
        }
        if (upsSaver.length < 1) {
            upsSaver = noPrice.clone();
            upsSaverNA = "N/A";
        }
        if (upsExpress.length < 1) {
            upsExpress = noPrice.clone();
            upsExpressNA = "N/A";
        }
        for (int j = 0; j < cheapList.length; j++) {

            out.println("<tr>");
            out.println("<td>" + c.weights[j] + " kg" + "</td>");
            out.println("<td class=\"succes\">" + fedexEconomy[j] + " " + fedexNA + "</td>");
            out.println("<td class=\"succes\">" + fedexPriority[j] + " " + fedexPriorityNA + "</td>");
            out.println("<td>" + fedexExpress[j] + " " + fedexExpressNA + "</td>");
            out.println("<td>" + upsSaver[j] + " " + upsSaverNA + "</td>");
            out.println("<td>" + upsExpress[j] + " " + upsExpressNA + "</td>");
            out.println("<td>" + c.carrierNames[cheapListCarrier[j]] + "</td>");
            out.println("</tr>");
        }
    %>

</table> 