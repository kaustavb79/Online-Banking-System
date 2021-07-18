<%@page import="bank.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="bank.connect_dao.DBOperation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                            var account_num=$(this).val();
                           $.ajax({
                               method:"get",
                               url: "FetchCustomer",
                               data: {acc_num:account_num},
                               success: function (data, textStatus, jqXHR) {
                                    $("#cus_detatils").html(data);
                                    
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown);
                                }
                                
                           });
                           
                          var link = $(this).parents().find("#link");
                           window.console.log(""+$(this).val());
                           link.attr("href", "RemoveCustomer?id=" + $(this).val());
                        });
                        
                   
                    });
                    
                    
                   
                </script>
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
    <div class="container-fluid" style="text-align: center;padding-top: 30px;">
        <form id="#form" name="customer">
            <div class="form-group" style="font-size: 20px;">
                <label for="sel1">Select Customer:</label>
                <select class="form-control text-center" id="sel1" >
                    <option value="">Select a Customer</option>
                  <%
                      for(Customer cus:list){
                  %>
                  <option value="<%= cus.getAccount_number() %>"><%= cus.getAccount_number()+"-----"+cus.getName() %></option>
                  <%
                      }
                  %>
                </select>
              </div> 
        </form>
        
                <div class="row" id="cus_detatils" style="height: 200px; font-family: cursive; font-size:25px; ">
                    
                </div>   
    </div>
    <a href="" id="link"><button id="button" class="btn btn-primary text-center" style="margin-top: 50px;margin-left: 43%;">Remove Customer</button></a>
     
</body>
</html>