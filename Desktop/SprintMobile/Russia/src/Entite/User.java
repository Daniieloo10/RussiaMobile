/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Dambo
 */
public class User {
    static int id;
    static  String username;
    static String pass;
    int idu;
    String usernameu;
   String passu;

    public User(int id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        
    }

    public User() {
        
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getUsernameu() {
        return usernameu;
    }

    public void setUsernameu(String usernameu) {
        this.usernameu = usernameu;
    }

    public String getPassu() {
        return passu;
    }

    public void setPassu(String passu) {
        this.passu = passu;
    }
    

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        User.pass = pass;
    }
    
    
    
}
