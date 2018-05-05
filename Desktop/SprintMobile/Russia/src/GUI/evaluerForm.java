/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evaluation;
import Entities.Evenement;
import Services.EvenementServices;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;

/**
 *
 * @author boussandel
 */
public class evaluerForm {
    Form f;
    
    TextField tiduser;
    TextField tidevenement;
    TextField tnote ; 
    
   
    
    
    
    Button btnajout,btnaff;

    public evaluerForm() {
        f = new Form("Evaluer");
        
        //formulaire//
        
        tiduser = new TextField("iduser");
        tidevenement = new TextField("ideve");
        tnote = new TextField("note");
        
       
        
        btnajout = new Button("Evaluer");
        btnaff=new Button("Affichage");
        f.add(tidevenement);
        f.add(tiduser);
        f.add(tnote);
        
       
        
        
        f.add(btnajout);
        f.add(btnaff);
        
        btnajout.addActionListener((e) -> {
            EvenementServices es = new EvenementServices(); 
             Evaluation ev = new Evaluation(); 
             ev.setEmailParticipant("email");
             ev.setPrenomParticipant("nomparticipant");
             ev.setNomParticipant("nnom");
             ev.setNomEvenement("Moscow");
             ev.setIdUser(2);
             ev.setIdEvenement(10);
             ev.setNoteEvenement(5);
             System.out.println(ev);
              es.evaluer(ev);
            

        });
        btnaff.addActionListener((e)->{
            AfficherEvents a;
            try {
                a = new AfficherEvents();
                a.getF().show();
            } catch (IOException ex) {
               
            }
            
                   
         });
    }
         
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTiduser() {
        return tiduser;
    }

    public TextField getTidevenement() {
        return tidevenement;
    }

    public TextField getTnote() {
        return tnote;
    }


}
