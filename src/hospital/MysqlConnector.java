package hospital;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author ofentse
 */
public class MysqlConnector {
    
    public static Connection CONNECTION = null;
    public static Statement STATEMENT = null;

    public MysqlConnector() {
        
        try{
            //-- Load the database class driver --
            Class.forName("com.mysql.jdbc.Driver");
            
           //construct a database connection 
            String dbURL = "jdbc:mysql://192.168.100.77:3306/thabiso";

            //-- Get database connection handler --
            CONNECTION  =  DriverManager.getConnection(dbURL, "sms","sms12345");
            if(CONNECTION != null){ 
                //-- create a Statement object for sending SQL statements to the database. --
               STATEMENT = CONNECTION.createStatement();
               System.out.println("Connection to the database server has been established");
            } else{
                System.out.println("Connection to the database server failed");
            }
        }catch(Exception error ){
            //-- Print a user friendly error --
            System.out.println(error.getMessage());
        }
    }
    
    
    //method to send a query to db, and catches exception
    public boolean insertPatient (Patient p){
        String query = "INSERT INTO `patients` (`id`, `fname`, `lname`, `cell`, `email`, `gender`, `physicalAddress`, `postalAddress`, `occupation`)\n" +
            "VALUES ('0', '"+p.getFname()+"', '"+p.getLname()+"', '"+p.getCell()+"', '"+p.getEmail()+"', '"+p.getGender()+"','"+p.getPhysicalAddress()+"','"+p.getPostalAddress()+"','"+p.getOccupation()+"')";
        
               
        try{
            //UPDATING RECORDS TO DB, U USE EXCECUTE UPDATE
        int rs = STATEMENT.executeUpdate(query);
        
                if (rs>0){
                  return true;
                } else {
                 return false;
                }
                 
            } catch(SQLException ex){
          
            System.err.println(ex.getMessage());
            return false;

      }
        
    }
    
    //method to send a query to db, and catches exception
    public boolean insertDoctor (Doctor d){
        String query = "INSERT INTO `patients` (`id`, `fname`, `lname`,`speciality`, `gender`)\n" +
            "VALUES ('0', '"+d.getFname()+"', '"+d.getLname()+"','"+d.getSpeciality()+"', '"+d.getGender()+"')";
        
               
        try{
            //UPDATING RECORDS TO DB, U USE EXCECUTE UPDATE
        int rs = STATEMENT.executeUpdate(query);
        
                if (rs>0){
                  return true;
                } else {
                 return false;
                }
                 
            } catch(SQLException ex){
          
            System.err.println(ex.getMessage());
            return false;

      }
        
    }
    
      
    public ObservableList<Patient> getPatients(){
           
           ObservableList<Patient> patients = FXCollections.observableArrayList();
           
            String query = "SELECT `id`, `fname`,`lname`, `cell`, `email`, `gender`, `physicalAddress`, `postalAddress`, `occupation` FROM `patients`";
           
            try{
               
               ResultSet resultSet = STATEMENT.executeQuery(query);
               
               while(resultSet.next()){
                   Patient pt = new Patient(resultSet.getString("id"), resultSet.getString("fname"), 
                         resultSet.getString("lname"), 
                         resultSet.getString("cell"), 
                         resultSet.getString("email"), 
                         resultSet.getString("gender"), 
                         resultSet.getString("physicalAddress"),
                         resultSet.getString("postalAddress"), 
                         resultSet.getString("occupation"));
                   
                   patients.add(pt);
               }
               
               return patients;
            }catch(SQLException ex){
               //-- print an error
               System.out.println(ex.getMessage());
               
               return patients;
            }
           
    }

    
    public ObservableList<Doctor> getDoctor(){
           
           ObservableList<Doctor> doctor = FXCollections.observableArrayList();
           
            String query = "SELECT `id`, `fname`,`lname`,  `gender` FROM `patients`";
           
            try{
               
               ResultSet resultSet = STATEMENT.executeQuery(query);
               
               while(resultSet.next()){
                   Doctor pt = new Doctor(resultSet.getString("id"), resultSet.getString("fname"), 
                         resultSet.getString("lname"), 
                         resultSet.getString("speciality"),
                         resultSet.getString("gender")),
                   
                   doctor.add(pt); 
               }
               
               return patients;
            }catch(SQLException ex){
               //-- print an error
               System.out.println(ex.getMessage());
               
               return patients;
            }
           
    }
    
    
    
    
    /**
     * Add or update patient records
     * @param patient
     * @return 
     *
    public boolean updatePatient(Patient patient){
        try{
            String query = "INSERT INTO `patients` (`id`, `fname`, `lname`,`cell`, `email`,"
                    + "`postalAddress`, `physicalAddress`, `occupation`, `gender`)"
                    + " VALUES('0', '"+patient.getFname()+"', '"+patient.getLname()+"',"
                    + " '"+patient.getCell()+"' , '"+patient.getEmail()+"', "
                    + " '"+patient.getPostalAddress()+"', '"+patient.getPhysicalAddress()+"',"
                    + " '"+patient.getOccupation()+"', '"+patient.getGender()+"')";
            
            if(!patient.getId().equalsIgnoreCase("")){
                query = "UPDATE `patients` SET "
                    + " `fname`='"+patient.getFname()+"', `cell`='"+patient.getCell()+"',"
                    + " `lname`='"+patient.getLname()+"', `occupation`='"+patient.getOccupation()+"',"
                    + " `email`='"+patient.getEmail()+"',`postalAddress`='"+patient.getPostalAddress()+"',"
                    + " `physicalAddress`='"+patient.getPhysicalAddress()+"',"
                    + " `gender`='"+patient.getGender()+"'"
                    + " WHERE `id`='"+patient.getId()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0)? true : false;
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    /**
     * Add or update patient records
     * @param appointment
     * @return 
     *
    public boolean updateAppointment(Appointment appointment){
        try{
            String query = "INSERT INTO `appointment` (`id`, `patientID`, `doctorID`,`description`, `appdate`, `status`)"
                    + " VALUES('0', '"+appointment.getPatientID()+"', '"+appointment.getDoctorID()+"',"
                    + " '"+appointment.getDescription()+"' , '"+appointment.getDate()+"', '"+appointment.getStatus()+"')";
            
            if(!appointment.getId().equalsIgnoreCase("")){
                query = "UPDATE `appointment` SET "
                    + " `patientID`='"+appointment.getPatientID()+"', `description`='"+appointment.getDescription()+"',"
                    + " `appdate`='"+appointment.getDate()+"', `doctorID`='"+appointment.getDoctorID()+"',"
                    + " `status`='"+appointment.getStatus()+"'"
                    + " WHERE `id`='"+appointment.getId()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0)? true : false;
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public ObservableList<Appointment> getAppointmentsFor(String ID){
        
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `patientID`, `doctorID`, `description`, `appdate`, "
                         + " `status`"
                         + " FROM `appointment` WHERE `doctorID` = '"+ID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                appointments.add(new Appointment(result.getString("id"), result.getString("patientID"),
                result.getString("doctorID"), result.getString("appdate"), result.getString("description"),
                result.getString("status")));
            }
            return appointments;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return appointments;
        }
    }
    
    
    /**
     * Get patient by ID
     * @return 
     *
    
    public Patient getPatientByID(String ID){
        
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients` WHERE `id` = '"+ID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
           if(result.next()){
                return new Patient(result.getString("id"), result.getString("fname"),
                    result.getString("lname"), result.getString("cell"), result.getString("email"),
                    result.getString("gender"), result.getString("physicalAddress"), 
                    result.getString("postalAddress"), result.getString("occupation"));
            }
            return new Patient();
        
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Patient();
        }
    }
    
    
    /**
     * Get all patients
     * @return 
     *
    public ObservableList<Patient> getPatients(){
        
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                patients.add(new Patient(result.getString("id"), result.getString("fname"),
                result.getString("lname"), result.getString("cell"), result.getString("email"),
                result.getString("gender"), result.getString("physicalAddress"), 
                result.getString("postalAddress"), result.getString("occupation")));
            }
            return patients;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return patients;
        }
    }
    
    /**
     * Get all patients
     * @return 
     *
    public ObservableList<String> getPatientNames(){
        
        ObservableList<String> patients = FXCollections.observableArrayList();
        try{
            String query = " SELECT CONCAT_WS(' ',`fname`,`lname`) AS `fullname` FROM `patients`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                patients.add(result.getString("fullname"));
            }
            return patients;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return patients;
        }
    }
    
    
    /**
     * Get patient profile by name
     * @param name
     * @return 
     *
    public Patient getPatientByName(String name){
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients` WHERE CONCAT_WS(' ',`fname`,`lname`) = '"+name+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Patient(result.getString("id"), result.getString("fname"),
                result.getString("lname"), result.getString("cell"), result.getString("email"),
                result.getString("gender"), result.getString("physicalAddress"), 
                result.getString("postalAddress"), result.getString("occupation"));
            }
            return new Patient();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Patient();
        }
    }*/
   
}
