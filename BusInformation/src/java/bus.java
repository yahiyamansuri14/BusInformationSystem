
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
/**
 *
 * @author Yahiya Mansuri
 */
public class bus extends HttpServlet {
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String s1=request.getParameter("date");
        String s2=request.getParameter("dep_stn");
        String s3=request.getParameter("des_stn");
        String s4=request.getParameter("bno");
        String s5=request.getParameter("dep_time");
        String s6=request.getParameter("reach_time");
        String s7=request.getParameter("route");
        try{
            Connection con;
            PreparedStatement ps;
            String sql="insert into bus values(?,?,?,?,?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","root");
            ps=con.prepareStatement(sql);
            ps.setString(1,s1);
            ps.setString(2,s2);
            ps.setString(3,s3);
            ps.setString(4,s4);
            ps.setString(5,s5);
            ps.setString(6,s6);
            ps.setString(7,s7);
            int n=ps.executeUpdate();
            if(n>0){
                out.println("Data Inserted Successfuly");
                out.println("<a href=busentry.html>Enter-Details-Again</a>");
            }
            else{
                out.println("There is problem while entering bus information");
            }
            con.close();
        }catch(Exception e){out.println(e);}
 
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
