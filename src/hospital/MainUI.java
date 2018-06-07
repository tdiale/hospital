package hospital;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TDiale
 */
public class MainUI extends Stage{
    
    public MainUI(){
        
        //set stage/screen title and icon
        setTitle("Hospital Appointment System");
        getIcons().add(new Image(MainUI.class.getResourceAsStream("clinic.png")));
        
        BorderPane root = new BorderPane ();
        
        //---content goes here----------
        
        
        
        TabPane tabpane = new TabPane(); //parent container
        
        Tab dashboard = new Tab("Dashboard"); //creating a dashboard tab
        dashboard.setClosable(false); //disables the close button on tabs 
        tabpane.getTabs().add(dashboard);
        
        // create and add patients tab
        Tab patients = new Tab("Patients");
        patients.setClosable(false);
        tabpane.getTabs().add(patients);
        
        // create and add appointments tab
        Tab appointments = new Tab("Appointments");
        appointments.setClosable(false);
        tabpane.getTabs().add(appointments);
        
        Tab doctors = new Tab("Doctors");
        doctors.setClosable(false);
        tabpane.getTabs().add(doctors);
        
        //add contents to tabs
        PatientProfile profiles = new PatientProfile();
        patients.setContent(profiles);
        
        DoctorProfile profile = new DoctorProfile();
        doctors.setContent(profile);
        
           
        
        root.setCenter(tabpane);
        
    
        Scene scene = new Scene(root, 800, 500);
        
        setScene(scene);
        show();
    }
        
    
}
