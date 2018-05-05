 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Entities.Evaluation;
import Entities.Evenement;
import Services.EvenementServices;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.gui.FacebookShare;
import java.io.IOException;
import java.util.ArrayList;
import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 *
 * @author boussandel
 */
public class DetailsEvent  {
    Form f;
    float moy = 0 ; 
      static int rateValue ;
      ArrayList<Evaluation> evaluations = new ArrayList<>(); 
      boolean aaaa = false ; 
    

    public DetailsEvent(int id) throws IOException
    {
  
         Services.EvenementServices ps = new EvenementServices();
          Evenement p = ps.searchByName(id);
        f = new Form("Details du:"+p.getNomEvenement(), BoxLayout.y());
        f.getStyle().setBgColor(0xFFFFFF);
        Container c2 = new Container(BoxLayout.y());
       
           
            
            
       Button sb = new ShareButton();
            
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
            


                String clientId = "171373916895282";
                String redirectURI = "http://www.codenameone.com/";
                String clientSecret = "dhia13013";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                //Sets a LoginCallback listener
                fb.setCallback(new LoginCallback() {
                    
    public void loginSuccessful() {
        ConnectionRequest connectionRequest = new ConnectionRequest();
        connectionRequest.setPost(false);
        connectionRequest.setUrl(
                "{server}/auth/facebook/callback?access_token=EAACEdEose0cBAHruXEuxcEjGM6ZCJsYy4pJDbYeiqBwlDsutjCGXfPBZCAo7Y9SmBZBB9MP8EdM1ZB7zyxHQWBEQmdbeQuOc3wZBzx5nRAZBq3IY5u2kPI23ZBTqCuwvNUKdCNRKh0eVGHdKyZATzVKqPzRO7BIlLYl3sFoe7iDGhZAaYfHt3Hi4W7aL5E7tXUsAOJZAwvcjcZBKwZDZD");
        connectionRequest.addResponseListener(event -> {
            Log.p(new String(connectionRequest.getResponseData()));
        });
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    @Override
    public void loginFailed(String errorMessage) {
        Dialog.show("No!", "it did not work!", "sad", null);
    }
});


Button loginWithFacebook = new Button("Fb Auth");
loginWithFacebook.addActionListener(event -> {
    //trigger the login if not already logged in
    if(!fb.isUserLoggedIn()){
        fb.doLogin();
    }else{
        //get the token and now you can query the facebook API
        String token = fb.getAccessToken().getToken();
    }
});

        System.out.println("id:"+id);
             
           
            ImageViewer iv = new ImageViewer();
            iv.setImage(Image.createImage("/" + p.getImage()).scaledHeight(200).scaledWidth(200));

           

            SpanLabel Nomeve = new SpanLabel("Nom Evenement : "+p.getNomEvenement());
  //          SpanLabel date= new SpanLabel("Date : "+p.getDateEvenement().toString());
            SpanLabel typeeve = new SpanLabel("Type : "+p.getType());
            Label Ideve = new Label(String.valueOf(p.getIdEvenement()));
            SpanLabel etat = new SpanLabel("etat : "+String.valueOf(p.getEtat()));
            SpanLabel Dureeeve = new SpanLabel("Taille : "+p.getDuree());
            SpanLabel nbrPlace= new SpanLabel("nombre de place : "+p.getNbrPlaces());
            SpanLabel destination = new SpanLabel("Destination : "+p.getDestination());
            SpanLabel description = new SpanLabel("Destination : "+p.getDescription());
            SpanLabel Prixeve = new SpanLabel( "Prix : "+Float.toString(p.getPrix())+" $" );
            
            
            Ideve.setVisible(false);
       //     Button ToCart = new Button("AddToCart");
            
            // rating 1 celui de la moyenne
             moy = ps.moyenneEvaluation(id);
           // System.out.println("Moyenne="+moy);
            Slider s = new Slider();
            s=createStarRankSlider();
            s.setProgress((int) moy);
            s.setEditable(false);
            // donner un rating : 
            Slider se = new Slider();
            se=createStarRankSlider();
            
        evaluations= ps.getEvaluations(User.getId());
      System.out.println(evaluations);
                 for (int j = 0; j < evaluations.size(); j++){
            if((evaluations.get(j).getIdEvenement()==id)&&((evaluations.get(j).getIdUser()==User.getId())))
             {
                 aaaa = true ; 
               
            }
             }         
 System.out.println("evaluer ? :"+aaaa);
            Button evaluer = new Button("Evaluer"); 
            
            evaluer.addActionListener((ActionListener) (ActionEvent evt) -> {
                if (aaaa==true) {
                    Dialog.show("deja evaluer","Vous aveez deja attribuer une note", "Ok", null);
                }
                else if(aaaa==false) {
                    
                    
                EvenementServices es = new EvenementServices();
                Evaluation eva = new Evaluation(); 
                eva.setIdUser(User.getId());
                eva.setEmailParticipant("email@email.com");
                eva.setNomEvenement(Nomeve.getText());
                eva.setPrenomParticipant("redirectURI");
                eva.setNoteEvenement(1);
                eva.setIdEvenement(id);
                eva.setPrenomParticipant("prenom");
                es.evaluer(eva);
               
                Dialog.show("Evaluation ajouté", "Merci pour votre", "Ok", null);
           try {
                AfficherEvents c=new AfficherEvents();
                c.getF().show();
            } catch (IOException ex) {
                System.out.println("ERREUR");            }
            }
                
        });
            
        Button b8=new Button("partager sur fcb");
         
         
         b8.addActionListener(new ActionListener() {
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
           
  
      
      
         
         
            
            c2.add(iv);
            c2.add(FlowLayout.encloseCenter(s));
            c2.add(FlowLayout.encloseCenter(sb));
            c2.add(Nomeve);
            c2.add(typeeve);
            c2.add(Prixeve);
            c2.add(etat);
            c2.add(destination);
      // c2.add(date);
            c2.add(Dureeeve);
            c2.add(nbrPlace);
            c2.add(FlowLayout.encloseCenter(se));
            c2.add(FlowLayout.encloseCenter(evaluer));
            
            c2.add(b8);
            
            
            
            c2.add(Ideve);
            
           
           
            
            f.add(c2);
          //  f.add(loginWithFacebook);
         // ShareButton.postOnWall("userId", "String message");
             float evaluation=0 ; 
           
           
            
             

               
           
            
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
          
        
    
            
        
        
        

            f.addCommand(retour);
            f.addCommand(Ajouter);
            f.addCommand(mesPartici);
            f.addCommand(Tous);
            
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
    starRank.setMaxValue(6);
    starRank.setProgress(2);
    Font fnt;
       /*  if (Font.isNativeFontSchemeSupported()){
        Dialog.show("Supported?", "Yes it is supported" + Font.getDefaultFont(), "Ok", null);
}*/
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
