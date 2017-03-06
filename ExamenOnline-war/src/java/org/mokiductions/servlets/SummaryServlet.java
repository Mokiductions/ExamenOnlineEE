/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mokiductions.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mokiductions.classes.SummaryValue;
import org.mokiductions.entities.Userdata;
import org.mokiductions.entities.Userexams;
import org.mokiductions.sessionbeans.UserdataFacade;
import org.mokiductions.sessionbeans.UserexamsFacade;

/**
 *
 * @author gines
 */
public class SummaryServlet extends HttpServlet {

    @EJB
    private UserdataFacade userdataFacade;

    @EJB
    private UserexamsFacade userexamsFacade;
    
    private Gson gson;
    
    public SummaryServlet() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<SummaryValue> summary = new ArrayList<>();
            List<Userexams> userexams = userexamsFacade.findAll();
            for (Userexams ue : userexams) {
                summary.add(new SummaryValue(userdataFacade.find(ue.getUserexamsIdUserid().getUserdataId()).getUserdataUserid(), 
                        ue.getUserexamsStarttime(), ue.getUserexamsEndtime(), ue.getScore()));
            }
            String jsonString = new Gson().toJson(summary);
            out.println(jsonString);
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
