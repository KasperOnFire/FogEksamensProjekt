package Servlet;

import User.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import User.Logic.CreateUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/createuser"})
public class createuser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        HttpSession session = request.getSession();
        CreateUser CU = new CreateUser();
        
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
//SHIT DOESNT WORK FROM HERE
        if (CU.checkIfAvaible(username)) {
            if (CU.insertUser(username, password, email)) {
                session.setAttribute("loggedIn", true);
                User u = CU.returnUser(username);
                session.setAttribute("user", u);
                session.setAttribute("currentUser", u.getUname());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                String eMessage = "Something went wrong!";
                request.setAttribute("errorCode", eMessage);
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }
        } else {
            String eMessage = "Username already in use!";
            request.setAttribute("errorCode", eMessage);
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(createuser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(createuser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
