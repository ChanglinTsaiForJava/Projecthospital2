package com.luv2code.springboot.cruddemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="midterm")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hospital {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pk")
    private int id;

    @JsonProperty("院所名稱")
    @Column(name="院所名稱")
    private String name;

    @JsonProperty("地址")
    @Column(name="地址")
    private String address;

    @JsonProperty("電話")
    @Column(name="電話")
    private String phoneNumber;

    @Column(name="建立者")
    private String creator;

    @Column(name="修改者")
    private String modifier;



    public Hospital() {

    }

    public Hospital( String name, String address, String phoneNumber) {

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Hospital(String name, String address, String phoneNumber, String creator, String modifier) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creator = creator;
        this.modifier = modifier;
    }
// define constructors


    // define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    // define toString
    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}








