/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvm.daw.uf3;

/**
 *
 * @author manuc
 */
public class Music {
    private int id;
    private String name;
    private float price;
    private String author;
    private String album;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
 
    public Music(){
        this.id = -1;
        this.name = "";
        this.price = 0f;
        this.author = "";
        this.album = "";
    }
 
    public Music(int id, String name, float price, String author, String album, int rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.album = album;
        this.rating = rating;
    }
}
