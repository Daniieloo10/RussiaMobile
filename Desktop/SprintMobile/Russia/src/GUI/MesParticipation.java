/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Entities.Evenement;
import Entities.Participation;
import Services.EvenementServices;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author boussandel
 */
public class MesParticipation {
    Form f;
    ImageViewer Pdp;
     private Resources theme;

    public MesParticipation() throws IOException {
    
    //  theme = UIManager.initFirstTheme("/theme_1");
     
     
        f = new Form("Ou je participe", BoxLayout.y());
        f.getStyle().setBgColor(0xFFFFFF);
      
        
        EvenementServices ps = new EvenementServices();
        ArrayList<Evenement> lis = new ArrayList<Evenement>() ;  // because of static user 
        
        ArrayList<Participation> listEvents = ps.getparticipation(User.getId()) ; // because of static user 

        for(Participation p : listEvents)
                {
                    
                    Evenement evv  = ps.searchByName(p.getIdevenement());
                    System.out.println(evv);
                    lis.add(evv);
                    
                }
       // System.out.println(lis);

        Container c2 = new Container(BoxLayout.y());
        

        for (int i = 0; i < lis.size(); i++) {
              
            Container c4 = new Container(BoxLayout.y());
            Container c1 = new Container(BoxLayout.x());
            c1.setHeight(20000);
            Container c3 = new Container(BoxLayout.y());
            Container c5 = new Container(BoxLayout.y()); 
            
            
            
           // c3.setHeight(5000);
            c1.getStyle().setBorder(Border.createLineBorder(2));
            c1.getStyle().setMargin(1, 1, 1, 1);
            c1.getStyle().setPadding(0, 0,0, 0);

            c1.getStyle().setBgColor(0xC40C0C);
            ImageViewer iv = new ImageViewer();
           // iv.setPreferredSize(new Dimension(100,100));
            iv.setImage(Image.createImage("/" + lis.get(i).getImage()).scaledHeight(200).scaledWidth(200));

            Label Prix = new Label( Float.toString(lis.get(i).getPrix())+"DT" );

            Label NomEvent = new Label(lis.get(i).getNomEvenement());
           
            Label Id = new Label(String.valueOf(lis.get(i).getIdEvenement()));
            Label etat = new Label(String.valueOf(lis.get(i).getEtat()));
            
            Id.setVisible(false);
           // Container cBtoCart = new Container(new FlowLayout(Component.RIGHT,Component.TOP));
            Button participer = new Button("Participer");
           // participer.setWidth(50);
            
           
           //  btn.getSameHeight();
          Font materialFont = FontImage.getMaterialDesignFont();
            Button myButton = new Button("Annuler");
            
            /*myButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_BLOCK, myButton.getUnselectedStyle())); */ 
         // myButton.setSize(new Dimension(20, 20));
            


    myButton.addActionListener((e) -> {
          int ID =  Integer.valueOf(Id.getText());
          
         // System.out.println(ps.moyenneEvaluation(18));
           Dialog.show("Annuler", "Voulez vous vraiment annulé votre Participation", "OK","annuler");
            
          
System.out.println(ID);
          ps.annulerParticipation(ID);
           MesParticipation h;
                    try {
                        h= new MesParticipation();
                        h.getF().show();
                    } catch (IOException ex) {
                        
                    }
          
            

        });
    
   
            
            
            
            c4.add(iv); 
            
            c1.add(c4);
            c5.add(myButton);
            Button b2=new Button("Details");
            myButton.setPreferredSize(new Dimension(50,50));
            myButton.setAutoSizeMode(true);
            b2.setAutoSizeMode(true);
             b2.addActionListener((e) -> {
          int ID =  Integer.valueOf(Id.getText());
          
         // System.out.println(ps.moyenneEvaluation(18));
         //  Dialog.show("Annuler", "Voulez vous vraiment annulé votre Participation", "OK","annuler");
            
          
DetailsEvent a;
            try {
                a = new DetailsEvent(Integer.valueOf(Id.getText()));
                a.getF().show();
            } catch (IOException ex) {
               
            } 
          
            

        });;
            
            
            c5.add(b2);
                    
            
           
            c1.add(c3);
            //c1.add(c4);
            //c1.add(participer) ;
            c3.add(NomEvent);
            c3.add(etat);
           
            c3.add(Prix);
            c3.add(c5);
           // c3.add(btn);
            participer.addActionListener((e) -> {
     
         
            });
            
             

            c3.add(Id);
            //c3.setPreferredSize(new Dimension(400, 150));
            

           c2.add(c1);
           // c2.add(participer) ;

        }
   
        
        f.add(c2);
        
        //f.getStyle().setBgColor(0xC40C0C);

        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
