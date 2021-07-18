package bank.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Customer;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CustomerLogin", urlPatterns = {"/login_process"})
public class CustomerLogin extends HttpServlet {

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean check=false;
        String username=request.getParameter("username");
        String password=request.getParameter("pswd");        
        Customer c=DBOperation.loginCustomer(username,password);
        HttpSession session=request.getSession(true); 
        //Session Timeout after 20 Mins.
        session.setMaxInactiveInterval(1200);
        if(c!=null){
            session.setAttribute("account_num", c.getAccount_number());
            response.sendRedirect("customer/customer_dashboard.jsp");
        }
        else{
            response.getWriter().print("Invalid Username Or Password!!!");
            response.setHeader("Refresh","2;url=index.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }
}
