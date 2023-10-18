package com.acl.hexagonal.domain;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Card {
    private String cardNumber;
    private String ownerName;
    private double balance;
}
