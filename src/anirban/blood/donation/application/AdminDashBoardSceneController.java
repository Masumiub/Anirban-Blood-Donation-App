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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AdminDashBoardSceneController implements Initializable {

    Admin loggedUser;
    @FXML
    private TableView<BloodRequest> bloodRequestTableView;
    @FXML
    private TableColumn<BloodRequest, String> nameCol;
    @FXML
    private TableColumn<BloodRequest, String> bloodGroupCol;
    @FXML
    private TableColumn<BloodRequest, String> amountCol;
    @FXML
    private TableColumn<BloodRequest, String> dateCol;
    @FXML
    private TableColumn<BloodRequest, String> timeCol;
    @FXML
    private TableColumn<BloodRequest, String> hospitalCol;
    @FXML
    private TableColumn<BloodRequest, String> contactNoCol;
    @FXML
    private TableColumn<BloodRequest, String> reasonCol;
    @FXML
    private TableColumn<BloodRequest, String> requestIDCol;
    
    public void initData(User u){
        loggedUser = (Admin)u;
        //userIDLabel.setText(loggedUser.getUserID());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("Name"));
        bloodGroupCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("BloodGroup"));
        amountCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("Amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("DeliveryDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("Time"));
        hospitalCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("HospitalName_Location"));
        contactNoCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("ContactNo"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("Reason"));
        requestIDCol.setCellValueFactory(new PropertyValueFactory<BloodRequest, String>("RequestID"));
        
        LoadBloodRequestList();
    }    
    
    private void LoadBloodRequestList(){
        ObjectInputStream ois=null;
        try {
            BloodRequest p;
            ois = new ObjectInputStream(new FileInputStream("BloodRequestList.bin"));
            while(true){
            p = (BloodRequest) ois.readObject();
            bloodRequestTableView.getItems().add(0, p);
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
    @FXML
    private void SignOutOnClickButton(ActionEvent event) throws IOException {
        Parent backToLoginScene = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Scene Mainscene = new Scene(backToLoginScene);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Mainscene);
        window.show();
    }

    @FXML
    private void AddBloodDonorOnClick(ActionEvent event) throws IOException {
        Parent addDonorParent = FXMLLoader.load(getClass().getResource("AddBloodDonorScene.fxml"));
        Scene addDonorViewScene = new Scene(addDonorParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Add A Blood Donor");
        newWindow.setScene(addDonorViewScene);
        newWindow.show();
    }

    @FXML
    private void AddBloodBankOnClick(ActionEvent event) throws IOException {
        Parent addBloodBankParent = FXMLLoader.load(getClass().getResource("AddBloodBankScene.fxml"));
        Scene addBloodBankViewScene = new Scene(addBloodBankParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Add A Blood Bank");
        newWindow.setScene(addBloodBankViewScene);
        newWindow.show();
    }

    @FXML
    private void AddAUserOnClick(ActionEvent event) throws IOException {
        Parent addUserParent = FXMLLoader.load(getClass().getResource("AddUserScene.fxml"));
        Scene addUserViewScene = new Scene(addUserParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Add A User");
        newWindow.setScene(addUserViewScene);
        newWindow.show();
    }

    @FXML
    private void closeSystemOnClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void addbloodbankonClickmenu(ActionEvent event) throws IOException {
        Parent addBloodBankParent = FXMLLoader.load(getClass().getResource("AddBloodBankScene.fxml"));
        Scene addBloodBankViewScene = new Scene(addBloodBankParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Add A Blood Bank");
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
