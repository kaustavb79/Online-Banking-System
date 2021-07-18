<%@page import="bank.model.Customer"%>
<%@page import="bank.connect_dao.DBOperation"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CUSTOMER DASHBOARD</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
      .header {
        padding:9px 10px;
        background:rgba(85, 218, 200, 0.911);
        color: #f1f1f1;
        font-size: medium;
        text-align: center;
      }

      footer {
        padding-top: 5px;
        padding-bottom: 3px;
        background:rgba(85, 218, 200, 0.911);
        color: black;
        font-size:18px;
        text-align: center;
        font-family: 'Times New Roman', Times, serif;
        position: relative;
        bottom: 0;
        width: 100%;
      }
      .header h2{
        font-weight: bolder;
        color: black;
        font-family: 'Times New Roman', Times, serif;
      }
      .navbar{
        background-color: rgb(158, 230, 240);
        font-size: 18px;
        border-color: transparent;
      }
	  body
    {
      text-align: center;
      font-size: 18px;
      font-family: 'Times New Roman', Times, serif;
    }
    #btn{
      display: inline-block;
       text-align: left;
       margin-left:150px;
    }
    #addr{
      display: inline;
    }
    #addr > p{
      float: right;
      margin-top: auto;
      font-weight: normal;
    }
    label{
      font-weight: bold;
    }
    </style> 
</head>
<body>
		<%
        if(request.getSession()!=null){
        HttpSession sess=request.getSession();
        if(sess.getAttribute("account_num")!=null){
            long anum=(Long)sess.getAttribute("account_num");       
            Customer cus=DBOperation.returnCustomerByAccountNumber(anum);
            String gender;
            if(cus.getGender().equals("Male")){
                gender="Mr.";
            }
            else{
                gender="Mrs.";
            }
            String path=application.getContextPath()+"/customer/";        
    %>
  <div class="header">
      <h2>ONLINE BANKING SYSTEM</h2><h4 style=" color:black;">Welcome <%= gender.concat(" "+cus.getName().toUpperCase()) %> </h4>
  </div>
  <div class="container">
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <ul class="nav navbar-nav">
		<li class="list"><a id="active" href="<%= path %>account_summary.jsp" style="color: black;  font-family: 'Times New Roman', Times, serif;">Account Summary</a></li>
		<li class="list"><a href="<%= path %>transfer.jsp" style="color: black; font-family: 'Times New Roman', Times, serif;">Fund Transfer</a></li>
		<li class="list"><a href="<%= path %>account_statement.jsp" style="color: black; font-family: 'Times New Roman', Times, serif;">Account Statement</a></li>
		<li class="list"><a href="<%= path %>profile.jsp" style="color: black; font-family: 'Times New Roman', Times, serif;">Profile</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="<%= path %>logout.jsp" style="color: black; font-family: 'Times New Roman', Times, serif;"><span class="glyphicon glyphicon-log-in" style="color: black; font-size: 20px;"></span>&nbsp; Logout</a></li>
        </ul>
      </div>
    </nav>
      <div id="frame" style="width: 100%;height: 100%;padding:25px;">
		<h2><u>PROFILE</u></h2>
    <div id="btn">
     <br>
      <label>NAME:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= cus.getName() %>
      <br><br>
      <label>EMAIL:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= cus.getEmail() %>
      <br><br>
      <label>MOBILE:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= cus.getMobile() %>
      <br><br>
      <label>AADHAAR NO.:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= cus.getAadhaar() %>
      <br><br>
      <label>PAN NO.:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= cus.getPan() %>
      <br><br>
      <label id="addr">ADDRESS:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p id="demo2"><%= cus.getAddress() %></p></label> 
	  </div>
    <br>
    <footer>
      Online Banking. All Rights Reserved.
    </footer>  
</div>
<script>
  function myFunction() {
    var str = document.getElementById("demo2").innerHTML;
    var n = str.length;
    var temp=""; 
     for(var i=0;i<n;i++)
     {  
       if(i==0){
        temp+=str.charAt(i);
        continue;
       }    
       if(i%40==0)
       {
         temp+="<br>";
       }
       temp+=str.charAt(i);
     }
     document.getElementById("demo2").innerHTML=temp;
  }
  
  </script>
<%  
            }else{
                %>
            <h4>Session Expired</h4>
    <%
            response.setHeader("Refresh", "3;url=../index.html");
            }
        }
    %>
</body>
</html>