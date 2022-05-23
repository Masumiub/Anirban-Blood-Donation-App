/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable{
    private String UserID,
            Password, 
            FullName, 
            BloodGroup, 
            UserType, 
            LastBloodDonatedDate, 
            ContactNumber,
            SecondaryContactNumber, 
            Location;

    public User(String UserID, String Password, String FullName, String BloodGroup, String UserType, String LastBloodDonatedDate, String ContactNumber, String SecondaryContactNumber, String Location) {
        this.UserID = UserID;
        this.Password = Password;
        this.FullName = FullName;
        this.BloodGroup = BloodGroup;
        this.UserType = UserType;
        this.LastBloodDonatedDate = LastBloodDonatedDate;
        this.ContactNumber = ContactNumber;
        this.SecondaryContactNumber = SecondaryContactNumber;
        this.Location = Location;
    }

    public String getUserID() {
        return UserID;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullName() {
        return FullName;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getUserType() {
        return UserType;
    }

    public String getLastBloodDonatedDate() {
        return LastBloodDonatedDate;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getSecondaryContactNumber() {
        return SecondaryContactNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setBloodGroup(String BloodGroup) {
        this.BloodGroup = BloodGroup;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public void setLastBloodDonatedDate(String LastBloodDonatedDate) {
        this.LastBloodDonatedDate = LastBloodDonatedDate;
    }

    public void setContactNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }

    public void setSecondaryContactNumber(String SecondaryContactNumber) {
        this.SecondaryContactNumber = SecondaryContactNumber;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", Password=" + Password + ", FullName=" + FullName + ", BloodGroup=" + BloodGroup + ", UserType=" + UserType + ", LastBloodDonatedDate=" + LastBloodDonatedDate + ", ContactNumber=" + ContactNumber + ", SecondaryContactNumber=" + SecondaryContactNumber + ", Location=" + Location + '}';
    }
    
    public static ArrayList<User> listOfUser(){
        ArrayList<User>uList = new ArrayList<>();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("User.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User u;
            try{
                while(true){
                    u = (User)ois.readObject();
                    uList.add(u);
                }
            }
            catch(Exception e){}                
        } 
        catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) {
                    ois.close();
                }
            } 
            catch (IOException ex){}
        }    
        return uList;
    }
    
    public static User verifylogin(String userID, String password, String userType){
        ArrayList<User>uList = User.listOfUser();
        for(User i: uList){
            if(i.getUserID().equals(userID) && i.getPassword().equals(password) && i.getUserType().equals(userType)){
                return i;
            }
        }
        return null;
    }
    
    public abstract void addUser();
}
