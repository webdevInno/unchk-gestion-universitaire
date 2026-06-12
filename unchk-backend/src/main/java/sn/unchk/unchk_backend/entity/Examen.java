package sn.unchk.unchk_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "examens")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private LocalDate dateExamen;
    private String salle;
    private String duree;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    @JsonIgnoreProperties({"formateur", "description"})
    private Cours cours;
}