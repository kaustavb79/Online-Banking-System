package admin;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/admin.login"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context=getServletContext();
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if(username.equals(context.getInitParameter("username"))&&password.equals(context.getInitParameter("pwd"))){
            response.sendRedirect("main_page.jsp");
        }
        else{
            response.getWriter().print("Invalid username && Password!!!");
            response.setHeader("Refresh", "2;url=../index.html");
        }
    }
}
