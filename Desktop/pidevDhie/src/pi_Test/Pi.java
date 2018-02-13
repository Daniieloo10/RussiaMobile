/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_Test;


import Entités.EvaluationEvenement;
import Entités.Evenement;
import Entités.Participation;
import Entités.User;
import IServices.IEvenement; 



/**
 *
 * @author boussandel
 */
import Services.EvenementServices;
import Services.ParticipationServices;
import Services.UserService;
import java.sql.Date;

public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String str="2015-03-31";  
    Date date=Date.valueOf(str);//converting string into sql date
       
        
        //es.modifierEvenement(e2, 10);
     //  es.supprimerEvenement(15);
        
        
        
        //es.findAll();
        //Participation  p= new Participation(12,02,40,50); 
       // ParticipationServices ps = new ParticipationServices(); 
        //ps.ajouterParticipation(p);
        
        //EvaluationEvenement ee = new EvaluationEvenement(12,20,"dhia","eddine"); 
        //Services.EvaluationServices evs = new EvaluationServices(); 
        //evs.ajouter_Evaluation(ee);
        
         User u = new  User("nom","prenom","email","password","telephone","nationalite","role","login") ; 
           Services.UserService us = new UserService(); 
           us.add(u);
          // us.delete(1);
            //us.findById(1);
            us.getAll();
            us.authentication("dhia", "dhia") ;
            Evenement e = new Evenement(5,"journee","dhdhdh",date , "jhhhhh","hdhdhdh","dhdhdhd","djdjhdhdh","dhdhdhdh",52,10,20) ; 
            Evenement e2 = new Evenement(15,"journee","dhdhdh",date , "jhhhhh","hdhdhdh","dhdhdhd","djdjhdhdh","dhdhdhdh",52,10,9) ; 


        EvenementServices es = new EvenementServices();
        es.ajouterEvenement(e2);
       

                   
        
        
       
       
       
        
       
        
        
      
    }
    
}
