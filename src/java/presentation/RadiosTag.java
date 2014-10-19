package presentation;

import integration.DAO;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammar
 */
public class RadiosTag extends TagSupport {

    private String tabletype;

    /**
     *
     * @param tt table type
     */
    public void setTabletype(String tt) {
        this.tabletype = tt;
    }

    /**
     *
     * @return table type
     */
    public String getTabletype() {
        return this.tabletype;
    }

    /**
     *
     * @return SKIP_BODY
     */
    @Override
    public int doStartTag() {
        DAO dao = DAO.getQuizDAO();

        try {
            JspWriter out = pageContext.getOut();

            HashMap<Integer, String> items = this.getTabletype().equals("topics") ? dao.getTopicsNames() : dao.getDifficultiesNames();
            String itemName = this.getTabletype().equals("topics") ? "topic_id" : "difficulty_id";

            Iterator i = items.keySet().iterator();
            while (i.hasNext()) {
                Integer itemId = Integer.parseInt(i.next().toString());

                out.println("<div class=\"radio\">\n"
                        + "            <label for=\"" + itemId + "\">\n"
                        + "                <input type=\"radio\" value=\"" + itemId + "\" name=\"" + itemName + "\" id=\"" + itemId + "\"/>\n"
                        + items.get(itemId) + "\n</label>\n"
                        + "        </div>");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RadiosTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RadiosTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RadiosTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    /**
     *
     * @return EVAL_PAGE
     */
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

}
