/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evenement;
import Services.EvenementServices;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class AjoutController implements Initializable {

    @FXML
    private TextField textNom;
    @FXML
    private TextField textDestination;
    @FXML
    private TextArea textDescription;
    @FXML
    private TextField etat;
    @FXML
    private TextField type;
    @FXML
    private TextField duree;
    @FXML
    private TextField nombrePlaces;
    @FXML
    private TextField prix;
    @FXML
    private TextField image;
    @FXML
    private DatePicker date;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAnnuler;
    @FXML
    private ImageView imageVue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterEvenement(ActionEvent event) throws ParseException, IOException {
        
        String nomEvenement = textNom.getText() ; 
        String destination=textDestination.getText(); 
        String Description = this.textDescription.getText(); 
        LocalDate dateEvenement = date.getValue(); 
        String etatEvenement = etat.getText(); 
        String typeEvenement = type.getText(); 
        String dureeEvenement=duree.getText(); 
        String imageEvenement=image.getText();
        int nbrPlaces = Integer.parseInt(nombrePlaces.getText());
        float prixEvenement = Float.parseFloat(prix.getText());
      java.sql.Date dateev = java.sql.Date.valueOf(dateEvenement);
        
        
Evenement ev = new Evenement(nomEvenement, Description, dateev, dureeEvenement, Description, typeEvenement, destination, imageEvenement, nbrPlaces, prixEvenement, nbrPlaces);
        
        Services.EvenementServices es = new EvenementServices(); 
        es.ajouterEvenement(ev);
        
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merci ");
            alert.setContentText("Evenement ajouté avec succées  ");
            alert.showAndWait();*/
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("FXMLAjout2.fxml")));
                Parent root = loader.load();
                FXMLAjout2Controller FXMLAjout2Controller = loader.getController();
                FXMLAjout2Controller.setNom(nomEvenement);
                Scene scene = textNom.getScene();
                scene.setRoot(root);
                
                
                
                
            
            
      
        
        
        
        
        
                
                
        
    }

    @FXML
    private void annulerEvenement(ActionEvent event) {
        
    }
    
}
