/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.Serializable;

/**
 *
 * @author movie
 */
public class Admin extends User implements Serializable{

    public Admin(String UserID, String Password, String FullName, String BloodGroup, String UserType, String LastBloodDonatedDate, String ContactNumber, String SecondaryContactNumber, String Location) {
        super(UserID, Password, FullName, BloodGroup, UserType, LastBloodDonatedDate, ContactNumber, SecondaryContactNumber, Location);
    }
    
    @Override
    public void addUser(){
        int num1 = 1;
        int num2 = 2;
        int res = num1+num2;
    }
        
}
