/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Backend.*;
import Carport.Carport;
import MaterialList.MaterialList;
import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Kasper
 */
@WebServlet(name = "Get2D", urlPatterns = {"/Get2D"})
public class Get2D extends HttpServlet {

    DataProcessor dp;

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

        HttpSession session = request.getSession();
        dp = new DataProcessor();

        String userString = (String) session.getAttribute("userString");

        String jsonStr;
        jsonStr = (String) request.getParameter("json");
        if (jsonStr != null) {
        } else {
            jsonStr = (String) session.getAttribute("json");
        }
        if (jsonStr != null) {
        } else {
            jsonStr = dp.getCarportFromUser(userString);
        }
        
        
        MaterialList priceCalc = new MaterialList();
        
        Carport carport = dp.parseJson(jsonStr);
        
        //carport not null check
        System.out.println(carport.getRoof().isGable());
        
        int price = priceCalc.calcPrice(carport);
        request.setAttribute("price", price);
        
        
        request.setAttribute("json", jsonStr);

        request.getRequestDispatcher("2D-render.jsp").forward(request, response);

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
            Logger.getLogger(Get2D.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Get2D.class.getName()).log(Level.SEVERE, null, ex);
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
