package bank.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.connect_dao.DBOperation;
import bank.model.Customer;
import bank.model.Transactions;

@WebServlet(name = "Transfer", urlPatterns = {"/customer/Transfer"})
public class Transfer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        HttpSession s=request.getSession();
        Customer cust_debit=(Customer)s.getAttribute("customer");
        Date dateOfTransaction=new Date();
        String name=request.getParameter("name");
        long account_number=Long.parseLong(request.getParameter("account_num"));
        double amount=Double.parseDouble(request.getParameter("amount"));
        String branch=request.getParameter("branch");
        String ifsc=null;
        if(branch==null){
            ifsc=request.getParameter("ifsc");
        }
        
        String comment_credit="Fund Transfer by "+cust_debit.getName().toUpperCase()+".";
        String comment_debit="Transfer to "+account_number+"\n Benificiary Name: "+name;
        
        Customer cust_credit=DBOperation.returnCustomerByAccountNumber(account_number);
        if(cust_credit==null){
            out.println("Invalid Account Number!!!\nRedirecting");
                response.setHeader("Refresh","2,url=transfer.jsp");
        }
        if(ifsc!=null){
            if(!(cust_credit.getIFSCcode().getIFSC().equals(ifsc))){
                out.println("Invalid IFSC Code!!!\nRedirecting");
                    response.setHeader("Refresh","2,url=transfer.jsp");
            }
        }
        double cust_debit_balance=cust_debit.getBalance()-amount;
        double cust_credit_balance=cust_credit.getBalance()+amount;
        
        Transactions debit=new Transactions();
        debit.setComment(comment_debit);
        debit.setCredit(" ");
        debit.setDebit("Rs. "+String.valueOf(amount));
        debit.setAccount_number(cust_debit.getAccount_number());        
        debit.setDateOfTransaction(dateOfTransaction);
        
        Transactions credit=new Transactions();
        credit.setComment(comment_credit);
        credit.setCredit("Rs. "+String.valueOf(amount));
        credit.setDebit("");
        credit.setAccount_number(account_number);
        credit.setDateOfTransaction(dateOfTransaction);
        
        if(cust_debit.getBalance()>amount && cust_debit_balance>0){            
            if(DBOperation.updateBalance(cust_credit_balance, account_number)&&DBOperation.updateBalance(cust_debit_balance, cust_debit.getAccount_number())){
                if(DBOperation.commitTransaction(debit)&&DBOperation.commitTransaction(credit)){
                    out.println("Transaction Successfull.....Redirecting");
                    response.setHeader("Refresh","2,url=account_statement.jsp");
                }
                else{
                    out.println("Transaction Failed.....Redirecting");
                    response.setHeader("Refresh","2,url=transfer.jsp");
                }
            }
        }
        else{
            out.println("Amount specified is more than your balance / your balance after debit is less than 0...Redirecting");
            response.setHeader("Refresh","2,url=transfer.jsp");
        }
        
    }
}
