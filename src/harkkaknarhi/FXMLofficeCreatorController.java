/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Qnaerhi
 */
public class FXMLofficeCreatorController implements Initializable {

    @FXML
    private TextField cityField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityIDField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField openingField;
    @FXML
    private TextField latField;
    @FXML
    private TextField lngField;
    @FXML
    private Button addButton;
    @FXML
    private Label label;
    @FXML
    private WebView webbi;
    @FXML
    private Button deletePoint;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webbi.getEngine().load(getClass().getResource("index.html").toExternalForm());
        // TODO
    }    
    private void draw() {
        String street = addressField.getText();
        String postal = cityIDField.getText();
        String city = cityField.getText();
        String info = openingField.getText();
        
        webbi.getEngine().executeScript("document.goToLocation('"+street+","+postal+","+city+",','"+info+"','red')");
        }

    @FXML
    private void addPoint(ActionEvent event) {
        dataBase db = new dataBase();
        int cityID = Integer.parseInt(cityIDField.getText());
        String city = cityField.getText().toUpperCase();
        String add = addressField.getText();
        String name = nameField.getText();
        String op = openingField.getText();
        float lat = Float.parseFloat(latField.getText());
        float lng = Float.parseFloat(lngField.getText());
        if (db.duplicateCheck("automaatti",name)) {
            Automaatti au = new Automaatti(cityID,city,add,name,lat,lng,op);
            if (db.insertPoints(au)) {
                draw();
                label.setText("homma doned");
            }
        } else {
            label.setText("Samanniminen piste on jo olemassa!");
        }
    }

    @FXML
    private void deletePoint(ActionEvent event) {
        dataBase db = new dataBase();
        int cityID = Integer.parseInt(cityIDField.getText());
        String city = cityField.getText().toUpperCase();
        String add = addressField.getText();
        String name = nameField.getText();
        if (db.deleteAutomaatti(city,add,cityID,name)){
            label.setText("piste poistettu");
        }
    }
}
