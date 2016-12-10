package br.com.renandias.bookshelf.models;

/**
 * Created by Renan on 10/12/2016.
 */

public class Book {

    private Long id;
    private String name;
    private Integer pages;

    //Constructors
    public Book() {}

    public Book(String name, Integer pages) {
        this.name = name;
        this.pages = pages;
    }

    public Book(Long id, String name, Integer pages) {
        this.id = id;
        this.name = name;
        this.pages = pages;
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

    @Override
    public String toString() {
        return id + " " + name + " " + pages;
    }

}
