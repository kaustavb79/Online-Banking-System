package bank.employee;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.connect_dao.SendEmail;
import bank.model.Customer;

@WebServlet(name = "RemoveCustomer", urlPatterns = {"/employee/RemoveCustomer"})
public class RemoveCustomer extends HttpServlet {

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
        
        String acc_num=request.getParameter("id");
        
        Customer cus=DBOperation.returnCustomerByAccountNumber(Long.parseLong(acc_num));
        
        String subject="Account Closing in Branch "+cus.getIFSCcode().getIFSC();
        
        String message="<body style=\"text-align: center;\">\n" +
"        <div style=\"width:100%;\">\n" +
"            <h3 style=\"font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;background-color: #b00707;font-size: 30px;padding: 10px;color: white;\">Account Removed</h3>\n" +
"            <div style=\"background-color: #C4D3DF;padding: 15px;\">\n" +
"                <p style=\"font-size: 23px;font-family: 'Times New Roman', Times, serif;\">Thank you Mr./Mrs. <b>"+cus.getName().toUpperCase()+"</b></p>\n" +
"                <p style=\"font-family: 'Times New Roman', Times, serif;font-size: 20px;\">Thank you for closing your "+cus.getType_of_account()+" account in our bank.</p></div></div></body>";

        if(DBOperation.removeCustomer(Long.parseLong(acc_num))){
            try{
                    SendEmail.sendMail(host, port, sender_address, sender_pass, cus.getEmail(), subject, message);
                }catch(Exception e){
                    e.printStackTrace();
                }
            response.getWriter().print("Customer Removed!!!");
            response.setHeader("Refresh", "1;url=remove_customer.jsp");
        }
        else{
            response.getWriter().println("Error Ocurred");
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
