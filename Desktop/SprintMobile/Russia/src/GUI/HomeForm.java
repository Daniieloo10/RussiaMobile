/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import Services.EvenementServices;
import Entities.Evenement;
import com.codename1.components.Ads;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author boussandel
 */
public class HomeForm {
 
    Ads a ;
    Form f;
    String path ; 
       Image img ;
    TextField tnom;
    TextField tdestination;
   // TextArea tdescription ; 
    TextField tduree;
     Picker tdate ; 
    Button timage ;
    TextField ttype;
    TextField tiduser;
    TextField tnombreplace;
    TextField tprix;
   // TextField timage;
    TextField tetat;
    
    
    
    Button btnajout,btnaff;

    public HomeForm() {
        f = new Form("Ajout Evenement");
      
        //formulaire//
        
    tnom = new TextField("Nom");
        tdestination = new TextField("Destination");
        ttype = new TextField("type");
        //tiduser = new TextField("idUser");
        tnombreplace = new TextField("nombre de place");
        tprix = new TextField("Prix");
        timage= new Button("image");
       // timage = new TextField("Image");
        tetat = new TextField("Etat");
    //  PickerComponent  tdate = PickerComponent.createDate(new Date()).label("Date");
    tdate = new Picker();
    tdate.setType(Display.PICKER_TYPE_DATE);
        
        TextComponent tdescription = new TextComponent().label("Description").multiline(true);

        tduree = new TextField("Duree"); 
            // boutons//
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");

   
        Label dateLabel =new Label("Date");
        Picker dateTime1 = new Picker();
        dateTime1.setType(Display.PICKER_TYPE_DATE);
        Container DateContainer =new Container(new BoxLayout(BoxLayout.X_AXIS));
        DateContainer.add(dateLabel);
       
       
       
        
       
       
        f.add(tnom);
        f.add(tdestination);
        f.add(tdescription); 
        f.add(tduree);
        f.add(ttype);
      f.add(dateTime1) ;
        f.add(tprix);
        f.add(timage); 
        f.add(tetat);
        f.add(tnombreplace);
       
       // f.add(tiduser); 
        
        
        f.add(btnajout);
        f.add(btnaff);
        
        timage.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                   if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path);
                        Image img = null;
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                            System.out.println(img);
                            
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
                    
        });

                
        
        
        
        btnajout.addActionListener((ActionEvent e) -> {
            EvenementServices es = new EvenementServices(); 
             Evenement event = new Evenement();
             event.setNomEvenement(tnom.getText());
             event.setDestination(tdestination.getText());
             event.setDescription(tdescription.getText());
             event.setDuree(tduree.getText());
             event.setType(ttype.getText());
            /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             event.setDateEvenement(tdate.getDate());
             System.out.println(tdate.getDate());
*/
             //event.setDateEvenement(formatter);
           
         //  String  stringdate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateTime1.getDate());
              String  stringdate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateTime1.getDate());
        System.out.println("Date ev:"+stringdate1);
        event.setDateEvenement(stringdate1);
             
             event.setPrix(Double.parseDouble(tprix.getText()));
             event.setImage(path);
             event.setEtat(tetat.getText());
             event.setNbrPlaces(Integer.parseInt(tnombreplace.getText()));
             event.setId_user(User.getId()); // change to the id of current session because now its satic number .. ! 
             es.ajoutTask(event);
            Dialog.show("Succées", "Votre Evenement a ete ajoute avec succées...", "OK",null);
            
          

            

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

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
