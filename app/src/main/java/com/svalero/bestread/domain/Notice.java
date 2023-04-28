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
@Entity(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NonNull
    private String code;

    @Column
    @NonNull
    private String titleNotice;

    @Column
    @NonNull
    private String description;

    @Column
    private LocalDate dateSendNotice;

    @Column
    private float rating;

    @Column
    @NonNull
    private boolean hasRead;

    @Column
    private long bookId;

    @ToString.Exclude
    @JsonBackReference(value = "user_notices")
    @ManyToOne
    @JoinColumn(name = "user_notices")
    private User userNotices;

    @ToString.Exclude
    @ManyToMany(mappedBy = "notices")
    private List<Rent> rents;

    @ToString.Exclude
    @ManyToMany(mappedBy = "noticesByBooks")
    private List<Book> books;

}
