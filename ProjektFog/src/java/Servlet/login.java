package Servlet;

import User.AdminUser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import User.Logic.LoginFront;
import User.Order;
import User.User;
import java.util.ArrayList;

/**
 * 
 * This servlet handles the login of a user or an admin
 *
 * @author Kasper
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * 
     * Handles the login request
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        HttpSession session = request.getSession();
        LoginFront login = new LoginFront();
        User user = null;
        AdminUser AdminUser = null;
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (session.getAttribute("loggedIn") == null) {
            session.setAttribute("loggedIn", false);
        }

        if (session.getAttribute("adminLoggedIn") == null) {
            session.setAttribute("adminLoggedIn", false);
        }

        if (request.getParameter("adminLogin") == null) {
            if (!(Boolean) session.getAttribute("loggedIn")) {
                if (login.passwordCheck(username, password)) {
                    
                    user = login.returnUser(username);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("username", user.getUname());
                    session.setAttribute("userString", user.getUserString());
                    session.setAttribute("carport", user.getCarport());
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    String eMessage = "Wrong username / password";
                    request.setAttribute("errorCode", eMessage);
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else {
                String eMessage = "Already logged in!";
                request.setAttribute("errorCode", eMessage);
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            if (!(Boolean) session.getAttribute("loggedIn") && !(Boolean) session.getAttribute("adminLoggedIn")) {
                if (login.adminPasswordCheck(username, password)) {
                    AdminUser = login.returnAdminUser(username);
                    ArrayList<Order> orders = login.getOrders();
                    
                    session.setAttribute("ordersPending", orders);
                    session.setAttribute("adminLoggedIn", true);
                    session.setAttribute("username", AdminUser.getUname());
                    session.setAttribute("userString", AdminUser.getUserString());
                    getServletContext().getRequestDispatcher("/manage.jsp").forward(request, response);
                } else {//Wrong password
                    session.setAttribute("adminLoggedIn", false);
                    getServletContext().getRequestDispatcher("/adminpanel.jsp").forward(request, response);
                }
            } else {
                getServletContext().getRequestDispatcher("/adminpanel.jsp").forward(request, response);
            }
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
