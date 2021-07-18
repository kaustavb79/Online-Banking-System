<%@page import="java.util.List"%>
<%@page import="bank.connect_dao.DBOperation"%>
<%@page import="bank.model.Employee"%>
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
        if(c[i].getName().equals("manager_id")){
            ck=c[i];
            break;
        }
    }    
    String value=ck.getValue();
    long m_id=Integer.parseInt(value);        
    List<Employee> list=DBOperation.returnAllEmployeeByIFSC(m_id);
%>
<form action="update_employee_next.jsp" method="POST">
    <div class="form-group">
        <label for="sel1">Select Employee ID: </label>
        
        <select class="form-control" id="sel1" name="emp_id">
            <% for(Employee emp:list){ %>
          <option value="<%= emp.getEmployee_id() %>"><%= emp.getEmployee_id()+"-----"+emp.getName() %></option>
          <% } %>
        </select>
        <br>
        <label for="sel2">Field(s) to be Updated:</label>
        <select multiple class="form-control" id="sel2" name="fields">
          <option value="email">Email Id</option>
          <option value="mobile">Mobile</option>
          <option value="address">Address</option>
        </select>
      </div>
<br>
      <input type="submit" value="Next"/>
</form>

</body>
</html>
