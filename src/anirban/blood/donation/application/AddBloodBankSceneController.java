/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AddBloodBankSceneController implements Initializable {

    @FXML
    private TextField bloodBankNameTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField contactNoTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addBloodBankOnClick(ActionEvent event) {
        if( bloodBankNameTextField.getText().equals("")|| contactNoTextField.getText().equals("") || locationTextField.getText().equals("") ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Fillup the Required Fields!");
            a.showAndWait();
        }
        else{
            File f = null;
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            
            try{
            f= new File("BloodBankList.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            
            BloodBank newBloodBank = new BloodBank(bloodBankNameTextField.getText(), contactNoTextField.getText(), locationTextField.getText());
            
            oos.writeObject(newBloodBank);
            
            }catch (IOException ex) {
            Logger.getLogger(AddBloodBankSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AddBloodBankSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Success! Your Blood Bank is Added.");
        a.showAndWait();
        }
        bloodBankNameTextField.clear();
        contactNoTextField.clear();
        locationTextField.clear();
    }
    
}
