package bank.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Manager;

@WebServlet(name = "ManagerLogin", urlPatterns = {"/manager/manager_login.do"})
public class ManagerLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Manager m=DBOperation.loginManager(username,password);
        
        if(m!=null){
            request.setAttribute("manager", m);
            RequestDispatcher rd=request.getRequestDispatcher("manager_dashboard.jsp");
            rd.forward(request, response);
        }
        else{
            out.println("Invalid username & password!!");
            response.setHeader("Refresh", "5;url=../index.html");
        }
    }
}
