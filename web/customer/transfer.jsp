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
	  h3{
			text-align: center;
			padding:35px;
			font-weight: bolder;
		}
		input[type=submit]{
			margin-left: 40%;
			margin-top: 20px;
		}
		input[type=reset]{
			margin-top: 20px;
		}
		input[type=text]{
			text-align: center;
			font-size: 18px;
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
			sess.setAttribute("customer", cus);			
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
	  <% if(cus.getNetbankingAllowed().equalsIgnoreCase("yes")){ %>
		<h3>FUND TRANSFER PAGE</h3>
        <div class="container">
            <form id="form" class="form-horizontal" action="Transfer" method="post">
                <div class="form-group">
                  <label class="control-label col-sm-5" for="name">Benificiary Name:</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="name" name="name" required/><br>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-5" for="anum">Account Number:</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="anum" name="account_num" minlength="8" maxlength="8" onkeyup="accountNum()" required/><br><span id="s1"></span>
                  </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="canum">Confirm Account Number:</label>
                    <div class="col-sm-3">
                      <input type="text" class="form-control" id="canum" minlength="8" maxlength="8" onmouseout="checkAccount()" required/><br><span id="s2"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="amt">Amount:</label>
                    <div class="col-sm-3">
                      <input type="text" class="form-control" id="amt" name="amount" maxlength="7" onmouseout="balance()" required/><br><span id="s3"></span>
                    </div>
                </div>
                <div id="radio" style="text-align: center;">
                    <label class="control-label" style="margin-right: 50px;">Branch:</label>
                    <label class="radio-inline"><input type="radio" id="sbranch" name="branch" value="<%= cus.getIFSCcode().getIFSC() %>" checked>Same Branch</label>
                    <label class="radio-inline"><input type="radio" id="obranch" name="branch" value="">Other Branch</label>
                </div><br>
                <input type="submit" id="submit" value="SUBMIT" class="btn btn-default"/>
                <input type="reset" value="RESET" class="btn btn-default"/>
            </form> 
        </div>
		<%  
		}else{
		%>
			<h4>Net- Banking Disabled!!!<br> Contact your respective branch...</h4>
		<%		
		}
                 %>
	  </div>
    <br>
    <footer>
      Online Banking. All Rights Reserved.
    </footer>  
</div>
<script>
            function accountNum(){
                var anum=document.getElementById("anum").value;
                var s1=document.getElementById("s1");
                if(isNaN(anum)){
                    s1.innerHTML="Invalid Account Number!!";
                    s1.color="RED";
                }
                else{
                    s1.innerHTML="";
                }
            };

            function checkAccount(){
                var anum=document.getElementById("anum").value;
                var canum=document.getElementById("canum").value;
                var s2=document.getElementById("s2");
                if(anum===canum){
                    s2.innerHTML="";
                }
                else{
                    s2.innerHTML="Account Number Doens't Match!!";
                }
            };

            function balance(){
                var amt=document.getElementById("amt").value;
                var s3=document.getElementById("s3");
                var amount=parseInt(amt);
                console.log(amount);
                if(isNaN(amt)){
                    s3.innerHTML="Invalid Amount!!";
                    s3.innerHTML.color="RED";
                }else if(amount<0 || amount>=1000001){
                    s3.innerHTML="Amount Should Be greater than Rs.0 and Less than equal to Rs. 10,00,000(10 Lacks)!!";
                    s3.innerHTML.color="RED";
                }
                else{
                    s3.innerHTML="";
                }
            };

            document.getElementById("obranch").addEventListener("click",function(){
                document.getElementById("radio").insertAdjacentHTML("afterend","<br><div id='demo' class='form-group'>"+
                    "<label class='control-label col-sm-5' for='ifsc'>IFSC Code:</label>"+
                  "<div class='col-sm-3'>"+
                    "<input type='text' class='form-control' id='ifsc' name='ifsc' required/>"+
                    "</div></div>");
            });
            document.getElementById("sbranch").addEventListener("click",function(){
                document.getElementById("demo").remove();
            });
            
            document.getElementById("submit").addEventListener("click",function(){
                document.getElementById("submit").disabled=true;
                document.getElementById("reset").disabled=true;
            });
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