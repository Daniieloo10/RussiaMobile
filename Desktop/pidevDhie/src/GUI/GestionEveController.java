/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evenement;
import Services.EvenementServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class GestionEveController implements Initializable {

    @FXML
    private TableView<Evenement> tEvenement;
    @FXML
    private TableColumn<?, ?> nomEve;
    @FXML
    private TableColumn<Evenement, Date> dateEve;
    @FXML
    private TableColumn<?, ?> TypeEve;
    @FXML
    private TableColumn<?, ?> destEve;
    @FXML
    private TableColumn<?, ?> etatEve;
    @FXML
    private TableColumn<?, ?> placeEve;
    @FXML
    private TableColumn<?,?> prixEve;
    
    private ObservableList<Evenement> data;
   
    private Label lbl1;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField jNom;
    @FXML
    private JFXTextField Jdes;
    @FXML
    private JFXTextField jDuree;
    @FXML
    private JFXTextField jType;
    @FXML
    private JFXTextField etats;
    @FXML
    private JFXTextField jprix;
    @FXML
    private JFXTextField jnbr;
    @FXML
    private Label lbl12;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXTextArea jDesc;
    @FXML
    private TableColumn<?, ?> duree;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField jImage;
    @FXML
    private JFXTextField idEve;
    @FXML
    private JFXTextField recherchelabel;
    @FXML
    private TableColumn<?, ?> colImage;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setsCllTable();
       data = FXCollections.observableArrayList();
       loadDataFromDatabase();
       setcellValue();
       modifier.setDisable(true);
       supprimer.setDisable(true);
       anchor.setDisable(true);
       search();
       
       
       
    }    
    
    /*public void setLabel (String text) {
        this.lbl1.setText("xxxxx");
    }*/
    
    public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        recherchelabel.setOnKeyReleased(e -> {
            recherchelabel.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evenement>) evenement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((evenement.getNomEvenement().contains(newValue)) || (evenement.getNomEvenement().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tEvenement.comparatorProperty());
            tEvenement.setItems(sorteddata);
        });
    }
    
    
    
     private void loadDataFromDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/russia2018";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM evenement");
            while (rs.next()) {
                
                int id_e = rs.getInt("idEvenement");
                String nomE = rs.getString("nomEvenement");
                Date dateE = rs.getDate("dateEvenement");
                String destinationE = rs.getString("destination"); 
                String descriptionE = rs.getString("description") ; 
                String dureeE = rs.getString("duree");
                String typeE = rs.getString("type");
                String etatE = rs.getString("etat");
                int nombreE = rs.getInt("nombrePlace") ;
                String img = rs.getString("image");
               Double  prixEve =   rs.getDouble("prix") ;
                System.out.println(prixEve);
                data.add(new Evenement(id_e, nomE, dateE, destinationE, typeE, dureeE, descriptionE,img ,etatE, nombreE, prixEve));
                System.out.println("recup table view ok !");
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tEvenement.setItems(data);
    }
     
      public void setsCllTable() {
        nomEve.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        dateEve.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        destEve.setCellValueFactory(new PropertyValueFactory<>("destination"));
        TypeEve.setCellValueFactory(new PropertyValueFactory<>("type"));
        duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        
        
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatEve.setCellValueFactory(new PropertyValueFactory<>("etat"));
        placeEve.setCellValueFactory(new PropertyValueFactory<>("nombrePlace"));
        prixEve.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }
      
      private void setcellValue() {
        tEvenement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement pl = tEvenement.getItems().get(tEvenement.getSelectionModel().getSelectedIndex());
                idEve.setText(Integer.toString(pl.getIdEvenement()));
                jNom.setText(pl.getNomEvenement());
                date.setValue(pl.getDateEvenement().toLocalDate());
                Jdes.setText((pl.getDestination()));
                jType.setText(pl.getType());
                etats.setText(pl.getEtat());
                jnbr.setText(Integer.toString(pl.getNbrPlaces()));
                jprix.setText(Double.toString(pl.getPrix()));
                jDesc.setText(pl.getDescription()) ; 
                jDuree.setText(pl.getDuree());
                
                
                modifier.setDisable(false);
                supprimer.setDisable(false);
                anchor.setDisable(false);
                
                
                
            }
        });
    }

    @FXML
    private void deleteAction(ActionEvent event) throws SQLException {
       
         int idE = Integer.valueOf(idEve.getText());
         String nomE = (jNom.getText());
         Date datefe = (Date.valueOf(date.getValue()));
         Services.EvenementServices ser = new EvenementServices();
         ser.supprimerEvenement(idE);
        /*String url = "jdbc:mysql://localhost:3306/russia2018";
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        String sql = ("Delete From `evenement`  WHERE `evenement`.`idEvenement` = (?)");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idE);*/
        
        idEve.setText("");
        jNom.setText("");
        date.setValue(null);
        Jdes.setText("");
        jType.setText("");
        etats.setText("");
        jnbr.setText("");
        jprix.setText("");
        jDesc.setText("") ; 
        jDuree.setText("");
        
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evenement supprimé");
        alert.setHeaderText("Suppression");
        alert.setContentText("Suppression:"+nomE+"Prévu le :"+datefe);
        alert.showAndWait();
        
         
    
    
    }

    @FXML
    private void updateAction(ActionEvent event) throws SQLException {
        int idE = Integer.parseInt(idEve.getText());
        String nomE = (jNom.getText());
        Date datefe = (Date.valueOf(date.getValue()));
        String destE = Jdes.getText() ;
        String dureeE = jDuree.getText(); 
        String typeE = jType.getText(); 
        String etatE = etats.getText() ; 
        String descE = jDesc.getText(); 
        String image = jImage.getText(); 
        float prixE = Float.parseFloat(jprix.getText());
        int nbrP = Integer.parseInt(jnbr.getText()) ;
        Evenement even = new Evenement();
        String url = "jdbc:mysql://localhost:3306/russia2018";
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        String sql = ("UPDATE `evenement` SET `nomEvenement` = (?), `description` = (?) , `dateEvenement` = (?) , `duree` = (?),`destination` = (?),`type` = (?) ,`etat` = (?) ,`image` = (?) ,`nombrePlace` = (?) ,`prix` = (?)  WHERE `evenement`.`idEvenement` = (?)");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nomE);
        pstmt.setString(2, descE);
        pstmt.setDate(3, datefe);
        pstmt.setString(4, dureeE);
        pstmt.setString(5, destE);
        pstmt.setString(6, typeE);
        pstmt.setString(7, etatE);
        pstmt.setString(8,image );
        pstmt.setInt(9, nbrP);
        pstmt.setFloat(10, prixE);
        pstmt.setInt(11,idE);
        pstmt.executeUpdate();
        conn.close();
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setHeaderText("Modification");
        alert.setContentText("Modification:"+nomE+"Prévu le :"+datefe);
        alert.showAndWait();
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setContentText("xxxxx");
        alert.showAndWait();*/
    }

    @FXML
    private void recherchebtn(ActionEvent event) {
        Evenement ev = new Evenement();
        Services.EvenementServices ser = new EvenementServices();
        ev.setNomEvenement(recherchelabel.getText());
        Evenement even = ser.recher(recherchelabel.getText());
        if (even == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message de recherche ");
            alert.setContentText(" Cete évenement n'existe pas  ");
            alert.showAndWait();
        } else {
            
                idEve.setText(Integer.toString(ev.getIdEvenement()));
                jNom.setText(ev.getNomEvenement());
                date.setValue(ev.getDateEvenement().toLocalDate());
                Jdes.setText((ev.getDestination()));
                jType.setText(ev.getType());
                etats.setText(ev.getEtat());
                jnbr.setText(Integer.toString(ev.getNbrPlaces()));
                jprix.setText(Double.toString(ev.getPrix()));
                jDesc.setText(ev.getDescription()) ; 
                jDuree.setText(ev.getDuree());
    }
     
}}
