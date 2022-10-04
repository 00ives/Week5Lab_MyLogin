package Servlets;

import Models.AccountService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ivorl
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
/////////////////////////////// this part isnt working
        String logout = request.getParameter("logout");
        String message = "";
        String loggedIn = (String) session.getAttribute("loggedIn");
//        if (logout != null && logout.equals("logout")){

        if (logout != null){
            loggedIn = null;
            message = "You have successfully logged out";
            request.setAttribute("message", message);
            session.invalidate();
        }
        if (loggedIn != null){
            response.sendRedirect("home");
            return;
        }
//////////////////////////////////
        


/****************************************to make this section work don't forget to go to home.jsp and swap out the commented wals
//        String logout =  request.getAttribute("logout");

        String logout = (String) session.getAttribute("logout");
       
        if (logout != null) {
            session.invalidate();
            String message = "You have successfully logged out";
            request.setAttribute("message", message);
        }
****************************************/
        getServletContext()
                .getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//                response.sendRedirect("/home");
//                response.sendRedirect("https://www.google.ca/");
        boolean validUser = false;
        String message = "";

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.equals("") || password.equals("")) {
            message = "Username and password cannot be empty.";
//            request.setAttribute("message", message);

        } else {
            AccountService user = new AccountService();
            validUser = user.login(username, password);
        }

        if (validUser) {
            session.setAttribute("username", username);
            String loggedIn = "loggedIn";
            session.setAttribute("loggedIn" , loggedIn);
            response.sendRedirect("home");
            return;

        } else if (!validUser && !(username.equals("")) || !(password.equals(""))) {
            message = "Invalid Login";

        }
        request.setAttribute("message", message);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

}
