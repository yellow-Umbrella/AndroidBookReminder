package br.com.renandias.bookshelf.models;

import android.graphics.Bitmap;

/**
 * Created by Renan on 10/12/2016.
 */

/**
 * Classe model de um Livro(Book) com id, nome, Quantidade de páginas e uma imagem.
 */
public class Book {

    //Atributos
    private Long id;
    private String name;
    private Integer pages;
    private Bitmap bitmapImage;

    //Constructors
    public Book() {}

    public Book(String name, Integer pages, Bitmap bitmapImage) {
        this.name = name;
        this.pages = pages;
        this.bitmapImage = bitmapImage;
    }

    public Book(Long id, String name, Integer pages) {
        this.id = id;
        this.name = name;
        this.pages = pages;
    }

    public Book(Long id, String name, Integer pages, Bitmap bitmapImage) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.bitmapImage = bitmapImage;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }
}
