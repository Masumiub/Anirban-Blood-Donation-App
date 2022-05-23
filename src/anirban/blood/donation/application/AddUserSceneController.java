/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AddUserSceneController implements Initializable {

    @FXML
    private TextField userIDTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private ComboBox<String> bloodGroupComboBox;
    @FXML
    private DatePicker lastBloodDonationDate;
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField contactNoTextField;
    @FXML
    private TextField secondContactNoTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloodGroupComboBox.getItems().addAll("A+", "B+", "AB+" , "O+", "A-", "B-", "AB-", "O-");
        bloodGroupComboBox.setValue("A+");
        
        userTypeComboBox.getItems().addAll("General Member", "Admin");
        userTypeComboBox.setValue("Admin");
    }    

    
    public int checkUniqueID(String inputUserID){
        int found=0;
        ObjectInputStream ois=null;
        try {
            User u;
            ois = new ObjectInputStream(new FileInputStream("User.bin"));
            while(true){
            u = (User) ois.readObject();
            if(u.getUserID().equals(inputUserID)){
                found++;
                break;
            }
            }   
        }catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
        return found;
    }
    public int checkValidpass(String inputPass){
        int valid = 0;
        if(inputPass.length()<6){
            valid++;
        }
        return valid;
    }
    
    @FXML
    private void addUserOnClick(ActionEvent event) {
        if(checkUniqueID (userIDTextField.getText())>0 ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your User ID must be Unique!");
            a.showAndWait();
        }
        else if(  checkValidpass(passwordTextField.getText())>0 ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your Password must be 6 characters long!");
            a.showAndWait();
        }
        else if(userIDTextField.getText().equals("") || passwordTextField.getText().equals("") || 
                fullNameTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Fillup the Required Fields.");
            a.showAndWait();
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("User.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }

            User u= new Admin(userIDTextField.getText(), passwordTextField.getText(), fullNameTextField.getText(), 
                    bloodGroupComboBox.getSelectionModel().getSelectedItem(), userTypeComboBox.getSelectionModel().getSelectedItem(), lastBloodDonationDate.getValue().toString(),
                    contactNoTextField.getText(),secondContactNoTextField.getText(), locationTextField.getText() );
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AddUserSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AddUserSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Success! Your User is Added.");
        a.showAndWait();
        userIDTextField.clear();
        passwordTextField.clear();
        fullNameTextField.clear();
        
    }
    
}
}