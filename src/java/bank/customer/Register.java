package bank.customer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.connect_dao.SendEmail;
import bank.model.Customer;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        String username=request.getParameter("user");
        String password=request.getParameter("pswd");
        String email=request.getParameter("email");
        String acc_num=request.getParameter("account_num");
        String name=request.getParameter("fullname");        
        Customer cus=DBOperation.returnCustomerByAccountNumber(Long.parseLong(acc_num));
        String update="update Customer set username='"+username+"' , password='"+password+"' where account_number="+cus.getAccount_number();
        String subject="ONLINE BANKING REGISTRATION";
        String message;
        String gen;
        if(cus.getGender().equals("Male")){
            gen="Mr.";
        }
        else{
            gen="Mrs.";
        }
        
        message="<body style=\"text-align: center;\">\n" +
"        <div style=\"width:100%;\">\n" +
"            <h3 style=\"font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;background-color: rgb(114, 194, 124);font-size: 30px;padding: 10px;color: white;\">Online Banking Registration Details</h3>\n" +
"            <div style=\"background-color: #C4D3DF;padding: 15px;\">\n" +
"                <p style=\"font-size: 23px;font-family: 'Times New Roman', Times, serif;\">Welcome "+gen+" <b>"+name.toUpperCase()+"</b></p>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Thank you for registering for Online Banking .</p><br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Your Login Details are</p><br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Username: </i></u></p>&nbsp;&nbsp;"+username+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Password: </i></u></p>&nbsp;&nbsp;"+password+"<br>\n" +
"                <br><br>\n" +
"\n" +
"                <span style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><a href=\"http://onlinebank-env-1.eba-qej9spxf.us-east-2.elasticbeanstalk.com/\" style=\"text-decoration: none;font-size: 22px;font-style: italic;\">Click Here</a><br> to login into Online Banking..</span>\n" +
"            </div>\n" +
"        </div>\n" +
"    </body>";
        
        if(cus!=null){
            if(!cus.getEmail().equalsIgnoreCase(email)){
                out.println("\t\t --> Invalid Email Id!!!\n\n");
            }
            else if(!cus.getName().equalsIgnoreCase(name)){
                out.println("\t\t --> Invalid Name!!!\n\n");
            }
            else{  
                DBOperation.registerCustomer(update);
                try{                    
                    SendEmail.sendMail(host, port, sender_address, sender_pass, cus.getEmail(), subject, message);
                }catch(Exception e){
                    e.printStackTrace();
                }
                out.println("\n\n\n\n\t\t\t\tSuccessfully Registered for Online Banking!!!\n\n\t\t\t Redirecting....");
                response.setHeader("Refresh","3;url=index.html");
            }
            response.setHeader("Refresh","3;url=index.html");
        }
        else{
            out.println("Invalid Account Number!!!");
            response.setHeader("Refresh","2;url=index.html");
        }
    }
}
