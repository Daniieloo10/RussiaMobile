/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author boussandel
 */
public class User {
   private int idUser ; 
   private String nom ; 
   private String prenom ; 
   private String email ; 
   private String password ; 
   private String telephone ; 
   private String nationalite ; 
   private String role ; 
   private String login ; 
   
   public User () {
       
   }

    public User(String nom, String prenom, String email, String password, String telephone, String nationalite, String role, String login) {
        this.nom=nom ; 
        this.prenom=prenom ; 
        this.email = email ; 
        this.password=password ; 
        this.telephone=telephone; 
        this.nationalite=nationalite; 
        this.role=role ; 
        this.login=login ;
    }

    public User(int aInt, String string, String string0) {
        this.idUser=aInt; 
        this.nom=string ; 
        this.prenom=string0;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
}
