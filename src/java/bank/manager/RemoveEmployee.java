package bank.manager;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.connect_dao.SendEmail;
import bank.model.Employee;

@WebServlet(name = "RemoveEmployee", urlPatterns = {"/manager/RemoveEmployee"})
public class RemoveEmployee extends HttpServlet {

    String host,port,sender_address,sender_pass;
    @Override
    public void init() throws ServletException {
        ServletContext context=getServletContext();
        host=context.getInitParameter("host");
        port=context.getInitParameter("port");
        sender_address=context.getInitParameter("sender_email");
        sender_pass=context.getInitParameter("password");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String emp_id=request.getParameter("id");
        
        Employee emp=DBOperation.fetchEmployeeById(Long.parseLong(emp_id));
        
        String subject="EMPLOYEE REMOVAL";
        String gen;
        if(emp.getGender().equals("Male")){
            gen="Mr.";
        }
        else{
            gen="Mrs.";
        }
        
        String message="<body style=\"text-align: center;\">\n" +
"        <div style=\"width:100%;\">\n" +
"            <h3 style=\"font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;background-color: rgb(114, 194, 124);font-size: 30px;padding: 10px;color: white;\">THANK YOU</h3>\n" +
"            <div style=\"background-color: #C4D3DF;padding: 15px;\">\n" +
"                <p style=\"font-size: 23px;font-family: 'Times New Roman', Times, serif;font-style:italic;\">"+gen+" <b>"+emp.getName().toUpperCase()+"</b></p>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;font-style:italic;\">On behalf of '<b>Online Banking</b>',<br> sincerely thank you for your dedication and commitment for your services.</p><br>\n" +
"				<p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;font-style:italic;\">Your work is truly commendable and we appreciate your contribution.</p><br>\n" +
"				<p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;font-style:italic;\">Thank you for your services to our bank.</p><br>\n" +
"				<br><br>\n" +
"				<p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;font-style:italic;\">Regards,<br>Online Banking<br>Branch: "+emp.getIFSCcode().getBranchName()+"<br>"+emp.getIFSCcode().getIFSC()+"</p><br>\n" +
"\n" +
"			</div>\n" +
"        </div>\n" +
"    </body>";
        
        if(DBOperation.removeEmployee(Long.parseLong(emp_id))){
            try{
                    SendEmail.sendMail(host, port, sender_address, sender_pass, emp.getEmail(), subject, message);
                }catch(Exception e){
                    e.printStackTrace();
                }
            response.getWriter().print("Employee Removed!!!");
            response.setHeader("Refresh", "2;url=remove_employee.jsp");
        }
        else{
            response.getWriter().println("Error Ocurred");
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
