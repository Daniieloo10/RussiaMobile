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
import com.codename1.components.ShareButton;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.gui.FacebookShare;
import com.sun.prism.Texture;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author Slim
 */
public class AfficherEvents {

    Form f;
    ImageViewer Pdp;
    float moy = 0 ; 
     EvenementServices ps = new EvenementServices();
     
    public AfficherEvents() throws IOException {
    
        f = new Form("Welcome to Evenements", BoxLayout.y());
        f.getStyle().setBgColor(0xFFFFFF);
      
       
        ArrayList<Evenement> lis = ps.getList2();
       // System.out.println(lis);

        Container c2 = new Container(BoxLayout.y());
        

        for (int i = 0; i < lis.size(); i++) {
            ArrayList<Participation> mesparticipation = new ArrayList<Participation>(); 
            mesparticipation= ps.getparticipation(User.getId());
         //   System.out.println(mesparticipation);
             boolean participe = false ;
            
            Container c4 = new Container(BoxLayout.y());
            Container c1 = new Container(BoxLayout.x());
            c1.setHeight(20000);
            Container c3 = new Container(BoxLayout.y());
            
            
            
           // c3.setHeight(5000);
            c1.getStyle().setBorder(Border.createLineBorder(2));
            c1.getStyle().setMargin(1, 1, 1, 1);
            c1.getStyle().setPadding(0, 0,0, 0);

            c1.getStyle().setBgColor(0xC40C0C);
            ImageViewer iv = new ImageViewer();
           // iv.setPreferredSize(new Dimension(100,100));
            iv.setImage(Image.createImage("/" + lis.get(i).getImage()).scaledHeight(200).scaledWidth(200));
             ShareButton sb = new ShareButton();
    sb.setIcon(Image.createImage("/fb.png"));
    sb.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
               //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  Dialog.show("Bonjour", "Partagé dans votre profil", "Ok","Cancel");

             
           FacebookShare fb = new FacebookShare("EAACEdEose0cBADaFLZCAbsGVYR1lBgQu8OpZCINLwoZCi4PADr6s4YEP6oQOqw2Uswb126nohapF5M62jyOiIoAMUBT7U4mW2Vi5NqjGjPBVyHhMsCEgjBkSUmPHokShjZBZAbL1DX3KwJFpqmzzgRm1WM8lehZCc0VpnGHNrSeUZBRpuhSTSjCHklTsLDc5MDu1XtncM6VHgZDZD");
                 try {
                     fb.share("Russia 2018 Project .. Be ready .. !");
                 } catch (IOException ex) {
                 }
           
           
             
             }
         });
            

            Label Prix = new Label( Float.toString(lis.get(i).getPrix())+"DT" );

            Label NomEvent = new Label(lis.get(i).getNomEvenement());
           
            Label Id = new Label(String.valueOf(lis.get(i).getIdEvenement()));
            Label etat = new Label(String.valueOf(lis.get(i).getDateEvenement()));
            etat.setAutoSizeMode(true);
             Prix.setAutoSizeMode(true);
              NomEvent.setAutoSizeMode(true);
            
            
            Id.setVisible(false);
           // Container cBtoCart = new Container(new FlowLayout(Component.RIGHT,Component.TOP));
          
           
           
           // participer.setWidth(50);
           for (int j = 0; j < mesparticipation.size(); j++)
            {
                if((mesparticipation.get(j).getIdevenement()==Integer.parseInt(Id.getText()))&&((mesparticipation.get(j).getIduser()==User.getId())))
                {
                    participe=true;
                }
            }
          System.out.println(mesparticipation);
             Button participer = new Button("Participer");
          
           if(participe==true){
               participer.setText("Annuler");
            }
            Button btn = new Button("Details");
           //  btn.getSameHeight();
            moy = ps.moyenneEvaluation(lis.get(i).getIdEvenement());
          //  System.out.println("Moyenne="+moy);
            Slider s = new Slider();
            s=createStarRankSlider();
            s.setProgress((int) moy);
            s.setEditable(false);
            
            
            //s.setWidth(50);
            // rating
           
            c4.add(iv); 
            c4.add(FlowLayout.encloseCenter(s));
            c4.add(FlowLayout.encloseCenter(sb));
            System.out.println("User"+User.getId());
            c1.add(c4);
            

            c1.add(c3);
            //c1.add(c4);
            //c1.add(participer) ;
            c3.add(NomEvent);
            c3.add(etat);
            
            c3.add(Prix);
            c3.add(participer);
            c3.add(btn);
           // c3.add(btn);
          // System.out.println(ps.searchByName(28));
            participer.addActionListener((e) -> {
                if(participer.getText().toLowerCase().equals("annuler"))
                {
                    ps.annulerParticipation(Integer.valueOf(Id.getText()));
                    Dialog.show("Evenement supprimé","Succées","ok",null);
                     AfficherEvents h;
                    try {
                        h= new AfficherEvents();
                        h.getF().show();
                    } catch (IOException ex) {
                        
                    }
            
                }
                else {
         Evenement p = ps.searchByName(Integer.valueOf(Id.getText()));
                Evenement liiste = ps.searchByName(19); 
              //  System.out.println(p);
                 
                
                
                
        
         if(p.getNbrPlaces()>1){
                Participation participation = new Participation (); 
                participation.setIduser(User.getId()); // change this to p.getId_user() because its a static user now .. ! 
                participation.setIdevenement(p.getIdEvenement());
                EvenementServices es = new EvenementServices(); 
                es.participer(participation);
                es.decremente(Integer.parseInt(Id.getText()));
                
                Dialog.show("Succées","participation ajouté","ok",null);
                
                     AfficherEvents h;
                    try {
                        h= new AfficherEvents();
                        h.getF().show();
                    } catch (IOException ex) {
                        
                    }
         }
         else if(p.getNbrPlaces()==1){
             Participation participation = new Participation (); 
                participation.setIduser(User.getId()); // change this to p.getId_user() because its a static user now .. ! 
                participation.setIdevenement(p.getIdEvenement());
                EvenementServices es = new EvenementServices(); 
                es.participer(participation);
                es.decrementation2(Integer.parseInt(Id.getText()));
                
                Dialog.show("Succées","participation ajouté","ok",null);
               
                     AfficherEvents h;
                    try {
                        h= new AfficherEvents();
                        h.getF().show();
                    } catch (IOException ex) {
                        
                    }
                
            }
         else {
             Dialog.show("Echec","évenement reservé", "Ok",null);
         }
                }
         
            });
            
             btn.addActionListener((e) -> {
        DetailsEvent a;
            try {
                a = new DetailsEvent(Integer.valueOf(Id.getText()));
                a.getF().show();
            } catch (IOException ex) {
               
            } 
            });

            c3.add(Id);
          //  c3.setPreferredSize(new Dimension(400, 150));
            

           c2.add(c1);
           // c2.add(participer) ;

        }
   
        Toolbar tb=f.getToolbar();
       
            tb.addMaterialCommandToOverflowMenu("Ajouter evenement",FontImage.MATERIAL_SHOPPING_BASKET, (e)->{
                HomeForm c=new HomeForm();
                c.getF().show();
   
        //   tb.openSideMenu();
        });
              
            Command retour = new Command("retour"){
           @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                AfficherEvents c=new AfficherEvents();
                c.getF().show();
            } catch (IOException ex) {
                System.out.println("ERREUR");            }
            }
            
            };
             Command Ajouter = new Command("Ajouter une evenement"){
           @Override
            public void actionPerformed(ActionEvent e)
            {
                HomeForm c=new HomeForm();
                c.getF().show();
            }
            
            };
            
               Command mesPartici = new Command("mesParticipation"){
           @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                MesParticipation c=new MesParticipation();
                c.getF().show();
            } catch (IOException ex) {
                System.out.println("ERREUR");            }
            }};
               
                Command Tous = new Command("Tous les evenements"){
           @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                AfficherEvents c=new AfficherEvents();
                c.getF().show();
            } catch (IOException ex) {
                System.out.println("ERREUR");            }
            }};
           
        f.add(c2);
        

        
            f.addCommand(retour);
            f.addCommand(Ajouter);
            f.addCommand(mesPartici);
            f.addCommand(Tous);
        
        //f.getStyle().setBgColor(0xC40C0C);

        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
     private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

    public Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    starRank.setProgress(2);
    Font fnt;
       

      fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
  
      Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}

}
