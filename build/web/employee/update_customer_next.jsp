<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <style>
      form{
            text-align: center;
            padding: 50px;
      }
      label{
          font-size: 18px;
          font-style: italic;
          font-family: 'Courier New', Courier, monospace;
          
      }
      input[type=text],[type=email]{
          padding: 10px;
          text-align: center;
          font-size: 18px;
      }
      input[type=submit]{
        padding: 10px;
          text-align: center;
          font-size: 20px;
          text-transform: uppercase;
          border-radius: 10px;
          background-color: aquamarine;

      }
      textarea{
         width: 400px;
         height:100px;
         font-family: sans-serif;
         font-size: 18px;
         top:25px;
         position: relative;
     }
	  </style>
  
</head>
<body>
    <%
        String acc_num=request.getParameter("account_num");
        String[] fields=request.getParameterValues("fields");
        session.setAttribute("fields", fields);
    %>
    <form action="UpdateCustomer" method="POST">        
        <label for="anum">Account Number: &nbsp;</label>
        <input type="text" name="acc_num" id="anum" value="<%= acc_num %>" readonly/>
        <br><br>
        <%  for(String field:fields) { %>
        <label for="usr"><%= field.toUpperCase() %>: &nbsp;</label>
        <% 
            if(field.equalsIgnoreCase("address")){            
        %>
            <textarea name="<%= field %>" id="usr" required></textarea><br><br>
        <%  
            } 
            if(field.equalsIgnoreCase("mobile") || field.equalsIgnoreCase("pan")){              
        %>
            <input type="text"  name="<%= field %>" id="usr" maxlength="10" minlength="10" required/>
            <br><br>
        <%  
            }
            if(field.equalsIgnoreCase("aadhaar")){
        %>
            <input type="text" name="<%= field %>" id="usr" maxlength="12" minlength="12" required/>
            <br><br>
        <%
            }
            if(field.equalsIgnoreCase("email")){
        %>
        <input type="email" name="<%= field %>" id="usr" required/>
            <br><br>
        <%
            }
          } 
        %>        
        <br><br><br>
        <input  type="submit" value="UPDATE"/>
    </form>
</body>
</html>

