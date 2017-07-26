/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Qnaerhi
 */
public class FXMLmakeItemController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField depthField;
    @FXML
    private TextField breakField;
    @FXML
    private Button button;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createItem(ActionEvent event) {
        dataBase db = new dataBase();
        opjekt o = new opjekt(nameField.getText(),Integer.parseInt(weightField.getText()),Integer.parseInt(heightField.getText()),Integer.parseInt(widthField.getText()),Integer.parseInt(depthField.getText()),Integer.parseInt(breakField.getText()));
        db.createItem(o);
        try {
                Stage webview = new Stage();

                Parent page = FXMLLoader.load(getClass().getResource("FXMLPackageCreator.fxml"));

                Scene scene = new Scene(page);

                webview.setScene(scene);
                webview.show();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    
}
