/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mokiductions.servlets;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mokiductions.classes.AnswerValue;
import org.mokiductions.classes.QuestionCorrection;
import org.mokiductions.entities.Answers;
import org.mokiductions.entities.Questions;
import org.mokiductions.entities.Useranswers;
import org.mokiductions.entities.Userdata;
import org.mokiductions.entities.Userexams;
import org.mokiductions.entities.Userquestions;
import org.mokiductions.sessionbeans.QuestionsFacade;
import org.mokiductions.sessionbeans.UseranswersFacade;
import org.mokiductions.sessionbeans.UserdataFacade;
import org.mokiductions.sessionbeans.UserexamsFacade;
import org.mokiductions.sessionbeans.UserquestionsFacade;

/**
 *
 * @author gines
 */
public class CorrectionServlet extends HttpServlet {

    @EJB
    private UseranswersFacade useranswersFacade;

    @EJB
    private UserquestionsFacade userquestionsFacade;

    @EJB
    private QuestionsFacade questionsFacade;

    @EJB
    private UserexamsFacade userexamsFacade;

    @EJB
    private UserdataFacade userdataFacade;

    private Gson gson;

    private Userdata userdata;
    private Userexams userexam;

    private List<AnswerValue> userAnswers;
    private ArrayList<AnswerValue> correctAnswers;
    private ArrayList<QuestionCorrection> corrections;

    private String uid;

    public CorrectionServlet() {
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

            uid = request.getUserPrincipal().getName();

            BufferedReader reader = request.getReader();
            String s = "";

            userAnswers = new Gson().fromJson(reader, new TypeToken<List<AnswerValue>>() {
            }.getType());

            persistUserdata();
            persistUserexams();
            persistUserquestionsAndUseranswers();
            loadCorrectExam();
            doCorrection();

            String jsonString = new Gson().toJson(corrections);
            out.println(jsonString);
        } catch (Exception e) {
        }
    }

    private long findTime() {
        for (AnswerValue av : userAnswers) {
            if (av.getName().equals("startTime")) {
                long l = Long.parseLong(av.getValue());
                userAnswers.remove(av);
                return l;
            }
        }
        return 0L;
    }

    private Userdata findUserdata(String userid) {
        for (Userdata u : userdataFacade.findAll()) {
            if (u.getUserdataUserid().equals(userid)) {
                return u;
            }
        }
        return null;
    }

    private void persistUserdata() {
        String userid = "userid3";
        userdata = findUserdata(uid);
        if (userdata == null) {
            userdata = new Userdata();
            userdata.setUserdataUserid(uid);
            userdata.setUserdataFirstname("Nombre");
            userdata.setUserdataLastname("Apellido");
            userdata.setUserdataPhonenumber(971456123);
            userdataFacade.create(userdata);
        }
    }

    private void persistUserexams() {
        Calendar cal = Calendar.getInstance();
        Date endtime = new Date();
        cal.setTimeInMillis(findTime());
        Date starttime = cal.getTime();
        userexam = new Userexams();
        userexam.setUserexamsStarttime(starttime);
        userexam.setUserexamsEndtime(endtime);
        userexam.setUserexamsIdUserid(userdata);
        userexamsFacade.create(userexam);
    }

    private void persistUserquestionsAndUseranswers() {
        Userquestions userquestions = null;
        Useranswers useranswers;
        String uqId = "";
        for (AnswerValue av : userAnswers) {
            Questions q = questionsFacade.findById(Integer.parseInt(av.getName()));
            if (!(av.getName().equals(uqId))) {
                userquestions = new Userquestions();
                userquestions.setUserquestionsIdExam(userexam);
                userquestions.setUserquestionsIdQuestion(q);
                userquestionsFacade.create(userquestions);
            }
            uqId = av.getName();
            useranswers = new Useranswers();
            useranswers.setUseranswersIdQuestion(userquestions);
            useranswers.setUserquestionsAnswer(av.getValue());
            useranswersFacade.create(useranswers);
        }
    }

    private void loadCorrectExam() {
        correctAnswers = new ArrayList<>();
        for (AnswerValue av : userAnswers) {
            Questions q = questionsFacade.findById(Integer.parseInt(av.getName()));
            List<Answers> answers = q.getAnswersList();
            for (Answers a : answers) {
                correctAnswers.add(new AnswerValue(av.getName(), a.getAnswersAnswer()));
            }
        }
    }

    private void doCorrection() {
        corrections = new ArrayList<>();
        QuestionCorrection qc = null;
        String questId = "";
        int rep = 1;
        for (int i = 0; i < userAnswers.size(); i++) {
            float score = 0;
            if (!(questId.equals(userAnswers.get(i).getName()))) {
                if (userAnswers.get(i).getValue().equals(correctAnswers.get(i).getValue())) {
                    score = 1;
                }
                qc = new QuestionCorrection(questionsFacade.findById(Integer.parseInt(userAnswers.get(i).getName())).getQuestionsTitle(),
                        userAnswers.get(i).getValue(),
                        correctAnswers.get(i).getValue(), score);
                rep = 1;
                corrections.add(qc);
            } else {
                // Es otra respuesta de la pregunta anterior
                float oldScore = qc.getScore() * rep;
                rep++;
                if (userAnswers.get(i).getValue().equals(correctAnswers.get(i).getValue())) {
                    score = 1;
                }
                if (!(correctAnswers.get(i).getValue().equals(correctAnswers.get(i - 1).getValue()))) {
                    corrections.get(corrections.size() - 1).setCorrectAnswer(corrections.get(corrections.size() - 1).getCorrectAnswer() + ", " + correctAnswers.get(i).getValue());
                }
                if (!(userAnswers.get(i).getValue().equals(userAnswers.get(i - 1).getValue()))) {
                    corrections.get(corrections.size() - 1).setUserAnswer(corrections.get(corrections.size() - 1).getUserAnswer() + ", " + userAnswers.get(i).getValue());
                }
                corrections.get(corrections.size() - 1).setScore(roundToDecimalPlaces((oldScore + score) / rep, 2));
                // calcular la puntuacion en proporcion al numero de aciertos, y actualizar la pregunta anterior
            }
            questId = userAnswers.get(i).getName();
        }
        double finalScore = 0;
        for (QuestionCorrection q : corrections) {
            finalScore += q.getScore();
        }
        userexam.setScore(finalScore);
        userexamsFacade.edit(userexam);
    }

    private float roundToDecimalPlaces(float value, int decimalPlaces) {
        double shift = Math.pow(10, decimalPlaces);
        return (float) (Math.round(value * shift) / shift);
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
