package bank.employee;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Employee;

@WebServlet(name = "EmployeeLogin", urlPatterns = {"/employee/emplogin.do"})
public class EmployeeLogin extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Employee e=DBOperation.loginEmployee(username, password);
                
        if(e!=null){
            request.setAttribute("employee", e);
            RequestDispatcher rd=request.getRequestDispatcher("employee_dashboard.jsp");
            rd.forward(request, response);
        }
        else{
            out.println("Invalid username & password!!");
            response.setHeader("Refresh", "5;url=../index.html");
        }        
    }
}
