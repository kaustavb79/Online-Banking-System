<%@page import="bank.model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BANK EMPLOYEE DASHBOARD</title>
        <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="image/../logo.png" type="image/x-icon"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/1a88ea4f70.js" crossorigin="anonymous"></script>
  <style>
        .navbar-inverse .navbar-nav > li > a,
    .navbar-inverse .navbar-nav > li  a:hover,
        .navbar-inverse .navbar-nav > li  a:focus {
        color: black;
            background-color: transparent;
            font-size: 20px;

        }

        .navbar-fixed-left {
        width: 250px;
        position: fixed;
        border-radius: 0;
        height: 100%;
        margin-top: 80px;
        text-align: center;
        }

        .navbar-fixed-left .navbar-nav > li {
        float: none;  /* Cancel default li float: left */
        width: 100%;
        }

        .navbar-fixed-left + .container {
        padding-left: 160px;
        }

        /* On using dropdown menu (To right shift popuped) */
        .navbar-fixed-left .navbar-nav > li > .dropdown-menu {
        margin-top: -50px;
        margin-left: 250px;
        padding: 20px;
        font-size: 18px;

        }
        .navbar-fixed-left .dropdown > a:hover ,
        .navbar .dropdown > a:active ,
        .navbar .dropdown > a:visited,
        .navbar-fixed-left .navbar-nav:hover {
            background-color: transparent;
            list-style: none;
        }

        .navbar-fixed-top .dropdown > a:hover,.navbar-fixed-top .dropdown > a:active{
            background-color: transparent;
        }

        .navbar-fixed-left .navbar-nav li a:hover{
          background-color: transparent;
        }
</style>
<script type="text/javascript"> 
        function preventBack() { 
            window.history.forward();  
        } 
          
        setTimeout("preventBack()", 0); 
          
        window.onunload = function () { null; }; 
    </script> 
    </head>
    <body>
        <%            
            Employee m=(Employee)request.getAttribute("employee");
            Cookie ck=new Cookie("employee_id", String.valueOf(m.getEmployee_id()));
            Cookie ck1=new Cookie("ifsc_id",String.valueOf(m.getIFSCcode()));
            ck.setMaxAge(60*30);
            ck1.setMaxAge(60*30);
            response.addCookie(ck);
            response.addCookie(ck1);         
        %>
        <nav class="navbar navbar-inverse navbar-fixed-top shadow-lg p-3 mb-5 bg-white rounded" style="background-color: #a3a199; height: 80px;padding-top: 15px;">
            <div class="container-fluid">
              <div class="navbar-header">
                  <a class="navbar-brand" href="#" style="color: rgb(10, 7, 7); font-weight: bold; font-size: 20px;"><img src="../image/employee_logo.png" style="height:73px; float: left; position: relative; top: -27px;"/>&nbsp;EMPLOYEE DASHBOARD</a>
              </div>              
              <ul class="nav navbar-nav navbar-right " style="font-family: Georgia, 'Times New Roman', Times, serif; font-size: 20px; text-align: center;padding-right: 50px;">
                  <li><a href="emp_logout.jsp"><i class="fas fa-power-off"></i>&nbsp;Log Out</a></li>                
              </ul>              
            </div>
          </nav>
         
          <nav class="navbar navbar-fixed-left" style="background-color: #f2e4aa;">           
            <ul class="nav navbar-nav" style="width: 100%; list-style: none; ">
                <li><a href="employee_details.jsp" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Employee Details</a></li>  
                <li><a href="add_customer.html" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Add Customer</a></li>                  
                <li><a href="remove_customer.jsp" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Remove Customer</a></li>   
                <li><a href="listcustomer.jsp" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">List All Customer's</a></li>   
                <li><a href="update_customer.jsp" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Update Customer Details</a></li>   
                <li><a href="transactions.jsp" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Customer Account Statement</a></li>   
                <li><a href="freeze_account.jsp" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Freeze/Un-freeze Customer Account</a></li>
                <li><a href="deposit.html" target="main_frame" style="font-size: 20px; text-decoration: none; color: black;">Deposit Money</a></li>  
            </ul>           
          </nav>          
        <iframe name="main_frame" src="employee_details.jsp" style="margin-left: 260px; margin-top: 100px; width: 80%; height: 500px; border: none; "></iframe>        
    </body>
</html>

