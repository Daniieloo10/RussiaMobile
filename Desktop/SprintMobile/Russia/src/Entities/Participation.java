/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author boussandel
 */
public class Participation {
    
    private int idparticipation ; 
    private int iduser ; 
    private int idevenement ; 

    public Participation() {
    }

    
    public Participation(int idparticipation, int iduser, int idevenement) {
        this.idparticipation = idparticipation;
        this.iduser = iduser;
        this.idevenement = idevenement;
    }

    public Participation(int iduser, int idevenement) {
        this.iduser = iduser;
        this.idevenement = idevenement;
    }

    public int getIdparticipation() {
        return idparticipation;
    }

    public int getIduser() {
        return iduser;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    @Override
    public String toString() {
        return "Participation{" + "idparticipation=" + idparticipation + ", iduser=" + iduser + ", idevenement=" + idevenement + '}';
    }
    
    
    
    
}
