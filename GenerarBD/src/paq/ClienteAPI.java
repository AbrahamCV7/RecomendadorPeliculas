/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ClienteAPI {
    
   
    private static int limite= 100;
    private static int limiteS=999;
    private static  List<String> conPost=new ArrayList();
    
    
    
    public static void conPoster() throws ParseException{
        String movieId = "";
        for(int i=limite; i<limiteS; i++)
            {
                movieId = "tt1570" + i;
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://www.omdbapi.com/").queryParam("i", movieId).queryParam("apikey", "92bba147");
                Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();
                JSONParser parser = new JSONParser();
                JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
                String poster = (String) responseJSON.get("Poster");
                if(poster!=null){
                    if(poster.length()>4){
                        conPost.add(movieId);
                    }
                }
        }
        System.out.println(conPost.size());
    }
    
    
    public static void ObtieneDatos() throws ParseException, ClassNotFoundException 
    {   
       
        List<String> generos = new ArrayList();
        List<String> actores = new ArrayList();
        List<String> directores = new ArrayList();         
        
        for (String movieId : conPost) {
            
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://www.omdbapi.com/").queryParam("i", movieId).queryParam("apikey", "92bba147");

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            JSONParser parser = new JSONParser();
            JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
            
                
                  
                    //Llenar generos 
                    String genero = (String) responseJSON.get("Genre");
                    System.out.println(genero);
                    if(genero!=null){
                      //System.out.println("\n generos " + genero);
                      StringTokenizer tokens = new StringTokenizer(genero,",");
                      while(tokens.hasMoreTokens())
                          generos.add(tokens.nextToken().trim());
                    }
                    
                    //Llenar Actores
                    String cadaActor = (String) responseJSON.get("Actors");
                    if(cadaActor!=null)
                    {
                      //System.out.println("\n Actores " + cadaActor);
                      StringTokenizer tokens = new StringTokenizer(cadaActor,",");
                      while(tokens.hasMoreTokens())
                          actores.add(tokens.nextToken().trim());
                    }
                    
                    //LLenar directores
                    String director = (String) responseJSON.get("Director");
                    if(director!=null){
                        //System.out.println("\n generos " + genero);
                        StringTokenizer tokens = new StringTokenizer(director,",");
                        while(tokens.hasMoreTokens())
                            directores.add(tokens.nextToken().trim());
                    }
                    
                    
                    
                    
            
        }
        
        for (String genero : generos) {
            System.out.println(genero);
        }
      
        //LLenar generos en la base de datos
        Set<String> conjuntoGeneros = new HashSet<>(generos);
                    generos = new ArrayList<>(conjuntoGeneros);
        
        for(String l:generos){
            UtilBD.registraGenros(l,"genero");
        }
          
        
        //LLenar actores en la base de datos
        
        Set<String> conjuntoActores = new HashSet<>(actores);
                    actores = new ArrayList<>(conjuntoActores);
        
        for(String l:actores){
            UtilBD.registraGenros(l,"actor");
        }
        
        
        //LLenar directores en la base de datos
        
        Set<String> conjuntoDirectores = new HashSet<>(directores);
                    directores = new ArrayList<>(conjuntoDirectores);
        
        for(String l:directores){
            UtilBD.registraGenros(l,"director");
        }
        
        
        
                
    }
    
     public static void obtienePeliculas() throws ParseException, ClassNotFoundException 
    {                 
        
        for (String movieId : conPost) {
            
       
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://www.omdbapi.com/").queryParam("i", movieId).queryParam("apikey", "4e916de9");

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            JSONParser parser = new JSONParser();
            JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
            String title = (String) responseJSON.get("Title");
            String poster = (String) responseJSON.get("Poster");
            
                
            

                Pelicula p = new Pelicula();
                p.setTitulo(title.trim());
                p.setIdApi(movieId);
                p.setPelicula(poster);
                
                
                int idP=UtilBD.registraPelicula(p);

                String genero = (String) responseJSON.get("Genre");

                
                if (genero != null) {
                    //System.out.println("\n generos " + genero);
                    StringTokenizer tokens = new StringTokenizer(genero, ",");
                    String temp = "";
                    int idG;
                    while (tokens.hasMoreTokens()) {
                        temp = tokens.nextToken().trim();
                        
                        idG = UtilBD.ObtieId("idGenero", "genero", "Genero", temp);
                        UtilBD.registraRelacion("peliculagenero", idP, idG);
                    
                    }
                    
                }
           
                 String Actors = (String) responseJSON.get("Actors");
           
                if (Actors != null) {
                    
                    StringTokenizer tokens = new StringTokenizer(Actors, ",");
                    String temp = "";
                    int idA;
                    while (tokens.hasMoreTokens()) {
                        temp = tokens.nextToken().trim();
                        
                        idA = UtilBD.ObtieId("idActor", "actor", "Actor", temp);
                        UtilBD.registraRelacion("peliculaactor", idP, idA);
                    }
                    
                }

                String Directores = (String) responseJSON.get("Director");

                if (Directores != null) {
                    //System.out.println("\n generos " + genero);
                    StringTokenizer tokens = new StringTokenizer(Directores, ",");
                    String temp = "";
                    int idD;
                    while (tokens.hasMoreTokens()) {
                        temp = tokens.nextToken().trim();
                       
                        idD = UtilBD.ObtieId("idDirector", "director", "Director", temp);

                        UtilBD.registraRelacion("peliculadirector", idP, idD);
                    }
                    
                }
                       
          }
        
    }
     
        
    
     
    public static void obtieneActores() throws ParseException, ClassNotFoundException 
    {
        List<String> actores = new ArrayList();
        String movieId = "";
        for(int i=limite; i<limiteS; i++)
        {
            movieId = "tt1570" + i;
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://www.omdbapi.com/").queryParam("i", movieId).queryParam("apikey", "92bba147");

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            JSONParser parser = new JSONParser();
            JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));

            String cadaActor = (String) responseJSON.get("Actors");
            if(cadaActor!=null)
            {
              //System.out.println("\n Actores " + cadaActor);
              StringTokenizer tokens = new StringTokenizer(cadaActor,",");
              while(tokens.hasMoreTokens())
                  actores.add(tokens.nextToken().trim());
            }
        
        }
        
        Set<String> conjuntoActores = new HashSet<>(actores);
                    actores = new ArrayList<>(conjuntoActores);
        
        for(String l:actores){
            UtilBD.registraGenros(l,"actor");
        }
        
        
        
       
    }
    
    public static void obtieneDirectores() throws ParseException, ClassNotFoundException 
    {   
        
        List<String> directores = new ArrayList();
       
        String movieId = "";
        for(int i=limite; i<limiteS; i++)
        {
            movieId = "tt1570" + i;
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://www.omdbapi.com/").queryParam("i", movieId).queryParam("apikey", "4e916de9");

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            JSONParser parser = new JSONParser();
            JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));


            String director = (String) responseJSON.get("Director");


            if(director!=null){
              //System.out.println("\n generos " + genero);
              StringTokenizer tokens = new StringTokenizer(director,",");
              while(tokens.hasMoreTokens())
                  directores.add(tokens.nextToken().trim());
            }

          
        
        
        }
        
      
        
        Set<String> conjuntoDirectores = new HashSet<>(directores);
                    directores = new ArrayList<>(conjuntoDirectores);
        
        for(String l:directores){
            UtilBD.registraGenros(l,"director");
        }
        
       
        
        
        
    }
     
     
    
     
    
    public static List<Pelicula> peliculasGenero(String genero) throws ParseException
    {
        List<Pelicula> listaPeliculas = new ArrayList();
        
         String movieId = "";
        for(int i=limite; i<limiteS; i++)
        {   
           // tt3896198"
            movieId = "tt1570" + i;
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://www.omdbapi.com/").queryParam("i", movieId).queryParam("apikey", "4e916de9");

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            JSONParser parser = new JSONParser();
            JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
            
            String generos = (String) responseJSON.get("Genre");
               if (generos != null) {
                
                List<String> items = Arrays.asList(generos.trim().split("\\s*,\\s*"));
                
                    if (items.contains(genero)) {
                       
                        
                       
                    }
                
            }
              
        }

        return listaPeliculas;}
    
  
    
     
    
    public static List<Pelicula> peliculasPorTema(String tema) throws ParseException
    {
        List<Pelicula> listaPeliculas = new ArrayList();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.omdbapi.com/") .queryParam("s", tema) .queryParam("apikey", "92bba147");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
        JSONArray peliculas = (JSONArray) responseJSON.get("Search");
        for (Iterator it = peliculas.iterator(); it.hasNext();) 
        {
            Object pelicula = it.next();
            JSONObject cadaPelicula = (JSONObject) pelicula;
            String titulo = (String) cadaPelicula.get("Title");
            String anio = (String) cadaPelicula.get("Year");
            String poster = (String) cadaPelicula.get("Poster");
           
        }
        return listaPeliculas;
    }
}
