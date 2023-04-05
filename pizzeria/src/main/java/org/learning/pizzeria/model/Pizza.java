package org.learning.pizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotNull
    @Size(min=3, max=200)
    private String nome;

    @NotEmpty
    @NotNull
    @Size(min=3, max=250)
    private String descrizione;

    @NotNull
    @DecimalMin("1.00")
    @DecimalMax("50.00")
    private BigDecimal prezzo;

    @OneToMany(mappedBy = "pizza")
    private List<Offerta> offerteList;

    public List<Offerta> getOfferteList() {
        return offerteList;
    }
    public void setOfferteList(List<Offerta> offerteList) {
        this.offerteList = offerteList;
    }

    public Pizza() {
        super();
    }

    public Pizza(String nome, String descrizione, BigDecimal prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
