package com.softwear.webapp5.data;

public class UserEditProfileDTO {
    
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String mobileNumber;
    private String birthDate;

    public UserEditProfileDTO(String name, String lastName, String address, String email, String mobileNumber,
            String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    
}
