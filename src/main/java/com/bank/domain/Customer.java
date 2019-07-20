package com.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;  

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            targetEntity = Account.class,
            mappedBy = "customer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
