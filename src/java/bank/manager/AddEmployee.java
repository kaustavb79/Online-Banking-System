package bank.manager;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.connect_dao.SendEmail;
import bank.model.Employee;
import bank.model.Manager;

@WebServlet(name = "AddEmployee", urlPatterns = {"/manager/AddEmployee"})
public class AddEmployee extends HttpServlet {

    String host,port,sender_address,sender_pass;
    @Override
    public void init() throws ServletException {
        ServletContext context=getServletContext();
        host=context.getInitParameter("host");
        port=context.getInitParameter("port");
        sender_address=context.getInitParameter("sender_email");
        sender_pass=context.getInitParameter("password");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie ck=null;
        Cookie[] c=request.getCookies();

        for(int i=0;i<c.length;i++){
            if(c[i].getName().equals("manager_id")){
                ck=c[i];
                break;
            }
        }
        String value=ck.getValue();
        long man_id=Long.parseLong(value);
        Manager m=DBOperation.fetchManagerDetailById(man_id);

        String[] name=request.getParameter("name").split(" ");
    
        Employee emp=new Employee();
        emp.setName(request.getParameter("name"));
        emp.setEmail(request.getParameter("email"));
        emp.setMobile(request.getParameter("mobile"));
        emp.setAadhaar(request.getParameter("aadhaar"));
        emp.setGender(request.getParameter("gender"));
        emp.setUsername(name[0].toLowerCase().concat(request.getParameter("aadhaar").substring(7)));
        emp.setPassword("#"+name[0].toUpperCase().concat(request.getParameter("mobile").substring(6)));
        emp.setSalary(Double.parseDouble(request.getParameter("salary")));
        
        String subject="EMPLOYEE LOGIN DETAILS";
        String message;
        String gen;
        if(emp.getGender().equals("Male")){
            gen="Mr.";
        }
        else{
            gen="Mrs.";
        }
        
        message="<body style=\"text-align: center;\">\n" +
"        <div style=\"width:100%;\">\n" +
"            <h3 style=\"font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;background-color: #116280;font-size: 30px;padding: 10px;color: white;\">Employee Login Details</h3>\n" +
"            <div style=\"background-color: #94d9f2;padding: 15px;\">\n" +
"                <p style=\"font-size: 23px;font-family: 'Times New Roman', Times, serif;\">Welcome "+gen+" <b>"+emp.getName().toUpperCase()+"</b></p>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Your Login Details are</p><br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Username: </i></u></p>&nbsp;&nbsp;"+emp.getUsername()+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Password: </i></u></p>&nbsp;&nbsp;"+emp.getPassword()+"<br>\n" +
"                <br><br>\n" +
"\n" +
"                <span style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><a href=\"http://onlinebank-env-1.eba-qej9spxf.us-east-2.elasticbeanstalk.com/employee/employee_login.html\" style=\"text-decoration: none;font-size: 22px;font-style: italic;\">Click Here</a><br> to login into Online Banking System..</span>\n" +
"            </div>\n" +
"        </div>\n" +
"    </body>";
        
        if(DBOperation.addEmployee(emp,m.getIFSCcode())){            
                try{
                    SendEmail.sendMail(host, port, sender_address, sender_pass, emp.getEmail(), subject, message);
                }catch(Exception e){
                    e.printStackTrace(response.getWriter());
                }
            response.getWriter().println("Employee Added....\n Mail sent to "+emp.getEmail());
            response.setHeader("Refresh","2;url=list_employee.jsp");
        }
        else{
            response.getWriter().println("Error Occurred!! Employee Not added");
            response.setHeader("Refresh","3;url=manager_details.jsp");
        }
    }
}
