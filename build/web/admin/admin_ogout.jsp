<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ONLINE BANKING</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/1a88ea4f70.js" crossorigin="anonymous"></script>
    </head>
    <body style="background-color: rgb(215, 236, 255);">
        <div class="container-fluid text-center" style="padding-top: 20%; color: rgb(88, 219, 99);">
            <i class="fas fa-spinner fa-spin fa-7x"></i>
            <br>
            <br>
            <p style="font-size: 35px;font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; font-weight: bold;">Redirecting.......</p>
        </div>
    </body>
    
   <%
       session.invalidate();
       System.out.println("Session Closed");
       response.setHeader("Refresh","5;url=../index.html");

   %>
</html>
