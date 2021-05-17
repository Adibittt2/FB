package com.aditya_verma.foodies_business;

import java.util.ArrayList;
import java.util.List;

public class DataModal {

    // variables for storing our image and name.
    String name,mobile,near_area,address,location_text,mode_of_payment,total_bill_price,date;
    ArrayList<String> food_list;

    public DataModal() {
        // empty constructor required for firebase.
    }

    public DataModal(String name, String mobile, String near_area, String address, String location_text, String mode_of_payment, String total_bill_price, String date, ArrayList<String> food_list) {
        this.name = name;
        this.mobile = mobile;
        this.near_area = near_area;
        this.address = address;
        this.location_text = location_text;
        this.mode_of_payment = mode_of_payment;
        this.total_bill_price = total_bill_price;
        this.date = date;
        this.food_list = food_list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNear_area() {
        return near_area;
    }

    public void setNear_area(String near_area) {
        this.near_area = near_area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation_text() {
        return location_text;
    }

    public void setLocation_text(String location_text) {
        this.location_text = location_text;
    }

    public String getMode_of_payment() {
        return mode_of_payment;
    }

    public void setMode_of_payment(String mode_of_payment) {
        this.mode_of_payment = mode_of_payment;
    }

    public String getTotal_bill_price() {
        return total_bill_price;
    }

    public void setTotal_bill_price(String total_bill_price) {
        this.total_bill_price = total_bill_price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getFood_list() {
        return food_list;
    }

    public void setFood_list(ArrayList<String> food_list) {
        this.food_list = food_list;
    }
}