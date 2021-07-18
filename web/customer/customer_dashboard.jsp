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
        position: fixed;
        bottom: 0;
        width: 88%;
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
	  
        <table class="table text-center">
        <thead class="thead-dark">
          <tr>
            <th class="text-center">ACCOUNT NUMBER</th>
            <th class="text-center">TYPE OF ACCOUNT</th>
            <th class="text-center">EMAIL ID</th>
            <th class="text-center">MOBILE NO.</th>
            <th class="text-center">BALANCE</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><%= cus.getAccount_number() %></td>
            <td><%= cus.getType_of_account() %></td>
            <td><%= cus.getEmail() %></td>
            <td><%= cus.getMobile() %></td>
            <td>Rs. <%= cus.getBalance() %></td>
          </tr>
        </tbody>
      </table>
	  </div>
    <br>
    <footer>
      Online Banking. All Rights Reserved.
    </footer>  
</div>
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