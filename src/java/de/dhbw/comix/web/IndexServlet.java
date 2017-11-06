package de.dhbw.comix.web;

import de.dhbw.comix.database.Comic;
import de.dhbw.comix.database.DatabaseFacade;
import de.dhbw.comix.database.Serie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Import ArrayList
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Requenst Handler für die Startseite mit folgenden Funktionen:
 *
 *   * Anzeige aller vorhandenen Comics * Anlage eines neuen Comics * Löschen
 * aller ausgewählten Comics
 */
@WebServlet(urlPatterns = {"/index.html"})
public class IndexServlet extends HttpServlet {

    @EJB
    DatabaseFacade database;

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

        // DUMMY VALUES
//        this.database.createNewComic("Monkey Island", "The Secret of Monkey Island", 0, 1990, "Guybrush Threepwood", "Ron Gilbert");
//        this.database.createNewComic("Monkey Island", "Monkey Island 2: LeChuck's Revenge", 1, 1991, "Guybrush Threepwood", "Ron Gilbert");
//        this.database.createNewComic("King's Quest", "Kings Quest", 0, 1983, "King Graham", "Roberta Williams");
        // Speichern der Serien in den Session-Kontext
        List<Serie> serienList = this.database.getAllComics();
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

        HttpSession s = request.getSession();

        // Reset Error Messages
        s.setAttribute("fehlermeldungen", null);

        String action = request.getParameter("action");

        switch (action) {
            case "create": {
                addComic(request, response);
                break;
            }
            case "remove": {
                removeComics(request, response);
                break;
            }
            default: {
                System.out.println("UNKNOWN ACTION");
                break;
            }

        }

        response.sendRedirect(request.getRequestURI());
    }

    private void removeComics(HttpServletRequest request, HttpServletResponse response) {

        String[] comicIDs = request.getParameterValues("removeComic");

        if (comicIDs == null || comicIDs.length == 0) {
            setFehlermeldung(request, "Es wurden keine Comics ausgewählt!");
            return;
        }

        // Remove the selected Comics from the database
        for (String s_ID : comicIDs) {
            long id;
            try {
                id = Long.parseLong(s_ID);
            } catch (NumberFormatException ex) {
                setFehlermeldung(request, "Fehler beim Entfernen:" + ex.getMessage());
                return;
            }

            if (id >= 0) {

                Comic c = this.database.getComicById(id);

                if (c != null) {
                    this.database.delete(c);
                } else {
                    setFehlermeldung(request, "Konnte den ausgewählten Comic nicht finden!");
                }
            } else {
                setFehlermeldung(request, "Invalide ID!");
            }
        }
    }

    private void addComic(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession s = request.getSession();

        String serie = (String) request.getParameter("serie");
        String nummer = (String) request.getParameter("nummer");
        String jahr = (String) request.getParameter("jahr");
        String titel = (String) request.getParameter("titel");
        String zeichner = (String) request.getParameter("zeichner");
        String texter = (String) request.getParameter("texter");
        
        boolean isValid = true;
        ArrayList<String> errors = new ArrayList<>();

        if (serie.isEmpty()) {
            errors.add("Bitte geben Sie eine Serie ein.");
            isValid = false;
        }
        if (nummer.isEmpty()) {
            errors.add("Bitte geben Sie eine Nummer ein.");
            isValid = false;
        }
        if (jahr.isEmpty()) {
            errors.add("Bitte geben Sie ein Jahr ein.");
            isValid = false;
        }
        if (titel.isEmpty()) {
            errors.add("Bitte geben Sie eine Serie ein.");
            isValid = false;
        }
        if (zeichner.isEmpty()) {
            errors.add("Bitte geben Sie einen Zeichner ein.");
            isValid = false;
        }
        if (texter.isEmpty()) {
            errors.add("Bitte geben Sie einen Texter ein.");
            isValid = false;
        } 
        
       
            
        if(isValid) {
            int dataJahr = Integer.parseInt(jahr);
            int dataNummer = Integer.parseInt(nummer);

            this.database.createNewComic(serie, titel, dataNummer, dataJahr, zeichner, texter);

            s.removeAttribute("title");
            s.removeAttribute("jahr");
            s.removeAttribute("nummer");
            s.removeAttribute("serie");
            s.removeAttribute("zeichner");
            s.removeAttribute("texter");
        } else {
            String[] errs = new String[errors.size()];
            errors.toArray(errs);
            setFehlermeldung(request, errs);
        }

    }

    private void setFehlermeldung(HttpServletRequest request, String... msg) {
        HttpSession s = request.getSession();
        s.setAttribute("fehlermeldungen", msg);
    }
}
