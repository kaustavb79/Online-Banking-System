package bank.employee;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.connect_dao.SendEmail;
import bank.model.Customer;
import bank.model.Employee;

@WebServlet(name = "AddCustomer", urlPatterns = {"/employee/AddCustomer"})
public class AddCustomer extends HttpServlet {

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
    Cookie[] cks=request.getCookies();
    
    for(int i=0;i<cks.length;i++){
        if(cks[i].getName().equals("employee_id")){
            ck=cks[i];
            break;
        }
    }
    String value=ck.getValue();
    long emp_id=Long.parseLong(value);
    Employee emp=DBOperation.fetchEmployeeById(emp_id);
        
    Random ran=new Random();
    
   long acc_num=DBOperation.returnAccountNumber();
   
   if(acc_num==0){
       acc_num+=10000000;
   }
   else{
       acc_num+=1;
   }
        Customer c=new Customer();
        String name=request.getParameter("name");
        String mobile=request.getParameter("mobile");
        String email=request.getParameter("email");
        String aadhaar=request.getParameter("aadhaar");
        String type_of_account=request.getParameter("type");
        String address=request.getParameter("address");
        Date date_of_creation=new Date();
        String pan=request.getParameter("pan");
        String gender=request.getParameter("gender");
        
        c.setAccount_number(acc_num);
        c.setName(name);
        c.setMobile(mobile);
        c.setEmail(email);
        c.setType_of_account(type_of_account);
        c.setAadhaar(aadhaar);
        c.setPan(pan);
        c.setBalance(0.00);
        c.setDate_of_creation(date_of_creation);
        c.setNetbankingAllowed("yes");
        c.setAddress(address);
        c.setGender(gender);
        
        String subject="NEW ACCOUNT OPENING";
        String message;
        String gen;
        if(gender.equals("Male")){
            gen="Mr.";
        }
        else{
            gen="Mrs.";
        }
        
        message="<body style=\"text-align: center;\">\n" +
"        <div style=\"width:100%;\">\n" +
"            <h3 style=\"font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;background-color: rgb(114, 194, 124);font-size: 30px;padding: 10px;color: white;\">Account Creation</h3>\n" +
"            <div style=\"background-color: #C4D3DF;padding: 15px;\">\n" +
"                <p style=\"font-size: 23px;font-family: 'Times New Roman', Times, serif;\">Welcome "+gen+" <b>"+name.toUpperCase()+"</b></p>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Thank you for opening a "+type_of_account+" account in our bank.</p><br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Your Account Details are</p><br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Account Number: </i></u></p>&nbsp;&nbsp;"+acc_num+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Email Id: </i></u></p>&nbsp;&nbsp;"+email+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Mobile: </i></u></p>&nbsp;&nbsp;"+mobile+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Address: </i></u></p>&nbsp;&nbsp;"+address+"<br>\n" +
"\n" +
"                <br><br>\n" +
"\n" +
"                <span style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">To register for Onine Banking, <a href=\"http://onlinebank-env-1.eba-qej9spxf.us-east-2.elasticbeanstalk.com/\" style=\"text-decoration: none;font-size: 22px;font-style: italic;\">Click Here</a><br>Click on Sign Up and fill in the Details.</span>\n" +
"            </div>\n" +
"        </div>\n" +
"    </body>";
        
        if(DBOperation.addCustomer(c,emp.getIFSCcode())){
            try{
                    SendEmail.sendMail(host, port, sender_address, sender_pass, c.getEmail(), subject, message);
                }catch(Exception e){
                    e.printStackTrace(response.getWriter());
                }
            response.getWriter().println("Customer Added");
            response.setHeader("Refresh","2;url=listcustomer.jsp");

        }
        else{
            response.getWriter().println("Error Occurred!!");
            response.setHeader("Refresh","3;url=employee_details.jsp");
        }
        
    }

    
}
