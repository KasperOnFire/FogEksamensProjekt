/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Carport.*;
import JSON.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
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
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String json = (String) request.getParameter("json");

        //Next try, like finalproject
        JSONObject obj = new JSONObject(json);
        //base
        int width = obj.getJSONObject("guiCarport").getInt("width");
        int height = obj.getJSONObject("guiCarport").getInt("height");
        int depth = obj.getJSONObject("guiCarport").getInt("depth");
        Base b = new Base(width, depth, height);
        //roof
        boolean isGable = obj.getJSONObject("guiRoof").getBoolean("gableRoof");
        int sides = obj.getJSONObject("guiRoof").getJSONObject("overhang").getInt("sides");
        int front = obj.getJSONObject("guiRoof").getJSONObject("overhang").getInt("front");
        int back = obj.getJSONObject("guiRoof").getJSONObject("overhang").getInt("back");
        Roof r = new Roof(isGable, sides, front, back);
        //shed
        boolean hasShed = obj.getJSONObject("guiShed").getBoolean("shed");
        int depthShed = obj.getJSONObject("guiShed").getInt("depth");
        int doorPlacement = obj.getJSONObject("guiShed").getInt("doorPLacement");
        String side = obj.getJSONObject("guiShed").getString("side");
        boolean rotateDoor = obj.getJSONObject("guiShed").getBoolean("rotateDoor");
        Shed s = new Shed(hasShed, depthShed, doorPlacement, side, rotateDoor);

        //Carport
        Carport c = new Carport(b, r, s);

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
