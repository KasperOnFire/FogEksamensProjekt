/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Backend.*;
import Carport.*;
import User.Logic.DatabaseFront;
import User.Logic.LoginFront;
import User.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * This servlet recieves the carport in JSON format from the 3d-render-gui, and
 * tries to save it to the user.
 *
 * @author Kasper
 */
@WebServlet(name = "DataReciever", urlPatterns = {"/DataReciever"})
public class DataReciever extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        DatabaseFront DBF = new DatabaseFront();
        HttpSession session = request.getSession();
        DataProcessor dp = new DataProcessor();
        String json = (String) request.getParameter("json");
        Carport c = dp.parseJson(json);

        if (c != null) { //Hvis det lykkedes
            session.setAttribute("Carport", c);
            if ((boolean) session.getAttribute("loggedIn") == true) {
                String userString = (String) session.getAttribute("userString");
                dp.saveCarportToUser(userString, json);
                try {
                    DBF.saveCarport(userString, json);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/signup.jsp").forward(request, response); //TODO: i det servlet der handler signup, skal der være et tjek for carport - så den kan gemmes
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
            Logger.getLogger(DataReciever.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DataReciever.class.getName()).log(Level.SEVERE, null, ex);
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
