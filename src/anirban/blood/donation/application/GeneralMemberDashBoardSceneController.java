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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class GeneralMemberDashBoardSceneController implements Initializable {

    GeneralMember loggedUser;
    @FXML
    private ComboBox<String> ReqBloodGroupComboBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private DatePicker deliveryDateTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextField hospitalNameTextField;
    @FXML
    private TextField reasonTextField;
    @FXML
    private ComboBox<String> SeaBloodGroupComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField contactNoTextField;
    
    private String requestedID;
    @FXML
    private TableView<BloodDonor> donorTableView;
    @FXML
    private TableColumn<BloodDonor, String> donorNameCol;
    @FXML
    private TableColumn<BloodDonor, String> bloodGroupCol;
    @FXML
    private TableColumn<BloodDonor, String> ContactNoCol;
    @FXML
    private TableColumn<BloodDonor, String> SecondContactNo;
    @FXML
    private TableColumn<BloodDonor, String> lastDonatedCol;
    @FXML
    private TableColumn<BloodDonor, String> locationCol;
    @FXML
    private TableColumn<BloodDonor, String> donorIDCol;
    
    public void initData(User u){
        loggedUser = (GeneralMember)u;
        //userIDLabel.setText(loggedUser.getUserID());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReqBloodGroupComboBox.getItems().addAll("A+", "B+", "AB+" , "O+", "A-", "B-", "AB-", "O-");
        ReqBloodGroupComboBox.setValue("A+");
        
        SeaBloodGroupComboBox.getItems().addAll("A+", "B+", "AB+" , "O+", "A-", "B-", "AB-", "O-");
        SeaBloodGroupComboBox.setValue("A+");
        
        donorNameCol.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("DonorName"));
        bloodGroupCol.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("BloodGroup"));
        ContactNoCol.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("ContactNo"));
        SecondContactNo.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("SecondaryContactNo"));
        lastDonatedCol.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("LastDonated"));
        locationCol.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("Location"));
        donorIDCol.setCellValueFactory(new PropertyValueFactory<BloodDonor, String>("DonorID"));
        
    }    

    @FXML
    private void SignOutOnClickButton(ActionEvent event) throws IOException {
        Parent backToLoginScene = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Scene Mainscene = new Scene(backToLoginScene);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Mainscene);
        window.show();
    }
    
    public String requestID(){
        /*int id = 1000;
        requestedID = String.valueOf(id);
        return requestedID;*/
        int id;
        Random random = new Random(System. nanoTime()); 
        id = random. nextInt(10000000);
        requestedID = String.valueOf(id);
        return requestedID;
    }
    @FXML
    private void RequestBloodOnClickButton(ActionEvent event) {
        if(nameTextField.getText().equals("")|| amountTextField.getText().equals("") || deliveryDateTextField.getValue().toString().equals("") 
            || timeTextField.getText().equals("") ||  hospitalNameTextField.getText().equals("")|| contactNoTextField.getText().equals("") || reasonTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please fillup the required form.");
            a.showAndWait();
        }
        else{
        String returnedID;
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("BloodRequestList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }

            BloodRequest newBloodRequest= new BloodRequest( nameTextField.getText(),
                    ReqBloodGroupComboBox.getSelectionModel().getSelectedItem(), amountTextField.getText(), deliveryDateTextField.getValue().toString(),
                    timeTextField.getText(), hospitalNameTextField.getText(), contactNoTextField.getText(), reasonTextField.getText(), returnedID = requestID() );
            
            oos.writeObject(newBloodRequest);
            
            //System.out.println(newBloodRequest.toString());
            
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
        a.setContentText("Success! Your Blood Request is sent. Please wait.");
        a.showAndWait();
        nameTextField.clear(); amountTextField.clear(); timeTextField.clear(); hospitalNameTextField.clear(); contactNoTextField.clear(); reasonTextField.clear();
    }
    }

    @FXML
    private void searchDonorOnClick(ActionEvent event) {
                ObjectInputStream ois=null;
         try {
            BloodDonor donor; 
            ois = new ObjectInputStream(new FileInputStream("BloodDonorList.bin"));
           
            while(true){
                donor = (BloodDonor) ois.readObject();
                String selectedBloodGroup = donor.getBloodGroup();
                if(selectedBloodGroup.equals((String)SeaBloodGroupComboBox.getSelectionModel().getSelectedItem())){
                    donorTableView.getItems().add(0, donor);
                }
            }
            
            
        } catch (Exception ex) {
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
    private void viewBloodBankListOnClick(ActionEvent event) throws IOException {
        Parent bloodbanklistscene = FXMLLoader.load(getClass().getResource("BloodBankListScene.fxml"));
        Scene bloodbanklistViewscene = new Scene(bloodbanklistscene);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Blood Bank List");
        newWindow.setScene(bloodbanklistViewscene);
        newWindow.show();
    }

    @FXML
    private void ViewOthersBloodRequestOnClick(ActionEvent event) throws IOException {
        Parent otherBloodRequestscene = FXMLLoader.load(getClass().getResource("OthersBloodRequestScene.fxml"));
        Scene otherBloodRequestViewscene = new Scene(otherBloodRequestscene);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Others Blood Request List");
        newWindow.setScene(otherBloodRequestViewscene);
        newWindow.show();
    }

    @FXML
    private void closeSystemOnClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void viewbankListOnClickMenu(ActionEvent event) throws IOException {
        Parent bloodbanklistscene = FXMLLoader.load(getClass().getResource("BloodBankListScene.fxml"));
        Scene bloodbanklistViewscene = new Scene(bloodbanklistscene);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Blood Bank List");
        newWindow.setScene(bloodbanklistViewscene);
        newWindow.show();
    }

    @FXML
    private void viewFAQSOnClick(ActionEvent event) throws IOException {
        Parent addBloodBankParent = FXMLLoader.load(getClass().getResource("FAQsScene.fxml"));
        Scene addBloodBankViewScene = new Scene(addBloodBankParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("FAQs");
        newWindow.setScene(addBloodBankViewScene);
        newWindow.show();
    }
    
    
}
