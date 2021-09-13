package com.example.servingwebcontent.user.order;


//import by.yurachel.springapp.model.phone.Phone;
import com.example.servingwebcontent.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;


    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne()
    private User user;

//    @ManyToMany()
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Phone> phones = new ArrayList<>();

    public Order() {
        creationDate = new Date();
    }


}
