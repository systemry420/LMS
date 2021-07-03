package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "students")
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    private long studentID;

    @ColumnInfo(name = "grade_id")
    private long gradeID;

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private String lastLogin;

    public Student(long gradeID, String name, String address, String email, String password, String phone) {
        this.gradeID = gradeID;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", gradeID=" + gradeID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                '}';
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public long getGradeID() {
        return gradeID;
    }

    public void setGradeID(long gradeID) {
        this.gradeID = gradeID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
