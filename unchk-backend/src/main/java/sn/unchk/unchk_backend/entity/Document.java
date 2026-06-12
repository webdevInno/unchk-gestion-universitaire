package sn.unchk.unchk_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    // Courrier arrivé / Courrier départ / Note de service /
    // Note administrative / Circulaire / Budget
    private String type;

    private String reference;

    @Column(name = "date_doc")
    private LocalDate dateDoc;

    @Column(length = 1000)
    private String description;

    @Column(length = 2000)
    private String contenu;

    @Column(name = "fichier_url")
    private String fichierUrl;

    // Qui peut voir ce document
    // Tous / Etudiant / Enseignant / Admin
    @Column(name = "role_acces")
    private String roleAcces = "Tous";

    // Actif / Archivé
    private String statut = "Actif";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Utilisateur auteur;
}