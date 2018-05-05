/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dambo
 */
public class Login {
    Form f;
            public Login(){
            Container c2 = new Container(BoxLayout.y());
                        Container c3 = new Container(BoxLayout.y());

            Container c1 =new Container(BoxLayout.x());
        f = new Form("Login", BoxLayout.y());
        f.getStyle().setBgColor(0xFFFFFF);
        Label login=new Label("Pseudo :");
        Label nom=new Label("Password : ");
        
                TextField n= new TextField();
                TextField pn = new TextField();
               
                c2.add(login);
                c2.add(nom);
              
                c3.add(n);
                c3.add(pn);
              
                c1.add(c2);
                c1.add(c3);
                
                




        
       
           
       
        
   
                    f.add(c1);
                    Button inscri=new Button("Login");
                    inscri.addActionListener((ActionEvent p)->{   ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/login.php?login="+n.getText()+"&pass="+pn.getText();
        con.setUrl(Url);

        System.out.println("tt");

         con.addResponseListener(e->{
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        System.out.println(obj);
                       
                        float id = Float.parseFloat(obj.get("id").toString());

                        User.setUsername(obj.get("username").toString());
                        User.setPass(obj.get("password").toString());
                        User.setId((int)Float.parseFloat(obj.get("id").toString()));
                        
                        
                       

                       

                    }
                } catch (IOException ex) {
                }

            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                        System.out.println(User.getUsername());
                     
                        if(User.getId()==0){
                            Dialog.show("Incorrect", "Username Or Password are Incorrect", "OK","Annulé");
                            
                        }
                        else{
                            AfficherEvents e;
                        try {
                            e = new AfficherEvents();
                             e.getF().show();
                        } catch (IOException ex) {
                        }
                           
              
                    }});
                 
          
                    f.add(inscri);
                  
                    

        
        
        //f.getStyle().setBgColor(0xC40C0C);

        //f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    }
    
