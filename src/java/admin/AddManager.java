package admin;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.connect_dao.SendEmail;
import bank.model.IFSCCode;
import bank.model.Manager;

@WebServlet(name = "AddManager", urlPatterns = {"/admin/AddManager"})
public class AddManager extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String address=request.getParameter("manager_address");
        String name=request.getParameter("name");
        String[] val=name.split(" ");
        String mobile=request.getParameter("mobile");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        String username=val[0].toLowerCase().concat(mobile.substring(3));
        String password="@"+val[0].toUpperCase().concat(mobile.substring(5));
        Manager m=new Manager();
        m.setName(name);
        m.setMobile(mobile);
        m.setEmail(email);
        m.setUsername(username);
        m.setPassword(password);
        m.setAddress(address);
        m.setGender(gender);
        IFSCCode ifsc=new IFSCCode();
        ifsc.setIFSC(request.getParameter("ifsc"));
        ifsc.setBranchName(request.getParameter("branch"));
        ifsc.setAddress(request.getParameter("address"));
        
        String subject="MANAGER LOGIN DETAILS";
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
"            <h3 style=\"font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;background-color: #116280;font-size: 30px;padding: 10px;color: white;\">Manager Login Details</h3>\n" +
"            <div style=\"background-color: #94d9f2;padding: 15px;\">\n" +
"                <p style=\"font-size: 23px;font-family: 'Times New Roman', Times, serif;\">Welcome "+gen+" <b>"+m.getName().toUpperCase()+"</b></p>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Your Login Details are</p><br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Username: </i></u></p>&nbsp;&nbsp;"+m.getUsername()+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>Password: </i></u></p>&nbsp;&nbsp;"+m.getPassword()+"<br>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><u><i>BRANCH: </i></u></p>&nbsp;&nbsp;"+ifsc.getIFSC()+">>>>"+ifsc.getBranchName()+"<br>\n" +
"                <br><br>\n" +
"\n" +
"                <span style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\"><a href=\"http://onlinebank-env-1.eba-qej9spxf.us-east-2.elasticbeanstalk.com/manager/manager_login.html\" style=\"text-decoration: none;font-size: 22px;font-style: italic;\">Click Here</a><br> to login into Online Banking System..</span>\n" +
"            </div>\n" +
"        </div>\n" +
"    </body>";
        
        
        if(DBOperation.addManager(m, ifsc)){
                try{
                    SendEmail.sendMail(host, port, sender_address, sender_pass, m.getEmail(), subject, message);
                }catch(MessagingException e){
                    e.printStackTrace(response.getWriter());
                }
                response.getWriter().println("Manager added!!!");
                response.setHeader("Refresh", "2;url=admin_page.jsp");
            }
        else{
            response.getWriter().println("Manager not added!!!");
            response.setHeader("Refresh", "2;url=admin_page.jsp");
        }
    }
}
