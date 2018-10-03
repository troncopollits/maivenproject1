/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.net.clases.Celda;
import com.net.clases.Fila;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author artur
 */
public class servlette extends HttpServlet {

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
        
        Gson gson = new Gson();
        String error = "";
        try (PrintWriter out = response.getWriter()) {
            
            Integer alto = Integer.parseInt(request.getParameter("alto"));
            Integer ancho = Integer.parseInt(request.getParameter("ancho"));
            
            //Comprobamos si se introducen 0 o caracteres
            if(alto == 0 || ancho == 0){
                 //Declaramos el gson y el json
                response.setStatus(500);
                error = "Los dos números han de ser positivos.";
                response.getWriter().append(gson.toJson(error));
            }/*else if(alto < 50 || ancho <50){
                 //Declaramos el gson y el json
                response.setStatus(500);
                error = "Has sobrepasado el máximo de números por variable.";
                response.getWriter().append(gson.toJson(error));
            }*/
           
                ArrayList<Fila> fs = new ArrayList<Fila>();
                for (int i = 1; i <= alto; i++) {

                    Fila f = new Fila();
                    f.setI(i);
                    fs.add(f);
                    f.setLista(new ArrayList<Celda>());

                    for (int j = 1; j <= ancho; j++) {
                        Celda c = new Celda();
                        c.setI(i);
                        c.setJ(j);
                        c.setRes(j * i);
                        f.getLista().add(c);                    
                    }
                }
                //Mandamos el json
                response.getWriter().append(gson.toJson(fs));
            
        }catch(Exception e){
            response.setStatus(500);
            error = "Datos introducidos erroneamente.";
            response.getWriter().append(gson.toJson(error));
        }finally{
            out.close();
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
    }// </editor-fold>

}
