package com.example.cim.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_tracker")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerOrder;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemOrder;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "quantity")
    private Integer quantity;

}
