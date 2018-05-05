/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evaluation;
import Entities.Evenement;
import Entities.Participation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;





/**
 *
 * @author Slim
 */
public class EvenementServices {

    public ArrayList<Evenement> getList2() {
        ArrayList<Evenement> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RussiaMobile/select.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                events.put("evenement", events.remove("root"));
                  // System.out.println("events: " + events);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("evenement");

                    for (Map<String, Object> obj : list) {
                           
                        Evenement event = new Evenement();
                        float id = Float.parseFloat(obj.get("idEvenement").toString());
                        event.setIdEvenement((int) id);
                        event.setEtat(obj.get("etat").toString());
                        event.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        event.setImage((String) obj.get("image"));
                        event.setNomEvenement((String) obj.get("nomEvenement"));
                        event.setDateEvenement(((String)obj.get("dateEvenement")));
                         
                        listEvents.add(event);
                        System.out.println(listEvents);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    
    public Evenement searchByName(int ideve) {
        ArrayList<Evenement> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RussiaMobile/getEvent.php?idEvenement="+ideve);
         System.out.println("L'ID =====> "+ideve);

        Evenement event = new Evenement();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    //listTasks = getListTask(new String(con.getResponseData()));
                    
                    
                    
                    
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("evenement", products.remove("root"));
                    //   System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("evenement");
                    

                    for (Map<String, Object> obj : list)
                    {
                        
                        
                        float id = Float.parseFloat(obj.get("idEvenement").toString());
                        event.setIdEvenement((int) id);
                        event.setEtat(obj.get("etat").toString());
                        event.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        event.setImage((String) obj.get("image"));
                        event.setNomEvenement((String) obj.get("nomEvenement"));
                        event.setDescription((String) obj.get("description"));
                        event.setDuree((String) obj.get("duree"));
                        event.setDestination((String) obj.get("destination"));
                        event.setType((String) obj.get("type"));
                        event.setNbrPlaces(Integer.parseInt(obj.get("nombrePlace").toString()));
                       // String string =obj.get("dateEvenement").toString();
                    /*    DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
                        Date date;
                        try {
                            date = format.parse(string);
                             event.setDateEvenement(date);
                        } catch (ParseException ex) {
                            
                        }*/
                  SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
                   String dateDb= ((String) obj.get("dateEvenement"));
                     
                      
                    event.setDateEvenement(dateDb);
                     System.out.println("Date services:"+dateDb);

                    
                        
                      
                        listEvents.add(event);
                    

                    }
                } catch (IOException ex) {
                    
                }
              

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return event;
    }
    
    public void ajoutTask(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/insertEv.php?nomEvenement="+ta.getNomEvenement()+ "&description="+ ta.getDescription()+"&dateEvenement="+ta.getDateEvenement()+"&duree="+ta.getDuree()+"&destination="+ta.getDestination()+"&type="+ta.getType()+"&etat="+ta.getEtat()+"&nombrePlace="+ta.getNbrPlaces()+"&prix="+ta.getPrix()+"&image="+ta.getImage()+"&idUser="+ta.getId_user();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener(e -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void participer(Participation p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/participer.php?iduser="+p.getIduser()+ "&idevenement="+ p.getIdevenement();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener(e -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void evaluer(Evaluation ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/evaluer.php?emailParticipant="+ev.getEmailParticipant()+ "&nomParticipant="+ev.getNomParticipant()+"&nomEvenement="+ev.getNomEvenement()+"&prenomParticipant="+ev.getPrenomParticipant()+"&noteEvenement="+ev.getNoteEvenement()+"&iduser="+ev.getNoteEvenement()+"&idevenement="+ev.getIdEvenement();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener(e -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void decremente(int ideve) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/decremnteNbrPlace.php?idEvenement="+ideve;
        con.setUrl(Url);

        System.out.println("decremnte succes");

        con.addResponseListener(e -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void decrementation2(int ideve) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/decrementation2.php?idEvenement="+ideve;
        con.setUrl(Url);

        System.out.println("decremnte succes");

        con.addResponseListener(e -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

  public ArrayList<Participation> getparticipation(int iduser) {
        ArrayList<Participation> listparticipation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RussiaMobile/mesparticipation2.php?iduser="+iduser);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                events.put("participation", events.remove("root"));
                  // System.out.println("events: " + events);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("participation");

                    for (Map<String, Object> obj : list) {

                        Participation partici = new Participation();
                        int id = Integer.parseInt(obj.get("idParticipation").toString());
                        //Date date = obj.get("dateEvenement");
                        partici.setIdparticipation(id);
                        partici.setIduser(Integer.parseInt(obj.get("iduser").toString()));
                        partici.setIdevenement(Integer.parseInt(obj.get("idevenement").toString()));
                       
                        listparticipation.add(partici);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listparticipation;
    }
  
    public void annulerParticipation(int ideve) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RussiaMobile/delete.php?idEvenement="+ideve;
        con.setUrl(Url);

        System.out.println("suppression");

        con.addResponseListener(e -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public  float  moyenneEvaluation(int ideve) {
        float moyenne=0 ; 
         ArrayList<Float> notes;
        notes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RussiaMobile/avg.php?idevenement="+ideve);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                events.put("notes", events.remove("root"));
                  // System.out.println("events: " + events);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("notes");

                    for (Map<String, Object> obj : list) {

                        Evenement event = new Evenement();
                        float moy = Float.parseFloat(obj.get("noteEvenement").toString());
                        
                        notes.add(moy);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        for(int i=0;i<notes.size();i++) {
          
            moyenne+=notes.get(i);
        }
        return moyenne/notes.size();
    }
     
     
     public ArrayList<Evaluation> getEvaluations(int iduser) {
        ArrayList<Evaluation> listevaluation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RussiaMobile/evaluation2.php?iduser="+iduser);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                events.put("evaluation", events.remove("root"));
                  // System.out.println("events: " + events);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("evaluation");

                    for (Map<String, Object> obj : list) {

                       Evaluation partici = new Evaluation();
                        int id = Integer.parseInt(obj.get("evaluationId").toString());
                        //Date date = obj.get("dateEvenement");
                        partici.setEvaluationId(id);
                        partici.setIdUser(Integer.parseInt(obj.get("iduser").toString()));
                        partici.setIdEvenement(Integer.parseInt(obj.get("idevenement").toString()));
                        partici.setNoteEvenement(Integer.parseInt(obj.get("noteEvenement").toString()));
                        
                       
                        listevaluation.add(partici);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listevaluation;
    }
    
}

