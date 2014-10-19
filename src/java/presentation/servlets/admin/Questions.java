package presentation.servlets.admin;

//import Authentication;
import integration.Authentication;
import integration.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammar
 */
public class Questions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        if(! Authentication.isAdministrator(request)) {
            Authentication.redirectLogIn(request, response);
        }
        String subAction = request.getParameter("sub-action");
        switch (subAction) {
            case "new":
                this.processNew(request);
                break;
            case "edit":
                this.processEdit(request);
                break;
            case "delete":
                this.processDelete(request);
                break;
        }
        response.sendRedirect("/Relay?action=/admin/questions/index.jsp");
        return;
    }

    private void processNew(HttpServletRequest request) throws ClassNotFoundException {
        Integer topicId = Integer.parseInt(request.getParameter("topic_id")),
                difficultyId = Integer.parseInt(request.getParameter("difficulty_id")),
                correctAnswer = Integer.parseInt(request.getParameter("correct_answer"));
        String title = request.getParameter("title"),
                answer_1 = request.getParameter("answer_1"),
                answer_2 = request.getParameter("answer_2"),
                answer_3 = request.getParameter("answer_3"),
                answer_4 = request.getParameter("answer_4");

        DAO dao = DAO.getQuizDAO();
        try {
            dao.insertQuestion(title, topicId, difficultyId, correctAnswer, answer_1, answer_2, answer_3, answer_4);
            System.out.println("Why I'm not typing fast?");
        } catch (SQLException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processEdit(HttpServletRequest request) throws ClassNotFoundException {
        Integer id = Integer.parseInt(request.getParameter("id")),
                topicId = Integer.parseInt(request.getParameter("topic_id")),
                difficultyId = Integer.parseInt(request.getParameter("difficulty_id")),
                correctAnswer = Integer.parseInt(request.getParameter("correct_answer"));
        String title = request.getParameter("title"),
                answer_1 = request.getParameter("answer_1"),
                answer_2 = request.getParameter("answer_2"),
                answer_3 = request.getParameter("answer_3"),
                answer_4 = request.getParameter("answer_4");
        DAO dao = DAO.getQuizDAO();
        try {
            dao.updateQuestion(id, title, topicId, difficultyId, correctAnswer, answer_1, answer_2, answer_3, answer_4);

        } catch (SQLException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void processDelete(HttpServletRequest request) throws ClassNotFoundException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DAO dao = DAO.getQuizDAO();
        try {
            dao.deleteQuestion(id);

        } catch (SQLException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
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
