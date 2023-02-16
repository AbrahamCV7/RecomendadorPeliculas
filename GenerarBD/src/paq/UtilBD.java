/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilBD 
{
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reomenedaciones";
    private static final String USER = "root";
    private static final String PASS = "root";

    static Connection conn;
    private static Statement stmt;

    public static void obtieneConexion() 
    {
        try 
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("Conectandose a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void registraGenros(String  p, String t) throws ClassNotFoundException
    {  
       
        try 
        {
           // System.out.println("Para registrar " + p);
            Class.forName(JDBC_DRIVER);
            //System.out.println("Conectandose a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
            String query = "INSERT INTO "+t+" VALUES (?,?)";
            PreparedStatement prep = conn.prepareStatement(query);  
             prep.setString(1,null); 
            prep.setString(2,p); 
            prep.executeUpdate();
            conn.close();
        }
        catch(SQLException ex) 
        {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public static int registraPelicula(Pelicula  p) throws ClassNotFoundException
    {   
        int id=-1;
        try 
        {
           // System.out.println("Para registrar " + p);
            Class.forName(JDBC_DRIVER);
            //System.out.println("Conectandose a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
            String query = "INSERT INTO pelicula VALUES (?,?,?,?)";
            PreparedStatement prep = conn.prepareStatement(query);  
            prep.setString(1,null); 
            prep.setString(2,p.getTitulo());
            prep.setString(3,p.getIdApi());
            prep.setString(4,p.getPelicula());
            
            prep.executeUpdate();
            
                 
        }
        catch(SQLException ex) 
        {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
           
        try {
            
            Statement st;
            st = conn.createStatement();
            String query = "SELECT idImdb FROM pelicula where Titulo=\""+p.getTitulo()+"\"";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) 
                {
                    query=rs.getString("idImdb");
                  
                }
             
           
            if(query!=null){
            
                id=Integer.parseInt(query); 
            }
           
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }

         
        
        
        return id;
    }
    
     
     public static int ObtieId(String c, String t,String n, String i) throws ClassNotFoundException
    {   
        int id=-1;
                 
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);  
            Statement st;
            st = conn.createStatement();
                          // SELECT idImdb FROM pelicula where Titulo='"+p.getTitulo()+" '";
            
            String query = "SELECT "+c+" FROM "+t+" where Nombre_"+n+"=\""+i+"\"";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
           
            while (rs.next()) 
                {
                    query=rs.getString(c);
                    System.out.println(query);
                }
             
            if(query!=null){
            
                id=Integer.parseInt(query); 
            }
                        
            
           conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }

         
        
        
        return id;
    }
     
     
      public static void registraRelacion(String  t,int id1, int id2) throws ClassNotFoundException
    {
        try 
        {
           // System.out.println("Para registrar " + p);
            Class.forName(JDBC_DRIVER);
            //System.out.println("Conectandose a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
            String query = "INSERT INTO "+t+" VALUES (?,?,?)";
            PreparedStatement prep = conn.prepareStatement(query);  
             prep.setString(1,null); 
             prep.setInt(2,id1);
             prep.setInt(3,id2);
             
            
            prep.executeUpdate();
            conn.close();
        }
        catch(SQLException ex) 
        {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
      
      
    
    /*
    public static List<Usuario> cargaListaUsuarios() throws ClassNotFoundException
    {
        List<Usuario> lista = new ArrayList<Usuario>(); 
         try 
        {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) 
            {
                System.out.println("UtilBD ====> Conectado a la base de datos");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM USUARIOS;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) 
                {
                    String usuario = rs.getString("usuario");
                    String pass = rs.getString("pass");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    String genero = rs.getString("genero");
                    String correo = rs.getString("correo");
                    Usuario usu = new Usuario(usuario, pass, nombre, edad, genero,correo);
                    lista.add(usu);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }*/
      
      public static List<Genero> cargaGenero() throws ClassNotFoundException
    {
        List<Genero> lista = new ArrayList<Genero>(); 
         try 
        {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) 
            {
                System.out.println("UtilBD ====> Conectado a la base de datos");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM genero;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) 
                {
                    String idG = rs.getString("idGenero");
                    String Nombre = rs.getString("Nombre_Genero");
                    Genero ge= new Genero(Integer.parseInt(idG) , Nombre);
                    
                   lista.add(ge);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
      
      
      
      public static List<Actor> cargaActores() throws ClassNotFoundException
    {
        List<Actor> lista = new ArrayList<Actor>(); 
         try 
        {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) 
            {
                System.out.println("UtilBD ====> Conectado a la base de datos");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM actor;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) 
                {
                    String idA = rs.getString("idActor");
                    String Nombre = rs.getString("Nombre_Actor");
                    Actor act= new Actor(Integer.parseInt(idA) , Nombre);
                    
                   lista.add(act);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
      
}
