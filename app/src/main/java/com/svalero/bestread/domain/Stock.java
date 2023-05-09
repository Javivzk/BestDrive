package com.svalero.bestread.domain;

import androidx.annotation.NonNull;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Data
@Entity(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NonNull
    private String code;

    @Column
    @NonNull
    private Boolean isAvailable;

    @Column
    private int quantity;


    @ToString.Exclude
    @ManyToMany(mappedBy = "stocks")
    private List<Book> books;

}
