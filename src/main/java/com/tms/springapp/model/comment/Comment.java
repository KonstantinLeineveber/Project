package com.tms.springapp.model.comment;

import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Entity
@Table(name = "comments")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @NotEmpty(message = "Text should not be empty")
    private String text;
    @NotEmpty(message = "Release date should not be empty")
    private String releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    public Comment() {
    }

}
