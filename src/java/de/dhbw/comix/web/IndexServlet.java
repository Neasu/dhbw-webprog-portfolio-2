package de.dhbw.comix.web;

import de.dhbw.comix.database.Comic;
import de.dhbw.comix.database.DatabaseFacade;
import de.dhbw.comix.database.Serie;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Requenst Handler für die Startseite mit folgenden Funktionen:
 * 
 *   * Anzeige aller vorhandenen Comics
 *   * Anlage eines neuen Comics
 *   * Löschen aller ausgewählten Comics
 */
@WebServlet(urlPatterns={"/index.html"})
public class IndexServlet extends HttpServlet {
    
    @EJB DatabaseFacade database;
    
    /**
     * GET-Anfrage: Alle vorhandenen Comics anzeigen
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        
        HttpSession session = request.getSession();
        List<Serie> serienList = this.database.getAllComics();
        
        // DUMMY VALUES
        
        Serie dummy = new Serie();
        dummy.setSerie("Dummy Serie");
        
        Comic dummyC1 = new Comic();
        dummyC1.setId((long) 1);
        dummyC1.setTitel("Dummy Comic 1");
        dummyC1.setJahr(1337);
        dummyC1.setZeichner("King Graham");
        dummyC1.setTexter("Guybrush Threepwood");
        
        Comic dummyC2 = new Comic();
        dummyC2.setId((long) 1);
        dummyC2.setTitel("Dummy Comic 1");
        dummyC2.setJahr(1337);
        dummyC2.setZeichner("King Graham");
        dummyC2.setTexter("Guybrush Threepwood");
        
        List<Comic> comics = dummy.getComics();
        
        comics.add(dummyC1);
        comics.add(dummyC2);
        
        dummy.setComics(comics);
        
        Serie dummyS2 = new Serie();
        dummyS2.setSerie("Dummy Serie 2");
        
        serienList.add(dummy);
        serienList.add(dummyS2);
        
//        for (Serie s : serienList) {
//            System.out.println(s.getSerie());
//        }
        
        // Speichern der Serien in den Session-Kontext
        session.setAttribute("serien", serienList);
        
        RequestDispatcher dp = request.getRequestDispatcher("WEB-INF/index.jsp");
        dp.forward(request, response);
    }
    
    /**
     * POST-Anfrage: Neuen Comic anlegen oder Comics löschen
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        System.out.println("POST recieved!");
        
        String action = request.getParameter("action");
        
        switch(action) {
            case "create": {
                System.out.println("CREATE BUTTON");
                break;
            }
            case "remove": {
                System.out.println("REMOVE BUTTON");
                break;
            }
            default: {
                System.out.println("UNKNOWN ACTION");
                break;
            }
                
        }
        
        response.sendRedirect(request.getRequestURI());
    }
}
