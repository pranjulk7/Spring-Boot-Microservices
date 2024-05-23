package com.eazybytes.account.entites;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Id
    private Long accountNumber;

    private String accountType;
    private String branchAddress;

}
