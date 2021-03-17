package com.example.hp.dc_project;

/**
 * Created by HP on 08-08-2018.
 */
public class Mechanist {
    private String id,Enable;
    private String Fname;
    private String Lname;
    private String Address;
    private String Pin;
    private String FilePhoto;
    private String Experience;
    private String Gender;
    private String JobTitle;
    private String Phone;
    private String HireDate;
    private String DOB;

    public Mechanist()
    {

    }
    public Mechanist(String Pin, String Enable , String FilePhoto, String JobTitle, String Gender, String Phone, String HireDate, String Address, String DOB, String Fname, String Lname, String Exp)
    {
        this.Enable=Enable;
        this.Address = Address;
        this.Fname = Fname;
        this.Lname = Lname;
        this.Phone = Phone;
        this.FilePhoto=FilePhoto;
        this.Experience =Experience;
        this.Pin=Pin;
    }


    public String getEnable() {
        return Enable;
    }

    public void setEnable(String Enable) {
        this.Enable = Enable;
    }

    public String getFilePhoto() {
        return FilePhoto;
    }

    public void setFilePhoto(String filePhoto) {
        FilePhoto = filePhoto;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        this.Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        this.Lname = lname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        this.Pin = pin;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.JobTitle = jobTitle;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = Phone;
    }

    public String getHireDate() {
        return HireDate;
    }

    public void setHireDate(String hireDate) {
        this.HireDate = hireDate;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        this.Experience = experience;
    }
}
