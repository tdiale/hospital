/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Priority.ALWAYS;
import javafx.scene.layout.Region;

/**
 *
 * @author TDiale
 */
public class PatientProfile extends BorderPane {

    //create a constructor
    public PatientProfile() {
        
       //creates a db connection (ready to execute some queries)
        MysqlConnector connector = new MysqlConnector();
        
        
        setStyle("-fx-background-color:blue");
        
        //create a toolbar
        HBox toolbar = new HBox();
        toolbar.setStyle("-fx-background-color:red");
        toolbar.setSpacing(5); //adds spaces
        toolbar.setPadding(new Insets(10));
        toolbar.setAlignment(Pos.CENTER_RIGHT); //aligning buttons to right
        setTop(toolbar);
        
        Button addPatient= new Button("Add Patient");
        
        //adding some function to the created button- you use eventhandler
        addPatient.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                 new UpdatePatient();
                
   /*             if (connector.insertPatient() == true){
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful");
                    alert.show();            
                }
                else {
                 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errot");
                    alert.setHeaderText(null);
                    alert.setContentText("Error");
                    alert.show();
                //when the statement isnt true u display an alert as above                
                }*/
            }
        });
       
        TableView tableview = new TableView(); //creating table
        
        Button refresh= new Button("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableview.setItems(connector.getPatients());
            }
        });
        
        Label title = new Label("Patient Profiles");
        
        //define the region
        Region space = new Region();
        HBox.setHgrow(space,ALWAYS);
        
        toolbar.getChildren().addAll(title, space, addPatient, refresh); //adding button to an HBox layout
        
        
        
        //creating columns
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("fname"));
       
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        
        // setting minimum width for columns
        firstNameCol.setMinWidth(100);
        lastNameCol.setMinWidth(100);
        emailCol.setMinWidth(100);
        
        //adding tableview to columns
        tableview.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        tableview.setItems(connector.getPatients());
        
        //adding the tableview to the center
        setCenter(tableview);
    }
    
    
}
