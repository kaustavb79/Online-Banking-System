
<%@page import="java.util.List"%>
<%@page import="bank.model.Customer"%>
<%@page import="bank.connect_dao.DBOperation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

  <style>
      select,option{
        text-align: center;
        font-size: 20px;
        padding: 5px;
      }
     
     form{
         padding-top: 20px;
         text-align: center;
         font-size: 24px;
         font-family: 'Courier New', Courier, monospace;
     }

     input[type=submit]{
         padding:7px;
         width:150px;
         border-radius: 4px;
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
<form action="update_customer_next.jsp" method="POST">
    <div class="form-group">
        <label for="sel1">Select Customer Account Number: </label>
        
        <select class="form-control" id="sel1" name="account_num">
            <% for(Customer cus:list){ %>
          <option value="<%= cus.getAccount_number() %>"><%= cus.getAccount_number()+"-----"+cus.getName() %></option>
          <% } %>
        </select>
        <br>
        <label for="sel2">Field(s) to be Updated:</label>
        <select multiple class="form-control" id="sel2" name="fields">
          <option value="email">Email Id</option>
          <option value="mobile">Mobile</option>
          <option value="aadhaar">Aadhaar</option>
          <option value="pan">Pan</option>
          <option value="address">Address</option>
        </select>
      </div>
<br>
      <input type="submit" value="Next"/>
</form>

</body>
</html>
