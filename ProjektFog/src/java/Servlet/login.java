package Servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import User.Logic.Login;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        HttpSession session = request.getSession();
        Login login = new Login();

        ArrayList<Integer> ordersArr = new ArrayList<Integer>();
        ordersArr.add(25);
        ordersArr.add(30);
        ordersArr.get(0);
        
        session.setAttribute("ordersPending", ordersArr);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (session.getAttribute("loggedIn") == null) {
            session.setAttribute("loggedIn", false);
        }
        
        if(session.getAttribute("adminLoggedIn") == null){
            session.setAttribute("adminLoggedIn", false);
        }
        
        if (request.getParameter("adminLogin") == null) {
            if (!(Boolean) session.getAttribute("loggedIn")) {
                if (login.passwordCheck(username, password)) {
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("user", login.returnUser(username));
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
        }else{
            if(!(Boolean) session.getAttribute("loggedIn") && !(Boolean) session.getAttribute("adminLoggedIn")){
                if(login.adminPasswordCheck(username, password)){
                    session.setAttribute("adminLoggedIn", true);
                    session.setAttribute("adminUser", login.returnAdminUser(username));
                    getServletContext().getRequestDispatcher("/manage.jsp").forward(request, response);
                }else{//Wrong password
                    session.setAttribute("adminLoggedIn", false);
                    getServletContext().getRequestDispatcher("/adminpanel.jsp").forward(request, response);
                }
            }else{
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
