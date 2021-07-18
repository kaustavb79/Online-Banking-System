package bank.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.connect_dao.DBOperation;

@WebServlet(name = "UpdateEmployee", urlPatterns = {"/manager/UpdateEmployee"})
public class UpdateEmployee extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emp_id=request.getParameter("emp_id");
        
        HttpSession session=request.getSession();
        String[] str=(String[])session.getAttribute("fields");
        
        //Generating the required Update Query
        String string="update Employee set";
        for(int i=0;i<str.length;i++){
           if(str[i].equalsIgnoreCase("email")){
                    string+=" "+str[i]+"='"+request.getParameter(str[i])+"'";
                }
           else if(str[i].equalsIgnoreCase("mobile")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           else if(str[i].equalsIgnoreCase("address")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           
           if(i>=0&&i<str.length-1){
           string+=" , ";
           }
        }
        
        string+=" where employee_id="+emp_id;
        
        if(DBOperation.updateEmployee(string,emp_id)){
            response.getWriter().println("Employee Record Updated!!!");
        }
        else{
            response.getWriter().println("Error Occured!!! Please Try Later");
            response.setHeader("Refresh", "2;url=update_employee.jsp");
        }
    }

}
