package sn.unchk.unchk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.unchk.unchk_backend.entity.Formateur;
import java.util.Optional;  // ← ajouter

import java.util.List;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {

    // Recherche par type (Enseignant, Tuteur...)
    List<Formateur> findByType(String type);

    // Recherche par spécialité
    List<Formateur> findBySpecialite(String specialite);

    // Recherche par formation rattachée
    List<Formateur> findByFormation(String formation);

    // Filtrer par statut
    List<Formateur> findByStatut(String statut);
    // Recherche par email (pour le profil)
    Optional<Formateur> findByEmail(String email);  // ← ajouter

    // Compter par statut
    long countByStatut(String statut);

    // Compter par type
    long countByType(String type);
    boolean existsByEmail(String email);

    // Recherche globale (nom, prénom, spécialité, formation)
    @Query("SELECT f FROM Formateur f WHERE " +
            "LOWER(f.nom) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.prenom) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.specialite) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.formation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(f.type) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Formateur> rechercheGlobale(@Param("search") String search);
}