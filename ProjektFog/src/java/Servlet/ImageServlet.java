/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Backend.MaterialList;
import Draw.Draw2D;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet"})
public class ImageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        //processRequest(request, response);

        int widthInt;
        int heightInt;
        int depthInt;

        String heightString;
        String widthString;
        String depthString;

        widthString = request.getParameter("width");
        heightString = request.getParameter("height");
        depthString = request.getParameter("depth");

        widthInt = Integer.valueOf(widthString);
        heightInt = Integer.valueOf(heightString);
        depthInt = Integer.valueOf(depthString);

        Draw2D d2d = new Draw2D();
        MaterialList ml = new MaterialList();
        
        ml.calcMaterialList(depthInt, widthInt);
        int roundedPrice = ml.totalPriceRounded();
        
        request.getSession().setAttribute("roundedPrice", roundedPrice);
        
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ImageIO.write(d2d.drawSide(heightInt, depthInt), "png", baos1);
        baos1.flush();
        byte[] imageInByteArray1 = baos1.toByteArray();
        baos1.close();
        String b64_Side = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray1);
        request.getSession().setAttribute("b64_Side", b64_Side);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ImageIO.write(d2d.drawFront(heightInt, widthInt), "png", baos2);
        baos2.flush();
        byte[] imageInByteArray2 = baos2.toByteArray();
        baos2.close();
        String b64_Front = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray2);
        request.getSession().setAttribute("b64_Front", b64_Front);

        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        ImageIO.write(d2d.drawRoof(widthInt, depthInt), "png", baos3);
        baos3.flush();
        byte[] imageInByteArray3 = baos3.toByteArray();
        baos3.close();
        String b64_Roof = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray3);
        request.getSession().setAttribute("b64_Roof", b64_Roof);

        request.getRequestDispatcher("/imagetest.jsp").forward(request, response);
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
