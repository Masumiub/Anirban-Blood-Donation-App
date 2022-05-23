
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


public class OthersBloodRequestSceneController implements Initializable {

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
}
