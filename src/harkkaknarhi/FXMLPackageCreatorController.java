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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Qnaerhi
 */
public class FXMLPackageCreatorController implements Initializable {

    @FXML
    private ComboBox<String> thingList;
    @FXML
    private ComboBox<String> classList;
    @FXML
    private Label infoLabel;
    @FXML
    private Button addBox;
    @FXML
    private Button addBtn;
    @FXML
    private Label packageInfo;
    @FXML
    private Button createPKG;
    @FXML
    private Button createThingButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataBase db = new dataBase();
        ArrayList<opjekt> lista = db.getOpjekts();
        int i;
        for(i = 0; i<lista.size(); i++) {
            opjekt o = (opjekt) lista.get(i);
            String rivi = o.name;
            thingList.getItems().addAll(rivi);
        }
        ArrayList<luokka> lister = db.getLuokkas();
        int j;
        for(j = 0; j<lister.size(); j++) {
            luokka l = (luokka) lister.get(j);
            String river = Integer.toString(l.label);
            classList.getItems().addAll(river);
        }
    }    

    @FXML
    private void addThing(ActionEvent event) {
        String item = thingList.getValue();
        dataBase db = new dataBase();
        opjekt i = db.getOpjekt("name",item);
        infoLabel.setText("info: \n" + i.labelWriter(i));
        
    }

    @FXML
    private void setPkg(ActionEvent event) {
        int label = Integer.parseInt(classList.getValue());
        dataBase db = new dataBase();
        luokka l = db.getLuokka(label);
        infoLabel.setText("info: \n" + l.labelWriter(l));
        
    }

    @FXML
    private void exportPkg(ActionEvent event) {
        int label = Integer.parseInt(classList.getValue());
        dataBase db = new dataBase();
        luokka l = db.getLuokka(label);
        String item = thingList.getValue();
       
        opjekt i = db.getOpjekt("name",item);
        pakkaaja p = new pakkaaja(i, l);
        packageInfo.setText(p.pack(i, l).intel);
        activeUser au =  db.getActiveUser();
        int a = au.getId();
        int idi = i.id;
        if (p.pack(i,l).b) {
            paketti pkg = new paketti(a,idi,label);
            db.pakettiin(pkg);
            System.out.println("paketti lisätty");
            Stage stage = (Stage) createPKG.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void createThing(ActionEvent event) {
        try {
            
            Stage webview = new Stage();
        
            Parent page = FXMLLoader.load(getClass().getResource("FXMLmakeItem.fxml"));
        
            Scene scene = new Scene(page);
        
            webview.setScene(scene);
            webview.show();
            Stage stage = (Stage) createPKG.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
