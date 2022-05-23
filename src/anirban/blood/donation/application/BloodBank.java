/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.Serializable;


public class BloodBank implements Serializable{
    private String BloodBankName;
    private String ContactNumber;
    private String Location;

    public BloodBank(String BloodBankName, String ContactNumber, String Location) {
        this.BloodBankName = BloodBankName;
        this.ContactNumber = ContactNumber;
        this.Location = Location;
    }

    public String getBloodBankName() {
        return BloodBankName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setBloodBankName(String BloodBankName) {
        this.BloodBankName = BloodBankName;
    }

    public void setContactNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return "BloodBank{" + "BloodBankName=" + BloodBankName + ", ContactNumber=" + ContactNumber + ", Location=" + Location + '}';
    }
    
    
}
