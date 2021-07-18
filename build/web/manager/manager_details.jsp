<%@page import="bank.connect_dao.DBOperation"%>
<%@page import="bank.model.Manager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
    .table th{
        text-align: center;
    }
</style>
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
    long man_id=Integer.parseInt(value);
    
    Manager m=DBOperation.fetchManagerDetailById(man_id);
%>
<div class="container">
             
  <table class="table table-striped" style="text-align: center;">
    <thead style="padding-left: 50px;">
      <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>MOBILE</th>
        <th>EMAIL</th>
      </tr>
    </thead>
    <tbody>
      <tr>
          <td><%= m.getManager_id() %></td>
          <td><%= m.getName() %></td>
          <td><%= m.getMobile() %></td>
          <td><%= m.getEmail() %></td>
      </tr>
      
    </tbody>
  </table>
</div>

</body>
</html>
