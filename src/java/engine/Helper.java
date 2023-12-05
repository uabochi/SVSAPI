package engine;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class Helper {
    Helper(){}
    
    public String createStudent(String name, String regNo, int level, String dept, int age) {
        try(Connection conn = getDBConnection()){
            String query = "INSERT INTO students (name, reg_no, level, department, age) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, regNo);
            stmt.setInt(3, level);
            stmt.setString(4, dept);
            stmt.setInt(5, age);
            ResultSet rs = stmt.executeQuery();
            
            return "Account Creation Failed";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Account Creation Successful";
    }
    
    public boolean checkLoginCredentials(String phone, String pin) {
        try(Connection conn = getDBConnection()){
            String query = "SELECT * FROM users WHERE phone = ? AND pin = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, phone);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean checkStudent(String regNo) {
        try(Connection conn = getDBConnection()){
            String query = "SELECT * FROM students WHERE reg_no = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regNo);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void logAction(String phone, String action) {
        try(Connection conn = getDBConnection()){
            String query = "INSERT INTO action_log (phone,action) VALUES(?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, phone);
            stmt.setString(2, action);
            stmt.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    JSONObject fetchStudent(String regNo) {
        try(Connection conn = getDBConnection()){
            String query = "SELECT * FROM students WHERE reg_no = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regNo);
            ResultSet rs = stmt.executeQuery();
            JSONObject json = new JSONObject();
            if(rs.next()){
                json.put("name", rs.getString("name"));
                json.put("regNo", rs.getString("reg_no"));
                json.put("age", rs.getString("age"));
                json.put("dept", rs.getString("department"));
                json.put("level", rs.getString("level"));
                return json;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String updateLevel(String regNo, int level) {
        try(Connection conn = getDBConnection()){
            String query = "UPDATE students SET level = ? WHERE reg_no = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, level);
            stmt.setString(2, regNo);
            stmt.execute();
            
            return "successful";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failed";
    }
    
    Connection getDBConnection() throws SQLException, ClassNotFoundException{
        String user = "root";
        String password = "Abochi@001";
        String url = "jdbc:mysql://localhost:3306/svg?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
    
    public void template(String phone, String pin) {
        try(Connection conn = getDBConnection()){
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
