package bank.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Customer;
import bank.model.Transactions;

@WebServlet(name = "Deposit", urlPatterns = {"/employee/Deposit"})
public class Deposit extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acc_num=request.getParameter("account_num");
        String amount=request.getParameter("amount");
        double amt=Double.parseDouble(amount);
        
        Customer cus=DBOperation.returnCustomerByAccountNumber(Long.parseLong(acc_num));
        Transactions tr=new Transactions();     
        tr.setComment("Deposit");
        tr.setDebit(" Rs. "+amount);
        tr.setCredit(" ");
        tr.setDateOfTransaction(new Date());
        tr.setAccount_number(cus.getAccount_number());
        
        if(cus!=null){
            double bal=cus.getBalance();            
            bal+=amt;
                        
            if(DBOperation.updateBalance(bal, Long.parseLong(acc_num)) && DBOperation.commitTransaction(tr) ){
                  response.getWriter().println("\n\n\tRs. "+amount+" deposited to account number: "+acc_num+" successfuly....\n\n");
                  response.setHeader("Refresh","2;url=listcustomer.jsp");
            }
                       
        }
        else{
                 response.getWriter().println("\n\n\t\tInvalid account number!!\n\n");
                 response.setHeader("Refresh","2;url=deposit.html");
        }
        
    }


}
