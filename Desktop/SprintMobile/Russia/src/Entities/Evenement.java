/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author boussandel
 */
public class Evenement {
    private int idEvenement;
    private String nomEvenement;
    private String destination;
    private String description;
    private String dateEvenement;
    private String etat ;  //true : disponible ------ false : n'est pas disponible
    private String duree;
    private String type ; 
    private String image;
    private  int nbrPlaces; 
    private int id_user; 
    private double prix;
    
    
    public Evenement () {
        
    }
    
   

   
    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(String dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getPrix() {
        return (float) prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", nomEvenement=" + nomEvenement + ", destination=" + destination + ", description=" + description + ", dateEvenement=" + dateEvenement + ", etat=" + etat + ", duree=" + duree + ", type=" + type + ", image=" + image + ", nbrPlaces=" + nbrPlaces + ", id_user=" + id_user + ", prix=" + prix + '}';
    }

  
    
    
    
    

    
    
    
    
    
    
}
