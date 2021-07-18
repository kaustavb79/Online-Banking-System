<%@page import="bank.connect_dao.DBOperation"%>
<%@page import="bank.model.Manager"%>
<%@page import="java.util.List"%>
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
            List<Manager> list=DBOperation.returnManager();    
        %>
        <div class="container">             
        <table class="table table-striped" style="text-align: center;">
          <thead style="padding-left: 50px;">
            <tr>
                <th>MANAGER ID</th>
                <th>NAME</th>
                <th>MOBILE</th>
                <th>EMAIL</th>
                <th>IFSC CODE</th>
                <th>BRANCH</th>
            </tr>
          </thead>
          <tbody>
              <% for(Manager man:list){ %>
            <tr>
              <td><%= man.getManager_id() %></td>
              <td><%= man.getName() %></td>
              <td><%= man.getMobile() %></td>
              <td><%= man.getEmail() %></td>
              <td><%= man.getIFSCcode().getIFSC() %></td>
              <td><%= man.getIFSCcode().getBranchName() %></td>
            </tr>
                     <% } %>
          </tbody>
        </table>
      </div>
    </body>
</html>
