<%@page import="java.util.List"%>
<%@page import="bank.connect_dao.DBOperation"%>
<%@page import="bank.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
          .table th{
              text-align: center;
          }
        </style>
    </head>
    <body>
        <%
            Cookie ck=null;
            Cookie[] c=request.getCookies();

            for(int i=0;i<c.length;i++){
                if(c[i].getName().equals("employee_id")){
                    ck=c[i];
                    break;
                }
            }
            String value=ck.getValue();
            long emp_id=Integer.parseInt(value);
            List<Customer> list=DBOperation.returnAllCustomerByIFSC(emp_id);
    
        %>
        <div class="container">
             
        <table class="table table-striped" style="text-align: center;">
          <thead style="padding-left: 50px;">
            <tr>
                <th>ACCOUNT NUMBER</th>
                <th>NAME</th>
                <th>EMAIL</th>
                <th>BALANCE</th>
                <th>NET-BANKING ALLOWED</th>
                <th>ACTION</th>
            </tr>
          </thead>
          <tbody>
              <%
                  for(Customer emp:list){
              %>
            <tr>
              <td><%= emp.getAccount_number() %></td>
              <td><%= emp.getName() %></td>
              <td><%= emp.getEmail() %></td>
              <td><%= emp.getBalance() %></td>
              <td><%= emp.getNetbankingAllowed().toUpperCase() %></td>
              <td><button><a href="freeze_acc?acc_num=<%= emp.getAccount_number() %>">Enable/Disable</a></button></td>
            </tr>
                     <%
                         }
                     %>
          </tbody>
        </table>
      </div>
    </body>
</html>
