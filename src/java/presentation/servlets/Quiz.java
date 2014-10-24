package presentation.servlets;

import business.TransferObjects.Question;
import business.TransferObjects.QuestionInterface;
import business.TransferObjects.TopicInterface;
import business.TransferObjects.User;
import business.TransferObjects.UserInterface;
import integration.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentation.Helper;

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
                    break;
                case "choose_difficulty":
                    this.saveDifficulty(request, response);
                    break;
                case "submit_answer":
                    this.processAnswer(request, response);
                    break;
                case "discard_answers":
                    this.discardAnswers(request, response);
                    break;
                case "compare":
                    Quiz.compareResult(request, response);
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

    /**
     * Save selected topic_id to Session
     *
     * @param request
     * @param response
     */
    private void saveTopic(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("topic_id") == null) {
            // get back to topic page
            try {
                response.sendRedirect("/Relay?action=/topic.jsp");
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Save topic to session
            try {
                // Empty session to prevent old session collision
                this.emptySession(request);

                Integer topicId = Integer.parseInt(request.getParameter("topic_id"));
                HttpSession session = request.getSession(true);
                session.setAttribute("quizTopicId", topicId);
            } catch (Exception ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                // redirect to difficulty page
                response.sendRedirect("/Relay?action=/difficulty.jsp");
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Save selected difficulty to Session
     *
     * @param request
     * @param response
     */
    private void saveDifficulty(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("difficulty_id") == null) {
            try {
                // get back to difficulty page
                response.sendRedirect("/Relay?action=/difficulty.jsp");
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Save topic to session
            try {
                // Empty session to prevent old session collision

                Integer difficultyId = Integer.parseInt(request.getParameter("difficulty_id"));
                HttpSession session = request.getSession(true);
                session.setAttribute("quizDifficultyId", difficultyId);
            } catch (Exception ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Process answering questions
            try {
                this.processQuestions(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void processAnswer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // check submitted answer
        if (request.getParameter("answer") == null) {
            // keep the contestant on the same page and display error message
            String msg = "You must select answer to continue";
            session.setAttribute("error", msg);
            RequestDispatcher rd = request.getRequestDispatcher("quiz.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // get answer number parameter
            Integer contestantAnswer = request.getParameter("answer") != null ? Integer.parseInt(request.getParameter("answer")) : 0;
            // get the random question from session
            QuestionInterface question = (QuestionInterface) session.getAttribute("quizQuestion");
            if (contestantAnswer == question.getCorrectAnswer()) {
                Double quizScore = session.getAttribute("quizScore") != null ? Double.parseDouble(session.getAttribute("quizScore").toString()) : 0;
                // increase 1 to the result in session
                Boolean answersDiscarded = session.getAttribute("answersDiscarded") != null ? Boolean.parseBoolean(session.getAttribute("answersDiscarded").toString()) : false;
                if (answersDiscarded) {
                    quizScore += 0.5;
                } else {
                    quizScore++;
                }
                // Save updated score in the session
                session.setAttribute("quizScore", quizScore);
            }
            // Remove indicator for answersDiscared, so discarding button could appear in the next question
            session.removeAttribute("answersDiscarded");
            // forward to answer next question (call this.processQuestions)
            try {
                this.processQuestions(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void processQuestions(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

        DAO dao = DAO.getQuizDAO();
        HttpSession session = request.getSession();
        // get current counter, it not set new one
        Integer quizCounter = session.getAttribute("quizCounter") != null ? Integer.parseInt(session.getAttribute("quizCounter").toString()) : 0;
        Integer quizTopicId = session.getAttribute("quizTopicId") != null ? Integer.parseInt(session.getAttribute("quizTopicId").toString()) : 0;
        Integer quizDifficultyId = session.getAttribute("quizDifficultyId") != null ? Integer.parseInt(session.getAttribute("quizDifficultyId").toString()) : 0;

        // Redirect to topic.jsp if topic_id is not set in session
        if (quizTopicId == 0) {
            try {
                response.sendRedirect("/topic.jsp");
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // get questions list
        ArrayList<Integer> questions = session.getAttribute("questionsList") != null ? (ArrayList<Integer>) session.getAttribute("questionsList") : new ArrayList<Integer>();

        // if counter is in range
        if (quizCounter < DAO.quizQuestions) {
            // get random question
            QuestionInterface randomQuestion = dao.getRandomQuestion(quizTopicId, quizDifficultyId);
            if (randomQuestion == null) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, "randomQuestion is null");
                // empty session
                this.emptySession(request);
                // redirect to home, with error message
                String msg = "Couldn't get random question!";
                session.setAttribute("error", msg);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // save random question to session questionsList
            session.setAttribute("quizQuestion", randomQuestion);

            Integer questionId = randomQuestion.getId();
            if (questionId != null) {
                questions.add(questionId);
                session.setAttribute("questionsList", questions);
            }

            try {
                // increase counter by 1
                session.setAttribute("quizCounter", ++quizCounter);
                // forward to quiz.jsp
                RequestDispatcher rd = request.getRequestDispatcher("quiz.jsp");
                rd.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Here: quiz counter not in range (finish answering the quiz)

            // Save the result to database:
            this.saveQuizScore(request, response);
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

    private void emptySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("quizScore");
        session.removeAttribute("quizCounter");
        session.removeAttribute("quizTopicId");
        session.removeAttribute("questionsList");
        session.removeAttribute("quizQuestion");
        session.removeAttribute("answersDiscarded");
    }

    private void saveQuizScore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
        DAO dao = DAO.getQuizDAO();
        HttpSession session = request.getSession();
        Integer quizTopicId = Integer.parseInt(session.getAttribute("quizTopicId").toString());
        Integer difficultyId = session.getAttribute("difficultyId") != null ? Integer.parseInt(session.getAttribute("difficultyId").toString()) : 1;
        Double score = session.getAttribute("quizScore") != null ? Double.parseDouble(session.getAttribute("quizScore").toString()) : 0;

        // save score to database
        UserInterface user = (User) session.getAttribute("user");
        dao.addScore(user.getId(), quizTopicId, difficultyId, score);
    }

    /**
     *
     * @param request Request
     * @param response Response
     * @return comparsion result
     */
    static public String compareResult(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String message = "We couldn't find your score for the selected topic, may be you didn't take a quiz in this topic yet?";
        if (request.getParameter("topic_id") == null) {
            message = "Please select topic first!";
            return message;
        }
        // get topicId
        Integer topicId = Integer.parseInt(request.getParameter("topic_id").toString());
        UserInterface user = (User) session.getAttribute("user");
        DAO dao = DAO.getQuizDAO();
        try {
            Integer contestantScore = dao.getScoreOrder(user.getId(), topicId);
            TopicInterface topic = dao.getTopic(topicId);
            String ordinal = Helper.getOrdinalFor(contestantScore);
            message = "You are " + ordinal + " scorer in " + topic.getName() + " topic!";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }

    /**
     * Discard randomly 2 incorrect answers
     *
     * @param request [description]
     * @param response [description]
     */
    private void discardAnswers(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Boolean answersDiscarded = session.getAttribute("answersDiscarded") != null ? Boolean.parseBoolean(session.getAttribute("answersDiscarded").toString()) : false;
        if (!answersDiscarded) {

            // get question from session
            QuestionInterface question = (Question) session.getAttribute("quizQuestion");

            // get the correct answer
            Integer correctAnswer = question.getCorrectAnswer();

            // get random number between 1-4 and not equal the correct answer
            Integer firstRandomIncorrectAnswer = Helper.getRandomNumber(1, 4, correctAnswer);
            Integer secondRandomIncorrectAnswer = Helper.getRandomNumber(1, 4, correctAnswer, firstRandomIncorrectAnswer);

            // update question in session by making the 2 incorrect answer = null
            question = this.discardOneQuestion(question, firstRandomIncorrectAnswer);
            question = this.discardOneQuestion(question, secondRandomIncorrectAnswer);

            // save question to session
            session.setAttribute("quizQuestion", question);
            // save indicator to session, to hide discarding answers button
            session.setAttribute("answersDiscarded", true);
        }
        // redirect to the question page
        RequestDispatcher rd = request.getRequestDispatcher("quiz.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private QuestionInterface discardOneQuestion(QuestionInterface question, Integer incorrectAnswer) {
        switch (incorrectAnswer) {
            case 1:
                question.setAnswer_1(null);
                break;
            case 2:
                question.setAnswer_2(null);
                break;
            case 3:
                question.setAnswer_3(null);
                break;
            case 4:
                question.setAnswer_4(null);
                break;
        }
        return question;
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
