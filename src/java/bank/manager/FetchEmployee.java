package bank.manager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Employee;

@WebServlet(name = "FetchEmployee", urlPatterns = {"/manager/employee.do"})
public class FetchEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String emp_id=request.getParameter("emp_id");
            Employee emp=DBOperation.fetchEmployeeById(Long.parseLong(emp_id));
            
            out.println("<br> <u>Name:</u>&nbsp;  &nbsp;&nbsp;      "+emp.getName());
            out.println("<br> <u>Email:</u>  &nbsp; &nbsp;&nbsp;     "+emp.getEmail());
            out.println("<br> <u>Mobile:</u>  &nbsp;  &nbsp;&nbsp;   "+emp.getMobile());
            out.println("<br> <u>Aadhaar No.:</u> &nbsp; "+emp.getAadhaar());
            out.println("<br> <u>Salary:</u>  &nbsp;&nbsp;&nbsp;     "+emp.getSalary());
            
        }
    }

}
