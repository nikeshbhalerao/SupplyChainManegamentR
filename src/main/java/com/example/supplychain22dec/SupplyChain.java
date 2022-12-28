package com.example.supplychain22dec;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
//import javafx.scene.control.TableColumn;

import java.io.IOException;

public class SupplyChain extends Application {

    public static final int width = 700, height = 500 , headerBar = 50;
    Pane bodypane = new Pane();
 //   public static int bodyWidth , bodyHeight;
    Login login = new Login();
    ProductDetails productDetails = new ProductDetails();
    Button globalLoginButton;
    Label customerEmailLabel  = null;
    String customerEmail = null;

    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("search") ;
        searchButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                String productName = searchText.getText();
                ;
                //clear body and put this new pane in the body
                bodypane.getChildren().clear();
                bodypane.getChildren().add(productDetails.getProductByName(productName));
            }
        });
        globalLoginButton = new Button("Log In");
        globalLoginButton.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodypane.getChildren().clear();
                bodypane.getChildren().add(loginPage());
                globalLoginButton.setDisable(true);

            }
        }));
        customerEmailLabel = new Label("Welcome user");


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodypane.getMinWidth(),headerBar-10);


        gridPane.setVgap(5);// v- vertical;
        gridPane.setHgap(5);//horizontal
        // gridPane.setStyle(" -fx-background-color: #C0C0C0");

        gridPane.add(searchText, 0,0);
        gridPane.add(searchButton, 1,0);
        gridPane.add(globalLoginButton,2,0);
        gridPane.add(customerEmailLabel,3,0);

        return gridPane;
    }

    private GridPane loginPage() {
        Label emailLabel = new Label("Email");
        Label passwordlabel = new Label("password");
        Label messageLable = new Label("I am message");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Log in");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
               // messageLable.setText(email +" $$" + password);

                if(login.customerLogin(email,password)){
                    messageLable.setText("Login Successful");
                    customerEmail = email;
                    globalLoginButton.setDisable(true);
                    customerEmailLabel.setText("Welcome : " + customerEmail);
                    bodypane.getChildren().clear();
                    bodypane.getChildren().add(productDetails.getAllProducts());
                }
                else{
                    messageLable.setText("Login failed");
                }
            }
        });

        GridPane gridpane = new GridPane();
        gridpane.setMinSize(bodypane.getMinWidth(),bodypane.getMinHeight());//center
        gridpane.setVgap(5);// v- vertical;
        gridpane.setHgap(5);//horizontal
        //gridpane.setStyle(" -fx-background-color: #C0C0C0");

        gridpane.setAlignment(Pos.CENTER); //centr;come email ,password box;

        gridpane.add(emailLabel, 0,0);
        gridpane.add(emailTextField, 1,0);
        gridpane.add(passwordlabel, 0,1);
        gridpane.add(passwordField, 1,1);
        gridpane.add(loginButton,0,2);
        gridpane.add(messageLable,1,2);

        return gridpane;
    }

    private GridPane footerBar(){

        Button addToCartButton = new Button("Add to Cart");
        Button buyNowButton = new Button("Buy Now");

        Label messageLable = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = productDetails.getSelectedProduct();
                if(Order.placeOrder(customerEmail,selectedProduct)){
                    messageLable.setText("Ordered");
                }
                else{
                    messageLable.setText("Order Failed");
                }
            }
        });



        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodypane.getMinWidth(),headerBar+10);


        gridPane.setVgap(5);// v- vertical;
        gridPane.setHgap(20);//horizontal
        // gridPane.setStyle(" -fx-background-color: #C0C0C0");

        //gridPane.setAlignment(Pos.CENTRE);    //<<  position cetre;
        gridPane.setTranslateY(headerBar+height+5);

        gridPane.add(addToCartButton, 0,0);
        gridPane.add(buyNowButton, 1,0);
        gridPane.add(messageLable, 2,0);


        return gridPane;
    }



    private Pane createContent(){
        Pane root = new Pane();

        root.setPrefSize(width,height+2*headerBar);
        bodypane.setMinSize(width,height);
        bodypane.setTranslateY(headerBar);

        bodypane.getChildren().addAll(productDetails.getAllProducts());

        root.getChildren().addAll(headerBar(), bodypane,footerBar());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
