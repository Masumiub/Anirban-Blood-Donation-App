/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author movie
 */
public class MainSceneController implements Initializable {
    
    private Label label;
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private TextField userIDTextField;
    @FXML
    private PasswordField passwordTextField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().addAll("General Member", "Admin");
        userTypeComboBox.setValue("General Member");
    }    

    @FXML
    private void signInOnClick(ActionEvent event) throws IOException{
        User u = User.verifylogin(userIDTextField.getText(), passwordTextField.getText(), userTypeComboBox.getSelectionModel().getSelectedItem());
        
        if(u==null){
            Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Sorry! Wrong UserID/Password. Please Try Again!");
                    a.showAndWait();
                    userIDTextField.clear();
                    passwordTextField.clear();
        }
        else{
            if(u instanceof Admin){
                 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("AdminDashBoardScene.fxml"));
                 Parent homeScene = loader.load();
                 Scene homepage = new Scene(homeScene);
                 AdminDashBoardSceneController controller = loader.getController();
                 //System.out.println(u.getUserID());
                 controller.initData(u);
                 Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window.setScene(homepage);
                 window.show();
             }
            
            if(u instanceof GeneralMember){
                 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("GeneralMemberDashBoardScene.fxml"));
                 Parent homeScene = loader.load();
                 Scene homepage = new Scene(homeScene);
                 GeneralMemberDashBoardSceneController controller = loader.getController();
                 //System.out.println(u.getUserID());
                 controller.initData(u);
                 Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window.setScene(homepage);
                 window.show();
             }
        }
    }

    @FXML
    private void signUpOnClick(ActionEvent event) throws IOException {
        Parent signUpFormParent = FXMLLoader.load(getClass().getResource("SignUpFormScene.fxml"));
        Scene signUpFormViewScene = new Scene(signUpFormParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("SignUp Form");
        newWindow.setScene(signUpFormViewScene);
        newWindow.show();
    }

    private void memberDashBoardOnClick(ActionEvent event) throws IOException {
        
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("GeneralMemberDashBoardScene.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    private void adminDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoardScene.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void closeSystemOnClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void showAboutAnirbanOnClick(ActionEvent event) throws IOException {
        Parent addBloodBankParent = FXMLLoader.load(getClass().getResource("AboutAnirbanScene.fxml"));
        Scene addBloodBankViewScene = new Scene(addBloodBankParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("About Anirban");
        newWindow.setScene(addBloodBankViewScene);
        newWindow.show();
    }

    @FXML
    private void showFAQsOnClick(ActionEvent event) throws IOException {
        Parent addBloodBankParent = FXMLLoader.load(getClass().getResource("FAQsScene.fxml"));
        Scene addBloodBankViewScene = new Scene(addBloodBankParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("FAQs");
        newWindow.setScene(addBloodBankViewScene);
        newWindow.show();
    }
    
}
