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
public class UpdatePatient extends Stage{
        
    
    public UpdatePatient(){
        
        //set or create stage/screen title and icon. stage is the main screen
        setTitle("Add Patient");
        getIcons().add(new Image(UpdatePatient.class.getResourceAsStream("clinic.png")));
        
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
        
        TextField cell = new TextField();
        Label cl = new Label("Cell");
        grid.add(cl, 0, 2);
        grid.add(cell, 1, 2);
        cell.setPromptText("Cell");
        cell.setPrefWidth(200);
        
        TextField email = new TextField();
        Label el = new Label("Email");
        grid.add(el, 0, 3);
        grid.add(email, 1, 3);
        email.setPromptText("Email");
        email.setPrefWidth(200);
        
        ComboBox<String>gen= new ComboBox<>(FXCollections.observableArrayList("Male","Female"));
        gen.setValue(gen.getItems().get(0));
        grid.add(new Label("Gender:"), 0, 4);
        grid.add(gen, 1, 4);
        gen.setPrefWidth(200);

        
        TextField physicalAddress = new TextField();
        Label py = new Label("Physical Address");
        grid.add(py, 0, 5);
        grid.add(physicalAddress, 1, 5);
        physicalAddress.setPromptText("Physical Address");
        physicalAddress.setPrefWidth(200);
        
        TextField postalAddress = new TextField();
        Label pt = new Label("Postal Address");
        grid.add(pt, 0, 6);
        grid.add(postalAddress, 1, 6);
        postalAddress.setPromptText("Postal Address");
        postalAddress.setPrefWidth(200);
        
        
        TextField occupation = new TextField();
        Label oc = new Label("Occupation");
        grid.add(oc, 0, 7);
        grid.add(occupation, 1, 7);
        occupation.setPromptText("Occupation");
        occupation.setPrefWidth(200);
        
       
        Button sub = new Button("Submit");
        sub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(firstName.getText().trim().equals("")&&
                        lastName.getText().trim().equals("")&&
                        cell.getText().trim().equals("")&&
                        email.getText().trim().equals("")&&
                        gen.getValue().trim().equals("")&&
                        physicalAddress.getText().trim().equals("")&&
                        postalAddress.getText().trim().equals("")&&
                       occupation.getText().trim().equals(""))
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
                    
                    Patient pat= new Patient("", firstName.getText().trim(), lastName.getText().trim(), cell.getText().trim(), email.getText().trim(), gen.getValue(),physicalAddress.getText().trim(), postalAddress.getText().trim(), occupation.getText().trim());
                    MysqlConnector connector = new MysqlConnector();
                    if(connector.insertPatient(pat)== true){

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
        
        
        
       //TextField email = new TextField();
        
         
        
        
        
        
        //---- End of content --------------------------------------------------
        Scene scene = new Scene(root, 400, 300);
        
        setScene(scene);
        
        initModality(Modality.APPLICATION_MODAL); 
        setResizable(false);
        
        show();
        
        
        
    }         
}
                

