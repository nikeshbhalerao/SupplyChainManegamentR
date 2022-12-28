package com.example.supplychain22dec;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId() {
        return id.get();
    }

    public String getName(){
        return name.get();
    }

    public double getPrice(){
        return price.get();
    }

    public Product(int id, String name, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product>getAllProduct(){
        DatabaseConnection databaseconnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProduct = "SELECT * FROM product";
        try{
            ResultSet rs  = databaseconnection.getQueryTable(selectProduct);
            while(rs.next()){
                productList.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),rs.getDouble("price")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
    public static ObservableList<Product>getProductbByName(String productName){
        DatabaseConnection databaseconnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProduct = String.format("SELECT * FROM product WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        try{
            ResultSet rs  = databaseconnection.getQueryTable(selectProduct);
            while(rs.next()){
                productList.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),rs.getDouble("price")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
