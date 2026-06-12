package sn.unchk.unchk_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seances_cours")
public class SeanceCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jour;
    private String heureDebut;
    private String heureFin;
    private String salle;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    @JsonIgnoreProperties({"description", "formateur"})
    private Cours cours;
}