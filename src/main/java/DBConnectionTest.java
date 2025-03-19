import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3306/reservations";
    	String user = "root";
    	String password = "123456";
        
        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1");

            if (rs.next()) {
                System.out.println("資料庫連線成功！");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
