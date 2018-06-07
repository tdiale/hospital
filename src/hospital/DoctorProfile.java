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
public class DoctorProfile extends BorderPane {
    
    
    public DoctorProfile() {
        
        //creates a db connection (ready to execute some queries)
        MysqlConnector connector = new MysqlConnector();
        
        setStyle("-fx-background-color:violet");
        
        //create a toolbar
        HBox toolbar = new HBox();
        toolbar.setStyle("-fx-background-color:pink");
        toolbar.setSpacing(5); //adds spaces
        toolbar.setPadding(new Insets(10));
        toolbar.setAlignment(Pos.CENTER_RIGHT); //aligning buttons to right
        setTop(toolbar);
        
        Button addDoctor= new Button("Add Doctor");
        
        //adding some function to the created button- you use eventhandler
        addDoctor.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                 new UpdateDoctor();
                
            }
        });
          
          //creating table
          TableView tableview = new TableView(); 
        
          Button refresh= new Button("Refresh");
          
          refresh.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            
            public void handle(ActionEvent event) {
                tableview.setItems(connector.getDoctor());
            }
        });
        
        
        Label title = new Label("Doctor Profile");
        
        //define the region
        Region space = new Region();
        HBox.setHgrow(space,ALWAYS);
        
        toolbar.getChildren().addAll(title, space, addDoctor, refresh); //adding button to an HBox layout
        
        
        //creating columns
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("fname"));
       
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        
        TableColumn specialityCol = new TableColumn("Speciality");
        specialityCol.setCellValueFactory(new PropertyValueFactory("speciality"));
        
        TableColumn genderCol = new TableColumn("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory("Gender"));
        
        // setting minimum width for columns
        firstNameCol.setMinWidth(100);
        lastNameCol.setMinWidth(100);
        genderCol.setMinWidth(100);
        
        //adding tableview to columns
        tableview.getColumns().addAll(firstNameCol, lastNameCol, specialityCol);
        tableview.setItems(connector.getDoctor());
        
        //adding the tableview to the center
        setCenter(tableview);
}
    
}
