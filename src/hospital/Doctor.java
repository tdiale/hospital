/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author TDiale
 */
public class Doctor {
    
    SimpleStringProperty id, fname, lname,speciality,gender;

    
    public Doctor(String id,String fname,String lname, String speciality,String gender) {        
        
        this.id = new SimpleStringProperty(id);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.speciality = new SimpleStringProperty(speciality);
        this.gender = new SimpleStringProperty(gender);
        
    }
    public Doctor() {
        
        this.id = new SimpleStringProperty("");
        this.fname = new SimpleStringProperty("");
        this.lname = new SimpleStringProperty("");
        this.speciality = new SimpleStringProperty("");
        this.gender = new SimpleStringProperty("");
        
    }

    //-- Getter methods --
    public String getId() { return id.get();
    }

    public String getFname() { return fname.get();
    }

    public String getLname() { return lname.get();
    }

    public String getSpeciality() { return speciality.get();
    }
      
    public String getGender() { return gender.get();
    }

    
    //-- Setter methods --
    public void setId(String id) {
        this.id.set(id);
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public void setSpeciality(String speciality) {
        this.speciality.set(speciality);
    }
    
    public void setGender(String gender) {
        this.gender.set(gender);
    }
    
    
    
}
