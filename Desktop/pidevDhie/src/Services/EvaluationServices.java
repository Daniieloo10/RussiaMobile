/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entités.EvaluationEvenement;
/**
 *
 * @author boussandel
 */
public class EvaluationServices {
    
     private  Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
    
    public EvaluationServices() {
                connection = DB.getInstance().getConnection();

    }
   
   
   
    
 public void ajouter_Evaluation(EvaluationEvenement eva) {

       try {
          String requete = "insert into `evaluation` (evaluationId,noteEvenement,prenomParticipant,nomParticipant) values (?,?,?,?)";

            ps = connection.prepareStatement(requete);
            ps.setInt(1, eva.getEvaluationId());
            ps.setFloat(2, eva.getNoteEvenement());
             ps.setString(3, eva.getPrenom_Participant()) ;
              ps.setString(4, eva.getNomParticipant()) ;
               
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Services.EvaluationServices.class.getName()).log(Level.SEVERE, null, ex);

        }}  
    
}
