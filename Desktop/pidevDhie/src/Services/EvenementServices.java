package Services;


import DataBase.DB;
import Entités.Evenement;
import IServices.IEvenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvenementServices implements IEvenement {
    private Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
  
    public EvenementServices() {
        connection = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterEvenement(Evenement ev) {
 String req = "insert into evenement (nomEvenement,description,dateEvenement,duree,destination,type,etat,image,nombrePlace,prix,idUser) values (?,?,?,?,?,?,?,?,?,?,?)";
 
        try {
            ps = connection.prepareStatement(req);
              
               
               ps.setString(1,ev.getNomEvenement());
               ps.setString(2,ev.getDescription());
               ps.setDate(3,  ev.getDateEvenement());
               ps.setString(4,ev.getDuree());
               ps.setString(5,ev.getDestination());
               
               ps.setString(6,ev.getType());
               
               ps.setString(7,ev.getEtat());
               ps.setString(8,ev.getImage());
               ps.setInt(9,ev.getNbrPlaces());
               ps.setDouble(10,ev.getPrix());
               ps.setInt(11,ev.getId_user());
               System.out.println("evenement ajouté");
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
  

    @Override
    public void supprimerEvenement(Integer idEvenement) {
       
        String req = "delete from evenement where idEvenement =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,idEvenement);
            ps.executeUpdate();
            System.out.println("evenement supprimé");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void modifierEvenement(Evenement ev,int id) {
     try {
            String req;
            req = "UPDATE evenement SET idEvenement=?,nomEvenement=?,description=?,dateEvenement=?,duree=?,destination=?,type=?,etat=?,image=?,nombrePlace=?,prix=?,idUser=? WHERE idEvenement=?";
      ps = connection.prepareStatement(req);
               ps.setInt(1, ev.getIdEvenement());
               ps.setString(2,ev.getNomEvenement());
               ps.setString(3,ev.getDescription());
               ps.setDate(4,  ev.getDateEvenement());
               ps.setString(5,ev.getDuree());
               ps.setString(6,ev.getDestination());
               
               ps.setString(7,ev.getType());
               
               ps.setString(8,ev.getEtat());
               ps.setString(9,ev.getImage());
               ps.setInt(10,ev.getNbrPlaces());
               ps.setDouble(11,ev.getPrix());
               ps.setInt(12,ev.getId_user());
               ps.setInt(13, id);
               System.out.println("evenement modifier");
     
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println(ps);
  Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
  
    
 

    @Override
    public Evenement rechercherById(Integer r) {
        String req = "select * from evenement where idEvenement = ?";
        Evenement evenement = null ; 
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                evenement = new Evenement(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getFloat(11),resultSet.getInt(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evenement ;
        
    }
    
    public List<Evenement> findAll()
        {
        List<Evenement> listeEvenement = new ArrayList<>();

        String requete = "select idEvenement,"
                + "nomEvenement,"
                +"description,"
                +"dateEvenement,"
                + "duree,"
                +"destination,"
                + "type,"
                
                
                +"etat,"
                + "image,"
                +"nombrePlace,"
                +"prix,"
                +"idUser from evenement";
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Evenement f = new Evenement();
                

               f.setNomEvenement(r.getString(2));
               f.setDescription(r.getString(3));
               f.setDateEvenement(r.getDate(4));
               f.setDuree(r.getString(5));
               f.setDestination(r.getString(6));
               f.setType(r.getString(7));
               
               f.setEtat(r.getString(8));
               
               f.setNbrPlaces(r.getInt(10));
               f.setPrix(r.getFloat(11));
               f.setId_user(r.getInt(12));
               
                     
                
               
                listeEvenement.add(f);
            }
            return listeEvenement;
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
        
    }
    
    public Evenement recher(String nom) {
        boolean exist = false;
       Evenement u = null;
      Evenement uc = null;
        String req = "SELECT * FROM evenement WHERE nomEvenement=?";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(req);
            ps.setString(1, nom);
       
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                u = new Evenement(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getFloat(11),resultSet.getInt(12));
            }
            exist = true;
            System.out.println("Evenement trouvé");
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    
    
    
    

    
    
    
    
}