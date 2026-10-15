import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student", "root", "YOUR_PASSWORD"
            );
            PreparedStatement stmt = con.prepareStatement(
                "SELECT name FROM student WHERE name=?"
            );
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                out.println("<h2>Found</h2>");
            } else {
                out.println("<h2>Not found</h2>");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            out.println("Database Error");
            e.printStackTrace();
        }
        out.close();
    }
}
