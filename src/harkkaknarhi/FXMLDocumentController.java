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
import org.sqlite.SQLiteException;

/**
 *
 * @author Qnaerhi
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;
    @FXML
    private Button logButton;
    
    
    @FXML
    private void startWebView(ActionEvent event) throws Exception {
        dataBase db = new dataBase();
        db.updateUserActivity();
        if (db.duplicateCheck("kayttajat", userField.getText())) {
            db.insertUser(userField.getText(),passField.getText());
            db.setActiveUser(userField.getText());
            try {
                
                Stage webview = new Stage();

                Parent page = FXMLLoader.load(getClass().getResource("FXMLWebView.fxml"));

                Scene scene = new Scene(page);

                webview.setScene(scene);
                webview.show();
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            label.setText("käyttäjänimi on varattu!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*dataBase db = new dataBase();
            try {
                db.create();
                PageReader in = new PageReader();
                try {
                    in.loadDocument();
                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }    
            } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }     */   // Ylläolevaa koodia tarvittiin vain alkuperäisen tietokannan luomiseen. Jos dataBase.db tulee sellaisenaan tiedostoissa, kaikki on ok.
    
    }    

    @FXML
    private void logIn(ActionEvent event) {
        dataBase db = new dataBase();
        db.updateUserActivity();
        String input = userField.getText();
        String inputpw = passField.getText();
        if(db.pwcheck(input, inputpw)) {
        db.setActiveUser(input);
        try {
            
            Stage webview = new Stage();
        
            Parent page = FXMLLoader.load(getClass().getResource("FXMLWebView.fxml"));
        
            Scene scene = new Scene(page);
        
            webview.setScene(scene);
            webview.show();
            Stage stage = (Stage) logButton.getScene().getWindow();
            stage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else {
            label.setText("Virhe kirjautumisessa");
        }
    }


    
}
