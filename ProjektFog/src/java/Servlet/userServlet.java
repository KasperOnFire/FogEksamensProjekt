/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import User.Logic.userFront;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author T430
 */
@WebServlet(name = "userServlet", urlPatterns = {"/userServlet"})
public class userServlet extends HttpServlet {

    userFront uF = null;

    public userServlet() {
        this.uF = new userFront();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();

            if (request.getParameter("claimOrder") != null) {
                uF.claimUser(request.getParameter("claimOrder"), Integer.parseInt(request.getParameter("claimOno")));
                session.setAttribute("ordersPending", uF.getAllOrders());
                getServletContext().getRequestDispatcher("/manage.jsp").forward(request, response);
            }

            if (request.getParameter("updateStatus") != null) {
                uF.updateStatus(Integer.parseInt(request.getParameter("ono")), Integer.parseInt(request.getParameter("status")));
                session.setAttribute("ordersPending", uF.getAllOrders());

                getServletContext().getRequestDispatcher("/manage.jsp").forward(request, response);
            }

            if (request.getParameter("refreshOrders") != null) {
                session.setAttribute("ordersPending", uF.getAllOrders());
                getServletContext().getRequestDispatcher("/manage.jsp").forward(request, response);
            }

            if (request.getParameter("retrieveOrders") != null) {
                switch (request.getParameter("retrieveOrders")) {
                    case "allOrders":
                        session.setAttribute("ordersPending", uF.getAllOrders());
                        session.setAttribute("nameType", "Alle ordrer");
                        break;
                    case "claimedOrders":
                        session.setAttribute("ordersPending", uF.getClaimedOrders(Integer.parseInt((String) session.getAttribute("empNo"))));
                        session.setAttribute("nameType", "Mine ordrer");
                        break;
                    case "finishedOrders":
                        session.setAttribute("ordersPending", uF.getFinishedOrders());
                        session.setAttribute("nameType", "Færdige ordrer");
                        break;
                    case "searchOrder":
                        session.setAttribute("ordersPending", uF.getSearchOrder(Integer.parseInt(request.getParameter("orderNumber"))));
                        session.setAttribute("nameType", "Søgning i ordrer");
                        break;
                    case "notClaimed":
                        session.setAttribute("ordersPending", uF.getNotClaimed());
                        session.setAttribute("nameType", "Afventede ordrer");
                        break;
                }
                getServletContext().getRequestDispatcher("/manage.jsp").forward(request, response);
            }

            if (request.getParameter("material") != null) {
                //Hent ALLE materialer med henblik på at ændre pris / slette / tilføje nye
                session.setAttribute("materials", uF.getAllMaterials());
                getServletContext().getRequestDispatcher("/material.jsp").forward(request, response);
            }

            if (request.getParameter("updatePrice") != null) {
                uF.updatePrice(Integer.parseInt(request.getParameter("mno")), Integer.parseInt(request.getParameter("price")));
                session.setAttribute("materials", uF.getAllMaterials());
                getServletContext().getRequestDispatcher("/material.jsp").forward(request, response);
            }

            if (request.getParameter("newMaterial") != null) {
                uF.newMaterial(request.getParameter("type"), Integer.parseInt(request.getParameter("price")), request.getParameter("name"), Integer.parseInt(request.getParameter("qoh")), Integer.parseInt(request.getParameter("size")));
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
        processRequest(request, response);
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
        processRequest(request, response);
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
