package com.example.supplychain22dec;

public class Order {
    public static boolean placeOrder(String customerEmail , Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format("INSERT INTO orders ( customer , product_id ) VALUES ((SELECT customerid FROM customer WHERE email = '%s'),%s)",customerEmail,product.getId());
        int rowCount =0;

        try{
            rowCount = databaseConnection.executeUpdateQuery(query);

        }catch(Exception e){
           e.printStackTrace();
        }
        return rowCount!=0;
    }

}
