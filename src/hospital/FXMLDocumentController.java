/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author TDiale. 
 */ 
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button login;
    
    @FXML
    private PasswordField password;
    private TextField username;
    
    String passw = "password";
    String usern = "username";
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
//              if(password.getText().trim().equals(passw) &&  //the trim method removes text
//                        username.getText().trim().equals(usern) ){
//                  
                  new MainUI();
                  Hospital.loginStage.close(); //closes the login screen
                  
               //}
//              else {
//                 Alert alert = new Alert(Alert.AlertType.WARNING);
//                  alert.setTitle("Authentication failed");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Please confirm your username and password combination and try again.");
//                alert.show();
//                //when the statement isnt true u display an alert as above
//              }
            }
        });
    }   
}
