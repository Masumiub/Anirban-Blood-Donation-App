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
public class BloodRequest implements Serializable{
    private String Name;
    private String BloodGroup;
    private String Amount;
    private String DeliveryDate;
    private String Time;
    private String HospitalName_Location;
    private String ContactNo;
    private String Reason;
    private String RequestID;

    public String getName() {
        return Name;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getAmount() {
        return Amount;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public String getTime() {
        return Time;
    }

    public String getHospitalName_Location() {
        return HospitalName_Location;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getReason() {
        return Reason;
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setBloodGroup(String BloodGroup) {
        this.BloodGroup = BloodGroup;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public void setDeliveryDate(String DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public void setHospitalName_Location(String HospitalName_Location) {
        this.HospitalName_Location = HospitalName_Location;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public void setRequestID(String RequestID) {
        this.RequestID = RequestID;
    }

    public BloodRequest(String Name, String BloodGroup, String Amount, String DeliveryDate, String Time, String HospitalName_Location, String ContactNo, String Reason, String RequestID) {
        this.Name = Name;
        this.BloodGroup = BloodGroup;
        this.Amount = Amount;
        this.DeliveryDate = DeliveryDate;
        this.Time = Time;
        this.HospitalName_Location = HospitalName_Location;
        this.ContactNo = ContactNo;
        this.Reason = Reason;
        this.RequestID = RequestID;
    }

    @Override
    public String toString() {
        return "BloodRequest{" + "Name=" + Name + ", BloodGroup=" + BloodGroup + ", Amount=" + Amount + ", DeliveryDate=" + DeliveryDate + ", Time=" + Time + ", HospitalName_Location=" + HospitalName_Location + ", ContactNo=" + ContactNo + ", Reason=" + Reason + ", RequestID=" + RequestID + '}';
    }
    
    
}
