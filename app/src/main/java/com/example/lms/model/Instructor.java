package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructor")
public class Instructor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "instructor_id")
    private int instructorID;

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String password;
    @ColumnInfo(name = "last_login")
    private String lastLogin;

    public Instructor(String name, String address, String phone, String email, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
