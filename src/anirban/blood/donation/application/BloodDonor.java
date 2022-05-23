/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anirban.blood.donation.application;

import java.io.Serializable;


public class BloodDonor implements Serializable{
    private String DonorName;
    private String DonorID;
    private String BloodGroup;
    private String ContactNo;
    private String SecondaryContactNo;
    private String LastDonated;
    private String Location;

    public BloodDonor(String DonorName, String DonorID, String BloodGroup, String ContactNo, String SecondaryContactNo, String LastDonated, String Location) {
        this.DonorName = DonorName;
        this.DonorID = DonorID;
        this.BloodGroup = BloodGroup;
        this.ContactNo = ContactNo;
        this.SecondaryContactNo = SecondaryContactNo;
        this.LastDonated = LastDonated;
        this.Location = Location;
    }

    public String getDonorName() {
        return DonorName;
    }

    public String getDonorID() {
        return DonorID;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getSecondaryContactNo() {
        return SecondaryContactNo;
    }

    public String getLastDonated() {
        return LastDonated;
    }

    public String getLocation() {
        return Location;
    }

    public void setDonorName(String DonorName) {
        this.DonorName = DonorName;
    }

    public void setDonorID(String DonorID) {
        this.DonorID = DonorID;
    }

    public void setBloodGroup(String BloodGroup) {
        this.BloodGroup = BloodGroup;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public void setSecondaryContactNo(String SecondaryContactNo) {
        this.SecondaryContactNo = SecondaryContactNo;
    }

    public void setLastDonated(String LastDonated) {
        this.LastDonated = LastDonated;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return "BloodDonor{" + "DonorName=" + DonorName + ", DonorID=" + DonorID + ", BloodGroup=" + BloodGroup + ", ContactNo=" + ContactNo + ", SecondaryContactNo=" + SecondaryContactNo + ", LastDonated=" + LastDonated + ", Location=" + Location + '}';
    }
    
    
    
}
