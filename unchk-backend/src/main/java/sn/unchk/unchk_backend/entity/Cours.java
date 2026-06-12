package sn.unchk.unchk_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String formation;
    private Integer nbEtudiants = 0;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    @JsonIgnoreProperties({"motDePasse", "createdAt", "actif"})
    private Utilisateur formateur;
}