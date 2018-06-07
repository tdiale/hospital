/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author TDiale
 */
public class UpdateDoctor extends Stage{
    
    public UpdateDoctor(){
        
        //set or create stage/screen title and icon. stage is the main screen
        setTitle("Add Doctor");
        getIcons().add(new Image(UpdateDoctor.class.getResourceAsStream("clinic.png")));
        
        BorderPane root = new BorderPane ();
        
        //-- Content goes here--------------------------------------------------
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5); //spacing between columns
        grid.setVgap(5); //spacing between rows
    
        TextField firstName = new TextField();
      
        Label fn = new Label("First Name");
        grid.add(fn, 0, 0);
        grid.add(firstName, 1, 0);
        firstName.setPromptText("First name");
        firstName.setPrefWidth(200);
    
        TextField lastName = new TextField();
        Label ln = new Label("Surname");
        grid.add(ln, 0, 1);
        grid.add(lastName, 1, 1);
        lastName.setPromptText("Last name");
        lastName.setPrefWidth(200);
        
        TextField speciality = new TextField();
        Label sp = new Label("Speciality");
        grid.add(sp, 0, 2);
        grid.add(lastName, 1, 2);
        speciality.setPromptText("Speciality");
        speciality.setPrefWidth(200);
    
        ComboBox<String>gen= new ComboBox<>(FXCollections.observableArrayList("Male","Female"));
        gen.setValue(gen.getItems().get(0));
        grid.add(new Label("Gender:"), 0, 3);
        grid.add(gen, 1, 3);
        gen.setPrefWidth(200);
        
        
    Button sub = new Button("Submit");
        sub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(firstName.getText().trim().equals("")&&
                        lastName.getText().trim().equals("")&&
                        speciality.getText().trim().equals("")&&
                        gen.getValue().trim().equals(""))
                    
                {
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.setTitle("Warning");
                   alert.setHeaderText(null);
                   
                   alert.setContentText("Ensure you've entered all fields");
                   alert.show();
                   
                   
                   // clearing field upon submit
                   firstName.clear();
                   lastName.clear();
                   
                }
                else{
                    
                    Doctor doc= new Doctor("", firstName.getText().trim(), lastName.getText().trim(), speciality.getText().trim(), gen.getValue());
                    MysqlConnector connector = new MysqlConnector();
                    
                    if(connector.insertDoctor(doc)== true){

                         //this is an dialog alert the patient record has been successfully added
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Patient has been added successfully");
                        alert.show();
                     }
                     else{
                         
                      //this is an dialog alert if the patient record is not successfully added
                      Alert alert = new Alert(Alert.AlertType.ERROR);//setting the alert type to error
                        alert.setTitle("Error");//alert heading
                        alert.setHeaderText(null);
                        alert.setContentText("Error occured while trying to add the patient");//setting the content of the error message
                        alert.show();
                     }
                }
                
            }
            
        });
        
        HBox hb = new HBox();
         root.setBottom(hb);
         
                  
         hb.setAlignment(Pos.CENTER_RIGHT);
         hb.getChildren().addAll(sub);
        
         root.setCenter(grid);
        
        
        
    // add other textfields here---
        
         
        
        
        
        
    //---- End of content --------------------------------------------------
        Scene scene = new Scene(root, 400, 300);
        
        setScene(scene);
        
        initModality(Modality.APPLICATION_MODAL); 
        setResizable(false);
        
        show();
    
    }   
}
