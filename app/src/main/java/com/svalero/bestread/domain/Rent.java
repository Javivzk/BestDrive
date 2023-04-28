package com.svalero.bestread.domain;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NonNull
    private String code;

    @Column
    private LocalDate startRent;

    @Column
    private LocalDate endRent;

    @Column
    @NonNull
    private boolean isReturned;

    @Column
    @NonNull
    private float totalPrice;

    @ToString.Exclude
    @OneToMany(mappedBy = "rent")
    private List<Book> book;

    @ToString.Exclude
    @OneToMany(mappedBy = "userRents")
    private List<User> user;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "notice_rent",
            joinColumns = @JoinColumn(name = "rent_id"),
            inverseJoinColumns = @JoinColumn(name = "notice_id"))
    private List <Notice> notices;

}
