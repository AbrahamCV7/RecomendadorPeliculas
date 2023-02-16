/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;

public class Pelicula
{
    private int idP;
    private String titulo;
    private String idApi;
    private String pelicula;

    public Pelicula() {
    }

    public Pelicula(int idP, String titulo, String idApi, String pelicula) {
        this.idP = idP;
        this.titulo = titulo;
        this.idApi = idApi;
        this.pelicula = pelicula;
    }

    
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdApi() {
        return idApi;
    }

    public void setIdApi(String idApi) {
        this.idApi = idApi;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

   

}
