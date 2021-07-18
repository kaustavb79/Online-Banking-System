<%@page import="java.util.List"%>
<%@page import="bank.connect_dao.DBOperation"%>
<%@page import="bank.model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
                    $(document).ready(function(){
                        $("#sel1").change(function(){
                            var emp=$(this).val();
                           $.ajax({
                               method:"get",
                               url: "employee.do",
                               data: {emp_id:emp},
                               success: function (data, textStatus, jqXHR) {
                                    $("#emp_detatils").html(data);
                                    
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown);
                                }
                                
                           });
                           
                          var link = $(this).parents().find("#link");
                           window.console.log(""+$(this).val());
                           link.attr("href", "RemoveEmployee?id=" + $(this).val());
                        });
                        
                   
                    });
                    
                    
                   
                </script>
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
    long man_id=Integer.parseInt(value);
    List<Employee> list=DBOperation.returnAllEmployeeByIFSC(man_id);    
    %>
    <div class="container-fluid" style="text-align: center;padding-top: 30px;">
        <form id="#form" name="employee">
            <div class="form-group" style="font-size: 20px;">
                <label for="sel1">Select Employee:</label>
                <select class="form-control text-center" id="sel1" >
                    <option value="">Select an Employee</option>
                  <%
                      for(Employee e:list){
                  %>
                  <option value="<%= e.getEmployee_id() %>"><%= e.getEmployee_id()+"-----"+e.getName() %></option>
                  <%
                      }
                  %>
                </select>
              </div> 
        </form>        
                <div class="row" id="emp_detatils" style="height: 200px; font-family: cursive; font-size:25px; ">
                    
                </div>   
    </div>
    <a href="" id="link"><button id="button" class="btn btn-primary text-center" style="margin-top: 50px;margin-left: 43%;">Remove Employee</button></a>
     
</body>
</html>
