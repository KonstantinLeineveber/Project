package com.tms.springapp.model.comment;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Comment implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private long film_id;
    private String userName;

    @NotEmpty(message = "Comment should not be empty")
    private String comment;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> comments = new ArrayList<>();

    public Comment() {
    }


}
