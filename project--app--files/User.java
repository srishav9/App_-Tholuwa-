package com.example.hp.dc_project;

/**
 * Created by HP on 09-07-2018.
 */
public class User {
    public String fname,lname,address,pin,phn;

    public User(){

    }

    public User(String fname, String lname, String address, String pin, String phn) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.pin = pin;
        this.phn = phn;
    }

    public String getFname() {return fname;}

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address ="Address: " +address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

}
