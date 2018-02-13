/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author boussandel
 */
public class Participation {
    int id_participation; 
    int idUser ; 
    int idEvenement; 
   static   private  int maxParticipation;  

    public Participation() {
    }
    
    public Participation (int idP,int idU,int idEv,int Max) {
        this.id_participation=idP; 
        this.idUser=idU ; 
        this.idEvenement=idEv; 
        this.maxParticipation=Max ; 
        
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public static int getMaxParticipation() {
        return maxParticipation;
    }

    public static void setNombreParticipation(int maxParticipation) {
        Participation.maxParticipation = maxParticipation;
    }
    
    
    
}