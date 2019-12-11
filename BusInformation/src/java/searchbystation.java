
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
/**
 *
 * @author Yahiya Mansuri
 */
public class searchbystation extends HttpServlet {
    Connection con;
    PreparedStatement ps;
    PrintWriter out;
    ResultSet rs;
    String sql;
    @Override
    public void init(){
        sql="select * from bus where dep_stn=? && des_stn=?";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/bms","root","root");
        ps=con.prepareStatement(sql);
        
        }catch(Exception e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s1=null,s2=null,s3=null,s4=null,s5=null,s6=null,s7=null;
        out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String str1=request.getParameter("dept_stn");
        String str2=request.getParameter("dest_stn");
        try{
            ps.setString(1,str1);
            ps.setString(2,str2);
            rs=ps.executeQuery();
            
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body{background-color:mintcream;}");
        out.println("*{\n" +
"                font-family:Arial Rounded MT Bold;\n" +
"       }");
        out.println("table{\n" +
"                margin-top:50px;\n" +
"                border-collapse:collapse;\n" +
"            }");
        out.println("td{\n" +
"                height:30px;\n" +
"                width:250px;\n" +
"                text-align: center;\n" +
"            }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>........Bus-Information-System........</h1>");
        //out.println("<h3>.......Lost-Person List......</h3>");
        
        
        out.println("<table border=2px solid black>");
            out.println("<tr>");
                out.println("<td>Date</td>");
                out.println("<td>Departure Station</td>");
                out.println("<td>Destination Station</td>");
                out.println("<td>Bus No</td>");
                out.println("<td>Departure Time</td>");
                out.println("<td>Reach Time</td>");
                out.println("<td>Route</td>");
            out.println("</tr>");
//            if(rs)
//            {
//                out.println("<h1>Pls Enter Valid Station Name</h1>");
//            }
            
            while(rs.next())
            {
                s1=rs.getString(1);
                s2=rs.getString(2);
                s3=rs.getString(3);
                s4=rs.getString(4);
                s5=rs.getString(5);
                s6=rs.getString(6);
                s7=rs.getString(7);
                out.println("<tr>");
                    out.println("<td>"+s1+"</td>");
                    out.println("<td>"+s2+"</td>");
                    out.println("<td>"+s3+"</td>");
                    out.println("<td>"+s4+"</td>");
                    out.println("<td>"+s5+"</td>");
                    out.println("<td>"+s6+"</td>");
                    out.println("<td>"+s7+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            if(s1==null)
            {
                out.println("<h3>Sorry,No Data Available....</h3>");
            }

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
