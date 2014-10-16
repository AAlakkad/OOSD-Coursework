/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.servlets;

import business.TransferObjects.QuestionInterface;
import integration.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ammar
 */
public class Quiz extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subAction = request.getParameter("sub_action");
        if (subAction != null) {
            switch (subAction) {
                case "choose_topic":
                    this.saveTopic(request, response);
                    try {
                        // Process answering questions
                        this.processQuestions(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "submit_answer":
                    this.processAnswer(request, response);
                    break;
            }
        } else {
            try {
                // Process answering questions
                this.processQuestions(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void processQuestions(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

        DAO dao = DAO.getQuizDAO();
        HttpSession session = request.getSession();
        // get current counter, it not set new one
        Integer quizCounter = session.getAttribute("quizCounter") != null ? Integer.parseInt(session.getAttribute("quizCounter").toString()) : 0;
        Integer quizTopicId = session.getAttribute("quizTopicId") != null ? Integer.parseInt(session.getAttribute("quizTopicId").toString()) : 1;

        // get questions list
        ArrayList<Integer> questions = session.getAttribute("questionsList") != null ? (ArrayList<Integer>) session.getAttribute("questionsList") : new ArrayList<>();

        // if counter in range 
        if (quizCounter < DAO.quizQuestions) {
            // get random question
            QuestionInterface randomQuestion = dao.getRandomQuestion(quizTopicId);

            // save random question to session questionsList
            session.setAttribute("quizQuestion", randomQuestion);

            Integer questionId = randomQuestion.getId();
            if (questionId != null) {
                questions.add(questionId);
                session.setAttribute("questionsList", questions);
            } else {
                // @TODO throw exception or do something to stop the execution
            }

            try {
                // increase counter by 1
                quizCounter++;
                session.setAttribute("quizCounter", quizCounter);
                // forward to quiz.jsp
                RequestDispatcher rd = request.getRequestDispatcher("quiz.jsp");
                rd.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // display result   
            RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Save selected topic_id to Session
     *
     * @param request
     * @param response
     */
    private void saveTopic(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Empty session to prevent old session collision
            this.emptySession(request);
            
            HttpSession session = request.getSession(true);
            Integer topicId = Integer.parseInt(request.getParameter("topic_id"));
            session.setAttribute("quizTopicId", topicId);
            RequestDispatcher rd = request.getRequestDispatcher("quiz.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processAnswer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // get the random question from session
        QuestionInterface question = (QuestionInterface) session.getAttribute("quizQuestion");
        // get answer number parameter
        Integer contestantAnswer = request.getParameter("answer") != null ? Integer.parseInt(request.getParameter("answer")) : 0;
        if (contestantAnswer == question.getCorrectAnswer()) {
            Integer score = session.getAttribute("quizScore") != null ? Integer.parseInt(session.getAttribute("quizScore").toString()) : 0;
            // increase 1 to the result in session
            score++;
            session.setAttribute("quizScore", score);
        }
        // forward to answer next question (call this.processQuestions)
        try {
            this.processQuestions(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void emptySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("quizScore");
        session.removeAttribute("quizCounter");
        session.removeAttribute("quizTopicId");
        session.removeAttribute("questionsList");
        session.removeAttribute("quizQuestion");
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
