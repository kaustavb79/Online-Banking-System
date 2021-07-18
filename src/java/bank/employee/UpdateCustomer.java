package bank.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.connect_dao.DBOperation;

@WebServlet(name = "UpdateCustomer", urlPatterns = {"/employee/UpdateCustomer"})
public class UpdateCustomer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String anum=request.getParameter("acc_num");
        
        HttpSession session=request.getSession();
        String[] str=(String[])session.getAttribute("fields");
        
        //Generating the required Update Query
        String string="update Customer set";
        for(int i=0;i<str.length;i++){
           if(str[i].equalsIgnoreCase("email")){
                    string+=" "+str[i]+"='"+request.getParameter(str[i])+"'";
                }
           else if(str[i].equalsIgnoreCase("mobile")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           else if(str[i].equalsIgnoreCase("aadhaar")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           else if(str[i].equalsIgnoreCase("pan")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           else if(str[i].equalsIgnoreCase("address")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           
           if(i>=0&&i<str.length-1){
           string+=" , ";
           }
        }
        
        string+=" where account_number="+anum;
        
        if(DBOperation.updateCustomerDetails(string,anum)){
            response.getWriter().println("Record Updated!!!");
            response.setHeader("Refresh", "2;url=listcustomer.jsp");
        }
        else{
            response.getWriter().println("Error Occured!!! Please Try Later");
            response.setHeader("Refresh", "2;url=update_customer.jsp");
        }
    }

}
