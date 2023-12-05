package engine;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.json.JSONObject;

@Path("start")
public class Backend {
    Helper helper;
    
    public Backend() {
        helper = new Helper();
    }

    //<editor-fold defaultstate="collapsed" desc="Create Student">
    @Path("create_student")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchStudent(@FormParam("p0") String name, 
            @FormParam("p1") String regNo, @FormParam("p2") int level,
            @FormParam("p3") String dept, @FormParam("p4") String age,
            @FormParam("p5") String phone) {
        JSONObject json = new JSONObject();
        boolean studentIsValid = helper.checkStudent(regNo);
        if(studentIsValid){
            json.put("status", true);
            json.put("data", helper.createStudent(name, regNo, level, dept, level));
            helper.logAction(phone, "Student acc creation successful: "+regNo);
        }else{
            json.put("status", false);
            helper.logAction(phone, "Student acc creation failed: "+regNo);
        }
        
        return json.toString();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Login">
    @Path("login")
    @POST
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String login(@FormParam("phone") String phone, @FormParam("pin") String pin) {
        JSONObject json = new JSONObject();
        boolean loginIsValid = helper.checkLoginCredentials(phone,pin);
        if(loginIsValid){
            json.put("status", true);
            helper.logAction(phone, "Login attempt successful");
        }else{
            json.put("status", false);
            helper.logAction(phone, "Login attempt failed");
        }
        return json.toString();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fetch Student">
    @Path("fetch_student")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchStudent(@FormParam("p0") String phone, 
            @FormParam("p1") String regNo) {
        JSONObject json = new JSONObject();
        boolean studentIsValid = helper.checkStudent(regNo);
        if(studentIsValid){
            json.put("status", true);
            json.put("data", helper.fetchStudent(regNo));
            helper.logAction(phone, "Student data requested: "+regNo);
        }else{
            json.put("status", false);
            helper.logAction(phone, "Student data request failed: "+regNo);
        }
        
        return json.toString();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Update Level">
    @Path("update_level")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLevel(@FormParam("p0") String regNo, 
            @FormParam("p1") int level) {
        JSONObject json = new JSONObject();
        boolean studentIsValid = helper.checkStudent(regNo);
        if(studentIsValid){
            json.put("status", true);
            json.put("data", helper.updateLevel(regNo, level));
            
        }else{
            json.put("status", false);
            
        }
        
        return json.toString();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Test">
    @Path("test")
    @GET
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String test() {
        JSONObject json = new JSONObject();
        json.put("status", "we dey gidigba!");
        return json.toString();
    }//</editor-fold>

    
}
