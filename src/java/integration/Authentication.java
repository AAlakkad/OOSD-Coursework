package integration;

import business.TransferObjects.User;
import business.TransferObjects.UserInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ammar
 */
public class Authentication {

    /**
     *
     * @param request
     * @return
     */
    public static boolean isLoggedIn(HttpServletRequest request) {
        UserInterface user = getUserFromRequest(request);
        if (user != null) {
            if (user.isAdministrator() || user.isContestant()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param request
     * @return
     */
    public static boolean isAdministrator(HttpServletRequest request) {
        UserInterface user = getUserFromRequest(request);
        if (user != null) {
            return user.isAdministrator();
        }
        return false;
    }

    private static User getUserFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = new User();
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
        return user;
    }

    /**
     *
     * @param request
     * @param response
     */
    public static void redirectLogIn(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("error", "You must log in to view that page.");
            response.sendRedirect("Relay?action=/login.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    public static void mustBeLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        if(! isLoggedIn(request)) {
            redirectLogIn(request, response);
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    public static void mustBeAdministrator(HttpServletRequest request, HttpServletResponse response) {
        if(! isAdministrator(request)) {
            redirectLogIn(request, response);
        }
    }
}
