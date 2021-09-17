package com.tms.springapp.model.film;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Film implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @NotEmpty(message = "Film name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;
    @NotEmpty(message = "Img link should not be empty")
    private String img;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @NotEmpty(message = "Release date should not be empty")
    private String releaseDate;
    @NotEmpty(message = "Story should not be empty")
    private String story;
    @NotEmpty(message = "Actors should not be empty")
    private String actors;


    private static final long serialVersionUID = 6295618226040646585L;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> images = new ArrayList<>();


    public Film() {
    }

    public Film(String name, String story, String img) {
        this.name = name;
        this.story = story;
        this.img = img;
    }

    public Film(long id, String name, String story) {
        this.id = id;
        this.name = name;
        this.story = story;
    }

    public Film(String name, String story) {
        this.name = name;
        this.story = story;
    }


}
