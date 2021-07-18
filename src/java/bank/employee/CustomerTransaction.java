package bank.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.connect_dao.DBOperation;
import bank.model.Transactions;

@WebServlet(name = "CustomerTransaction", urlPatterns = {"/employee/CustomerTransaction"})
public class CustomerTransaction extends HttpServlet {

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            SimpleDateFormat format=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
            List<Transactions> records=DBOperation.fetchTransactions(Long.parseLong(request.getParameter("acc_num")));
        System.out.println("\n\n"+records.size()+"\n\n"+request.getParameter("acc_num"));
        try(PrintWriter out=response.getWriter()){
            for(Transactions tr:records){
               out.println("<tr>"); 
               out.println("<td>"+tr.getTransaction_no()+"</td>" +
                "<td>"+tr.getComment()+"</td>" +
                "<td>"+tr.getDebit()+"</td>" +
                "<td>"+tr.getCredit()+"</td>" +
                "<td>"+format.format(tr.getDateOfTransaction())+"</td>");
               out.println("</tr>");
            }      
        }
            
        
    }

}
