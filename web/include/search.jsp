<%@page import="model.Control" %>
<%@page import="java.util.ArrayList"%>

<h3>Volumen beregner:</h3>
<div class ="row">
    <div class="col-sm-1">
        <p>Length:</p>  <br>      
    </div>
    <input id="1"><br>         
</div>
<div class ="row">
    <div class="col-sm-1">
        <p>Width:</p>  <br>      
    </div>
    <input id="2"><br>         
</div>
<div class ="row">
    <div class="col-sm-1">
        <p>Height:</p>  <br>      
    </div>
    <input id="3"><br>         
</div>
<div class ="row">
    <div class="col-sm-1">
        <p>Weight:</p>  <br>      
    </div>
    <input id="4"><br>         
</div>
    <button onclick=calc()>beregn volumen</button>
    <br/>
    <br/>


<form action="search" method="POST">

    <div class="row">
        <div class="col-sm-3">Country:<%@include file="autoCompleteCountries.jsp" %></div>
        <div class="col-sm-3"> Weight:<%@include file="autoCompleteWeight.jsp" %> </div>
        <div class="col-sm-6"> </div>
    </div>
    <div class="row">
        <br>
        <div class="col-sm-3">   <input type="submit" value="Search" /> </div>
    </div>
</form>
<br>

<table class="table table-striped table-hover table-bordered">
    <tr>
        <th>Weight</th>
        <th>Fedex Economy</th> 
        <th>Fedex Priority</th> 
        <th>UPS Saver</th>
        <th>UPS Express</th>
        <th>UPS Standard</th>
        <th>GLS</th>
        <th>Cheapest</th>
    </tr>
    <%
        Control c = new Control();
        String country = (String) request.getAttribute("country");
        String kgString = (String) request.getAttribute("weight");

        if (kgString == null) {
            kgString = "" + 0;
        }
        if (country == null) {
            country = "Denmark";
        }

        double kg1 = Double.parseDouble(kgString.replace(",", "."));
        kg1 = Math.round(kg1-0.5)+0.5;
        
        int kg = 1;
        for (int i = 0; i < c.weights.length; i++) {
            if (c.weights[i] == kg1) {
                kg1 = c.weights[i];
                kg = i;
            }
        }

        ArrayList<double[]> lists = new ArrayList();
        lists = c.allLists.get(country);

        double[] fedexEconomy = lists.get(0).clone();
        double[] fedexPriority = lists.get(1).clone();
        double[] upsSaver = lists.get(2).clone();
        double[] upsExpress = lists.get(3).clone();
        double[] upsStandard = lists.get(4).clone();
        double[] gls = lists.get(5).clone();

        double cheap = 99999.0;
        Double weight = 0.0;

        Double fedexE, fedexP, upsSa, upsE, upsSt, glsS = 0.0;

        weight = c.weights[kg];
        if (fedexEconomy.length > 1) {
            fedexE = fedexEconomy[kg];
        } else {
            fedexE = 0.0;
        }
        if (fedexPriority.length > 1) {
            fedexP = fedexPriority[kg];
        } else {
            fedexP = 0.0;
        }
        if (upsSaver.length > 1) {
            upsSa = upsSaver[kg];
        } else {
            upsSa = 0.0;
        }
        if (upsExpress.length > 1) {
            upsE = upsExpress[kg];
        } else {
            upsE = 0.0;
        }
        if (upsStandard.length > 1) {
            upsSt = upsStandard[kg];
        } else {
            upsSt = 0.0;
        }
        if (gls.length > 1 && kg < 100) {
            glsS = gls[kg];
        } else {
            glsS = 0.0;
        }

        int carrier = 0;
        if (kg > 99) {
            lists.remove(5);
        }

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 1) {
                if (lists.get(i)[kg] < cheap) {
                    cheap = lists.get(i)[kg];
                    carrier = i;
                }
            }
        }
        out.println("<tr>");
        out.println("<td>" + weight + " kg" + "</td>");
        out.println("<td>" + fedexE + "</td>");
        out.println("<td>" + fedexP + "</td>");
        out.println("<td>" + upsSa + "</td>");
        out.println("<td>" + upsE + "</td>");
        out.println("<td>" + upsSt + "</td>");
        out.println("<td>" + glsS + "</td>");
        out.println("<td>" + c.carrierNames[carrier] + "</td>");
        out.println("</tr>");
    %>
</table>

<script>
    var calc = function () {
        var length = document.getElementById("1").value;
        var width = document.getElementById("2").value;
        var height = document.getElementById("3").value;
        if(document.getElementById("rWeight").value>(length*width*height)/5000)){
            document.getElementById('weight').value = document.getElementById("rWeight").value;
        } else{
            document.getElementById('weight').value = ((length * width * height) / 5000);
        }
    };
</script>
