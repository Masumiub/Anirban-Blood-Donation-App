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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AddBloodDonorSceneController implements Initializable {

    @FXML
    private TextField donorNameTextField;
    @FXML
    private ComboBox<String> bloodGroupComboBox;
    @FXML
    private TextField contactNoTextField;
    @FXML
    private TextField secondaryContactNoTextField;
    @FXML
    private DatePicker lastDonationDate;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField donorIDTextField;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloodGroupComboBox.getItems().addAll("A+", "B+", "AB+" , "O+", "A-", "B-", "AB-", "O-");
        bloodGroupComboBox.setValue("A+");
    }  

    
    public int checkUniqueID(String inputUserID){
        int found=0;
        ObjectInputStream ois=null;
        try {
            User u;
            ois = new ObjectInputStream(new FileInputStream("BloodDonorList.bin"));
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
    @FXML
    private void AddDonorOnClick(ActionEvent event) {
        if(checkUniqueID (donorIDTextField.getText())>0 ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your Donor ID must be Unique!");
            a.showAndWait();
        }
        else if(donorIDTextField.getText().equals("")|| donorNameTextField.getText().equals("") || contactNoTextField.getText().equals("") || locationTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Fillup the Required Fields!");
            a.showAndWait();
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("BloodDonorList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }

            BloodDonor newDonor= new BloodDonor( donorNameTextField.getText(), donorIDTextField.getText(),
                    bloodGroupComboBox.getSelectionModel().getSelectedItem(), contactNoTextField.getText(), secondaryContactNoTextField.getText(),
                    lastDonationDate.getValue().toString(), locationTextField.getText() );
            
            oos.writeObject(newDonor);
           
            } catch (IOException ex) {
            Logger.getLogger(AddBloodDonorSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AddBloodDonorSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Success! Your Donor is Added.");
        a.showAndWait();
        }
        donorNameTextField.clear();
        donorIDTextField.clear();
        contactNoTextField.clear();
        secondaryContactNoTextField.clear();
        locationTextField.clear();
    }
    
    
    
}
