package bank.employee;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Customer;

@WebServlet(name = "FetchCustomer", urlPatterns = {"/employee/FetchCustomer"})
public class FetchCustomer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acc_num=request.getParameter("acc_num");
            Customer emp=DBOperation.returnCustomerByAccountNumber(Long.parseLong(acc_num));
            
            out.println("<br> <u>Name:</u>&nbsp;  &nbsp;&nbsp;      "+emp.getName());
            out.println("<br> <u>Email:</u>  &nbsp; &nbsp;&nbsp;     "+emp.getEmail());
            out.println("<br> <u>Mobile:</u>  &nbsp;  &nbsp;&nbsp;   "+emp.getMobile());
            out.println("<br> <u>Aadhaar No.:</u> &nbsp; "+emp.getAadhaar());
            out.println("<br> <u>Balance:</u>  &nbsp;&nbsp;&nbsp;     "+emp.getBalance());
            
        }
    }

}
