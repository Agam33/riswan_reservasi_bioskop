package com.ra.nontonfilm.model.film;

import com.ra.nontonfilm.model.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "orders")
public class Orders {
    @Id
    private String id;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
}
