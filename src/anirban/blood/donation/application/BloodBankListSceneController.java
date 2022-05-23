/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class BloodBankListSceneController implements Initializable {

    @FXML
    private TableView<BloodBank> tableView;
    @FXML
    private TableColumn<BloodBank, String> bloodBankCol;
    @FXML
    private TableColumn<BloodBank, String> contactNo;
    @FXML
    private TableColumn<BloodBank, String> locationCol;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloodBankCol.setCellValueFactory(new PropertyValueFactory<BloodBank, String>("BloodBankName"));
        contactNo.setCellValueFactory(new PropertyValueFactory<BloodBank, String>("ContactNumber"));
        locationCol.setCellValueFactory(new PropertyValueFactory<BloodBank, String>("Location"));
        
        LoadBloodBanktList();
    }    
    
    private void LoadBloodBanktList(){
        ObjectInputStream ois=null;
        try {
            BloodBank p;
            ois = new ObjectInputStream(new FileInputStream("BloodBankList.bin"));
            while(true){
                p = (BloodBank) ois.readObject();
                tableView.getItems().add(0, p);
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
    }
}
