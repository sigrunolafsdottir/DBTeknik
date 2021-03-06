package dbkurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerySimpleDemo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/MyClothesShop",
                             "sigrun",
                             "secretpassword");

            stmt = con.createStatement();
            rs = stmt.executeQuery("select id, name, city from customer");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");

                System.out.println("id: " + id + ", name: " + name + ", city: " + city);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if(rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if( con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    
}
