package sn.unchk.unchk_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "annonces")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String contenu;
    private LocalDate datePublication;
    private String cible;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    @JsonIgnoreProperties({"motDePasse", "createdAt", "actif"})
    private Utilisateur auteur;
}