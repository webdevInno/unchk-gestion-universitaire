package sn.unchk.unchk_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comptes_rendus")
public class CompteRendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String type;
    private LocalDateTime dateReunion;
    private String lieu;

    @Column(columnDefinition = "TEXT")
    private String participants;

    @Column(columnDefinition = "TEXT")
    private String ordreJour;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    @Column(columnDefinition = "TEXT")
    private String decisions;

    @Column(columnDefinition = "TEXT")
    private String recommandations;

    private String fichierUrl;
    private Boolean publique = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Utilisateur auteur;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}