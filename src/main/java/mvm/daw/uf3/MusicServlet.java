/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvm.daw.uf3;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvm.daw.uf3.db.DBConnection;

/**
 *
 * @author manuc
 */
@WebServlet(name = "MusicServlet", urlPatterns = {"/MusicServlet"})
public class MusicServlet extends HttpServlet {

    MusicService service;
    int counter;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            DBConnection dbConnection = new DBConnection("db.properties");
            service = new MusicService();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
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
            throws ServletException, IOException, SQLException, Exception {
        String todo = request.getParameter("todo");
        if (todo != null) {
            if (todo.equals("C")) {
                if (request.getParameter("name") != null) {
                    String author = request.getParameter("author");
                    String album = request.getParameter("album");
                    String name = request.getParameter("name");
                    float price = Float.valueOf(request.getParameter("price"));
                    int rating = Integer.valueOf(request.getParameter("rating"));
                    service.createSong(this.counter + 1, name, price, author, album, rating);
                    this.counter++;
                    request.setAttribute("songs_list", service.getSongsList());
                    getServletConfig().getServletContext().getRequestDispatcher("/songslist.jsp").forward(request, response);
                } else {
//                    request.setAttribute("create_song",null);
                    getServletConfig().getServletContext().getRequestDispatcher("/createform.jsp").forward(request, response);
                }
            } else if (todo.equals("D")) {
                out.println(request.getParameter("id"));
                String value = request.getParameter("id");
                int id = Integer.parseInt(value);
                service.removeSong(id);
                request.setAttribute("songs_list", service.getSongsList());
                getServletConfig().getServletContext().getRequestDispatcher("/songslist.jsp").forward(request, response);
            } else if (todo.equals("U")) {
                if (request.getParameter("name") != null) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    String author = request.getParameter("author");
                    String album = request.getParameter("album");
                    String name = request.getParameter("name");
                    float price = Float.valueOf(request.getParameter("price"));
                    int rating = Integer.valueOf(request.getParameter("rating"));
                    service.updateSong(id, name, price, author, album, rating);
                    request.setAttribute("songs_list", service.getSongsList());
                    getServletConfig().getServletContext().getRequestDispatcher("/songslist.jsp").forward(request, response);
                } else {
                    String value = request.getParameter("id");
                    int id = Integer.parseInt(value);
                    request.setAttribute("single_song", service.getSong(id));
                    getServletConfig().getServletContext().getRequestDispatcher("/updateform.jsp").forward(request, response);
                }
            } else if (todo.equals("B")) {
                if (request.getParameter("rating") != null) {
                    int rating = Integer.valueOf(request.getParameter("rating"));
                    request.setAttribute("songs_list", service.getSongsByRating(rating));
                    getServletConfig().getServletContext().getRequestDispatcher("/songslist.jsp").forward(request, response);
                } else {
                    getServletConfig().getServletContext().getRequestDispatcher("/filter.jsp").forward(request, response);
                }
            } else if (todo.equals("F")) {
                if (request.getParameter("query") != null) {
                    String query = request.getParameter("query");
                    request.setAttribute("songs_list", service.filterSongs(query));
                    getServletConfig().getServletContext().getRequestDispatcher("/songslist.jsp").forward(request, response);
                } else {
                    getServletConfig().getServletContext().getRequestDispatcher("/filter.jsp").forward(request, response);
                }
            }

        } else {
            String value = request.getParameter("id");
            if (value != null) {
                int id = Integer.parseInt(value);
                request.setAttribute("single_song", service.getSong(id));
            } else {
                request.setAttribute("songs_list", service.getSongsList());
            }
            getServletConfig().getServletContext().getRequestDispatcher("/songslist.jsp").forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(MusicServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MusicServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(MusicServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MusicServlet.class.getName()).log(Level.SEVERE, null, ex);
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
