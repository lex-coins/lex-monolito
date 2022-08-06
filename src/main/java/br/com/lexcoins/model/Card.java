package br.com.lexcoins.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_CARDS")
@Data
public class Card{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String network;
    @Column(nullable = false, unique = true)
    private String number;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cvv;
    @Column(nullable = false)
    private String expiration;


}