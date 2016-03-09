package ch.bfh.web;

import ch.bfh.ejb.SecureServiceEJBLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Peter Andres
 *
 * Simple test Servlet
 */
public class SecureServlet extends HttpServlet {

    private static String USER = "user";
    private static String ADMINISTRATOR = "administrator";

    @EJB
    SecureServiceEJBLocal secureBean;
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String auth = request.getAuthType();
            String user = request.getRemoteUser();
            boolean secure = request.isSecure();
            String role = "NONE";

            //check roles
            if (request.isUserInRole(USER)) {
                role = USER;
            } else if (request.isUserInRole(ADMINISTRATOR)) {
                role = ADMINISTRATOR;
            }

            //call out to the EJB
            String str = secureBean.echo("Call from webclient");
            String confidentalStr = secureBean.readConfidentalData();
            String internalStr = secureBean.readInternalData();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JEE7 Security - Kurs</title>");
            out.println("<meta name=\"description\" content=\"JEE7 Security Kurs 2016\"/>");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Das ist ein geschütztes Servlet</h1>");
            out.println("<br>");
            out.println("<h3>Deployed unter: " + request.getContextPath() + "</h3>");
            out.println("<h3>Akueller User = " + user + "</h3>");
            out.println("<h3>Akueller Rolle = " + role + "</h3>");
            out.println("<h3>Akuelle Authentisierungsart = " + auth + "</h3>");
            out.println("<h3>Per SSL erschlossen = " + secure + "</h3>");
            out.println("<br>");
            out.println("<br>");
            out.println("<h3>EJB Business-Call Echo-Service (alle Zugriff): " + str + "</h3>");
            out.println("<h3>EJB Business-Call readInternalData (User und Admin-Zugriff): " + internalStr + "</h3>");
            out.println("<h3>EJB Business-Call readConfidentalData (Nur Admin-Zugriff): " + confidentalStr + "</h3>");
            
            
            out.println("<br>");
            out.println("<a href='../index.html'>Home</a>");
            out.println("<br><br>");
            out.println("</body>");
            out.println("</html>");
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
    }

}
