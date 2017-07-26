/*
Lähteet: tutorialspoint.com, w3schools.com, stackoverflow.com, luentovideot, esimerkkirepository
Tekijä: Kuisma Närhi 0453757
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.web;
import static javafx.scene.paint.Color.web;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Qnaerhi
 */
public class FXMLWebViewController implements Initializable {

    @FXML
    private Button loadButton;
    @FXML
    private WebView webbi;
    
    @FXML
    private TextField cityField;
    @FXML
    private ComboBox<String> officeList;
    @FXML
    private Label infotext;
    @FXML
    private Button endPoint;
    @FXML
    private ComboBox<String> endOfficeList;
    @FXML
    private Button listbutton2;
    @FXML
    private Button createOffice;
    @FXML
    private ComboBox<String> pkgList;
    @FXML
    private Button clearbtn;
    @FXML
    private Button pkgButton;
    @FXML
    private Button mkPkg;
    @FXML
    private Button mkEsine;
    @FXML
    private Button logbtn;
   
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webbi.getEngine().load(getClass().getResource("index.html").toExternalForm());
        dataBase db = new dataBase();
        infotext.setText("active user: " +db.getActiveUser().getName());
        
        
    }
    
    
    @FXML
    private void listOffices(ActionEvent event) {
        officeList.getItems().clear();
        String city = cityField.getText().toUpperCase();
        dataBase db = new dataBase();
        ArrayList lista = db.returnAutomaatti("city", city);
        int i;
        for(i = 0; i<lista.size(); i++) {
            Automaatti au = (Automaatti) lista.get(i);
            String rivi = au.name;
            officeList.getItems().addAll(rivi);
        }
    }

    

    @FXML
    private void drawRoute(ActionEvent event) {
        String name = officeList.getValue();
        dataBase db = new dataBase();
        ArrayList<Automaatti> al = new ArrayList<>();
        al = db.returnAutomaatti("name",name);
        int id = al.get(0).id;
        String street = al.get(0).address;
        int postal = al.get(0).cityID;
        String city = al.get(0).city;
        String info = al.get(0).openinghrs;
        webbi.getEngine().executeScript("document.goToLocation('"+street+","+postal+","+city+",','"+name+" "+info+"','red')");
        
        String name2 = endOfficeList.getValue();
        ArrayList<Automaatti> all = new ArrayList<>();
        all = db.returnAutomaatti("name",name2);
        int id2 = all.get(0).id;
        String street2 = all.get(0).address;
        int postal2 = all.get(0).cityID;
        String city2 = all.get(0).city;
        String info2 = all.get(0).openinghrs;
        webbi.getEngine().executeScript("document.goToLocation('"+street2+","+postal2+","+city2+",','"+name2+" "+info2+"','blue')");
        
        char pkgInfo = pkgList.getValue().charAt(0);
        int pkgID = Character.getNumericValue(pkgInfo);
        System.out.println(pkgID);
        paketti pk = db.getPakettis("pakettiID",pkgID).get(0);
        lahetys lh = new lahetys(pk.userID,pk.lk,pk.op);
        lh.setLahto(al.get(0));
        System.out.println(lh.getLahto().id+lh.getLahto().name);
        lh.setPaate(all.get(0));
        ArrayList coords = new ArrayList<>();
        coords.add(al.get(0).lat);
        coords.add(al.get(0).lng);
        coords.add(all.get(0).lat);
        coords.add(all.get(0).lng);
        int speed = db.getLuokka(pk.lk).nopeus;
        float dist = db.getLuokka(pk.lk).etaisyys;
        timotei mies = new timotei();
        if (Float.parseFloat(webbi.getEngine().executeScript("document.createPath("+ coords +",'blue',"+speed+")")+"f")< dist) {
            if (mies.testaa(pk)) {
                webbi.getEngine().executeScript("document.deletePaths()");
                webbi.getEngine().executeScript("document.createPath("+ coords +",'red',"+speed+")");
                String status = "Paketti pääsi perille!";
                infotext.setText(status);
                lh.setStatus(status);
                lh.setMatka(Float.parseFloat(webbi.getEngine().executeScript("document.createPath("+ coords +",'blue',"+speed+")")+"f"));
                System.out.println(al.get(0).id+all.get(0).id+pkgID+status+Float.parseFloat(webbi.getEngine().executeScript("document.createPath("+ coords +",'red',"+speed+")")+"f"));
                db.lahetys(al.get(0).name, all.get(0).name, pkgID, status, Float.parseFloat(webbi.getEngine().executeScript("document.createPath("+ coords +",'red',"+speed+")")+"f"));
            } else {
                String status = "Paketille tapahtui inhimillinen vahinko matkalla.";
                lh.setStatus(status);
                infotext.setText(status);
                db.lahetys(lh.getLahto().name,lh.getPaate().name,pk.pakettiID,status,0);
            }
            
        } else {
            infotext.setText("Etäisyys on liian pitkä tälle pakettiluokalle!");
            webbi.getEngine().executeScript("document.deletePaths()");
        }
    }
     

    @FXML
    private void listEndOffices(ActionEvent event) {
        endOfficeList.getItems().clear();
        String city = cityField.getText().toUpperCase();
        dataBase db = new dataBase();
        ArrayList lista = db.returnAutomaatti("city", city);
        int i;
        for(i = 0; i<lista.size(); i++) {
            Automaatti au = (Automaatti) lista.get(i);
            String rivi = au.name;
            endOfficeList.getItems().addAll(rivi);
        }
    }

   

    @FXML
    private void createOffice(ActionEvent event) {
        try {
                Stage webview = new Stage();

                Parent page = FXMLLoader.load(getClass().getResource("FXMLofficeCreator.fxml"));

                Scene scene = new Scene(page);

                webview.setScene(scene);
                webview.show();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void clearRoads(ActionEvent event) {
        webbi.getEngine().executeScript("document.deletePaths()");
    }

    @FXML
    private void listPKG(ActionEvent event) {
        pkgList.getItems().clear();
        dataBase db = new dataBase();
        ArrayList<paketti> lista = db.getPakettis("userID",db.getActiveUser().id);
        int i;
        for(i = 0; i<lista.size(); i++) {
            paketti pk = lista.get(i);
            String rivi = (pk.getPakettiID() +". "+ db.getOpjekt("esineID", Integer.toString(pk.op)).name + " Pakettiluokka: " +db.getLuokka(pk.lk).label);
            System.out.println(rivi);
            pkgList.getItems().addAll(rivi);
        }
    }

    @FXML
    private void openPkgWindow(ActionEvent event) {
        try {
            
            Stage webview = new Stage();
        
            Parent page = FXMLLoader.load(getClass().getResource("FXMLPackageCreator.fxml"));
        
            Scene scene = new Scene(page);
        
            webview.setScene(scene);
            webview.show();          
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void makeThing(ActionEvent event) {
        try {
                Stage webview = new Stage();

                Parent page = FXMLLoader.load(getClass().getResource("FXMLmakeItem.fxml"));

                Scene scene = new Scene(page);

                webview.setScene(scene);
                webview.show();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void log(ActionEvent event) throws IOException {
        logger l = new logger();
        l.log();
    }

    
}
    

   
     //("document.goToLocation('" + street + "," + postal + "," + city + "',asd','blue')")
    //("document.goToLocation('Kalevankatu 59, 04230, Kerava','asd','red')") <--toimiva


    

