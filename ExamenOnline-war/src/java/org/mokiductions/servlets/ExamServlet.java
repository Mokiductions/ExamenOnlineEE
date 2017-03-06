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
import java.util.Collections;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mokiductions.entities.Questions;
import org.mokiductions.sessionbeans.QuestionsFacade;

/**
 *
 * @author gines
 */
@PermitAll
public class ExamServlet extends HttpServlet {

    @EJB
    private QuestionsFacade questionsFacade;

    private Gson gson;

    public ExamServlet() {
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
    @PermitAll
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
//            out.print(gson.toJson(questionsFacade.findAllText()));
            out.print(gson.toJson(getRandomTest()));
        }
    }
    
    private List<Questions> getRandomTest() {
        List<Questions> testQuestions = new ArrayList<>();
        List<Questions> questions;
        
        // Añade dos preguntas de tipo TEXT de forma aleatoria
        questions = questionsFacade.findAllText();
        Collections.shuffle(questions);
        if (questions.get(0) != null) testQuestions.add(questions.get(0));
        if (questions.get(1) != null) testQuestions.add(questions.get(1));
        
        // Añade dos preguntas de tipo CHECKBOX de forma aleatoria
        questions = questionsFacade.findQuestionsAndOptionsByType(2);
        Collections.shuffle(questions);
        if (questions.get(0) != null) testQuestions.add(questions.get(0));
        if (questions.get(1) != null) testQuestions.add(questions.get(1));
        
        // Añade dos preguntas de tipo SELECT de forma aleatoria
        questions = questionsFacade.findQuestionsAndOptionsByType(3);
        Collections.shuffle(questions);
        if (questions.get(0) != null) testQuestions.add(questions.get(0));
        if (questions.get(1) != null) testQuestions.add(questions.get(1));
        
        // Añade dos preguntas de tipo RADIO de forma aleatoria
        questions = questionsFacade.findQuestionsAndOptionsByType(5);
        Collections.shuffle(questions);
        if (questions.get(0) != null) testQuestions.add(questions.get(0));
        if (questions.get(1) != null) testQuestions.add(questions.get(1));
        
        // Añade dos preguntas de tipo MULTIPLE de forma aleatoria
        questions = questionsFacade.findQuestionsAndOptionsByType(6);
        Collections.shuffle(questions);
        if (questions.get(0) != null) testQuestions.add(questions.get(0));
        if (questions.get(1) != null) testQuestions.add(questions.get(1));
        
        // Ordena aleatoriamente las preguntas del examen
        Collections.shuffle(testQuestions);
        return testQuestions;
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
