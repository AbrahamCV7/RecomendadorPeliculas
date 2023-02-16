/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.simple.parser.ParseException;

public class Principal {
    
    public static void main(String args[]) throws ParseException, ClassNotFoundException
    {
         ClienteAPI.conPoster();
        ClienteAPI.ObtieneDatos();
        //ClienteAPI.obtieneDirectores();
        //ClienteAPI.obtieneActores();
       //ClienteAPI.peliculasGenero("Drama");
       
         ClienteAPI.obtienePeliculas();
       
    }
}
