package com.softwear.webapp5.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String img_route;
    public Product() { }
    public Product(String name, String img_route) {
        super();
        this.name = name;
        this.img_route = img_route;
    }
}