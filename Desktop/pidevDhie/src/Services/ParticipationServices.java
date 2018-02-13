/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boussandel
 */
public class ParticipationServices {
    private  Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
    
    public ParticipationServices() {
        connection = DB.getInstance().getConnection();

    }
        

    
    public void ajouterParticipation(Participation p) {

        try {
            String requete = "insert into participation (idParticipation,idUser,idEvenement,maxParticipation) values (?,?,?,?)";

            ps = connection.prepareStatement(requete);
            ps.setInt(1, p.getIdEvenement());
            ps.setInt(2, p.getIdUser());
            ps.setInt(3, p.getIdEvenement());
            ps.setInt(4, p.getId_participation());
             ps.executeUpdate();
             System.out.println("participation ajouté");
            
             } catch (SQLException ex) {
            Logger.getLogger(Services.ParticipationServices.class.getName()).log(Level.SEVERE, null, ex);

    
            }
   
         }

}
    
    

