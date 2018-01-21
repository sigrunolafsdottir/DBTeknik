package övn5_addPresent_SP;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;


public class Repository {

    private Properties p = new Properties();
    
    public Repository(){
        try {
            p.load(new FileInputStream("src/övn5_callable/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
      
    public String addPresent(String presentName){
       
        ResultSet rs = null;
        String query = "call addPresent(?)";
                
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                             p.getProperty("name"),
                             p.getProperty("password"));
             CallableStatement stmt = con.prepareCall(query)){

            stmt.setString(1, presentName);
            rs = stmt.executeQuery();
        }
        catch (Exception e){
            return "Could not add present "+presentName;
        }
        return presentName +" was added to database.";
    }
}
