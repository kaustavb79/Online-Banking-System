package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.connect_dao.DBOperation;

@WebServlet(name = "UpdateManager", urlPatterns = {"/admin/UpdateManager"})
public class UpdateManager extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String man_id=request.getParameter("manager_id");
        
        HttpSession session=request.getSession();
        String[] str=(String[])session.getAttribute("fields");
        
        //Generating the required Update Query
        String string="update Manager set";
        for(int i=0;i<str.length;i++){
           if(str[i].equalsIgnoreCase("email")){
                    string+=" "+str[i]+"='"+request.getParameter(str[i])+"'";
                }
           else if(str[i].equalsIgnoreCase("mobile")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           else if(str[i].equalsIgnoreCase("address")){
                    string+=" "+str[i]+"="+request.getParameter(str[i]);
                }
           
           if(i>=0&&i<str.length-1){
           string+=" , ";
           }
        }
        
        string+=" where manager_id="+man_id;
        
        if(DBOperation.updateManager(string,man_id)){
            response.getWriter().println("Record Updated!!!");
        }
        else{
            response.getWriter().println("Error Occured!!! Please Try Later");
            response.setHeader("Refresh", "2;url=update_manager.jsp");
        }
        
    }
}
