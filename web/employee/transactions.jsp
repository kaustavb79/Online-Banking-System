
<%@page import="bank.model.Customer"%>
<%@page import="java.util.List"%>
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
  
  <script>
      
       $(document).ready(function(){
                        $("#anum").change(function(){
                            var account_num=$(this).val();
                           $.ajax({
                               method:"post",
                               url: "CustomerTransaction",
                               data: {acc_num:account_num},
                               success: function (data, textStatus, jqXHR) {
                                    $("#record").html(data);
                                    
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown);
                                }
                                
                           });
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
    
    <div class="container" style="padding-top: 50px;">
        <form id="#form" name="customer_transactions">
        <div class="form-group">
            <label for="anum" style="font-size: 24px;margin-left: 35%;">Select Customer Account Number </label>
            <select class="form-control text-center" id="anum" name="acc_num" >
                <option value="">Select an account number</option>
                <% for(Customer cus:list){ %>
                <option value="<%= cus.getAccount_number() %>" style="text-align: center;"><%= cus.getAccount_number() %>&nbsp;/&nbsp;<%= cus.getName() %></option>
                <% } %>
            </select>
          </div>
        </form>
        <br><br><br>
        <table class="table text-center">
            
            <thead class="thead-dark">
              <tr>
                <th class="text-center">Transaction No.</th>
                <th class="text-center">Comment</th>
                <th class="text-center">Debit</th>
                <th class="text-center">Credit</th>
                <th class="text-center">Transaction Date</th>
              </tr>
            </thead>
            
            <tbody id="record">
                
            </tbody>
            
        </table>
    </div>
</body>
</html>
